package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;

import seedu.address.logic.commands.AddGroup;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.group.Group;

public class CreateGroupParser {
    /**
     * return an AddGroup command object that contains the value of user inputted group name
     * @param args contains group name
     * @return an AddGroup command object
     * @throws ParseException if parsing of group value fails
     */
    public AddGroup parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_GROUP);

        Group group = null;
        if (argMultimap.getValue(PREFIX_GROUP).isPresent()) {
            group = ParserUtil.parseGroup(argMultimap.getValue(PREFIX_GROUP).get());
        }

        return new AddGroup(group);

    }
}
