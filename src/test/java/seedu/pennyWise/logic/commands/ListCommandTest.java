package seedu.pennyWise.logic.commands;

import seedu.pennyWise.model.Model;


/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;

    // @BeforeEach
    // public void setUp() {
    //     model = new ModelManager(getTypicalPennyWise(), new UserPrefs());
    //     expectedModel = new ModelManager(model.getPennyWise(), new UserPrefs());
    // }
    // @Test
    // public void execute_listIsNotFiltered_showsSameList() {
    //     assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    // }
    // @Test
    // public void execute_listIsFiltered_showsEverything() {
    //     showPersonAtIndex(model, INDEX_FIRST_PERSON);
    //     assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    // }
}
