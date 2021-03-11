package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.model.Model.predicateShowAllPersonsInGroup;

import seedu.address.model.Model;
import seedu.address.model.group.Group;

/**
 * Lists all persons in the specific group to the user.
 */
public class ListAllFromGroupCommand extends Command {

    public static final String COMMAND_WORD = "listfromgrp";

    public static final String MESSAGE_SUCCESS = "Listed all persons from the group";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": List persons in the group. "
            + "Parameters: " + PREFIX_GROUP + "NAME ";

    private final Group toList;

    /**
     * Creates a ListAllFromGroupCommand to list all the person from the specified {@code Group}
     */
    public ListAllFromGroupCommand(Group group) {
        requireNonNull(group);
        toList = group;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicateShowAllPersonsInGroup(toList));
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
