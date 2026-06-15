package controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.PatientDao;
import entity.Patient;

@WebServlet("/patients")
public class PatientServlet extends HttpServlet {

    private PatientDao dao = new PatientDao();

    //list , search , delete
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        try {

            //delete
            if ("delete".equals(action)) {

                int id = Integer.parseInt(request.getParameter("id"));
                dao.deletePatient(id);

                response.sendRedirect("patients");
                return;
            }
            
            //edit
            if ("edit".equals(action)) {

                int id = Integer.parseInt(request.getParameter("id"));
                Patient p = dao.getById(id);

                request.setAttribute("patient", p);
            }

            //paging start
            int page = 1;
            int recordsPerPage = 4;

            if (request.getParameter("page") != null) {
                page = Integer.parseInt(request.getParameter("page"));
            }

            int start = (page - 1) * recordsPerPage;

            //search
            String keyword = request.getParameter("search");

            List<Patient> list;

            // Search + paging fix 
            if (keyword != null && !keyword.trim().isEmpty()) {

                list = dao.searchPatients(keyword);

                request.setAttribute("search", keyword);

            } else {

                list = dao.getPatientsByPage(start, recordsPerPage);

                int totalRecords = dao.getTotalPatients();
                int totalPages = (int) Math.ceil(totalRecords * 1.0 / recordsPerPage);

                request.setAttribute("totalPages", totalPages);
                request.setAttribute("currentPage", page);
            }

            request.setAttribute("patients", list);

            request.getRequestDispatcher("patient.jsp")
                   .forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //post : add, update
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        try {

            //add
            if ("add".equals(action)) {

                Patient p = new Patient();

                p.setName(request.getParameter("name"));
                p.setEmail(request.getParameter("email"));
                p.setPhone(request.getParameter("phone"));
                p.setGender(request.getParameter("gender"));
                p.setAge(Integer.parseInt(request.getParameter("age")));
                p.setAddress(request.getParameter("address"));
                p.setDisease(request.getParameter("disease"));
                p.setBlood(request.getParameter("blood"));

                dao.addPatient(p);

                response.sendRedirect("patients");
                return;
            }

            //update
            if ("update".equals(action)) {

                Patient p = new Patient();

                p.setId(Integer.parseInt(request.getParameter("id")));
                p.setName(request.getParameter("name"));
                p.setEmail(request.getParameter("email"));
                p.setPhone(request.getParameter("phone"));
                p.setGender(request.getParameter("gender"));
                p.setAge(Integer.parseInt(request.getParameter("age")));
                p.setAddress(request.getParameter("address"));
                p.setDisease(request.getParameter("disease"));
                p.setBlood(request.getParameter("blood"));

                dao.updatePatient(p);

                response.sendRedirect("patients");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}