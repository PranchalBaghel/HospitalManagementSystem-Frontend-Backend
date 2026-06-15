package controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.DepartmentDao;
import entity.Department;

@WebServlet("/departments")
public class DepartmentServlet extends HttpServlet {

    private DepartmentDao dao = new DepartmentDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        try {

            //delete
            if ("delete".equals(action)) {

                int id = Integer.parseInt(request.getParameter("id"));
                dao.deleteDepartment(id);

                response.sendRedirect("departments");
                return;
            }

            //edit
            if ("edit".equals(action)) {

                int id = Integer.parseInt(request.getParameter("id"));

                Department d = dao.getById(id);

                request.setAttribute("department", d);
            }

            //search + list
            String keyword = request.getParameter("search");

            List<Department> list;

            if (keyword != null && !keyword.trim().isEmpty()) {

                list = dao.searchDepartments(keyword);

            } else {

                list = dao.getAllDepartments();
            }

            request.setAttribute("departments", list);

            request.getRequestDispatcher("department.jsp")
                   .forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        try {

            //add
            if ("add".equals(action)) {

                Department d = new Department();

                d.setName(request.getParameter("name"));
                d.setDescription(request.getParameter("description"));

                dao.addDepartment(d);

                response.sendRedirect("departments");
                return;
            }

            //update
            if ("update".equals(action)) {

                Department d = new Department();

                d.setId(Integer.parseInt(request.getParameter("id")));
                d.setName(request.getParameter("name"));
                d.setDescription(request.getParameter("description"));

                dao.updateDepartment(d);

                response.sendRedirect("departments");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}