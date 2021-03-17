package seedu.address.ui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.Person;
import seedu.address.model.person.appointment.Appointment;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Person person;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private FlowPane tags;
    @FXML
    private FlowPane appointments;
    @FXML
    private FlowPane noOfAppointments;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        address.setText(person.getAddress().value);
        phone.setText(person.getPhone().value);
        email.setText(person.getEmail().value);

        //Newline
        Region p = new Region();
        p.setPrefSize(Double.MAX_VALUE, 0.0);

        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        int count = 0;
        if (person.getAppointment().size() > 0 && person.isViewAppInd()) {
            String displayResult = "";
            person.getAppointment().stream()
                    .sorted(Comparator.comparing(appointment -> appointment.appointmentDescription));
            ArrayList<Appointment> appointmentList = new ArrayList<>(person.getAppointment());
            Collections.sort(appointmentList);
            for (Appointment appointment : appointmentList) {
                count++;
                displayResult += count + ". "
                        + appointment.getDate() + " - " + appointment.appointmentDescription + "\n";
            }
            appointments.getChildren().addAll(new Label(displayResult));
            appointments.getChildren().add(lineBreak());
        } else {
            noOfAppointments.getChildren().add(new Label(person.getAppointment().size() + " Appointments"));
        }

    }

    private Region lineBreak() {
        return new Region() {{
                setPrefSize(Double.MAX_VALUE, 0.0);
            }};
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonCard)) {
            return false;
        }

        // state check
        PersonCard card = (PersonCard) other;
        return id.getText().equals(card.id.getText())
                && person.equals(card.person);
    }
}
