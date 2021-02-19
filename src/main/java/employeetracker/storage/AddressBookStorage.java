package employeetracker.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import employeetracker.commons.exceptions.DataConversionException;
import employeetracker.model.EmployeeTracker;
import employeetracker.model.ReadOnlyEmployeeTracker;

/**
 * Represents a storage for {@link EmployeeTracker}.
 */
public interface AddressBookStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getAddressBookFilePath();

    /**
     * Returns EmployeeTracker data as a {@link ReadOnlyEmployeeTracker}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyEmployeeTracker> readAddressBook() throws DataConversionException, IOException;

    /**
     * @see #getAddressBookFilePath()
     */
    Optional<ReadOnlyEmployeeTracker> readAddressBook(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyEmployeeTracker} to the storage.
     * @param addressBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveAddressBook(ReadOnlyEmployeeTracker addressBook) throws IOException;

    /**
     * @see #saveAddressBook(ReadOnlyEmployeeTracker)
     */
    void saveAddressBook(ReadOnlyEmployeeTracker addressBook, Path filePath) throws IOException;

}
