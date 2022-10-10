package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.entry.Entry;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Entry> filteredExpenditure;
    private final FilteredList<Entry> filteredIncome;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyPennyWise addressBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with penny wise: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredExpenditure = new FilteredList<>(this.addressBook.getExpenditureList());
        filteredIncome = new FilteredList<>(this.addressBook.getIncomeList());
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyPennyWise addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyPennyWise getAddressBook() {
        return addressBook;
    }

    @Override
    public boolean hasExpenditure(Entry entry) {
        requireNonNull(entry);
        return addressBook.hasExpenditure(entry);
    }

    @Override
    public void deleteExpenditure(Entry target) {
        addressBook.removeExpenditure(target);
    }

    @Override
    public void addExpenditure(Entry entry) {
        addressBook.addExpenditure(entry);
        updateFilteredEntryList(PREDICATE_SHOW_ALL_ENTRIES);
    }

    @Override
    public void setExpenditure(Entry target, Entry editedEntry) {
        requireAllNonNull(target, editedEntry);

        addressBook.setExpenditure(target, editedEntry);
    }

    @Override
    public boolean hasIncome(Entry entry) {
        requireNonNull(entry);
        return addressBook.hasIncome(entry);
    }

    @Override
    public void deleteIncome(Entry target) {
        addressBook.removeIncome(target);
    }

    @Override
    public void addIncome(Entry entry) {
        addressBook.addIncome(entry);
        updateFilteredEntryList(PREDICATE_SHOW_ALL_ENTRIES);
    }

    @Override
    public void setIncome(Entry target, Entry editedEntry) {
        requireAllNonNull(target, editedEntry);

        addressBook.setIncome(target, editedEntry);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Entry> getFilteredExpenditureList() {
        return filteredExpenditure;
    }

    @Override
    public ObservableList<Entry> getFilteredIncomeList() {
        return filteredIncome;
    }

    public void updateFilteredEntryList(Predicate<Entry> predicate) {
        requireNonNull(predicate);
        filteredExpenditure.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return addressBook.equals(other.addressBook)
                && userPrefs.equals(other.userPrefs)
                && filteredExpenditure.equals(other.filteredExpenditure);
    }

}
