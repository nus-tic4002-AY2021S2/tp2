package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.EmailUtil;
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
                email = new EmailUtil(data[1].trim()).getEmail();
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
                email = new EmailUtil(model.getFilteredPersonList().get(number - 1).toString()).getEmail();
            } else {
                return new CommandResult(String.format(MESSAGE_USAGE));
            }
            if (message.equals("") && !email.equals("")) {
                message = model.getFilteredPersonList().get(number - 1).toString();
            } else {
                return new CommandResult(String.format(MESSAGE_USAGE));
            }
            System.out.println(" email is " + email);
            System.out.println(" Message is " + message);

            new SendEmail(email, message);
            return new CommandResult(
                    String.format(MESSAGE_SUCCESS));
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

