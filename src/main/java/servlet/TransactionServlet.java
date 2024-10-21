package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.DeleteTransactionLogic;
import model.GetTransactionListLogic;
import model.Transaction;

@WebServlet("/TransactionServlet")
public class TransactionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		GetTransactionListLogic getTransactionListLogic = new GetTransactionListLogic();

		HttpSession session = request.getSession();
		String userId = (String)session.getAttribute("userId");
		
		List<Transaction> transactionList = getTransactionListLogic.execute(userId);
		request.setAttribute("transactionList", transactionList);
		
		if (userId == null) {
			response.sendRedirect("welcome.jsp");
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/main.jsp");
			dispatcher.forward(request, response);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		List<String> errorList = new ArrayList<String>();
		
		int transactionId = Integer.parseInt(request.getParameter("id"));
		
		DeleteTransactionLogic deleteTransactionLogic = new DeleteTransactionLogic();
		if (!deleteTransactionLogic.execute(transactionId)) {
			errorList.add("失敗しました");
			request.setAttribute("errorList", errorList);
		}
		
		GetTransactionListLogic getTransactionListLogic = new GetTransactionListLogic();
		
		HttpSession session = request.getSession();
		String userId = (String)session.getAttribute("userId");
		
		List<Transaction> transactionList = getTransactionListLogic.execute(userId);
		request.setAttribute("transactionList", transactionList);
		
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/main.jsp");
		dispatcher.forward(request, response);

			
	}

}
