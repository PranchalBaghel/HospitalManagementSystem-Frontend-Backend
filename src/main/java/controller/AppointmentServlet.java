package controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import entity.Appointment;
import model.AppointmentDao;

@WebServlet("/appointments")
public class AppointmentServlet extends HttpServlet {

    AppointmentDao dao = new AppointmentDao();

    
    // GET (LIST + DELETE + PAGING)

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("delete".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            dao.deleteAppointment(id);
            response.sendRedirect("appointments");
            return;
        }
        
        //edit
        if ("edit".equals(action)) {

            int id = Integer.parseInt(request.getParameter("id"));

            Appointment ap = dao.getById(id);

            request.setAttribute("appointment", ap);
        }

        //search
        String search = request.getParameter("search");

        if (search != null && !search.trim().isEmpty()) {

            List<Appointment> list = dao.searchAppointments(search);

            request.setAttribute("appointments", list);

            request.getRequestDispatcher("appointment.jsp").forward(request, response);

            return;
        }
        //paging
        int page = 1;
        int recordsPerPage = 3;

        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        int start = (page - 1) * recordsPerPage;

        // Join query wali list
        List<Appointment> list = dao.getAppointmentsByPage(start, recordsPerPage);

        int totalRecords = dao.getTotalAppointments();
        int totalPages = (int) Math.ceil((double) totalRecords / recordsPerPage);

        request.setAttribute("appointments", list);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);

        request.getRequestDispatcher("appointment.jsp").forward(request, response);
    }
   
    // POST (ADD + UPDATE STATUS)
  
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        // ADD
        if (action != null && action.equals("add")) {

            Appointment a = new Appointment();

            a.setAppointmentDate(Date.valueOf(request.getParameter("date")));
            String time = request.getParameter("time");

            if (time != null && time.length() == 5) {
                time = time + ":00";
            }

            a.setAppointmentTime(Time.valueOf(time));
            a.setPatientId(Integer.parseInt(request.getParameter("patientId")));
            a.setDoctorId(Integer.parseInt(request.getParameter("doctorId")));
            a.setStatus("Pending");

            dao.addAppointment(a);

            response.sendRedirect("appointments");
        }

        // UPDATE STATUS
        else if (action != null && action.equals("updateStatus")) {

            int id = Integer.parseInt(request.getParameter("id"));
            String status = request.getParameter("status");

            dao.updateStatus(id, status);

            response.sendRedirect("appointments");
        }
    }
}