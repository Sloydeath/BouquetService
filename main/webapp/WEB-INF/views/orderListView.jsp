<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="_menu.jsp"></jsp:include>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Список заказов</title>
</head>
<body>


<h3>Список заказов</h3>

<table border="1" cellpadding="5" cellspacing="1" >
    <tr>
        <th>ID</th>
        <th>Адрес</th>
        <th>Телефонный номер</th>
        <th>Статус</th>
        <th>ID пользователя</th>
        <th>Редактировать</th>
        <th>Удалить</th>
    </tr>
    <c:forEach items="${orderList}" var="order" >
        <tr>
            <td>${order.id}</td>
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
            <td>${order.user.id}</td>

            <td><a href="editOrder?id=${order.id}">Редактировать</a></td>
            <td><a href="deleteOrder?id=${order.id}">Удалить</a></td>
        </tr>
    </c:forEach>
</table>

<a href="createOrder" >Создать заказ</a>


</body>
</html>