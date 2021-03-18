package seedu.address.logic.commands;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.appointment.Appointment;
import seedu.address.model.person.medical.MedicalHistory;
import seedu.address.model.tag.Tag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

public class AddAppointmentCommand extends Command {
    public static final String COMMAND_WORD = "addApp";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": add appointment to this person";

    private final Index targetIndex;

    private final String description;

    private final String dateString;

    public AddAppointmentCommand(Index targetIndex, String description, String dateString) {
        this.targetIndex = targetIndex;
        this.description = description;
        this.dateString = dateString;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToAddApp = lastShownList.get(targetIndex.getZeroBased());

        Person editedPerson = personToAddApp;
        editedPerson.getAppointments().add(new Appointment(description,0,dateString));
        model.setPerson(personToAddApp, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(String.format(MESSAGE_USAGE, personToAddApp),
                MESSAGE_USAGE);
    }

}
