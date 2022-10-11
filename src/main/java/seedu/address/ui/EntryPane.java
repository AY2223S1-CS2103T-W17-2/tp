package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.commons.core.LogsCenter;

/**
 * Panel containing all entries
 */
public class EntryPane extends UiPart<Region> {
    private static final String FXML = "EntryPane.fxml";
    private final Logger logger = LogsCenter.getLogger(seedu.address.ui.EntryPane.class);

    private PersonListPanel expenseEntryPanel;
    private PersonListPanel incomeEntryPanel;

    @FXML
    private Tab expenses;

    @FXML
    private Tab income;

    @FXML
    private StackPane expenseEntryPlaceholder;

    @FXML
    private StackPane incomeEntryPlaceholder;


    /**
     * TODO: Edit expense and income
     * Creates a {@code EntryPane} with default entries
     */
    public EntryPane(PersonListPanel expenseEntry, PersonListPanel incomeEntry) {
        super(FXML);
        this.expenseEntryPanel = expenseEntry;
        this.incomeEntryPanel = incomeEntry;
        expenseEntryPlaceholder.getChildren().add(expenseEntry.getRoot());
        incomeEntryPlaceholder.getChildren().add(incomeEntry.getRoot());
    }

    public PersonListPanel getExpenseEntryPanel() {
        return this.expenseEntryPanel;
    }

    public PersonListPanel getIncomeEntryPanel() {
        return this.incomeEntryPanel;
    }
}