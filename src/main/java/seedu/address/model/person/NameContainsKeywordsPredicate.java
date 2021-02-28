package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;


/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class NameContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public NameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {



        if (keywords.isEmpty()) {
            return keywords.stream()
                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getName().fullName, keyword));
        } else {
            String obj = keywords.get(0).toLowerCase();

            if (obj.contains("n/")) {
                return keywords.stream()
                        .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getName().fullName, keyword));
            } else if (obj.contains("i/")) {
                return keywords.stream()
                        .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getIc().value, keyword));
            } else if (obj.contains("p/")) {
                return keywords.stream()
                        .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getPhone().value, keyword));
            } else if (obj.contains("e/")) {
                return keywords.stream()
                        .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getEmail().value, keyword));
            } else if (obj.contains("a/")) {
                return keywords.stream()
                        .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getAddress().value, keyword));
            } else {
                return keywords.stream()
                        .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getName().fullName, keyword));
            }
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((NameContainsKeywordsPredicate) other).keywords)); // state check
    }

}
