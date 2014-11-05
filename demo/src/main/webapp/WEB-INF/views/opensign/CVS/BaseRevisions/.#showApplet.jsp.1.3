<%@page contentType="text/html;charset=UTF-8" %>
<%@page pageEncoding="UTF-8" %>
<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <META http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title><fmt:message key="opensign.applet.title"/></title>
</head>
<body bgcolor="white">
<applet name="signing_applet" code="org.openoces.opensign.client.applet.bootstrap.BootApplet" width="${opensign.appletWidth}" height="${opensign.appletHeight}" codebase="${codeBase}" archive="${codeBase}/resources/opensign/${opensign.appletVersion}/OpenSign-bootstrapped.jar?time=${timestamp}" mayscript>
    <param name="ZIP_FILE_ALIAS" value="OpenSign">
    <param name="ZIP_BASE_URL" value="${codeBase}/resources/opensign/${opensign.appletVersion}/plugins">
    <param name="MS_SUPPORT" value="bcjce">
    <param name="SUN_SUPPORT" value="jsse">
    <param name="STRIP_ZIP" value="yes">
    <c:if test="${not empty plugins}">
        <param name="EXTRA_ZIP_FILE_NAMES" value="${plugins}">
    </c:if>
    <c:choose>
        <c:when test="${opensign.format eq 'PDF'}">
            <param name="signtext.uri" value="${opensign.pdf.pdfUri}"/>
            <param name="signtext.uri.hash.value" value="${opensign.pdf.pdfHashValue}"/>
            <param name="signtext.uri.hash.algorithm" value="${opensign.pdf.pdfHashAlgorithm}"/>
        </c:when>
        <c:otherwise>
            <param name="signtext" value="${opensign.base64Agreement}">
        </c:otherwise>
    </c:choose>
    <param name="signTextFormat" value="${opensign.format}">
    <param name="signText.chunk" value="true">
    <c:if test="${opensign.format eq 'XML'}">
        <param name="signTransformation" value="${opensign.base64Xslt}">
        <c:if test="${not empty opensign.xsltId}">
            <param name="signTransformationId" value="${opensign.xsltId}">
        </c:if>
    </c:if>
    <param name="locale" value="${opensign.appletLocale}">
    <param name="cabbase" value="${codeBase}/resources/opensign/${opensign.appletVersion}/OpenSign-bootstrapped.cab">
    <param name="key.store.directory" value="null">
    <param name="LOG_LEVEL" value="${opensign.loglevel}">
    <param name="socialsecuritynumber" value="${opensign.showSsid}">
    <param name="optionalid" value="${opensign.optionalid}">
    <param name="allowregistryaccess" value="${opensign.allowregistryaccess}">
    <param name="sortcertificates" value="${opensign.sortcertificates}">
    <c:if test="${not empty openlogon.logonto}">
        <param name="logonto" value="${opensign.logonto}">
    </c:if>
    <c:if test="${not empty openlogon.cdkortservice}">
        <param name="cdkortservice" value="${opensign.cdkortservice}">
    </c:if>
    <c:if test="${not empty openlogon.signproperties}">
        <param name="signproperties" value="${opensign.signproperties}">
    </c:if>
    <c:if test="${not empty openlogon.signatureAlgorithm and openlogon.signatureAlgorithm ne 'undefined'}">
        <param name="opensign.signature.factory" value="${opensign.signatureAlgorithm}">
    </c:if>
    <c:if test="${opensign.virklogon}">
        <param name="VIRK_LOGON" value="virklogon">
    </c:if>
    <c:if test="${!opensign.liveconnect}">
        <param name="opensign.doappletrequest" value="true">
    </c:if>
    <c:if test="${opensign.appletRequestOnMac}">
        <param name="opensign.doappletrequestonmac" value="true">
    </c:if>
    <c:choose>
        <c:when test="${not opensign.liveconnect or opensign.appletRequestOnMac}">
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

    <c:if test="${not empty opensign.issuerdnfilter}">
        <param name="issuerdnfilter" value="${opensign.base64Issuerdnfilter}">
    </c:if>
    <c:if test="${not empty opensign.subjectdnfilter}">
        <param name="issuerdnfilter" value="${opensign.base64subjectdnfilter}">
    </c:if>
    <c:if test="${not empty opensign.sernofilter}">
        <param name="issuerdnfilter" value="${opensign.base64sernofilter}">
    </c:if>
    <param name="gui" value="modern">
    <c:if test="${opensign.inputstyle ne 'not-specified'}">
        <param name="inputstyle" value="${opensign.inputstyle}">
    </c:if>
    <c:if test="${not empty opensign.logonheader}">
        <param name="LOGON_HEADER" value="${opensign.logonheader}">
    </c:if>
    <c:if test="${not empty opensign.base64AttachmentXml}">
        <param name="attachments" value="${opensign.base64AttachmentXml}">
    </c:if>
</applet>

<c:if test="${opensign.liveconnect}">

    <script language="JavaScript">

		var signatureChunk="";
		function addChunk(chunk) {
			signatureChunk = signatureChunk + chunk;
		}
		
		function allChunk() {
			onSignOK(signatureChunk);
		}
		
        function onSignOK(signature) {
            document.openLive.message.value=signature;
            document.openLive.result.value='ok';
            document.openLive.submit();
        }

        function onSignCancel() {
            document.openLive.result.value='cancel';
            document.openLive.submit();
        }

        function onSignError(msg) {
            document.openLive.result.value=msg;
            document.openLive.submit();
        }
    </script>


    <form:form modelAttribute="openLive" name="openLive" method="POST" action="display.html">
        <form:hidden path="message"/>
        <form:hidden path="result"/>
    </form:form>

</c:if>

</body>

</html>