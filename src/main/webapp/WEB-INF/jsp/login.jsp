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
<c:if test="${not empty errorMsg}">
    <p><c:out value="${errorMsg}" /></p>
</c:if>
<form action="LoginServlet" method="post">
ユーザーID:<input type="text" name="userId"><br>
パスワード:<input type="password" name="pass"><br>
<input type="submit" value="ログイン">
</form>
</body>
</html>