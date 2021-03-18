package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;


public class DeleteAppointmentCommandTest {

    private Model model1 = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_personDelAppointment_delSuccessful() throws Exception {
        Person personToDelAppointment = model1.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        assertTrue(personToDelAppointment.getAppointment().size() == 1);

        DeleteAppointmentCommand delAppCmd = new DeleteAppointmentCommand(INDEX_FIRST_PERSON, 1);

        delAppCmd.execute(model1);

        assertTrue(personToDelAppointment.getAppointment().size() == 0);

    }

}
