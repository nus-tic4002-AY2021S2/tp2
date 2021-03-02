package employeetracker.logic.commands;

import static employeetracker.logic.parser.CliSyntax.PREFIX_NAME;
import static employeetracker.logic.parser.CliSyntax.PREFIX_ROLE;
import static java.util.Objects.requireNonNull;

import employeetracker.commons.core.Messages;
import employeetracker.model.Model;
import employeetracker.model.employee.NameContainsKeywordsPredicate;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + COMMAND_WORD + " [" + PREFIX_NAME + "NAME] "
            + "Example: " + COMMAND_WORD + " n/ Alice bob charlie"
            + "\n OR \n"
            + COMMAND_WORD + " [" + PREFIX_ROLE + "ROLE] "
            + "Example: " + COMMAND_WORD + " r/ Developer";

    private final NameContainsKeywordsPredicate predicate;

    public FindCommand(NameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }




    ///--------
    //private final FindEmployeeDescriptor findEmployeeDescriptor;
    ///-------
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredEmployeeList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_EMPLOYEES_LISTED_OVERVIEW, model.getFilteredEmployeeList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicate.equals(((FindCommand) other).predicate)); // state check
    }
}
