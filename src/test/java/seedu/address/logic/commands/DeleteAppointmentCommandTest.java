package seedu.address.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

public class DeleteAppointmentCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_personAddAppointment_addSuccessful() throws Exception {
        Person personToDelAppointment = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        assertTrue(personToDelAppointment.getAppointment().size()==1);

        DeleteAppointmentCommand delAppCmd = new DeleteAppointmentCommand(INDEX_FIRST_PERSON, 1);

        delAppCmd.execute(model);

        assertTrue(personToDelAppointment.getAppointment().size()==0);

    }

}
