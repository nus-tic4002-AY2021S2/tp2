package employeetracker.logic.parser;

import static employeetracker.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import employeetracker.logic.commands.SortCommand;
import employeetracker.logic.parser.exceptions.ParseException;


/**
 * Parses input arguments and creates a new SortCommand object
 */
public class SortCommandParser implements Parser<SortCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the SortCommand
     * and returns an SortCommand object for execution.
     * @throws ParseException if the input field does not conform the expected format
     */
    public SortCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }

        if (!trimmedArgs.equalsIgnoreCase("n")
                && !trimmedArgs.equalsIgnoreCase("s")
                && !trimmedArgs.equalsIgnoreCase("j")
                && !trimmedArgs.equalsIgnoreCase("b")) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }

        return new SortCommand(trimmedArgs);
    }
}
