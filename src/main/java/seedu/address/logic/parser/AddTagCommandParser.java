package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDTAG;

import java.util.NoSuchElementException;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.AddTagCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new {@code RemoveTagCommand} object
 */
public class AddTagCommandParser implements Parser<AddTagCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code RemoveTagCommand}
     * and returns a {@code RemoveTagCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddTagCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_ADDTAG);
        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
            ParserUtil.parseTag(argMultimap.getValue(PREFIX_ADDTAG).orElseThrow());
        } catch (IllegalValueException | NoSuchElementException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddTagCommand.MESSAGE_USAGE), e);
        }
        return new AddTagCommand(index, ParserUtil.parseTag(argMultimap.getValue(PREFIX_ADDTAG).get()));
    }
}
