<%@ page import="java.util.List" %>
<%@ page import="com.vironit.bouquetService.model.Flower" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="_menu.jsp"></jsp:include>
<jsp:include page="_headerForUser.jsp"></jsp:include>

<jsp:useBean id="cart" class="com.vironit.bouquetService.util.Cart" scope="session" />

<html>
<head>
    <title>Коризина</title>
</head>
<body>

<c:choose>
    <c:when test="${not empty cart.flowers}">
        <h3>Список цветов в корзине</h3>

        <table border="1" cellpadding="5" cellspacing="1" >
            <tr>
                <th>ID</th>
                <th>Название</th>
                <th>Длина</th>
                <th>Цвет</th>
                <th>Цена</th>
                <th>Количество</th>
                <th>Удалить из корзины</th>
            </tr>

            <c:forEach items="${cart.flowers}" var="flower" >
                <tr>
                    <td>${flower.id}</td>

                    <c:choose>
                        <c:when test = "${flower.name == 'ROZE'}">
                            <td>Роза</td>
                        </c:when>

                        <c:when test = "${flower.name == 'ASTER'}">
                            <td>Астра</td>
                        </c:when>

                        <c:when test = "${flower.name == 'PEONY'}">
                            <td>Пиона</td>
                        </c:when>

                        <c:when test = "${flower.name == 'TULIP'}">
                            <td>Тюльпан</td>
                        </c:when>

                        <c:when test = "${flower.name == 'CHAMOMILE'}">
                            <td>Ромашка</td>
                        </c:when>

                        <c:when test = "${flower.name == 'LILAC'}">
                            <td>Лилия</td>
                        </c:when>

                        <c:when test = "${flower.name == 'CHRYSANTHEMUM'}">
                            <td>Хризантема</td>
                        </c:when>
                    </c:choose>

                    <c:choose>
                        <c:when test = "${flower.length == '30'}">
                            <td>30</td>
                        </c:when>

                        <c:when test = "${flower.length == '40'}">
                            <td>40</td>
                        </c:when>

                        <c:when test = "${flower.length == '50'}">
                            <td>50</td>
                        </c:when>

                        <c:when test = "${flower.length == '60'}">
                            <td>60</td>
                        </c:when>

                        <c:when test = "${flower.length == '70'}">
                            <td>70</td>
                        </c:when>

                        <c:when test = "${flower.length == '80'}">
                            <td>80</td>
                        </c:when>

                        <c:when test = "${flower.length == '90'}">
                            <td>90</td>
                        </c:when>

                        <c:when test = "${flower.length == '100'}">
                            <td>100</td>
                        </c:when>
                    </c:choose>

                    <c:choose>
                        <c:when test = "${flower.color == 'RED'}">
                            <td>Красный</td>
                        </c:when>

                        <c:when test = "${flower.color == 'YELLOW'}">
                            <td>Жёлтый</td>
                        </c:when>

                        <c:when test = "${flower.color == 'BLUE'}">
                            <td>Синий</td>
                        </c:when>

                        <c:when test = "${flower.color == 'PINK'}">
                            <td>Розовый</td>
                        </c:when>

                        <c:when test = "${flower.color == 'ORANGE'}">
                            <td>Оранжевый</td>
                        </c:when>

                        <c:when test = "${flower.color == 'BROWN'}">
                            <td>Коричневый</td>
                        </c:when>

                        <c:when test = "${flower.color == 'WHITE'}">
                            <td>Белый</td>
                        </c:when>

                        <c:when test = "${flower.color == 'PURPLE'}">
                            <td>Фиолетовый</td>
                        </c:when>

                        <c:when test = "${flower.color == 'BLACK'}">
                            <td>Чёрный</td>
                        </c:when>
                    </c:choose>

                    <td>${flower.price}</td>

                    <td>${flower.quality}</td>

                    <td><a href="deleteFlowerFromCart?id=${flower.id}">Удалить из корзины</a></td>
                </tr>
            </c:forEach>
        </table><br/><br/>


        <table border="0">
            <tr>
                <td>Цена букета </td>
                <td>
                    <%
                    List<Flower> flowers = cart.getFlowers();
                        double bouquetPrice = 0;
                        if (flowers != null) {
                            for (Flower f : flowers)
                                bouquetPrice += f.getPrice() * f.getQuality();
                            out.println(bouquetPrice);
                        } else {
                            bouquetPrice = 0;
                            out.println(bouquetPrice);
                        }
                    %>
                </td>
            </tr>

            <tr>
                <td>Количество цветов: </td>
                    <%
                        flowers = cart.getFlowers();
                        int count = 0;
                        if(flowers != null) {
                            for (Flower f : flowers)
                                count += f.getQuality();
                            out.println("<td>" + count + "</td>");
                        } else{
                            count = 0;
                            out.println("<td>" + count + "</td>");
                        }
                    %>
            </tr>
        </table>

        <a href="${pageContext.request.contextPath}/placeOrder">Оформить заказ</a><br/><br/><br/>

    </c:when>
    <c:otherwise>
        <h3>Корзина пуста!</h3><br>
    </c:otherwise>
</c:choose>

<a href="${pageContext.request.contextPath}/flowerRange">К списку цветов</a>

</body>
</html>