<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="_menu.jsp"></jsp:include>
<jsp:include page="_headerForUser.jsp"></jsp:include>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>О заказе</title>
</head>
<body>

<h3>Информация о заказе</h3>

<form method="POST" action="${pageContext.request.contextPath}/placeOrder">
    <table border="0">
        <tr>
            <td>Адрес доставки: </td>
            <td><input type="text" required placeholder="Энгельса 111-5" name="address"
                       minlength="1" maxlength="50"
                       value="${order.address}" /></td>
        </tr>

        <tr>
            <td>Ваш номер телефона: </td>
            <td><input type="text" required placeholder="443013314" name="phoneNumber"
                       pattern="[0-9]{9}"
                       minlength="9" maxlength="9"
                       value="${order.phoneNumber}" /></td>
        </tr>

        <tr>
            <td>Выберите тип букета</td>
            <td>
                <select required name="type">
                    <option value="ROUND">Круглый</option>
                    <option value="CASCADE">Каскадный</option>
                    <option value="FRAME">Каркасный</option>
                    <option value="JAPANESE">Японский</option>
                    <option value="WEDDING">Свадебный</option>
                </select>
            </td>
        </tr>

        <tr>
            <td colspan="2">
                <input type="submit" value="Оформить заказ" /><br>
                <a href="flowerRange">Вернуться к списку цветов</a>
            </td>
        </tr>
    </table>
</form>


</body>
</html>