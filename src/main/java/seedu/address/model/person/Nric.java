package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a person's NRIC in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidNric(String)}
 */
public class Nric {
    public static final String MESSAGE_NRIC_CONSTRAINTS =
            "Patient NRIC should follow the following format:\n"
                    + "NRICs should only contain alphanumeric characters. It should start with S, T, F or G"
                    + " followed by 7 numerical numbers and a checksum letter. It should not be blank.\n";


    /**
     * The first character of the NRIC must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String NRIC_VALIDATION_REGEX = "^[STFG]\\d{7}[A-Z]$";

    public final String value;

    /**
     * Constructs a {@code Nric}.
     *
     * @param nric A valid NRIC.
     */
    public Nric(String nric) {
        requireNonNull(nric);
        checkArgument(isValidNric(nric), MESSAGE_NRIC_CONSTRAINTS);
        this.value = nric;
    }

    /**
     * Returns true if a given string is a valid patient NRIC.
     */
    public static boolean isValidNric(String test) {
        return test.matches(NRIC_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Nric // instanceof handles nulls
                && this.value.equals(((Nric) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
