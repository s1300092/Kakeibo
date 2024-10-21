package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.GetTransactionListLogic;
import model.Transaction;
import model.TransactionLogic;

@WebServlet("/PostTransactionServlet")
public class PostTransactionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/entry.jsp");
		dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		List<String> errorList = new ArrayList<String>();
		
		String purpose = request.getParameter("purpose");
		String moneyStr = request.getParameter("money");
		String dateStr = request.getParameter("date");
		int money = -1;
		LocalDate date = null;
		
		boolean f = true;
		
		if (purpose == null || purpose.length() == 0) {
			errorList.add("用途が入力されていません");
			f = false;
		}
		
		if (moneyStr == null || moneyStr.length() == 0) {
			errorList.add("金額が入力されていません");
			f = false;	
		} else {
			money = Integer.parseInt(moneyStr);	
		}
		
		if (dateStr == null || dateStr.length() == 0) {
			errorList.add("日付が入力されていません");
			f = false;	
		} else {
			date = LocalDate.parse(dateStr);
		}
		
		if (f) {
			HttpSession session = request.getSession();
			String userId = (String)session.getAttribute("userId");
			
			Transaction t = new Transaction(purpose, money, date, userId);
			
			TransactionLogic transactionLogic = new TransactionLogic();
			
		    if (transactionLogic.execute(t, errorList)) {

		    	GetTransactionListLogic getTransactionListLogic = new GetTransactionListLogic();
		    	
				List<Transaction> transactionList = getTransactionListLogic.execute(userId);
				request.setAttribute("transactionList", transactionList);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/main.jsp");
				dispatcher.forward(request, response);
		
			} else {
				request.setAttribute("errorList", errorList);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/entry.jsp");
				dispatcher.forward(request, response);	
			}
		 }  else {
			request.setAttribute("errorList", errorList);
				
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/entry.jsp");
			dispatcher.forward(request, response);	
		}
		
    }

}
