package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.address.model.Model;
import seedu.address.model.group.GroupList;

public class ShowCommand extends Command {
    public static final String COMMAND_WORD = "show";

    public static final String MESSAGE_SUCCESS = "Showed all group";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        CommandResult commandResult = new CommandResult(GroupList.listGroups(),false, false);
        return commandResult;
    }
}
