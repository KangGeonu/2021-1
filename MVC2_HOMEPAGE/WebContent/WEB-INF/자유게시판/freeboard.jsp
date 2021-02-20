<%@page import="java.sql.Timestamp"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.BOARD.DAO.*"%>
<%@page import="com.BOARD.DTO.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name = "viewport" content="width=device-width",initial-scale="1">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" 
	rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
	
	<style>
	a{
		color : black;
		text-decoration : none;
	}
	a:hover{
		color : red;
	}
	</style>
	
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

	<%
		boardDAO bDAO = boardDAO.getInstance();
		ArrayList<boardDTO> list = bDAO.selectList();
	%>	
	<br><br>
	<div class = "row">
		<div class = "col-lg-1"></div>
		<div class = "col-lg-7">
			<table class="table table-striped" style = "text-align:center;">
			  <thead>
			    <tr>
			      <th scope="col">글 번 호</th>
			      <th scope="col">글 제 목</th>
			      <th scope="col">작 성 자</th>
			      <th scope="col">작 성 시 간</th>
			      <th scope="col"></th>
			    </tr>
			  </thead>
			  <tbody>
	<%
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd, hh:mm:ss");
		for(boardDTO b : list){
			String Date = "";
			Timestamp tDate = b.getTime();
			Date = sdf.format(tDate);
	%>
			  	<tr>
			  		<td><%=b.getNum() %></td>
			  		<td><a href = "READ?TITLE=<%=b.getTitle() %>"><%=b.getTitle() %></a></td>
			  		<td><%=b.getName() %></td>
			  		<td><%=Date %></td>
			  		<%if(b.getName().equals(session.getAttribute("loginNAME"))){  %>
			  		<td><form action = "TEXTDELETE" method = "POST"><button name = "NUM" value = "<%=b.getNum() %>" style = "margin-left : 30px;" >삭제</button></form></td>
			  		<%} %>
			  	</tr>
  	<%
		}
  	%>
			  </tbody>
			</table>
		</div>
		<div class = "col-lg-4"></div>
	</div>
	
	<div class = "container-fluid">
		<div class = "row">
		<div class = "col-lg-6"></div>
		<div class = "col-lg-2">
			<div class = "jumbotron" style = "padding-top:30px;" >
			<form action = "WRITE_PAGE" method = "POST">
				<button type = "submit" class = "btn btn-primary form-control">글 작성</button>
			</form>			
			</div>	
		</div>
		<div class = "col-lg-4"></div>		
		</div>
	</div>
	<script src = "https://code.jquery.com/jquery-3.3.3.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" 
	integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
</body>
</html>