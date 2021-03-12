package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;


public class DateTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Date(null));
    }

    @Test
    public void constructor_invalidDate_throwsIllegalArgumentException() {
        String invalidDate = " ";
        assertThrows(IllegalArgumentException.class, () -> new Date(invalidDate));
    }

    @Test
    public void isValidDate() {
        // null date
        assertThrows(NullPointerException.class, () -> Date.isValidDate(null));

        // invalid date
        assertFalse(Date.isValidDate("21/Feb/2020")); // in valid format
        assertFalse(Date.isValidDate("44-02-2020 ")); // day out of range
        assertFalse(Date.isValidDate("02-14-2020")); // month out of range

        // valid date
        assertTrue(Date.isValidDate("12-12-2020"));
        assertTrue(Date.isValidDate("22-02-2021"));
    }

    @Test
    public void isValidCalendarDate() {
        assertFalse(Date.isValidCalendarDate("29-02-2021")); // day in feb is bigger than 28
        assertFalse(Date.isValidCalendarDate("30-02-2020")); // day in feb is bigger than 28
        assertFalse(Date.isValidCalendarDate("30-02-2020")); // day in feb is bigger than 28

        assertTrue(Date.isValidCalendarDate("01-02-2020")); // correct date
        assertTrue(Date.isValidCalendarDate("30-03-2020")); // correct date
        assertTrue(Date.isValidCalendarDate("29-02-2020")); // leap year
    }

    @Test
    public void calculateDateDiff() {
        assertEquals(Date.calculateDateDiff("01-02-2021", "28-02-2021"), 27);
        assertNotEquals(Date.calculateDateDiff("01-02-2021", "28-02-2021"), 23);
    }
}
