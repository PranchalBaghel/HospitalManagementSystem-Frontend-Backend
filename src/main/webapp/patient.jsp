<%@ page import="java.util.*, entity.Patient" %>
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
<title>Patient Management</title>

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

<h2 style="text-align:center; ">Patient Management</h2>
<br>

<br>
<!-- ADD PATIENT -->
<h3>Add Patient</h3>
<br>
<form action="patients" method="post">
    <input type="hidden" name="action" value="add">

    <input type="text" name="name" placeholder="Name" required>
    <input type="email" name="email" placeholder="Email">
    <input type="text" name="phone" placeholder="Phone">
    <input type="text" name="gender" placeholder="Gender">
    <input type="number" name="age" placeholder="Age" required>
    <input type="text" name="address" placeholder="Address">
    <input type="text" name="disease" placeholder="Disease">
    <input type="text" name="blood" placeholder="Blood">
    
    <button type="submit">Add Patient</button>
</form>
<br>
<hr>
<br>

<!-- SEARCH PATIENT -->
<form action="patients" method="get" class="search-bar">
    <input type="hidden" name="action" value="search">
    <input type="text" name="search" placeholder="Search Patient Name">
    <button type="submit">Search</button>
</form>

<hr>

<!-- UPDATE FORM -->

<%
Patient editPatient = (Patient) request.getAttribute("patient");
if(editPatient != null){
%>
<br>
<h3>Update Patient</h3>
<br>
<form action="patients" method="post">
    <input type="hidden" name="action" value="update">
    <input type="hidden" name="id" value="<%=editPatient.getId()%>">

    <input type="text" name="name" value="<%=editPatient.getName()%>">
    <input type="email" name="email" value="<%=editPatient.getEmail()%>">
    <input type="text" name="phone" value="<%=editPatient.getPhone()%>">
    <input type="text" name="gender" value="<%=editPatient.getGender()%>">
    <input type="number" name="age" value="<%=editPatient.getAge()%>">
    <input type="text" name="address" value="<%=editPatient.getAddress()%>">
    <input type="text" name="disease" value="<%=editPatient.getDisease()%>">
    <input type="text" name="blood" value="<%=editPatient.getBlood()%>">

    <button type="submit">Update Patient</button>
</form>

<hr>

<% } %>

<!-- PATIENT LIST-->
<br>
<div class="recent-section">
<h3>Patient List</h3>
<br>
<table class="recent-table">
<thead>
<tr>
    <th>ID</th>
    <th>Name</th>
    <th>Email</th>
    <th>Phone</th>
    <th>Gender</th>
    <th>Age</th>
    <th>Address</th>
    <th>Disease</th>
    <th>Blood</th>
    <th>Action</th>
</tr>
</thead>
<tbody>

<%
List<Patient> list = (List<Patient>) request.getAttribute("patients");

if(list != null){
    for(Patient p : list){
%>

<tr>
    <td><%=p.getId()%></td>
    <td><%=p.getName()%></td>
    <td><%=p.getEmail()%></td>
    <td><%=p.getPhone()%></td>
    <td><%=p.getGender()%></td>
    <td><%=p.getAge()%></td>
    <td><%=p.getAddress()%></td>
    <td><%=p.getDisease()%></td>
    <td><%=p.getBlood()%></td>

    <td>
        <a href="patients?action=edit&id=<%=p.getId()%>"><i class="bi bi-pencil-square" style="color:#113167;"></i></a>
       
        <a href="patients?action=delete&id=<%=p.getId()%>"
           onclick="return confirm('Are you sure to delete?')">
           <i class="bi bi-trash3-fill" style="color:#113167;"></i>
        </a>
    </td>
</tr>

<%
    }
}
%>
</tbody>
</table>
</div>

<%
Integer cp = (Integer) request.getAttribute("currentPage");
Integer tp = (Integer) request.getAttribute("totalPages");

int currentPage = (cp != null) ? cp : 1;
int totalPages = (tp != null) ? tp : 1;
%>

<div class="pagination" >

<% if(currentPage > 1){ %>
<a href="patients?page=<%=currentPage-1%>">Prev</a>
<% } %>

<% for(int i=1; i<=totalPages; i++){ %>

<a href="patients?page=<%=i%>"
style="background:<%= (i==currentPage) ? "#113167" : "white" %>;
color:<%= (i==currentPage) ? "white" : "#113167" %>;">
<%=i%>
</a>

<% } %>

<% if(currentPage < totalPages){ %>
<a href="patients?page=<%=currentPage+1%>">Next</a>
<% } %>

</div>

</div>
</div>

</body>
</html>