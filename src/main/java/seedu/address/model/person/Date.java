package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.text.ParseException;
import java.text.SimpleDateFormat;


/**
 * Represents the date of when the case was reported.
 * Guarantees: immutable; is valid as declared in {@link #isValidDate(String)}
 */
public class Date {
    public static final String MESSAGE_DATE_CONSTRAINTS =
            "Date should follow date format 'dd-mm-yyyy' and it should be a valid calendar date";

    /**
     * The date format is 'dd-mm-yyyy', with leading zero is required
     */
    public static final String DATE_VALIDATION_REGEX = "^(3[01]|[12][0-9]|0[1-9])-(1[0-2]|0[1-9])-[0-9]{4}$";

    public final String value;

    /**
     * Constructs a {@code Date}.
     *
     * @param date A valid date.
     */
    public Date(String date) {
        requireNonNull(date);
        checkArgument(isValidDate(date), MESSAGE_DATE_CONSTRAINTS);
        checkArgument(isValidCalendarDate(date), MESSAGE_DATE_CONSTRAINTS);
        this.value = date;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Date // instanceof handles nulls
                && this.value.equals(((Date) other).value)); // state check
    }

    /**
     * Returns true if a given string is a valid date.
     */
    public static boolean isValidDate(String date) {
        return date.matches(DATE_VALIDATION_REGEX);
    }

    /**
     * Returns true if a given string is a valid calendar date.
     */
    public static boolean isValidCalendarDate(String date) {
        int days = Integer.parseInt(date.substring(0, 2));
        int month = Integer.parseInt(date.substring(3, 5));
        int year = Integer.parseInt(date.substring(6, 10));
        if (isLeapYear (year)) {
            if (days > 29 && month == 2) {
                return false;
            } } else {
            if (days > 28 && month == 2) {
                return false;
            }
        }
        return true;
    }

    /**
     * @param startDate start date
     * @param endDate end date
     * @return the number of days between start date and end date
     */
    public static float calculateDateDiff(String startDate, String endDate) {
        SimpleDateFormat formatter = new SimpleDateFormat ("dd-mm-yyyy");
        float daysBetween = 0;
        try {
            java.util.Date dateBefore = formatter.parse(startDate);
            java.util.Date dateAfter = formatter.parse(endDate);
            long difference = dateAfter.getTime() - dateBefore.getTime();
            daysBetween = (float) (difference / (1000 * 60 * 60 * 24));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //int result = Integer.parseInt(String.valueOf(daysBetween));
        return daysBetween;
    }

    /**
     * @param year year in integer
     * @return true or false if the given year is a leap year
     */
    public static boolean isLeapYear(int year) {
        boolean isLeap = false;
        if (year % 4 == 0) {
            if (year % 100 == 0) {
                if (year % 400 == 0) {
                    isLeap = true;
                } else {
                    isLeap = false;
                }
            } else {
                isLeap = true;
            }
        } else {
            isLeap = false;
        }
        return isLeap;
    }
}
