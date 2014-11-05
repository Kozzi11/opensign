<%@page contentType="text/html;charset=UTF-8" %>
<%@page pageEncoding="UTF-8" %>
<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <META http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title><fmt:message key="openexport.form.title"/></title>
</head>
<body bgcolor="white">

<h2><fmt:message key="openexport.form.title"/></h2>

<form:form modelAttribute="openexport" method="post" action="entrance_openexport.html" >

<table>
<tr>
<td>
<table>
    <tr>
        <td><fmt:message key="form.applet.version.label"/>:</td>
        <td align="right">
            <form:select id="selectappletversion" path="appletVersion">
                <form:option value="v2.1.7">v2.1.7</form:option>
                <form:option value="v2.1.9">v2.1.9</form:option>
                <form:option value="unstable"><fmt:message key="form.applet.version.unstable.label"/></form:option>
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
        <td><fmt:message key="form.applet.keystores.label"/></td>
        <td align="right">
            capi&nbsp;<form:checkbox path="keystores" value="capi"/><br/>
            pkcs12&nbsp;<form:checkbox path="keystores" value="pkcs12"/><br/>
            OCES .html keystore&nbsp;<form:checkbox path="keystores" value="oces"/><br/>
            cdcard&nbsp;<form:checkbox path="keystores" value="cdcard"/><br/>
            cryptoki&nbsp;<form:checkbox path="keystores" value="cryptoki"/>
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
        <td><fmt:message key="form.applet.sort.label"/></td>
        <td align="right">
            <form:radiobutton path="sortcertificates" value="true"/><fmt:message key="form.applet.true"/>&nbsp;
            <form:radiobutton path="sortcertificates" value="false"/><fmt:message key="form.applet.false"/>
        </td>
    </tr>
</table>
</td>
</tr>
<tr>
    <td align="right">
        <button id="continuebutton" onclick="document.forms[0].submit();"><fmt:message key="form.applet.submit.button.label"/></button>
    </td>
</tr>
</table>
</form:form>


</body>
</html>
