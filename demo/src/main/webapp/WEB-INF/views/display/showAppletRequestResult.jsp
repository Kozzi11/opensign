<%@page contentType="text/html;charset=UTF-8" %>
<%@page pageEncoding="UTF-8" %>
<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <META http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title><fmt:message key="applet.request.result.title"/></title>
</head>
<body bgcolor="white">
<h2><fmt:message key="result.summary.text"/></h2>
<fmt:message key="result.applet.label"/> <b>${openLive.result}</b><br>

<a href="${contextPath}/"><fmt:message key="result.anchor.back.label"/></a><br>
<form name="form" method="post" action="${contextPath}/servlet/XMLDisplay">
    <input type="hidden" name="xml" value="${appletRequest.message}">
    <input type="submit" value="view xml">
</form>
<br>

Signature verification <c:choose><c:when test="${verified}">was <b>successful</b></c:when><c:otherwise><b>failed</b></c:otherwise></c:choose><br>
<c:if test="${signatureProperties ne null}">
    <h3>The signed part of the signature contains:</h3>
    <table border=1>
        <tr><th>Name</th><th>Encoding</th><th>Visible to signer</th><th>Value</th></tr>
        <c:forEach var="prop" items="${signatureProperties}">
            <tr>
                <td>
                        ${prop.key}
                </td>
                <td>
                    n/a
                </td>
                <td>
                    n/a
                </td>
                <td>
                    <c:choose>
                        <c:when test="${prop.key eq 'signtext'}">
                            <textarea cols="80" rows="10" readonly>${prop.value}</textarea>
                        </c:when>
                        <c:otherwise>
                            ${prop.value}
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </c:forEach>
    </table>
</c:if>
</body>
</html>