<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="_menu.jsp"></jsp:include>

<html>
<head>
    <title>Админ</title>
</head>
<body>

<h1>Главная страница Админа</h1>
<h2>Выберите действие</h2>

<ul>
    <li><a href="${pageContext.request.contextPath}/">Главная страница</a></li>
    <li><a href="${pageContext.request.contextPath}/login">Авторизация</a></li>
    <li><a href="${pageContext.request.contextPath}/orderList">Список заказов</a></li>
    <li><a href="${pageContext.request.contextPath}/flowerList">Список цветов</a></li>
    <li><a href="${pageContext.request.contextPath}/bouquetList">Список букетов</a></li>

</ul>

<a href="<c:url value='/logout' />">Выход из аккаунта</a>
</body>
</html>