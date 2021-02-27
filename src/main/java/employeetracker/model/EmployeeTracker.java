package employeetracker.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import employeetracker.model.employee.Employee;
import employeetracker.model.employee.UniquePersonList;
import javafx.collections.ObservableList;

/**
 * Wraps all data at the employee-tracker level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class EmployeeTracker implements ReadOnlyEmployeeTracker {

    private final UniquePersonList persons;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
    }

    public EmployeeTracker() {}

    /**
     * Creates an EmployeeTracker using the Persons in the {@code toBeCopied}
     */
    public EmployeeTracker(ReadOnlyEmployeeTracker toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the employee list with {@code employees}.
     * {@code employees} must not contain duplicate employees.
     */
    public void setPersons(List<Employee> employees) {
        this.persons.setPersons(employees);
    }

    /**
     * Resets the existing data of this {@code EmployeeTracker} with {@code newData}.
     */
    public void resetData(ReadOnlyEmployeeTracker newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
    }

    //// employee-level operations

    /**
     * Returns true if a employee with the same identity as {@code employee} exists in the address book.
     */
    public boolean hasPerson(Employee employee) {
        requireNonNull(employee);
        return persons.contains(employee);
    }

    /**
     * Adds a employee to the address book.
     * The employee must not already exist in the address book.
     */
    public void addPerson(Employee p) {
        persons.add(p);
    }

    /**
     * Replaces the given employee {@code target} in the list with {@code editedEmployee}.
     * {@code target} must exist in the address book.
     * The employee identity of {@code editedEmployee} must not be the same as another existing employee in the
     * employee Tracker.
     */
    public void setPerson(Employee target, Employee editedEmployee) {
        requireNonNull(editedEmployee);

        persons.setPerson(target, editedEmployee);
    }

    /**
     * Removes {@code key} from this {@code EmployeeTracker}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Employee key) {
        persons.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return persons.asUnmodifiableObservableList().size() + " persons";
        // TODO: refine later
    }

    @Override
    public ObservableList<Employee> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EmployeeTracker // instanceof handles nulls
                && persons.equals(((EmployeeTracker) other).persons));
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }
}
