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
<title>Hospital Management System</title>
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">

</head>
<body>
<!-- Sidebar starts -->
<%@ include file="sidebar.jsp" %>
	<!-- Sidebar ends -->

	<!-- Main Content -->
	<div class="main">
	
	<!-- Navbar starts -->
 <%@ include file="navbar.jsp" %>
    <!-- Navbar ends -->
 
		<!-- Hero Section Starts -->

		<div class="hero">

			<div class="hero-text">

				<h1>Making Health Care Better Together</h1>

				<p>Hospital Management System helps hospitals manage patient
					records, doctor information, appointments, departments and billing
					services efficiently. Our goal is to provide better healthcare
					management through a simple and user-friendly platform.</p>

				<a href="appointments" class="hero-a">Make Appointment</a> <a href="doctors?view=list"
					class="hero-a-outline">View Doctors</a>

			</div>

			<img src="img\main.jpg"
				class="hero-img" alt="Doctor">

		</div>

		<!-- Hero Section Ends -->

		<!-- Our Services Starts -->

<div class="services">

	<h2>Our Services</h2>
	<p class="service-subtitle">
		We provide complete healthcare management solutions.
	</p>

	<div class="service-container">

		<div class="service-card">
			<i class="fa-regular fa-user"></i>
			<h3>Patient Management</h3>
			<p>Manage patient records and medical history efficiently.</p>
		</div>

		<div class="service-card">
			<i class="fa-solid fa-user-doctor"></i>
			<h3>Doctor Management</h3>
			<p>Maintain doctor profiles, schedules and information.</p>
		</div>

		<div class="service-card">
			<i class="fa-solid fa-calendar-check"></i>
			<h3>Appointments</h3>
			<p>Book and manage appointments quickly and easily.</p>
		</div>

		<div class="service-card">
			<i class="fa-solid fa-file-invoice-dollar"></i>
			<h3>Billing</h3>
			<p>Generate bills and manage payments efficiently.</p>
		</div>

	</div>

</div>

<!-- Our Services Ends -->

<!-- Footer Starts -->

<footer class="footer">

	<div class="footer-content">

		<div class="footer-section">
			<h3>MediFlow MS</h3>
			<p>
				Providing efficient hospital management solutions for better
				healthcare services.
			</p>
		</div>

		<div class="footer-section">
			<h3>Quick Links</h3>
			<a href="#">Home</a>
			<a href="about.jsp">About Us</a>
			<a href="#">Doctors</a>
			<a href="#">Contact</a>
		</div>

		<div class="footer-section">
			<h3>Contact Us</h3>
			<p><i class="fa-solid fa-phone"></i> +91 9876543210</p>
			<p><i class="fa-solid fa-envelope"></i> mediflow@gmail.com</p>
			<p><i class="fa-solid fa-location-dot"></i> Gwalior, India</p>
		</div>

	</div>

	<div class="footer-bottom">
		<p>© 2026 Hospital Management System | All Rights Reserved</p>
	</div>

</footer>

<!-- Footer Ends -->

	</div>
	<!-- Main Content -->

</body>
</html>