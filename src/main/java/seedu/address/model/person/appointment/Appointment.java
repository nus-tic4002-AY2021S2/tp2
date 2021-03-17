package seedu.address.model.person.appointment;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represents a Appointment in the patient book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidAppointmentDescription(String)}
 */
public class Appointment implements Comparable<Appointment> {

    public static final String MESSAGE_CONSTRAINTS = "Appointment description cannot be null.";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";
    public final String appointmentDescription;
    private String date;
    private Integer index;

    /**
     * Constructs a {@code Tag}.
     *  @param appointmentDescription A valid tag name.
     */
    public Appointment(String appointmentDescription) {
        requireNonNull(appointmentDescription);
        checkArgument(isValidAppointmentDescription(appointmentDescription), MESSAGE_CONSTRAINTS);
        this.appointmentDescription = appointmentDescription;
    }

    /**
     * Constructs a {@code Tag}.
     *  @param appointmentDescription A valid tag name.
     */
    public Appointment(String appointmentDescription, Integer index, String date) {
        requireNonNull(appointmentDescription);
        checkArgument(isValidAppointmentDescription(appointmentDescription), MESSAGE_CONSTRAINTS);
        this.appointmentDescription = appointmentDescription;
        this.date = date;
        this.index = index;
    }

    /**
     * Returns true if a given string is a valid tag name.
     */
    public static boolean isValidAppointmentDescription(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Appointment // instanceof handles nulls
                && appointmentDescription.equals(((Appointment) other).appointmentDescription)); // state check
    }

    @Override
    public int hashCode() {
        return appointmentDescription.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + appointmentDescription + ']';
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    @Override
    public int compareTo(Appointment appointment) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentDate = getDate();
        String prevDate = appointment.getDate();
        Date curDate = null;
        Date newDate = null;
        if (currentDate != "" && currentDate != null) {
            try {
                curDate = simpleDateFormat.parse(currentDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if (prevDate != "" && prevDate != null) {
            try {
                newDate = simpleDateFormat.parse(prevDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return curDate.compareTo(newDate);
    }
}
