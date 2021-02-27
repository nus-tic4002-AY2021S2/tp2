package employeetracker.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import employeetracker.commons.core.GuiSettings;
import employeetracker.model.employee.Employee;
import javafx.collections.ObservableList;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Employee> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' Employee Tracker file path.
     */
    Path getEmployeeTrackerFilePath();

    /**
     * Sets the user prefs' Employee Tracker file path.
     */
    void setEmployeeTrackerFilePath(Path employeeTrackerFilePath);

    /**
     * Replaces Employee Tracker data with the data in {@code employeeTracker}.
     */
    void setEmployeeTracker(ReadOnlyEmployeeTracker employeeTracker);

    /** Returns the EmployeeTracker */
    ReadOnlyEmployeeTracker getEmployeeTracker();

    /**
     * Returns true if a employee with the same identity as {@code employee} exists in the Employee Tracker.
     */
    boolean hasPerson(Employee employee);

    /**
     * Deletes the given employee.
     * The employee must exist in the Employee Tracker.
     */
    void deletePerson(Employee target);

    /**
     * Adds the given employee.
     * {@code employee} must not already exist in the Employee Tracker.
     */
    void addPerson(Employee employee);

    /**
     * Replaces the given employee {@code target} with {@code editedEmployee}.
     * {@code target} must exist in the Employee Tracker.
     * The employee identity of {@code editedEmployee} must not be the same as another existing employee
     * in the Employee Tracker.
     */
    void setPerson(Employee target, Employee editedEmployee);

    /** Returns an unmodifiable view of the filtered employee list */
    ObservableList<Employee> getFilteredPersonList();

    /**
     * Updates the filter of the filtered employee list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Employee> predicate);
}
