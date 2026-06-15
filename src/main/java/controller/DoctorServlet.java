package controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.DoctorDao;
import entity.Doctor;

@WebServlet("/doctors")
public class DoctorServlet extends HttpServlet {

    private DoctorDao dao = new DoctorDao();

    //get : list ,search , delete , edit , pagination
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        String view = request.getParameter("view");
        
        try {

           //delete
            if ("delete".equals(action)) {

                int id = Integer.parseInt(request.getParameter("id"));
                dao.deleteDoctor(id);

                response.sendRedirect("doctors");
                return;
            }

            //edit
            if ("edit".equals(action)) {

                int id = Integer.parseInt(request.getParameter("id"));
                Doctor d = dao.getById(id);

                request.setAttribute("doctor", d);
            }
            
            //for navbar
            if ("list".equals(view)) {

                List<Doctor> list = dao.getAllDoctors(); // NO paging, NO search

                request.setAttribute("doctors", list);

                request.getRequestDispatcher("doctorList.jsp")
                       .forward(request, response);
                return;
            }
            
            //paging
            int page = 1;
            int recordsPerPage = 4;

            if (request.getParameter("page") != null) {
                page = Integer.parseInt(request.getParameter("page"));
            }

            int start = (page - 1) * recordsPerPage;

            //search
            String keyword = request.getParameter("search");

            List<Doctor> list;

            //search + paging fix
            if (keyword != null && !keyword.trim().isEmpty()) {
                list = dao.searchDoctors(keyword);
                request.setAttribute("search", keyword);
            } else {

               

                list = dao.getDoctorsByPage(start, recordsPerPage);

                int totalRecords = dao.getTotalDoctors();
                int totalPages = (int) Math.ceil(totalRecords * 1.0 / recordsPerPage);

                request.setAttribute("totalPages", totalPages);
                request.setAttribute("currentPage", page);
            }

            request.setAttribute("doctors", list);

            request.getRequestDispatcher("doctor.jsp")
                   .forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //post : add , update
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        try {

            //add doctor
            if ("add".equals(action)) {

                Doctor d = new Doctor();

                d.setName(request.getParameter("name"));
                d.setEmail(request.getParameter("email"));
                d.setPhone(request.getParameter("phone"));
                d.setSpecialization(request.getParameter("specialization"));
                d.setExperience(Integer.parseInt(request.getParameter("experience")));
                d.setDepartmentId(Integer.parseInt(request.getParameter("departmentId")));

                dao.addDoctor(d);

                response.sendRedirect("doctors");
                return;
            }

            //update
            if ("update".equals(action)) {

                Doctor d = new Doctor();

                d.setId(Integer.parseInt(request.getParameter("id")));
                d.setName(request.getParameter("name"));
                d.setEmail(request.getParameter("email"));
                d.setPhone(request.getParameter("phone"));
                d.setSpecialization(request.getParameter("specialization"));
                d.setExperience(Integer.parseInt(request.getParameter("experience")));
                d.setDepartmentId(Integer.parseInt(request.getParameter("departmentId")));

                dao.updateDoctor(d);

                response.sendRedirect("doctors");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}