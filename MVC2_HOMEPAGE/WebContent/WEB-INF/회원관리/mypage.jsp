<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	String id = (String)session.getAttribute("loginID");
	String pw = (String)session.getAttribute("loginPW");
	String name = (String)session.getAttribute("loginNAME");
	String email = (String)session.getAttribute("loginEMAIL");
%>
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

	<br>
	<div class = "container">
		<div class = "row">
		<div class = "col-lg-2"></div>
		<div class = "col-lg-8">
			<div class = "jumbotron" style = "padding-top:30px;" >
			<form action = "UPDATE" method = "POST">
					<h3 style = "text-align :center;">마이페이지</h3>
					<div class = "form-group">
						아이디<input type = "text" name = "ID" class = "form-control" value = "<%= id %>" readonly/>
					</div><br>
					
					<div class = "form-group">
						비밀번호<input type = "password" name = "PW" class = "form-control" value = "<%= pw %>" required/>
					</div><br>
					
					<div class = "form-group">
						이름<input type = "text" name = "NAME" class = "form-control" value = "<%= name %>" required/>
					</div><br>
					
					<div class = "form-group">
						이메일<input type = "text" name = "EMAIL" class = "form-control" value = "<%= email %>" required/>
					</div><br>
					<input type = "submit" value = "수정하기" class = "btn btn-primary form-control">
			</form>		
			</div>	
		</div>
		<div class = "col-lg-2"></div>		
		</div>
	<script src = "https://code.jquery.com/jquery-3.3.3.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" 
	integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
</body>
</html>