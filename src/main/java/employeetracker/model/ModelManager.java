package employeetracker.model;

import static employeetracker.commons.util.CollectionUtil.requireAllNonNull;
import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import employeetracker.commons.core.GuiSettings;
import employeetracker.commons.core.LogsCenter;
import employeetracker.model.person.Person;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

/**
 * Represents the in-memory model of the employee tracker data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final EmployeeTracker employeeTracker;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;

    /**
     * Initializes a ModelManager with the given employeeTracker and userPrefs.
     */
    public ModelManager(ReadOnlyEmployeeTracker employeeTracker, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(employeeTracker, userPrefs);

        logger.fine("Initializing with address book: " + employeeTracker + " and user prefs " + userPrefs);

        this.employeeTracker = new EmployeeTracker(employeeTracker);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.employeeTracker.getPersonList());
    }

    public ModelManager() {
        this(new EmployeeTracker(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getEmployeeTrackerFilePath() {
        return userPrefs.getEmployeeTrackerFilePath();
    }

    @Override
    public void setEmployeeTrackerFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setEmployeeTrackerFilePath(addressBookFilePath);
    }

    //=========== EmployeeTracker ================================================================================

    @Override
    public void setEmployeeTracker(ReadOnlyEmployeeTracker employeeTracker) {
        this.employeeTracker.resetData(employeeTracker);
    }

    @Override
    public ReadOnlyEmployeeTracker getEmployeeTracker() {
        return employeeTracker;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return employeeTracker.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        employeeTracker.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        employeeTracker.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        employeeTracker.setPerson(target, editedPerson);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return employeeTracker.equals(other.employeeTracker)
                && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons);
    }

}
