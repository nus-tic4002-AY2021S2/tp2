package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.group.Group;
import seedu.address.model.group.GroupList;



/**
 * Deletes a Group identified using it's displayed index from show command in address book.
 */
public class DeleteGroupCommand extends Command {
    public static final String COMMAND_WORD = "deletegrp";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the group identified by the index number used in the displayed group list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SUCCESS = "Group deleted successfully: %1$s";
    //public static final String MESSAGE_PersonInGroup = "Cannot be deleted as there are persons in this group";



    private final Index targetIndex;

    public DeleteGroupCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Group groupName = GroupList.getGroup(targetIndex.getOneBased());
        int count = model.countPersonInGroup(Model.predicateShowAllPersonsInGroup(groupName));
        //System.out.println(count);
        //System.out.println(groupName.toString());
        if (count > 0) {
            throw new CommandException(Messages.MESSAGE_PERSON_IN_GROUP);
        }
        GroupList.deleteGroup(targetIndex.getOneBased());
        return new CommandResult(String.format(MESSAGE_SUCCESS, groupName.toString()));
    }
}
