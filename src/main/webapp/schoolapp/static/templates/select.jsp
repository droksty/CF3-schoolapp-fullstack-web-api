<%--
  Created by IntelliJ IDEA.
  User: droksty
  Date: 17-Apr-23
  Time: 6:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Main Menu</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/schoolapp/static/css/select.css">
</head>
<body>
    <div class="wrapper">
        <div>
            <p>Hello ${sessionScope.username}</p>
        </div>
        <div>
            <h3>Choose module to manage:</h3>
        </div>
        <div class="teachers">
            <a href="/schoolapp/teachers">Teachers</a>
        </div>
        <div class="users">
            <a href="/schoolapp/users">Users</a>
        </div>
    </div>
</body>
</html>
