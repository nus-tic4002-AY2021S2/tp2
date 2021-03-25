package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDTAG;
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

public class AddTagCommand extends Command {

    public static final String COMMAND_WORD = "addTag";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Add the tag of the person identified "
            + "by the index number used in the last person listing. "
            + "New tag will be added by the input.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_ADDTAG + "[REMARK]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_ADDTAG + "CalledTwice";

    public static final String MESSAGE_ADD_TAG_SUCCESS = "Added tag to Person: %1$s";

    private final Index index;
    private final Tag tag;


    /**
     * @param index of the person in the filtered person list to edit the tag
     * @param tag of the person to be added/removed
     */
    public AddTagCommand(Index index, Tag tag) {
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

        Set<Tag> oldTags = personToEdit.getTags();
        List<Tag> newTags = new ArrayList<>(oldTags);
        newTags.add(tag);
        Set<Tag> updatedTags = Set.copyOf(newTags);
        Person editedPerson = new Person(personToEdit.getName(), personToEdit.getDate(),
                personToEdit.getFollowUp(), personToEdit.getNric(), personToEdit.getPhone(),
                personToEdit.getEmail(), personToEdit.getAddress(), personToEdit.getDescription(),
                personToEdit.getRemark(), updatedTags);

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(generateSuccessMessage(editedPerson));
    }

    /**
     * Generates a command execution success message based on whether the remark is added to or removed from
     * {@code personToEdit}.
     */
    private String generateSuccessMessage(Person personToEdit) {
        return String.format(MESSAGE_ADD_TAG_SUCCESS, personToEdit);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddTagCommand)) {
            return false;
        }

        // state check
        AddTagCommand e = (AddTagCommand) other;
        return index.equals(e.index)
                && tag.equals(e.tag);
    }
}
