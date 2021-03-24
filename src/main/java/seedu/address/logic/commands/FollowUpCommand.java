package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FOLLOWUP;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.FollowUp;
import seedu.address.model.person.Person;


public class FollowUpCommand extends Command {

    public static final String COMMAND_WORD = "followUp";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the follow up of the person identified "
            + "by the index number used in the last person listing. "
            + "Existing follow up will be overwritten by the input.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_FOLLOWUP + "[FOLLOW]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_FOLLOWUP + "7";

    public static final String MESSAGE_ADD_FOLLOWUP_SUCCESS = "Added follow up to Person: %1$s";
    public static final String MESSAGE_DELETE_FOLLOWUP_SUCCESS = "Removed follow up from Person: %1$s";

    private final Index index;
    private final FollowUp followUp;

    /**
     * @param index of the person in the filtered person list to edit the followUp
     * @param followUp of the person to be updated to
     */
    public FollowUpCommand(Index index, FollowUp followUp) {
        requireAllNonNull(index, followUp);

        this.index = index;
        this.followUp = followUp;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {

        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());

        Person editedPerson = new Person(personToEdit.getName(), personToEdit.getDate(), followUp,
                personToEdit.getNric(), personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getAddress(), personToEdit.getDescription(), personToEdit.getRemark(),
                personToEdit.getTags());

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(generateSuccessMessage(editedPerson));
    }


    /**
     * Generates a command execution success message based on whether the remark is added to or removed from
     * {@code personToEdit}.
     */
    private String generateSuccessMessage(Person personToEdit) {
        String message = !followUp.value.isEmpty() ? MESSAGE_ADD_FOLLOWUP_SUCCESS : MESSAGE_DELETE_FOLLOWUP_SUCCESS;
        return String.format(message, personToEdit);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FollowUpCommand)) {
            return false;
        }

        // state check
        FollowUpCommand e = (FollowUpCommand) other;
        return index.equals(e.index)
                && followUp.equals(e.followUp);
    }
}
