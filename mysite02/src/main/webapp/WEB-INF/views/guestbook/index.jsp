<%@page import="com.poscoict.mysite.vo.GuestbookVo"%>
<%@page import="java.util.List"%>
<%@page import="com.poscoict.mysite.dao.GuestbookDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	//EmaillistDao객체 dao로 생성
	List<GuestbookVo> list = (List<GuestbookVo>)request.getAttribute("list");
	//빨간 줄 뜨는 import 부분에서 ctrl space 키를 누르면 내가 import 해야 될 것이 나온다.
%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="<%= request.getContextPath() %>/assets/css/guestbook.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<div id="header">
			<h1>MySite</h1>
			<ul>
				<li><a href="">로그인</a><li>
				<li><a href="<%= request.getContextPath() %>/user">회원가입</a><li>
				<li><a href="<%= request.getContextPath() %>/user">회원정보수정</a><li>
				<li><a href="">로그아웃</a><li>
				<li>님 안녕하세요 ^^;</li>
			</ul>
		</div>
		<div id="content">
			<div id="guestbook">
				<form action="<%= request.getContextPath() %>/guestbook?a=add" method="post">
					<input type="hidden" name="a" value="insert">
					<table>
						<tr>
							<td>이름</td><td><input type="text" name="name"></td>
							<td>비밀번호</td><td><input type="password" name="password"></td>
						</tr>
						<tr>
							<td colspan=4><textarea name="message" id="content"></textarea></td>
						</tr>
						<tr>
							<td colspan=4 align=right><input type="submit" VALUE=" 확인 "></td>
						</tr>
					</table>
				</form>
				<%
		int count=list.size();
		int index = 0;
		for(GuestbookVo vo : list){

	%>
				<ul>
					<li>
						<table>
							<tr>
								<td>[<%= count-index++ %>]</td>
								<td><%= vo.getName() %></td>
								<td><%= vo.getRegDate() %></td>
								<td><a href="<%= request.getContextPath() %>/guestbook?a=deleteform&no=<%=vo.getNo() %>">삭제</a></td>
							</tr>
							<tr>
							<td colspan=4><%= vo.getMessage().replaceAll("\n","<br/>") %> </td>
							<br>
							</tr>
						</table>
						<br>
					</li>
				</ul>
				<%
		}
	%>
			</div>
		</div>
		<div id="navigation">
			<ul>
				<li><a href="">홍길동</a></li>
				<li><a href="">방명록</a></li>
				<li><a href="">게시판</a></li>
			</ul>
		</div>
		<div id="footer">
			<p>(c)opyright 2015, 2016, 2017, 2018</p>
		</div>
	</div>
</body>
</html>