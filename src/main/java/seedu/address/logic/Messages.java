package seedu.address.logic;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.company.Company;

/**
 * Container for user visible messages.
 */
public class Messages {
    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT =
            "Oops! You have entered an invalid command format. "
                    + "Please follow the command format below and try again! \n%1$s";

    public static final String MESSAGE_INVALID_COMPANY_DISPLAYED_INDEX =
        "Oops! You have entered an index that is greater than the number of companies that you have. "
                + "Please try again!";

    public static final String MESSAGE_COMPANIES_LISTED_OVERVIEW = "Company(s) listed: %1$d";

    public static final String MESSAGE_DUPLICATE_FIELDS =
                "Oops! Multiple values are specified for the following single-valued parameter(s): ";

    public static final String MESSAGE_NON_INTEGER_INDEX = "Oops! The index you provided is not valid.\n"
            + "It should be a positive integer and within the range of 1 to "
            + Integer.MAX_VALUE + ".\n"
            + "Please try again with a valid index!";

    public static final String MESSAGE_NON_POSITIVE_INTEGER_INDEX = "Oops! The index you have given is not a "
            + "positive integer. Please try again with a positive integer!";

    public static final String MESSAGE_EMPTY_INDEX = "Oops! The index you have given is empty. "
            + "Remember to key in an index!";

    public static final String MESSAGE_EMPTY_PREFIX = "Oops! You have not entered any parameters. "
            + "Remember to follow the command format below! \n%1$s";

    public static final String MESSAGE_DUPLICATE_COMPANY_ADD_COMMAND_WITH_NO_CHANGES =
            "Oops! Duplicate entry is detected. \n"
                    + "You already have another entry with the exact same details for the "
                    + "company %s with the role %s and deadline %s at index %d.";

    public static final String MESSAGE_DUPLICATE_COMPANY_ADD_COMMAND_WITH_CHANGES =
            "Oops! Duplicate Entry Detected\n"
                    + "You already have another entry for the company %s with the role %s and deadline %s"
                    + " at index %d.\n"
                    + "Perhaps you meant to use the edit command instead? Type: edit %d %s";
    public static final String MESSAGE_DUPLICATE_COMPANY_EDIT_COMMAND =
            "Oops! Duplicate Entry Detected\nYou already have another entry for the company %s with the "
                    + "role %s and deadline %s at index %d.";

    /**
     * Returns an error message indicating the duplicate prefixes.
     */
    public static String getErrorMessageForDuplicatePrefixes(Prefix... duplicatePrefixes) {
        assert duplicatePrefixes.length > 0;

        Set<String> duplicateFields =
                Stream.of(duplicatePrefixes).map(Prefix::toString).collect(Collectors.toSet());

        return MESSAGE_DUPLICATE_FIELDS + String.join(" ", duplicateFields) + "\n"
                + "Please try again with only one value for each parameter!";
    }

    /**
     * Returns an error message indicating the duplicate company information for the add command.
     *
     * @param company
     * @param index
     * @param allChangedFields
     * @return string representing the error message
     */
    public static String getErrorMessageForDuplicateCompanyAddCommand(Company company,
                                                                      int index, String allChangedFields) {
        return allChangedFields.isEmpty()
                ? String.format(MESSAGE_DUPLICATE_COMPANY_ADD_COMMAND_WITH_NO_CHANGES,
                company.getName(), company.getRole(), company.getDeadline(), index + 1)
                : String.format(MESSAGE_DUPLICATE_COMPANY_ADD_COMMAND_WITH_CHANGES,
                company.getName(), company.getRole(), company.getDeadline(), index + 1, index + 1, allChangedFields);
    }

    /**
     * Returns an error message indicating the duplicate company information for the edit command.
     *
     * @param company
     * @param index
     * @return string representing the error message
     */
    public static String getErrorMessageForDuplicateCompanyEditCommand(Company company, int index) {
        return String.format(MESSAGE_DUPLICATE_COMPANY_EDIT_COMMAND,
                company.getName(), company.getRole(), company.getDeadline(), index + 1);
    }

    /**
     * Formats the {@code company} for display to the user.
     */
    public static String format(Company company) {
        final StringBuilder builder = new StringBuilder();
        builder.append(company.getName())
                .append("; Role: ")
                .append(company.getRole())
                .append("; Status: ")
                .append(company.getStatus())
                .append("; Deadline: ")
                .append(company.getDeadline())
                .append("; Recruiter Name: ")
                .append(company.getRecruiterName())
                .append("; Phone: ")
                .append(company.getPhone())
                .append("; Email: ")
                .append(company.getEmail())
                .append("; Priority: ")
                .append(company.getPriority());
        return builder.toString();
    }

    /**
     * Formats the {@code company} to only display the company's name to the user
     * with the left UI panel for viewing the company details.
     */
    public static String getCompanyName(Company company) {
        return company.getName().toString();
    }

    public static String getCompanyInfo(Company company) {
        return company.getName().toString() + " (" + "Role: " + company.getRole().toString()
                + ", " + "Deadline: " + company.getDeadline().toString() + ")";

    }
}
