package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a person's NRIC in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidIc(String)}
 */
public class Ic {
    public static final String MESSAGE_NRIC_CONSTRAINTS =
            "Patient NRIC should follow the format of 1 alphabet, 7 digits, 1 alphabet, and it should not be blank";

    /**
     * The first character of the NRIC must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String NRIC_VALIDATION_REGEX = "[\\p{Alpha}][\\p{Digit}]{7}[\\p{Alpha}]";

    public final String value;

    /**
     * Constructs a {@code Nric}.
     *
     * @param ic A valid NRIC.
     */
    public Ic(String ic) {
        requireNonNull(ic);
        checkArgument(isValidIc(ic), MESSAGE_NRIC_CONSTRAINTS);
        this.value = ic;
    }

    /**
     * Returns true if a given string is a valid patient NRIC.
     */
    public static boolean isValidIc(String test) {
        return test.matches(NRIC_VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Ic // instanceof handles nulls
                && this.value.equals(((Ic) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
