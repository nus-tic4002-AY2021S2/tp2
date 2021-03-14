package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.medical.MedicalHistory;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Tag}.
 */
class JsonAdaptedMedicalHistory {

    private final String medicalHistoryDescription;
    private final Integer index;

    /**
     * Constructs a {@code JsonAdaptedTag} with the given {@code tagName}.
     */
    @JsonCreator
    public JsonAdaptedMedicalHistory(@JsonProperty("description")String medicalHistoryDescription,
                                     @JsonProperty("index") Integer index) {
        this.medicalHistoryDescription = medicalHistoryDescription;
        this.index = index;
    }

    /**
     * Converts a given {@code Tag} into this class for Jackson use.
     */
    public JsonAdaptedMedicalHistory(MedicalHistory source) {
        medicalHistoryDescription = source.medicalHistoryDescription;
        index = source.index;
    }

    @JsonProperty("description")
    public String getMedicalHistoryDescription() {
        return medicalHistoryDescription;
    }

    @JsonProperty("index")
    public Integer getMedicalIndex() {
        return index;
    }


    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Appointment} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public MedicalHistory toModelMedicalHistory() throws IllegalValueException {
        if (!MedicalHistory.isValidMedicalHistoryDescription(medicalHistoryDescription)) {
            throw new IllegalValueException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new MedicalHistory(medicalHistoryDescription, index);
    }
}
