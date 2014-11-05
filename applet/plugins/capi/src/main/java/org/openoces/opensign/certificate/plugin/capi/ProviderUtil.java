package org.openoces.opensign.certificate.plugin.capi;

import java.security.Provider;
import java.security.Security;

/**
 * @author Paw Figgé Kjeldgaard (pfk@miracleas.dk)
 */
public class ProviderUtil {

    public static Provider getProvider(String providerName) {
        for (Provider provider : Security.getProviders()) {
            if (provider.getName().startsWith(providerName)) {
                return provider;
            }
        }
        return null;
    }
}
