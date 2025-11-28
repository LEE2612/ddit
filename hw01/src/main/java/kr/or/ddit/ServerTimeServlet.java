package kr.or.ddit;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/*
 2025/11/24 이현규
 1. 컨텍스트 패스 설정
 2. 스크립트 폴더 생성 및 js파일 생성
 3. js페이지에서 현재시간 출력 및 초당 자동 새로고침 시간 출력 기능 구현
 4. 서블릿 요청을 jsp페이지에서 출력되게 설정
 5. 서버시간 저장 구역 및 버튼 구현
 6. 버튼 클릭하면 현재 서버시간 저장 기능 구현
*/

// 요청 수신
@WebServlet("/01/server-now")
public class ServerTimeServlet extends HttpServlet{
	
	// 서버가 돌아갈동안 유지되는 서버 메모리 스태틱
	private static List<String> savedTimes = new ArrayList<>();

	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // mode 파라미터 확인
        String mode = req.getParameter("mode");

        // 현재 서버시간 생성
        String serverTime = new Date().toString();

        // Content-Type 설정
        resp.setContentType("application/json; charset=UTF-8");
        PrintWriter out = resp.getWriter();

        // mode=save -> 현재 서버시간 저장
        if ("save".equals(mode)) {
            savedTimes.add(serverTime);

            // JSON 배열 생성
            StringBuilder json = new StringBuilder();
            json.append("{\"saved\":[");

            for (int i = 0; i < savedTimes.size(); i++) {
                json.append("\"").append(savedTimes.get(i)).append("\"");
                if (i < savedTimes.size() - 1) json.append(",");
            }
            json.append("]}");

            out.print(json.toString());
        }
        // 기본 모드 -> 서버시간 반환
        else {
            out.print("{\"serverTime\": \"" + serverTime + "\", \"status\": \"OK\"}");
        }

        out.flush();
    }
}
