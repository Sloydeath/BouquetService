<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="_menu.jsp"></jsp:include>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Регистрация</title>
</head>
<body>

<h3>Регистрация</h3>

<form method="POST" action="${pageContext.request.contextPath}/doRegistration">
    <table border="0">
        <tr>
            <td>Имя</td>
            <td><input type="text" required placeholder="Андрей" name="firstName"
                       pattern="^[A-Za-zА-Яа-яЁё]+$}"
                       minlength="1" maxlength="50"
                       value="${user.firstName}" /></td>
        </tr>
        <tr>
            <td>Фамилия</td>
            <td><input type="text" required placeholder="Панас" name="lastName"
                       pattern="^[A-Za-zА-Яа-яЁё]+$"
                       minlength="1" maxlength="50"
                       value="${user.lastName}" /></td>
        </tr>
        <tr>
            <td>Email</td>
            <td><input type="email" required placeholder="pan.andrew@mail.ru" name="email"
                       minlength="2" maxlength="50"
                       value="${user.email}" /></td>
        </tr>
        <tr>
            <td>Пароль</td>
            <td><input type="text" required placeholder="pan2000" name="password"
                       pattern="[A-Za-zА-Яа-яЁё-0-9]+$"
                       minlength="1" maxlength="50"
                       value="${user.password}" /></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="Зарегистрироваться" />
                <a href="${pageContext.request.contextPath}/login">Отмена</a>
            </td>
        </tr>
    </table>
</form>


</body>
</html>