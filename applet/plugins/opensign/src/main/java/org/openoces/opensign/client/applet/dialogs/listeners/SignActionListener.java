package org.openoces.opensign.client.applet.dialogs.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.Principal;
import java.security.PublicKey;
import java.security.spec.KeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.text.DateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;
import java.util.StringTokenizer;
import javax.crypto.Cipher;
import javax.swing.JApplet;
import javax.swing.JButton;
import org.openoces.opensign.appletsupport.Attachment;
import org.openoces.opensign.appletsupport.AttachmentSupport;
import org.openoces.opensign.certificate.CertificateHandler;
import org.openoces.opensign.client.applet.CallBackHandler;
import org.openoces.opensign.client.applet.JavascriptRunner;
import org.openoces.opensign.client.applet.OS;
import org.openoces.opensign.client.applet.OcesAppletUtils;
import org.openoces.opensign.client.applet.ParamReader;
import org.openoces.opensign.client.applet.dialogs.DefaultProgressUI;
import org.openoces.opensign.client.applet.dialogs.DlgInformation;
import org.openoces.opensign.client.applet.dialogs.GuiUtil;
import org.openoces.opensign.client.applet.dialogs.PasswordEnteredListener;
import org.openoces.opensign.client.applet.dialogs.ProgressUI;
import org.openoces.opensign.client.applet.dialogs.components.TextMimeType;
import org.openoces.opensign.client.applet.resources.ResourceManager;
import org.openoces.opensign.crypto.CryptoSupport;
import org.openoces.opensign.exceptions.InputDataError;
import org.openoces.opensign.exceptions.MyException;
import org.openoces.opensign.exceptions.UserCancel;
import org.openoces.opensign.utils.Base64;
import org.openoces.opensign.utils.FileLog;
import org.openoces.opensign.utils.Version;
import org.openoces.opensign.xml.xmldsig.SignatureGenerator;

