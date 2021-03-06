package org.openoces.opensign.certificate.support.suncrypto;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author Paw Figgé Kjeldgaard (pfk@miracleas.dk)
 */
public class DefaultURLConnectionFactory implements URLConnectionFactory {

    @Override
    public URLConnection create(URL url) throws IOException {
        return url.openConnection();
    }
}
