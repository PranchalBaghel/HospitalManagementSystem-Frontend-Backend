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
<title>Doctors List</title>

<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">

<style>


.page-title{ 
    font-size: 32px;
    color: #113167;
    margin: 20px 0;
}

.doctor-container{
    display: grid;
    grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
    gap: 20px;
    padding: 20px;
}

.doctor-card{
    background: #fff;
    border-radius: 12px;
    box-shadow: 0 4px 12px rgba(0,0,0,0.1);
    padding: 20px;
    transition: 0.3s;
}

.doctor-card:hover{
    transform: translateY(-5px);
}

.doctor-name{
    font-size: 22px;
    font-weight: bold;
    color: #113167;
}

.doctor-info{
    margin-top: 10px;
    color: #555;
}

.doctor-id{
    font-size: 12px;
    background: #113167;
    color: white;
    padding: 3px 8px;
    border-radius: 20px;
    float: right;
}

</style>

</head>

<body>

<%@ include file="sidebar.jsp" %>

<div class="main">

<%@ include file="navbar.jsp" %>

<h2 class="page-title" style="text-align: center;"> Our Doctors </h2>

<%
List<Doctor> doctors = (List<Doctor>) request.getAttribute("doctors");

if(doctors != null && !doctors.isEmpty()){
    for(Doctor d : doctors){
%>

<div class="doctor-container">

    <div class="doctor-card card">

        <div class="doctor-id">
            ID: <%= d.getId() %>
        </div>

        <div class="doctor-name">
           <%= d.getName() %>
        </div>

        <div class="doctor-info card">
            <p><b>Specialization:</b> <%= d.getSpecialization() %></p>
            <p><b>Email:</b> <%= d.getEmail() %></p>
            <p><b>Phone:</b> <%= d.getPhone() %></p>
            <p><b>Experience:</b> <%= d.getExperience() %> years</p>
        </div>

    </div>

</div>

<%
    }
} else {
%>

<p style="margin:20px; color:red;">No doctors found</p>

<%
}
%>

</div>

</body>
</html>