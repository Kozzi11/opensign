package org.openoces.opensign.pdf.validator.client;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author: Michael Martinsen (mmart)
 */
public class UserPreferencesTestcase {
    private UserPreferences subject;

    @Before
    public void setup() throws Exception {
        subject = new UserPreferences();
    }

    @After
    public void tearDown() throws Exception {
        subject = null;
    }

    @Test
    public void testIllegalPreferenceName() throws Exception {

        String preferenceName = null;
        String preferenceValue = "my-value";
        try {
            subject.setPreference(preferenceName, preferenceValue);
            fail("Expected exception not thrown.");
        } catch (Exception e) {
            // This is expected.
            if (! (e instanceof IllegalArgumentException)) {
                fail("Wrong exception was thrown. Expected IllegalArgumentException.");
            }
        }

        preferenceName = " ";

        try {
            subject.setPreference(preferenceName, preferenceValue);
            fail("Expected exception not thrown.");
        } catch (Exception e) {
            // This is expected.
            if (! (e instanceof IllegalArgumentException)) {
                fail("Wrong exception was thrown. Expected IllegalArgumentException.");
            }
        }
    }

    @Test
    public void testIllegalPreferenceValue() throws Exception {

        String preferenceName = UserPreferences.PREFERENCE_PATH_OF_LATEST_FILE_ADDED;
        String preferenceValue = null;
        try {
            subject.setPreference(preferenceName, preferenceValue);
            fail("Expected exception not thrown.");
        } catch (Exception e) {
            // This is expected.
            if (! (e instanceof IllegalArgumentException)) {
                fail("Wrong exception was thrown. Expected IllegalArgumentException.");
            }
        }

        preferenceValue = "   ";

        try {
            subject.setPreference(preferenceName, preferenceValue);
            fail("Expected exception not thrown.");
        } catch (Exception e) {
            // This is expected.
            if (! (e instanceof IllegalArgumentException)) {
                fail("Wrong exception was thrown. Expected IllegalArgumentException.");
            }
        }
    }

    @Test
    public void testInAndOut() throws Exception {
        String preferenceValue = "C:\\";
        subject.setPreference(UserPreferences.PREFERENCE_PATH_OF_LATEST_FILE_ADDED, preferenceValue);
        String returnedValue = subject.getPreference(UserPreferences.PREFERENCE_PATH_OF_LATEST_FILE_ADDED);

        assertEquals(preferenceValue, returnedValue);
    }

    @Test
    public void testMissingPreference() throws Exception {
        String returnedValue = subject.getPreference(UserPreferences.PREFERENCE_PATH_OF_LATEST_FILE_ADDED);
        assertEquals("Returned value is not nul as expected.", null, returnedValue);
    }

    @Test
    public void testSaveAndLoad() throws Exception {
        String preferenceValue = "C:\\";
        subject.setPreference(UserPreferences.PREFERENCE_PATH_OF_LATEST_FILE_ADDED, preferenceValue);
        String returnedValue = subject.getPreference(UserPreferences.PREFERENCE_PATH_OF_LATEST_FILE_ADDED);

        assertEquals(preferenceValue, returnedValue);

        try {
            subject.save();
        } catch (Exception e) {
            fail("Unexpected exception during save.");
        }

        subject = null;


        UserPreferences loadedPreferences = new UserPreferences();
        loadedPreferences.load();

        String returnedValueFromLoadedPrefs =
                loadedPreferences.getPreference(UserPreferences.PREFERENCE_PATH_OF_LATEST_FILE_ADDED);

        assertEquals("Preference value returned from loaded preferences is not as expected.", preferenceValue, returnedValueFromLoadedPrefs);

    }

}
