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
import model.Account;
import model.RegistrationLogic;


@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/registration.jsp");
		dispatcher.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String userId = request.getParameter("userId");
		String pass1 = request.getParameter("pass1");
		String pass2 = request.getParameter("pass2");
		List<String> errorList = new ArrayList<String>();
		
		request.setAttribute("errorList", errorList);
		
		if (!pass1.equals(pass2)) {
			errorList.add("パスワードが違います");
			request.setAttribute("errorList", errorList);
			RequestDispatcher  dispatcher = request.getRequestDispatcher("WEB-INF/jsp/registration.jsp");
			dispatcher.forward(request, response);
	    } else {
			Account account = new Account(userId, pass1);
			RegistrationLogic bo = new RegistrationLogic();
			if (bo.execute(account, errorList)) {
				HttpSession session = request.getSession();
				session.setAttribute("userId", userId);
				
				RequestDispatcher  dispatcher = request.getRequestDispatcher("WEB-INF/jsp/loginOK.jsp");
				dispatcher.forward(request, response);	
			} else {
				request.setAttribute("errorList", errorList);
				
				RequestDispatcher  dispatcher = request.getRequestDispatcher("WEB-INF/jsp/registration.jsp");
				dispatcher.forward(request, response);
			}
			
				

		}
		
		
	}

}
