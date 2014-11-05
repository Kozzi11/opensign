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

/* $Id: DisplayAttachment.java,v 1.3 2012/09/27 11:03:50 pakj Exp $ */
package org.openoces.opensign.demo.servlets;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Paw Figgé Kjeldgaard (pfk@miracleas.dk)
 */
public class DisplayAttachment extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Opensign opensign = (Opensign) session.getAttribute("opensign");
        String file = request.getParameter("file");
        Attachment attachment = getAttachment(file, opensign);
        if (attachment != null) {
            response.setContentType(attachment.getContentType());
            response.setContentLength(attachment.getContent().length);
            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(attachment.getContent());
            outputStream.flush();
            outputStream.close();
        } else {
            response.sendError(404);
        }
   }

    private Attachment getAttachment(String file, Opensign opensign) {
        for (Attachment attachment : opensign.getAttachments()) {
            if (attachment.getName().equals(file)) {
                return attachment;
            }
        }
        return null;
    }
}