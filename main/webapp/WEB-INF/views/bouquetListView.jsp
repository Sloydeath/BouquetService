<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="_menu.jsp"></jsp:include>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Список букетов</title>
</head>
<body>


<h3>Список букетов</h3>

<table border="1" cellpadding="5" cellspacing="1" >
    <tr>
        <th>ID</th>
        <th>Тип</th>
        <th>ID Заказа</th>
        <th>Редактировать</th>
        <th>Удалить</th>
    </tr>

    <c:forEach items="${bouquetList}" var="bouquet" >

        <tr>
            <td>${bouquet.id}</td>

            <c:choose>
                <c:when test = "${bouquet.type == 'ROUND'}">
                    <td>Круглый</td>
                </c:when>

                <c:when test = "${bouquet.type == 'CASCADE'}">
                    <td>Каскадный</td>
                </c:when>

                <c:when test = "${bouquet.type == 'FRAME'}">
                    <td>Каркасный</td>
                </c:when>

                <c:when test = "${bouquet.type == 'JAPANESE'}">
                    <td>Японский</td>
                </c:when>

                <c:when test = "${bouquet.type == 'WEDDING'}">
                    <td>Свадебный</td>
                </c:when>
            </c:choose>

            <td>${bouquet.order.id}</td>


            <td><a href="editBouquet?id=${bouquet.id}">Редактировать</a></td>
            <td><a href="deleteBouquet?id=${bouquet.id}">Удалить</a></td>
        </tr>
    </c:forEach>
</table>

<a href="createBouquet" >Добавить букет</a>


</body>
</html>