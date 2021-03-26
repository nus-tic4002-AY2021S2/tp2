package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Comparator;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.medical.MedicalHistory;

public class AddMedicalCommand extends Command {
    public static final String COMMAND_WORD = "addMed";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": To add medical history to patient, please enter"
            + " the id of the person you want to add \nmedical history, and follow by '\\d +  history description'"
            + " For example : \n"
            + "usage 'addMed 1 \\d Two month of Panandol'";

    public static final String SUCCESS_MESSAGE_USAGE = COMMAND_WORD + ": Medical history has been successfully added"
            + " to this patient.";

    private final Index targetIndex;

    private final String description;

    private final String dateString;

    /**
     * @param targetIndex
     * @param description
     * @param dateString
     */
    public AddMedicalCommand(Index targetIndex, String description, String dateString) {
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

        Person personToAddMed = lastShownList.get(targetIndex.getZeroBased());

        Person editedPerson = personToAddMed;
        Integer maxIndex = 0;
        if (editedPerson.getMedicalHistories().size() != 0) {
            maxIndex = editedPerson.getMedicalHistories().stream()
                    .max(Comparator.comparing(v -> v.getIndex())).get().getIndex();
        }

        editedPerson.getMedicalHistories().add(new MedicalHistory(description, ++maxIndex));
        model.setPerson(personToAddMed, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(String.format(SUCCESS_MESSAGE_USAGE, personToAddMed),
                SUCCESS_MESSAGE_USAGE);
    }

}
