package employeetracker.logic.commands;

import static employeetracker.model.Model.PREDICATE_SHOW_ALL_EMPLOYEES;
import static java.util.Objects.requireNonNull;

import employeetracker.model.Model;

public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all employees";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredEmployeeList(PREDICATE_SHOW_ALL_EMPLOYEES);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
