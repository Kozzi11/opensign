package org.openoces.opensign.pdf.validator.client;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

/**
 * This is a class to hold various user preferences. For example the path of the latest file added.
 *
 * @author: Michael Martinsen (mmart)
 */
public class UserPreferences {
    /**
     * The file name of the preferences file.
     */
    private static final String PREFERENCES_FILE_NAME = "validator.properties";

    public static final String PREFERENCE_PATH_OF_LATEST_FILE_ADDED = "pathOfLatestFileAdded";

    /**
     * Properties object used to hold all preferences
     */
    Properties preferences;

    public UserPreferences() {
        preferences = new Properties();
    }

    public boolean isEmpty() {
        return preferences.isEmpty();
    }

    public void setPreference(String preference, String value) {
        if (preference == null || preference.trim().length() == 0) {
            throw new IllegalArgumentException("Preference is null or blank.");
        }
        if (value == null || value.trim().length() == 0) {
            throw new IllegalArgumentException("Preference value is null or blank.");
        }

        preferences.setProperty(preference, value);
    }

    public String getPreference(String preference) {
        if (preference == null || preference.trim().length() == 0) {
            throw new IllegalArgumentException("Requested preference cannot be null or blank.");
        }

        return (String) preferences.get(preference);
    }

    public void save() throws Exception {
        preferences.store(new FileOutputStream(PREFERENCES_FILE_NAME), "This file is updated by the PDF-validator application. Please do not edit it manually!");
    }

    public void load() throws Exception {
        preferences.load(new FileInputStream(PREFERENCES_FILE_NAME));
    }

}


















