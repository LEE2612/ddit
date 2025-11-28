package kr.or.ddit;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/currency/exchange")
public class CurrencyExchangeServlet extends HttpServlet {
	private static final double RATE = 1466.0; // 기준 환율 1달러 = 1466원

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<title>원/달러 환율 계산기</title>");
		out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css' rel='stylesheet'>");
		out.println("<style>"
				+ "input[type=number]::-webkit-inner-spin-button,input[type=number]::-webkit-outer-spin-button{-webkit-appearance:none;margin:0;}"
				+ "input[type=number]{-moz-appearance:textfield;}"
				+ "</style>");
		out.println("</head>");
		out.println("<body class='container mt-5'>");
		out.println("<h2>원/달러 환율 계산기</h2>");
		out.println("<form method='post' action='/hw01/currency/exchange' class='d-flex align-items-center gap-2'>");

		// 라벨
		out.println("<label for='amount' class='form-label mb-0'>원화 입력:</label>");

		// 입력창
		out.println("<input type='number' id='amount' name='amount' step='1' min='0' required class='form-control' style='width:150px;'>");

		// 계산 버튼
		out.println("<button type='submit' class='btn btn-primary'>계산</button>");

		out.println("</form>");

		// 환율 안내 (폼 아래)
		out.println("<div style='font-size:0.9rem; color:gray; margin-top:5px;'>기준 환율: 1달러 = 1466원</div>");
		out.println("</body>");
		out.println("</html>");

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		String amountStr = request.getParameter("amount");
		double won = 0;
		double dollar = 0;

		try {
			won = Double.parseDouble(amountStr);
			dollar = won / RATE;
		} catch (NumberFormatException e) {
			out.println("<p>잘못된 금액 입력입니다.</p>");
		}

		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<title>환율 계산 결과</title>");
		out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css' rel='stylesheet'>");
		out.println("</head>");
		out.println("<body class='container mt-5'>");

		out.println("<h2>환율 계산 결과</h2>");
		out.println("<div class='card mt-3' style='width: 22rem;'>"); // card start
		out.println("<div class='card-header'>환율 계산 결과</div>"); // card-header
		// 카드 본문
		out.println("<div class='card-body'>"); // card-body start

		out.printf("<p class='card-text'><strong>입력한 금액:</strong> %.0f 원</p>%n", won);
		out.println("<hr>"); // 밑줄
		out.printf("<p class='card-text'><strong>환산 금액:</strong> %.2f 달러</p>%n", dollar);
		out.println("<hr>"); // 밑줄

		// 다시 계산 버튼
		out.println("<a href='/hw01/currency/exchange' class='btn btn-success mt-3'>다시 계산</a>");

		out.println("</div>"); // card-body end
		out.println("</div>"); // card end

		out.println("</body>");
		out.println("</html>");

	}
}
