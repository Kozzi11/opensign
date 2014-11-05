package org.openoces.opensign.pdf.validator.client;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * A class to hold the build time stamp.
 *
 * @author: Michael Martinsen (mmart)
 */
public class BuildTimestamp {
    public static final String VERSION_FILE_NAME = "version.properties";
    public static final String BUILD_TIMESTAMP_KEY = "build.timestamp";
    public static final String TIMESTAMP_NOT_AVAILABLE_TEXT = "n/a";

    private Properties versionProperties;

    public BuildTimestamp() {
        versionProperties = new Properties();
    }

    public String getTimestamp() {
        String timestamp = TIMESTAMP_NOT_AVAILABLE_TEXT;

        try {
            load();

            if (versionProperties.containsKey(BUILD_TIMESTAMP_KEY)) {
                timestamp = (String) versionProperties.get(BUILD_TIMESTAMP_KEY);
            }
        } catch (Exception e) {
            // TODO: Should be logged...
        }

        return timestamp;
    }

    private void load() throws Exception {
        versionProperties.load(new FileInputStream(VERSION_FILE_NAME));
    }

}
