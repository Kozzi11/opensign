package org.openoces.opensign.client.applet.dialogs.panel;

import java.awt.Container;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.Principal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.MutableComboBoxModel;
import org.openoces.opensign.certificate.CertificateHandler;
import org.openoces.opensign.certificate.NotExportableException;
import org.openoces.opensign.certificate.x509.KeyUsage;
import org.openoces.opensign.client.applet.CallBackHandler;
import org.openoces.opensign.client.applet.CertificatePollerService;
import org.openoces.opensign.client.applet.CertificateView;
import org.openoces.opensign.client.applet.GuiCallback;
import org.openoces.opensign.client.applet.ParamReader;
import org.openoces.opensign.client.applet.dialogs.GuiUtil;
import org.openoces.opensign.client.applet.dialogs.PasswordEnteredListener;
import org.openoces.opensign.client.applet.dialogs.components.ComboBoxRenderer;
import org.openoces.opensign.client.applet.dialogs.components.ComponentFactory;
import org.openoces.opensign.client.applet.dialogs.listeners.BrowseForCertificateActionListener;
import org.openoces.opensign.client.applet.dialogs.listeners.CertificateDetailsActionListener;
import org.openoces.opensign.client.applet.resources.ResourceManager;
import org.openoces.opensign.crypto.SignatureAlgorithmFactory;
import org.openoces.opensign.crypto.SignatureAlgorithmHandler;
import org.openoces.opensign.utils.Base64;
import org.openoces.opensign.utils.FileLog;

