package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.util.EmailUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;



public class SendCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void invalidEmailAddress() {

        String email = "hello.com";

        try {
            assertEquals(new EmailUtil(email).getEmail(), "No valid email address found");
        } catch (CommandException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void integerNumber() {
        String number = "hello";
        String[] command = new String[0];
        SendCommand sendCommand = new SendCommand(command);

        // false when number is hello
        assertFalse(sendCommand.isNumeric(number));

        number = "1";
        // true when number is 1
        assertTrue(sendCommand.isNumeric(number));

    }
}
