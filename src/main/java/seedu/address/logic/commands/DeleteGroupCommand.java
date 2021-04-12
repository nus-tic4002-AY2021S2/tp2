package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.ArrayList;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.group.Group;
import seedu.address.model.group.GroupList;
import seedu.address.model.person.Person;



/**
 * Deletes a Group identified using it's displayed index from show command in address book.
 */
//@SuppressWarnings("checkstyle:Regexp")
public class DeleteGroupCommand extends Command {
    public static final String COMMAND_WORD = "deletegrp";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the group identified by the index number used in the displayed group list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SUCCESS = "Group deleted successfully: %1$s";
    public static final String MESSAGE_WARNING = "This index is not valid. Please check";
    private final Index targetIndex;

    public DeleteGroupCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (targetIndex.getOneBased() > (GroupList.getGroupListSize() - 1)) {
            //group size has one hidden element, the N/A group. This needs to be deducted.
            return new CommandResult(String.format(MESSAGE_WARNING));
        }
        Group groupName = GroupList.getGroup(targetIndex.getOneBased() + 1);
        if (model.countPersonInGroup(Model.predicateShowAllPersonsInGroup(groupName)) > 0) {
            //throw new CommandException(Messages.MESSAGE_PERSON_IN_GROUP);
            //new DeleteAllPersonFromGroupCommand(groupName);
            ArrayList<Person> personInGroup = model.getPersonListInThisGroup(groupName);
            for (int i = 0; i < personInGroup.size(); i++) {
                Person person = personInGroup.get(i);
                model.unAssignPersonToGroup(person);
            }
            model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        }
        GroupList.deleteGroup(targetIndex.getOneBased() + 1);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_SUCCESS, groupName.toString()));
    }
}
