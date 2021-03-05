package seedu.address.model.person.appointment;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Appointment in the patient book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidAppointmentDescription(String)}
 */
public class Appointment {

    public static final String MESSAGE_CONSTRAINTS = "Appointment description cannot be null.";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";
    public final String appointmentDescription;

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

}
