package org.openoces.opensign.demo.servlets;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Random;

@Controller
@RequestMapping(value = "/entrance_openexport.html")
public class ExportController {

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getCreateForm(ModelMap modelMap) {
        Openexport openexport = new Openexport();
        openexport.setAppletVersion("v2.1.9");
        openexport.setAppletWidth(500);
        openexport.setAppletHeight(200);
        openexport.setSignproperties("challenge=mychallenge");
        openexport.setCdkortservice("demo");
        openexport.setAppletLocale("en,US");
        openexport.setShowSsid(false);
        openexport.setOptionalid("no");
        openexport.setLoglevel("info");
        openexport.setLiveconnect(true);
        openexport.setKeystores(new String[]{"oces"});
        openexport.setSignatureAlgorithm("undefined");
        openexport.setVirklogon(false);
        openexport.setInputstyle("not-specified");
        openexport.setAllowregistryaccess(true);
        openexport.setSortcertificates(true);
        openexport.setAppletRequestOnMac(false);

        modelMap.addAttribute("openexport", openexport);

        return new ModelAndView("openexport/createForm");
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView getShowApplet(@ModelAttribute Openexport openexport, HttpServletRequest request, ModelMap modelMap) {
        String contextPath = request.getContextPath();
        Long timestamp = System.currentTimeMillis();

        modelMap.addAttribute("contextPath", contextPath);
        modelMap.addAttribute("timestamp", timestamp);

        String docBase = (request.isSecure() ? "https" : "http") + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
        modelMap.addAttribute("docBase", docBase);

        StringBuilder plugins = new StringBuilder();
        if(openexport.getKeystores() != null && openexport.getKeystores().length > 0) {
            plugins.append(openexport.getKeystores()[0]);
            for(int i = 1; i < openexport.getKeystores().length; i++) {
                plugins.append(",").append(openexport.getKeystores()[i]);
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

        return new ModelAndView("openexport/showApplet");
    }


}
