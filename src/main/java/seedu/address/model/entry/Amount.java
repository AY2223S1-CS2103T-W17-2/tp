
package seedu.address.model.entry;

import static java.lang.Double.parseDouble;
import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.text.DecimalFormat;

/**
 * Represents an Entry's amount in the penny wise application.
 * Guarantees: immutable; is valid as declared in {@link #isValidAmount(String)}
 */
public class Amount {

    public static final String MESSAGE_CONSTRAINTS = "Expense amount should only contain positive numbers, "
                    + "and it should be formatted to accept 2 decimal places";
    public static final String VALIDATION_REGEX = "^\\s*(?=.*[1-9])\\d*\\.\\d{2}\\s*$";
    private static final DecimalFormat AMOUNT_FORMAT = new DecimalFormat("0.00");

    private final double amount;
    private final String amountString;

    /**
     * Constructs a {@code Amount}.
     *
     * @param amount A valid expense amount.
     */
    public Amount(String amount) {
        requireNonNull(amount);
        checkArgument(isValidAmount(amount), MESSAGE_CONSTRAINTS);
        this.amount = parseDouble(amount);
        this.amountString = amount;
    }

    /**
     * Returns true if a given string is a valid expense amount.
     */
    public static boolean isValidAmount(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public double getValue() {
        return amount;
    }

    /**
     * Adds the value in the two specified {@code Amount} and returns a new {@code Amount}
     * that contains a value equivalent to the sum. The addition respects the standard
     * addition operator and its properties, such as commutativity, associativity, identity
     * and distributivity.
     *
     * @param amount1 An {@code Amount} containing the value to be added
     * @param amount2 An {@code Amount} containing the value to be added
     * @return An {@code Amount} containing the value of the sum of the two specified {@Amount}.
     */
    public static Amount add(Amount amount1, Amount amount2) {
        double amount = amount1.getValue() + amount2.getValue();
        return new Amount(AMOUNT_FORMAT.format(amount));
    }

    @Override
    public String toString() {
        return amountString;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Amount // instanceof handles nulls
                && this.amount == (((Amount) other).amount)); // state check
    }

    @Override
    public int hashCode() {
        return amountString.hashCode();
    }
}

