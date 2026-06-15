<%@ page import="java.util.*, entity.Appointment" %>
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
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Appointment Management</title>

<link rel="stylesheet" href="css/style.css">

<link rel="stylesheet"
href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">

<link rel="stylesheet"
href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">

</head>

<body>

<%@ include file="sidebar.jsp" %>

<div class="main">

<%@ include file="navbar.jsp" %>

<div class="main-box">

<h2 style="text-align:center;">Appointment Management</h2>

<br><br>

<!-- ADD APPOINTMENT -->

<h3>Add Appointment</h3>
<br>

<form action="appointments" method="post">

    <input type="hidden" name="action" value="add">

    <input type="date" name="date" required>
    <input type="time" name="time" required>
    <input type="number" name="patientId" placeholder="Patient ID" required>
    <input type="number" name="doctorId" placeholder="Doctor ID" required>

    <button type="submit">Add Appointment</button>
</form>

<hr>

<!--  SEARCH  -->

<br>

<form action="appointments" method="get" class="search-bar">

    <input type="text" name="search" placeholder="Search Appointment">

    <button type="submit">Search</button>

</form>

<hr>

<br>

<!-- UPDATE FORM -->

<%
Appointment editAppt = (Appointment) request.getAttribute("appointment");

if(editAppt != null){
%>

<h3>Update Appointment Status</h3>
<br>

<form action="appointments" method="post">

    <input type="hidden" name="action" value="updateStatus">
    <input type="hidden" name="id" value="<%=editAppt.getId()%>">

    <select name="status">
        <option>Pending</option>
        <option>Confirmed</option>
        <option>Completed</option>
        <option>Cancelled</option>
    </select>

    <button type="submit">Update</button>

</form>

<hr>

<% } %>

<!--  LIST  -->

<div class="recent-section">

<h3>Appointment List</h3>
<br>

<table class="recent-table">

    <tr>
        <th>ID</th>
        <th>Patient</th>
        <th>Doctor</th>
        <th>Date</th>
        <th>Time</th>
        <th>Status</th>
        <th>Action</th>
    </tr>

<%
List<Appointment> list =
(List<Appointment>)request.getAttribute("appointments");

if(list != null && !list.isEmpty()){

    for(Appointment a : list){
%>

<tr>

    <td><%=a.getId()%></td>

    <td><%=a.getPatientName()%></td>

    <td><%=a.getDoctorName()%></td>

    <td><%=a.getAppointmentDate()%></td>

    <td><%=a.getAppointmentTime()%></td>

    <td><%=a.getStatus()%></td>

    <td>

        <a href="appointments?action=edit&id=<%=a.getId()%>" style="color:#113167";>
    <i class="bi bi-pencil-square"></i>
</a>

        <a href="appointments?action=delete&id=<%=a.getId()%>"
           onclick="return confirm('Delete this appointment?')" style="color:#113167";>

            <i class="bi bi-trash-fill"></i>

        </a>

    </td>

</tr>

<%
    }
}else{
%>

<tr>

<td colspan="7">No Appointments Found</td>

</tr>

<%
}
%>

</table>

</div>
<!--PAGINATION  -->

<%
Integer currentPage = (Integer) request.getAttribute("currentPage");
Integer totalPages = (Integer) request.getAttribute("totalPages");

if(currentPage != null && totalPages != null){
%>

<div class="pagination">

    <% if(currentPage > 1){ %>
        <a href="appointments?page=<%=currentPage-1%>">Prev</a>
    <% } %>

    <% for(int i=1; i<=totalPages; i++){ %>

        <a class="<%= (i==currentPage) ? "active" : "" %>"
           href="appointments?page=<%=i%>">
           <%=i%>
        </a>

    <% } %>

    <% if(currentPage < totalPages){ %>
        <a href="appointments?page=<%=currentPage+1%>">Next</a>
    <% } %>

</div>

<% } %>

</div>
</div>

</body>
</html>