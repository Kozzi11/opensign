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

/* $Id: Resources_es_ES.java,v 1.6 2013/02/08 10:06:29 anmha Exp $ */

package org.openoces.opensign.client.applet.resources;

/**
 * es,ES specific resource class
 *
 * @author ?  <msahagun@msc.es>
 * @author Enric Granda  <enric.granda@safelayer.com>
 * @author Paw F. Kjeldgaard <pakj@danid.dk>
 * @author Anders M. Hansen <consult@ajstemp.dk>
 */

public class Resources_es_ES extends AbstractListResourceBundle {

    protected Object[][] getContents() {
        return deepCopy(contents);
    }

    private static final Object[][] contents = {
        {
        "ABOUT", "Acerca de..."}, {
        "ABOUT_DETAILS", "Identificación y firma digital con certificados OCES y soporte PID-CPR"}, {
        "BACKUP_KEYFILE", "Hacer copia de seguridad del fichero de claves..."}, {
        "CANCEL", "Cancelar"}, {
        "CANCEL_MODERN", "Cancelar"}, {
        "CANCEL_LOGON_MODERN", "Cancelar"}, {
        "CERT_EMPLOYEE", "empleado"}, {
        "CERT_PERSONAL", "personal"}, {
        "CERTIFICATE_DETAILS", "Detalles del certificado"}, {
        "CERTIFICATE_LIST", "Certificado:"}, {
        "CONFIRM_DELETE", "¿Está seguro de que quiere eliminar el fichero de claves?"}, {
        "CUSTOMERID", "Id de cliente"}, {
        "DEFAULT_ERROR_MSG", "Se ha producido un error"}, {
        "DELETE_KEYFILE", "eliminar el fichero de claves..."}, {
        "DRIVER_NOT_FOUND", "PKCS11 driver not found"}, {
        "INSERT_HARDWARETOKEN", "Insert hardware to read certificates"}, {
        "HARDWARETOKEN_COULD_NOT_INITIALIZE", "Could not initialize hardware"}, {
        "HARDWARETOKEN_CHECKSUM_ERROR_TITLE", "Unknown driver for hardware"}, {
        "HARDWARETOKEN_CHECKSUM_ERROR_MESSAGE", "Could not recognize the found driver.\nAre you sure you want to continue?"}, {
        "POLLER_ADDING_CERTIFICATES", "Adding certificates to list..."}, {
        "DLG_ABOUT_LGPL_NOTICE","Este software es software libre, se encuentra bajo los términos de " +
            "GNU LESSER GENERAL PUBLIC LICENSE.\n"+
            "Lea: http://www.gnu.org/licenses/lgpl.txt o " +
            "opensign.license para conocer más detalles(en inglés).\n"+
            "Está permitido usar, copiar, modificar y distribuir este software\n" +
            "para cualquier propósito sin pagar ninguna tasa por ello, siempre y cuando\n" +
            "este copyright aparezca en todas las copias y que\n" +
            "ambos, el copyright y este aviso sobre los permisos aparezca en la\n" +
            "documentación del proyecto."}, {
        "DLG_ABOUT_MAJOR_CONTRIBUTORS", "Principales colaboradores:"}, {
        "DLG_ABOUT_NAME", "OpenSign"}, {
        "DLG_ABOUT_NAME_DETAILS", "Applet para la firma digital y el control de acceso"}, {
        "DLG_CERTIFICATEDETAILS_BUTTON_CLOSE", "Cerrar"}, {
        "DLG_CERTIFICATEDETAILS_HEADER", "Detalles del certificado"}, {
        "DLG_CERTIFICATEDETAILS_LABEL_CERTIFICATESTORE", "Origen"}, {
        "DLG_CERTIFICATEDETAILS_LABEL_CERTIFICATESTORE_PKCS12", "Fichero PKCS12"}, {
        "DLG_CERTIFICATEDETAILS_LABEL_CERTIFICATESTORE_CRYPTOKI", "Hardware"}, {
        "DLG_CERTIFICATEDETAILS_LABEL_CERTIFICATESTORE_UNKNOWN", "Desconocido"}, {
        "DLG_CERTIFICATEDETAILS_LABEL_DATE_EXPIRY", "Válido hasta"}, {
        "DLG_CERTIFICATEDETAILS_LABEL_DATE_ISSUANCE", "Válido desde"}, {
        "DLG_CERTIFICATEDETAILS_LABEL_ISSUERDN", "Emisor"}, {
        "DLG_CERTIFICATEDETAILS_LABEL_KEYUSAGE", "Usos válidos"}, {
        "DLG_CERTIFICATEDETAILS_LABEL_KEYUSAGE_CRL_SIGN", "Firma de CRL"}, {
        "DLG_CERTIFICATEDETAILS_LABEL_KEYUSAGE_DATA_ENCIPHERMENT", "Cifrado de datos"}, {
        "DLG_CERTIFICATEDETAILS_LABEL_KEYUSAGE_DIGITAL_SIGNATURE", "Firma digital"}, {
        "DLG_CERTIFICATEDETAILS_LABEL_KEYUSAGE_KEY_AGREEMENT", "Intercambio de claves"}, {
        "DLG_CERTIFICATEDETAILS_LABEL_KEYUSAGE_KEY_CERT_SIGN", "Firma de certificados"}, {
        "DLG_CERTIFICATEDETAILS_LABEL_KEYUSAGE_KEY_ENCIPHERMENT", "Cifrado de claves"}, {
        "DLG_CERTIFICATEDETAILS_LABEL_KEYUSAGE_NON_REPUDIATION", "No repudio"}, {
        "DLG_CERTIFICATEDETAILS_LABEL_KEYUSAGE_NOT_AVAILABLE", "No disponible"}, {
        "DLG_CERTIFICATEDETAILS_LABEL_SERIALNUMBER", "Número de serie"}, {
        "DLG_CERTIFICATEDETAILS_LABEL_SUBJECTDN", "Titular"}, {
        "DLG_CERTIFICATEDETAILS_LABEL_VERSION", "Versión"},  {
	    "DLG_CPR_HEADER", "Introduzca el número de la Seguridad Social"}, {
        "DLG_INFO_HEADER", "Info"}, {
	    "DLG_ERROR_HEADER", "Error"}, {
        "DLG_ERROR_TEXT_INVALID_PASSWORD", "Se ha producido un error. La contraseña podría ser incorrecta"}, {
        "DLG_ERROR_TEXT_LOAD_FROM_FILE_FAILURE", "Se ha producido un error durante la carga del fichero. La contraseña podría ser incorrecta"}, {
        "DLG_ERROR_TEXT_GENERAL_LOAD_FROM_FILE_FAILURE", "El fichero no ha sido reconocido como un certificado o como un driver para hardware."}, {
        "DLG_INFO_BUTTON_OK", "Aceptar"}, {
        "DLG_INFO_HEADER_BETA", "Nota importante"}, {
        "DLG_INFO_HEADER_CERTIFICATE_LOADED", "Certificado cargado"}, {
        "DLG_INFO_HEADER_NO_KEYSTORES_AVAILABLE", "No hay almacenes de claves disponibles"}, {
        "DLG_INFO_TEXT_BETA", "Ésta es una versión beta del applet OpenOces. No debería usarse para otros fines que no sean el testeo"}, {
        "DLG_INFO_TEXT_CERTIFICATE_LOADED", "El certificado es válido para firmar"}, {
        "DLG_INFO_TEXT_NO_KEYSTORES_AVAILABLE", "Información"}, {
        "DLG_LOADFILE_HEADER", "Seleccionar un certificado desde un fichero..."}, {
        "DLG_PASSWORD_BUTTON_CANCEL", "Cancelar"}, {
        "DLG_PASSWORD_BUTTON_OK", "Aceptar"}, {
        "DLG_PASSWORD_HEADER_PREFIX", "Introducir contraseña para"}, {
        "DLG_PASSWORD_HEADER_CRYPTOKI", "hardware"}, {
	    "DLG_PASSWORD_LABEL_CPR", "SSN"}, {
	    "DLG_PASSWORD_LABEL_PASSWORD", "Contraseña"}, {
	"DLG_PASSWORD_LABEL_PIN", "PIN"}, {
	"DLG_PINCODE_HEADER", "Código PIN para la tarjeta CD"}, {
	"ERR_CREATE_KEY_DIR", "No se ha podido crear el directorio del certificado"}, {
        "ERR_WRONG_CERTIFICATE", "Este certificado no es válido para la firma electrónica"}, {
        "ERR_WRONG_PASSWORD", "Contraseña no válida"}, {
        "EXPORT", "Exportar..." }, {
        "FIND_CERTIFICATE", "Seleccionar certificado"}, {
        "FUNCTIONS", "Funciones >>"}, {
        "FUNCTIONS_MODERN", "Seleccione certificado..."}, {
        "FUNCTIONS_MODERN_LOGON", "Examinar..."}, {
        "HEADER_SIGNTEXT", "El documento a firmar es el siguiente:"}, {
        "IMPORT", "Importar..." }, {
        "KEYSTORE_CDCARD", "Clave de la tarjeta CD"}, {
        "KEYSTORE_MS", "Almacén interno de claves (CAPI)"}, {
        "KEYSTORE_PKCS12", "Certificados externos (PKCS12)"}, {
        "LOADING_CERTIFICATES", "Se están cargando los certificados..."}, {
        "LOGONTO", "Entrar en"}, {
        "LOGON_HOW_TO", "seleccionando su certificado y haciendo clic"}, {
        "LOGON_HOW_TO2", "Seleccione su certificado y entre en"}, {
        "NO", "No"}, {
        "NO_CERTIFICATES", "No hay certificados"}, {
        "NO_KEYSTORES", "No hay almacenes de claves disponibles"}, {
        "OK", "Aceptar"}, {
        "OK_SIGN_MODERN", "Firmar"}, {
        "OK_LOGON_MODERN", "Entrar"}, {
        "PASSWORD", "Contraseña"}, {
        "PATH", "Seleccione certificado..."}, {
	    "PLEASE_WAIT", "Por favor, espere..."},{
        "PREFERENCES", "Preferencias..." }, {
        "RESTORE_KEYFILE", "Restaurar copia de seguridad del fichero de claves"}, {
        "SEE", "http://it-practice.dk\nhttp://www.portalprotect.dk"}, {
        "SELECT_KEYSTORE", "Seleccionar almacén de claves..." }, {
        "SOCIAL_SECURITY_NUMBER", "Número de la Seguridad Social"}, {
        "TITLE_LOGON", "Certificado X.509 OCES para identificarse"}, {
        "TITLE_SIGN", "OpenOCES firma digital"}, {
        "USERID", "Id de usuario"}, {
        "TITLE_SIGN", "Firma Digital"}, {
        "WINDOW_MAIN_BUTTON_CANCEL", "Cancelar"}, {
        "WINDOW_MAIN_BUTTON_CHOOSE_FROM_FILE", "Seleccionar desde fichero ..."}, {
        "WINDOW_MAIN_BUTTON_DETAILS", "Detalles ..."}, {
        "WINDOW_MAIN_BUTTON_SIGN", "Firmar"}, {
        "WINDOW_MAIN_LABEL_CHOOSE_CERTIFICATE", "Seleccionar el certificado"}, {
        "YES", "Sí"} , {"DLG_CERTIFICATEDETAILS_LABEL_CERTIFICATESTORE_OCES", "Oces file"}
    };
}
