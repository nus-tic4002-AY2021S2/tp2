package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;

import java.util.stream.Stream;

import seedu.address.logic.commands.AddGroupCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.group.Group;

public class CreateGroupParser {
    /**
     * return an AddGroup command object that contains the value of user inputted group name
     * @param args contains group name
     * @return an AddGroup command object
     * @throws ParseException if parsing of group value fails
     */
    public AddGroupCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_GROUP);

        if (!arePrefixesPresent(argMultimap, PREFIX_GROUP)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddGroupCommand.MESSAGE_USAGE));
        }

        Group group = null;
        if (argMultimap.getValue(PREFIX_GROUP).isPresent()) {
            group = ParserUtil.parseGroup(argMultimap.getValue(PREFIX_GROUP).get());
        }

        return new AddGroupCommand(group);

    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
