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

@WebServlet("/AdminRegister")
public class AdminRegisterServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
       
        System.out.println("Password : " + password);
        System.out.println("Confirm Password : " + confirmPassword);
        HttpSession session = request.getSession();

        // Confirm Password Check
        if (!password.equals(confirmPassword)) {

            session.setAttribute("error",
                    "Password and Confirm Password do not match.");

            response.sendRedirect("admin/register.jsp");
            return;
        }

        AdminDao dao = new AdminDao();

        // Email Already Exists
        if (dao.emailExists(email)) {

            session.setAttribute("error",
                    "Email already registered.");

            response.sendRedirect("register.jsp");
            return;
        }

        // Password Hash
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        Admin admin = new Admin();

        admin.setName(name);
        admin.setEmail(email);
        admin.setPassword(hashedPassword);

        int result = dao.registerAdmin(admin);

        if (result > 0) {

            session.setAttribute("success",
                    "Registration Successful. Please Login.");

            response.sendRedirect("login.jsp");

        } else {

            session.setAttribute("error",
                    "Registration Failed.");

            response.sendRedirect("register.jsp");
        }

    }

}