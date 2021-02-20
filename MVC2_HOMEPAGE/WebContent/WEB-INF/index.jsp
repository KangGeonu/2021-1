<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name = "viewport" content="width=device-width",initial-scale="1">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" 
	rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
<title>JSP 게시판</title>
</head>
<body>
	<c:if test="${ newResult == 1}">
				<script>
					alert("회원가입 되었습니다.");
				</script>
	</c:if>
	<c:if test="${ loginResult == 1}">
			<script>
				alert("로그인하였습니다.");
			</script>
	</c:if>
	<c:if test="${ updateResult == 1}">
			<script>
				alert("회원정보 수정완료");
			</script>
	</c:if>
	<nav class="navbar navbar-expand-lg navbar-light" style="background-color: #e3f2fd;">
	 <div class="container-fluid">
	   <a class="navbar-brand" href="HOME">JSP 게시판</a>
	   <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
	     <span class="navbar-toggler-icon"></span>
	   </button>
	   <div class="collapse navbar-collapse" id="navbarNavDropdown">
	     <ul class="navbar-nav">
	       <li class="nav-item">
	         <a class="nav-link" href="FREEBOARD">자유게시판</a>
	       </li>
	       <li class="nav-item">
	         <a class="nav-link" href="#">장터게시판</a>
	       </li>
	       <li class="nav-item">
	         <a class="nav-link" href="#">놀이터</a>
	       </li>
	       	       
	       <li class="nav-item dropdown">
	         <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-expanded="false">
	           	Option
	         </a>

	         <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
	           <li><a class="dropdown-item" href="MYPAGE">마이페이지</a></li>
	           	<c:if test="${ loginID == null}">
					<li><a class="dropdown-item" href="LOGIN">로그인</a></li>
				</c:if>
				<c:if test="${ loginID != null}">
					<li><a class="dropdown-item" href="LOGOUT">로그아웃</a></li>
				</c:if>
	           <li><a class="dropdown-item" href="NEWUSER">회원가입</a></li>
	         </ul>
	       </li>
	     </ul>
	   </div>
	 </div>
	</nav>
	
	<script src = "https://code.jquery.com/jquery-3.3.3.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" 
	integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
</body>
</html>