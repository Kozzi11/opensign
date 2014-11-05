package org.openoces.opensign.certificate.plugin.capi;

import java.math.BigInteger;
import java.util.Date;

/**
 * @author Paw Figgé Kjeldgaard (pfk@miracleas.dk)
 */
public interface MicrosoftCapi {
    void hello();

    byte[][][] getCertificatesInSystemStore(String storeName);

    byte[] signMessage(byte[] toBeSigned, byte[] certificate, String algorithm);

    int getCertificateVersion(byte[] certificate);

    byte[] digest(byte[] data, String algorithm);

    int getLastErrorCode();

    int getMajorVersion();

    int getMinorVersion();

    int getKeyUsage(byte[] certificate);

    Date getNotBeforeDate(byte[] certificate);

    Date getNotAfterDate(byte[] certificate);

    BigInteger getSerialNumberBigInteger(byte[] certificate);

    String getSubjectDnString(byte[] certificate);

    String getIssuerDnString(byte[] certificate);
}
