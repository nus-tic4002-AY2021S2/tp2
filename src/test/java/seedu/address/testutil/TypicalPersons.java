package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_CALLED;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_SEVERITY;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Person;


/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline").withDate("02-02-2021")
        .withNric("S1234567B").withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
        .withPhone("94351253").withRemark("She likes aardvarks.").withFollowUp("7")
        .withDescription("She was molested by a Senior Investigations Officer "
            + "at the Jurong Police Division Headquarters in the interview room around 9.00pm.")
        .withTags("NeverCalled").build();
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier").withDate("02-02-2021")
        .withNric("S1234569B").withAddress("311, Clementi Ave 2, #02-25").withRemark("He can't take beer!")
        .withEmail("johnd@example.com").withPhone("98765432").withFollowUp("7")
        .withDescription("This man is a construction site manager who flew a drone over the Istana, "
            + "beyond the boundary of his nearby work site, at 8.35am.")
        .withTags("LowPriority", "NeverCalled").build();
    public static final Person CARL = new PersonBuilder().withName("Carl Kurz").withDate("02-02-2021")
        .withNric("S1234567C").withPhone("95352563")
        .withDescription("This man is a construction site manager who flew a drone over the Istana, "
            + "beyond the boundary of his nearby work site, at 8.35am.")
        .withEmail("heinz@example.com").withAddress("wall street").build();
    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier").withDate("02-02-2021")
        .withNric("S1234565E").withPhone("87652533")
        .withDescription("This man is a construction site manager who flew a drone over the Istana, "
            + "beyond the boundary of his nearby work site, at 8.35am.")
        .withEmail("cornelia@example.com").withAddress("10th street").withTags("NeverCalled").build();
    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer").withDate("02-02-2021")
        .withNric("S2234567B").withPhone("94822241")
        .withDescription("She was molested by a Senior Investigations Officer "
            + "at the Jurong Police Division Headquarters in the interview room around 9.00pm.")
        .withEmail("werner@example.com").withAddress("michegan ave").build();
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz").withDate("02-02-2021")
        .withNric("S3234567B").withPhone("94824272")
        .withDescription("She was molested by a Senior Investigations Officer "
            + "at the Jurong Police Division Headquarters in the interview room around 9.00pm.")
        .withEmail("lydia@example.com").withAddress("little tokyo").build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best").withDate("02-02-2021")
        .withNric("S1234567F").withPhone("94824423")
        .withDescription("This man is a construction site manager who flew a drone over the Istana, "
            + "beyond the boundary of his nearby work site, at 8.35am.")
        .withEmail("anna@example.com").withAddress("4th street").build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier")
        .withNric("S9234567B").withPhone("84824244")
        .withDescription("This man is a construction site manager who flew a drone over the Istana, "
            + "beyond the boundary of his nearby work site, at 8.35am.")
        .withEmail("stefan@example.com").withAddress("little india").build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller")
        .withNric("S9134567B").withPhone("84821315")
        .withDescription("She was molested by a Senior Investigations Officer "
            + "at the Jurong Police Division Headquarters in the interview room around 9.00pm.")
        .withEmail("hans@example.com").withAddress("chicago ave").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY).withDate(VALID_DATE_AMY)
        .withNric(VALID_NRIC_AMY).withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY)
        .withAddress(VALID_ADDRESS_AMY).withDescription(VALID_DESCRIPTION_AMY)
        .withTags(VALID_TAG_CALLED).build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB).withDate(VALID_DATE_BOB)
        .withNric(VALID_NRIC_BOB).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
        .withAddress(VALID_ADDRESS_BOB).withDescription(VALID_DESCRIPTION_BOB)
        .withTags(VALID_TAG_SEVERITY, VALID_TAG_CALLED).build();

    private TypicalPersons() {
    } // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }
        return ab;
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
