package employeetracker.logic.commands;

import static employeetracker.testutil.Assert.assertThrows;
import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import employeetracker.commons.core.GuiSettings;
import employeetracker.logic.commands.exceptions.CommandException;
import employeetracker.model.EmployeeTracker;
import employeetracker.model.Model;
import employeetracker.model.ReadOnlyEmployeeTracker;
import employeetracker.model.ReadOnlyUserPrefs;
import employeetracker.model.employee.Employee;
import employeetracker.testutil.PersonBuilder;
import javafx.collections.ObservableList;

public class AddCommandTest {

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();
        Employee validEmployee = new PersonBuilder().build();

        CommandResult commandResult = new AddCommand(validEmployee).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validEmployee), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validEmployee), modelStub.personsAdded);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Employee validEmployee = new PersonBuilder().build();
        AddCommand addCommand = new AddCommand(validEmployee);
        ModelStub modelStub = new ModelStubWithPerson(validEmployee);

        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_PERSON, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Employee alice = new PersonBuilder().withName("Alice").build();
        Employee bob = new PersonBuilder().withName("Bob").build();
        AddCommand addAliceCommand = new AddCommand(alice);
        AddCommand addBobCommand = new AddCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddCommand addAliceCommandCopy = new AddCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different employee -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getEmployeeTrackerFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setEmployeeTrackerFilePath(Path employeeTrackerFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Employee employee) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setEmployeeTracker(ReadOnlyEmployeeTracker newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyEmployeeTracker getEmployeeTracker() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Employee employee) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Employee target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Employee target, Employee editedEmployee) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Employee> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Employee> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single employee.
     */
    private class ModelStubWithPerson extends ModelStub {
        private final Employee employee;

        ModelStubWithPerson(Employee employee) {
            requireNonNull(employee);
            this.employee = employee;
        }

        @Override
        public boolean hasPerson(Employee employee) {
            requireNonNull(employee);
            return this.employee.isSamePerson(employee);
        }
    }

    /**
     * A Model stub that always accept the employee being added.
     */
    private class ModelStubAcceptingPersonAdded extends ModelStub {
        final ArrayList<Employee> personsAdded = new ArrayList<>();

        @Override
        public boolean hasPerson(Employee employee) {
            requireNonNull(employee);
            return personsAdded.stream().anyMatch(employee::isSamePerson);
        }

        @Override
        public void addPerson(Employee employee) {
            requireNonNull(employee);
            personsAdded.add(employee);
        }

        @Override
        public ReadOnlyEmployeeTracker getEmployeeTracker() {
            return new EmployeeTracker();
        }
    }

}
