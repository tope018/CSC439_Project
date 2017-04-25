package de.fhdw.ergoholics.brainphaser;

import org.junit.Test;

import de.fhdw.ergoholics.brainphaser.activities.main.PhaseoutActivity;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
 * Created by espahr on 4/24/17.
 */

public class PhaseoutActivityTest extends PhaseoutActivity {

    @Test
    public void testPhasesExhasted() throws Exception {
        assertFalse(phasesExhausted());
    }

    @Test
    public void testResetValues(){
        resetValues();
    }
}
