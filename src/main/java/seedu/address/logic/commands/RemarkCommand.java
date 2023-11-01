package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_COMPANIES;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.company.Company;
import seedu.address.model.company.Remark;

/**
 * Adds a remark to an existing company in the address book.
 */
public class RemarkCommand extends Command {

    public static final String COMMAND_WORD = "remark";
    public static final String REMOVE_COMMAND_WORD = "unremark";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the remark of the company identified "
            + "by the index number used in the displayed company list. "
            + "Existing remark will be overwritten by the input.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_REMARK + "REMARK\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_REMARK + "Likes to swim.";
    public static final String MESSAGE_REMOVE_USAGE = REMOVE_COMMAND_WORD
            + ": Removes the remark identified by the index number used in the displayed company list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_NO_REMARK = "Oops! No remarks is entered. "
            + "Use " + PREFIX_REMARK + "REMARK to add remark. Please try again!";
    public static final String MESSAGE_ADD_REMARK_SUCCESS = "Added remark to Company: %1$s";
    public static final String MESSAGE_DELETE_REMARK_SUCCESS = "Removed remark from Company: %1$s";

    private final Index index;
    private final Remark remark;

    /**
     * @param index of the company in the filtered company list to edit the remark.
     * @param remark of the company to be updated to.
     */
    public RemarkCommand(Index index, Remark remark) {
        requireAllNonNull(index, remark);

        this.index = index;
        this.remark = remark;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Company> lastShownList = model.getFilteredCompanyList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_COMPANY_DISPLAYED_INDEX);
        }


        Company companyToRemark = lastShownList.get(index.getZeroBased());
        Company remarkedCompany = new Company(companyToRemark.getName(), companyToRemark.getPhone(),
                companyToRemark.getEmail(), companyToRemark.getRole(), companyToRemark.getDeadline(),
                companyToRemark.getStatus(), companyToRemark.getRecruiterName(), companyToRemark.getPriority(),
                remark);

        model.setCompany(companyToRemark, remarkedCompany);
        model.updateFilteredCompanyList(PREDICATE_SHOW_ALL_COMPANIES);
        model.setCurrentViewedCompany(remarkedCompany);

        return new CommandResult(generateSuccessMessage(remarkedCompany));
    }

    /**
     * Generates a command execution success message based on whether the remark is added to or removed from
     * {@code companyToEdit}.
     */
    private String generateSuccessMessage(Company companyToEdit) {
        String message = !remark.value.isEmpty() ? MESSAGE_ADD_REMARK_SUCCESS : MESSAGE_DELETE_REMARK_SUCCESS;
        return String.format(message, Messages.getCompanyName(companyToEdit));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RemarkCommand)) {
            return false;
        }

        RemarkCommand e = (RemarkCommand) other;
        return index.equals(e.index)
                && remark.equals(e.remark);
    }
}
