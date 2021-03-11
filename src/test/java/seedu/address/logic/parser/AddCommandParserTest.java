package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.DATE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DATE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.DESCRIPTION_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESCRIPTION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DESCRIPTION;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.NRIC_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NRIC_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_CALLED;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_SEVERITY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_CALLED;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_SEVERITY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.model.person.Address;
import seedu.address.model.person.Date;
import seedu.address.model.person.Description;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.PersonBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Person expectedPerson = new PersonBuilder(BOB).withTags(VALID_TAG_CALLED).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + DATE_DESC_BOB
                + NRIC_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + DESCRIPTION_BOB + TAG_DESC_CALLED, new AddCommand(expectedPerson));
        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + DATE_DESC_BOB
                + NRIC_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + DESCRIPTION_BOB + TAG_DESC_CALLED, new AddCommand(expectedPerson));
        // multiple dates - last date accepted
        assertParseSuccess(parser, NAME_DESC_BOB + DATE_DESC_BOB
                + DATE_DESC_AMY + NRIC_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + DESCRIPTION_BOB + TAG_DESC_CALLED, new AddCommand(expectedPerson));
        // multiple nrics - last nric accepted
        assertParseSuccess(parser, NAME_DESC_BOB + DATE_DESC_BOB
                + NRIC_DESC_AMY + NRIC_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + DESCRIPTION_BOB + TAG_DESC_CALLED, new AddCommand(expectedPerson));
        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + DATE_DESC_BOB
                + NRIC_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + DESCRIPTION_BOB + TAG_DESC_CALLED, new AddCommand(expectedPerson));
        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_BOB + DATE_DESC_BOB
                + NRIC_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_AMY + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + DESCRIPTION_BOB + TAG_DESC_CALLED, new AddCommand(expectedPerson));
        // multiple addresses - last address accepted
        assertParseSuccess(parser, NAME_DESC_BOB + DATE_DESC_BOB
                + NRIC_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_AMY
                + ADDRESS_DESC_BOB + DESCRIPTION_BOB + TAG_DESC_CALLED, new AddCommand(expectedPerson));
        // multiple descriptions - last description accepted
        assertParseSuccess(parser, NAME_DESC_BOB + DATE_DESC_BOB
                + NRIC_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + DESCRIPTION_AMY
                + DESCRIPTION_BOB + TAG_DESC_CALLED, new AddCommand(expectedPerson));
        // multiple tags - all accepted
        Person expectedPersonMultipleTags = new PersonBuilder(BOB).withTags(VALID_TAG_CALLED, VALID_TAG_SEVERITY)
                .build();
        assertParseSuccess(parser, NAME_DESC_BOB + DATE_DESC_BOB
                + NRIC_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + DESCRIPTION_BOB
                + TAG_DESC_SEVERITY + TAG_DESC_CALLED, new AddCommand(expectedPersonMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Person expectedPerson = new PersonBuilder(AMY).withTags().build();
        assertParseSuccess(parser, NAME_DESC_AMY + DATE_DESC_AMY + NRIC_DESC_AMY
                + PHONE_DESC_AMY + EMAIL_DESC_AMY + ADDRESS_DESC_AMY + DESCRIPTION_AMY,
                new AddCommand(expectedPerson));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + DATE_DESC_BOB + NRIC_DESC_BOB
                + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + DESCRIPTION_BOB,
                expectedMessage);

        // missing date prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_DATE_BOB + NRIC_DESC_BOB
                + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + DESCRIPTION_BOB,
                expectedMessage);

        // missing ic prefix
        assertParseFailure(parser, NAME_DESC_BOB + DATE_DESC_BOB + VALID_NRIC_BOB
                + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + DESCRIPTION_BOB,
                expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + DATE_DESC_BOB + NRIC_DESC_BOB
                + VALID_PHONE_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + DESCRIPTION_BOB,
                expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BOB + DATE_DESC_BOB + NRIC_DESC_BOB
                + PHONE_DESC_BOB + VALID_EMAIL_BOB + ADDRESS_DESC_BOB + DESCRIPTION_BOB,
                expectedMessage);

        // missing address prefix
        assertParseFailure(parser, NAME_DESC_BOB + DATE_DESC_BOB + NRIC_DESC_BOB
                + PHONE_DESC_BOB + EMAIL_DESC_BOB + VALID_ADDRESS_BOB + DESCRIPTION_BOB,
                expectedMessage);

        // missing description prefix
        assertParseFailure(parser, NAME_DESC_BOB + DATE_DESC_BOB + NRIC_DESC_BOB
                + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + VALID_DESCRIPTION_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_DATE_BOB + VALID_NRIC_BOB
                + VALID_PHONE_BOB + VALID_EMAIL_BOB + VALID_ADDRESS_BOB + VALID_DESCRIPTION_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {

        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + DATE_DESC_BOB + NRIC_DESC_BOB
                + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + DESCRIPTION_BOB
                + TAG_DESC_SEVERITY + TAG_DESC_CALLED, Name.MESSAGE_CONSTRAINTS);

        // invalid date
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_DATE_DESC + NRIC_DESC_BOB
                + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + DESCRIPTION_BOB
                + TAG_DESC_SEVERITY + TAG_DESC_CALLED, Date.MESSAGE_DATE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + DATE_DESC_BOB
                + NRIC_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + DESCRIPTION_BOB
                + TAG_DESC_SEVERITY + TAG_DESC_CALLED, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + DATE_DESC_BOB
                + NRIC_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC + ADDRESS_DESC_BOB + DESCRIPTION_BOB
                + TAG_DESC_SEVERITY + TAG_DESC_CALLED, Email.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, NAME_DESC_BOB + DATE_DESC_BOB + NRIC_DESC_BOB
                + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC + DESCRIPTION_BOB
                + TAG_DESC_SEVERITY + TAG_DESC_CALLED, Address.MESSAGE_CONSTRAINTS);

        // invalid description
        assertParseFailure(parser, NAME_DESC_BOB + DATE_DESC_BOB + NRIC_DESC_BOB
                + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + INVALID_DESCRIPTION
                + TAG_DESC_SEVERITY + TAG_DESC_CALLED, Description.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BOB + DATE_DESC_BOB
                + NRIC_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + DESCRIPTION_BOB
                + INVALID_TAG_DESC + VALID_TAG_CALLED, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + DATE_DESC_BOB
                + NRIC_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + INVALID_ADDRESS_DESC + DESCRIPTION_BOB, Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + DATE_DESC_BOB
                        + NRIC_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + ADDRESS_DESC_BOB + DESCRIPTION_BOB + TAG_DESC_SEVERITY + TAG_DESC_CALLED,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
