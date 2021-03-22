package employeetracker.model.employee;

import static employeetracker.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class DateOfBirthTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DateOfBirth(null));
    }

    @Test
    public void constructor_invalidDateOfBirth_throwsIllegalArgumentException() {
        String invalidDateOfBirth = "";
        assertThrows(IllegalArgumentException.class, () -> new DateOfBirth(invalidDateOfBirth));
    }

    @Test
    public void isValidDateOfBirth() {
        // null date of birth
        assertThrows(NullPointerException.class, () -> DateOfBirth.isValidDateOfBirth(null));

        // invalid date of birth
        assertFalse(DateOfBirth.isValidDateOfBirth("")); // empty string
        assertFalse(DateOfBirth.isValidDateOfBirth(" ")); // spaces only
        assertFalse(DateOfBirth.isValidDateOfBirth("1990/12/10")); // date of birth in yyyy/mm/dd format
        assertFalse(DateOfBirth.isValidDateOfBirth("12-10-1990")); // date of birth in dd-mm-yyyy format
        assertFalse(DateOfBirth.isValidDateOfBirth("19901210")); // date of birth in yyyymmdd format
        assertFalse(DateOfBirth.isValidDateOfBirth("199-12-32")); // date of birth with invalid year
        assertFalse(DateOfBirth.isValidDateOfBirth("1990-13-10")); // date of birth with invalid month
        assertFalse(DateOfBirth.isValidDateOfBirth("1990-12-32")); // date of birth with invalid day

        // valid date of birth
        assertTrue(DateOfBirth.isValidDateOfBirth("1990-12-10"));
    }
}
