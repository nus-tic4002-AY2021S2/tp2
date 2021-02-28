package employeetracker.logic.parser;

import static employeetracker.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static employeetracker.logic.parser.CliSyntax.*;
import static java.util.Objects.requireNonNull;

import java.util.Arrays;

import employeetracker.logic.commands.FindCommand;
import employeetracker.logic.parser.exceptions.ParseException;
import employeetracker.model.employee.Name;
import employeetracker.model.employee.NameContainsKeywordsPredicate;
import employeetracker.model.employee.RoleContainsKeywordsPredicate;

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

        String[] nameKeywords = new String[0];
        //   String[] nameKeywords = trimmedArgs.split("\\s+"); ///-----may need to change it
      //  System.out.println("--------------------actual"+Arrays.toString(nameKeywords));
       // FindEmployeeDescriptor findEmployeeDescriptor = new FindEmployeeDescriptor();
       // NameContainsKeywordsPredicate editEmployeeDescriptor = new FindCommand.NameContainsKeywordsPredicate();
        ///--------
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            Name nameObjectArray
                    = (ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
            trimmedArgs = nameObjectArray.toString().trim();
            nameKeywords = trimmedArgs.split("\\s+");
            return new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
        }
        ///--------
//        if (argMultimap.getValue(PREFIX_ROLE).isPresent()) {
//          //  editEmployeeDescriptor.setRole(ParserUtil.parseRole(argMultimap.getValue(PREFIX_ROLE).get()));
//            return new FindCommand(new RoleContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
//        }

        return new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }

}
