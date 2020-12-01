<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="_menu.jsp"></jsp:include>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>Изменение цветов</title>
</head>
<body>

<h3>Изменение цветов</h3>
 
<c:if test="${not empty flower}">
    <form method="POST" action="${pageContext.request.contextPath}/editFlower">
        <table border="0">
            <tr>
                <input type="hidden" name="id" value="${flower.id}" />
                <td>ID</td>
                <td>${flower.id}</td>
            </tr>

            <tr>
                <td>Название</td>
                <td>
                    <select required name = "name">
                        <option value="ROZE">Роза</option>
                        <option value="ASTER">Астра</option>
                        <option value="PEONY">Пиона</option>
                        <option value="TULIP">Тюльпан</option>
                        <option value="CHAMOMILE">Ромашка</option>
                        <option value="LILAC">Лилия</option>
                        <option value="CHRYSANTHEMUM">Хризантема</option>
                    </select>
                </td>
            </tr>

            <tr>
                <td>Длина</td>
                <td>
                    <select required name="length">
                        <option value="30">30</option>
                        <option value="40">40</option>
                        <option value="50">50</option>
                        <option value="60">60</option>
                        <option value="70">70</option>
                        <option value="80">80</option>
                        <option value="90">90</option>
                        <option value="100">100</option>
                    </select>
                </td>
            </tr>

            <tr>
                <td>Цвет</td>
                <td>
                    <select required name="color">
                        <option value="RED">Красный</option>
                        <option value="YELLOW">Жёлтый</option>
                        <option value="BLUE">Синий</option>
                        <option value="PINK">Розовый</option>
                        <option value="ORANGE">Оранжевый</option>
                        <option value="BROWN">Коричневый</option>
                        <option value="WHITE">Белый</option>
                        <option value="PURPLE">Фиолетовый</option>
                        <option value="BLACK">Чёрный</option>
                    </select>
                </td>
            </tr>

            <tr>
                <td>Цена</td>
                <td><input type="text" required name="price" value="${flower.price}" /></td>
            </tr>

            <tr>
                <td>Количество</td>
                <td><input type="text" name="quality"
                           min="0"
                           value="${flower.quality}" />
                </td>
            </tr>
        </table>

        <input type="submit" value="Изменить" />
        <a href="${pageContext.request.contextPath}/flowerList">Отмена</a><br/>
    </form>
</c:if>

</body>
</html>