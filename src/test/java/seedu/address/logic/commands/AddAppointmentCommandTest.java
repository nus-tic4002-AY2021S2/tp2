package seedu.address.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

public class AddAppointmentCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullPerson_throwsIndexOutOfBoundException() {
        assertThrows(IndexOutOfBoundsException.class, () -> new AddAppointmentCommand(Index.fromOneBased(0), null, null));
    }

    @Test
    public void execute_personAddAppointment_addSuccessful() throws Exception {
        Person personToAddAppointment = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        assertTrue(personToAddAppointment.getAppointment().size()==1);

        AddAppointmentCommand appAppCmd = new AddAppointmentCommand(INDEX_FIRST_PERSON, "Description 2", "2021-03-16 12:00:00");

        appAppCmd.execute(model);

        assertTrue(personToAddAppointment.getAppointment().size()==2);

        AddAppointmentCommand appAppCmd2 = new AddAppointmentCommand(INDEX_FIRST_PERSON, "Description 3", "2021-03-16 12:00:00");

        appAppCmd2.execute(model);

        assertTrue(personToAddAppointment.getAppointment().size()==3);

    }

}
