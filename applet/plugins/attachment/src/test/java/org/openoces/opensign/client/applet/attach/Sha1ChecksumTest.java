package org.openoces.opensign.client.applet.attach;

import junit.framework.Assert;
import org.junit.Test;
import org.openoces.opensign.util.test.HexUtil;

/**
 * @author Paw Figgé Kjeldgaard (pfk@miracleas.dk)
 */
public class Sha1ChecksumTest {

    @Test
    public void getShaValue() throws Exception {
        String value = "OpenSign";
        Sha1Checksum checksum = new Sha1Checksum();
        checksum.update(value.getBytes(), 0, value.getBytes().length);
        byte[] hash = checksum.getShaValue();
        Assert.assertNotNull(hash);
        Assert.assertEquals("4a5b8099ab62351e46b601f378173739dae2953e", HexUtil.getHexString(hash));
    }


}
