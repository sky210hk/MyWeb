<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<!-- 참고. 
	format : 형식변경
	parse : 형 변환
	-->
	
	<h3>formatNumber -> 숫자의 형식 변환(반드시 숫자 형식을 가지고 있어야 합니다.)</h3>
	
	<c:set var = "d01" value = "2020" />
	
	<fmt:formatNumber var = "v01" value = "${d01 }" pattern = "000000"/>
	${v01 }<br>
	
	<fmt:formatNumber var = "v02" value = "${d01 }" pattern = "0000.00"/>
	${v02 }<br>
	
	<h3>formatDate -> 날짜의 형식 변환(반드시 날짜 형식을 가지고 있어야 합니다.)</h3>
	
	<c:set var = "d02" value = "<%= new Date() %>"/>
	
	<fmt:formatDate var = "v03" value="${d02 }" pattern = "yyyyMMdd"/>
	${v03 }<br>
	
	<fmt:formatDate var = "v04" value="${d02 }" pattern = "yyyy년MM월dd일"/>
	${v04 }<br>
	
	<fmt:formatDate var = "v05" value="${d02 }" pattern = "yyyy-MM-dd HH:mm:ss"/>
	${v05 }<br>

	<hr>
	
	<h3>parseDate -> 문자를 날짜로 형 변환</h3>
	
	<c:set var = "d03" value = "2020/01/26"/>
	
	<fmt:parseDate var = "v06" value = "${d03 }" pattern = "yyyy/MM/dd"></fmt:parseDate>
	${v06 }<br>
	
	<c:set var = "d04" value = "2020/01/26 23:12:44"/>
	
	<fmt:parseDate var = "v07" value = "${d04 }" pattern = "yyyy/MM/dd HH:mm:ss"/>
	${v07 }<br>
	
	<h3>parseNumber -> 문자를 숫자로 형 변환</h3>
	
	<c:set var = "d05" value = "1.123"/>
	<c:set var = "d06" value = "1.123abc"/>
	
	<fmt:parseNumber var = "v08" value = "${d05 }" pattern = "0.000"/>
	${v08 }<br>
	
	<fmt:parseNumber var = "v09" value = "${d06 }" type = "number"/>
	${v09 }<br> <!-- 사용할 수 있는 숫자형태로 바꿈 -->
	
	<hr>
	
	<h3>아래의 값을 2020년05월03일 형식으로 출력</h3>
	
	<c:set var = "time_a" value = "2020-05-03"/>
	
	<fmt:parseDate var = "time_a1" value = "${time_a }" pattern = "yyyy-MM-dd"/>
	<fmt:formatDate var = "time_a2" value = "${time_a1 }" pattern = "yyyy년MM월dd일"/>
	${time_a2 }<br>
	
	
	
	
	
	

</body>
</html>





