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
            keywords.stream().anyMatch(
                keyword -> StringUtil.containsWordIgnoreCase(person.getName().fullName, keyword));
            return false;
        } else {
            String obj = keywords.get(0).toLowerCase();
            if (obj.substring(obj.indexOf("/") + 1).trim().isEmpty()) {
                return true;
            }
            if (obj.contains("n/")) {
                return keywords.stream()
                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getName().fullName, keyword));
            } else if (obj.contains("de/")) {
                return keywords.stream()
                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getDescription().value, keyword));
            } else if (obj.contains("d/")) {
                return keywords.stream()
                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getDate().value, keyword));
            } else if (obj.contains("i/")) {
                return keywords.stream()
                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getNric().value, keyword));
            } else if (obj.contains("p/")) {
                return keywords.stream()
                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getPhone().value, keyword));
            } else if (obj.contains("e/")) {
                return keywords.stream()
                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getEmail().value, keyword));
            } else if (obj.contains("a/")) {
                return keywords.stream()
                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getAddress().value, keyword));
            } else if (obj.contains("r/")) {
                return keywords.stream()
                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getRemark().value, keyword));
            } else if (obj.contains("f/")) {
                return keywords.stream()
                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getFollowUp().value, keyword));
            } else if (obj.contains("t/")) {
                return keywords.stream()
                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getTags().toString(), keyword));
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
