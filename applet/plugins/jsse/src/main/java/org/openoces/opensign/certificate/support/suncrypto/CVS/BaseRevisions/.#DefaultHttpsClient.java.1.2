package org.openoces.opensign.certificate.support.suncrypto;

import org.openoces.opensign.crypto.SSLResponse;
import org.openoces.opensign.utils.Base64;
import org.openoces.opensign.utils.FileLog;
import org.openoces.opensign.utils.IOUtils;

import javax.net.ssl.*;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.*;
import java.util.Map;

/**
 * @author Paw Figgé Kjeldgaard (pfk@miracleas.dk)
 */
public class DefaultHttpsClient implements HttpsClient {
    private static final String[] trustedRootCertificates = new String[]{
            //Globalsign root
            "MIIDdTCCAl2gAwIBAgILAgAAAAAA1ni3lAUwDQYJKoZIhvcNAQEEBQAwVzELMAkG" +
                    "A1UEBhMCQkUxGTAXBgNVBAoTEEdsb2JhbFNpZ24gbnYtc2ExEDAOBgNVBAsTB1Jv" +
                    "b3QgQ0ExGzAZBgNVBAMTEkdsb2JhbFNpZ24gUm9vdCBDQTAeFw05ODA5MDExMjAw" +
                    "MDBaFw0xNDAxMjgxMjAwMDBaMFcxCzAJBgNVBAYTAkJFMRkwFwYDVQQKExBHbG9i" +
                    "YWxTaWduIG52LXNhMRAwDgYDVQQLEwdSb290IENBMRswGQYDVQQDExJHbG9iYWxT" +
                    "aWduIFJvb3QgQ0EwggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIBAQDaDuaZ" +
                    "jc6j40+Kfvvxi4Mla+pIH/EqsLmVEQS98GPR4mdmzxzdzxtIK+6NiY6arymAZavp" +
                    "xy0Sy6scTHAHoT0KMM0VjU/43dSMUBUc71DuxC73/OlS8pF94G3VNTCOXkNz8kHp" +
                    "1Wrjsok6Vjk4bwY8iGlbKk3Fp1S4bInMm/k8yuX9ifUSPJJ4ltbcdG6TRGHRjcdG" +
                    "snUOhugZitVtbNV4FpWi6cgKOOvyJBNPc1STE4U6G7weNLWLBYy5d4ux2x8gkasJ" +
                    "U26Qzns3dLlwR5EiUWMWea6xrkEmCMgZK9FGqkjWZCrXgzT/LCrBbBlDSgeF59N8" +
                    "9iFo7+ryUp9/k5DPAgMBAAGjQjBAMA4GA1UdDwEB/wQEAwIABjAdBgNVHQ4EFgQU" +
                    "YHtmGkUNl8qJUC99BM00qP/8/UswDwYDVR0TAQH/BAUwAwEB/zANBgkqhkiG9w0B" +
                    "AQQFAAOCAQEArqqf/LfSyx9fOSkoGJ40yWxPbxrwZKJwSk8ThptgKJ7ogUmYfQq7" +
                    "5bCdPTbbjwVR/wkxKh/diXeeDy5slQTthsu0AD+EAk2AaioteAuubyuig0SDH81Q" +
                    "gkwkr733pbTIWg/050deSY43lv6aiAU62cDbKYfmGZZHpzqmjIs8d/5GY6dT2iHR" +
                    "rH5Jokvmw2dZL7OKDrssvamqQnw1wdh/1acxOk5jQzmvCLBhNIzTmKlDNPYPhyk7" +
                    "ncJWWJh3w/cbrPad+D6qp1RF8PX51TFl/mtYnHGzHtdS6jIX/EBgHcl5JLL2bP2o" +
                    "Zg6C3ZjL2sJETy6ge/L3ayx2EYRGinij4w==",
            // Thawte Server CA
            "MIIDEzCCAnygAwIBAgIBATANBgkqhkiG9w0BAQQFADCBxDELMAkGA1UEBhMCWkEx" +
                    "FTATBgNVBAgTDFdlc3Rlcm4gQ2FwZTESMBAGA1UEBxMJQ2FwZSBUb3duMR0wGwYD" +
                    "VQQKExRUaGF3dGUgQ29uc3VsdGluZyBjYzEoMCYGA1UECxMfQ2VydGlmaWNhdGlv" +
                    "biBTZXJ2aWNlcyBEaXZpc2lvbjEZMBcGA1UEAxMQVGhhd3RlIFNlcnZlciBDQTEm" +
                    "MCQGCSqGSIb3DQEJARYXc2VydmVyLWNlcnRzQHRoYXd0ZS5jb20wHhcNOTYwODAx" +
                    "MDAwMDAwWhcNMjAxMjMxMjM1OTU5WjCBxDELMAkGA1UEBhMCWkExFTATBgNVBAgT" +
                    "DFdlc3Rlcm4gQ2FwZTESMBAGA1UEBxMJQ2FwZSBUb3duMR0wGwYDVQQKExRUaGF3" +
                    "dGUgQ29uc3VsdGluZyBjYzEoMCYGA1UECxMfQ2VydGlmaWNhdGlvbiBTZXJ2aWNl" +
                    "cyBEaXZpc2lvbjEZMBcGA1UEAxMQVGhhd3RlIFNlcnZlciBDQTEmMCQGCSqGSIb3" +
                    "DQEJARYXc2VydmVyLWNlcnRzQHRoYXd0ZS5jb20wgZ8wDQYJKoZIhvcNAQEBBQAD" +
                    "gY0AMIGJAoGBANOkUG7I/1Zr5s9dtuoMaHVHoqrC2oQl/Kj0R1HahbUgdJSGHg91" +
                    "yekIYfUGbTBuFRkC6VLAYttNmZ7iagxEOM3+vuNkCXDF/rFrKbYvScg71CcEJRCX" +
                    "L+eQbcAoQpnXTEPew/UhbVSfXcNY4cDk2VuwuNy0e982OsK1ZiIS1ocNAgMBAAGj" +
                    "EzARMA8GA1UdEwEB/wQFMAMBAf8wDQYJKoZIhvcNAQEEBQADgYEAB/pMaVz7lcxG" +
                    "7oWDTSEwjsrZqG9JGubaUeNgcGyEYRGhGshIPllDfU+VPaGLtwtimHp1it2ITk6e" +
                    "QNuozDJ0uW8NxuOzRAvZim+aKZuZGCg70eNAKJpaPNW15yAbi8qkq43pUdniTCxZ" +
                    "qdq5snUb9kLy78fyGPmJvKP/iiMucEc=",
            // TDC SSL CA
            "MIIEBzCCAu+gAwIBAgIEPiwgOTANBgkqhkiG9w0BAQUFADA3MQswCQYDVQQGEwJE" +
                    "SzEMMAoGA1UEChMDVERDMRowGAYDVQQLExFUREMgU1NMIFNlcnZlciBDQTAeFw0w" +
                    "MzAxMjAxNTQzNTVaFw0zNzAxMjAxNjEzNTVaMDcxCzAJBgNVBAYTAkRLMQwwCgYD" +
                    "VQQKEwNUREMxGjAYBgNVBAsTEVREQyBTU0wgU2VydmVyIENBMIIBIjANBgkqhkiG" +
                    "9w0BAQEFAAOCAQ8AMIIBCgKCAQEA2ccg54uj7AKBZCwhFQbn0ovjkDjjFw2pi1eM" +
                    "HlWqlHLm6dUMtfuL77fIkNUAFSurGfMFL1xoaXVaq5z4c7gCG2pEkHdg3F4RHAOv" +
                    "6JvpbMDBRFLyNUgC6x9tk4YG9qGsGtDTljAT+ATKorFPszhoCP5SAKOGgnMY/MGo" +
                    "xYhOFjjc5+PfpqZNO5nG/FbzzB+lwrgEuwi6odMA92/2Zgi1xRr0AxfnhkZPfKU9" +
                    "XHrLEsaPnk3DH2gXf1q++h4YMSwWX7Kqp+ffKA2wIIeKOZ33bXNyMXjgi6EYQyAL" +
                    "jCpZCdZX4ok9DSUEx1WXOy2AOrKMcMTF1vvJOxAQOJthyq0EewIDAQABo4IBGTCC" +
                    "ARUwEQYJYIZIAYb4QgEBBAQDAgAHMFkGA1UdHwRSMFAwTqBMoEqkSDBGMQswCQYD" +
                    "VQQGEwJESzEMMAoGA1UEChMDVERDMRowGAYDVQQLExFUREMgU1NMIFNlcnZlciBD" +
                    "QTENMAsGA1UEAxMEQ1JMMTArBgNVHRAEJDAigA8yMDAzMDEyMDE1NDM1NVqBDzIw" +
                    "MzcwMTIwMTYxMzU1WjALBgNVHQ8EBAMCAQYwHwYDVR0jBBgwFoAU/R7Cswg6ldHU" +
                    "pYfOzUGEc+8zdA0wHQYDVR0OBBYEFP0ewrMIOpXR1KWHzs1BhHPvM3QNMAwGA1Ud" +
                    "EwQFMAMBAf8wHQYJKoZIhvZ9B0EABBAwDhsIVjYuMDo0LjADAgSQMA0GCSqGSIb3" +
                    "DQEBBQUAA4IBAQCSW6NVUU2DncWp8F1c1ZMB9erEv+IHSKrCQAeAZNHVYL6zmoN0" +
                    "676M7msXdNzQPPBmJY1kKO/Z7PtnMvyR+8+5HggvI7E+OJ/87lUkHviJjvTYVr6r" +
                    "5REI6FDNB8R7hYPdW/DyZJCcgj6wa62DfRA8IfNQqNfUnZ5XEfRIGqWEFPvB0gaF" +
                    "mJ0JZpZzDm/gZ/gPvRBqEllAeaSttze/EIxPJ58FfdaVh/1Embujj0rqzfJvpw/a" +
                    "3nvvrDMJ9KHndX+c3g3ogi8H2Psnbsp6j8thqGnnE0pVYNL8mpS8/UQZM5NZxv2C" +
                    "oaxVDWHUkiritzawvUO14S2dV9YfnEuuskJG",
            "MIIE2DCCBEGgAwIBAgIEN0rSQzANBgkqhkiG9w0BAQUFADCBwzELMAkGA1UEBhMC" +
                    "VVMxFDASBgNVBAoTC0VudHJ1c3QubmV0MTswOQYDVQQLEzJ3d3cuZW50cnVzdC5u" +
                    "ZXQvQ1BTIGluY29ycC4gYnkgcmVmLiAobGltaXRzIGxpYWIuKTElMCMGA1UECxMc" +
                    "KGMpIDE5OTkgRW50cnVzdC5uZXQgTGltaXRlZDE6MDgGA1UEAxMxRW50cnVzdC5u" +
                    "ZXQgU2VjdXJlIFNlcnZlciBDZXJ0aWZpY2F0aW9uIEF1dGhvcml0eTAeFw05OTA1" +
                    "MjUxNjA5NDBaFw0xOTA1MjUxNjM5NDBaMIHDMQswCQYDVQQGEwJVUzEUMBIGA1UE" +
                    "ChMLRW50cnVzdC5uZXQxOzA5BgNVBAsTMnd3dy5lbnRydXN0Lm5ldC9DUFMgaW5j" +
                    "b3JwLiBieSByZWYuIChsaW1pdHMgbGlhYi4pMSUwIwYDVQQLExwoYykgMTk5OSBF" +
                    "bnRydXN0Lm5ldCBMaW1pdGVkMTowOAYDVQQDEzFFbnRydXN0Lm5ldCBTZWN1cmUg" +
                    "U2VydmVyIENlcnRpZmljYXRpb24gQXV0aG9yaXR5MIGdMA0GCSqGSIb3DQEBAQUA" +
                    "A4GLADCBhwKBgQDNKIM0VBuJ8w+vN5Ex/68xYMmo6LIQaO2f55M28Qpku0f1BBc/" +
                    "I0dNxScZgSYMVHINiC3ZH5oSn7yzcdOAGT9HZnuMNSjSuQrfJNqc1lB5gXpa0zf3" +
                    "wkrYKZImZNHkmGw6AIr1NJtl+O3jEP/9uElY3KDegjlrgbEWGWG5VLbmQwIBA6OC" +
                    "AdcwggHTMBEGCWCGSAGG+EIBAQQEAwIABzCCARkGA1UdHwSCARAwggEMMIHeoIHb" +
                    "oIHYpIHVMIHSMQswCQYDVQQGEwJVUzEUMBIGA1UEChMLRW50cnVzdC5uZXQxOzA5" +
                    "BgNVBAsTMnd3dy5lbnRydXN0Lm5ldC9DUFMgaW5jb3JwLiBieSByZWYuIChsaW1p" +
                    "dHMgbGlhYi4pMSUwIwYDVQQLExwoYykgMTk5OSBFbnRydXN0Lm5ldCBMaW1pdGVk" +
                    "MTowOAYDVQQDEzFFbnRydXN0Lm5ldCBTZWN1cmUgU2VydmVyIENlcnRpZmljYXRp" +
                    "b24gQXV0aG9yaXR5MQ0wCwYDVQQDEwRDUkwxMCmgJ6AlhiNodHRwOi8vd3d3LmVu" +
                    "dHJ1c3QubmV0L0NSTC9uZXQxLmNybDArBgNVHRAEJDAigA8xOTk5MDUyNTE2MDk0" +
                    "MFqBDzIwMTkwNTI1MTYwOTQwWjALBgNVHQ8EBAMCAQYwHwYDVR0jBBgwFoAU8Bdi" +
                    "E1U9s/8KAGv7UISX8+1i0BowHQYDVR0OBBYEFPAXYhNVPbP/CgBr+1CEl/PtYtAa" +
                    "MAwGA1UdEwQFMAMBAf8wGQYJKoZIhvZ9B0EABAwwChsEVjQuMAMCBJAwDQYJKoZI" +
                    "hvcNAQEFBQADgYEAkNwwAvpkdMKnCqV8IY00F6j7Rw7/JXyNEwr75Ji174z4xRAN" +
                    "95K+8cPV1ZVqBLssziY2ZcgxxufuP+NXdYR6Ee9GTxj005i7qIcyunL2POI9n9cd" +
                    "2cNgQ4xYDiKWL2KjLB+6rQXvqzJ4h6BUcxm1XAX5Uj5tLUUL9wqT6u0G+bI="

    };

