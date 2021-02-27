package employeetracker.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import employeetracker.model.EmployeeTracker;
import employeetracker.model.ReadOnlyEmployeeTracker;
import employeetracker.model.employee.Address;
import employeetracker.model.employee.DateOfBirth;
import employeetracker.model.employee.DateOfJoining;
import employeetracker.model.employee.Email;
import employeetracker.model.employee.Employee;
import employeetracker.model.employee.Name;
import employeetracker.model.employee.Phone;
import employeetracker.model.employee.Role;
import employeetracker.model.employee.Salary;
import employeetracker.model.tag.Tag;

/**
 * Contains utility methods for populating {@code EmployeeTracker} with sample data.
 */
public class SampleDataUtil {
    public static Employee[] getSamplePersons() {
        return new Employee[] {
            new Employee(new Name("Alex Yeoh"), new Role("Developer"), new Phone("87438807"),
                    new Email("alexyeoh@example.com"), new Address("Blk 30 Geylang Street 29, #06-40"),
                    new DateOfBirth("1990-12-10"), new DateOfJoining("2020-01-02"), new Salary("5000"),
                    getTagSet("friends")),

            new Employee(new Name("Bernice Yu"), new Role("Developer"), new Phone("99272758"),
                    new Email("berniceyu@example.com"), new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                    new DateOfBirth("1988-03-10"), new DateOfJoining("2020-01-02"), new Salary("5000"),
                    getTagSet("colleagues", "friends")),

            new Employee(new Name("Charlotte Oliveiro"), new Role("System Analyst"), new Phone("93210283"),
                    new Email("charlotte@example.com"), new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                    new DateOfBirth("1991-11-01"), new DateOfJoining("2020-01-15"), new Salary("6000"),
                    getTagSet("neighbours")),

            new Employee(new Name("David Li"), new Role("Finance Manager"), new Phone("91031282"),
                    new Email("lidavid@example.com"), new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                    new DateOfBirth("1992-05-28"), new DateOfJoining("2020-02-01"), new Salary("6000"),
                    getTagSet("family")),

            new Employee(new Name("Irfan Ibrahim"), new Role("Account Manager"), new Phone("92492021"),
                    new Email("irfan@example.com"), new Address("Blk 47 Tampines Street 20, #17-35"),
                    new DateOfBirth("1984-08-20"), new DateOfJoining("2020-02-01"), new Salary("6000"),
                    getTagSet("classmates")),

            new Employee(new Name("Roy Balakrishnan"), new Role("Project Manager"), new Phone("92624417"),
                    new Email("royb@example.com"), new Address("Blk 45 Aljunied Street 85, #11-31"),
                    new DateOfBirth("1979-01-14"), new DateOfJoining("2020-03-01"), new Salary("7000"),
                    getTagSet("colleagues"))
        };
    }

    public static ReadOnlyEmployeeTracker getSampleEmployeeTracker() {
        EmployeeTracker sampleAb = new EmployeeTracker();
        for (Employee sampleEmployee : getSamplePersons()) {
            sampleAb.addPerson(sampleEmployee);
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
