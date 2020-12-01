<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="_menu.jsp"></jsp:include>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Добавить цветок</title>
</head>
<body>

<h3>Добавить цветок</h3>

<form method="POST" action="${pageContext.request.contextPath}/createFlower">
    <table border="0">
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
            <td><input type="text" required placeholder="6" name="price" value="${flower.price}" /></td>
        </tr>

        <tr>
            <td>Количество</td>
            <td><input type="text" name="quality"
                       min="0"
                       value="${flower.quality}" />
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="Создать" />
                <a href="flowerList">Отмена</a>
            </td>
        </tr>
    </table>
</form>


</body>
</html>