package seedu.address.logic.commands;


import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;


public class AddAppointmentCommandTest {

    private Model model1 = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullPerson_throwsIndexOutOfBoundException() {
        assertThrows(IndexOutOfBoundsException.class, () -> new AddAppointmentCommand(Index.fromOneBased(0),
                null, null));
    }

    @Test
    public void execute_personAddAppointment_addSuccessful() throws Exception {
        Person personToAddAppointment = model1.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());

        assertTrue(personToAddAppointment.getAppointment().size() == 0);

        AddAppointmentCommand appAppCmd = new AddAppointmentCommand(INDEX_SECOND_PERSON,
                "Description 2", "2021-03-16 12:00:00");

        appAppCmd.execute(model1);

        assertTrue(personToAddAppointment.getAppointment().size() == 1);

        AddAppointmentCommand appAppCmd2 = new AddAppointmentCommand(INDEX_SECOND_PERSON,
                "Description 3", "2021-03-16 17:00:00");

        appAppCmd2.execute(model1);

        //assertTrue(personToAddAppointment.getAppointment().size() == 2);

    }

}
