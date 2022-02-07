<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<!-- 1부터 100까지 홀수의 합을 구해보자 -->

	<!-- 기존의 java구문 -->
	<%
	int sum = 0;
	for(int i = 1; i <= 100; i+=2) {
		sum += i;
	}
	%>
	결과 : <%= sum %>
	
	<br/>
	<!-- forEach반복문  -->
	<c:set var = "total" value = "0"/>
	<c:forEach var = "i" begin = "1" end = "100" step = "2" >
		<c:set var = "total" value = "${total + i }"/>
	</c:forEach>
	
	결과 : ${total }
	
	<hr>
	<h3>구구단 3단 jstl로 출력</h3>
	<c:forEach var = "i" begin = "1" end = "9" step = "1">
		3 x ${i } = ${3 * i }<br>
	</c:forEach>	

	<hr>
	<h3>2 ~ 9단까지 모든 구구단 jstl로 출력</h3>
	<c:forEach var = "i" begin = "2" end = "9" step = "1">
		<br>
		<c:forEach var = "j" begin = "1" end = "9" step = "1">
			${i } x ${j } = ${i * j }<br>	
		</c:forEach>
	</c:forEach>
	
	<hr>
	<h3>향상된 포문</h3>
	<%
	int[] arr = {1,2,3,4,5};
	for(int i : arr) {
		out.println(i);		
	}
	%>
	
	<br>
	<!-- items : 향상된 for문을 사용할 수 있도록 함 -->
	<c:set var = "arr2" value = "<%= new int[] {1,2,3,4,5} %>"/>
	
	<c:forEach var = "i" items = "${arr2 }">
		${i }
	</c:forEach>
	
	<hr>
	<!-- varStatus : for의 상태(인덱스)값을 의미합니다. -->
	<c:forEach var = "i" items = "${arr2 }" varStatus = "x">
		${x.index } 인덱스의 값 : ${i }<br>
	</c:forEach>


</body>
</html>