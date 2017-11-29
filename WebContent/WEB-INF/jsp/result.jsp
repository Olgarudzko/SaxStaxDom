<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>XML parsed</title>
</head>
<body>
<c:if test="${requestScope.result != null}">
<c:choose>
<c:when test="${!requestScope.result.isEmpty()}">
<c:forEach items="${requestScope.result}" var="line">
  <c:out value="${line}"/>
  <br/>
</c:forEach>
<br/>
<form action="FrontController" method='post'>
<input type="submit" name="command" value="NEXT"/>
</form>
</c:when>
<c:otherwise>
End of XML reached
</c:otherwise>
</c:choose>
</c:if>
</body>
</html>