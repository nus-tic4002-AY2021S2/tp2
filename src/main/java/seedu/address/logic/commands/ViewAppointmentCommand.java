package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.NameContainsKeywordsPredicate;



/**
 * Finds and lists all persons in patient book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class ViewAppointmentCommand extends Command {

    public static final String COMMAND_WORD = "viewApp";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all appointment of this person";

    private final NameContainsKeywordsPredicate predicate;

    public ViewAppointmentCommand(NameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredViewAppPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewAppointmentCommand // instanceof handles nulls
                && predicate.equals(((ViewAppointmentCommand) other).predicate)); // state check
    }
}
