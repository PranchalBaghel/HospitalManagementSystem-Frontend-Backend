
<%@ page import="java.util.*, java.util.HashMap, java.util.Map"%>
<%@ page import="entity.Bill, entity.Patient"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
response.setHeader("Pragma", "no-cache");
response.setDateHeader("Expires", 0);

HttpSession session1 = request.getSession(false);

if(session1 == null || session1.getAttribute("admin") == null){
    response.sendRedirect("login.jsp");
    return;
}
%>
<%
Bill bill = (Bill) request.getAttribute("bill");
List<Patient> patients = (List<Patient>) request.getAttribute("patients");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Billing Management</title>

<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">

<style>

h2 {
	margin-bottom: 15px;
}

.form-group {
	margin-bottom: 15px;
}

input, select {
	width: 100%;
	padding: 10px;
	border: 1px solid #ccc;
	border-radius: 5px;
}

.btn {
	padding: 10px 15px;
	border: none;
	border-radius: 5px;
	cursor: pointer;
}

.btn-secondary {
	background: #6c757d;
	color: #fff;
}

</style>

</head>
<body>
	<%@ include file="sidebar.jsp"%>

	<div class="main">

		<!--  ADD / EDIT BILL FORM  -->
		<%@ include file="navbar.jsp"%>
		
		<h2 style="text-align:center;">Bill Info</h2>
		<div class="card">

			<h3 >
				<i class="bi bi-receipt"></i>
				Add Bill
			</h3>
<br>
			<form action="bills" method="post">

				<%
				if (bill != null) {
				%>
				<input type="hidden" name="id" value="<%=bill.getId()%>">
				<%
				}
				%>

				<input type="hidden" name="action"
					value="<%=(bill != null) ? "update" : "add"%>">

				<!-- Patient -->
				<div class="form-group">
					<label>Patient</label> <select name="patientId" required>
						<option value="">Select Patient</option>

						<%
						for (Patient p : patients) {
						%>

					<option value="<%=p.getId()%>"
    <%= (bill != null && bill.getPatientId() == p.getId()) ? "selected" : "" %>>

    P-<%=p.getId()%> | <%=p.getName()%>

</option> 	

						<%
						}
						%>

					</select>
				</div>

				<!-- Amount -->
				<div class="form-group">
					<label>Amount</label> <input type="number" name="amount"
						value="<%=(bill != null) ? bill.getAmount() : ""%>" required>
				</div>

				<!-- Status -->
				<div class="form-group">
					<label>Status</label> <select name="status">
						<option value="Pending"
							<%=(bill == null || !bill.isPaid()) ? "selected" : ""%>>
							Pending</option>

						<option value="Paid"
							<%=(bill != null && bill.isPaid()) ? "selected" : ""%>>
							Paid</option>
					</select>
				</div>

				<button type="submit" >
					<i class="bi bi-save"></i> Save
				</button>

				<a href="bills" class="btn btn-secondary"> Reset </a>

			</form>

		</div>

		<!--  SEARCH BOX  -->

		<div class="card">


			<form action="bills" method="get" class="search-bar">

				<input type="text" name="search"
					placeholder="Search by patient name...">

				<button type="submit">Search</button>

			</form>

		</div>

		<!--  BILL LIST  -->

		

		<%
    List<Bill> bills = (List<Bill>) request.getAttribute("bills");

    if (bills == null) {
        bills = new ArrayList<>();
    }
%>

<div class="recent-section">

    <h3>Bill List</h3>
<br>
    <table class="recent-table">

        <thead>
            <tr>
                <th>Bill ID</th>
                <th>Patient ID</th>
                <th>Patient Name</th>
                <th>Amount</th>
                <th>Status</th>
                <th>Action</th>
            </tr>
        </thead>

        <tbody>

        <%
            if (bills.size() > 0) {
                for (Bill b : bills) {
        %>

            <tr>
                <td>BILL-<%=b.getId()%></td>

                <td><%=b.getPatientId()%></td>

                <td><%=b.getPatientName()%></td>

                <td>₹<%=b.getAmount()%></td>

                <td>
                    <% if (b.isPaid()) { %>
                        <span style="color:green;">Paid</span>
                    <% } else { %>
                        <span style="color:orange;">Pending</span>
                    <% } %>
                </td>

                <td>
                    <a href="bills?action=edit&id=<%=b.getId()%>">
                        <i class="bi bi-pencil-square" style="color:#113167;"></i>
                    </a>

                    <a href="bills?action=delete&id=<%=b.getId()%>"
                       onclick="return confirm('Delete this bill?')">
                        <i class="bi bi-trash3-fill" style="color:#113167;"></i>
                    </a>
                </td>
            </tr>

        <%
                }
            } else {
        %>

            <tr>
                <td colspan="6" style="text-align:center;">
                    No Bills Found
                </td>
            </tr>

        <%
            }
        %>

        </tbody>

    </table>

</div>


		<!--  PAGINATION  -->

		<%
		int currentPage = (request.getAttribute("currentPage") != null) ? (Integer) request.getAttribute("currentPage") : 1;

		int totalPages = (request.getAttribute("totalPages") != null) ? (Integer) request.getAttribute("totalPages") : 1;
		%>

		<div class="pagination">

			<%
			if (currentPage > 1) {
			%>
			<a href="bills?page=<%=currentPage - 1%>"> Prev </a>
			<%
			}
			%>

			<%
			for (int i = 1; i <= totalPages; i++) {
			%>

			<a href="bills?page=<%=i%>"
				class="<%=(i == currentPage) ? "active" : ""%>"> <%=i%>

			</a>

			<%
			}
			%>

			<%
			if (currentPage < totalPages) {
			%>
			<a href="bills?page=<%=currentPage + 1%>"> Next </a>
			<%
			}
			%>

		</div>
	</div>
</body>
</html>