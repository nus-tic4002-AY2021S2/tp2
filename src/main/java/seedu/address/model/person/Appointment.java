package seedu.address.model.person;

import java.util.Date;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's name in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class Appointment {


    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    private String details;
    private Date appointmentDate;
    private boolean attended = false;
    /**
     * Constructs a {@code Name}.
     *
     * @param name A valid name.
     */
    public Appointment(String details, Date appointmentDate) {
        this.details = details;
        this.appointmentDate = appointmentDate;
    }

    public Appointment(String details, Date appointmentDate,boolean attended) {
        this.details = details;
        this.appointmentDate = appointmentDate;
        this.attended = attended;

    }



    @Override
    public String toString() {
        return appointmentDate.toString() + " - "+details ;
    }


}
