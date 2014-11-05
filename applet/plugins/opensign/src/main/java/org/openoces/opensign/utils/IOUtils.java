package org.openoces.opensign.utils;

import java.io.*;

public class IOUtils {

    protected IOUtils() {}

    public static void close(InputStream inputStream) {
        try {
            if (inputStream != null) {
                inputStream.close();
                inputStream = null;
            }
        } catch (IOException e) {
            FileLog.debug(e.getMessage());
        }
    }

    public static void close(OutputStream outputStream) {
        try {
            if (outputStream != null) {
                outputStream.close();
                outputStream = null;
            }
        } catch (IOException e) {
            FileLog.debug(e.getMessage());
        }
    }

    public static void close(Reader reader) {
        try {
            if (reader != null) {
                reader.close();
            }
        } catch (IOException e) {
            FileLog.debug(e.getMessage());
        }
    }

    public static void close(Writer writer) {
        try {
            if (writer != null) {
                writer.close();
            }
        } catch (IOException e) {
            FileLog.debug(e.getMessage());
        }
    }

}
