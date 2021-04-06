package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.medical.MedicalHistory;

public class DeleteMedicalCommand extends Command {
    public static final String COMMAND_WORD = "deleteMed";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": To delete medical history for patient, please enter"
            + " the first id of the person you \nwant to delete medical history"
            + ", and follow by 'medical history id' for patient" + " For example : \n"
            + "usage 'deleteMed 1 2'";

    public static final String SUCCESS_MESSAGE_USAGE = COMMAND_WORD + ": Medical history has been successfully "
            + "deleted"
            + " for this patient.";

    public static final String OUT_INDEX_MESSAGE_USAGE = COMMAND_WORD + ": Medical index is out of bound, please check "
            + "again.";
    private final Index targetIndex;

    private final Integer secondIndex;

    /**
     * @param targetIndex
     * @param secondIndex
     */
    public DeleteMedicalCommand(Index targetIndex, Integer secondIndex) {
        this.targetIndex = targetIndex;
        this.secondIndex = secondIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToDelApp = lastShownList.get(targetIndex.getZeroBased());

        Person editedPerson = personToDelApp;
        List<MedicalHistory> sortedList = new ArrayList<>(editedPerson.getMedicalHistories());
        Collections.sort(sortedList);
        if (secondIndex > sortedList.size()) {
            return new CommandResult(String.format(OUT_INDEX_MESSAGE_USAGE, personToDelApp),
                    OUT_INDEX_MESSAGE_USAGE);
        }
        sortedList.remove(secondIndex - 1);
        Set<MedicalHistory> editedSet = new HashSet<>(sortedList);
        editedPerson.setMedicalHistories(editedSet);
        model.setPerson(personToDelApp, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(String.format(SUCCESS_MESSAGE_USAGE, personToDelApp),
                SUCCESS_MESSAGE_USAGE);
    }

}
