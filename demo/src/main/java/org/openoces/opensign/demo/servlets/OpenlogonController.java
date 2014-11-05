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

/* $Id: OpenlogonController.java,v 1.5 2013/02/27 09:16:39 pakj Exp $ */

package org.openoces.opensign.demo.servlets;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Random;

/**
 * @author Paw Figgé Kjeldgaard (pfk@miracleas.dk)
 */
@Controller
@RequestMapping(value = "/entrance_openlogon.html")
public class OpenlogonController {

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getCreateForm(ModelMap modelMap) {
        Openlogon openlogon = new Openlogon();
        openlogon.setAppletVersion("v2.1.9");
        openlogon.setAppletWidth(500);
        openlogon.setAppletHeight(200);
        openlogon.setSignproperties("challenge=mychallenge");
        openlogon.setCdkortservice("demo");
        openlogon.setAppletLocale("en,US");
        openlogon.setShowSsid(false);
        openlogon.setOptionalid("no");
        openlogon.setLoglevel("info");
        openlogon.setLiveconnect(true);
        openlogon.setKeystores(new String[]{"capi", "pkcs12", "oces"});
        openlogon.setSignatureAlgorithm("undefined");
        openlogon.setVirklogon(false);
        openlogon.setInputstyle("not-specified");
        openlogon.setAllowregistryaccess(true);
        openlogon.setSortcertificates(true);
        openlogon.setAppletRequestOnMac(false);

        modelMap.addAttribute("openlogon", openlogon);

        return new ModelAndView("openlogon/createForm");
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView getShowApplet(@ModelAttribute Openlogon openlogon, HttpServletRequest request, ModelMap modelMap) {
        String contextPath = request.getContextPath();
        Long timestamp = System.currentTimeMillis();

        modelMap.addAttribute("contextPath", contextPath);
        modelMap.addAttribute("timestamp", timestamp);

        String docBase = (request.isSecure() ? "https" : "http") + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
        modelMap.addAttribute("docBase", docBase);

        StringBuilder plugins = new StringBuilder();
        if(openlogon.getKeystores() != null && openlogon.getKeystores().length > 0) {
            plugins.append(openlogon.getKeystores()[0]);
            for(int i = 1; i < openlogon.getKeystores().length; i++) {
                plugins.append(",").append(openlogon.getKeystores()[i]);
            }
        }
        modelMap.addAttribute("plugins", plugins.toString());

        Random random = new Random();
        Integer randomValue = random.nextInt(256);

        modelMap.addAttribute("random", randomValue);
        modelMap.addAttribute("cookies", request.getCookies());
        modelMap.addAttribute("numberOfCookies", request.getCookies() != null ? request.getCookies().length : 0);

        OpenLive openLive = new OpenLive();
        modelMap.addAttribute("openLive", openLive);

        return new ModelAndView("openlogon/showApplet");
    }

}