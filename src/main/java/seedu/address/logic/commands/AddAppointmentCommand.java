package seedu.address.logic.commands;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Appointment;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static java.util.Objects.requireNonNull;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class AddAppointmentCommand extends Command {
    private static final String MESSAGE_ADD_PERSON_APPOINTMENT_SUCCESS = "Success";
    private SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD");

    public static final String COMMAND_WORD = "addap";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Add appointment to a person "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alice bob charlie";

    private String details;
    private Date date;
    private Index targetIndex;

    public AddAppointmentCommand(String details, String dateString, Index index) throws ParseException {
        this.details  = details;
        this.date = sdf.parse(dateString);
        this.targetIndex = index;

    }

    @Override
    public CommandResult execute(Model model) throws CommandException {

        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToAddAddApointments = lastShownList.get(targetIndex.getZeroBased());
        Appointment appointment = new Appointment(details,date);
        model.addAppointmentToPerson(personToAddAddApointments,appointment);
        return new CommandResult(String.format(MESSAGE_ADD_PERSON_APPOINTMENT_SUCCESS, personToAddAddApointments));
    }

}
