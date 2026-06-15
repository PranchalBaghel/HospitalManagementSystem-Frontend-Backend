<%@page import="entity.Appointment"%>
<%@ page import="java.util.List, entity.Doctor" %>
<%@page import="java.util.List"%>

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
<title>Dashboard</title>

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

    <div class="dashboard-cards">

       <div class="dash-card">
    <h3>Total Patients</h3>
    <h1><%= request.getAttribute("totalPatients") %></h1>
</div>

        <div class="dash-card">
    <h3>Total Doctors</h3>
    <h1><%= request.getAttribute("totalDoctors") %></h1>
</div>

        <div class="dash-card">
    <h3>Total Appointments</h3>
    <h1><%= request.getAttribute("totalAppointments") %></h1>
</div>

       <div class="dash-card">
    <h3>Total Revenue</h3>
    <h1>₹ <%= request.getAttribute("totalRevenue") %></h1>
</div>

    </div>
    <%-- Recent appointment --%>
<div class="recent-section">

    <h2>Recent Appointments</h2>

    <table class="recent-table">
        <tr>
            <th>Patient</th>
            <th>Doctor</th>
            <th>Date</th>
            <th>Time</th>
            <th>Status</th>
        </tr>

        <%
            List<Appointment> list =
                (List<Appointment>) request.getAttribute("appointmentsList");

            if(list != null){
                for(Appointment a : list){
        %>

        <tr>
            <td><%= a.getPatientName() %></td>
            <td><%= a.getDoctorName() %></td>
            <td><%= a.getAppointmentDate()  %></td>
            <td><%= a.getAppointmentTime()  %></td>
            <td><%= a.getStatus() %></td>
        </tr>

        <%
                }
            }
        %>

    </table>
    
</div>


 <%-- Doctor Workload Report --%>
<div class="recent-section">

    <h2>Doctor Workload Report</h2>

    <table class="recent-table">
        <tr>
           <th>Doctor</th>
        <th>Specialization</th>
        <th>Total Appointments</th>
        </tr>

        <%
List<Doctor> workload = (List<Doctor>) request.getAttribute("workload");
        for(Doctor d : workload){
%>

        <tr>
           <td><%= d.getName() %></td>
    <td><%= d.getSpecialization() %></td>
    <td><%= d.getTotalAppointments() %></td>
        </tr>

        <%
}
%>

    </table>
    
</div>

<%-- Patient visit history --%>

<div class="recent-section">
<h2>Patient Visit History</h2>

<table class="recent-table">

    <tr>
        <th>Patient</th>
        <th>Doctor</th>
        <th>Date</th>
        <th>Time</th>
        <th>Status</th>
    </tr>

<%
List<Appointment> history =
        (List<Appointment>) request.getAttribute("visitHistory");

if(history != null){
    for(Appointment a : history){
%>

<tr>
    <td><%= a.getPatientName() %></td>
    <td><%= a.getDoctorName() %></td>
    <td><%= a.getAppointmentDate() %></td>
    <td><%= a.getAppointmentTime() %></td>
    <td><%= a.getStatus() %></td>
</tr>

<%
    }
}
%>

</table>

</div>

</div>

</body>
</html>