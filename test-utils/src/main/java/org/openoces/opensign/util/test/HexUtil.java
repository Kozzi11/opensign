package org.openoces.opensign.util.test;

/**
 * @author Paw Figgé Kjeldgaard (pfk@miracleas.dk)
 */
public class HexUtil {

    public static String getHexString(byte[] bytes) throws Exception {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
        }
        return result.toString();
    }
}
