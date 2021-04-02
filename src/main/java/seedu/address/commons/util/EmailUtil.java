package seedu.address.commons.util;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.exceptions.CommandException;


public class EmailUtil {
    private final String email;
    private final String regex = "^[\\w!#$%&'*+/=?`{|}~^.-]+@[^\\W_][a-zA-Z0-9.-]*[^\\W_]"
            + "+\\.[^\\W_][a-zA-Z0-9.-]*[^\\W_]$";

    public EmailUtil(String email) {
        this.email = email;
    }

    public String getEmail() throws CommandException {
        Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(email);
        if (m.matches()) {
            return m.group(1);
        } else {
            throw new CommandException("Not a valid email address or there is empty space after [e/]");
        }
    }

    /**
     * check email
     */

    public boolean checkEmail() {
        Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(email);
        if (m.matches()) {
            return true;
        } else {
            return false;
        }
    }
}
