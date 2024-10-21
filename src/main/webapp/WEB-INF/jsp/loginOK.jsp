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
<p>ようこそ<c:out value="${userId}" />さん</p>
<a href="TransactionServlet">家計簿をつける</a><br>
<a href="Logout">トップへ戻る（ログアウト）</a>
</body>
</html>