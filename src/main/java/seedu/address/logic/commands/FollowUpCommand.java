package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FOLLOWUP;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.FollowUp;
import seedu.address.model.person.Person;


public class FollowUpCommand extends Command {

    public static final String COMMAND_WORD = "followUp";
    public static final String MESSAGE_INVALID = "FollowUp should only contain positive integers, must be less than 366"
            + " days and it should not be blank";
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

        if (!followUp.value.matches("[0-9]+")) {
            return new CommandResult(MESSAGE_INVALID);
        } else {
            int day = Integer.parseInt(followUp.value);
            if (day > 365) {
                return new CommandResult(MESSAGE_INVALID);
            } else {
                List<Person> lastShownList = model.getFilteredPersonList();

                if (index.getZeroBased() >= lastShownList.size()) {
                    throw new CommandException("Please enter a valid index number from 1 to "
                        + model.getAddressBook().getPersonList().size() + ".");
                }

                Person personToEdit = lastShownList.get(index.getZeroBased());

                Person editedPerson = new Person(personToEdit.getName(), personToEdit.getDate(), followUp,
                        personToEdit.getNric(), personToEdit.getPhone(), personToEdit.getEmail(),
                        personToEdit.getAddress(), personToEdit.getDescription(), personToEdit.getRemark(),
                        personToEdit.getTags());

                model.setPerson(personToEdit, editedPerson);
                model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

                return new CommandResult("The follow up days has been changed.");
            }
        }
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
