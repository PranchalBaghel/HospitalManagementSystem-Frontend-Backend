<%@ page import="java.util.*, entity.Doctor" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
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
<title>Doctor Management</title>

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

<h2 style="text-align:center;">Doctor Management</h2>
<br>
<br>

<!-- ADD DOCTOR -->

<h3>Add Doctor</h3>
<br>
<form action="doctors" method="post">
    <input type="hidden" name="action" value="add">

    <input type="text" name="name" placeholder="Name" required>
    <input type="email" name="email" placeholder="Email">
    <input type="text" name="phone" placeholder="Phone">
    <input type="text" name="specialization" placeholder="Specialization"> 
    <input type="number" name="experience" placeholder="Experience"><br>
    <input type="number" name="departmentId" placeholder="Department ID">
    <button type="submit">Add Doctor</button>
</form>

<hr>


<!-- SEARCH -->
<br>
<form action="doctors" method="get" class="search-bar">
    <input type="text" name="search" placeholder="Search Doctor Name">
    <button type="submit">Search</button>
</form>

<hr>
<br>

<!-- UPDATE FORM -->


<%
Doctor editDoctor = (Doctor) request.getAttribute("doctor");
if(editDoctor != null){
%>

<h3>Update Doctor</h3>
<br>
<form action="doctors" method="post">
    <input type="hidden" name="action" value="update">
    <input type="hidden" name="id" value="<%=editDoctor.getId()%>">

    <input type="text" name="name" value="<%=editDoctor.getName()%>">
    <input type="email" name="email" value="<%=editDoctor.getEmail()%>">
    <input type="text" name="phone" value="<%=editDoctor.getPhone()%>">
    <input type="text" name="specialization" value="<%=editDoctor.getSpecialization()%>">
    <input type="number" name="experience" value="<%=editDoctor.getExperience()%>"><br>
    <input type="number" name="departmentId" value="<%=editDoctor.getDepartmentId()%>">

    <button type="submit">Update Doctor</button>
</form>

<hr>

<% } %>


<!-- DOCTOR LIST -->

<div class="recent-section">
<h3>Doctor List</h3>
<br>
<table class="recent-table">
<thead>
<tr>
    <th>ID</th>
    <th>Name</th>
    <th>Email</th>
    <th>Phone</th>
    <th>Specialization</th>
    <th>Experience</th>
    <th>Department</th>
    <th>Action</th>
</tr>
</thead>
<tbody>
<%
List<Doctor> list = (List<Doctor>) request.getAttribute("doctors");

if(list != null){
    for(Doctor d : list){
%>

<tr>
    <td><%=d.getId()%></td>
    <td><%=d.getName()%></td>
    <td><%=d.getEmail()%></td>
    <td><%=d.getPhone()%></td>
    <td><%=d.getSpecialization()%></td>
    <td><%=d.getExperience()%></td>
    <td><%=d.getDepartmentId()%></td>

    <td>
        <a href="doctors?action=edit&id=<%=d.getId()%>"><i class="bi bi-pencil-square" style="color:#113167;"></i></a>
        <a href="doctors?action=delete&id=<%=d.getId()%>"
           onclick="return confirm('Delete doctor?')"><i class="bi bi-trash3-fill" style="color:#113167;"></i></a>
    </td>
</tr>

<%
    }
}
%>
</tbody>

</table>

</div>
<!-- PAGINATION -->


<%
Integer currentPage = (Integer) request.getAttribute("currentPage");
Integer totalPages = (Integer) request.getAttribute("totalPages");

if(currentPage != null && totalPages != null){
%>

<div class="pagination" >

    <% if(currentPage > 1){ %>
        <a href="doctors?page=<%=currentPage-1%>">Prev</a>
    <% } %>

    <% for(int i=1; i<=totalPages; i++){ %>

        <a style="background:<%= (i==currentPage) ? "#113167" : "white" %>;
        color:<%= (i==currentPage) ? "white" : "#113167" %>;"
           href="doctors?page=<%=i%>">
           <%=i%>
        </a>

    <% } %>

    <% if(currentPage < totalPages){ %>
        <a href="doctors?page=<%=currentPage+1%>">Next</a>
    <% } %>

</div>

<% } %>

</div>
</div>

</body>
</html>