package employeetracker.storage;

import static employeetracker.testutil.Assert.assertThrows;
import static employeetracker.testutil.TypicalPersons.ALICE;
import static employeetracker.testutil.TypicalPersons.HOON;
import static employeetracker.testutil.TypicalPersons.IDA;
import static employeetracker.testutil.TypicalPersons.getTypicalEmployeeTracker;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import employeetracker.commons.exceptions.DataConversionException;
import employeetracker.model.EmployeeTracker;
import employeetracker.model.ReadOnlyEmployeeTracker;

public class JsonEmployeeTrackerStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonEmployeeTrackerStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readEmployeeTracker_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readEmployeeTracker(null));
    }

    private java.util.Optional<ReadOnlyEmployeeTracker> readEmployeeTracker(String filePath) throws Exception {
        return new JsonEmployeeTrackerStorage(Paths.get(filePath))
                .readEmployeeTracker(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readEmployeeTracker("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readEmployeeTracker("notJsonFormatEmployeeTracker.json"));
    }

    @Test
    public void readEmployeeTracker_invalidPersonEmployeeTracker_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readEmployeeTracker("invalidPersonEmployeeTracker.json"));
    }

    @Test
    public void readEmployeeTracker_invalidAndValidPersonEmployeeTracker_throwDataConversionException() {
        assertThrows(DataConversionException.class, () ->
                readEmployeeTracker("invalidAndValidPersonEmployeeTracker.json"));
    }

    @Test
    public void readAndSaveEmployeeTracker_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempAddressBook.json");
        EmployeeTracker original = getTypicalEmployeeTracker();
        JsonEmployeeTrackerStorage jsonAddressBookStorage = new JsonEmployeeTrackerStorage(filePath);

        // Save in new file and read back
        jsonAddressBookStorage.saveEmployeeTracker(original, filePath);
        ReadOnlyEmployeeTracker readBack = jsonAddressBookStorage.readEmployeeTracker(filePath).get();
        assertEquals(original, new EmployeeTracker(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addPerson(HOON);
        original.removePerson(ALICE);
        jsonAddressBookStorage.saveEmployeeTracker(original, filePath);
        readBack = jsonAddressBookStorage.readEmployeeTracker(filePath).get();
        assertEquals(original, new EmployeeTracker(readBack));

        // Save and read without specifying file path
        original.addPerson(IDA);
        jsonAddressBookStorage.saveEmployeeTracker(original); // file path not specified
        readBack = jsonAddressBookStorage.readEmployeeTracker().get(); // file path not specified
        assertEquals(original, new EmployeeTracker(readBack));

    }

    @Test
    public void saveEmployeeTracker_nullEmployeeTracker_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveEmployeeTracker(null, "SomeFile.json"));
    }

    /**
     * Saves {@code addressBook} at the specified {@code filePath}.
     */
    private void saveEmployeeTracker(ReadOnlyEmployeeTracker addressBook, String filePath) {
        try {
            new JsonEmployeeTrackerStorage(Paths.get(filePath))
                    .saveEmployeeTracker(addressBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveEmployeeTracker_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveEmployeeTracker(new EmployeeTracker(), null));
    }
}