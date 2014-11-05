package org.openoces.opensign.client.applet.dialogs.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import org.openoces.opensign.appletsupport.AttachmentSupport;
import org.openoces.opensign.certificate.CertificateHandler;
import org.openoces.opensign.client.applet.CallBackHandler;
import org.openoces.opensign.client.applet.GuiCallback;
import org.openoces.opensign.client.applet.ParamReader;
import org.openoces.opensign.client.applet.dialogs.AttachmentSupportFactory;
import org.openoces.opensign.client.applet.dialogs.DefaultAttachmentSupportFactory;
import org.openoces.opensign.client.applet.dialogs.GuiUtil;
import org.openoces.opensign.client.applet.dialogs.components.ComponentFactory;
import org.openoces.opensign.client.applet.dialogs.components.SignTextDisplay;
import org.openoces.opensign.client.applet.dialogs.components.SigntextValidationException;
import org.openoces.opensign.client.applet.dialogs.components.TextMimeType;
import org.openoces.opensign.client.applet.dialogs.listeners.CancelLogonActionListener;
import org.openoces.opensign.client.applet.dialogs.listeners.PrintActionListener;
import org.openoces.opensign.client.applet.dialogs.listeners.SignActionListener;
import org.openoces.opensign.client.applet.dialogs.listeners.SignCertificateView;
import org.openoces.opensign.client.applet.dialogs.listeners.SignText;
import org.openoces.opensign.client.applet.dialogs.listeners.StringSignText;
import org.openoces.opensign.client.applet.resources.ResourceManager;
import org.openoces.opensign.utils.Base64;
import org.openoces.opensign.utils.FileLog;

