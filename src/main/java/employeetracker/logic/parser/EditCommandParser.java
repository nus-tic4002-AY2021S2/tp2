package employeetracker.logic.parser;

import static employeetracker.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static employeetracker.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static employeetracker.logic.parser.CliSyntax.PREFIX_DATE_OF_BIRTH;
import static employeetracker.logic.parser.CliSyntax.PREFIX_DATE_OF_JOINING;
import static employeetracker.logic.parser.CliSyntax.PREFIX_EMAIL;
import static employeetracker.logic.parser.CliSyntax.PREFIX_NAME;
import static employeetracker.logic.parser.CliSyntax.PREFIX_PHONE;
import static employeetracker.logic.parser.CliSyntax.PREFIX_ROLE;
import static employeetracker.logic.parser.CliSyntax.PREFIX_SALARY;
import static employeetracker.logic.parser.CliSyntax.PREFIX_TAG;
import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import employeetracker.commons.core.index.Index;
import employeetracker.logic.commands.EditCommand;
import employeetracker.logic.commands.EditCommand.EditPersonDescriptor;
import employeetracker.logic.parser.exceptions.ParseException;
import employeetracker.model.tag.Tag;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_ROLE, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                        PREFIX_DATE_OF_BIRTH, PREFIX_DATE_OF_JOINING, PREFIX_SALARY, PREFIX_TAG);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editPersonDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_ROLE).isPresent()) {
            editPersonDescriptor.setRole(ParserUtil.parseRole(argMultimap.getValue(PREFIX_ROLE).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editPersonDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editPersonDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            editPersonDescriptor.setAddress(ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get()));
        }
        if (argMultimap.getValue(PREFIX_DATE_OF_BIRTH).isPresent()) {
            editPersonDescriptor.setDateOfBirth(ParserUtil.parseDateOfBirth(
                    argMultimap.getValue(PREFIX_DATE_OF_BIRTH).get()));
        }
        if (argMultimap.getValue(PREFIX_DATE_OF_JOINING).isPresent()) {
            editPersonDescriptor.setDateOfJoining(ParserUtil.parseDateOfJoining(
                    argMultimap.getValue(PREFIX_DATE_OF_JOINING).get()));
        }
        if (argMultimap.getValue(PREFIX_SALARY).isPresent()) {
            editPersonDescriptor.setSalary(ParserUtil.parseSalary(argMultimap.getValue(PREFIX_SALARY).get()));
        }
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editPersonDescriptor::setTags);

        if (!editPersonDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index, editPersonDescriptor);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    private Optional<Set<Tag>> parseTagsForEdit(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseTags(tagSet));
    }

}