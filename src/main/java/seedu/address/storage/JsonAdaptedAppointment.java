package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.appointment.Appointment;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Tag}.
 */
class JsonAdaptedAppointment {

    private final String appointmentDescription;

    /**
     * Constructs a {@code JsonAdaptedTag} with the given {@code tagName}.
     */
    @JsonCreator
    public JsonAdaptedAppointment(String appointmentDescription) {
        this.appointmentDescription = appointmentDescription;
    }

    /**
     * Converts a given {@code Tag} into this class for Jackson use.
     */
    public JsonAdaptedAppointment(Appointment source) {
        appointmentDescription = source.appointmentDescription;
    }

    @JsonValue
    public String getAppointmentDescription() {
        return appointmentDescription;
    }


    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Appointment} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public Appointment toModelAppointment() throws IllegalValueException {
        if (!Appointment.isValidAppointmentDescription(appointmentDescription)) {
            throw new IllegalValueException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Appointment(appointmentDescription);
    }
}
