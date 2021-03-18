package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

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



/**
 * Finds and lists all persons in patient book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class ViewAppointmentCommand extends Command {

    public static final String COMMAND_WORD = "viewApp";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all appointment of this person";

    private final Index targetIndex;

    public ViewAppointmentCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToShowAppointment = lastShownList.get(targetIndex.getZeroBased());

        String[] listName = personToShowAppointment.getName().fullName.split(" ");
        ArrayList<String> arrayList = new ArrayList(Arrays.asList(listName));
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(arrayList);
        model.updateFilteredPersonList(predicate);
        Person editedPerson = personToShowAppointment;
        editedPerson.setViewAppInd(true);
        model.setPerson(personToShowAppointment, editedPerson);

        return new CommandResult(String.format(MESSAGE_USAGE, personToShowAppointment),
                MESSAGE_USAGE);
    }

    private static Person createEditedPerson(Person personToEdit,
                                             EditCommand.EditPersonDescriptor editPersonDescriptor) {
        assert personToEdit != null;

        Name updatedName = editPersonDescriptor.getName().orElse(personToEdit.getName());
        Phone updatedPhone = editPersonDescriptor.getPhone().orElse(personToEdit.getPhone());
        Email updatedEmail = editPersonDescriptor.getEmail().orElse(personToEdit.getEmail());
        Address updatedAddress = editPersonDescriptor.getAddress().orElse(personToEdit.getAddress());
        Set<Tag> updatedTags = editPersonDescriptor.getTags().orElse(personToEdit.getTags());
        Set<Appointment> updatedAppointments = editPersonDescriptor.getAppointments()
                .orElse(personToEdit.getAppointment());
        Set<MedicalHistory> updatedMedicalHistory = editPersonDescriptor.getMedicalHistories()
                .orElse(personToEdit.getMedicalHistories());
        return new Person(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedTags,
                updatedAppointments, updatedMedicalHistory);
    }

}
