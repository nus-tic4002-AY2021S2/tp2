package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMOVETAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;


/**
 * Changes the tags of an existing person in the address book.
 */
public class RemoveTagCommand extends Command {

    public static final String COMMAND_WORD = "removeTag";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Remove the tag of the person identified "
            + "by the index number used in the last person listing. "
            + "Existing tags will be overwritten by the input.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_REMOVETAG + "[REMARK]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_REMOVETAG + "CalledTwice";

    public static final String MESSAGE_REMOVE_TAG_SUCCESS = "Removed tag from Person: %1$s";

    private final Index index;
    private final Tag tag;

    /**
     * @param index of the person in the filtered person list to edit the tag
     * @param tag of the person to be added/removed
     */
    public RemoveTagCommand(Index index, Tag tag) {
        requireAllNonNull(index, tag);
        this.index = index;
        this.tag = tag;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        if (personToEdit.getTags().contains(tag) && !personToEdit.getTags().isEmpty()) {
            Set<Tag> oldTags = personToEdit.getTags();
            List<Tag> newTags = new ArrayList<>(oldTags);
            newTags.remove(tag);
            Set<Tag> updatedTags = Set.copyOf(newTags);
            Person editedPerson = new Person(personToEdit.getName(), personToEdit.getDate(),
                    personToEdit.getFollowUp(), personToEdit.getNric(), personToEdit.getPhone(),
                    personToEdit.getEmail(), personToEdit.getAddress(), personToEdit.getDescription(),
                    personToEdit.getRemark(), updatedTags);

            model.setPerson(personToEdit, editedPerson);
            model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

            return new CommandResult(generateSuccessMessage(editedPerson));
        }
        throw new CommandException("Incorrect tag name, please make sure the tag name is correct "
                + "or the tag is empty, please add a new tag instead");
    }

    /**
     * Generates a command execution success message based on whether the remark is added to or removed from
     * {@code personToEdit}.
     */
    private String generateSuccessMessage(Person personToEdit) {
        return String.format(MESSAGE_REMOVE_TAG_SUCCESS, personToEdit);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RemoveTagCommand)) {
            return false;
        }

        // state check
        RemoveTagCommand e = (RemoveTagCommand) other;
        return index.equals(e.index)
                && tag.equals(e.tag);
    }
}
