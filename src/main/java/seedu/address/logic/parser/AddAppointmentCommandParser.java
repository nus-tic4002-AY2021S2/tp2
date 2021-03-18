package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddAppointmentCommand;
import seedu.address.logic.commands.ViewAppointmentCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;


/**
 * Parses input arguments and creates a new FindCommand object
 */
public class AddAppointmentCommandParser implements Parser<AddAppointmentCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddAppointmentCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args.trim().split(" ")[0]);
            String s = args;

            String description = s.substring(s.indexOf("/d") + 3, s.indexOf("/t"));

            String dateString =  s.substring(s.indexOf("/t") + 3);

            return new AddAppointmentCommand(index,description,dateString);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddAppointmentCommand.MESSAGE_USAGE), pe);
        }
    }

}
