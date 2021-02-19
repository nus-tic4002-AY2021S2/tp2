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

import employeetracker.model.person.Person;
import employeetracker.model.person.exceptions.DuplicatePersonException;
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
        // Two persons with the same identity fields
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
        EmployeeTrackerStub newData = new EmployeeTrackerStub(newPersons);

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
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(employeeTracker.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> employeeTracker.getPersonList().remove(0));
    }

    /**
     * A stub ReadOnlyEmployeeTracker whose persons list can violate interface constraints.
     */
    private static class EmployeeTrackerStub implements ReadOnlyEmployeeTracker {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();

        EmployeeTrackerStub(Collection<Person> persons) {
            this.persons.setAll(persons);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }
    }

}
