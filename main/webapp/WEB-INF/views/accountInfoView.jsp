<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="_menu.jsp"></jsp:include>
<jsp:include page="_headerForUser.jsp"></jsp:include>

<html>
<head>
    <title>Аккаунт</title>
</head>
<body>

<h3>Информация об аккаунте</h3>

<table border="0">
    <tr>
        <td>ID: </td>
        <td>${user.id}</td>
    </tr>

    <tr>
        <td>Имя: </td>
        <td>${user.firstName}</td>
    </tr>

    <tr>
        <td>Фамилия: </td>
        <td>${user.lastName}</td>
    </tr>

    <tr>
        <td>Email: </td>
        <td>${user.email}</td>
    </tr>

    <tr>
        <td>Роль: </td>
        <td>${user.userRole}</td>
    </tr>
</table><br><br>

<h4>Мои заказы:</h4><br>

<table border="1" cellpadding="5" cellspacing="1" >
    <tr>
        <th>Адрес</th>
        <th>Телефонный номер</th>
        <th>Статус</th>
    </tr>
    <c:forEach items="${orders}" var="order" >
        <tr>
            <td>${order.address}</td>
            <td>${order.phoneNumber}</td>
            <c:choose>
                <c:when test = "${order.status == 'NOT_CONFIRMED'}">
                    <td>Не подтверждён</td>
                </c:when>

                <c:when test = "${order.status == 'CONFIRMED'}">
                    <td>Подтверждён</td>
                </c:when>

                <c:when test = "${order.status == 'DELIVERED'}">
                    <td>Доставлен</td>
                </c:when>

                <c:when test = "${order.status == 'ASSEMBLED'}">
                    <td>Собран</td>
                </c:when>

                <c:when test = "${order.status == 'IN_PROCESS'}">
                    <td>Обрабатывается</td>
                </c:when>
            </c:choose>
        </tr>
    </c:forEach>
</table>

<a href="${pageContext.request.contextPath}/login">Назад</a><br/>

</body>
</html>
