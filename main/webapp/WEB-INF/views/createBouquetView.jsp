<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="_menu.jsp"></jsp:include>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Добавить букет</title>
</head>
<body>

<h3>Добавить букет</h3>

<form method="POST" action="${pageContext.request.contextPath}/createBouquet">
    <table border="0">
        <tr>
            <td>Тип</td>
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
            <td>ID заказа</td>
            <td><input type="text" required placeholder="1" name="orderId" value="${bouquet.order.id}" /></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="Создать" />
                <a href="bouquetList">Отмена</a>
            </td>
        </tr>
    </table>
</form>


</body>
</html>