package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents the recursive follow up calls for a person.
 * Guarantees: immutable; is valid as declared in {@link #isValidFollowUp(String)}
 */
public class FollowUp {

    public static final String MESSAGE_FOLLOWUP_CONSTRAINTS =
        "FollowUp should only contain positive integers, must be 365 days or less and it should not be blank";
    public static final String VALIDATION_REGEX = "[0-9]+";
    public final String value;
    /**
     * Constructs a {@code FollowUp}.
     *
     * @param followUp A valid number of days for FollowUp.
     */
    public FollowUp(String followUp) {
        requireNonNull(followUp);
        checkArgument(isValidFollowUp(followUp), MESSAGE_FOLLOWUP_CONSTRAINTS);
        value = followUp;
    }

    @Override
    public String toString() {
        return value;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidFollowUp(String test) {
        if (test.matches(VALIDATION_REGEX)) {
            int days = Integer.parseInt(test);
            if (test.matches(VALIDATION_REGEX) && days < 366) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FollowUp // instanceof handles nulls
                && value.equals(((FollowUp) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
