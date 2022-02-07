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

	<!-- 변수선언(e1태그로 참조가 가능합니다. -->
	<c:set var = "a" value = "1" />
	${a } <!-- 변수a의 값 : 1 -->
	
	<!-- 출력문 -->
	<c:out value = "hello" /> <!-- hello를 출력  -->
	<c:out value = "${a }" /> <!-- 앞서 선언한 변수a를 출력 -->

	<hr/>
	
	<!-- 조건문(if) -->
	<c:if test="true">
		무조건 실행되는 문장
	</c:if>
		
	<hr/>

	<!-- 조건문의 혼용도 가능하다.  -->
	<!-- 문자열의 비교 eq  -->
	<c:if test="${param.name eq '홍길동' }">
		이름이 홍길동 입니다.<br>
	</c:if>
	
	<c:if test="${param.name eq '이순신' }">
		이름이 이순신 입니다.<br>
	</c:if>
	
	<c:if test="${param.age >= 20 }">
		성인입니다.
	</c:if>	

	<c:if test="${param.age < 20 }">
		미성년자입니다.
	</c:if>

</body>
</html>








