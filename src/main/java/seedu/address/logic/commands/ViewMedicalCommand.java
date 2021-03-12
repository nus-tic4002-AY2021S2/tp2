package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;


/**
 * Finds and lists all persons in patient book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */

public class ViewMedicalCommand extends Command {

    public static final String COMMAND_WORD = "viewMed";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": View all medical records of this person";

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

        return new CommandResult(String.format(MESSAGE_USAGE, personToShowMedicalHistory));
    }

    public MedicalRecordResult executeViewMedical(Model model) {
        return null;
    }
}
