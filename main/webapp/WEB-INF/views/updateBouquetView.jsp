<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="_menu.jsp"></jsp:include>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>Редактирование букета</title>
</head>
<body>

<h3>Редактирование букета</h3>
 
<c:if test="${not empty bouquet}">
    <form method="POST" action="${pageContext.request.contextPath}/editBouquet">
        <table border="0">
            <tr>
                <input type="hidden" name="id" value="${bouquet.id}" />
                <td>ID</td>
                <td>${bouquet.id}</td>
            </tr>

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
                <td><input type="text" required name="orderId" value="${bouquet.order.id}" /></td>
            </tr>
        </table>

        <input type="submit" value="Изменить" />
        <a href="${pageContext.request.contextPath}/bouquetList">Отмена</a><br/>
    </form>
</c:if>

</body>
</html>