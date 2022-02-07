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
	<!-- choose태그를 이용해서 점수가 90이상이면 A, 80이상이면 B, 70이상이면 C, 나머지는 F로 표현 -->
	<!-- 중첩문장을 이용해서 95점 이상일 때는 A+, 90 ~ 95점일 때는 A로 표현 -->
	<c:choose>
		<c:when test="${param.point >= 90 }">
			<c:choose>
				<c:when test="${param.point >= 95 }">
					A+학점입니다.
				</c:when>
				<c:otherwise>
					A학점입니다.
				</c:otherwise>
			</c:choose>
		</c:when>
		<c:when test="${param.point >= 80 }">
			B학점입니다.
		</c:when>
		<c:when test="${param.point >= 70 }">
			C학점입니다.
		</c:when>
		<c:otherwise>
			F학점입니다.
		</c:otherwise>
	</c:choose>
</body>
</html>