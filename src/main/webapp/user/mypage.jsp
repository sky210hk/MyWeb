<%@page import="com.myweb.user.model.UsersVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "../include/header.jsp"%>
<%
	//세션이 없는 경우(login.jsp로 리다이렉트)
	if(session.getAttribute("vo") == null) {
		response.sendRedirect("login.jsp");
	}
	//세션이 있는 경우 : vo에 저장
	UsersVO vo =(UsersVO)session.getAttribute("vo");
%>
<section>
	<div align = "center">
		<h3><%= vo.getId() %>(<%= vo.getName() %>)님의 정보관리</h3>	
	
		<a href = "update.jsp">[정보수정]</a>
		<a href = "delete.jsp">[회원탈퇴]</a>
		
	</div>
</section>

<%@ include file = "../include/footer.jsp"%>