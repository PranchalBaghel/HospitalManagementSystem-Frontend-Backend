<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>About Us</title>

<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">

</head>
<body>

<!-- SIDEBAR -->
<%@ include file="sidebar.jsp" %>

<div class="main">

    <!-- NAVBAR -->
    <%@ include file="navbar.jsp" %>

   <!-- About Us Section Starts -->

<div class="about" id="about">
	<div class="about-content">

		<div class="about-text">

			<h2>About Us</h2>

			<p>
				Hospital Management System is a modern healthcare solution
				designed to simplify hospital operations and improve patient
				care. Our system helps manage patient records, doctor details,
				appointments, departments, and billing services efficiently.
			</p>

			<br>

			<p>
				We aim to reduce manual work, improve accuracy, and provide
				faster access to important information. With an easy-to-use
				interface and organized data management, our system supports
				better healthcare services for both patients and hospital staff.
			</p>

			<br>


		</div>

		<img class="about-img"
			src="https://cdn-icons-png.flaticon.com/512/2785/2785482.png"
			alt="About Hospital">

	</div>
	<div class="container">

    <p>
        Hospital Management System is a web-based application designed to simplify and automate hospital operations.
        It helps hospitals manage patients, doctors, appointments, departments, and billing efficiently.
    </p>

    <div class="section">

        <h2 style="color:#113167;">Our Objective</h2>

        <div class="card">
            To reduce manual work, improve accuracy, and provide faster access to hospital information.
            Our system ensures better coordination between patients and hospital staff.
        </div>

    </div>

    <div class="section">

        <h2 style="color:#113167;">Key Features</h2>

        <div class="card">
            <ul>
                <li>Patient Management System</li>
                <li>Doctor Information Management</li>
                <li>Appointment Booking</li>
                <li>Billing & Reports</li>
            </ul>
        </div>

    </div>

    <div class="section">

        <h2 style="color:#113167;">Why Choose Us?</h2>

        <div class="card">
            Simple UI, fast performance, and easy data management for hospitals of all sizes.
        </div>

    </div>

</div>

</div>

<!-- About Us Section Ends -->

</div>

</body>
</html>