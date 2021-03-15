package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.appointment.Appointment;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Tag}.
 */
class JsonAdaptedAppointment {

    private final String appointmentDescription;
    private final Integer index;
    private final String date;

    /**
     * Constructs a {@code JsonAdaptedTag} with the given {@code tagName}.
     */
    @JsonCreator
    public JsonAdaptedAppointment(@JsonProperty("description") String appointmentDescription,
                                  @JsonProperty("index") Integer index,
                                  @JsonProperty("date") String date) {
        this.appointmentDescription = appointmentDescription;
        this.date = date;
        this.index = index;
    }

    /**
     * Converts a given {@code Tag} into this class for Jackson use.
     */
    public JsonAdaptedAppointment(Appointment source) {
        appointmentDescription = source.appointmentDescription;
        date = source.getDate();
        index = source.getIndex();
    }

    @JsonProperty("description")
    public String getAppointmentDescription() {
        return appointmentDescription;
    }

    @JsonProperty("index")
    public Integer getIndex() {
        return index;
    }
    @JsonProperty("date")
    public String getDate() {
        return date;
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
        return new Appointment(appointmentDescription, index, date);
    }
}
