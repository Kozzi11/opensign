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

/* $Id: Resources_nl_NL.java,v 1.4 2013/02/08 09:34:05 anmha Exp $ */

package org.openoces.opensign.client.applet.resources;

/**
 * nl,NL specific resource class
 *
 * @author Peter Lind Damkjaer  <pld@tdc.dk>
 * @author Paw F. Kjeldgaard <pakj@danid.dk>
 * @author Anders M. Hansen <consult@ajstemp.dk>
 */


public class Resources_nl_NL extends AbstractListResourceBundle {

    protected Object[][] getContents() {
        return deepCopy(contents);
    }

    private static final Object[][] contents = {
        {
		"LOGONTO", "Login bij" }, {
		"ABOUT", "Over..."}, {
            "ABOUT_DETAILS", "Login en digitale ondertekening met OCES certificaten en PID-CPR ondersteuning"}, {
            "BACKUP_KEYFILE", "Backup sleutelbestand..."}, {
            "CANCEL","Annuleer"}, {
            "CANCEL_LOGON_MODERN", "Annuleer"}, {
            "CERTIFICATE_DETAILS","Certificaat details..."}, {
            "CERTIFICATE_LIST","Certificaat"}, {
            "CERT_EMPLOYEE", "medearbeider"}, {
            "CERT_PERSONAL", "persoonlijk"}, {
            "CONFIRM_DELETE","Sleutelbestand verwijderen?"}, {
            "CUSTOMERID","Klant id"}, {
            "DEFAULT_ERROR_MSG", "Er heeft zich een fout voorgedaan"}, {
            "DELETE_KEYFILE", "Sleutelbestand verwijderen..."}, {
            "DRIVER_NOT_FOUND", "PKCS11 driver not found"}, {
            "INSERT_HARDWARETOKEN", "Insert hardware to read certificates"}, {
            "HARDWARETOKEN_COULD_NOT_INITIALIZE", "Could not initialize hardware"}, {
            "HARDWARETOKEN_CHECKSUM_ERROR_TITLE", "Unknown driver for hardware"}, {
            "HARDWARETOKEN_CHECKSUM_ERROR_MESSAGE", "Could not recognize the found driver.\nAre you sure you want to continue?"}, {
            "POLLER_ADDING_CERTIFICATES", "Adding certificates to list..."}, {
            "DLG_ABOUT_LGPL_NOTICE","This is open source software, placed under the terms of the " +
            "GNU LESSER GENERAL PUBLIC LICENSE.\n"+
            "Please see: http://www.gnu.org/licenses/lgpl.txt or " +
            "opensign.license for details.\n"+
            "Permission to use, copy, modify, and distribute this software\n" +
            "for any purpose and without fee is hereby granted, provided\n" +
            "that the above copyright notices appear in all copies and that\n" +
            "both the copyright notice and this permission notice appear in\n" +
            "supporting documentation." }, {
            "DLG_ABOUT_MAJOR_CONTRIBUTORS","Belangrijke bijdragen van:" }, {
            "DLG_ABOUT_NAME", "OpenSign" }, {
            "DLG_ABOUT_NAME_DETAILS","Applet voor digitale handtekenining en login" }, {
            "DLG_CERTIFICATEDETAILS_BUTTON_CLOSE","Sluiten"}, {
            "DLG_CERTIFICATEDETAILS_HEADER","Certificaat details"}, {
            "DLG_CERTIFICATEDETAILS_LABEL_CERTIFICATESTORE","Bron"}, {
            "DLG_CERTIFICATEDETAILS_LABEL_CERTIFICATESTORE_PKCS12","PKCS12 bestand"}, {
            "DLG_CERTIFICATEDETAILS_LABEL_CERTIFICATESTORE_CRYPTOKI", "Hardware"}, {
            "DLG_CERTIFICATEDETAILS_LABEL_CERTIFICATESTORE_UNKNOWN","Onbekend"}, {
            "DLG_CERTIFICATEDETAILS_LABEL_DATE_EXPIRY","Verloopt"}, {
            "DLG_CERTIFICATEDETAILS_LABEL_DATE_ISSUANCE","Uitgegeven"}, {
            "DLG_CERTIFICATEDETAILS_LABEL_ISSUERDN","Uitgever"}, {
            "DLG_CERTIFICATEDETAILS_LABEL_KEYUSAGE","Sleutel gebruik"}, {
            "DLG_CERTIFICATEDETAILS_LABEL_KEYUSAGE_CRL_SIGN","CRL ondertekening"}, {
            "DLG_CERTIFICATEDETAILS_LABEL_KEYUSAGE_DATA_ENCIPHERMENT","Data codering"}, {
            "DLG_CERTIFICATEDETAILS_LABEL_KEYUSAGE_DIGITAL_SIGNATURE","Digitale handtekening"}, {
            "DLG_CERTIFICATEDETAILS_LABEL_KEYUSAGE_KEY_AGREEMENT","Sleutel overeenkomst"}, {
            "DLG_CERTIFICATEDETAILS_LABEL_KEYUSAGE_KEY_CERT_SIGN","Certificaat ondertekening"}, {
            "DLG_CERTIFICATEDETAILS_LABEL_KEYUSAGE_KEY_ENCIPHERMENT","Sleutel codering"}, {
            "DLG_CERTIFICATEDETAILS_LABEL_KEYUSAGE_NON_REPUDIATION","Onweerlegbaarheid van inhoud"}, {
            "DLG_CERTIFICATEDETAILS_LABEL_KEYUSAGE_NOT_AVAILABLE","Niet beschikbaar"}, {
            "DLG_CERTIFICATEDETAILS_LABEL_SERIALNUMBER","Serienummer"}, {
            "DLG_CERTIFICATEDETAILS_LABEL_SUBJECTDN","Onderwerp"}, {
            "DLG_CERTIFICATEDETAILS_LABEL_VERSION","Versie"}, {
            "DLG_CPR_HEADER", "Geef sofi nummer"}, {
            "DLG_INFO_HEADER", "Info"}, {
            "DLG_ERROR_HEADER","Fout"}, {
            "DLG_ERROR_TEXT_INVALID_PASSWORD","Er heeft zich een fout voorgedaan. Het wachtwoord is mogelijk onjuist"}, {
            "DLG_ERROR_TEXT_LOAD_FROM_FILE_FAILURE","Er heeft zich een fout voorgedaan bij het openen van het bestand. Het wachtwoord is mogelijk onjuist"}, {
            "DLG_INFO_BUTTON_OK","Ok"}, {
            "DLG_INFO_HEADER_BETA","Belangrijke note"}, {
            "DLG_INFO_HEADER_CERTIFICATE_LOADED","Laad certificaat"}, {
            "DLG_INFO_HEADER_NO_KEYSTORES_AVAILABLE","Geen sleutelbestanden aanwezig"},{
            "DLG_INFO_TEXT_BETA", "Dit is een beta versie van een OpenOCES applet. Deze moet niet gebruik worden voor andere doeleinden dan testen"}, {
            "DLG_INFO_TEXT_CERTIFICATE_LOADED","Het certificaat kan nu gebruikt worden om te ondertekenen"}, {
            "DLG_INFO_TEXT_NO_KEYSTORES_AVAILABLE","Info"},{
            "DLG_LOADFILE_HEADER","Kies een certificaat uit een bestand ..."}, {
            "DLG_PASSWORD_BUTTON_CANCEL","Annuleer"}, {
            "DLG_PASSWORD_BUTTON_OK","Ok"}, {
            "DLG_PASSWORD_HEADER_PREFIX","Geef wachtwoord voor"}, {
            "DLG_PASSWORD_HEADER_CRYPTOKI", "hardware"}, {
            "DLG_PASSWORD_LABEL_CPR", "Sofi nummer"}, {
            "DLG_PASSWORD_LABEL_PIN", "PIN-kode"}, {
            "DLG_PASSWORD_LABEL_PASSWORD","Wachtwoord"}, {
            "DLG_PINCODE_HEADER", "PIN-kode voor CD kaart"}, {
            "ERR_CREATE_KEY_DIR", "Certificaat directory kan niet op schijf gecreëerd worden"}, {
            "ERR_WRONG_CERTIFICATE", "Dit certificaat kan hier niet gebruikt worden om te ondertekenen" }, {
            "ERR_WRONG_PASSWORD", "Ongeldig wachtwoord" }, {
            "FIND_CERTIFICATE", "Zoek certificaat"}, {
            "FUNCTIONS", "Functies >>" }, {
            "HEADER_SIGNTEXT", "Hieronder is het dokument dat dient te worden ondertekend"}, {
            "IMPORT", "Inlees..."}, {
            "KEYSTORE_CDCARD", "Sleutelbestanden op CD kaart"}, {
            "KEYSTORE_MS", "Intern Windows sleutelbestand (CAPI)" }, {
 	"KEYSTORE_PKCS12", "Extern sleutelbestand (PKCS12)" }, {
            "LOADING_CERTIFICATES", "Certificaaten worden geladen..."}, {
            "LOGONTO", "Login by"}, {
            "LOGON_HOW_TO", "door u certificaat te selekteren en te klikken"}, {
            "LOGON_HOW_TO2", "Selekteer certificaat en login by"}, {
            "NO", "Nee" }, {
			"NO_CERTIFICATES", "Geen certificaten" }, {
            "NO_KEYSTORES", "Geen sleutelbestand types aanwezig" }, {
			"OK", "OK" }, {
            "OK_LOGON_MODERN", "OK"}, {
            "OK_SIGN_MODERN", "Onderteken"}, {
            "PASSWORD", "Wachtwoord" }, {
            "PATH", "Localiseer certificaat..." }, {
            "RESTORE_KEYFILE", "Backup terugzetten" }, {
            "SELECT_KEYSTORE","Selekteer sleutelbestanden..."}, {
        "SEE", "http://it-practice.dk\nhttp://www.portalprotect.dk" }, {
  	    "SOCIAL_SECURITY_NUMBER", "Sofi Nummer" }, {
	    "TITLE_LOGON", "X.509 OCES certificaat login" }, {
	    "TITLE_SIGN", "OpenOCES digitale handtekening" }, {
  	    "USERID", "Gebruikers id" }, {
            "TITLE_SIGN", "Digitale handtekening"}, {
            "WINDOW_MAIN_BUTTON_CANCEL","Annuleer"},{
            "WINDOW_MAIN_BUTTON_CHOOSE_FROM_FILE","Selecteer uit bestand ..."},{
            "WINDOW_MAIN_BUTTON_DETAILS","Details ..."},{
            "WINDOW_MAIN_BUTTON_SIGN","Onderteken"},{
            "WINDOW_MAIN_LABEL_CHOOSE_CERTIFICATE","Selecteer certificaat"}, {
            "YES","Ja"} , {"DLG_CERTIFICATEDETAILS_LABEL_CERTIFICATESTORE_OCES", "Oces file"}
    };
};
