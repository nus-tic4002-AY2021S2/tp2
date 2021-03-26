package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.medical.MedicalHistory;


/**
 * Finds and lists all persons in patient book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */

public class ViewMedicalCommand extends Command {

    public static final String COMMAND_WORD = "viewMed";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all medical history of this person, "
            + "please enter"
            + " the id of the person you want \nto view medical history, for example : \n"
            + "usage 'viewMed 1'";

    public static final String SUCCESS_MESSAGE_USAGE = COMMAND_WORD + ": This person's medical history "
            + "details has been listed.";

    private final Index targetIndex;

    public ViewMedicalCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToShowMedicalHistory = lastShownList.get(targetIndex.getZeroBased());

        StringBuilder viewMedicalHistory = new StringBuilder();
        viewMedicalHistory.append("Here is the Medical History of " + personToShowMedicalHistory.getName() + " : \n");
        List<MedicalHistory> medicalHistories = new ArrayList<>(personToShowMedicalHistory.getMedicalHistories());
        Collections.sort(medicalHistories);
        Integer count = 0;
        for (MedicalHistory medicalHistory : medicalHistories) {
            viewMedicalHistory.append(++count + ". " + medicalHistory.getMedicalHistoryDescription() + "\n");

        }

        return new CommandResult(String.format(SUCCESS_MESSAGE_USAGE, personToShowMedicalHistory),
                viewMedicalHistory.toString());
    }

    public MedicalRecordResult executeViewMedical(Model model) {
        return null;
    }
}
