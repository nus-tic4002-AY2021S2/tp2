package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.ViewAppointmentCommand;

class ViewAppointmentCommandParserTest {

    private ViewAppointmentCommandParser parser = new ViewAppointmentCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        ViewAppointmentCommand expectedViewAppCommand =
                new ViewAppointmentCommand(Index.fromOneBased(1));
        assertParseSuccess(parser, "Alice Bob", expectedViewAppCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Alice \n \t Bob  \t", expectedViewAppCommand);
    }
}
