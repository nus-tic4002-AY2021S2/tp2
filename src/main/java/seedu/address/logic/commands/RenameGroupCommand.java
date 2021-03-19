package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.group.Group;
import seedu.address.model.group.GroupList;

public class RenameGroupCommand extends Command {
    public static final String COMMAND_WORD = "rename";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Rename the group identified by the index number used in the displayed group list and a specified "
            + "new name.\n"
            + "Parameters: INDEX (must be a positive integer) " + PREFIX_GROUP + " GROUP_NAME\n"
            + "Example: " + COMMAND_WORD + " 1" + PREFIX_GROUP + "NEW_GROUP_NAME";

    public static final String MESSAGE_SUCCESS = "Group index %1$d rename successfully: %2$s";
    public static final String MESSAGE_INVALID_INDEX = "The index specified is invalid.";

    private final Index targetIndex;
    private final Group group;

    /**
     * @param targetIndex the target specified index
     * @param group the renamed group name
     */
    public RenameGroupCommand(Index targetIndex, Group group) {
        this.targetIndex = targetIndex;
        this.group = group;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (targetIndex.getOneBased() <= 0 || targetIndex.getOneBased() > model.getGroupSize()) {
            throw new CommandException(MESSAGE_INVALID_INDEX);
        }

        model.renameGroup(targetIndex.getOneBased(), group.toString());
        return new CommandResult(String.format(MESSAGE_SUCCESS, targetIndex.getOneBased(), group.toString()));
    }
}
