<%@page import="com.poscoict.mysite.vo.BoardVo"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
				<c:import url="/WEB-INF/views/includes/header.jsp"/>

		<div id="content">
			<div id="board" class="board-form">
				<table class="tbl-ex">
					<tr>
						<th colspan="2">글보기</th>
					</tr>
					<tr>
					
						<td class="label">제목</td>
				
						<td>${ findtitle}</td>
					</tr>
				
					<tr>
						<td class="label">내용</td>
					
						<td>
							<div class="view-content">
								${findcontents}
							</div>
						</td>
					
					</tr>
				</table>
				<div class="bottom">
					<a href="${pageContext.request.contextPath }/board">글목록</a>
					<a href="${pageContext.request.contextPath }/board?a=addForm&no=${no}">답글 작성</a>
					
					
						<c:choose>
							<c:when test="${board_list eq userno}">
								<a href="${pageContext.request.contextPath }/board?a=modifyform&no=${no}">글수정</a>
							
							</c:when>
							<c:otherwise>
							
							</c:otherwise>
						</c:choose>
					
					
					
					
					
					
				</div>
			</div>
		</div>
			<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="board"/>
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>