package kr.or.ddit;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/*
  2025/11/27 이현규
  1. DTO 제작
  	- name이랑 desc 정의
  	- Getter 메서드
  2. Service 제작
  	- 대상 홈페이지 스크랩 기능 수행
  3. Servlet 제작
  	- 요청 응답
  4. JSP 페이지 제작
  	- 페이지 구성
  * 실험적 항목 제외 실패
*/


@WebServlet("/proxy/http-headers")
public class HttpHeadersProxyServlet extends HttpServlet {

    private final HttpHeaderService service = new HttpHeaderService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json;charset=UTF-8");
        List<HeaderInfo> headers = service.getHttpHeaders();

        Gson gson = new Gson();
        response.getWriter().write(gson.toJson(headers));
    }
}
