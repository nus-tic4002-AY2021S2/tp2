package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;

import java.text.SimpleDateFormat;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddAppointmentCommand;
import seedu.address.logic.commands.AddMedicalCommand;
import seedu.address.logic.parser.exceptions.ParseException;


/**
 * Parses input arguments and creates a new FindCommand object
 */
public class AddAppointmentCommandParser implements Parser<AddAppointmentCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddAppointmentCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TIME, PREFIX_DESCRIPTION);
        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());

            if (argMultimap.getValue(PREFIX_TIME).isPresent() && argMultimap.getValue(PREFIX_DESCRIPTION).isPresent()) {
                String timeString = argMultimap.getValue(PREFIX_TIME).get();
                String description = argMultimap.getValue(PREFIX_DESCRIPTION).get();
                if (description.equals("") || description == null) {
                    throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                            AddMedicalCommand.MESSAGE_USAGE));
                }
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-DD HH:mm:ss");
                simpleDateFormat.parse(timeString);
                return new AddAppointmentCommand(index, description.trim(), timeString);
            } else {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        AddAppointmentCommand.MESSAGE_USAGE));
            }
        } catch (ParseException | java.text.ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddAppointmentCommand.MESSAGE_USAGE), pe);
        }
    }

}
