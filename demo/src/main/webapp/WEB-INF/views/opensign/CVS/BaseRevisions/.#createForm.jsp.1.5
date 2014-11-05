<%@page contentType="text/html;charset=UTF-8" %>
<%@page pageEncoding="UTF-8" %>
<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sql_rt" uri="http://java.sun.com/jstl/sql_rt" %>
<html>
<head>
    <META http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title><fmt:message key="openlogon.form.title"/></title>
    <script language="JavaScript" src="resources/js/jquery.min.js"></script>
    <script language="JavaScript">

		if(typeof String.prototype.trim !== 'function') { 
		  String.prototype.trim = function() { 
		    return this.replace(/^\s+|\s+$/g, '');  
		  } 
		} 

        function validate() {
            var format = $("#format option:selected").val();

            if(format != "PDF") {
                var agreement = $("#agreement").val();
                if (agreement.trim().length == 0) {
                    alert('<fmt:message key="form.applet.agreement.missing.text"/>');
                    return false;
                }

                var xslt = $("#xslt").val();
                if (format == "XML" && xslt.trim().length == 0) {
                    alert('<fmt:message key="form.applet.xslt.missing.text"/>');
                    return false;
                }
            }
            return true;
        }

        function addAttachment() {
            $("#event").val("addAttachment");
            document.forms[0].submit();
        }

        function showApplet() {
            if (validate()) {
                $("#event").val("showApplet");
                document.forms[0].submit();
            }
        }

        function setupAgreementInput() {
            var format = $("#format option:selected").val();
            if (format == "XML") {
                $("#xml-info").show();
                $("#agreement-info").show();
                $("#agreement-pdf").hide();
            } else if(format == "PDF") {
                $("#agreement-info").hide();
                $("#agreement-pdf").show();
                $("#xml-info").hide();
            } else {
                $("#xml-info").hide();
                $("#agreement-pdf").hide();
                $("#agreement-info").show();
            }
        }

        function setupForm() {
            setupAgreementInput();
        }

    </script>

</head>
<body bgcolor="white" onload="setupForm();">

<h2><fmt:message key="opensign.form.title"/></h2>

<form:form modelAttribute="opensign" method="post" action="entrance_opensign.html" enctype="multipart/form-data">
<form:hidden path="event" id="event"/>
<div style="color: red;"> <form:errors path="*"  /></div>
<table>
<tr>
<td>
<table>
<tr>
    <td>
        <fmt:message key="form.applet.agreement.format.label"/>
    </td>
    <td align="right">
        <form:select id="format" path="format" onchange="setupAgreementInput();">
            <form:option value="PLAIN">PLAIN</form:option>
            <form:option value="HTML">HTML</form:option>
            <form:option value="XML">XML</form:option>
            <form:option value="PDF">PDF</form:option>
        </form:select>
    </td>
</tr>
<tr>
    <td colspan="2">
        <div id="agreement-info">
            <fieldset>
                <legend><fmt:message key="form.applet.agreement.text"/></legend>
                <form:textarea id="agreement" path="agreement" cols="80" rows="10"/>
            </fieldset>
        </div>
        <div id="agreement-pdf">
            <fieldset>
                <legend><fmt:message key="form.applet.agreement.pdf.text"/></legend>
                <c:if test="${opensign.pdf != null}">
                    ${opensign.pdf.name}<br/>
                </c:if>
                <form:input path="agreementPdfFileData" type="file"/>
            </fieldset>
        </div>
    </td>
</tr>
<tr>
    <td colspan="2">
        <div id="xml-info">
            <fieldset>
                <legend><fmt:message key="form.applet.xslt.text"/></legend>
                <table width="100%">
                    <tr>
                        <td colspan="2"><form:textarea id="xslt" path="xslt" cols="80" rows="10"/></td>
                    </tr>
                    <tr>
                        <td><fmt:message key="form.applet.xslt.id.label"/></td>
                        <td align="right"><form:input path="xsltId"/></td>
                    </tr>
                </table>
            </fieldset>
        </div>
    </td>
</tr>
<tr>
    <td><fmt:message key="form.applet.version.label"/>:</td>
    <td align="right">
        <form:select path="appletVersion">
            <form:option value="v1.6.7">v1.6.7</form:option>
            <form:option value="v1.6.9">v1.6.9</form:option>
            <form:option value="v1.8.1">v1.8.1</form:option>
            <form:option value="v2.0.0">v2.0.0</form:option>
            <form:option value="v2.1.7">v2.1.7</form:option>
            <form:option value="v2.1.9">v2.1.9</form:option>
            <form:option value="unstable"><fmt:message
                    key="form.applet.version.unstable.label"/></form:option>
        </form:select>
    </td>
