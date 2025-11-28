<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>실시간 시계</title>
</head>
<body>
    
    <h1>현재 클라이언트의 시각 : <span id="clock"></span></h1>
    
    <h1>현재 서버의 시각 : <span id="serverTimeDisplay"></span></h1>
    
    <hr> 
    
    <a href="#" onclick="saveServerTime(); return false;">서버 시간 저장하기</a>
    
    <script src="../resources/js/01/timeDisplay.js"></script>
</body>
</html>