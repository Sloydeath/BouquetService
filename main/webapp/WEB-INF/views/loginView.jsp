<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="_menu.jsp"></jsp:include>

<html>
<head>
    <title>Авторизация</title>

</head>
<body>

<div class="form">

    <h1>Вход в систему</h1><br>
    <form method="post" action="">

        <input type="email" required placeholder="login" name="email"><br>
        <input type="password" required placeholder="password"
               pattern="[A-Za-zА-Яа-яЁё-0-9]+$"
               name="password"><br><br>
        <input class="button" type="submit" value="Войти">

        <a href="${pageContext.request.contextPath}/registration">Регистрация</a><br/>

    </form>
</div>
</body>
</html>