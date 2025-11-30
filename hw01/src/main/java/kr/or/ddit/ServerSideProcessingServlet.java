package kr.or.ddit;

import java.io.IOException;
import java.io.PrintWriter;

<<<<<<< HEAD
=======
import javax.management.RuntimeErrorException;

import jakarta.servlet.RequestDispatcher;
>>>>>>> branch 'main' of https://github.com/LEE2612/ddit.git
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

<<<<<<< HEAD
=======
/*
	2025/11/28 이현규 사칙연산 part2
	- jsp 입력페이지와 사칙연산기능을 수행하는 서블릿 제작
	- jsp 페이지 제작전에 테스터 외부 api를 사용하여 작동 여부 확인 및 오류코드 처리 정상 작동 확인
	- jsp 페이지는 webapp안의 calc/calculate폴더 경로에 생성 이름은 calc.jsp
	- http://localhost/hw02/calc/calculate/calc.jsp 입력 페이지 경로
	- ex) 1 + 2 결과값 경로 http://localhost/hw02/calc/calculate?left=1&operator=%2B&right=2
	- 싱글페이지가 아니라 멀티페이지로 구현 / 입력창 => 결과창
	- 상태코드 구현 완료 = 값이 입력되지 않았을때, 입력받은 값이 숫자가 아닐때, 기호가 잘못되었을때, 결과값이 올바르지 않을때
	===================part1==================
	- 체크박스 html or json 출력 형식 분리
	- jsp에서 유효성 검사 실행
	- 
 */

>>>>>>> branch 'main' of https://github.com/LEE2612/ddit.git
@WebServlet("/calc/calculate")
public class ServerSideProcessingServlet extends HttpServlet {

<<<<<<< HEAD
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setCharacterEncoding("UTF-8");

        String left = req.getParameter("left");
        String right = req.getParameter("right");
        String operator = req.getParameter("operator");

        if(left == null || right == null || left.isBlank() || right.isBlank()) {
            resp.sendError(400, "값이 입력되지 않았습니다");
            return;
        }

        int num1, num2;
        try {
            num1 = Integer.parseInt(left);
            num2 = Integer.parseInt(right);
        } catch (NumberFormatException e) {
            resp.sendError(400, "숫자만 입력 가능합니다");
            return;
        }

        int result;
        switch(operator) {
            case "+": result = num1 + num2; break;
            case "-": result = num1 - num2; break;
            case "*": result = num1 * num2; break;
            case "/":
                if(num2 == 0) {
                    resp.sendError(400, "0으로 나눌 수 없습니다");
                    return;
                }
                result = num1 / num2;
                break;
            default:
                resp.sendError(400, "잘못된 연산자입니다");
                return;
        }

        String accept = req.getHeader("Accept");
        
        if(accept != null && accept.contains("application/json")) {
            resp.setContentType("application/json;charset=UTF-8");
            try(PrintWriter out = resp.getWriter()) {
                String jsonResponse = String.format(
                    "{\"num1\":%d, \"operator\":\"%s\", \"num2\":%d, \"result\":%d}", 
                    num1, operator, num2, result
                );
                out.print(jsonResponse);
            }
            return; 
        } 
        else {
            resp.setContentType("text/html;charset=UTF-8");
            try(PrintWriter out = resp.getWriter()) {
                out.print("<h1>"+num1 + operator + num2 + "=" + result + "</h1>");
            }
            return; 
        }
    }
}
=======
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		// 1. 데이터 선언 영역

		String left = req.getParameter("left"); // 외부 요청 수신 받기 (테스터)
		String right = req.getParameter("right");
		String operator = req.getParameter("operator");
		String accept = req.getParameter("accept");

		if(left.equals("") || right.equals("")) {
			resp.sendError(400, "값이 입력되지 않았습니다");
			return;
		}

		// 받은 문자열값 숫자로 변환
		int num1 = Integer.parseInt(req.getParameter("left"));
		int num2 = Integer.parseInt(req.getParameter("right"));

		// 2. 함수 영역

		// 스위치 문으로 변환한 숫자들을 이용해서 연산 처리
		int result = 0; // 결과값용 int
		switch(operator) {
		case "+" : result = num1 + num2; break;
		case "-" : result = num1 - num2; break;
		case "*" : result = num1 * num2; break;
		case "/" : result = num1 / num2; break;
		default  : break;
		}

//		System.out.println(accept.getClass().getName());
		// 3. 리턴 영역
//		if(accept.equals("html")) { //html인경우
//			resp.setContentType("text/html;charset=UTF-8");// 응답 구문
//		}else { //json도 html도 아닌 경우
//			resp.setContentType("application/json;");
//		}
//		
//		if(resp.getStatus()==200) { 
//			try (PrintWriter out = resp.getWriter()) { // 향상된 트라이 / 리소스문 사용
//				System.out.println("정상 출력 확인");
//				out.print("result: "+result); // 결과값 응답
//			}
//		}else {
//			resp.sendError(400);
//			System.out.println("잘못된 입력입니다");
//		}
		
		if ("html".equals(accept)) {
		    resp.setContentType("text/html;charset=UTF-8");
		    try(PrintWriter out = resp.getWriter()) {
		        out.printf("<h3>계산 결과: %d</h3>", result);
		    }
		} else { // json이거나 기본 처리
		    resp.setContentType("application/json;charset=UTF-8");
		    try(PrintWriter out = resp.getWriter()) {
		        out.printf("{\"result\": %d}", result);
		    }
		}

		
		// resp.getWriter().write(gson);

		// 결과 html, json -> 결과 result : "4" 
		
		// System.out.println(num1 + operator + num2 + "="+ result); // 숫자값, 연산기호, 연산식, 결과값 잘 나오는지 확인




	}
}
>>>>>>> branch 'main' of https://github.com/LEE2612/ddit.git
