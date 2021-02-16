package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class IcTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Ic(null));
    }

    @Test
    public void constructor_invalidIc_throwsIllegalArgumentException() {
        String invalidIc = "";
        assertThrows(IllegalArgumentException.class, () -> new Ic(invalidIc));
    }

    @Test
    public void isValidIc() {
        // null IC
        assertThrows(NullPointerException.class, () -> Ic.isValidIc(null));

        // invalid IC
        assertFalse(Ic.isValidIc("")); // empty string
        assertFalse(Ic.isValidIc("S11111B")); // only 5 digit
        assertFalse(Ic.isValidIc("^"));
        assertFalse(Ic.isValidIc("11111111B"));
        assertFalse(Ic.isValidIc("S11111111B"));


        // valid IC
        assertTrue(Ic.isValidIc("S1234567B")); //format of 1 alphabet, 7 digits, 1 alphabet, and it should not be blank
    }
}
