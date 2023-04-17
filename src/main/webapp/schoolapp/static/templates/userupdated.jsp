<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page pageEncoding="UTF-8" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Νέα Στοιχεία User</h1>
	<p>teacher ${requestScope.user.username}</p>
	<p>teacher ${requestScope.user.password}</p>
	<a href="${pageContext.request.contextPath}/schoolapp/users">Επιστροφή</a>
</body>
</html>
