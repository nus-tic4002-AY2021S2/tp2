package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.DeleteGroupCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;


public class DeleteGroupCommandParser implements Parser<DeleteGroupCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteGroupCommand
     * and returns a DeleteGroupCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteGroupCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteGroupCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE), pe);
        }
    }


}
