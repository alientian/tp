package seedu.address.ui;

import static seedu.address.ui.CompanyCardUtils.createPriorityFlowPane;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import seedu.address.model.company.Company;

/**
 * An UI component that displays information of a {@code Company}.
 */
public class CompanyCard extends UiPart<Region> {

    private static final String FXML = "CompanyListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Company company;

    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label role;
    @FXML
    private Label deadline;
    @FXML
    private Label status;
    @FXML
    private FlowPane priority;

    /**
     * Creates a {@code CompanyCode} with the given {@code Company} and index to display.
     */
    public CompanyCard(Company company, int displayedIndex) {
        super(FXML);
        this.company = company;
        id.setText(displayedIndex + ". ");
        name.setText(company.getName().fullName);
        role.setText(company.getRole().jobRole);
        deadline.setText(company.getDeadline().toString());
        status.setText(company.getStatus().getDescription());
        priority.getChildren().add(createPriorityFlowPane(company, false));
    }
}
