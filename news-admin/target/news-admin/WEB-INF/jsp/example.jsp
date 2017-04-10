<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Hello World</title>
</head>
<body>
   <h2>${message}</h2>

   <c:forEach begin="1" end="3" var="i">

   										<c:url value="/newsList" var="url">
   											<c:param name="page" value="${i}" />
   										</c:url>
   										<a href="${url}">${i}</a>


   							</c:forEach>

</body>
</html>