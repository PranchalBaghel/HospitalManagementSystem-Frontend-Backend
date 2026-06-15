package controller;

import java.io.IOException;
import java.util.List;

import entity.Bill;
import entity.Patient;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.BillDao;
import model.PatientDao;

@WebServlet("/bills")
public class BillServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    BillDao billDao = new BillDao();
    PatientDao patientDao = new PatientDao();

    //  GET 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        // Delete
        if ("delete".equals(action)) {

            int id = Integer.parseInt(request.getParameter("id"));

            billDao.deleteBill(id);

            response.sendRedirect("bills");
            return;
        }

        // Edit
        if ("edit".equals(action)) {

            int id = Integer.parseInt(request.getParameter("id"));

            Bill bill = billDao.getBillById(id);

            request.setAttribute("bill", bill);
        }

        // Search
        String search = request.getParameter("search");

        if (search != null && !search.trim().isEmpty()) {

            List<Bill> list = billDao.searchBills(search);

            request.setAttribute("bills", list);
            request.setAttribute("patients", patientDao.getAllPatients());

            request.getRequestDispatcher("bill.jsp").forward(request, response);
            return;
        }

        // Pagination
        int page = 1;
        int recordsPerPage = 3;

        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        int start = (page - 1) * recordsPerPage;

        List<Bill> list = billDao.getBillsByPage(start, recordsPerPage);

        int totalRecords = billDao.getTotalBills();

        int totalPages = (int) Math.ceil((double) totalRecords / recordsPerPage);

        request.setAttribute("bills", list);
        request.setAttribute("patients", patientDao.getAllPatients());

        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);

        request.getRequestDispatcher("bill.jsp").forward(request, response);
    }

    // POST 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        // Add Bill
        if ("add".equals(action)) {

            Bill bill = new Bill();

            bill.setPatientId(Integer.parseInt(request.getParameter("patientId")));
            bill.setAmount(Double.parseDouble(request.getParameter("amount")));

            String status = request.getParameter("status");
            bill.setPaid("Paid".equals(status));

            billDao.addBill(bill);

            response.sendRedirect("bills");
        }

        // Update Bill
        else if ("update".equals(action)) {

            Bill bill = new Bill();

            bill.setId(Integer.parseInt(request.getParameter("id")));
            bill.setPatientId(Integer.parseInt(request.getParameter("patientId")));
            bill.setAmount(Double.parseDouble(request.getParameter("amount")));

            String status = request.getParameter("status");
            bill.setPaid("Paid".equals(status));

            billDao.updateBill(bill);

            response.sendRedirect("bills");
        }

    }

}