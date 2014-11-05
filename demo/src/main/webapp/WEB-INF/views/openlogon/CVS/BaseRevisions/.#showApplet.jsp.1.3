<%@page contentType="text/html;charset=UTF-8" %>
<%@page pageEncoding="UTF-8" %>
<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <META http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title><fmt:message key="openlogon.applet.title"/></title>
</head>
<body bgcolor="white">
    <applet name="signing_applet" code="org.openoces.opensign.client.applet.bootstrap.BootApplet" width="${openlogon.appletWidth}" height="${openlogon.appletHeight}" codebase="${docBase}" archive="${docBase}/resources/opensign/${openlogon.appletVersion}/OpenSign-bootstrapped.jar?time=${timestamp}" mayscript>
        <param name="ZIP_FILE_ALIAS" value="OpenLogon">
        <param name="ZIP_BASE_URL" value="${docBase}/resources/opensign/${openlogon.appletVersion}/plugins">
        <param name="MS_SUPPORT" value="bcjce">
        <param name="SUN_SUPPORT" value="jsse">
        <param name="STRIP_ZIP" value="yes">
        <c:if test="${not empty plugins}">
            <param name="EXTRA_ZIP_FILE_NAMES" value="${plugins}">
        </c:if>
        <param name="locale" value="${openlogon.appletLocale}">
        <param name="cabbase" value="${docBase}/resources/opensign/${openlogon.appletVersion}/OpenSign-bootstrapped.cab">
        <param name="key.store.directory" value="null">
        <param name="LOG_LEVEL" value="${openlogon.loglevel}">
        <param name="socialsecuritynumber" value="${openlogon.showSsid}">
        <param name="optionalid" value="${openlogon.optionalid}">
        <param name="allowregistryaccess" value="${openlogon.allowregistryaccess}">
        <param name="sortcertificates" value="${openlogon.sortcertificates}">
        <c:if test="${not empty openlogon.logonto}">
            <param name="logonto" value="${openlogon.logonto}">
        </c:if>
        <c:if test="${not empty openlogon.cdkortservice}">
            <param name="cdkortservice" value="${openlogon.cdkortservice}">
        </c:if>
        <c:if test="${not empty openlogon.signproperties}">
            <param name="signproperties" value="${openlogon.signproperties}">
        </c:if>
        <c:if test="${not empty openlogon.signatureAlgorithm and openlogon.signatureAlgorithm ne 'undefined'}">
            <param name="opensign.signature.factory" value="${openlogon.signatureAlgorithm}">
        </c:if>
        <c:if test="${openlogon.virklogon}">
            <param name="VIRK_LOGON" value="virklogon">
        </c:if>
        <c:if test="${openlogon.appletRequestOnMac}">
            <param name="opensign.doappletrequestonmac" value="true">
        </c:if>
        <c:choose>
            <c:when test="${not openlogon.liveconnect or openlogon.appletRequestOnMac}">
                <param name="opensign.verifieruri" value="${contextPath}/servlet/Verifier?id=${random}">
                <param name="opensign.canceluri" value="${contextPath}/display.html?result=cancel">
                <param name="opensign.erroruri" value="${contextPath}/display.html?result=error">
                <param name="opensign.alerturi" value="${contextPath}/display.html?result=alert">
                <param name="opensign.verifiedokuri" value="${contextPath}/display.html?result=ok&id=${random}">
                <param name="opensign.verifiederroruri" value="${contextPath}/display.html?result=error&id=${random}">
            </c:when>
            <c:otherwise>
                <param name="opensign.message.name" value="message">
                <param name="opensign.result.name" value="result">
            </c:otherwise>
        </c:choose>
        <param name="opensign.cookiecount" value="${numberOfCookies}">
        <c:if test="${numberOfCookies > 0}">
            <c:forEach items="${cookies}" var="cookie" varStatus="status">
                <param name="opensign.cookie.${status.index + 1}.name" value="${cookie.name}">
                <param name="opensign.cookie.${status.index + 1}.value" value="${cookie.value}">
            </c:forEach>
        </c:if>
        <param name="opensign.formdata.count" value="2">
        <param name="opensign.formdata.1.name" value="formfield1name">
        <param name="opensign.formdata.1.value" value="formfield1value">
        <param name="opensign.formdata.2.name" value="formfield2name">
        <param name="opensign.formdata.2.value" value="formfield2value">
        <c:if test="${!openlogon.liveconnect}">
            <param name="opensign.doappletrequest" value="true">
        </c:if>
        <c:if test="${not empty openlogon.issuerdnfilter}">
            <param name="issuerdnfilter" value="${openlogon.base64Issuerdnfilter}">
        </c:if>
        <c:if test="${not empty openlogon.subjectdnfilter}">
            <param name="issuerdnfilter" value="${openlogon.base64subjectdnfilter}">
        </c:if>
        <c:if test="${not empty openlogon.sernofilter}">
            <param name="issuerdnfilter" value="${openlogon.base64sernofilter}">
        </c:if>
        <param name="gui" value="modern">
        <c:if test="${openlogon.inputstyle ne 'not-specified'}">
            <param name="inputstyle" value="${openlogon.inputstyle}">
        </c:if>
        <c:if test="${not empty openlogon.logonheader}">
            <param name="LOGON_HEADER" value="${openlogon.logonheader}">
        </c:if>
    </applet>

    <c:if test="${openlogon.liveconnect}">

        <script language="JavaScript">

            function onLogonOK(signature) {
                document.forms[0].message.value=signature;
                document.forms[0].result.value='ok';
                document.forms[0].submit();
            }

            function onLogonCancel() {
                document.forms[0].result.value='cancel';
                document.forms[0].submit();
            }

            function onLogonError(msg) {
                document.forms[0].result.value=msg;
                document.forms[0].submit();
            }
        </script>

        <form:form modelAttribute="openLive" method="POST" action="display.html">
            <form:hidden path="message"/>
            <form:hidden path="result"/>
        </form:form>

    </c:if>

</body>

</html>