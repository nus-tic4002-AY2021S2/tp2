package employeetracker.model.person;

import static employeetracker.commons.util.AppUtil.checkArgument;
import static java.util.Objects.requireNonNull;

public class DateOfBirth {

    public static final String MESSAGE_CONSTRAINTS =
            "Date of birth should be in yyyy-mm-dd format";
    public static final String VALIDATION_REGEX = "^\\d{4}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$";
    public final String value;

    /**
     * Constructs a {@code DateOfBirth}.
     *
     * @param dateOfBirth A valid date of birth.
     */
    public DateOfBirth(String dateOfBirth) {
        requireNonNull(dateOfBirth);
        checkArgument(isValidDateOfBirth(dateOfBirth), MESSAGE_CONSTRAINTS);
        value = dateOfBirth;
    }

    /**
     * Returns true if a given string is a valid date of birth.
     */
    public static boolean isValidDateOfBirth(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Email // instanceof handles nulls
                && value.equals(((Email) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
