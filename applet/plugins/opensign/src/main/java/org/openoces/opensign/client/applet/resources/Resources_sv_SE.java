/*
    Copyright 2006 IT Practice A/S
    Copyright 2006 TDC Totalløsninger A/S
    Copyright 2006 Jens Bo Friis
    Copyright 2006 Preben Rosendal Valeur
    Copyright 2006 Carsten Raskgaard
    Copyright 2013 Anders M. Hansen


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

/* $Id: Resources_sv_SE.java,v 1.5 2013/02/08 10:00:50 anmha Exp $ */

package org.openoces.opensign.client.applet.resources;

/**
 * sv,SE specific resource class
 *
 * @author Jens Bo Friis  <jbf@it-practice.dk>
 * @author Carsten Raskgaard  <carsten@raskgaard.dk>
 * @author Preben Rosendal Valeur  <prv@tdc.dk>
 * @author Paw F. Kjeldgaard <pakj@danid.dk>
 * @author Anders M. Hansen <consult@ajstemp.dk>
 */


public class Resources_sv_SE extends AbstractListResourceBundle {

    protected Object[][] getContents() {
        return deepCopy(contents);
    }

    private static final Object[][] contents = {
        {
		"ABOUT", "Om..."}, {
		"ABOUT_DETAILS", "Logon och digital sigering med OCES certifikat och support for PID-CPR"}, {
		"ATTACHMENT_LIST", "Bilagor..."},{
		"ATTACHMENT_SAVE", "Spara..."},{
		"ATTACHMENT_VIEW", "Visa..."},{
		"BACKUP_KEYFILE", "Säkerhetskopiera nyckelfil..."}, {
		"CANCEL", "Avbryt"}, {
		"CANCEL_MODERN", "Avbryt"}, {
		"CANCEL_LOGON_MODERN", "Avbryt"}, {
		"CERT_EMPLOYEE", "Anställd"}, {
		"CERT_PERSONAL", "Personlig"}, {
		"CERTIFICATE_DETAILS", "Certifikatdetaljer..."}, {
		"CERTIFICATE_EXPIRED", "Utgått"}, {            
		"CERTIFICATE_LIST", "Certifikat"}, {
		"CONFIRM_ACCEPT_WRONG_CHECKSUM", "Fel checksumma - fortsätt ändå?"},{    
        "CONFIRM_DELETE", "Radera nyckelfil?"}, {
        "CUSTOMERID", "Kundid"}, {
        "DEFAULT_ERROR_MSG", "Ett fel har uppstått"}, {
        "DELETE_KEYFILE", "Radera nyckelfil..."}, {
        "DRIVER_NOT_FOUND", "PKCS11 driver not found"}, {
        "INSERT_HARDWARETOKEN", "Insert hardware to read certificates"}, {
        "HARDWARETOKEN_COULD_NOT_INITIALIZE", "Could not initialize hardware"}, {
        "HARDWARETOKEN_CHECKSUM_ERROR_TITLE", "Unknown driver for hardware"}, {
        "HARDWARETOKEN_CHECKSUM_ERROR_MESSAGE", "Could not recognize the found driver.\nAre you sure you want to continue?"}, {
        "POLLER_ADDING_CERTIFICATES", "Adding certificates to list..."}, {
        "DLG_ABOUT_LGPL_NOTICE", "This is open source software, placed under the terms of the " +
        "GNU LESSER GENERAL PUBLIC LICENSE.\n" +
        "Please see: http://www.gnu.org/licenses/lgpl.txt or " +
        "opensign.license for details.\n" +
        "Permission to use, copy, modify, and distribute this software\n" +
        "for any purpose and without fee is hereby granted, provided\n" +
        "that the above copyright notices appear in all copies and that\n" +
        "both the copyright notice and this permission notice appear in\n" +
        "supporting documentation."}, {
        "DLG_ABOUT_MAJOR_CONTRIBUTORS", "Major contributors:"}, {
        "DLG_ABOUT_NAME", "OpenSign"}, {
        "DLG_ABOUT_NAME_DETAILS", "Applet för digitala signaturer och logon"}, {
        "DLG_CERTIFICATEDETAILS_BUTTON_CLOSE", "Stäng"}, {
        "DLG_CERTIFICATEDETAILS_HEADER", "Certifikatdetaljer"}, {
        "DLG_CERTIFICATEDETAILS_LABEL_CERTIFICATESTORE", "Källa"}, {
        "DLG_CERTIFICATEDETAILS_LABEL_CERTIFICATESTORE_PKCS12", "PKCS12-fil"}, {
        "DLG_CERTIFICATEDETAILS_LABEL_CERTIFICATESTORE_CRYPTOKI", "Hardware"}, {
        "DLG_CERTIFICATEDETAILS_LABEL_CERTIFICATESTORE_UNKNOWN", "Okänd"}, {
        "DLG_CERTIFICATEDETAILS_LABEL_DATE_EXPIRY", "Utgång"}, {
        "DLG_CERTIFICATEDETAILS_LABEL_DATE_ISSUANCE", "Utställd"}, {
        "DLG_CERTIFICATEDETAILS_LABEL_ISSUERDN", "Utställare"}, {
        "DLG_CERTIFICATEDETAILS_LABEL_KEYUSAGE", "Nyckelanvändning"}, {
        "DLG_CERTIFICATEDETAILS_LABEL_KEYUSAGE_CRL_SIGN", "CRL-signering"}, {
        "DLG_CERTIFICATEDETAILS_LABEL_KEYUSAGE_DATA_ENCIPHERMENT", "Data-kryptering"}, {
        "DLG_CERTIFICATEDETAILS_LABEL_KEYUSAGE_DIGITAL_SIGNATURE", "Signering"}, {
        "DLG_CERTIFICATEDETAILS_LABEL_KEYUSAGE_KEY_AGREEMENT", "Nyckelutbyte"}, {
        "DLG_CERTIFICATEDETAILS_LABEL_KEYUSAGE_KEY_CERT_SIGN", "Certifikatsignering"}, {
        "DLG_CERTIFICATEDETAILS_LABEL_KEYUSAGE_KEY_ENCIPHERMENT", "Nyckelkryptering"}, {
        "DLG_CERTIFICATEDETAILS_LABEL_KEYUSAGE_NON_REPUDIATION", "Icke-förnekande"}, {
        "DLG_CERTIFICATEDETAILS_LABEL_KEYUSAGE_NOT_AVAILABLE", "ej tillgänglig"}, {
        "DLG_CERTIFICATEDETAILS_LABEL_SERIALNUMBER", "Serienummer"}, {
        "DLG_CERTIFICATEDETAILS_LABEL_SUBJECTDN", "Ämne"}, {
        "DLG_CERTIFICATEDETAILS_LABEL_VERSION", "Version"}, {
        "DLG_CPR_HEADER", "Ange personnummer"}, {
        "DLG_INFO_HEADER", "Info"}, {
        "DLG_ERROR_HEADER", "Fel"}, {
        "DLG_ERROR_TEXT_INVALID_PASSWORD", "Ett fel uppstod. Lösenordet kan vara fel."}, {
        "DLG_ERROR_TEXT_LOAD_FROM_FILE_FAILURE", "Ett fel uppstod när filen laddades. Lösenordet kan vara fel."}, {
        "DLG_ERROR_TEXT_GENERAL_LOAD_FROM_FILE_FAILURE", "Filen tycks inte innehålla ett certifikat eller hardware driver."}, {
        "DLG_INFO_BUTTON_OK", "OK"}, {
        "DLG_INFO_HEADER_BETA", "Viktigt"}, {
        "DLG_INFO_HEADER_CERTIFICATE_LOADED", "Laddning av certifikat"}, {
        "DLG_INFO_HEADER_NO_KEYSTORES_AVAILABLE", "Ingen nyckelcontainer tillgänglig"}, {
        "DLG_INFO_TEXT_BETA", "Detta är en betaversion av OpenOCES-applet. Den bör ej användas för andra ändamål än test."}, {
        "DLG_INFO_TEXT_CERTIFICATE_LOADED", "Certifikatet kan nu användas för signering"}, {
        "DLG_INFO_TEXT_NO_KEYSTORES_AVAILABLE", "Info"}, {
        "DLG_LOADFILE_HEADER", "Välj ett certifikat från fil..."}, {
        "DLG_PASSWORD_BUTTON_CANCEL", "Avbryt"}, {
        "DLG_PASSWORD_BUTTON_OK", "OK"}, {
        "DLG_PASSWORD_HEADER_PREFIX", "Ange lösenord för"}, {
        "DLG_PASSWORD_HEADER_CRYPTOKI", "hardware"}, {
        "DLG_PASSWORD_LABEL_CPR", "SSN"}, {
        "DLG_PASSWORD_LABEL_PASSWORD", "Lösenord"}, {
        "DLG_PASSWORD_LABEL_PIN", "PIN"}, {
        "DLG_PINCODE_HEADER", "PIN-kod för CD-kort"}, {            
        "ERR_CREATE_KEY_DIR", "Kan ej skapa certifikatkatalog på disk"}, {
        "ERR_WRONG_CERTIFICATE", "Detta certifikat kan ej användas för signering här"}, {
        "ERR_WRONG_PASSWORD", "Ogiltigt lösenord"}, {
        "EXPORT","Exportera..."}, {
        "FIND_CERTIFICATE", "Sök certifikat"}, {
        "FUNCTIONS", "Funktioner >>"}, {
        "FUNCTIONS_MODERN", "Hitta certifikat..."}, {
        "FUNCTIONS_MODERN_LOGON", "Bläddra..."}, {
        "HEADER_SIGNTEXT", "Nedan är dokumentet som skall signeras"}, {
        "IMPORT", "Importera..."}, {    
        "KEYSTORE_CDCARD", "Nyckel på CD-kort"}, {
        "KEYSTORE_MS", "Intern nyckelcontainer (CAPI)"}, {
        "KEYSTORE_PKCS12", "Externa nyckelfiler (PKCS12)"}, {
        "LOADING_CERTIFICATES", "Laddar certifikat..."}, {
        "LOGONTO", "Logga in till"}, {
        "LOGON_HOW_TO", "genom att välja ditt certifikat och klicka"}, {
        "LOGON_HOW_TO2", "Välj certifikat och logga in till"}, {
        "NO", "Nej"}, {
        "NO_CERTIFICATES", "Inget certifikat"}, {
        "NO_KEYSTORES", "Ingen typ för nyckelcontainer tillgänglig"}, {
        "OK", "OK"}, {
        "OK_SIGN_MODERN", "Signera"}, {
        "OK_LOGON_MODERN", "Logga in"}, {
        "PASSWORD", "Lösenord"}, {
        "PATH", "Hitta certifikat..."}, {
        "PLEASE_WAIT", "Vänta..."}, {
        "PREFERENCES", "Inställningar..."}, {
        "RESTORE_KEYFILE", "Återställ backup"}, {
        "SEE", "http://it-practice.dk\nhttp://www.portalprotect.dk"}, {
        "SELECT_KEYSTORE","Välj nyckelcontainer..."}, {
        "SOCIAL_SECURITY_NUMBER", "Personnummer"}, {
        "TITLE_LOGON", "X.509 OCES certifikatinloggning"}, {
        "TITLE_SIGN", "OpenOCES digital signatur"}, {
        "USERID", "Användarid"}, {
        "TITLE_SIGN", "Digital signatur"}, {
        "WINDOW_MAIN_BUTTON_CANCEL", "Avbryt"}, {
        "WINDOW_MAIN_BUTTON_CHOOSE_FROM_FILE", "Välj från fil ..."}, {
        "WINDOW_MAIN_BUTTON_DETAILS", "Detaljer ..."}, {
        "WINDOW_MAIN_BUTTON_SIGN", "Signera"}, {
        "WINDOW_MAIN_LABEL_CHOOSE_CERTIFICATE", "Välj certifikat"}, {
        "YES", "Ja"} , {"DLG_CERTIFICATEDETAILS_LABEL_CERTIFICATESTORE_OCES", "Oces file"}
    };
}
