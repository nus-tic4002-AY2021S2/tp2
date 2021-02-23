package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class NricTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Nric(null));
    }

    @Test
    public void constructor_invalidNric_throwsIllegalArgumentException() {
        String invalidNric = "";
        assertThrows(IllegalArgumentException.class, () -> new Nric(invalidNric));
    }

    @Test
    public void isValidNric() {
        // null IC
        assertThrows(NullPointerException.class, () -> Nric.isValidNric(null));

        // invalid IC
        assertFalse(Nric.isValidNric("")); // empty string
        assertFalse(Nric.isValidNric("S11111B")); // only 5 digit
        assertFalse(Nric.isValidNric("^"));
        assertFalse(Nric.isValidNric("11111111B"));
        assertFalse(Nric.isValidNric("S11111111B"));

        // valid Nric,1 alphabet, 7 digits, 1 alphabet and it should not be blank
        assertTrue(Nric.isValidNric("S1234567B"));
    }
}
