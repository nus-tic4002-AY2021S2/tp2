package employeetracker.testutil;

import employeetracker.model.EmployeeTracker;
import employeetracker.model.person.Person;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code EmployeeTracker ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private EmployeeTracker addressBook;

    public AddressBookBuilder() {
        addressBook = new EmployeeTracker();
    }

    public AddressBookBuilder(EmployeeTracker addressBook) {
        this.addressBook = addressBook;
    }

    /**
     * Adds a new {@code Person} to the {@code EmployeeTracker} that we are building.
     */
    public AddressBookBuilder withPerson(Person person) {
        addressBook.addPerson(person);
        return this;
    }

    public EmployeeTracker build() {
        return addressBook;
    }
}
