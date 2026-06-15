package controller;

import java.io.IOException;

import org.mindrot.jbcrypt.BCrypt;

import entity.Admin;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.AdminDao;

@WebServlet("/AdminLogin")
public class AdminLoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {

		String email = request.getParameter("email");
		String password = request.getParameter("password");

		HttpSession session = request.getSession();

		AdminDao dao = new AdminDao();

		Admin admin = dao.getAdminByEmail(email);

		if (admin != null && BCrypt.checkpw(password, admin.getPassword())) {

			// Login Success
			session.setAttribute("admin", admin);

			response.sendRedirect("index.jsp");

		} else {

			// Login Failed
			session.setAttribute("error", "Invalid Email or Password");

			response.sendRedirect("login.jsp");
		}

	}
}