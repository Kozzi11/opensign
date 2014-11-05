/*
    Copyright 2011 Paw F. Kjeldgaard


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

/* $Id: NonLiveconnectConnection.java,v 1.4 2012/09/27 11:03:52 pakj Exp $ */
package org.openoces.opensign.client.applet;

import org.openoces.opensign.utils.FileLog;

import javax.swing.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public final class NonLiveconnectConnection implements JavascriptService {
    private static final int chunkSize = 1000;

    private AbstractApplet applet;

    public NonLiveconnectConnection(AbstractApplet applet) {
        this.applet = applet;
    }

    @Override
    public void callFunction(String function, String[] args) {
        String result = getFunctionResult(function, args);
        if (result != null) {
            if (args == null || args.length == 0)
                args = new String[]{""};
            FileLog.info("Calling NonLiveconnectConnection.");
            doAppletRequest(args[0], result);
        }
    }

    /**
     * @return In indicator
     */
    public boolean doAppletRequest() {
        String doAutoMac = applet.getParameter("opensign.doappletrequestonmac");
        String doAppletRequest = applet.getParameter("opensign.doappletrequest");

        if (doAutoMac != null
                && doAutoMac.equalsIgnoreCase("true")
                && OS.isOSMac()) {
            return true;
        }

        if (doAppletRequest != null
                && doAppletRequest.equalsIgnoreCase("true")) {
            FileLog.debug("doAppletRequest() called - returning true (doappletrequest flag set)");
            return true;
        }

        return false;
    }

    public String getFunctionResult(String function, String args[]) {
        FileLog.debug("Getting function result for: " + function);
        if (doAppletRequest()) {
            function = function.toLowerCase();

            if (function.indexOf("alert") >= 0)
                return "alert";
            else if (function.indexOf("error") >= 0)
                return "error";
            else if (function.indexOf("cancel") >= 0)
                return "cancel";
            else if (function.indexOf("ok") >= 0)
                return "ok";
        }

        return null;
    }

    public void doAppletRequest(String message, String result) {
        FileLog.debug("Sending message:\n" + message);
        try {
            String vurl = getDocBase(applet.getDocumentBase());
            String uri = applet.getParameter("opensign.verifieruri");

            if ("ok".equalsIgnoreCase(result) ||
                    "error".equalsIgnoreCase(result) ||
                    "alert".equalsIgnoreCase(result)) {
                if (uri == null)
                    uri = "";
                if (!uri.startsWith("/"))
                    vurl = vurl + "/";
                vurl = vurl + uri;
                if (uri.startsWith("https://")) {
                    vurl = uri;
                }

                URLConnection con = new URL(vurl).openConnection();

                ByteArrayOutputStream bo = new ByteArrayOutputStream();
                StringBuffer output = new StringBuffer(2048);

                String mname = applet.getParameter("opensign.message.name");
                if (mname == null || mname.length() <= 0)
                    mname = "message";

                String rname = applet.getParameter("opensign.result.name");
                if (rname == null || rname.length() <= 0)
                    rname = "result";

                if (message != null) {
                    output.append(mname);
                    output.append("=");

                    char[] chars = message.toCharArray();

                    for (int i = 0; i < chars.length; i++) {
                        if (chars[i] >= 'a'
                                && chars[i] <= 'z'
                                || chars[i] >= 'A'
                                && chars[i] <= 'Z'
                                || chars[i] >= '0'
                                && chars[i] <= '9') {
                            output.append(chars[i]);
                        } else {
                            output.append("%");
                            String s = Integer.toHexString((int) chars[i]);
                            s = s.toUpperCase();
                            if (s.length() < 2)
                                output.append("0");
                            output.append(s);
                        }
                    }
                }

                if (result != null) {
                    if (message != null)
                        output.append("&");
                    output.append(rname);
                    output.append("=");
                    output.append(result);
                }

                int ffcount = getCustomFormFieldCount(applet);
                for (int i = 0; i < ffcount; i++) {
                    output.append("&");
                    output.append(getCustomFormField(applet, i + 1));
                }

                bo.write(output.toString().getBytes());
                byte[] bytes = bo.toByteArray();

                int responsecode;

                /* java vm specific branch
                   unfortunately not all vms return an object of the same class, so we have to deal with all
                   possibilities
                */
                if (con instanceof HttpURLConnection) {
                    HttpURLConnection httpcon = (HttpURLConnection) con;
                    httpcon.setRequestMethod("POST");

                    httpcon.setDoOutput(true);
                    httpcon.setUseCaches(false);
                    if (!HttpURLConnection.getFollowRedirects()) {
                        try {
                            HttpURLConnection.setFollowRedirects(true);
                        } catch (SecurityException e) {
                            FileLog.info("Couldn't setFollowRedirects");
                        }
                    }
                    httpcon.setRequestProperty("Content-Length", "" + bytes.length);
                    if ("OPERA".equals(org.openoces.opensign.client.applet.OS.getBrowser(applet))) {
                        httpcon.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
                    }

                    if (getCookieString(applet) != null)
                        httpcon.setRequestProperty("Cookie", getCookieString(applet));
                    httpcon.connect();
                    writeChunked(httpcon.getOutputStream(), bytes);
                    httpcon.getOutputStream().close();
                    responsecode = httpcon.getResponseCode();

                } else {
                    con.setDoOutput(true);
                    con.setDoInput(true);
                    con.setUseCaches(false);

                    con.setRequestProperty("Content-Length", "" + bytes.length);
                    if (getCookieString(applet) != null)
                        con.setRequestProperty("Cookie", getCookieString(applet));

                    con.connect();

                    OutputStream outStream = con.getOutputStream();
                    writeChunked(outStream, bytes);
                    outStream.close();

                    String httpStatusLine = con.getHeaderField(0);


                    responsecode = getHttpStatusCode(httpStatusLine);
                }

                if (responsecode == 200) {
                    FileLog.debug("Verified OK!!!");
                    uri = applet.getParameter("opensign.verifiedokuri");
                } else {
                    FileLog.debug("Verified NOT OK!!!");
                    uri = applet.getParameter("opensign.verifiederroruri");
                }
            }

            if (result.equalsIgnoreCase("error"))
                uri = applet.getParameter("opensign.erroruri");
            if (result.equalsIgnoreCase("alert"))
                uri = applet.getParameter("opensign.alerturi");
            if (result.equalsIgnoreCase("cancel"))
                uri = applet.getParameter("opensign.canceluri");

            vurl = getDocBase(applet.getDocumentBase());
            if (uri == null)
                uri = "";
            if (!uri.startsWith("/"))
                vurl = vurl + "/";
            vurl = vurl + uri;
            if (uri.startsWith("https://")) {
                vurl = uri;
            }

            applet.getAppletContext().showDocument(new URL(vurl));
        } catch (MalformedURLException urle) {
            FileLog.error("malformed applet request url ", urle);
        } catch (IOException ioe) {
            FileLog.error("io errror occured ", ioe);
        }
    }

    private void writeChunked(OutputStream os, byte[] bytes) throws IOException {
        int off = 0;
        while (off < bytes.length) {
            int len = Math.min(chunkSize, bytes.length - off);
            os.write(bytes, off, len);
            off += len;
            os.flush();
        }
    }

    /**
     * Returns the http status code given a http status line
     *
     * @param statusLine the status
     * @return The status code or -1 if the status line is not valid
     */
    private int getHttpStatusCode(String statusLine) {
        int sp0, sp1, httpStatusCode = -1;

        sp0 = statusLine.indexOf(' ');
        sp1 = statusLine.indexOf(' ', sp0 + 1);


        if (sp0 != -1) {
            try {
                httpStatusCode = (sp1 == -1) ? Integer.valueOf(statusLine.substring(sp0 + 1)) : Integer.valueOf(statusLine.substring(sp0 + 1, sp1));
            } catch (Exception e) {
                FileLog.error("an exception occurred", e);
            }
        }

        return httpStatusCode;
    }

    /**
     * Returns the count of the custom form entries given as
     * applet parametes.
     *
     * @return -1 if count is not defined or not a number. The number in other cases
     */
    private int getCustomFormFieldCount(JApplet applet) {
        String scount = applet.getParameter("opensign.formdata.count");
        int count = 0;

        if (scount == null)
            return -1;

        try {
            count = Integer.parseInt(scount.trim());
        } catch (Exception e) {
            FileLog.error(
                    "The argument opensign.formdata.count is not a number '"
                            + scount
                            + "'");
            return -1;
        }

        return count;
    }

    /**
     * combines parameters to form a custom form field name/value pair
     *
     * @param index The index of the form field value in the applet parameters
     * @return The form field string
     */
    private String getCustomFormField(JApplet applet, int index) {
        String name =
                applet.getParameter("opensign.formdata." + index + ".name");
        String value =
                applet.getParameter("opensign.formdata." + index + ".value");
        return name + "=" + value;
    }

    /**
     * Generates a complete cookie string from a the applet paramets.
     * The method will return <code>null</code> if the cookie count is
     * less or equal to 0, not a number or not existing.
     *
     * @return The generated cookie string
     */
    private String getCookieString(JApplet applet) {
        String scount = applet.getParameter("opensign.cookiecount");
        int count;

        if (scount == null)
            return null;

        try {
            count = Integer.parseInt(scount.trim());
        } catch (Exception e) {
            FileLog.error(
                    "The argument opensign.cookiecount is not a number '"
                            + scount
                            + "'");
            return null;
        }

        if (count == 0)
            return null;

        StringBuffer cookieString = new StringBuffer();
        for (int i = 0; i < count; i++) {
            if (i > 0) {
                cookieString.append("; ");
            }
            cookieString.append(getClientCookie(applet, i + 1));
        }

        return cookieString.toString();
    }

    /**
     * combines parameters to form a client cookie value
     *
     * @param index The index of the client cookie value in the applet parameters
     * @return The client cookie value
     */
    private String getClientCookie(JApplet applet, int index) {
        String name = applet.getParameter("opensign.cookie." + index + ".name");
        String value =
                applet.getParameter("opensign.cookie." + index + ".value");
        return name + "=" + value;
    }

    private String getDocBase(URL docbase) {
        return OcesAppletUtils.getDocBase(docbase);
    }

}