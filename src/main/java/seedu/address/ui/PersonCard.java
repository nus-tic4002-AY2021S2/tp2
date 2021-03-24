package seedu.address.ui;

import java.util.Comparator;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.util.DateUtil;
import seedu.address.logic.LogicManager;
import seedu.address.model.person.Person;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";
    private static final String ICON_EXCLAMATION = "/images/exclamation_point_icon.png";

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
    private Label date;
    @FXML
    private Label nric;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private Label description;
    @FXML
    private Label remarkTitle;
    @FXML
    private Label remark;
    @FXML
    private Label followUp;
    @FXML
    private Label callMessage;
    @FXML
    private FlowPane tags;
    @FXML
    private ImageView displayIcon;

    /**
     * Constructs a {@code PersonCard}.
     *
     * @param person Represents a Person in the address book.
     * @param displayedIndex The index of the Person.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        date.setText(person.getDate().value);
        nric.setText(person.getNric().value);
        phone.setText(person.getPhone().value);
        address.setText(person.getAddress().value);
        email.setText(person.getEmail().value);
        description.setText(person.getDescription().value);
        description.setWrapText(true);
        remark.setText(person.getRemark().value);
        remark.setWrapText(true);
        followUp.setText(person.getFollowUp().value + " days");
        remarkTitle.setText("");
        callMessage.setText("");

        if (new DateUtil(person.getFollowUp().value, person.getDate().value).isLastDay()
            && !person.getFollowUp().value.equals("0")) {
            displayIcon.setImage(new Image(ICON_EXCLAMATION));
            callMessage.setText("Call Today!");
        }

        if (!person.getRemark().value.equals("")) {
            final Logger logger = LogsCenter.getLogger(LogicManager.class);
            logger.info("Remark present; remarkTitle will be displayed");
            remarkTitle.setText("Officer's Remark");
        }
        person.getTags().stream()
            .sorted(Comparator.comparing(tag -> tag.tagName))
            .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
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
