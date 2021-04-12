package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class FollowUpTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new FollowUp(null));
    }

    @Test
    public void constructor_invalidFollowUp_throwsIllegalArgumentException() {
        String invalidFollowUp = "";
        assertThrows(IllegalArgumentException.class, () -> new FollowUp(invalidFollowUp));
    }

    @Test
    public void isValidFollowUp() {
        // null followUp
        assertThrows(NullPointerException.class, () -> FollowUp.isValidFollowUp(null));

        // invalid followUp
        assertFalse(FollowUp.isValidFollowUp("")); // empty string
        assertFalse(FollowUp.isValidFollowUp(" ")); // spaces only
        assertFalse(FollowUp.isValidFollowUp("phone")); // non-numeric
        assertFalse(FollowUp.isValidFollowUp("9011p041")); // alphabets within digits
        assertFalse(FollowUp.isValidFollowUp("@%"));
        assertFalse(FollowUp.isValidFollowUp("9312 1534")); // spaces within digits
        assertFalse(FollowUp.isValidFollowUp("366"));
        assertFalse(FollowUp.isValidFollowUp("-1"));


        // valid followUp
        assertTrue(FollowUp.isValidFollowUp("3")); // exactly 3 numbers
        assertTrue(FollowUp.isValidFollowUp("0")); // exactly 3 numbers
        assertTrue(FollowUp.isValidFollowUp("365")); // exactly 3 numbers

    }

}
