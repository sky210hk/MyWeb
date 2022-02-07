<%@page import="com.myweb.user.model.UsersVO"%>
<%@page import="com.myweb.user.model.UsersDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

	//값을 받고, 화면에 대한 처리
	request.setCharacterEncoding("UTF-8");
	
	String id = request.getParameter("id");
	String pw = request.getParameter("pw");
	String name = request.getParameter("name");
	String email = request.getParameter("email");
	String address = request.getParameter("address");
	
	//간단한 중복검사(ID만 있는지 확인하면 충분함)
	UsersDAO dao = UsersDAO.getInstance();
	int result = dao.idCheck(id);
	
	if(result == 1) { //1인 경우 : 중복이 있다는 소리
%>
	<script>
		alert("중복된 아이디입니다.")
		history.go(-1); //뒤로가기
	</script>
<%
	} else { //1이 아닌 경우 : 중복이 없다는 소리
		
		UsersVO vo = new UsersVO(id, pw, name, email,address, null);
		
		int result2 = dao.insert(vo);
		
		if(result2 == 1) { //성공
%>
	<script>
		alert("회원가입을 축하합니다!");
		location.href = "login.jsp"; //페이지를 이동시킨다.
	</script>
<%		
		} else { //실패
%>
	<script>
		alert("회원가입에 실패했습니다")
		history.go(-1); //뒤로가기
	</script>
<%			
		}
		
	}
	
	
	
	
%>