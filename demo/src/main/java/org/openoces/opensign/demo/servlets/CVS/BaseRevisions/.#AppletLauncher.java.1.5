/*
    Copyright 2006 IT Practice A/S
    Copyright 2006 TDC Totalløsninger A/S
    Copyright 2006 Jens Bo Friis
    Copyright 2006 Preben Rosendal Valeur
    Copyright 2006 Carsten Raskgaard
    Copyright 2011 Daniel Andersen


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

/* $Id: AppletLauncher.java,v 1.5 2012/09/27 11:03:50 pakj Exp $ */

package org.openoces.opensign.demo.servlets;

import org.openoces.opensign.demo.utils.Base64;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.StringTokenizer;

/**
 * This class is used to launch the OpenSign applet from the demonstrational setup on
 * www.openoces.org.
 *
 * @author Carsten Raskgaard  <carsten@raskgaard.dk>
 * @author Mads Jensen <mjn@trifork.com>
 * @author Jeppe Burchhardt <Jeppe.Burchhardt@Cryptomathic.com>
 * @author Ole Friis Østergaard <ofo@trifork.com>
 * @author Daniel Andersen <daand@nets.eu>
 */

public class AppletLauncher extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws IOException, ServletException {

        String title = "";
        String applet = request.getParameter("appletname");
        String applet_width = request.getParameter("applet_width");
        String applet_height = request.getParameter("applet_height");
        String appletDirectory;

        appletDirectory = request.getParameter("applet_version");

        StringTokenizer st = new StringTokenizer(request.getParameter("applet_version").substring(1), ".-");

        int majorVersion =  st.hasMoreTokens() ? parseInt(st.nextToken()) : -1;
        int minorVersion = st.hasMoreTokens() ? parseInt(st.nextToken()) : -1;

        boolean bootDeploy = majorVersion == -1 || (majorVersion >= 1 && minorVersion > 2);
        boolean preAttachmentSupport = majorVersion != -1 && majorVersion <= 1 && minorVersion < 5;

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html>");

        out.println("<head>");
        out.println("<title>" + title + "</title>");
        out.println("</head>");

        out.println("<body bgcolor=\"white\">");

