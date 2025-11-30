package kr.or.ddit;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/calc/calculate")
public class ServerSideProcessingServlet extends HttpServlet {

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