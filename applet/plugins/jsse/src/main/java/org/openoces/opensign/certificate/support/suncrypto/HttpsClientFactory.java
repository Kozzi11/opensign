package org.openoces.opensign.certificate.support.suncrypto;

import java.net.URL;
import java.util.Map;

/**
 * @author Paw Figgé Kjeldgaard (pfk@miracleas.dk)
 */
public interface HttpsClientFactory {

    HttpsClient create(SunCryptoSupport sunCryptoSupport, URL url, Map<String, String> params);
}
