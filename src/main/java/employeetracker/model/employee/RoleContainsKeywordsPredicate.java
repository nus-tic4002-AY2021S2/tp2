package employeetracker.model.employee;

import employeetracker.commons.util.StringUtil;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Employee}'s {@code Name} matches any of the keywords given.
 */
public class RoleContainsKeywordsPredicate implements Predicate<Employee> {
    private final List<String> keywords;

    public RoleContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Employee employee) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(String.valueOf(employee.getRole()), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RoleContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((RoleContainsKeywordsPredicate) other).keywords)); // state check
    }

}
