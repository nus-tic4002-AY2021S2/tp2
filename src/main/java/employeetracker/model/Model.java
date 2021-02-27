package employeetracker.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import employeetracker.commons.core.GuiSettings;
import employeetracker.model.person.Person;
import javafx.collections.ObservableList;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

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
     * Returns true if a person with the same identity as {@code person} exists in the Employee Tracker.
     */
    boolean hasPerson(Person person);

    /**
     * Deletes the given person.
     * The person must exist in the Employee Tracker.
     */
    void deletePerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the Employee Tracker.
     */
    void addPerson(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the Employee Tracker.
     * The person identity of {@code editedPerson} must not be the same as another existing person
     * in the Employee Tracker.
     */
    void setPerson(Person target, Person editedPerson);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);
}
