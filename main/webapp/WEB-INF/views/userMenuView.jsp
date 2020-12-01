<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="_menu.jsp"></jsp:include>
<jsp:include page="_headerForUser.jsp"></jsp:include>

<html>
<head>
    <title>Пользователь</title>

</head>
<body>

<h1>Главная страница Пользователя</h1>
<h2>Выберите действие</h2>

<ul>
    <li><a href="${pageContext.request.contextPath}/accountInfo">Информация об аккаунте</a></li>
    <li><a href="${pageContext.request.contextPath}/flowerRange">Ассортимент</a></li>
    <li><a href="${pageContext.request.contextPath}/cart">Корзина</a></li>

</ul>

<a href="<c:url value="/logout"/>">Выход из аккаунта</a>
</body>
</html>