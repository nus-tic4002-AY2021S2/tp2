package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FOLLOWUP;

import java.util.NoSuchElementException;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.FollowUpCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.FollowUp;

public class FollowUpCommandParser implements Parser<FollowUpCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the {@code RemarkCommand}
     * and returns a {@code RemarkCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FollowUpCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_FOLLOWUP);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
            ParserUtil.parseFollowUp(argMultimap.getValue(PREFIX_FOLLOWUP).orElseThrow());
        } catch (IllegalValueException | NoSuchElementException e) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FollowUpCommand.MESSAGE_USAGE));
        }

        String followUp = argMultimap.getValue(PREFIX_FOLLOWUP).orElse("");

        return new FollowUpCommand(index, new FollowUp(followUp));
    }
}
