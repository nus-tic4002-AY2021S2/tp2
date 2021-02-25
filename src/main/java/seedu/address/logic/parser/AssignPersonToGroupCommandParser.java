package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.stream.Stream;

import seedu.address.logic.commands.AssignPersonToGroupCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.group.Group;
import seedu.address.model.person.Name;


public class AssignPersonToGroupCommandParser {
    /**
     * return an AssignPersonToGroupCommand command object that contains the value of user inputted person name
     * and group name
     * @param args contains person name and group name
     * @return an AssignPersonToGroupCommand command object
     * @throws ParseException if parsing of person or group value fails
     */
    public AssignPersonToGroupCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_GROUP);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_GROUP)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AssignPersonToGroupCommand.MESSAGE_USAGE));
        }

        Group group = ParserUtil.parseGroup(argMultimap.getValue(PREFIX_GROUP).get());
        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());

        return new AssignPersonToGroupCommand(group, name);

    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