    private SunCryptoSupport sunCryptoSupport;
    private URL url;
    private Map<String, String> params;
    private URLConnectionFactory urlConnectionFactory;

    public DefaultHttpsClient(SunCryptoSupport sunCryptoSupport, URL url, Map<String, String> params) {
        this.sunCryptoSupport = sunCryptoSupport;
        this.url = url;
        this.params = params;
        this.urlConnectionFactory = new DefaultURLConnectionFactory();
    }

    @Override
    public void setUrlConnectionFactory(URLConnectionFactory urlConnectionFactory) {
        this.urlConnectionFactory = urlConnectionFactory;
    }

    @Override
    public SSLResponse post() {
        String USER_AGENT = "OpenSign v1.x.y (http://www.openoces.org)";


        if (!"https".equals(url.getProtocol())) {
            FileLog.warn("Url does not indicate the use of https. Indicate protocol: " + url.getProtocol());
            return null;
        }
        SSLSocketFactory originalSSLSocketFactory = null;
        OutputStream os = null;
        PrintStream ps = null;
        try {
            SSLContext context = SSLContext.getInstance("TLS");
            context.init(null, //No Key manager needed
                    new TrustManager[]{new SunTrustManager()},
                    new java.security.SecureRandom());
            originalSSLSocketFactory = HttpsURLConnection.getDefaultSSLSocketFactory();
            HttpsURLConnection.setDefaultSSLSocketFactory(context.getSocketFactory());
            URLConnection con = createUrlConnection(url);
            con.setRequestProperty("User-Agent", USER_AGENT);
            con.setDoOutput(true);
            con.setDoInput(true);
            os = con.getOutputStream();
            if (os == null) {
                FileLog.warn("Did not get an output stream");
            }
            ps = new PrintStream(os);
            ps.write(sunCryptoSupport.generateContents(params));
            ps.flush();

            /* read response */
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String line;
            StringBuffer response = new StringBuffer();
            while ((line = br.readLine()) != null) {
                response.append(line).append("\n");
            }
            return new SSLResponse(response);

        } catch (IOException e) {
            FileLog.warn("An IO exception occurred", e);
            return null;
        } catch (NoSuchAlgorithmException e) {
            FileLog.warn("NoSuchAlgorithmn exception occurred", e);
            return null;
        } catch (KeyManagementException e) {
            FileLog.warn("KeyManagement exception occurred", e);
            return null;
        } finally {
            IOUtils.close(os);
            IOUtils.close(ps);
            // reset to the original value
            if (originalSSLSocketFactory != null) {
                HttpsURLConnection.setDefaultSSLSocketFactory(originalSSLSocketFactory);
            }
        }
    }

