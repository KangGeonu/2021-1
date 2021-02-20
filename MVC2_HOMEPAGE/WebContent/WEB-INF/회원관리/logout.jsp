<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP 게시판</title>
</head>
<body>
	<c:if test = "${loginID == null}">
		<script>
			alert("로그아웃 하였습니다.");
			location.href = "HOME";
		</script>
	</c:if>
</body>
</html>