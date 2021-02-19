package employeetracker.storage;

import static employeetracker.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import employeetracker.commons.exceptions.IllegalValueException;
import employeetracker.commons.util.JsonUtil;
import employeetracker.model.EmployeeTracker;
import employeetracker.testutil.TypicalPersons;

public class JsonSerializableEmployeeTrackerTest {

    private static final Path TEST_DATA_FOLDER =
            Paths.get("src", "test", "data", "JsonSerializableEmployeeTrackerTest");
    private static final Path TYPICAL_PERSONS_FILE = TEST_DATA_FOLDER.resolve("typicalPersonsEmployeeTracker.json");
    private static final Path INVALID_PERSON_FILE = TEST_DATA_FOLDER.resolve("invalidPersonEmployeeTracker.json");
    private static final Path DUPLICATE_PERSON_FILE = TEST_DATA_FOLDER.resolve("duplicatePersonEmployeeTracker.json");

    @Test
    public void toModelType_typicalPersonsFile_success() throws Exception {
        JsonSerializableEmployeeTracker dataFromFile = JsonUtil.readJsonFile(TYPICAL_PERSONS_FILE,
                JsonSerializableEmployeeTracker.class).get();
        EmployeeTracker addressBookFromFile = dataFromFile.toModelType();
        EmployeeTracker typicalPersonsAddressBook = TypicalPersons.getTypicalEmployeeTracker();
        assertEquals(addressBookFromFile, typicalPersonsAddressBook);
    }

    @Test
    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
        JsonSerializableEmployeeTracker dataFromFile = JsonUtil.readJsonFile(INVALID_PERSON_FILE,
                JsonSerializableEmployeeTracker.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
        JsonSerializableEmployeeTracker dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PERSON_FILE,
                JsonSerializableEmployeeTracker.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableEmployeeTracker.MESSAGE_DUPLICATE_PERSON,
                dataFromFile::toModelType);
    }

}