</tr>
<tr>
    <td><fmt:message key="form.applet.width.label"/>:</td>
    <td align="right">
        <form:select path="appletWidth">
            <form:option value="100">100</form:option>
            <form:option value="200">200</form:option>
            <form:option value="300">300</form:option>
            <form:option value="400">400</form:option>
            <form:option value="500">500</form:option>
            <form:option value="600">600</form:option>
            <form:option value="700">700</form:option>
            <form:option value="800">800</form:option>
            <form:option value="900">900</form:option>
            <form:option value="1000">1000</form:option>
        </form:select>
    </td>
</tr>
<tr>
    <td><fmt:message key="form.applet.height.label"/>:</td>
    <td align="right">
        <form:select path="appletHeight">
            <form:option value="100">100</form:option>
            <form:option value="200">200</form:option>
            <form:option value="300">300</form:option>
            <form:option value="400">400</form:option>
            <form:option value="500">500</form:option>
            <form:option value="600">600</form:option>
            <form:option value="700">700</form:option>
            <form:option value="800">800</form:option>
            <form:option value="900">900</form:option>
            <form:option value="1000">1000</form:option>
        </form:select>
    </td>
</tr>
<tr>
    <td><fmt:message key="form.applet.logonto.label"/></td>
    <td align="right">
        <form:input path="logonto"/>
    </td>
</tr>
<tr>
    <td><fmt:message key="form.applet.sign.properties.label"/></td>
    <td align="right">
        <form:input path="signproperties"/>
    </td>
</tr>
<tr>
    <td><fmt:message key="form.applet.cdkortservice.label"/></td>
    <td align="right">
        <form:input path="cdkortservice"/>
    </td>
</tr>
<tr>
    <td><fmt:message key="form.applet.locale.label"/>:</td>
    <td align="right">
        <form:select path="appletLocale">
            <form:option value="en,US"><fmt:message key="form.applet.locale.en.us"/></form:option>
            <form:option value="da,DK"><fmt:message key="form.applet.locale.da.dk"/></form:option>
            <form:option value="ca,ES"><fmt:message key="form.applet.locale.ca.es"/></form:option>
            <form:option value="es,ES"><fmt:message key="form.applet.locale.es.es"/></form:option>
            <form:option value="nl,NL"><fmt:message key="form.applet.locale.nl.nl"/></form:option>
            <form:option value="no,NO"><fmt:message key="form.applet.locale.no.no"/></form:option>
            <form:option value="nn,NO"><fmt:message key="form.applet.locale.nn.no"/></form:option>
            <form:option value="sv,SE"><fmt:message key="form.applet.locale.sv.se"/></form:option>
        </form:select>
    </td>
</tr>
<tr>
    <td><fmt:message key="form.applet.ssid.label"/> :</td>
    <td align="right">
        <form:radiobutton path="showSsid" value="true"/><fmt:message key="form.applet.true"/>&nbsp;
        <form:radiobutton path="showSsid" value="false"/><fmt:message key="form.applet.false"/>
    </td>
</tr>
<tr>
    <td><fmt:message key="form.applet.optional.id.label"/>:</td>
    <td align="right">
        <form:select path="optionalid">
            <form:option value="no"><fmt:message key="form.applet.optional.id.no.label"/></form:option>
            <form:option value="CUSTOMERID"><fmt:message
                    key="form.applet.optional.id.customerid.label"/></form:option>
            <form:option value="USERID"><fmt:message
                    key="form.applet.optional.id.userid.label"/></form:option>
        </form:select>
    </td>
</tr>
<tr>
    <td><fmt:message key="form.applet.log.label"/>:</td>
    <td align="right">
        <form:select path="loglevel">
            <form:option value="debug"><fmt:message key="form.applet.debug.label"/></form:option>
            <form:option value="info"><fmt:message key="form.applet.info.label"/></form:option>
            <form:option value="warn"><fmt:message key="form.applet.warn.label"/></form:option>
            <form:option value="error"><fmt:message key="form.applet.error.label"/></form:option>
            <form:option value="fatal"><fmt:message key="form.applet.fatal.label"/></form:option>
        </form:select>
    </td>
</tr>
<tr>
    <td><fmt:message key="form.applet.subject.dn.filter.label"/></td>
    <td align="right">
        <form:input path="subjectdnfilter"/>
    </td>
</tr>
<tr>
    <td><fmt:message key="form.applet.issuer.dn.filter.label"/></td>
    <td align="right">
        <form:input path="issuerdnfilter"/>
    </td>
</tr>
<tr>
    <td><fmt:message key="form.applet.serial.number.filter.label"/></td>
    <td align="right">
        <form:input path="sernofilter"/>
    </td>
</tr>
<tr>
    <td><fmt:message key="form.applet.live.connect.label"/>:</td>
    <td align="right">
        <form:radiobutton path="liveconnect" value="true"/><fmt:message key="form.applet.true"/>&nbsp;
        <form:radiobutton path="liveconnect" value="false"/><fmt:message key="form.applet.false"/>
    </td>
</tr>
<tr>
    <td><fmt:message key="form.applet.applet.request.on.mac.label"/>:</td>
    <td align="right">
        <form:radiobutton path="appletRequestOnMac" value="true"/><fmt:message key="form.applet.true"/>&nbsp;
        <form:radiobutton path="appletRequestOnMac" value="false"/><fmt:message key="form.applet.false"/>
    </td>
