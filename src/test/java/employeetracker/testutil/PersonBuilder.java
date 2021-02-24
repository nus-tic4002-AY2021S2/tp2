package employeetracker.testutil;

import java.util.HashSet;
import java.util.Set;

import employeetracker.model.person.Address;
import employeetracker.model.person.DateOfBirth;
import employeetracker.model.person.DateOfJoining;
import employeetracker.model.person.Email;
import employeetracker.model.person.Name;
import employeetracker.model.person.Person;
import employeetracker.model.person.Phone;
import employeetracker.model.person.Role;
import employeetracker.model.person.Salary;
import employeetracker.model.tag.Tag;
import employeetracker.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_ROLE = "Developer";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_DATE_OF_BIRTH = "1990-10-12";
    public static final String DEFAULT_DATE_OF_JOINING = "2020-01-01";
    public static final String DEFAULT_SALARY = "6000";

    private Name name;
    private Role role;
    private Phone phone;
    private Email email;
    private Address address;
    private DateOfBirth dateOfBirth;
    private DateOfJoining dateOfJoining;
    private Salary salary;
    private Set<Tag> tags;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        role = new Role(DEFAULT_ROLE);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        dateOfBirth = new DateOfBirth(DEFAULT_DATE_OF_BIRTH);
        dateOfJoining = new DateOfJoining(DEFAULT_DATE_OF_JOINING);
        salary = new Salary(DEFAULT_SALARY);
        tags = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        role = personToCopy.getRole();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        address = personToCopy.getAddress();
        dateOfBirth = personToCopy.getDateOfBirth();
        dateOfJoining = personToCopy.getDateOfJoining();
        salary = personToCopy.getSalary();
        tags = new HashSet<>(personToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Role} of the {@code Person} that we are building.
     */
    public PersonBuilder withRole(String role) {
        this.role = new Role(role);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Person} that we are building.
     */
    public PersonBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code DateOfBirth} of the {@code Person} that we are building.
     */
    public PersonBuilder withDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = new DateOfBirth(dateOfBirth);
        return this;
    }

    /**
     * Sets the {@code DateOfJoining} of the {@code Person} that we are building.
     */
    public PersonBuilder withDateOfJoining(String dateOfJoining) {
        this.dateOfJoining = new DateOfJoining(dateOfJoining);
        return this;
    }

    /**
     * Sets the {@code Salary} of the {@code Person} that we are building.
     */
    public PersonBuilder withSalary(String salary) {
        this.salary = new Salary(salary);
        return this;
    }

    public Person build() {
        return new Person(name, role, phone, email, address, dateOfBirth, dateOfJoining, salary, tags);
    }

}