        if (bootDeploy) {
            String codeValue = "org.openoces.opensign.client.applet.bootstrap." + ("v1.3.0".equals(request.getParameter("applet_version")) ? "RMBootApplet" : "BootApplet");

            out.println("<applet name=\"signing_applet\" code=\"" + codeValue + "\" width=\"" + applet_width + "\" height=\"" + applet_height + "\" codebase=\"/demo\" archive=\"/demo/" + appletDirectory + "/OpenSign-bootstrapped.jar?time=" + System.currentTimeMillis() + "\" mayscript>\n");
            if (applet.equals("Sign")) {
                out.println(getParamPair("ZIP_FILE_ALIAS", "OpenSign"));
            } else {
                out.println(getParamPair("ZIP_FILE_ALIAS", "OpenLogon"));
            }

            if (appletDirectory.equals(".")){
            	out.println(getParamPair("ZIP_BASE_URL", "/demo"));
            } else {
            	out.println(getParamPair("ZIP_BASE_URL", "/demo/" + appletDirectory + "/plugins"));
            }

            out.println(getParamPair("MS_SUPPORT", "bcjce"));
            out.println(getParamPair("SUN_SUPPORT", "jsse"));

            out.println(getParamPair("STRIP_ZIP", "yes"));// for stripping BC!

            String[] potentialPlugins = {"capi", "pkcs12", "cdcard", "oces", "cryptoki", preAttachmentSupport ? "non-existing" : "attachment"};
            String plugins = "";
            for (int i = 0; i < potentialPlugins.length; i++) {
                String potentialPlugin = potentialPlugins[i];
                String pluginSelected = request.getParameter(potentialPlugin);

                if (pluginSelected != null) {
                    if (plugins.length() > 0) {
                        plugins += ",";
                    }
                    plugins += potentialPlugin;
                }
            }
            if (plugins != null) {
                out.println(getParamPair("EXTRA_ZIP_FILE_NAMES", plugins));
            }

            if (request.getParameter("attachment") != null) {

                String[][] attachmentdata = {
                        {"A sample png sample", "openoces-logo.png", "image/png", "8233", "DtQ1+kwcved81t36LBNM+7fngV4=", "mandatory"},
                        {"A sample text document", "sample.txt", "text/plain", "6", "iEPX+SQWIR3p67lj/0zigSWTKHg=", "optional"},
                        {"A sample html document", "sample.html", "text/html", "146", "16X24HTyYJzlrND0gnkg0vSjrto=", "mandatory"},
                        {"A sample rtf document", "sample.rtf", "text/rtf", "2188", "ngGho/oKxUIhg6ZNIo64mA44IO0=", "mandatory"},
                        {"A sample pdf document (has invalid checksum)", "sample.pdf", "text/pdf", "177150", "iEPX+SQWIR3p67lj/0zigSWTKHg=", "optional"},
                };

                StringBuffer sb = new StringBuffer();
                sb.append("<?xml version=\"1.0\" encoding=\"utf8\" ?>");
                sb.append("<attachments>");
                for (int i = 0; i < attachmentdata.length; i++) {
                    String[] strings = attachmentdata[i];
                    String aTitle = strings[0];
                    String aFile = strings[1];
                    String aMimeType = strings[2];
                    String aSize = strings[3];
                    String aDigest = strings[4];
                    boolean isOptional = "optional".equals(strings[5]);

                    sb.append("<attachment>");
                    sb.append("<title>").append(aTitle).append("</title>");
                    sb.append("<path>").append("/demo/attachments/").append(aFile).append("</path>");
                    sb.append("<mimeType>").append(aMimeType).append("</mimeType>");
                    sb.append("<size>").append(aSize).append("</size>");
                    sb.append("<hashValue>").append(aDigest).append("</hashValue>");
                    if (isOptional) {
                        sb.append("<optional/>");
                    }
                    sb.append("</attachment>");
                }
                sb.append("</attachments>");

                out.println(getParamPair("attachments", Base64.base64Encode(sb.toString())));
            }
//            out.println(getParamPair("LOG_LEVEL","DEBUG")); // todo: make configurable
            out.println(getParamPair("LOG_LEVEL", "INFO")); // todo: make configurable
        } else {
            out.println("<applet name=\"signing_applet\" code=\"org.openoces.opensign.client.applet." + applet + "\" width=\"" + applet_width + "\" height=\"" + applet_height + "\" codebase=\"/demo\" archive=\"/xdemo/" + appletDirectory + "/OpenSign.jar\" mayscript>\n");
        }

        out.println(getParamPair("locale", request.getParameter("locale")) + "\n");
        if (bootDeploy) {
        	if (!appletDirectory.equals(".")){
        		out.println(getParamPair("cabbase", "/demo/" + appletDirectory + "/OpenSign-bootstrapped.cab") + "\n");
        	} else {
        		out.println(getParamPair("cabbase", "/demo/OpenSign-bootstrapped.cab") + "\n");
        	}
        } else {
            out.println(getParamPair("cabbase", "/demo/" + appletDirectory + "/OpenSign.cab") + "\n");
        }
        out.println(getParamPair("key.store.directory", "null") + "\n");

