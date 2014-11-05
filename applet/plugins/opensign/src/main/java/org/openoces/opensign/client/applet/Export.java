package org.openoces.opensign.client.applet;

import org.openoces.opensign.client.applet.dialogs.ExportScreen;
import org.openoces.opensign.client.applet.resources.ResourceManager;
import org.openoces.opensign.utils.FileLog;
import org.openoces.opensign.utils.Version;
import org.openoces.opensign.xml.nanoxml.XMLException;

public class Export extends AbstractApplet {

    public Export() {
        super(ExportScreen.class);
        this.setName(ResourceManager.getString("TITLE_LOGON") + " v." + Version.getVersion());
    }

    public String getAppletInfo() {
        return "OpenExport";
    }

    protected ParamReader createParamReader() {
        try {
            String explicitInputStyle = getParameter("inputstyle");
            String virkLogon = getParameter("VIRK_LOGON");

            if (explicitInputStyle != null) {
                if ("default".equals(explicitInputStyle)) {
                    return new DefaultParamReader(this);
                } else if ("virk".equals(explicitInputStyle)) {
                    return new AppletArguments(this);
                } else {
                    return new DefaultParamReader(this);
                }
            } else {
                if (virkLogon != null) {
                    return new AppletArguments(this);// could be named XmlParamReader..
                } else {
                    return new DefaultParamReader(this);
                }
            }
        } catch (XMLException e) {
            FileLog.error("Could not create applet arguments", e);
            return new DefaultParamReader(this);
        }
    }

    @Override
    public void setOutputData(String outputData) {
    }

    @Override
    public void setAppletState(String state) {
    }
}
