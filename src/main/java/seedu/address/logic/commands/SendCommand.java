package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.notification.SendEmail;

public class SendCommand extends Command {

    public static final String COMMAND_WORD = "send";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": send person data to a colleague "
            + "or an email to the person in the list\n"
            + "Parameters: Send INDEX e/EMAIL or send INDEX MESSAGE\n"
            + "Example: send 1 e/hellokitty@hotmail.com OR send 1 I am not able to contact you, please call me";

    public static final String MESSAGE_INVALID = "The person index provided is invalid";
    public static final String MESSAGE_SUCCESS = "Email has been sent!";

    private String[] keywords;

    public SendCommand(String[] keywords) {
        this.keywords = keywords;
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        String email = "";
        String message = "";
        Boolean numberExsit = false;
        int number = 0;

        for (Object obj : keywords) {
            if (obj.toString().contains("e/")) {
                String[] data = obj.toString().split("e/", 2);
                email = getEmail(data[1].trim());
                message = "";
                if (number != 0) {
                    break;
                }
            } else {
                if (numberExsit == false && isNumeric(obj.toString().trim())) {
                    number = Integer.parseInt(obj.toString().trim());
                    numberExsit = true;
                } else {
                    if (email.equals("")) {
                        message = message.concat(obj.toString() + " ");
                    }
                }
            }
        }


        if (number > model.getFilteredPersonList().size() || number <= 0) {
            return new CommandResult(String.format(MESSAGE_INVALID));
        } else {
            if (email.equals("") && !message.equals("")) {
                email = getEmail(model.getFilteredPersonList().get(number - 1).toString());

            }
            if (message.equals("") && !email.equals("")) {
                message = model.getFilteredPersonList().get(number - 1).toString();
            }
            if (email.equals("") && message.equals("")) {
                return new CommandResult(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
            } else {

                new SendEmail(email, message);
                return new CommandResult(
                        String.format(MESSAGE_SUCCESS));
            }
        }
    }

    public String getEmail(String email) throws CommandException {
        String regex = ".*(\\b[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}\\b).*";
        Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(email);
        if (m.matches()) {
            return m.group(1);
        } else {
            throw new CommandException("No valid email address found");
        }
    }
    /**
     * checking the number
     * @param string
     * @return
     */
    public boolean isNumeric(String string) {
        int intValue;
        if (string == null || string.equals("")) {
            return false;
        }

        try {
            intValue = Integer.parseInt(string);
            System.out.println(intValue);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Input String cannot be parsed to Integer.");
        }
        return false;
    }
}