        out.println(getParamPair("loglevel", request.getParameter("loglevel")) + "\n");
        if (applet.equals("Sign")) {
            boolean plainText = "plaintext".equals(request.getParameter("inputtextas"));
            if (plainText) {
                out.println(getParamPair("signtext", new String(Base64.base64Encode(request.getParameter("text")))) + "\n");
            } else {
                out.println(getParamPair("signtext", new String(Base64.base64Encode(request.getParameter("xmltext")))) + "\n");
            }

            String signTextFormat = request.getParameter("signtext_format");
            out.println(getParamPair("signTextFormat", signTextFormat));
            if (signTextFormat.equals("XML")){
                String xslt = request.getParameter("xslt");
                out.println(getParamPair("signTransformation", new String(Base64.base64Encode(xslt))));
                String xsltId = request.getParameter("xslt_id");
                if (xsltId != null && xsltId.trim().length() != 0) {
                    out.println(getParamPair("signTransformationId", xsltId));
                }
            }
            
            String signtextfontname = request.getParameter("signtextfontname");
            String signtextfontsize = request.getParameter("signtextfontsize");
            String signtextcols = request.getParameter("signtextcols");
            String signtextrows = request.getParameter("signtextrows");

            if (signtextfontname != null && signtextfontname.trim().length() != 0) {
                out.println(getParamPair("signtextfontname", signtextfontname));
            }
            if (signtextfontsize != null && signtextfontsize.trim().length() != 0) {
                out.println(getParamPair("signtextfontsize", signtextfontsize));
            }
            if (signtextcols != null && signtextcols.trim().length() != 0) {
                out.println(getParamPair("signtextcols", signtextcols));
            }
            if (signtextrows != null && signtextrows.trim().length() != 0) {
                out.println(getParamPair("signtextrows", signtextrows));
            }
        }
        out.println(getParamPair("background", request.getParameter("background")));
        out.println(getParamPair("socialsecuritynumber", request.getParameter("socialsecuritynumber") != null ? request.getParameter("socialsecuritynumber") : "") + "\n");
        out.println(getParamPair("optionalid", request.getParameter("optionalid") != null ? request.getParameter("optionalid") : "") + "\n");

        if ("no".equals(request.getParameter("allowregistryaccess"))) {
            out.println(getParamPair("disallowregistryaccess", ""));
        }

        if ("yes".equals(request.getParameter("sortcertificates"))) {
            out.println(getParamPair("sortcertificates", ""));
        }


        Random rand = new Random();
        int v = rand.nextInt(256);

        out.println(getParamPair("opensign.doappletrequest", request.getParameter("liveconnect").equals("no") ? "true" : "false"));
        if (request.getParameter("logonto") != null) {
            out.println(getParamPair("logonto", request.getParameter("logonto")));
        }
        if (request.getParameter("cdkortservice") != null) {
            out.println(getParamPair("cdkortservice", request.getParameter("cdkortservice")));
        }
        if (request.getParameter("signproperties") != null) {
            out.println(getParamPair("signproperties", request.getParameter("signproperties")));
        }
        if (request.getParameter("certlistwidth") != null && !"".equals(request.getParameter("certlistwidth"))) {
            try {
                Integer.parseInt(request.getParameter("certlistwidth"));
                out.println(getParamPair("certlistwidth", request.getParameter("certlistwidth")));
            } catch (NumberFormatException e) {
                // this is a minor issue, so we just keep quiet
            }
        }
        if (request.getParameter("signatureAlgorithm") != null) {
            if (!request.getParameter("signatureAlgorithm").equals("undefined")) {
                out.println("<param name=\"opensign.signature.factory\" value=\"" + request.getParameter("signatureAlgorithm") +"\">\n");
            }
        }

        if (request.getParameter("virklogon") != null) {
            if (request.getParameter("virklogon").equals("yes")) {
                out.println("<param name=\"VIRK_LOGON\" value=\"virklogon\">\n");
            }
        }
        out.println(getParamPair("opensign.doappletrequestonmac", "true"));
        out.println(getParamPair("opensign.verifieruri", "/demo/servlet/Verifier?id=" + v));
        out.println(getParamPair("opensign.canceluri", "/demo/servlet/Display?result=cancel"));
        out.println(getParamPair("opensign.erroruri", "/demo/servlet/Display?result=error"));
        out.println(getParamPair("opensign.alerturi", "/demo/servlet/Display?result=alert"));
        out.println(getParamPair("opensign.verifiedokuri", "/demo/servlet/Display?result=ok&id=" + v));
        out.println(getParamPair("opensign.verifiederroruri", "/demo/servlet/Display?result=error&id=" + v));
        out.println(getParamPair("opensign.message.name", "message"));
        out.println(getParamPair("opensign.result.name", "result"));

