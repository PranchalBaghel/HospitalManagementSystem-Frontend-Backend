<%@ page import="java.util.*, entity.Department" %>
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
<title>Department Management</title>

<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
	
<style>


input, textarea {
    width: 50%; 
    padding: 10px;
    margin:2px;
    border: 1px solid #ccc;
    border-radius: 6px;
    font-size: 14px;
}

textarea {
    resize: none;
    height: 80px;
}

p {
    color: #113167;
    font-size:35px;
}


/* Department Cards */

.department-container{
    display:grid;
    grid-template-columns:repeat(auto-fit,minmax(320px,1fr));
    gap:25px;
    margin-top:20px;
}

.department-card{
    background:#fff;
    border-radius:14px;
    box-shadow:0 6px 18px rgba(17,49,103,.15);
    padding:22px;
    display:flex;
    flex-direction:column;
    justify-content:space-between;
    transition:.3s;
}

.department-card:hover{
    transform:translateY(-5px);
}

.department-header{
    display:flex;
    justify-content:space-between;
    align-items:center;
    margin-bottom:15px;
}

.department-title{
    font-size:28px;
    font-weight:600;
    color:#113167;
}

.department-id{
    background:#113167;
    color:#fff;
    padding:4px 10px;
    border-radius:20px;
    font-size:13px;
}

.department-desc{
    color:#555;
    line-height:1.7;
    margin-bottom:20px;
}

.department-footer{
    display:flex;
    justify-content:space-between;
    align-items:center;
}

.edit-btn{
    border:1px solid #113167;
    color:#113167;
    padding:8px 14px;
    border-radius:6px;
    text-decoration:none;
}

.edit-btn:hover{
    background:#113167;
    color:#fff;
}

.delete-btn{
    color:#e80230;
    text-decoration:none;
    font-weight:bold;
}

.delete-btn:hover{
    text-decoration:underline;
}

</style>

</head>

<body>

<%@ include file="sidebar.jsp" %>

<div class="main">

<%@ include file="navbar.jsp" %>

<div class="main-box">


<br>
<!-- SEARCH -->

<form action="departments" method="get" class="search-bar">

    <input type="text" name="search" placeholder="Search Department">

    <button type="submit">Search</button>

</form>

<hr>

<br>
<br>
<!-- Update Department -->
<%
Department editDept = (Department) request.getAttribute("department");

if(editDept != null){
%>

<p>Update Department</p>
<br>
<form action="departments" method="post">
    <input type="hidden" name="action" value="update">
    <input type="hidden" name="id" value="<%=editDept.getId()%>">

    <input type="text" name="name" value="<%=editDept.getName()%>">

    <textarea name="description"><%=editDept.getDescription()%></textarea>
<br><br>
    <button type="submit">Update</button>
</form>

<hr>

<% } %>


<!-- LIST -->
<br>
<p class="text-align-left my-2 fw-bold" style="color:#113167; animation: heroAppear 1s ease;">
    Departments
</p>

<div class="department-container">

<%
List<Department> list = (List<Department>) request.getAttribute("departments");

if(list != null){
    for(Department d : list){
%>

<div class="department-card">

    <div class="department-header">

        <div class="department-title">
            <%= d.getName() %>
        </div>

        <div class="department-id">
            ID : <%= d.getId() %>
        </div>

    </div>

    <div class="department-desc">
        <%= d.getDescription() %>
    </div>

    <div class="department-footer">

        <a href="departments?action=edit&id=<%=d.getId()%>"
           class="edit-btn">

            <i class="fa-solid fa-pen-to-square"></i>
            Edit

        </a>

        <a href="departments?action=delete&id=<%=d.getId()%>"
           class="delete-btn"
           onclick="return confirm('Delete department?')">

            Delete

        </a>

    </div>

</div>

<%
    }
}
%>

</div>
<br><br>
<!--ADD DEPARTMENT -->
<hr><br><br>
<p>Add Department</p>
<br>
<form action="departments" method="post">
    <input type="hidden" name="action" value="add">

    <input type="text" name="name" placeholder="Department Name" required>

    <textarea name="description" placeholder="Description"></textarea>
<br><br>
    <button type="submit">Add Department</button>
</form>

<hr>

<br>


</div>
</div>

</body>
</html>