</tr>
<hr/>
<tr>
    <td valign="top"><fmt:message key="form.applet.keystores.label"/></td>
    <td align="right">
        capi&nbsp;<form:checkbox path="keystores" value="capi"/><br/>
        pkcs12&nbsp;<form:checkbox path="keystores" value="pkcs12"/><br/>
        OCES .html keystore&nbsp;<form:checkbox path="keystores" value="oces"/><br/>
        cdcard&nbsp;<form:checkbox path="keystores" value="cdcard"/><br/>
        cryptoki&nbsp;<form:checkbox path="keystores" value="cryptoki"/>
    </td>
</tr>
<tr>
    <td><fmt:message key="form.applet.algorithm.method.label"/></td>
    <td align="right">
        <form:select path="signatureAlgorithm">
            <form:option value="org.openoces.opensign.crypto.DefaultSignatureAlgorithm"><fmt:message
                    key="form.applet.algorithm.method.default.label"/></form:option>
            <form:option value="org.openoces.opensign.crypto.OcesSignatureAlgorithm"><fmt:message
                    key="form.applet.algorithm.method.oces.label"/></form:option>
            <form:option value="undefined"><fmt:message
                    key="form.applet.algorithm.method.undefined.label"/></form:option>
        </form:select>
    </td>
</tr>
<tr>
    <td><fmt:message key="form.applet.virk.label"/></td>
    <td align="right">
        <form:radiobutton path="virklogon" value="true"/><fmt:message key="form.applet.true"/>&nbsp;
        <form:radiobutton path="virklogon" value="false"/><fmt:message key="form.applet.false"/>
    </td>
</tr>
<tr>
    <td><fmt:message key="form.applet.inputstyle.label"/></td>
    <td align="right">
        <form:select path="inputstyle">
            <form:option value="default"><fmt:message
                    key="form.applet.inputstyle.default.label"/></form:option>
            <form:option value="virk"><fmt:message key="form.applet.inputstyle.virk.label"/></form:option>
            <form:option value="ver_1.0"><fmt:message
                    key="form.applet.inputstyle.ver_1.0.label"/></form:option>
            <form:option value="not-specified"><fmt:message
                    key="form.applet.inputstyle.not-specified.label"/></form:option>
        </form:select>
    </td>
</tr>
<tr>
    <td><fmt:message key="form.applet.windows.registry.label"/></td>
    <td align="right">
        <form:radiobutton path="allowregistryaccess" value="true"/><fmt:message key="form.applet.true"/>&nbsp;
        <form:radiobutton path="allowregistryaccess" value="false"/><fmt:message key="form.applet.false"/>
    </td>
</tr>
<tr>
    <td><fmt:message key="form.applet.sort.label"/></td>
    <td align="right">
        <form:radiobutton path="sortcertificates" value="true"/><fmt:message key="form.applet.true"/>&nbsp;
        <form:radiobutton path="sortcertificates" value="false"/><fmt:message key="form.applet.false"/>
    </td>
</tr>
<tr>
    <td><fmt:message key="form.applet.logon.header.label"/></td>
    <td align="right">
        <form:input path="logonheader"/>
    </td>
</tr>
</table>
</td>
</tr>
<tr>
    <td>
        <fieldset>
            <legend><fmt:message key="form.applet.file.attachment.label"/></legend>
            <table width="100%">
                <thead>
                <tr>
                    <th>Title</th>
                    <th>Mime type</th>
                    <th>Size</th>
                    <th>Valid Checksum?</th>
                    <th>Is optional?</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${opensign.attachments}" var="attachment" varStatus="loop">
                    <tr>
                        <td>${attachment.name}</td>
                        <td>${attachment.contentType}</td>
                        <td>${attachment.size}</td>
                        <td>
                            <form:radiobutton path="attachments[${loop.index}].validChecksum" value="true"/><fmt:message
                                key="form.applet.true"/>&nbsp;
                            <form:radiobutton path="attachments[${loop.index}].validChecksum"
                                              value="false"/><fmt:message key="form.applet.false"/>
                        </td>
                        <td>
                            <form:radiobutton path="attachments[${loop.index}].optional" value="true"/><fmt:message
                                key="form.applet.true"/>&nbsp;
                            <form:radiobutton path="attachments[${loop.index}].optional" value="false"/><fmt:message
                                key="form.applet.false"/>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
                <tfoot>
                <tr>
                    <td colspan="5">
                        <form:input path="fileData" id="fileAttachment" type="file" onchange="addAttachment();"/>
                    </td>
                </tr>
                </tfoot>
            </table>
        </fieldset>

    </td>
</tr>
<tr>
    <td align="right">
        <button onclick="showApplet();"><fmt:message key="form.applet.submit.button.label"/></button>
    </td>
</tr>

</table>


</form:form>


</body>
</html>
