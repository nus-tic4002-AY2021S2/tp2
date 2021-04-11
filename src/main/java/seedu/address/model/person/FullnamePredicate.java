package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;



/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class FullnamePredicate implements Predicate<Person> {
    private final List<String> keywords;

    public FullnamePredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(keyword -> person.getName().fullName.equalsIgnoreCase(keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FullnamePredicate // instanceof handles nulls
                && keywords.equals(((FullnamePredicate) other).keywords)); // state check
    }

}
