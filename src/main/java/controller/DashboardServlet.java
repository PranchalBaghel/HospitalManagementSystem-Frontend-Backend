package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.DashboardDao;
import java.io.IOException;
import java.util.List;

import entity.Appointment;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 private DashboardDao dao = new DashboardDao();
    public DashboardServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		    int totalPatients = dao.getTotalPatients();
	        int totalDoctors = dao.getTotalDoctors();
	        int totalAppointments = dao.getTotalAppointments();
	        double totalRevenue = dao.getTotalRevenue();
	        List<Appointment> list = dao.getRecentAppointments();
	       

	        
	        request.setAttribute("totalPatients", totalPatients);
	        request.setAttribute("totalDoctors", totalDoctors);
	        request.setAttribute("totalAppointments", totalAppointments);
	        request.setAttribute("totalRevenue", totalRevenue);
	        request.setAttribute("appointmentsList", list);
	        request.setAttribute("workload", dao.getDoctorWorkload());
	        request.setAttribute("visitHistory", dao.getPatientVisitHistory());
	        
	        request.getRequestDispatcher("dashboard.jsp")
	               .forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}

}