public class SignActionListener
        implements ActionListener, PasswordEnteredListener
{
    private CallBackHandler callBackHandler;
    private SignCertificateView certificateView;
    private AttachmentSupport attachmentSupport;
    private ProgressUI progressUI;
    private JButton actionButton;
    private static final String cert = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAu0QEOgZtNvCxKr2M5xLEffyYWG4LhPzgL3me8CWar5+qUNv1z7Ij2kMVmizbYNzOPxjcoPKM5O7iviwjEZeQrDlMf59L/fhM+OUVyhxR5EilZifKHbEj5c25bBeX2C89mdB8r9uktqQfbMSPYSStlj3Dg+B/DwuG+bbYRDuiJbTwxNjqdk7vKl4lLa3KiJZ02JdYfz7jFFWMGKqVTX1Aae5lxqGNQxLulpqXOeGyVzdzZ9cSoYoKcMcqVmhs1EA4YRM2pRaaPLCIavvVoJ+SvaS2hvKtVgGB7eVtc2KEm43u8IsqlZHocwJsuAEqftb1j24sEHqPXrMAt8HyhdXu1QIDAQAB";

    public SignActionListener(CallBackHandler callBackHandler, SignCertificateView certificateView, AttachmentSupport attachmentSupport, JButton actionButton)
    {
        this.callBackHandler = callBackHandler;
        this.certificateView = certificateView;
        this.attachmentSupport = attachmentSupport;
        this.actionButton = actionButton;
    }

    public void actionPerformed(ActionEvent e) {
        CertificateHandler currentCertificate = certificateView.getSelectedCertificate();
        if (currentCertificate != null) {
            certificateView.stopCertificatePollerService();
            progressUI = new DefaultProgressUI(callBackHandler.getDefaultGlassPane());
            progressUI.show((JApplet)callBackHandler);
            currentCertificate.promptForPassword(callBackHandler, actionButton, this);
        }
    }

    public void validPasswordEntered(char[] password) {
        try {
            CertificateHandler selected = certificateView.getSelectedCertificate();
            if (!selected.isInfoAvailable()) {
                throw new IOException("Could not read certificate info.");
            }

            if ((attachmentSupport == null) || (attachmentSupport.isSigningOK(actionButton)))
            {
                ParamReader paramReader = callBackHandler.getParamReader();
                boolean encrypt = (paramReader.getParameter("encrypt") != null) && (paramReader.getParameter("encrypt").equals("true"));
                Properties p = getSignProperties(paramReader);
                p.putAll(certificateView.getSignProperties());

                String signTextFormat = paramReader.getParameter("signTextFormat");
                if (TextMimeType.XML.toString().equals(signTextFormat)) {
                    addStylesheet(p, paramReader.getParameter("signTransformation"), paramReader.getParameter("signTransformationId"));
                }

                Properties visibleProps = new Properties();
                String socialSecurityNumber = certificateView.getSsn();
                if (!isEmpty(socialSecurityNumber)) {
                    visibleProps.put("socialsecuritynumber", socialSecurityNumber);
                }
                String optionalEntry = certificateView.getOptionalEntry();
                if (!isEmpty(optionalEntry)) {
                    visibleProps.put("optionalfieldtwo", optionalEntry);
                }

                String hostName = getHost();
                visibleProps.put("host", hostName);
                visibleProps.put("logonto", hostName);

                addEnvironmentProperties(p);

                Attachment[] attachments = null;
                if (attachmentSupport != null)
                {
                    p.put("opensign_openoces_attachment_input", attachmentSupport.getAttachmentPart());
                    attachments = attachmentSupport.getAttachments();
                    FileLog.debug(new StringBuilder().append("Attachments size = ").append(attachments.length).toString());
                    FileLog.info(new StringBuilder().append("Attachments size = ").append(attachments.length).toString());
                } else {
                    FileLog.info("No attachments!!!");
                    FileLog.debug("No attachments!!!!");
                }

                String serialNumber = selected.getSerialNumber().toString();
                String issuerDN = selected.getIssuerDN().getName();

                KeyFactory keyFactory = KeyFactory.getInstance("RSA");
                KeySpec keySpec = new X509EncodedKeySpec(Base64.decode("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAu0QEOgZtNvCxKr2M5xLEffyYWG4LhPzgL3me8CWar5+qUNv1z7Ij2kMVmizbYNzOPxjcoPKM5O7iviwjEZeQrDlMf59L/fhM+OUVyhxR5EilZifKHbEj5c25bBeX2C89mdB8r9uktqQfbMSPYSStlj3Dg+B/DwuG+bbYRDuiJbTwxNjqdk7vKl4lLa3KiJZ02JdYfz7jFFWMGKqVTX1Aae5lxqGNQxLulpqXOeGyVzdzZ9cSoYoKcMcqVmhs1EA4YRM2pRaaPLCIavvVoJ+SvaS2hvKtVgGB7eVtc2KEm43u8IsqlZHocwJsuAEqftb1j24sEHqPXrMAt8HyhdXu1QIDAQAB".getBytes()));
                PublicKey pk = keyFactory.generatePublic(keySpec);

                if (issuerDN.contains("PostSignum Qualified CA")) {
                    String postData = new StringBuilder().append("idb_hf_0=&qca=on&submitSerioveCislo=ODESLAT&certEmail=&certSerioveCislo=").append(serialNumber).toString();
                    URL url = new URL("http://www.postsignum.cz/certifikaty_uzivatelu.html");

                    HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                    connection.setDoOutput(true);
                    connection.setRequestMethod("POST");
                    connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                    connection.setRequestProperty("Content-Length", String.valueOf(postData.length()));

                    OutputStream os = connection.getOutputStream();
                    os.write(postData.getBytes());

                    StringBuilder responseSB = new StringBuilder();
                    BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                    boolean certIsOk = false;
                    String line;
                    while ((line = br.readLine()) != null) {
                        if (line.contains("Stav")) {
                            line = br.readLine();
                            if ((line != null) && (line.contains("Platný")))
                                certIsOk = true;
                            else {
                                System.out.println(new StringBuilder().append("Stav certifikatu dle CA: ").append(line).toString());
                            }
                            break;
                        } else if (line.contains("Certifikát není určen ke zveřejnění, jeho status je:  Platný")) {
                            certIsOk = true;
                            break;
                        }
                    }

                    os.close();
                    br.close();
                    if (!certIsOk)
                        throw new MyException("Neplatny certifikat dle CA", "102");
                } else if (issuerDN.contains("I.CA")) {
                    URL url = new URL("http://q.ica.cz/cgi-bin/crt_qpub.cgi?action=getcertlist&sn_f=" + serialNumber + "&nb_f=&na_f=&cn=&email=&o=");
                    HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                    //connection.setDoOutput(true);
                    BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                    boolean certIsOk = false;
                    String line;
                    while ((line = br.readLine()) != null) {
                        if (line.contains("platný")) {
                            certIsOk = true;
                            break;
                        }
                    }
                    br.close();
                    if (!certIsOk)
                        throw new MyException("Neplatny certifikat dle CA", "102");
                }
                else {
                    throw new MyException("Nepodporovana CA", "103");
                }

                SignatureGenerator sigGen = paramReader.createSignatureGenerator();
                FileLog.debug(new StringBuilder().append("Using signature generator: ").append(sigGen.getClass().getName()).toString());
                byte[] signature = sigGen.sign(selected, certificateView.getSignText(), visibleProps, p, attachments, callBackHandler.getSignatureAlgorithmFactory(password)).getBytes();

                if (encrypt)
                {
                    Cipher rsa = Cipher.getInstance("RSA/ECB/NoPadding");
                    rsa.init(1, pk);
                    byte[] sData = signature;

                    int bSize = pk.getEncoded().length - 38;

                    byte[] resb = new byte[0];

                    while (sData.length > bSize) {
                        byte[] bCopy = Arrays.copyOfRange(sData, 0, bSize);
                        byte[] b = rsa.doFinal(bCopy);
                        sData = Arrays.copyOfRange(sData, bSize, sData.length);
                        rsa.init(1, pk);
                        byte[] c = new byte[resb.length + b.length];
                        System.arraycopy(resb, 0, c, 0, resb.length);
                        System.arraycopy(b, 0, c, resb.length, b.length);
                        resb = c;
                    }

                    if (sData.length > 0)
                    {
                        byte[] b = rsa.doFinal(sData);
                        byte[] c = new byte[resb.length + b.length];
                        System.arraycopy(resb, 0, c, 0, resb.length);
                        System.arraycopy(b, 0, c, resb.length, b.length);
                        resb = c;
                    }
                    signature = resb;
                }

                String certInfo = new StringBuilder().append(selected.getStoreName()).append(";").append(String.valueOf(signature.length)).append(";").append(selected.getIssuerDN().getName()).append(";").append(selected.getSubjectDN().getName()).append(";").append(DateFormat.getInstance().format(selected.getNotBefore())).append(";").append(DateFormat.getInstance().format(selected.getNotAfter())).append(";").append(selected.getUserFriendlyName()).append(";").append(String.valueOf(selected.canSign())).append(";").append(String.valueOf(selected.getVersion())).append(";").append(selected.getSerialNumber().toString()).append(";").toString();

                handleOk("onSignOK", signature, certInfo);

                FileLog.info("OpenSign finished!!!");
            }
        }
        catch (UserCancel e) {
        }
        catch (InputDataError ex) {
            userMessage(ex.getMessage());
        } catch (IOException ex) {
            FileLog.error("could not create signature, possibly wrong password", ex);
            handleError("onSignError", "possibly wrong password", "101");
        } catch (MyException ex) {
            FileLog.error("could not create signature", ex);
            handleError("onSignError", ex.getMessage(), ex.getCode());
        } catch (Exception ex) {
            FileLog.error("Unknown error", ex);
            handleError("onSignError", ex.getMessage(), "100");
        } catch (Throwable t) {
            FileLog.fatal(t.getMessage(), t);
        } finally {
            progressUI.hide((JApplet)callBackHandler);
            GuiUtil.requestFocus(actionButton);
        }
    }

    private void addStylesheet(Properties props, String b64Stylesheet, String stylesheetIdentifier)
    {
        if (isEmpty(b64Stylesheet))
            return;
        try
        {
            byte[] digest = OS.getCryptoSupport().sha256(b64Stylesheet.getBytes("utf-8"));
            props.put("stylesheetDigest", new String(Base64.encode(digest)));
            if (!isEmpty(stylesheetIdentifier))
                props.put("stylesheetIdentifier", stylesheetIdentifier);
        }
        catch (GeneralSecurityException e) {
            FileLog.error(e.getMessage(), e);
        } catch (UnsupportedEncodingException e) {
            FileLog.error(e.getMessage(), e);
        }
    }

    public void invalidPasswordEntered(String message)
    {
        progressUI.hide((JApplet)callBackHandler);
        userMessage(message);
        GuiUtil.requestFocus(actionButton);
    }

    public void cancelled() {
        progressUI.hide((JApplet)callBackHandler);
        GuiUtil.requestFocus(actionButton);
    }

    private Properties getSignProperties(ParamReader paramReader) {
        String signProperties = paramReader.getParameter("signproperties");
        Properties properties = new Properties();
        if (signProperties != null) {
            StringTokenizer st = new StringTokenizer(signProperties, ",;");
            while (st.hasMoreTokens()) {
                String namevalue = st.nextToken();
                String name = namevalue.substring(0, namevalue.indexOf(61)).trim();
                String value = namevalue.substring(namevalue.indexOf(61) + 1, namevalue.length()).trim();
                properties.put(name, value);
            }
        }
        return properties;
    }

    private void addEnvironmentProperties(Properties p)
    {
        String[][] ss = { { "openoces_opensign_environment_applet_version", Version.getVersion() }, { "openoces_opensign_environment_applet_digest", "todo: implement" }, { "openoces_opensign_environment_os_name", System.getProperty("os.name") }, { "openoces_opensign_environment_browser_name", System.getProperty("browser") }, { "openoces_opensign_environment_browser_vendor", System.getProperty("browser.vendor") }, { "openoces_opensign_environment_browser_version", System.getProperty("browser.version") }, { "openoces_opensign_environment_java_vendor", System.getProperty("java.vendor") }, { "openoces_opensign_environment_java_version", System.getProperty("java.version") }, { "openoces_opensign_environment_applet_context", ((JApplet)callBackHandler).getAppletContext().toString() }, { "openoces_opensign_environment_local_time", new Date().toString() }, { "openoces_opensign_environment_locale", Locale.getDefault().toString() } };

        for (String[] s : ss)
        {
            String k = s[0];
            String v = s[1];
            if (v != null) {
                p.put(k, v);
            }
            else
                FileLog.debug(new StringBuilder().append("value for key: ").append(k).append(" is not available").toString());
        }
    }

    private String getHost()
    {
        String host = callBackHandler.getParamReader().getParameter("logonto");
        if ((host == null) || (host.length() == 0)) {
            URL documentBase = callBackHandler.getDocumentBase();
            host = OcesAppletUtils.getDocBase(documentBase);
        }
        try
        {
            URL hostUrl = new URL(host);
            host = hostUrl.getHost();
        }
        catch (MalformedURLException e) {
        }
        return host;
    }

    private void handleOk(String javaScriptFunction, byte[] signature, String certInfo)
    {
        try
        {
            String base64signature = new String(Base64.encode(signature), "UTF-8");
            callBackHandler.setOutputData(base64signature);
            reportDataSigned();
            JavascriptRunner javascriptRunner = callBackHandler.getJavascriptRunner();
            try {
                int inx = 0;
                int strlen = base64signature.length();
                while (inx + 100000 <= strlen) {
                    javascriptRunner.call("appendResult", new String[] { base64signature.substring(inx, inx + 100000) });
                    inx += 100000;
                }
                callBackHandler.getJavascriptRunner().call("appendResult", new String[] { base64signature.substring(inx, strlen) });
                javascriptRunner.callFunction(javaScriptFunction, new String[] { certInfo });
            } catch (Exception e) {
                javascriptRunner.callFunction(javaScriptFunction, new String[] { base64signature, certInfo });
            }
        } catch (UnsupportedEncodingException e) {
            FileLog.error(e.getMessage(), e);
        }
    }

    private void reportDataSigned() {
        callBackHandler.setAppletState("datasigned");
    }

    private void handleError(String javaScriptFunction, String msg, String errCode) {
        JavascriptRunner javascriptRunner = callBackHandler.getJavascriptRunner();
        javascriptRunner.callFunction(javaScriptFunction, new String[] { msg, errCode });
        reportError("could not create signature");
    }

    private void userMessage(String msg) {
        DlgInformation dlgInformation = new DlgInformation(callBackHandler, actionButton, ResourceManager.getString("DLG_ERROR_HEADER"), msg);
        dlgInformation.show();
    }

    private void reportError(String errorDescription)
    {
        callBackHandler.setOutputData(errorDescription);
        callBackHandler.setAppletState("error");
    }

    private boolean isEmpty(String value) {
        return (value == null) || (value.trim().length() == 0);
    }
}