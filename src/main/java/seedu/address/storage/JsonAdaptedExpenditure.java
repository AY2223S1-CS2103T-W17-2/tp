package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.entry.Amount;
import seedu.address.model.entry.Date;
import seedu.address.model.entry.Description;
import seedu.address.model.entry.Entry;
import seedu.address.model.entry.EntryType;
import seedu.address.model.entry.Expenditure;
import seedu.address.model.entry.Tag;

/**
 * Jackson-friendly version of {@link Expenditure}.
 */
public class JsonAdaptedExpenditure extends JsonAdaptedEntry {
    /**
     * Constructs a {@code JsonAdaptedEntry} with the given person details.
     */
    protected static EntryType type = new EntryType("e");
    @JsonCreator
    public JsonAdaptedExpenditure(@JsonProperty("description") String description,
                                  @JsonProperty("amount") String amount,
                                  @JsonProperty("date") String date,
                                  @JsonProperty("tagged") String tagged) {
        super(description, amount, date, tagged);
    }

    public JsonAdaptedExpenditure(Entry source) {
        super(source);
    }

    /**
     * Checks the JsonEntry for validity
     * @throws IllegalValueException if the json entry is not valid
     */
    @Override
    public void checkIsValidJsonEntry() throws IllegalValueException {
        super.checkIsValidJsonEntry();
        if (!Tag.isValidTagName(JsonAdaptedExpenditure.type, tagged)) {
            throw new IllegalValueException(Tag.EXPENDITURE_CONSTRAINTS);
        }
    }

    @Override
    public Expenditure toModelType() throws IllegalValueException {
        checkIsValidJsonEntry();
        // final List<Tag> personTags = new ArrayList<>();
        // for (JsonAdaptedTag tag : tagged) {
        //     personTags.add(tag.toModelType());
        // }
        final Description modelDescription = new Description(description);
        final Amount modelAmount = new Amount(amount);
        final Date modelDate = new Date(date);
        final Tag modelTag = new Tag(JsonAdaptedExpenditure.type, tagged);
        return new Expenditure(modelDescription, modelDate, modelAmount, modelTag);
    }
}
