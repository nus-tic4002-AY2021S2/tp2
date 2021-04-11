package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Address;
import seedu.address.model.person.Date;
import seedu.address.model.person.Description;
import seedu.address.model.person.Email;
import seedu.address.model.person.FollowUp;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {

    public static final Remark EMPTY_REMARK = new Remark("");

    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"),
                new Date("02-04-2021"),
                new FollowUp("7"),
                new Nric("S1234567A"),
                new Phone("87438807"),
                new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                new Description("This man is a construction site manager who flew a drone over the Istana, "
                    + "beyond the boundary of his nearby work site, at 8.35am."),
                new Remark("He could be just curious to see what the Istana looks like."),
                getTagSet("HighPriority", "CalledOnce")),
            new Person(new Name("Bernice Yu"),
                new Date("22-02-2021"),
                new FollowUp("30"),
                new Nric("S7654321B"),
                new Phone("99272758"),
                new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                new Description("She was molested by a Senior Investigations Officer "
                    + "at the Jurong Police Division Headquarters in the interview room around 9.00pm."),
                new Remark("She informed a policeman at the Police Cantonment Complex 5 days later."),
                getTagSet("MediumPriority", "NeverCalled")),
            new Person(new Name("Charlotte Oliveiro"),
                new Date("21-02-2021"),
                new FollowUp("14"),
                new Nric("S5671234C"),
                new Phone("93210283"),
                new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                new Description("This woman was caught drink-driving at Clarke Quay along the River Valley Road, "
                    + "at 3.03am."),
                EMPTY_REMARK,
                getTagSet("LowPriority", "NeverCalled")),
            new Person(new Name("Irfan Ibrahim"),
                new Date("19-03-2021"),
                new FollowUp("7"),
                new Nric("S2456752E"),
                new Phone("92492021"),
                new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                new Description("He is involved in a neighbour dispute with Roy Balakrishnan, about a huge "
                    + "potted plant that fell and broke into pieces at 6.45pm and requests for compensation."),
                new Remark("He claims that Roy bumped into the plant while walking past."),
                getTagSet("LowPriority", "CalledOnce")),
            new Person(new Name("Roy Balakrishnan"),
                new Date("19-03-2021"),
                new FollowUp("7"),
                new Nric("S2486752F"),
                new Phone("92624417"),
                new Email("royb@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-32"),
                new Description("He is involved in a neighbour dispute with Irfan Ibrahim, about a huge "
                    + "potted plant that fell and broke into pieces at 6.45pm and requests for compensation."),
                new Remark("He claims that there was a strong wind which toppled the plant."),
                getTagSet("LowPriority", "CalledOnce"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
            .map(Tag::new)
            .collect(Collectors.toSet());
    }

}
