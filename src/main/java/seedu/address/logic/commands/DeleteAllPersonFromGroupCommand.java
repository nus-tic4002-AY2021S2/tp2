package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.ArrayList;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.group.Group;
import seedu.address.model.person.Person;

public class DeleteAllPersonFromGroupCommand extends Command {

    public static final String COMMAND_WORD = "deletepsngrp";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": delete all person in a group. "
            + "Parameters: " + PREFIX_GROUP + "GROUP NAME ";

    public static final String MESSAGE_SUCCESS = "all Person has been deleted from Group %1$s. ";
    public static final String MESSAGE_NO_EXIST_GROUP = "The Group doesn't exists in the address book";

    private final Group group;

    public DeleteAllPersonFromGroupCommand(Group group) {
        requireNonNull(group);
        this.group = group;
    }

    /**
     * Run the process of deleting all people from the group
     * @param model {@code Model} which the command should operate on.
     * @return Message success
     * @throws CommandException if group does not exists
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasGroup(this.group)) {
            throw new CommandException(MESSAGE_NO_EXIST_GROUP);
        }

        // select all people in this.group
        ArrayList<Person> personInGroup = model.getPersonListInThisGroup(this.group);
        // remove this.group from each of them
        for (int i = 0; i < personInGroup.size(); i++) {
            Person person = personInGroup.get(i);
            model.unAssignPersonToGroup(person);
        }

        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_SUCCESS, group));
    }
}
