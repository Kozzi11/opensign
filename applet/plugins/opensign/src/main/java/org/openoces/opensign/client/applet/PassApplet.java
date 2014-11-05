/*
    Copyright 2006 IT Practice A/S
    Copyright 2006 TDC Totalløsninger A/S
    Copyright 2006 Jens Bo Friis
    Copyright 2006 Preben Rosendal Valeur
    Copyright 2006 Carsten Raskgaard


    This file is part of OpenSign.

    OpenSign is free software; you can redistribute it and/or modify
    it under the terms of the GNU Lesser General Public License as published by
    the Free Software Foundation; either version 2.1 of the License, or
    (at your option) any later version.

    OpenSign is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public License
    along with OpenOcesAPI; if not, write to the Free Software
    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA


    Note to developers:
    If you add code to this file, please take a minute to add an additional
    copyright statement above and an additional
    @author statement below.
*/

/* $Id: PassApplet.java,v 1.4 2012/09/27 11:03:52 pakj Exp $ */

package org.openoces.opensign.client.applet;

/**
 * This class implements a version of the applet used to extract information about a certificate
 * using liveconnect
 *
 * @author Preben Rosendal Valeur  <prv@tdc.dk>
 * @author Paw F. Kjeldgaard <pakj@danid.dk>
 */

import org.openoces.opensign.certificate.x509.KeyStoreHandler;
import org.openoces.opensign.client.applet.interfaces.PollingApplet;
import org.openoces.opensign.client.applet.interfaces.ServiceApplet;
import org.openoces.opensign.client.applet.dialogs.PassScreen;
import org.openoces.opensign.utils.FileLog;

public class PassApplet extends AbstractApplet implements ServiceApplet, PollingApplet, SetPollApplet {
	private static final String PARAM_SERVICE_PROVIDER = "SERVICE_PROVIDER";
    private static final String PARAM_SERVICE_NAME = "SERVICE_NAME";
    private static final String PARAM_SERVICE_PARAM = "SERVICE_PARAM";

    private String appletState;
    private String [] outputDataArray;
    private String [] outputDataNames;
    private int outputDataIndex;

    public PassApplet() {
        super(PassScreen.class);
    }

    public void init() {
        initState();
        paramReader = createParamReader();
        commonInit();
        super.init();
    }

    private void initState(){
        setAppletState("INIT");
        outputDataArray = null;
        outputDataIndex = 0;
    }

    public void start() {
        // just override supers
        super.start();
        doService();
    }

    protected ParamReader createParamReader() {
        //FIXME: implement me
        return new DefaultParamReader(this);
    }

    public String getAppletInfo() {
        return null;
    }

    public void doService() {
        initState();
        // get then name of the keystorehandler
        String serviceProvider = this.getParameter(PARAM_SERVICE_PROVIDER);
        String serviceName = this.getParameter(PARAM_SERVICE_NAME);
        String serviceParam = this.getParameter(PARAM_SERVICE_PARAM);
//        String serviceProvider = "KEYSTORE_CDCARD";
//        String serviceName = "IMPORT";
        // (alternative: let keystorehandler register service...)
        boolean providerFound = false;
        for (KeyStoreHandler keyStoreHandler : getKeyStoreHandlers()) {
            if (keyStoreHandler.getName().equals(serviceProvider)) {
                providerFound = true;
                keyStoreHandler.service(this, serviceName, serviceParam);
            }
        }
        if (!providerFound) {
            FileLog.error("ServiceProvider " + serviceProvider + " not found");
        }
    }

    public void setAppletState(String appletState) {
        this.appletState = appletState;
    }

    public String getAppletState() {
        if (outputDataArray == null){
            return appletState;
        }
        // xxx
        return outputDataNames[outputDataIndex]+" READY";
    }

    public void setOutputData(String [] outputDataNames, String [] outputDataArray) {
        if (outputDataNames.length != outputDataArray.length){
            FileLog.fatal("Lengths of output data arrays doesn't match in PassApplet");
        }
        this.outputDataArray = outputDataArray.clone();
        this.outputDataNames = outputDataNames.clone();
        outputDataIndex = 0;
    }

    public String getOutputData() {
        if (outputDataArray == null){
            return "ERROR: output data not ready";
        }
        String result = outputDataArray[outputDataIndex];
        outputDataIndex++;
        outputDataIndex %= outputDataArray.length;
        return result;
    }

    public void setOutputData(String outputData) {
    }
}