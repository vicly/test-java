<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<body>
<h1>Title : ${title}</h1>
<h1>Message : ${message}</h1>

<p>
    Hello <b>
    <c:out value="${pageContext.request.remoteUser}"/> :
     ${pageContext.request.userPrincipal.name}</b>
</p>

<c:if test="${pageContext.request.authType == 'FORM_AUTH'}">
    <c:url var="logoutUrl" value="/logout"/>
    <form action="${logoutUrl}" method="post">
        <input type="submit" value="Logout"/>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>
</c:if>


</body>
</html>
