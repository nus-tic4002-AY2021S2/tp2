package seedu.address.logic;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteMedicalCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class DeleteMedicalCommandParser implements Parser<DeleteMedicalCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteMedicalCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args.trim().split(" ")[0]);
            Integer secondInd = Integer.parseInt(args.trim().split(" ")[1].trim());

            return new DeleteMedicalCommand(index, secondInd);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteMedicalCommand.MESSAGE_USAGE), pe);
        }
    }

}
