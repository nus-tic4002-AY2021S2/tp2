package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.group.Group;


public class AddGroupCommand extends Command {

    public static final String COMMAND_WORD = "create";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Create a group to the address book. "
                                                + "Parameters: " + PREFIX_GROUP + "NAME ";

    public static final String MESSAGE_SUCCESS = "New Group added: %1$s";
    public static final String MESSAGE_DUPLICATE_GROUP = "This Group already exists in the address book";

    private final Group toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddGroupCommand(Group group) {
        requireNonNull(group);
        toAdd = group;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasGroup(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_GROUP);
        }

        model.addGroup(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }


}
