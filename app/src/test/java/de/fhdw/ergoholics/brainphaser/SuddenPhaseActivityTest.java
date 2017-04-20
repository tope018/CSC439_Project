package de.fhdw.ergoholics.brainphaser;

import org.junit.Test;

import java.util.ArrayList;

import de.fhdw.ergoholics.brainphaser.activities.main.SuddenPhaseActivity;
import de.fhdw.ergoholics.brainphaser.utility.ChallengeQuestion;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by espahr on 4/18/17.
 */

public class SuddenPhaseActivityTest extends SuddenPhaseActivity {

    @Test
    public void testCheckAnswer() throws Exception {
        ArrayList<ChallengeQuestion> array = new ArrayList<ChallengeQuestion>();
        ChallengeQuestion question = new ChallengeQuestion();
        question.setButtonOneBoolean(true);
        int arrayValue = 0;
        array.add(question);
        boolean rtnValue = checkAnswer(array, "buttonOne"); //should return false
        assertFalse(rtnValue);
    }

    @Test
    public void testCheckForTie() throws Exception {
        boolean rtnValue = checkForTie();
        assertFalse(rtnValue);
    }
}
