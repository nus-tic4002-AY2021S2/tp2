package employeetracker.model;

import static employeetracker.commons.util.CollectionUtil.requireAllNonNull;
import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import employeetracker.commons.core.GuiSettings;
import employeetracker.commons.core.LogsCenter;
import employeetracker.model.employee.Employee;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

/**
 * Represents the in-memory model of the employee tracker data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final EmployeeTracker employeeTracker;
    private final UserPrefs userPrefs;
    private final FilteredList<Employee> filteredEmployees;

    /**
     * Initializes a ModelManager with the given employeeTracker and userPrefs.
     */
    public ModelManager(ReadOnlyEmployeeTracker employeeTracker, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(employeeTracker, userPrefs);

        logger.fine("Initializing with address book: " + employeeTracker + " and user prefs " + userPrefs);

        this.employeeTracker = new EmployeeTracker(employeeTracker);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredEmployees = new FilteredList<>(this.employeeTracker.getPersonList());
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
    public void setEmployeeTrackerFilePath(Path employeeTrackerFilePath) {
        requireNonNull(employeeTrackerFilePath);
        userPrefs.setEmployeeTrackerFilePath(employeeTrackerFilePath);
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
    public boolean hasPerson(Employee employee) {
        requireNonNull(employee);
        return employeeTracker.hasPerson(employee);
    }

    @Override
    public void deletePerson(Employee target) {
        employeeTracker.removePerson(target);
    }

    @Override
    public void addPerson(Employee employee) {
        employeeTracker.addPerson(employee);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Employee target, Employee editedEmployee) {
        requireAllNonNull(target, editedEmployee);

        employeeTracker.setPerson(target, editedEmployee);
    }

    //=========== Filtered Employee List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Employee} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Employee> getFilteredPersonList() {
        return filteredEmployees;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Employee> predicate) {
        requireNonNull(predicate);
        filteredEmployees.setPredicate(predicate);
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
                && filteredEmployees.equals(other.filteredEmployees);
    }

}
