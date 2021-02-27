package employeetracker.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import employeetracker.commons.exceptions.IllegalValueException;
import employeetracker.model.EmployeeTracker;
import employeetracker.model.ReadOnlyEmployeeTracker;
import employeetracker.model.employee.Employee;

/**
 * An Immutable EmployeeTracker that is serializable to JSON format.
 */
@JsonRootName(value = "employeetracker")
class JsonSerializableEmployeeTracker {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate employee(s).";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableEmployeeTracker} with the given persons.
     */
    @JsonCreator
    public JsonSerializableEmployeeTracker(@JsonProperty("persons") List<JsonAdaptedPerson> persons) {
        this.persons.addAll(persons);
    }

    /**
     * Converts a given {@code ReadOnlyEmployeeTracker} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableEmployeeTracker}.
     */
    public JsonSerializableEmployeeTracker(ReadOnlyEmployeeTracker source) {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code EmployeeTracker} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public EmployeeTracker toModelType() throws IllegalValueException {
        EmployeeTracker employeeTracker = new EmployeeTracker();
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Employee employee = jsonAdaptedPerson.toModelType();
            if (employeeTracker.hasPerson(employee)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            employeeTracker.addPerson(employee);
        }
        return employeeTracker;
    }

}