public class SignPanel extends AbstractCertificateViewPanel
        implements SignCertificateView
{
    private JLabel titleLabel;
    private SignTextDisplay taSignText;
    private JLabel listLabel;
    private AttachmentSupport attachmentSupport;
    private List<JButton> actionButtons;
    private String signText;
    private JButton printButton;

    public SignPanel(CallBackHandler callbackHandler, List<CertificateHandler> certificates, boolean supportBrowsingForCertificate)
    {
        super(callbackHandler, certificates, supportBrowsingForCertificate, new CertificateFilter[] { new DefaultCertificateFilter() });

        titleLabel = componentFactory.createHeaderLabel(ResourceManager.getString("HEADER_SIGNTEXT"));
        listLabel = componentFactory.createLabel("CERTIFICATE_LIST");

        JButton viewAttachmentButton = componentFactory.createNormalButton("ATTACHMENT_LIST", false);
        AttachmentSupportFactory attachmentSupportFactory = new DefaultAttachmentSupportFactory();
        attachmentSupport = attachmentSupportFactory.createAttachmentSupport(callbackHandler, viewAttachmentButton);

        ParamReader paramReader = callbackHandler.getParamReader();

        actionButtons = new ArrayList();

        actionButtons.add(bOk);
        actionButtons.add(bDetails);

        if (attachmentSupport != null) {
            viewAttachmentButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    SignPanel.this.showAttachments();
                }
            });
            actionButtons.add(viewAttachmentButton);
        }

        actionButtons.add(bCancel);

        signText = paramReader.getSigntext().toString();
        TextMimeType signTextFormat = getTextMimeType(paramReader);
        String b64stylesheet = paramReader.getParameter("signTransformation");
        String stylesheet = "";
        if (b64stylesheet != null) {
            byte[] ba = Base64.decode(b64stylesheet.getBytes());
            try {
                stylesheet = new String(ba, "UTF8");
            } catch (UnsupportedEncodingException e) {
                FileLog.error("Invalid stylesheet", e);
                throw new RuntimeException(e);
            }
        }
        try {
            taSignText = componentFactory.createSignTextDisplay(signTextFormat, signText, stylesheet);
            taSignText.setFocusTraversalKeysEnabled(false);
            taSignText.addFocusListener(createFocusListener(taSignText));
        } catch (SigntextValidationException e) {
            FileLog.error("Invalid sign text", e);
            throw new RuntimeException(e);
        }

        bCancel.addActionListener(new CancelLogonActionListener("Sign", callbackHandler));

        bOk.addActionListener(new SignActionListener(callbackHandler, this, attachmentSupport, bOk));

        printButton = componentFactory.createLinkImageButton("PRINT_LABEL", "PRINT_TOOLTIP", "print_normal.png");
        printButton.setRolloverEnabled(true);
        printButton.setRolloverIcon(new ImageIcon(GuiUtil.createImage("print_hover.png")));
        printButton.addActionListener(new PrintActionListener(taSignText));
    }

    private TextMimeType getTextMimeType(ParamReader paramReader) {
        String signTextFormat = paramReader.getParameter("signTextFormat");
        return signTextFormat != null ? TextMimeType.valueOf(signTextFormat) : TextMimeType.PLAIN;
    }

    protected String getOkButtonTextId()
    {
        return "OK_SIGN_MODERN";
    }

    protected String getBrowseButtonTextId()
    {
        return "FUNCTIONS_MODERN_LOGON";
    }

    protected JComponent[] getActionButtons()
    {
        return (JComponent[])actionButtons.toArray(new JButton[actionButtons.size()]);
    }

    protected JComponent getContentPanel()
    {
        JPanel signPanel = componentFactory.createPanel(new BorderLayout());
        signPanel.add(titleLabel, "North");

        JScrollPane sp = componentFactory.createScrollPane(taSignText);
        sp.setName("SignScrollPane");
        sp.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                GuiUtil.doInGui(new GuiCallback() {
                    public void doInGui() {
                        taSignText.grabFocus();
                        taSignText.setCaretPosition(0);
                    }
                });
            }
        });
        signPanel.add(sp, "Center");

        JPanel printPanel = componentFactory.createPanel(new FlowLayout(2, 0, 0));
        printPanel.add(printButton);

        JPanel panel = componentFactory.createPanel(new GridBagLayout());

        int y = 0;
        y = GuiUtil.addSingleLine(panel, 0, y, printPanel);
        y = GuiUtil.addSingleLine(panel, 0, y, listLabel);
        y = GuiUtil.addComboBoxLine(panel, 0, y, chCertList, bFunctions);

        if ((lSocialSecurityNumber != null) && (tfSocialSecurityNumber != null)) {
            y = GuiUtil.addSingleLine(panel, 0, y, lSocialSecurityNumber);
            y = GuiUtil.addTextFieldLine(panel, 0, y, tfSocialSecurityNumber);
        }
        if ((lOptionalEntry != null) && (tfOptionalEntry != null)) {
            y = GuiUtil.addSingleLine(panel, 0, y, lOptionalEntry);
            y = GuiUtil.addTextFieldLine(panel, 0, y, tfOptionalEntry);
        }
        GuiUtil.addFillerLine(panel, y);

        signPanel.add(panel, "South");

        return signPanel;
    }

    public JComponent getFocusComponent()
    {
        return bOk;
    }

    private void showAttachments() {
        attachmentSupport.show(callbackHandler);
    }

    public Properties getSignProperties()
    {
        Properties properties = new Properties();
        try {
            properties.put("openoces_opensign_layout_signtext_fontname", taSignText.getFont().getFontName());
        } catch (NoSuchMethodError e) {
            FileLog.debug("Applet signtext font name not available");
        }
        properties.put("openoces_opensign_layout_color_background", getBackground().getRed() + "," + getBackground().getGreen() + "," + getBackground().getBlue());
        try {
            properties.put("openoces_opensign_layout_size_width", "" + getWidth());
            properties.put("openoces_opensign_layout_size_height", "" + getHeight());
        } catch (NoSuchMethodError e) {
            FileLog.debug("Applet dimensions not available");
        }
        try {
            properties.put("openoces_opensign_layout_signtext_fontsize", "" + taSignText.getFont().getSize());
        } catch (NoSuchMethodError e) {
            FileLog.debug("Applet signtext font size not available");
        }
        return properties;
    }

    public SignText getSignText() {
        return new StringSignText(signText);
    }

    private FocusListener createFocusListener(final JComponent signTextComponent) {
        return new FocusListener() {
            private final KeyListener listener = new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    switch (e.getKeyCode()) {
                        case 9:
                            GuiUtil.requestFocus(printButton);
                    }
                }
            };

            public void focusGained(FocusEvent e)
            {
                GuiUtil.doInGui(new GuiCallback() {
                    public void doInGui() {
                        signTextComponent.addKeyListener(listener);
                    }
                });
            }

            public void focusLost(FocusEvent e) {
                GuiUtil.doInGui(new GuiCallback() {
                    public void doInGui() {
                        signTextComponent.removeKeyListener(listener);
                    }
                });
            }
        };
    }
}