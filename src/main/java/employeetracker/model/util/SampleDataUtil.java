package employeetracker.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import employeetracker.model.EmployeeTracker;
import employeetracker.model.ReadOnlyEmployeeTracker;
import employeetracker.model.person.Address;
import employeetracker.model.person.DateOfBirth;
import employeetracker.model.person.DateOfJoining;
import employeetracker.model.person.Email;
import employeetracker.model.person.Name;
import employeetracker.model.person.Person;
import employeetracker.model.person.Phone;
import employeetracker.model.tag.Tag;

/**
 * Contains utility methods for populating {@code EmployeeTracker} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                    new Address("Blk 30 Geylang Street 29, #06-40"), new DateOfBirth("1990-12-10"),
                    new DateOfJoining("2020-01-02"), getTagSet("friends")),

            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                    new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"), new DateOfBirth("1988-03-10"),
                    new DateOfJoining("2020-01-02"), getTagSet("colleagues", "friends")),

            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                    new Address("Blk 11 Ang Mo Kio Street 74, #11-04"), new DateOfBirth("1991-11-01"),
                    new DateOfJoining("2020-01-15"), getTagSet("neighbours")),

            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                    new Address("Blk 436 Serangoon Gardens Street 26, #16-43"), new DateOfBirth("1992-05-28"),
                    new DateOfJoining("2020-02-01"), getTagSet("family")),

            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                    new Address("Blk 47 Tampines Street 20, #17-35"), new DateOfBirth("1984-08-20"),
                    new DateOfJoining("2020-02-01"), getTagSet("classmates")),

            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                    new Address("Blk 45 Aljunied Street 85, #11-31"), new DateOfBirth("1979-01-14"),
                    new DateOfJoining("2020-03-01"), getTagSet("colleagues"))
        };
    }

    public static ReadOnlyEmployeeTracker getSampleEmployeeTracker() {
        EmployeeTracker sampleAb = new EmployeeTracker();
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
