<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<<<<<<< HEAD
    <meta charset="UTF-8">
    <title>AJAX 계산기</title>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    
    <script>
    $(document).ready(function() {
        let root = "<%= request.getContextPath() %>";
        if (root && root.length > 0 && root.charAt(root.length - 1) !== '/') {
             root += '/';
        }

        $("#calcForm").submit(function(event) {
            event.preventDefault(); 
            
            const dataObject = {
                left: $("#left").val(),
                right: $("#right").val(),
                operator: $("#operator").val()
            };
            
            const acceptFormat = $("input[name='accept']:checked").val(); 

            $("#resultArea").text("계산 중..."); 
            $("input[type='submit']").prop('disabled', true); 

            $.ajax({
                url: root + "calc/calculate", 
                method: "get",
                data: dataObject,
                headers: {
                    "Accept": acceptFormat === "json" ? "application/json" : "text/html"
                },
                dataType: "text", 
                
                complete: function() {
                    $("input[type='submit']").prop('disabled', false); 
                },

                success: function(responseString) { 
                    // 디버깅용
                    console.log("서버 응답 문자열 전체:", responseString); 

                    if (acceptFormat == "json") {
                        try {
                            const resultObject = JSON.parse(responseString.trim());
                            
                            const { num1, operator: op, num2, result: finalResult } = resultObject; 

                            if (num1 !== undefined && op !== undefined && num2 !== undefined && finalResult !== undefined) {
                                const resultText = num1 + " " + op + " " + num2 + " = " + finalResult;
                                $("#resultArea").text(resultText);
                            } else {
                                $("#resultArea").text("JSON 응답 데이터가 불완전합니다. 받은 데이터: " + responseString.trim());
                            }

                        } catch (e) {
                            console.error("JSON 파싱 실패:", e);
                            $("#resultArea").text("JSON 파싱 실패: 서버 응답 확인 필요. " + e.message);
                        }
                    } else {
                        // HTML 결과 형식
                        $("#resultArea").html(responseString.trim()); 
                    }
                },
                error: function(xhr) {
                    let errorMessage = `오류: ${xhr.status} - `;
                    let responseText = xhr.responseText || ""; 

                    if (xhr.status === 400) {
                        errorMessage += responseText.length > 0 ? responseText : "입력 값을 확인해 주세요 (400 Bad Request).";
                    } else if (xhr.statusText) {
                        errorMessage += xhr.statusText;
                    } else {
                        errorMessage += "알 수 없는 오류 발생";
                    }
                    
                    $("#resultArea").text(errorMessage);
                    $("input[type='submit']").prop('disabled', false); 
                }
            });
        });
    });
    </script>
    
    <style>
        body { font-family: Arial, sans-serif; margin: 30px; background-color: #f7f7f7;}
        h1, h4 { color: #333; }
        input[type="text"], select, label { 
            margin: 5px; padding: 10px; font-size: 16px; border: 1px solid #ccc; border-radius: 5px;
        }
        input[type="submit"] {
            padding: 10px 20px;
            font-size: 18px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s;
        }
        input[type="submit"]:hover {
            background-color: #45a049;
        }
        #resultArea { 
            margin-top: 20px; 
            font-weight: bold; 
            padding: 15px; 
            border: 2px solid #ddd; 
            background-color: white; 
            border-radius: 8px;
            font-size: 1.2em;
            color: #007bff;
        }
        #acceptFormat label {
            display: inline-block;
            margin-right: 15px;
            cursor: pointer;
        }
    </style>
=======
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resource/js/app/calculate.js"></script>
>>>>>>> branch 'main' of https://github.com/LEE2612/ddit.git
</head>
<body>
<<<<<<< HEAD
<h1>AJAX 계산기</h1>

<h4>수신 희망 컨텐츠 형식 선택</h4>
<div id="acceptFormat">
    <label><input type="radio" name="accept" value="html" checked />HTML</label>
    <label><input type="radio" name="accept" value="json" />JSON</label>
</div>

<form id="calcForm">
    <input id="left" name="left" type="text" placeholder="숫자 입력">
    <select id="operator" name="operator">
        <option>+</option>
        <option>-</option>
        <option>*</option>
        <option>/</option>
    </select>
    <input id="right" name="right" type="text" placeholder="숫자 입력">
    
    <input type="submit" value="계산"/> 
</form>

<div id="resultArea">
    ex) 2 + 2 = 4 (결과는 여기에 표시)
</div>
=======
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

>>>>>>> branch 'main' of https://github.com/LEE2612/ddit.git
</body>
</html>
