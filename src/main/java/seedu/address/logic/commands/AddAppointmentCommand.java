package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.appointment.Appointment;

public class AddAppointmentCommand extends Command {

    public static final String COMMAND_WORD = "addApp";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": To add appointments to patient, please enter"
            + " the id of the person you want to add appointments,\nand follow by '\\d +  appointment description'"
            + " and follow by '\\t YYYY-MM-DD HH:mm:ss' \ndate format must be exactly correct. For example : \n"
            + "usage 'addApp 1 \\d Meeting doctor for medical checkup \\t 2021-03-04 10:00:00'";

    public static final String SUCCESS_MESSAGE_USAGE = COMMAND_WORD + ": Appointment has been successfully added  "
            + "to this patient.";

    public static final String DUPLICATE_MESSAGE_USAGE = COMMAND_WORD + ": Appointment with same time already existed, "
            + "Please double check the timing.";

    private final Index targetIndex;

    private final String description;

    private final String dateString;

    private final String dateFormat = "yyyy-MM-dd";
    /**
     *
     * @param targetIndex
     * @param description
     * @param dateString
     */
    public AddAppointmentCommand(Index targetIndex, String description, String dateString) {
        this.targetIndex = targetIndex;
        this.description = description;
        this.dateString = dateString;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        LocalDate date = null;

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToAddApp = lastShownList.get(targetIndex.getZeroBased());

        String[] onlyDate = dateString.split(" ");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(dateFormat)
                .withResolverStyle(ResolverStyle.SMART);

        try {
            date = LocalDate.parse(onlyDate[0], dateFormatter);
        } catch (DateTimeParseException e) {
            throw new CommandException(Messages.MESSAGE_INVALID_DATE_FORMAT);
        }

        Person editedPerson = personToAddApp;
        List<Appointment> appointments = new ArrayList<>(editedPerson.getAppointments());
        for (Appointment appointment : appointments) {
            boolean existed = appointment.getDate().equals(dateString);
            if (existed) {
                return new CommandResult(String.format(DUPLICATE_MESSAGE_USAGE, personToAddApp),
                        DUPLICATE_MESSAGE_USAGE);
            }
        }

        editedPerson.getAppointments().add(new Appointment(description, 1, dateString));
        model.setPerson(personToAddApp, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(String.format(SUCCESS_MESSAGE_USAGE, personToAddApp),
                SUCCESS_MESSAGE_USAGE);
    }

}
