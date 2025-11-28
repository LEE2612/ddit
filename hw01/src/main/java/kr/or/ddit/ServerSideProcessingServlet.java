package kr.or.ddit;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/*
	2025/11/28 이현규 사칙연산 part1
	- jsp 입력페이지와 사칙연산기능을 수행하는 서블릿 제작
	- jsp 페이지 제작전에 테스터 외부 api를 사용하여 작동 여부 확인
	- jsp 페이지는 webapp안의 calc/calculate폴더 경로에 생성 이름은 calc.jsp
	- http://localhost/hw02/calc/calculate/calc.jsp 입력 페이지 경로
	- ex) 1 + 2 결과값 경로 http://localhost/hw02/calc/calculate?left=1&operator=%2B&right=2
	- 싱글페이지가 아니라 멀티페이지로 구현
	- 상태코드 구현
*/

@WebServlet("/calc/calculate")
public class ServerSideProcessingServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
//		System.out.println("수신 받았쪄"); // 수신 잘되는지 콘솔 로그 출력
		String left = req.getParameter("left"); // 외부 요청 수신 받기 (테스터)
		String right = req.getParameter("right");
		String operator = req.getParameter("operator");
		
//		System.out.println(left); // 테스터 외부 app로 받은 문자열값 잘 나오는지 출력
//		System.out.println(right);
//		System.out.println(operator);
		
		int num1 = Integer.parseInt(left); // 받은 문자열값 숫자로 변환
		int num2 = Integer.parseInt(right);
		
//		System.out.println(num1); // 숫자로 변환 잘됐는지 확인용 변환값 출력
//		System.out.println(num2);
		
		// 스위치 문으로 변환한 숫자들을 이용해서 연산 처리
		int result = 0; // 결과값용 int
		switch(operator) {
		case "+" : result = num1 + num2; break;
		case "-" : result = num1 - num2; break;
		case "*" : result = num1 * num2; break;
		case "/" : result = num1 / num2; break;
		default: System.out.println("정확한 값을 입력하십시오");
		}
		
		System.out.println(num1 + operator + num2 + "="+ result); // 숫자값, 연산기호, 연산식, 결과값 잘 나오는지 확인
		
		resp.setContentType("text/html;charset=UTF-8"); // 응답 구문
		
		try (PrintWriter out = resp.getWriter()) { // 향상된 트라이 / 리소스문 사용
			out.print("result: "+result); // 결과값 응답
		}
	}
}
