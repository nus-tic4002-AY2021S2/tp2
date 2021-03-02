package employeetracker.logic.parser;

import static employeetracker.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static employeetracker.logic.parser.CliSyntax.PREFIX_NAME;
import static employeetracker.logic.parser.CliSyntax.PREFIX_ROLE;
import static java.util.Objects.requireNonNull;

import java.util.Arrays;

import employeetracker.logic.commands.FindCommand;
import employeetracker.logic.parser.exceptions.ParseException;
import employeetracker.model.employee.Name;
import employeetracker.model.employee.NameContainsKeywordsPredicate;
import employeetracker.model.employee.Role;


/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        ///--------
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_ROLE);

        ///---------
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        String[] employeeKeywords = new String[0];
        String findBy = null;

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            Name nameObjectArray = (ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
            trimmedArgs = nameObjectArray.toString().trim();
            employeeKeywords = trimmedArgs.split("\\s+");
            findBy = String.valueOf(PREFIX_NAME);
        }

        if (argMultimap.getValue(PREFIX_ROLE).isPresent()) {
            Role roleObjectArray = (ParserUtil.parseRole(argMultimap.getValue(PREFIX_ROLE).get()));
            trimmedArgs = roleObjectArray.toString().trim();
            employeeKeywords = trimmedArgs.split("\\s+");
            findBy = String.valueOf(PREFIX_ROLE);
        }

        return new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList(employeeKeywords), findBy));


    }

}
