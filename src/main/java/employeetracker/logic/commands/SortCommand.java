package employeetracker.logic.commands;

import static java.util.Objects.requireNonNull;

import employeetracker.model.Model;


/**
 * Sort and lists all persons in address book by name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sorts the list of all employee records in the Employee Tracker.\n"
            + "Parameters: TYPE (must be the letter n, s, d or b)\n"
            + "Example: " + COMMAND_WORD + " n";

    public static final String MESSAGE_SORT_EMPLOYEE_SUCCESS = "Employees list has been sorted.";

    private final String sortField;

    public SortCommand(String field) {
        this.sortField = field;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.sortEmployee(sortField);
        return new CommandResult(MESSAGE_SORT_EMPLOYEE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortCommand // instanceof handles nulls
                && sortField.equals(((SortCommand) other).sortField)); // state check
    }

}
