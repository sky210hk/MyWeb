<%@page import="com.myweb.user.model.UsersVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<%
	if(session.getAttribute("vo") == null) {
		response.sendRedirect("login.jsp");
	}
	//이 페이지에 진입했을 때, 비밀번호를 제외한 회원정보를 input태그에 미리 보이도록 처리하기
	UsersVO vo = (UsersVO)session.getAttribute("vo");
%>
<section>
	<div align="center">
		<h3>회원정보 수정</h3>
		<hr/>
		<form action="update_ok.jsp" method="post">
			<table border="1">
				<tr>
					<td>아이디</td> 
					<td><input type="text" name="id" value = "<%= vo.getId() %>" placeholder="4글자이상" readonly></td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td><input type="password" name="pw" placeholder="4글자이상" required pattern="\\w{4, }"></td>
				</tr>
				<tr>
					<td>이름</td>
					<td><input type="text" name="name" value = "<%= vo.getName() %>" placeholder="이름은 필수입니다." required></td>
				</tr>
				<tr>
					<td>이메일</td>
					<td><input type="email" name="email" value = "<%= vo.getEmail() %>"></td>
				</tr>
				<tr>
					<td>주소</td>
					<td><input type="text" name="address" value = "<%= vo.getAddress() %>" ></td>
				</tr>
			</table>

			<input type="submit" value="정보수정" class="btn btn-default"> 
			<input type="button" value="마이페이지" class="btn btn-primary" onclick="location.href = 'mypage.jsp'">
		</form>
	</div>
</section>
<%@ include file="../include/footer.jsp"%>

