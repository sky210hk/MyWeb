<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<h3>EL태그는 표현식을 대체합니다.</h3>

	<%= 1+ 2 %><br>
	
	${1 + 2 }<br> <!-- 3 -->
	${1 > 2 }<br> <!-- false -->
	${1 == 2 }<br> <!-- false -->
	${1 == 2 ? '같음' : '다름' }<br> <!-- 다름 -->
	${1 < 2 && 1 > 2 }<br> <!-- false -->
	${1 < 2 || 1 > 2 }<br> <!-- true -->
	
	${'홍길동' == '홍길동' }<br> <!-- 문자열의 비교도 가능 -->
	
	<h3>e1태그에서 지원하는 문법</h3>
	${1 == 2 or 1 > 2 }<br> <!-- false -->
	${1 == 2 and 1 > 2 }<br> <!-- false -->
	${'홍길동' eq '홍길동' }<br> <!-- true -->
	${ not false }<br><!-- true -->
	
</body>
</html>