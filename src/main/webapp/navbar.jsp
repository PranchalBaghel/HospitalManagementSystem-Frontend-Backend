<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="entity.Admin"%>

<%
Admin admin = (Admin) session.getAttribute("admin");
%>

<div class="navbar">

    <h1 style="color: #113167;">Hospital <span style="color:#204585;">Management</span> </h1>

    <ul class="nav-links">

        <li><a href="index.jsp">Home</a></li>
        <li><a href="about.jsp">About us</a></li>
        <li><a href="doctors?view=list">Doctors</a></li>
    <li><a href="register.jsp" class="register-btn">Register</a></li>
        <li class="admin-name"  >
          <a href="index.jsp" style="color: #113167; font-size:25px; font-weight:bold; font-style:italic;"><i class="bi bi-person-fill"></i>
          <%= admin.getName() %></a> 
        </li>
        

    </ul>

</div>