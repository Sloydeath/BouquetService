<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="_menu.jsp"></jsp:include>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>Редактирование заказа</title>
</head>
<body>

<h3>Редактирование заказа</h3>
 
<c:if test="${not empty order}">
    <form method="POST" action="${pageContext.request.contextPath}/editOrder">
        <table border="0">
            <tr>
                <input type="hidden" name="id" value="${order.id}" />
                <td>ID</td>
                <td>${order.id}</td>
            </tr>

            <tr>
                <td>Адрес</td>
                <td><input type="text" required name="address"
                           minlength="2" maxlength="50"
                           value="${order.address}" /></td>
            </tr>

            <tr>
                <td>Номер телефона</td>
                <td><input type="text" required name="phoneNumber"
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
                <td><input type="text" required name="userId" value="${order.user.id}" /></td>
            </tr>
        </table>

        <input type="submit" value="Изменить" />
        <a href="${pageContext.request.contextPath}/orderList">Отмена</a><br/>
    </form>
</c:if>

</body>
</html>