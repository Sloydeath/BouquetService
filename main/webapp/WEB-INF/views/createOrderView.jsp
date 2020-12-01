<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="_menu.jsp"></jsp:include>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Добавить заказ</title>
</head>
<body>

<h3>Добавить заказ</h3>

<form method="POST" action="${pageContext.request.contextPath}/createOrder">
    <table border="0">
        <tr>
            <td>Адрес</td>
            <td><input type="text" required placeholder="Энгельса 111-5" name="address"
                       minlength="1" maxlength="50"
                       value="${order.address}" /></td>
        </tr>
        <tr>
            <td>Номер телефона</td>
            <td><input type="text" required placeholder="443013314" name="phoneNumber"
                       pattern="[0-9]{9}"
                       minlength="9" maxlength="9"
                       value="${order.phoneNumber}" /></td>
        </tr>
        <tr>
            <td>Статус</td>
            <td>
                <select required name="status">
                    <option value="NOT_CONFIRMED">Не подтверждён</option>
                    <option value="CONFIRMED">Подтверждён</option>
                    <option value="IN_PROCESS">Обрабатывается</option>
                    <option value="ASSEMBLED">Собран</option>
                    <option value="DELIVERED">Доставлен</option>
                </select>
            </td>
        </tr>
        <tr>
            <td>ID пользователя</td>
            <td><input type="text" required placeholder="1" name="userId" value="${order.user.id}" /></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="Создать" />
                <a href="orderList">Отмена</a>
            </td>
        </tr>
    </table>
</form>


</body>
</html>