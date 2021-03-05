package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.notification.SendEmail;

public class SendCommand extends Command {

    public static final String COMMAND_WORD = "send";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": send email to user"
            + "Parameters: Send INDEX e/[EMAIL]"
            + "example: send 1 e/hellokitty@hotmail.com";

    public static final String MESSAGE_INVALID = "Inalid INPUT";
    public static final String MESSAGE_SUCCESS = "Email has been send";

    private String[] keywords;

    public SendCommand(String[] keywords) {
        this.keywords = keywords;
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        String email = "";
        int number = 0;
        for (Object obj: keywords) {
            if (obj.toString().contains("e/")) {
                String[] data = obj.toString().split("e/", 2);
                email = data[1].trim();
            } else {
                number = Integer.parseInt(obj.toString().trim());
            }
        }

        if (number > model.getFilteredPersonList().size() || number <= 0) {
            return new CommandResult(String.format(MESSAGE_INVALID));
        } else {
            new SendEmail(email, model.getFilteredPersonList().get(number - 1).toString());
            return new CommandResult(
                    String.format(MESSAGE_SUCCESS));
        }
    }
}
