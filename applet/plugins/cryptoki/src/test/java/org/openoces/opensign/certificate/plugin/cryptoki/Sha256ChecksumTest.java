package org.openoces.opensign.certificate.plugin.cryptoki;

import junit.framework.Assert;
import org.junit.Test;
import org.openoces.opensign.util.test.HexUtil;

/**
 * @author Paw Figgé Kjeldgaard (pfk@miracleas.dk)
 */
public class Sha256ChecksumTest {

    @Test
    public void getShaValue() throws Exception {
        Sha256Checksum checksum = new Sha256Checksum("src/test/resources/opensign.txt");
        byte[] hash = checksum.getShaValue();
        Assert.assertNotNull(hash);
        Assert.assertEquals("07977f1c22a91c9c2026dca15964318e135b064cd304d208fe5c381fbb9208dc", HexUtil.getHexString(hash));
    }


}
