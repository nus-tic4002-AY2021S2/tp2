package employeetracker.model.person;

import static employeetracker.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import employeetracker.model.tag.Tag;

/**
 * Represents a Person in the Employee Tracker.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;
    private final DateOfBirth dateOfBirth;
    private final DateOfJoining dateOfJoining;

    // Data fields
    private final Address address;
    private final Role role;
    private final Salary salary;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Role role, Phone phone, Email email, Address address, DateOfBirth dateOfBirth,
                  DateOfJoining dateOfJoining, Salary salary, Set<Tag> tags) {
        requireAllNonNull(name, role, phone, email, address, dateOfBirth, dateOfJoining, salary, tags);
        this.name = name;
        this.role = role;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.dateOfJoining = dateOfJoining;
        this.salary = salary;
        this.tags.addAll(tags);
    }

    public Name getName() {
        return name;
    }

    public Role getRole() {
        return role;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public DateOfBirth getDateOfBirth() {
        return dateOfBirth;
    }

    public DateOfJoining getDateOfJoining() {
        return dateOfJoining;
    }

    public Salary getSalary() {
        return salary;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both persons have the same role.
     */
    public boolean hasSameRole(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getRole().equals(getRole());
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return otherPerson.getName().equals(getName())
                && otherPerson.getRole().equals(getRole())
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getAddress().equals(getAddress())
                && otherPerson.getDateOfBirth().equals(getDateOfBirth())
                && otherPerson.getDateOfJoining().equals(getDateOfJoining())
                && otherPerson.getSalary().equals(getSalary())
                && otherPerson.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, role, phone, email, address, dateOfBirth, dateOfJoining, salary, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Role: ")
                .append(getRole())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Address: ")
                .append(getAddress())
                .append("; Date of Birth: ")
                .append(getDateOfBirth())
                .append("; Date of Joining: ")
                .append(getDateOfJoining())
                .append("; Salary: ")
                .append(getSalary());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

}
