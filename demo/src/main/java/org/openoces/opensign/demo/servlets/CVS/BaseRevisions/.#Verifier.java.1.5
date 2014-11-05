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

/* $Id: Verifier.java,v 1.5 2012/09/27 11:03:50 pakj Exp $ */

package org.openoces.opensign.demo.servlets;

import org.openoces.ooapi.environment.Environments;
import org.openoces.ooapi.environment.TestableEnvironments;
import org.openoces.ooapi.signatures.OpensignAbstractSignature;
import org.openoces.opensign.demo.utils.Base64;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;

/**
 * This class implements a servlet used to verify the signature. It is used in the demo setup on
 * www.openoces.org.
 *
 * @author Carsten Raskgaard  <carsten@raskgaard.dk>
 * @author Mads Jensen <mjn@trifork.com>
 * @author Jeppe Burchhardt <Jeppe.Burchhardt@Cryptomathic.com>
 * @author Ole Friis Østergaard <ofo@trifork.com>
 *
 */

public class Verifier extends HttpServlet {
	private static final long serialVersionUID = 3800581329792411795L;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws IOException, ServletException {

        try {
            String xmldoc_b64 = request.getParameter("message");
            Integer id = new Integer(request.getParameter("id"));

            File directory = (File) getServletContext().getAttribute("javax.servlet.context.tempdir");
            if( directory == null)
                throw new Exception("Could not get temporary dir");
            File file = new File(directory, "opensign_" + id + ".tmp");
            if (!(file.createNewFile() || (file.delete() && file.createNewFile()))) {
                throw new Exception("Could not open " + file.getAbsolutePath());
            }
            FileWriter outTmp = new FileWriter(file);
            outTmp.write(xmldoc_b64);
            outTmp.close();

            String xmldoc = Base64.base64Decode(xmldoc_b64.getBytes("UTF-8"));
            setupEnvironment();
			OpensignAbstractSignature aos = org.openoces.ooapi.signatures.OpensignSignatureFactory.getInstance().generateOpensignSignature(xmldoc);
			aos.verify();

			response.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
        }
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