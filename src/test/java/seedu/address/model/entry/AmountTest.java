package seedu.address.model.entry;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AmountTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Amount(null));
    }

    @Test
    public void constructor_invalidAmount_throwsIllegalArgumentException() {
        String invalidAmount = "";
        assertThrows(IllegalArgumentException.class, () -> new Amount(invalidAmount));
    }

    @Test
    public void add_validAmounts_success() {
        Amount amount1 = new seedu.address.model.entry.Amount("1.00");
        Amount amount2 = new seedu.address.model.entry.Amount("2.00");

        assertEquals(new seedu.address.model.entry.Amount("3.00"), Amount.add(amount1, amount2));
    }

    @Test
    public void isValidAmount() {
        // EP: null
        assertThrows(NullPointerException.class, () -> Amount.isValidAmount(null));

        // EP: empty string
        assertFalse(Amount.isValidAmount("")); // Boundary value
        assertFalse(Amount.isValidAmount("  "));

        // EP: not a number
        assertFalse(Amount.isValidAmount("abc"));
        assertFalse(Amount.isValidAmount("aaa"));

        // EP: negative numbers
        assertFalse(Amount.isValidAmount("-1"));
        assertFalse(Amount.isValidAmount("-1.11"));

        // EP: numbers with white space
        assertFalse(Amount.isValidAmount("3. 22"));

        // EP: positive numbers
        assertTrue(Amount.isValidAmount("3.22"));
        assertTrue(Amount.isValidAmount("30.22"));
        assertTrue(Amount.isValidAmount("30000.22"));
        assertTrue(Amount.isValidAmount(" 3.22")); // leading white space
        assertTrue(Amount.isValidAmount("3.22 ")); // trailing white space
        assertTrue(Amount.isValidAmount(" 3.22 "));

        assertFalse(Amount.isValidAmount("3")); // Zero decimal place
        assertFalse(Amount.isValidAmount("3.1")); // One decimal place
        assertFalse(Amount.isValidAmount("3.333")); // Three decimal place
    }
}