        Cookie cookies[] = request.getCookies();
        if (cookies != null) {
            out.println(getParamPair("opensign.cookiecount", "" + cookies.length));
            for (int i = 0; i < cookies.length; i++) {
                out.println(getParamPair("opensign.cookie." + (i + 1) + ".name", cookies[i].getName()));
                out.println(getParamPair("opensign.cookie." + (i + 1) + ".value", cookies[i].getValue()));
            }
        } else {
            out.println(getParamPair("opensign.cookiecount", "0"));
        }

        out.println(getParamPair("opensign.formdata.count", "2"));
        out.println(getParamPair("opensign.formdata.1.name", "formfield1name"));
        out.println(getParamPair("opensign.formdata.1.value", "formfield1value"));
        out.println(getParamPair("opensign.formdata.2.name", "formfield2name"));
        out.println(getParamPair("opensign.formdata.2.value", "formfield2value"));

        if (request.getParameter("opensign.doappletrequest") != null) {
            out.println(getParamPair("opensign.doappletrequest", "true"));
        }

        String filters[] = {"subjectdnfilter", "issuerdnfilter", "sernofilter"};
        for (int i = 0; i < filters.length; i++) {
            String filtername = filters[i];
            String filtervalue = request.getParameter(filtername) == null ? "" : request.getParameter(filtername);
            if (!filtervalue.equals("")) {
                out.println(getParamPair(filtername, Base64.base64Encode(filtervalue)));
            }
        }
        out.println(getParamPair("gui", request.getParameter("gui")));

        if (!"not-specified".equals(request.getParameter("inputstyle"))) {
            out.println(getParamPair("inputstyle", request.getParameter("inputstyle")));
        }
        String logonHeader = request.getParameter("logonheader");
        if(logonHeader != null && logonHeader.trim().length() > 0) {
            out.println("<param name=\"LOGON_HEADER\" value=\"" + logonHeader + "\">\n");
        }

        out.println("</applet>");
        boolean poll = request.getParameter("liveconnect").equals("poll");

        out.println("<script language=\"JavaScript\">");

        out.println("function on" + applet + "OK(signature) {");
        if (!poll) {
            out.println("document.signedForm.message.value=signature;");
            out.println("document.signedForm.result.value='ok';");
            out.println("document.signedForm.submit();");
        }
        out.println("}");

        out.println("function on" + applet + "Cancel() {");
        if (!poll) {
            out.println("document.signedForm.result.value='cancel';");
            out.println("document.signedForm.submit();");
        }
        out.println("}");

        out.println("function on" + applet + "Error(msg) {");
        if (!poll) {
            out.println("document.signedForm.result.value=msg;");
            out.println("document.signedForm.submit();");
        }
        out.println("}");
        out.println("</script>");
        if (poll) {
            out.println("<script language=\"JavaScript\" src=\"/demo/polling.js\"></script>");
        }
        out.println("<form name=\"signedForm\" method=\"post\" action=\"/demo/servlet/Display\">");
        out.println("<input type=\"hidden\" name=\"message\">");
        out.println("<input type=\"hidden\" name=\"result\">");
        out.println("</form>");
        out.println("</body>");

        out.println("</html>");
    	
    }

    private String getParamPair(String name, String value) {
        return "<param name=\"" + name + "\" value=\"" + value + "\">";
    }

    private int parseInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (Exception e) {
            return -1;
        }
    }

}