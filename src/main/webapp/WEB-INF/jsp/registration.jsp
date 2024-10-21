<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>マイ家計簿</title>
</head>
<body>
<c:forEach var="item" items="${errorList}">
    <p><c:out value="${item}" /></p>
</c:forEach>
<form action="RegistrationServlet" method="post">
ユーザーID:<input type="text" name="userId"><br>
パスワード:<input type="password" name="pass1"><br>
パスワード（確認）:<input type="password" name="pass2"><br>
<input type="submit" value="登録">
<a href="Logout">トップへ戻る</a>
</form>
</body>
</html>