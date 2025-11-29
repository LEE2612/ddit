<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resource/js/app/calculate.js"></script>
</head>
<body>
    <form action="/hw02/calc/calculate">
        <h4>수신 희망 컨텐츠 형식 선택</h4>
        <label><input type="radio" name="accept" value="html" checked />HTML</label>
        <label><input type="radio" name="accept" value="json" />JSON</label>

        <br>
        <input id="left" type="text" name="left"/>
        <select id="operator" name="operator">
            <option>+</option>
            <option>-</option>
            <option>*</option>
            <option>/</option>
        </select>
        <input id="right" type="text" name="right"/> 
        <br>

        <button type="submit">전송</button>

        <h4>결과값:</h4>
    </form>

    <div id="resultArea"></div>

</body>
</html>