public abstract class AbstractCertificateViewPanel extends AbstractActionsPanel
        implements CertificateView
{
    private static String[] validOptionalIds = { "CUSTOMERID", "USERID" };
    protected MutableComboBoxModel chCertListModel;
    protected MutableComboBoxModel chCertListModel2;
    protected JComboBox chCertList;
    protected JComboBox chCertList2;
    protected JButton bOk;
    protected JButton bCancel;
    protected JButton bFunctions;
    protected JButton bFunctions2;
    protected JButton bDetails;
    protected JLabel lSocialSecurityNumber;
    protected JTextField tfSocialSecurityNumber;
    protected JLabel lOptionalEntry;
    protected JTextField tfOptionalEntry;
    private final CertificatePollerService certificatePollerService;
    private Thread certificatePollerThread;
    private static final CertificateHandler EMPTY_CERTIFICATE = new EmptyCertificateHandler(ResourceManager.getString("NO_CERTIFICATES"));
    private List<CertificateFilter> certificateFilters;

    protected AbstractCertificateViewPanel(CallBackHandler callbackHandler, List<CertificateHandler> certificates, boolean supportBrowsingForCertificate, CertificateFilter... certificateFilters)
    {
        super(callbackHandler);

        this.certificateFilters = Arrays.asList(certificateFilters);

        chCertListModel = new DefaultComboBoxModel();

        chCertList = componentFactory.createComboBox(chCertListModel);

        for (CertificateHandler certificate : certificates) {
            addUniqueCertificate(certificate);
        }

        CertificateHandler preSelectedCertificate = getPreSelectedCertificate();
        if (preSelectedCertificate != null) {
            chCertList.setSelectedItem(preSelectedCertificate);
            chCertList2.setSelectedItem(preSelectedCertificate);
        }
        else if (chCertListModel.getSize() > 0) {
            chCertList.setSelectedIndex(0);
        }

        Container appletContainer = callbackHandler.getContentPane();
        ComboBoxRenderer renderer = new ComboBoxRenderer();
        renderer.setPreferredSize(new Dimension(appletContainer.getWidth() - 40, 20));
        chCertList.setRenderer(renderer);

        bOk = componentFactory.createNormalButton(getOkButtonTextId(), true);
        bCancel = componentFactory.createNormalButton("CANCEL_LOGON_MODERN", false);

        bFunctions = componentFactory.createNormalButton(getBrowseButtonTextId(), false);

        bFunctions.addActionListener(new BrowseForCertificateActionListener(callbackHandler, this, bFunctions, chCertList));

        if (!supportBrowsingForCertificate) {
            bFunctions.setEnabled(false);
            bFunctions2.setEnabled(false);
        }

        bDetails = componentFactory.createNormalButton("WINDOW_MAIN_BUTTON_DETAILS", false);
        bDetails.addActionListener(new CertificateDetailsActionListener(callbackHandler, this, bDetails));

        if (chCertListModel.getSize() == 0) {
            chCertListModel.addElement(EMPTY_CERTIFICATE);

            bOk.setEnabled(false);
            bDetails.setEnabled(false);
        }

        ParamReader paramReader = callbackHandler.getParamReader();
        String socialSecurityNumber = paramReader.getParameter("socialsecuritynumber");
        if ((socialSecurityNumber != null) && (socialSecurityNumber.equalsIgnoreCase("yes"))) {
            lSocialSecurityNumber = componentFactory.createLabel("SOCIAL_SECURITY_NUMBER");
            tfSocialSecurityNumber = componentFactory.createTextField();
            tfSocialSecurityNumber.setSize(150, 20);
        }

        String optionalEntry = paramReader.getParameter("optionalid");
        if ((optionalEntry != null) && (isOptionalEntryValid(optionalEntry))) {
            lOptionalEntry = componentFactory.createLabel(optionalEntry);
            tfOptionalEntry = componentFactory.createTextField();
            tfOptionalEntry.setSize(150, 20);
        }

        certificatePollerService = new CertificatePollerService(callbackHandler, this);

        GuiUtil.addEnterKeyListener(chCertList, bOk);
    }

    protected abstract String getOkButtonTextId();

    protected abstract String getBrowseButtonTextId();

    public CertificateHandler getSelectedCertificate() {
        return (CertificateHandler)chCertList.getSelectedItem();
    }

    public final String getSsn() {
        return tfSocialSecurityNumber != null ? tfSocialSecurityNumber.getText() : null;
    }

    public final String getOptionalEntry() {
        return tfOptionalEntry != null ? tfOptionalEntry.getText() : null;
    }

    private CertificateHandler getPreSelectedCertificate() {
        try {
            ParamReader paramReader = callbackHandler.getParamReader();
            String certificateId = paramReader.getParameter("certificateId");
            if (certificateId != null) {
                byte[] ba = Base64.decode(certificateId.getBytes());
                String userFriendlyName = new String(ba, "UTF8").replace('\\', '/');
                for (int i = 0; i < chCertListModel.getSize(); i++) {
                    CertificateHandler certificate = (CertificateHandler)chCertListModel.getElementAt(i);
                    if (certificate.getUserFriendlyName().equals(userFriendlyName)) {
                        return certificate;
                    }
                }
            }
            return null;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public void addCertificate(final CertificateHandler certificate) {
        if (filter(certificate))
            GuiUtil.doInGui(new GuiCallback() {
                public void doInGui() {
                    if (AbstractCertificateViewPanel.this.addUniqueCertificate(certificate)) {
                        chCertList.setSelectedItem(certificate);
                        int index = AbstractCertificateViewPanel.this.getIndex(AbstractCertificateViewPanel.EMPTY_CERTIFICATE);
                        if (index != -1) {
                            chCertListModel.removeElementAt(index);
                            bOk.setEnabled(true);
                            bDetails.setEnabled(true);
                        }
                        Container appletContainer = callbackHandler.getContentPane();
                        appletContainer.validate();
                        appletContainer.repaint();
                    }
                }
            });
    }

    public void addCertificate(final CertificateHandler certificate, final JComboBox comboBox)
    {
        if (filter(certificate))
            GuiUtil.doInGui(new GuiCallback() {
                public void doInGui() {
                    if (AbstractCertificateViewPanel.this.addUniqueCertificate(certificate, comboBox)) {
                        comboBox.setSelectedItem(certificate);
                        int index = AbstractCertificateViewPanel.this.getIndex(AbstractCertificateViewPanel.EMPTY_CERTIFICATE, (MutableComboBoxModel)comboBox.getModel());
                        if (index != -1) {
                            ((MutableComboBoxModel)comboBox.getModel()).removeElementAt(index);
                            bOk.setEnabled(true);
                            bDetails.setEnabled(true);
                        }
                        Container appletContainer = callbackHandler.getContentPane();
                        appletContainer.validate();
                        appletContainer.repaint();
                    }
                }
            });
    }

    public void addCertificate(final File certificate, final JComboBox comboBox)
    {
        GuiUtil.doInGui(new GuiCallback() {
            public void doInGui() {
                if (AbstractCertificateViewPanel.this.addUniqueCertificate(certificate, comboBox)) {
                    comboBox.setSelectedItem(certificate);
                    int index = AbstractCertificateViewPanel.this.getIndex(AbstractCertificateViewPanel.EMPTY_CERTIFICATE, (MutableComboBoxModel)comboBox.getModel());
                    if (index != -1) {
                        ((MutableComboBoxModel)comboBox.getModel()).removeElementAt(index);
                    }

                    Container appletContainer = callbackHandler.getContentPane();
                    appletContainer.validate();
                    appletContainer.repaint();
                }
            }
        });
    }

    private boolean addUniqueCertificate(CertificateHandler certificate)
    {
        int index = getIndex(certificate);
        if (index == -1) {
            chCertListModel.addElement(certificate);
            return true;
        }
        return false;
    }

    private boolean addUniqueCertificate(CertificateHandler certificate, JComboBox comboBox)
    {
        int index = getIndex(certificate, (MutableComboBoxModel)comboBox.getModel());
        if (index == -1) {
            ((MutableComboBoxModel)comboBox.getModel()).addElement(certificate);
            return true;
        }
        return false;
    }

    private boolean addUniqueCertificate(File certificate, JComboBox comboBox)
    {
        int index = getIndex(certificate, (MutableComboBoxModel)comboBox.getModel());
        if (index == -1) {
            ((MutableComboBoxModel)comboBox.getModel()).addElement(certificate);
            return true;
        }
        return false;
    }

    private boolean filter(CertificateHandler handler) {
        for (CertificateFilter filter : certificateFilters) {
            if (filter.filter(handler)) return true;
        }
        return false;
    }

    protected void finalize() throws Throwable {
        try {
            FileLog.debug("AbstractCertificateViewPanel stopping");
            stopCertificatePollerService();
            if (chCertListModel != null) {
                chCertListModel = null;
            }
            if (chCertList != null) {
                chCertList.removeAll();
                chCertList = null;
            }
            if (chCertListModel2 != null) {
                chCertListModel2 = null;
            }
            if (chCertList2 != null) {
                chCertList2.removeAll();
                chCertList2 = null;
            }
            bOk = null;
            bCancel = null;
            bFunctions = null;
            bFunctions2 = null;
            bDetails = null;
            certificateFilters = null;
        } finally {
            super.finalize();
        }
    }

    public void stopCertificatePollerService() {
        certificatePollerService.stopProgress();
        if (certificatePollerThread != null) {
            FileLog.debug("Stopping " + certificatePollerThread.getName());
            certificatePollerThread.interrupt();
            certificatePollerThread = null;
        }
    }

    public void showPanel()
    {
        super.showPanel();
        certificatePollerThread = new Thread(certificatePollerService, "CertificatePollerThread");
        certificatePollerThread.start();
    }

    private int getIndex(CertificateHandler certificateHandler) {
        for (int i = 0; i < chCertListModel.getSize(); i++) {
            CertificateHandler certificate = (CertificateHandler)chCertListModel.getElementAt(i);
            if (certificate.equals(certificateHandler)) {
                return i;
            }
        }
        return -1;
    }

    private int getIndex(CertificateHandler certificateHandler, MutableComboBoxModel model) {
        for (int i = 0; i < model.getSize(); i++) {
            CertificateHandler certificate = (CertificateHandler)model.getElementAt(i);
            if (certificate.equals(certificateHandler)) {
                return i;
            }
        }
        return -1;
    }

    private int getIndex(File certificateHandler, MutableComboBoxModel model) {
        for (int i = 0; i < model.getSize(); i++) {
            Object certificate = model.getElementAt(i);
            if (certificate.equals(certificateHandler)) {
                return i;
            }
        }
        return -1;
    }

    private boolean isOptionalEntryValid(String optionalEntry) {
        for (String validOptionalId : validOptionalIds) {
            if (validOptionalId.equals(optionalEntry)) {
                return true;
            }
        }

        return false;
    }

    private static final class EmptyCertificateHandler implements CertificateHandler {
        private String userFrindlyName;

        private EmptyCertificateHandler(String userFrindlyName) {
            this.userFrindlyName = userFrindlyName;
        }

        public byte[] sign(byte[] toBeSigned, SignatureAlgorithmFactory signatureAlg) {
            return new byte[0];
        }

        public byte[][] getCertificateChain() throws IOException {
            return new byte[0][];
        }

        public String getStoreName() {
            return "";
        }

        public Properties getExtraCertificateProperties() {
            return null;
        }

        public byte[] digest(byte[] data, SignatureAlgorithmHandler signatureAlgorithmHandler) {
            return new byte[0];
        }

        public void promptForPassword(CallBackHandler callBackHandler, JComponent actionButton, PasswordEnteredListener listener) {
        }

        public boolean isInfoAvailable() {
            return false;
        }

        public String getUserFriendlyName() {
            return userFrindlyName;
        }

        public Principal getSubjectDN() throws IOException {
            return null;
        }

        public Principal getIssuerDN() throws IOException {
            return null;
        }

        public BigInteger getSerialNumber() throws IOException {
            return BigInteger.ZERO;
        }

        public Date getNotBefore() throws IOException {
            return new Date();
        }

        public Date getNotAfter() throws IOException {
            return new Date();
        }

        public int getVersion() throws IOException {
            return 0;
        }

        public String getKeyUsage() throws IOException {
            return "";
        }

        public boolean canSign() throws IOException {
            return false;
        }

        public KeyUsage getIntendedKeyUsage() throws IOException {
            return null;
        }

        public byte[] getCertificate() throws IOException {
            return new byte[0];
        }

        public int compareTo(Object o) {
            return 0;
        }

        public boolean equals(Object o) {
            if (this == o) return true;
            if ((o == null) || (getClass() != o.getClass())) return false;

            EmptyCertificateHandler that = (EmptyCertificateHandler)o;

            return userFrindlyName.equals(that.userFrindlyName);
        }

        public int hashCode()
        {
            return userFrindlyName.hashCode();
        }

        public String toString()
        {
            return userFrindlyName;
        }

        public boolean isExportable()
        {
            return false;
        }

        public byte[] getPkcs12() throws NotExportableException
        {
            throw new NotExportableException();
        }
    }
}