package de.fhdw.ergoholics.brainphaser;

import org.junit.Test;

import de.fhdw.ergoholics.brainphaser.activities.main.SuddenPhaseActivity;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by espahr on 4/18/17.
 */

public class SuddenPhaseActivityTest extends SuddenPhaseActivity {

    @Test
    public void testCheckAnswer() throws Exception {
        boolean[] testArrayForFalse = new boolean[] {true, true};
        boolean rtnValue = checkAnswer(testArrayForFalse);
        assertFalse(rtnValue);
    }

    @Test
    public void testCheckForTie() throws Exception {
        boolean[] testArrayForFalse = new boolean[] {true, true};
        boolean[] testArrayForTrue = new boolean[] {false};
        boolean rtnValue = checkForTie(testArrayForFalse);
        assertFalse(rtnValue);
        rtnValue = checkForTie(testArrayForTrue);
        assertTrue(rtnValue);
    }
}
