package org.openoces.opensign.util.test;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Paw Figgé Kjeldgaard (pfk@miracleas.dk)
 */
public class CertificateFileReader {

    private static final String BEGIN_CERTIFICATE = "-----BEGIN CERTIFICATE-----";
    private static final String END_CERTIFICATE = "-----END CERTIFICATE-----";


    public static List<X509Certificate> parseCertificates(String filePath) throws CertificateException, IOException {
        List<X509Certificate> certificates = new ArrayList<X509Certificate>();

        CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");

        String fileAsString = readFileAsString(filePath);

        while (fileAsString.contains("-----BEGIN CERTIFICATE-----\n")) {

            int startOfCertificateAndTheRest = fileAsString.indexOf(BEGIN_CERTIFICATE);
            int endOfCertificate = fileAsString.indexOf(END_CERTIFICATE, startOfCertificateAndTheRest) + END_CERTIFICATE.length();


            String certificateAsString = fileAsString.substring(startOfCertificateAndTheRest, endOfCertificate);

            X509Certificate certificate = (X509Certificate) certificateFactory.generateCertificate(new ByteArrayInputStream(certificateAsString.getBytes()));

            certificates.add(certificate);

            fileAsString = fileAsString.substring(endOfCertificate);
        }

        return certificates;
    }


    public static String getPkcs12FromOcesFile(String filePath) throws IOException {
        String pkcs12 = null;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(CertificateFileReader.class.getResourceAsStream(filePath)));

            for (; ; ) {
                String line = reader.readLine();
                if (line == null)
                    break;
                if ((line.contains("pkcs12 = "))) {
                    int lastQuote = line.lastIndexOf('\"');
                    int secondLastQuote = line.lastIndexOf("\"", lastQuote - 1);
                    pkcs12 = line.substring(secondLastQuote + 1, lastQuote);
                    break;
                }
            }
        } finally {
            if (reader != null) reader.close();
        }

        return pkcs12;
    }

    private static String readFileAsString(String filePath) throws java.io.IOException {
        return FileReader.readFile(filePath);
    }

}
