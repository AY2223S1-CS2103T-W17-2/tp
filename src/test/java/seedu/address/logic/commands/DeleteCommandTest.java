package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TYPE_EXPENDITURE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TYPE_INCOME;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showExpenditureAtIndex;
import static seedu.address.testutil.TypicalEntry.getTypicalPennyWise;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ENTRY;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_ENTRY;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.entry.Entry;
import seedu.address.model.entry.EntryType;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model = new ModelManager(getTypicalPennyWise(), new UserPrefs());
    private EntryType expenditureType = new EntryType(VALID_TYPE_EXPENDITURE);
    private EntryType incomeType = new EntryType(VALID_TYPE_INCOME);
    // need income version
    @Test
    public void execute_validIndexUnfilteredExpenditureList_success() {
        Entry expenditureToDelete = model.getFilteredExpenditureList().get(INDEX_FIRST_ENTRY.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_ENTRY, this.expenditureType);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS, expenditureToDelete);

        ModelManager expectedModel = new ModelManager(model.getPennyWise(), new UserPrefs());
        expectedModel.deleteExpenditure(expenditureToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredExpenditureList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredExpenditureList().size() + 1);
        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex, this.expenditureType);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_ENTRY_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredExpenditureList_success() {
        showExpenditureAtIndex(model, INDEX_FIRST_ENTRY);

        Entry expenditureToDelete = model.getFilteredExpenditureList().get(INDEX_FIRST_ENTRY.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_ENTRY, this.expenditureType);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS, expenditureToDelete);

        Model expectedModel = new ModelManager(model.getPennyWise(), new UserPrefs());
        expectedModel.deleteExpenditure(expenditureToDelete);
        showNoExpenditure(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredExpenditureList_throwsCommandException() {
        showExpenditureAtIndex(model, INDEX_FIRST_ENTRY);

        Index outOfBoundIndex = INDEX_SECOND_ENTRY;
        // ensures that outOfBoundIndex is still in bounds of penny wise list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getPennyWise().getExpenditureList().size());

        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex, this.expenditureType);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_ENTRY_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteCommand deleteFirstCommand = new DeleteCommand(INDEX_FIRST_ENTRY, expenditureType);
        DeleteCommand deleteSecondCommand = new DeleteCommand(INDEX_SECOND_ENTRY, expenditureType);

        // same object -> returns true
        assertEquals(deleteFirstCommand, deleteFirstCommand);

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(INDEX_FIRST_ENTRY, expenditureType);

        assertEquals(deleteFirstCommand, deleteFirstCommandCopy);

        // different types -> returns false
        assertNotEquals(1, deleteFirstCommand);

        // null -> returns false
        assertNotEquals(null, deleteFirstCommand);

        // different person -> returns false
        assertNotEquals(deleteFirstCommand, deleteSecondCommand);
    }

    /**
     * Updates {@code model}'s filtered list to show no expenditure.
     */
    private void showNoExpenditure(Model model) {
        model.updateFilteredEntryList(e -> false);

        assertTrue(model.getFilteredExpenditureList().isEmpty());
    }

    /**
     * Updates {@code model}'s filtered list to show no income.
     */
    private void showNoIncome(Model model) {
        model.updateFilteredEntryList(e -> false);

        assertTrue(model.getFilteredIncomeList().isEmpty());
    }
}