    protected URLConnection createUrlConnection(URL url) throws IOException {
        return urlConnectionFactory.create(url);
    }

    class SunTrustManager implements X509TrustManager {

        private Certificate[] trustedRoots = null;

        public SunTrustManager() {
            trustedRoots = new Certificate[trustedRootCertificates.length];
            for (int i = 0; i < trustedRoots.length; i++) {
                byte[] decoded = Base64.decode(trustedRootCertificates[i].getBytes());
                try {
                    CertificateFactory cf = CertificateFactory.getInstance("X.509");
                    InputStream bis = new ByteArrayInputStream(decoded);
                    trustedRoots[i] = cf.generateCertificate(bis);
                } catch (CertificateException e) {
                    FileLog.error("CertificateException in SunTrustManager: " + e.getMessage());
                }
            }
        }

        public boolean isClientTrusted(X509Certificate[] cert) {
            return true;
        }

        public boolean isServerTrusted(X509Certificate[] certChain) {
            boolean trusted = false;

            //Check chain consistency
            int verifyCert = 0;
            if (certChain.length > 1) {
                for (int i = 1; i < certChain.length; i++) {
                    try {
                        certChain[i - 1].verify(certChain[i].getPublicKey());
                    } catch (Exception e) {
                        return false;
                    }
                }
                verifyCert = certChain.length - 1;
                if (certChain[verifyCert].getIssuerDN().equals(certChain[verifyCert].getSubjectDN())) {
                    verifyCert--;
                }
            }


            for (Certificate trustedRoot : trustedRoots) {
                try {
                    certChain[verifyCert].verify(trustedRoot.getPublicKey());
                    trusted = true;
                    break;
                } catch (Exception e) {
                    //Not signed by trustedRoot
                }
            }
            return trusted;

        }

        public X509Certificate[] getAcceptedIssuers() {
            X509Certificate[] trusted = new X509Certificate[trustedRoots.length];
            try {
                for (int i = 0; i < trustedRoots.length; i++) {
                    Certificate trustedRoot = trustedRoots[i];
                    trusted[i] = sunCryptoSupport.getCryptoFactory().createCertificate(trustedRoot.getEncoded());
                }
                return trusted;
            } catch (CertificateEncodingException e) {
                FileLog.error(e.getMessage(), e);
            } catch (CertificateException e) {
                FileLog.error(e.getMessage(), e);
            }
            return null;
        }

        public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

        }

        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            if (authType == null || "".equals(authType)) {
                throw new IllegalArgumentException("Null or empty authType string supplied");
            }
            if (chain == null || chain.length == 0) {
                throw new IllegalArgumentException("Empty certChain supplied");
            }
            boolean isTrusted = isServerTrusted(chain);
            if (!isTrusted) {
                throw new CertificateException("Certificate chain is not trusted");
            }
        }
    }


}
