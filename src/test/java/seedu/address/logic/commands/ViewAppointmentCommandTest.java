package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

class ViewAppointmentCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        Index firstPredicate =
                INDEX_FIRST_PERSON;
        Index secondPredicate =
                INDEX_FIRST_PERSON;

        ViewAppointmentCommand viewAppFirstCommand = new ViewAppointmentCommand(firstPredicate);
        ViewAppointmentCommand viewAppSecondCommand = new ViewAppointmentCommand(secondPredicate);

        // same object -> returns true
        assertTrue(viewAppFirstCommand.equals(viewAppFirstCommand));

        // same values -> returns true
        ViewAppointmentCommand viewAppFirstCommandCopy = new ViewAppointmentCommand(firstPredicate);
        assertTrue(viewAppFirstCommand.equals(viewAppFirstCommandCopy));

        // different types -> returns false
        assertFalse(viewAppFirstCommand.equals(1));

        // null -> returns false
        assertFalse(viewAppFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(viewAppFirstCommand.equals(viewAppSecondCommand));
    }


}
