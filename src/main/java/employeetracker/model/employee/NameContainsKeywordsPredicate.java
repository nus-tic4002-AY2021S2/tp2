package employeetracker.model.employee;

import java.util.List;
import java.util.function.Predicate;

import employeetracker.commons.util.StringUtil;

import static employeetracker.logic.parser.CliSyntax.PREFIX_NAME;
import static employeetracker.logic.parser.CliSyntax.PREFIX_ROLE;

/**
 * Tests that a {@code Employee}'s {@code Name} matches any of the keywords given.
 */
public class NameContainsKeywordsPredicate implements Predicate<Employee> {
    private final List<String> keywords;
    private final String findBy;
    public NameContainsKeywordsPredicate(List<String> keywords, String findBy) {
        this.keywords = keywords;
        this.findBy = findBy;
    }

    @Override
    public boolean test(Employee employee) {

       // System.out.println("----NameContainsKeywordsPredicate findBy:" + findBy);
        if (findBy.equals(String.valueOf(PREFIX_NAME))) {
            return keywords.stream()
                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(employee.getName().fullName, keyword));

        }
        if (findBy.equals(String.valueOf(PREFIX_ROLE))) {
            return keywords.stream()
                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(String.valueOf(employee.getRole()), keyword));

        }


        return false;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((NameContainsKeywordsPredicate) other).keywords)); // state check
    }

}
