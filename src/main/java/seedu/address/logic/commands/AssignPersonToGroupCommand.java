package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.group.Group;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;

public class AssignPersonToGroupCommand extends Command {
    public static final String COMMAND_WORD = "assignptg";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Assign person to a group. "
            + "Parameters: " + PREFIX_NAME + "PERSON Name" + PREFIX_GROUP + "GROUP NAME ";

    public static final String MESSAGE_SUCCESS = "Person %1$s has been assigned to Group %2$s. ";
    public static final String MESSAGE_NO_EXIST_GROUP = "The Group assigned doesn't exists in the address book";
    public static final String MESSAGE_NO_EXIST_PERSON = "The Person assigned doesn't exists in the address book";

    private final Group group;
    private final Name name;

    /**
     * @param group A group object
     * @param name A person name object
     */
    public AssignPersonToGroupCommand(Group group, Name name) {
        requireNonNull(group);
        requireNonNull(name);
        this.group = group;
        this.name = name;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasGroup(this.group)) {
            throw new CommandException(MESSAGE_NO_EXIST_GROUP);
        }

        Person person = model.getPerson(this.name);
        if (person == null || !model.hasPerson(person)) {
            throw new CommandException(MESSAGE_NO_EXIST_PERSON);
        }

        model.assignPersonToGroup(this.group, person);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_SUCCESS, name, group));
    }
}
