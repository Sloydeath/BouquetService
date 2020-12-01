<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="_menu.jsp"></jsp:include>
<jsp:include page="_headerForUser.jsp"></jsp:include>


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>
        <c:choose>
            <c:when test = "${flower.name == 'ROZE'}">
                Роза
            </c:when>

            <c:when test = "${flower.name == 'ASTER'}">
                Астра
            </c:when>

            <c:when test = "${flower.name == 'PEONY'}">
                Пион
            </c:when>

            <c:when test = "${flower.name == 'TULIP'}">
                Тюльпан
            </c:when>

            <c:when test = "${flower.name == 'CHAMOMILE'}">
                Ромашка
            </c:when>

            <c:when test = "${flower.name == 'LILAC'}">
                Лилия
            </c:when>

            <c:when test = "${flower.name == 'CHRYSANTHEMUM'}">
                Хризантема
            </c:when>
        </c:choose>
    </title>

</head>
<body>



    <c:choose>
        <c:when test="${not empty flower && flower.quality != 0}">
            <form method="POST" action="${pageContext.request.contextPath}/addFlowerToCart">
                <h3>Список цветов</h3>
                <table border="1" cellpadding="5" cellspacing="1" >
                    <tr>
                        <th>ID</th>
                        <th>Название</th>
                        <th>Длина</th>
                        <th>Цвет</th>
                        <th>Цена</th>
                        <th>Количество</th>
                        <th>Добавить в корзину</th>
                    </tr>

                    <tr>
                        <input type="hidden" name="id" value="${flower.id}" />
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
                        <td>
                            <input type="number" name="quality"
                                   min="1" max="${flower.quality}"
                                   value="${flower.quality}">
                        </td>

                        <td><input type="submit" value="Добавить в корзину"/></td>
                    </tr>
                </table>
            </form>
        </c:when>

        <c:otherwise>
            <h3>К сожалению, таких цветов у нас нет :(</h3><br>
            <h3>Но есть много других!</h3><br>
        </c:otherwise>
    </c:choose>

<a href="${pageContext.request.contextPath}/flowerRange">Вернуться к списку цветов</a>


</body>
</html>