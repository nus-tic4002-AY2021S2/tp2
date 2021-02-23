package employeetracker.storage;

import static employeetracker.storage.JsonAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT;
import static employeetracker.testutil.Assert.assertThrows;
import static employeetracker.testutil.TypicalPersons.BENSON;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import employeetracker.commons.exceptions.IllegalValueException;
import employeetracker.model.person.Address;
import employeetracker.model.person.DateOfBirth;
import employeetracker.model.person.DateOfJoining;
import employeetracker.model.person.Email;
import employeetracker.model.person.Name;
import employeetracker.model.person.Phone;

public class JsonAdaptedPersonTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_DATE_OF_BIRTH = "30-12-1999";
    private static final String INVALID_DATE_OF_JOINING = "30-12-2019";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_ROLE = BENSON.getRole().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_ADDRESS = BENSON.getAddress().toString();
    private static final String VALID_DATE_OF_BIRTH = BENSON.getDateOfBirth().toString();
    private static final String VALID_DATE_OF_JOINING = BENSON.getDateOfJoining().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(BENSON);
        assertEquals(BENSON, person.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(INVALID_NAME, VALID_ROLE, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_DATE_OF_BIRTH, VALID_DATE_OF_JOINING, VALID_TAGS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(null, VALID_ROLE,VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_DATE_OF_BIRTH, VALID_DATE_OF_JOINING, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_ROLE, INVALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_DATE_OF_BIRTH, VALID_DATE_OF_JOINING, VALID_TAGS);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_ROLE, null, VALID_EMAIL,
                VALID_ADDRESS, VALID_DATE_OF_BIRTH, VALID_DATE_OF_JOINING, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_ROLE, VALID_PHONE, INVALID_EMAIL,
                VALID_ADDRESS, VALID_DATE_OF_BIRTH, VALID_DATE_OF_JOINING, VALID_TAGS);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_ROLE, VALID_PHONE, null,
                VALID_ADDRESS, VALID_DATE_OF_BIRTH, VALID_DATE_OF_JOINING, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_ROLE, VALID_PHONE, VALID_EMAIL,
                INVALID_ADDRESS, VALID_DATE_OF_BIRTH, VALID_DATE_OF_JOINING, VALID_TAGS);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_ROLE, VALID_PHONE, VALID_EMAIL,
                null, VALID_DATE_OF_BIRTH, VALID_DATE_OF_JOINING, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidDateOfBirth_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_ROLE, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, INVALID_DATE_OF_BIRTH, VALID_DATE_OF_JOINING, VALID_TAGS);
        String expectedMessage = DateOfBirth.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullDateOfBirth_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_ROLE, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, null, VALID_DATE_OF_JOINING, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, DateOfBirth.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidDateOfJoining_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_ROLE, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_DATE_OF_BIRTH, INVALID_DATE_OF_JOINING, VALID_TAGS);
        String expectedMessage = DateOfJoining.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullDateOfJoining_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_ROLE, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_DATE_OF_BIRTH, null, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, DateOfJoining.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_ROLE, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_DATE_OF_BIRTH, VALID_DATE_OF_JOINING, invalidTags);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

}
