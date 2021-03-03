package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.SendCommand;
import seedu.address.logic.parser.exceptions.ParseException;


public class SendCommandParser implements Parser<SendCommand> {

    /**
     * validate send command.
     */

    public SendCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SendCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");
        return new SendCommand(nameKeywords);
    }
}
