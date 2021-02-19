package employeetracker.model;

import employeetracker.model.person.Person;
import javafx.collections.ObservableList;

/**
 * Unmodifiable view of an employee tracker
 */
public interface ReadOnlyEmployeeTracker {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();

}
