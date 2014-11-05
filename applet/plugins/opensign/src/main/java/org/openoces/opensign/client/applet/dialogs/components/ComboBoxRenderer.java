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

/* $Id: ComboBoxRenderer.java,v 1.2 2012/02/28 08:20:48 pakj Exp $ */

package org.openoces.opensign.client.applet.dialogs.components;

import org.openoces.opensign.certificate.CertificateHandler;
import org.openoces.opensign.client.applet.resources.ResourceManager;
import org.openoces.opensign.utils.FileLog;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class ComboBoxRenderer extends JLabel implements ListCellRenderer {

    public ComboBoxRenderer() {
        setOpaque(true);
        setHorizontalAlignment(LEFT);
        setVerticalAlignment(CENTER);

        // reset the Label Border by add EmptyBorder
        setBorder(BorderFactory.createCompoundBorder(
                getBorder(), // outside border, your Label Border
                BorderFactory.createEmptyBorder(0, 5, 0, 0))); // add 5 pixel for left for inside Border
    }

    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        if (isSelected || cellHasFocus) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }

        if(value != null) {
            setText(getCertificateDisplayName((CertificateHandler)value));
        }
        setFont(list.getFont());
        return this;
    }

    private String getCertificateDisplayName(CertificateHandler certificateHandler) {
        String name;
        String primaryName, secondaryName = "";
        boolean hasExpired = false;
        String hasExpiredPart = null;

        if (!certificateHandler.isInfoAvailable())
            return certificateHandler.getUserFriendlyName();

        int maxWidth = (int)(getPreferredSize().getWidth() / DefaultComponentFactory.labelFontWith);

        // todo: re-insert the expression evaluator etc
        primaryName = certificateHandler.getUserFriendlyName();
        try {
            // check if it is personal or employee cert
            String subjectDN = certificateHandler.getSubjectDN().getName();

            hasExpired = certificateHandler.getNotAfter() != null && certificateHandler.getNotAfter().before(new Date());

            /* save space for expired part */
            hasExpiredPart = "[" + ResourceManager.getString("CERTIFICATE_EXPIRED") + "]";
            if (hasExpired) {
                maxWidth = maxWidth - hasExpiredPart.length();
            }

            if (subjectDN.indexOf("-RID:") != -1) {
                // new version: make it "cvr:12341234"
                secondaryName = "cvr:" + getStringBetween("=CVR:", "-RID:", certificateHandler.getSubjectDN().getName());
            } else if (subjectDN.indexOf("-UID:") != -1) {
                secondaryName = "vcert:" + getStringBetween("=CVR:", "-UID:", certificateHandler.getSubjectDN().getName());
            } else if (subjectDN.indexOf("PID:") != -1) {
                secondaryName = ResourceManager.getString("CERT_PERSONAL");
            } else {
                secondaryName = certificateHandler.getSerialNumber().toString(16);
                // cut it down if longer than others:
                if (secondaryName.length() > getSecondaryNameLength()) {
                    secondaryName = secondaryName.substring(0, getSecondaryNameLength() - 2) + "..";
                }
            }
        } catch (Exception e) {
            FileLog.error("unexpected situation occurred", e);
        }

        name = primaryName;
        if (name.equals("")) {
            name = secondaryName;

            if (name.equals("")) {
                try {
                    name = certificateHandler.getSubjectDN().toString();
                } catch (Exception e2) {
                    FileLog.error("an exception occurred", e2);
                    name = "(unknown)";
                }
            }

            return name;
        }

        if (!secondaryName.equals("")) {
            // Abbreviate the name if needed
            if (name.length() + secondaryName.length() + 3 > maxWidth) {
                // abbrev name first:
                int maxNameLength = maxWidth - (secondaryName.length() + 3);
                name = name.substring(0, Math.min(name.length(), Math.max(0, maxNameLength - 2))) + "..";
            }
            name += " (" + secondaryName + ")";
        }

        if (hasExpired) {
            name += hasExpiredPart;
        }

        return name;
    }

    private int getSecondaryNameLength() {
        String s1 = ResourceManager.getString("CERT_EMPLOYEE");
        String s2 = ResourceManager.getString("CERT_PERSONAL");
        return Math.max(s1.length(), s2.length());
    }

    private String getStringBetween(String prefix, String postfix, String source) {
        int start = source.indexOf(prefix);
        if (start == -1) {
            FileLog.error("Error finding prefix " + prefix + " in source " + source);
            return "?";
        }
        String rest = source.substring(start + prefix.length());
        int end = rest.indexOf(postfix);
        if (end == -1) {
            FileLog.error("Error finding postfix " + postfix + " in rest " + rest);
            return "?";
        }
        return rest.substring(0, end);
    }
}
