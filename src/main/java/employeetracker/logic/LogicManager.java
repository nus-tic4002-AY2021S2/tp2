package employeetracker.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import employeetracker.commons.core.GuiSettings;
import employeetracker.commons.core.LogsCenter;
import employeetracker.logic.commands.Command;
import employeetracker.logic.commands.CommandResult;
import employeetracker.logic.commands.exceptions.CommandException;
import employeetracker.logic.parser.AddressBookParser;
import employeetracker.logic.parser.exceptions.ParseException;
import employeetracker.model.Model;
import employeetracker.model.ReadOnlyEmployeeTracker;
import employeetracker.model.person.Person;
import employeetracker.storage.Storage;
import javafx.collections.ObservableList;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final AddressBookParser addressBookParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        addressBookParser = new AddressBookParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = addressBookParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveAddressBook(model.getEmployeeTracker());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyEmployeeTracker getAddressBook() {
        return model.getEmployeeTracker();
    }

    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return model.getFilteredPersonList();
    }

    @Override
    public Path getAddressBookFilePath() {
        return model.getAddressBookFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }
}
