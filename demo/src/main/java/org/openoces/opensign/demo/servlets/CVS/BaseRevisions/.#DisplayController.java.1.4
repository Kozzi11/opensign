/*
    Copyright 2012 Paw Figgé Kjeldgaard


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

/* $Id: DisplayController.java,v 1.4 2012/09/27 11:03:49 pakj Exp $ */

package org.openoces.opensign.demo.servlets;

import org.openoces.ooapi.environment.Environments;
import org.openoces.ooapi.environment.TestableEnvironments;
import org.openoces.ooapi.signatures.OpensignAbstractSignature;
import org.openoces.ooapi.signatures.OpensignSignatureFactory;
import org.openoces.opensign.demo.utils.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Paw Figgé Kjeldgaard (pfk@miracleas.dk)
 */
@Controller
@RequestMapping(value = "/display.html")
public class DisplayController {


    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getShowAppletRequestResult(@ModelAttribute AppletRequest appletRequest, HttpServletRequest request) {
        String contextPath = request.getContextPath();
        Map<String, Object> values = new HashMap<String, Object>();
        values.put("contextPath", contextPath);

        StringBuilder bs = new StringBuilder();

        if ("ok".equals(appletRequest.getResult())) {
            try {
                ServletContext servletContext = request.getSession().getServletContext();
                File directory = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
                File file = new File(directory, "opensign_" + appletRequest.getId() + ".tmp");
                FileInputStream f = new FileInputStream(file);
                byte[] buf = new byte[8192];
                int i;
                while ((i = f.read(buf)) != -1) {
                    bs.append(new String(buf, 0, i, "UTF-8"));
                }
                f.close();
                file.delete();
                values.put("verified", true);

                String message = bs.toString();

                appletRequest.setMessage(message);

                String doc = Base64.base64Decode(message);

                setupEnvironment();
                OpensignAbstractSignature aos = OpensignSignatureFactory.getInstance().generateOpensignSignature(doc);
                aos.verify();

                values.put("signatureProperties", aos.getSignatureProperties());

            } catch (Exception e) {
                values.put("verified", false);
                appletRequest.setMessage("");
            }
        }

        return new ModelAndView("display/showAppletRequestResult", values);
    }


    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView getShowOpenliveResult(@ModelAttribute("openLive") OpenLive openLive, HttpServletRequest request) {
        String contextPath = request.getContextPath();
        Boolean sigVerified = false;
        Map<String, Object> values = new HashMap<String, Object>();
        values.put("contextPath", contextPath);
        if ("ok".equals(openLive.getResult())) {
            try {
                String doc = Base64.base64Decode(openLive.getMessage());

                setupEnvironment();
                OpensignAbstractSignature aos = OpensignSignatureFactory.getInstance().generateOpensignSignature(doc);
                aos.verify();

                sigVerified = true;

                values.put("signatureProperties", aos.getSignatureProperties());

            } catch (Exception e) {
                sigVerified = false;
            }
        }
        values.put("verified", sigVerified);
        return new ModelAndView("display/showOpenLiveResult", values);
    }

    private void setupEnvironment() {
        TestableEnvironments.setEnvironments(
                Environments.Environment.OCESI_DANID_ENV_PROD,
                Environments.Environment.OCESI_DANID_ENV_SYSTEMTEST,
                Environments.Environment.OCESI_DANID_ENV_DEVELOPMENT,
                Environments.Environment.CAMPUSI_DANID_ENV_PROD,
                Environments.Environment.OCESII_DANID_ENV_PREPROD,
                Environments.Environment.OCESII_DANID_ENV_PROD,
                Environments.Environment.OCESII_DANID_ENV_EXTERNALTEST,
                Environments.Environment.OCESII_DANID_ENV_INTERNALTEST,
                Environments.Environment.OCESII_DANID_ENV_IGTEST,
                Environments.Environment.OCESII_DANID_ENV_OPERATIONSTEST,
                Environments.Environment.OCESII_DANID_ENV_DEVELOPMENTTEST,
                Environments.Environment.OCESII_DANID_ENV_DEVELOPMENT,
                Environments.Environment.OCESII_DANID_ENV_LOCALHOST_TESTING);
    }
}