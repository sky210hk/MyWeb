<%@page import="com.myweb.user.model.UsersVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<!-- requestScope를 굳이 적지 않아도 된다. -->
	<!-- 이름을 찾는 순서가 범위가 짧은거부터 찾는데 
	request가 생명주기가 가장 짧기 때문에 어차피 먼저 찾게 된다.  -->
	${vo.id }<br>
	${vo.name }<br>
	${vo.email }
	
	<hr/>
	
	<!-- requestScope를 적는다면? 문제 없음-->
	${requestScope.vo.id }<br>
	${requestScope.vo.name }<br>
	${requestScope.vo.email }	

</body>
</html>