<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="/hw02/calc/calculate" method="get">
<input type="text" name="left"/>
<select name="operator">
			<option>+</option>
			<option>-</option>
			<option>*</option>
			<option>/</option>
</select>
<input type="text" name="right"/> <br>
<input type="submit" value="전송">
<h4>결과값: ${result }</h4>
</form>
</body>
</html>