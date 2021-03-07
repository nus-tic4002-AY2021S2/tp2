package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.notification.SendEmail;

public class SendCommand extends Command {

    public static final String COMMAND_WORD = "send";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": send email to user"
            + "Parameters: Send INDEX e/[EMAIL]"
            + "example: send 1 e/hellokitty@hotmail.com";

    public static final String MESSAGE_INVALID = "Invalid range";
    public static final String MESSAGE_SUCCESS = "Email has been send";

    private String[] keywords;

    public SendCommand(String[] keywords) {
        this.keywords = keywords;
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        String email = "";
        String message = "";
        int number = 0;
        for (Object obj : keywords) {
            if (obj.toString().contains("e/")) {
                String[] data = obj.toString().split("e/", 2);
                email = getEmail(data[1].trim());
            } else if (obj instanceof Integer) {
                number = Integer.parseInt(obj.toString().trim());

            } else {
                message = obj.toString().trim();
            }
        }

        if (number > model.getFilteredPersonList().size() || number <= 0) {
            return new CommandResult(String.format(MESSAGE_INVALID));
        } else {
            if (email == "") {
                email = getEmail(model.getFilteredPersonList().get(number - 1).toString());
            }
            if (message == "") {
                message = model.getFilteredPersonList().get(number - 1).toString();
            }
            new SendEmail(email, message);
            return new CommandResult(
                    String.format(MESSAGE_SUCCESS));
        }
    }

    private String getEmail(String email) throws CommandException {
        String regex = ".*(\\b[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}\\b).*";
        Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(email);
        if (m.matches()) {
            return m.group(1);
        } else {
            throw new CommandException("No valid email address found");
        }
    }
}

