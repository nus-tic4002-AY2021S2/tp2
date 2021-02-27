package employeetracker.model;

import static employeetracker.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static employeetracker.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static employeetracker.testutil.Assert.assertThrows;
import static employeetracker.testutil.TypicalPersons.ALICE;
import static employeetracker.testutil.TypicalPersons.getTypicalEmployeeTracker;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import employeetracker.model.employee.Employee;
import employeetracker.model.employee.exceptions.DuplicatePersonException;
import employeetracker.testutil.PersonBuilder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EmployeeTrackerTest {

    private final EmployeeTracker employeeTracker = new EmployeeTracker();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), employeeTracker.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> employeeTracker.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyEmployeeTracker_replacesData() {
        EmployeeTracker newData = getTypicalEmployeeTracker();
        employeeTracker.resetData(newData);
        assertEquals(newData, employeeTracker);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two employees with the same identity fields
        Employee editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Employee> newEmployees = Arrays.asList(ALICE, editedAlice);
        EmployeeTrackerStub newData = new EmployeeTrackerStub(newEmployees);

        assertThrows(DuplicatePersonException.class, () -> employeeTracker.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> employeeTracker.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInEmployeeTracker_returnsFalse() {
        assertFalse(employeeTracker.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInEmployeeTracker_returnsTrue() {
        employeeTracker.addPerson(ALICE);
        assertTrue(employeeTracker.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInEmployeeTracker_returnsTrue() {
        employeeTracker.addPerson(ALICE);
        Employee editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(employeeTracker.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> employeeTracker.getPersonList().remove(0));
    }

    /**
     * A stub ReadOnlyEmployeeTracker whose employees list can violate interface constraints.
     */
    private static class EmployeeTrackerStub implements ReadOnlyEmployeeTracker {
        private final ObservableList<Employee> employees = FXCollections.observableArrayList();

        EmployeeTrackerStub(Collection<Employee> employees) {
            this.employees.setAll(employees);
        }

        @Override
        public ObservableList<Employee> getPersonList() {
            return employees;
        }
    }

}
