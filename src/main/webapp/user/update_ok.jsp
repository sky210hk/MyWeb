<%@page import="com.myweb.user.model.UsersVO"%>
<%@page import="com.myweb.user.model.UsersDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//1.
	request.setCharacterEncoding("UTF-8");

	String id = request.getParameter("id");
	String pw = request.getParameter("pw");
	String name = request.getParameter("name");
	String email = request.getParameter("email");
	String address = request.getParameter("address");
	
	//2. DAO에 update메서드를 생성하고, 업데이트 구문을 수행합니다. 성공, 실패여부를 1 또는 0으로 받아오세요
	//3. 수정 성공 시 script태그를 이용해서 "회원정보가 수정되었습니다." 출력 후에 마이페이지로 이동
	//4. 수정 실패 시 "회원정보 수정에 실패했습니다." 출력 후에 마이페이지로 이동
	
	UsersDAO dao = UsersDAO.getInstance();
	UsersVO vo = new UsersVO(id, pw, name, email, address, null);
	
	int result = dao.update(vo);
	
	//3. 수정 성공 시 script태그를 이용해서 "회원정보가 수정되었습니다." 출력 후에 마이페이지로 이동
	if(result == 1){ //성공시에 세션정보 수정
		session.setAttribute("vo", vo);
%>
	<script>
		alert("회원정보가 수정되었습니다.");
		location.href = "mypage.jsp"; 
	</script>
<%	//4. 수정 실패 시 "회원정보 수정에 실패했습니다." 출력 후에 마이페이지로 이동	
	} else {
%>
	<script>
		alert("회원정보 수정에 실패했습니다.");
		location.href = "mypage.jsp";
	</script>
<%
	}
%>












