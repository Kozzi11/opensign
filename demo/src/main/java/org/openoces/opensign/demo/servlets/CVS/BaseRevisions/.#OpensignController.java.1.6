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

/* $Id: OpensignController.java,v 1.6 2013/02/27 09:16:39 pakj Exp $ */

package org.openoces.opensign.demo.servlets;

import org.openoces.opensign.demo.utils.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Random;

/**
 * @author Paw Figgé Kjeldgaard (pfk@miracleas.dk)
 */
@SessionAttributes({"opensign"})
@Controller
@RequestMapping(value = "/entrance_opensign.html")
public class OpensignController {
   private final static String[] NOT_SUPPORTED_PDF_VERSIONS = {"v1.6.7", "v1.6.9", "v1.8.1"};

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getCreateForm(ModelMap modelMap) {
        Opensign opensign = new Opensign();
        opensign.setAppletVersion("v2.1.9");
        opensign.setAppletWidth(600);
        opensign.setAppletHeight(400);
        opensign.setSignproperties("challenge=mychallenge");
        opensign.setCdkortservice("demo");
        opensign.setAppletLocale("en,US");
        opensign.setShowSsid(false);
        opensign.setOptionalid("no");
        opensign.setLoglevel("debug");
        opensign.setLiveconnect(true);
        opensign.setKeystores(new String[]{"capi", "pkcs12", "oces"});
        opensign.setSignatureAlgorithm("undefined");
        opensign.setVirklogon(false);
        opensign.setInputstyle("not-specified");
        opensign.setAllowregistryaccess(true);
        opensign.setSortcertificates(true);
        opensign.setAppletRequestOnMac(false);
        opensign.setFormat("PLAIN");

        modelMap.addAttribute("opensign", opensign);

        return new ModelAndView("opensign/createForm");
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView getShowApplet(@ModelAttribute("opensign") Opensign opensign, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, BindingResult result) {
        try {
            if (opensign.getFileData() != null && opensign.getFileData().getSize() > 0) {
                opensign.addAttachment(new Attachment(opensign.getFileData()));
            }
            if (opensign.getAgreementPdfFileData() != null && !opensign.getAgreementPdfFileData().isEmpty()) {
                Pdf pdf = new Pdf(opensign.getAgreementPdfFileData());
                pdf.setPdfUri(request.getContextPath() + "/servlet/DisplayPdfSignText;jsessionid=" + request.getSession().getId());
                pdf.setPdfHashAlgorithm("sha1");
                pdf.setPdfHashValue(hash(opensign.getAgreementPdfFileData().getBytes()));

                opensign.setPdf(pdf);
            }

            if ("addAttachment".equals(opensign.getEvent())) {
                opensign.setFileData(null);
                return new ModelAndView("opensign/createForm");
            } else {
                if (opensign.getFormat().equals("PDF") && opensign.getPdf() == null) {
                    result.addError(new ObjectError("pdf", "PDF file is missing!!!"));
                    return new ModelAndView("opensign/createForm");
                }

                if(opensign.getFormat().equals("PDF") && isPdfSupported(opensign.getAppletVersion())) {
                    result.addError(new ObjectError("appletVersion", "PDF not supportted!!!"));
                    return new ModelAndView("opensign/createForm");
                }

                String codeBase = (request.isSecure() ? "https" : "http") + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
                modelMap.addAttribute("codeBase", codeBase);

                Long timestamp = System.currentTimeMillis();

                modelMap.addAttribute("contextPath", request.getContextPath());
                modelMap.addAttribute("timestamp", timestamp);


                StringBuilder plugins = new StringBuilder();
                if (opensign.getKeystores() != null && opensign.getKeystores().length > 0) {
                    plugins.append(opensign.getKeystores()[0]);
                    for (int i = 1; i < opensign.getKeystores().length; i++) {
                        plugins.append(",").append(opensign.getKeystores()[i]);
                    }
                    if (opensign.getAttachments().size() > 0) {
                        plugins.append(",attachment");
                    }
                    plugins.append(",pdf");
                }
                modelMap.addAttribute("plugins", plugins.toString());

                Random random = new Random();
                Integer randomValue = random.nextInt(256);

                modelMap.addAttribute("random", randomValue);
                modelMap.addAttribute("cookies", request.getCookies());
                modelMap.addAttribute("numberOfCookies", request.getCookies() != null ? request.getCookies().length : 0);

                if (opensign.getAttachments().size() > 0) {
                    String xml = createAttachmentXml(opensign.getAttachments(), request, response);
                    opensign.setAttachmentXml(xml);
                }

                OpenLive openLive = new OpenLive();
                openLive.setMessage("test");
                openLive.setResult("test");
                modelMap.addAttribute("openLive", openLive);

                return new ModelAndView("opensign/showApplet");
            }
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    private String createAttachmentXml(List<Attachment> attachments, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\" encoding=\"utf8\" ?>");
        sb.append("<attachments>");
        for (Attachment attachment : attachments) {

            sb.append("<attachment>");
            sb.append("<title>").append(attachment.getName()).append("</title>");

            String path = request.getContextPath() + "/servlet/DisplayAttachment;jsessionid=" + request.getSession().getId() + "?file=" + URLEncoder.encode(attachment.getName(), "UTF-8");

            sb.append("<path>").append(path).append("</path>");
            sb.append("<mimeType>").append(attachment.getContentType()).append("</mimeType>");
            sb.append("<size>").append(attachment.getContent().length).append("</size>");

            String hash = attachment.isValidChecksum() ? hash(attachment.getContent()) : hash("24523542".getBytes());

            sb.append("<hashValue>").append(hash).append("</hashValue>");
            if (attachment.isOptional()) {
                sb.append("<optional/>");
            }
            sb.append("</attachment>");
        }
        sb.append("</attachments>");

        return sb.toString();
    }

    private String hash(byte[] value) {
        try {
            MessageDigest digest = MessageDigest.getInstance("sha1");
            byte[] hash = digest.digest(value);
            return Base64.base64Encode(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

    }

    private boolean isPdfSupported(String version) {
        for (String NOT_SUPPORTED_PDF_VERSION : NOT_SUPPORTED_PDF_VERSIONS) {
            if (NOT_SUPPORTED_PDF_VERSION.equals(version)) {
                return true;
            }
        }
        return false;
    }
}