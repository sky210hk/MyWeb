<%@page import="com.myweb.user.model.UsersVO"%>
<%@page import="com.myweb.user.model.UsersDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//앞에서 비밀번호를 받았기 때문에, 비밀번호 검증 후 삭제를 진행합니다.
	/*
   1. 사용자가 입력한 pw값과 id를 기반으로 login() 메서드를 실행시켜서
      비밀번호가 맞는지 검증합니다.
      
   2. login() 가 null을 반환하면 "현재 비밀번호를 확인하세요" 출력 뒤로가기
      login() 가 값을 가진다면 delete()메서드를 호출해서 삭제를 진행하면 됩니다.   
   
   3. 삭제 성공시에는 세션을 전부 지우고 index페이지로 리다이렉트
      삭제 실패시에는 마이페이지로 리다이렉트
   */
	request.setCharacterEncoding("UTF-8");

	//1. 사용자가 입력한 pw값과 id를 기반으로 login() 메서드를 실행시켜서
    //비밀번호가 맞는지 검증합니다.
	UsersVO vo = (UsersVO)session.getAttribute("vo");
	String id = vo.getId(); //아이디
	
	String pw = request.getParameter("pw"); //파라미터값
	
	//id, pw값 확인
	//id, pw가 일치하면 존재하고 그렇지 않으면 null
	UsersDAO dao = UsersDAO.getInstance();
	UsersVO result = dao.login(id, pw); 
   	
	//2. login() 가 null을 반환하면 "현재 비밀번호를 확인하세요" 출력 뒤로가기
	if(result == null) {
%>
	<script>
		alert("현재 비밀번호를 확인하세요");
		history.go(-1);
	</script>
<%	//2. login() 가 값을 가진다면 delete()메서드를 호출해서 삭제를 진행하면 됩니다.
	} else {
		
		//3. 삭제 성공시에는 세션을 전부 지우고 index페이지로 리다이렉트
		int result2 = dao.delete(id);
		if(result2 ==1){
			session.invalidate();
			response.sendRedirect(request.getContextPath());
	
		//3. 삭제 실패시에는 마이페이지로 리다이렉트
		} else {
%>
	<script>
		alert("탈퇴에 실패했습니다. 관리자에게 문의하세요.");
		history.go(-1);
	</script>
<%
		}
	}
%>





