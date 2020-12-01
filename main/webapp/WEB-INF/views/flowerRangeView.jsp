<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<jsp:include page="_menu.jsp"></jsp:include>
<jsp:include page="_headerForUser.jsp"></jsp:include>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Ассортимент</title>
</head>
<body>

<h3>Ассортимент цветов</h3>

<b>Цветы:</b>

<form action="${pageContext.request.contextPath}/getFlowerList" method="post">

    <table border="0">
        <tr>
            <td>Название цветка</td>
            <td>
                <select required name = "name">
                    <option value="ROZE">Роза</option>
                    <option value="ASTER">Астра</option>
                    <option value="PEONY">Пион</option>
                    <option value="TULIP">Тюльпан</option>
                    <option value="CHAMOMILE">Ромашка</option>
                    <option value="LILAC">Лилия</option>
                    <option value="CHRYSANTHEMUM">Хризантема</option>
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
    </table>
    <input type="submit" value="Найти" />
</form>

</body>
</html>