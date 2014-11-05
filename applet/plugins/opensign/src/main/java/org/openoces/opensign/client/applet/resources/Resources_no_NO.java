/*
    Copyright 2006 IT Practice A/S
    Copyright 2006 TDC TotallÃžsninger A/S
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

/* $Id: Resources_no_NO.java,v 1.5 2013/02/08 10:00:49 anmha Exp $ */

package org.openoces.opensign.client.applet.resources;

/**
 * no,NO specific resource class
 *
 * @author Jens Bo Friis  <jbf@it-practice.dk>
 * @author Carsten Raskgaard  <carsten@raskgaard.dk>
 * @author Preben Rosendal Valeur  <prv@tdc.dk>
 * @author Tommy Kristiansen <tommy@commfides.com>
 * @author Paw F. Kjeldgaard <pakj@danid.dk>
 * @author Anders M. Hansen <consult@ajstemp.dk>
 */


public class Resources_no_NO extends AbstractListResourceBundle {

    protected Object[][] getContents() {
        return deepCopy(contents);
    }

    private static final Object[][] contents = {
        {
        		"ABOUT", "Om..."}, {
                "ABOUT_DETAILS", "Logon and digital signature with OCES certificates and PID-CPR support"}, {
                "ATTACHMENT_LIST", " Vedlegg..."},{
                "ATTACHMENT_SAVE", "Lagre..."},{
                "ATTACHMENT_VIEW", "Vis..."},{
                "BACKUP_KEYFILE", "Sikkerhetskopi av nøkkel fil..."}, {
                "CANCEL", "Avbryt"}, {
                "CANCEL_MODERN", "Avbryt"}, {
                "CANCEL_LOGON_MODERN", "Avbryt"}, {
                "CERT_EMPLOYEE", "Ansatt"}, {
                "CERT_PERSONAL", "Personlig"}, {
                "CERTIFICATE_DETAILS", "Sertifikat detaljer..."}, {
                "CERTIFICATE_EXPIRED", "Utgått"}, {            
                "CERTIFICATE_LIST", "Sertifikat"}, {
                "CONFIRM_ACCEPT_WRONG_CHECKSUM", "Feil sjekksum - fortsette uansett?"},{    
                "CONFIRM_DELETE", "Slette nøkkel fil?"}, {
                "CUSTOMERID", "Kundenummer"}, {
                "DEFAULT_ERROR_MSG", "En feil har oppstått"}, {
                "DELETE_KEYFILE", "Slette nøkkel fil..."}, {
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
                "DLG_ABOUT_MAJOR_CONTRIBUTORS", "Store bidragsytere :"}, {
                "DLG_ABOUT_NAME", "OpenSign"}, {
                "DLG_ABOUT_NAME_DETAILS", "Applet for digital signatur og logon"}, {
                "DLG_CERTIFICATEDETAILS_BUTTON_CLOSE", "Lukk"}, {
                "DLG_CERTIFICATEDETAILS_HEADER", "Sertifikat detaljer"}, {
                "DLG_CERTIFICATEDETAILS_LABEL_CERTIFICATESTORE", "Sertifikatlager"}, {
                "DLG_CERTIFICATEDETAILS_LABEL_CERTIFICATESTORE_PKCS12", "PKCS12 fil"}, {
                "DLG_CERTIFICATEDETAILS_LABEL_CERTIFICATESTORE_CRYPTOKI", "Hardware"}, {
                "DLG_CERTIFICATEDETAILS_LABEL_CERTIFICATESTORE_UNKNOWN", "Ukjent"}, {
                "DLG_CERTIFICATEDETAILS_LABEL_DATE_EXPIRY", "Gyldig til"}, {
                "DLG_CERTIFICATEDETAILS_LABEL_DATE_ISSUANCE", "Utstedt"}, {
                "DLG_CERTIFICATEDETAILS_LABEL_ISSUERDN", "Utsteder"}, {
                "DLG_CERTIFICATEDETAILS_LABEL_KEYUSAGE", "Bruk av nøkler"}, {
                "DLG_CERTIFICATEDETAILS_LABEL_KEYUSAGE_CRL_SIGN", "CRL signing"}, {
                "DLG_CERTIFICATEDETAILS_LABEL_KEYUSAGE_DATA_ENCIPHERMENT", "Datachiffrering"}, {
                "DLG_CERTIFICATEDETAILS_LABEL_KEYUSAGE_DIGITAL_SIGNATURE", "Digital signatur"}, {
                "DLG_CERTIFICATEDETAILS_LABEL_KEYUSAGE_KEY_AGREEMENT", "Nøkkel utveksling"}, {
                "DLG_CERTIFICATEDETAILS_LABEL_KEYUSAGE_KEY_CERT_SIGN", "Sertifikat signering"}, {
                "DLG_CERTIFICATEDETAILS_LABEL_KEYUSAGE_KEY_ENCIPHERMENT", "Nøkkelchiffrering"}, {
                "DLG_CERTIFICATEDETAILS_LABEL_KEYUSAGE_NON_REPUDIATION", "Ikke-avvisning"}, {
                "DLG_CERTIFICATEDETAILS_LABEL_KEYUSAGE_NOT_AVAILABLE", "ikke tilgjenglig"}, {
                "DLG_CERTIFICATEDETAILS_LABEL_SERIALNUMBER", "Serienummer"}, {
                "DLG_CERTIFICATEDETAILS_LABEL_SUBJECTDN", "Emne"}, {
                "DLG_CERTIFICATEDETAILS_LABEL_VERSION", "Versjon"}, {
                "DLG_CPR_HEADER", "Tast inn fødselsnummeret"}, {
                "DLG_INFO_HEADER", "Info"}, {
                "DLG_ERROR_HEADER", "Feil"}, {
                "DLG_ERROR_TEXT_INVALID_PASSWORD", "En feil har oppstått. Passordet kan være ugyldig"}, {
                "DLG_ERROR_TEXT_LOAD_FROM_FILE_FAILURE", "En feil har oppstått ved lesing av filen. Passordet kan være ugyldig"}, {
                "DLG_ERROR_TEXT_GENERAL_LOAD_FROM_FILE_FAILURE", "Filen var ikke gjenkjent som en sertifikat fil eller hardware driver."}, {
                "DLG_INFO_BUTTON_OK", "OK"}, {
                "DLG_INFO_HEADER_BETA", "Viktig info"}, {
                "DLG_INFO_HEADER_CERTIFICATE_LOADED", "Sertifikat innlesing"}, {
                "DLG_INFO_HEADER_NO_KEYSTORES_AVAILABLE", "Ingen nøkkellager tilgjenglig"}, {
                "DLG_INFO_TEXT_BETA", "Dette er en beta versjon av OpenOCES applet. Den skal ikke benyttes til noe annet en testing"}, {
                "DLG_INFO_TEXT_CERTIFICATE_LOADED", "Sertifikatet kan nå benyttes til signering"}, {
                "DLG_INFO_TEXT_NO_KEYSTORES_AVAILABLE", "Info"}, {
                "DLG_LOADFILE_HEADER", "Velg sertifikat fra fil ..."}, {
                "DLG_PASSWORD_BUTTON_CANCEL", "Avbryt"}, {
                "DLG_PASSWORD_BUTTON_OK", "OK"}, {
                "DLG_PASSWORD_HEADER_PREFIX", "Tast inn passord for"}, {
                "DLG_PASSWORD_HEADER_CRYPTOKI", "hardware"}, {
                "DLG_PASSWORD_LABEL_CPR", "FNR"}, {
                "DLG_PASSWORD_LABEL_PASSWORD", "Passord"}, {
                "DLG_PASSWORD_LABEL_PIN", "PIN"}, {
                "DLG_PINCODE_HEADER", "PIN kode for CD kort"}, {            
                "ERR_CREATE_KEY_DIR", "Kan ikke opprette sertifikat katalog på lagringsenheten"}, {
                "ERR_WRONG_CERTIFICATE", "Sertifikatet kan ikke benyttes for signering her"}, {
                "ERR_WRONG_PASSWORD", "Ugyldig passord"}, {
                "EXPORT","Eksporter..."}, {
                "FIND_CERTIFICATE", "Finn sertifikat"}, {
                "FUNCTIONS", "Funksjoner  >>"}, {
                "FUNCTIONS_MODERN", "Finn sertifikatet..."}, {
                "FUNCTIONS_MODERN_LOGON", "Bla igjennom..."}, {
                "HEADER_SIGNTEXT", "Under finner du dokumentet som skal signeres"}, {
                "IMPORT", "Importer..."}, {    
                "KEYSTORE_CDCARD", "Nøkkel på CD kort"}, {
                "KEYSTORE_MS", "Internt nøkkellager (CAPI)"}, {
                "KEYSTORE_PKCS12", "Eksterne nøkkel filer (PKCS12)"}, {
                "LOADING_CERTIFICATES", "Laster inn sertifikatene..."}, {
                "LOGONTO", "Logg på"}, {
                "LOGON_HOW_TO", "ved å velge sertifikat og klikk"}, {
                "LOGON_HOW_TO2", "Velg sertifikat og logg deg på"}, {
                "NO", "Nei"}, {
                "NO_CERTIFICATES", "Ingen sertifikater"}, {
                "NO_KEYSTORES", "Ingen tilgjenglige nøkkellager"}, {
                "OK", "OK"}, {
                "OK_SIGN_MODERN", "Signer"}, {
                "OK_LOGON_MODERN", "Logg inn"}, {
                "PASSWORD", "Passord"}, {
                "PATH", "Finn sertifikat..."}, {
                "PLEASE_WAIT", "Vennligst vent..."}, {
                "PREFERENCES", "Innstillinger..."}, {
                "RESTORE_KEYFILE", "Gjenopprett sikkerhetskopi"}, {
                "SEE", "http://it-practice.dk\nhttp://www.portalprotect.dk"}, {
                "SELECT_KEYSTORE","Velg nøkkellager..."}, {
                "SOCIAL_SECURITY_NUMBER", "Fødselsnummer"}, {
                "TITLE_LOGON", "X.509 OCES sertifikat inn logging"}, {
                "TITLE_SIGN", "OpenOCES digital signatur"}, {
                "USERID", "Bruker id"}, {
                "TITLE_SIGN", "Digital signatur"}, {
                "WINDOW_MAIN_BUTTON_CANCEL", "Avbryt"}, {
                "WINDOW_MAIN_BUTTON_CHOOSE_FROM_FILE", "Velg fra fil ..."}, {
                "WINDOW_MAIN_BUTTON_DETAILS", "Detaljer ..."}, {
                "WINDOW_MAIN_BUTTON_SIGN", "Signer"}, {
                "WINDOW_MAIN_LABEL_CHOOSE_CERTIFICATE", "Velg sertifikat"}, {
                "YES", "Ja"} , {"DLG_CERTIFICATEDETAILS_LABEL_CERTIFICATESTORE_OCES", "Oces file"}

    };
}
