<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
response.setHeader("Pragma", "no-cache");
response.setDateHeader("Expires", 0);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Register</title>

<link rel="stylesheet" href="css/style.css">

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css">

<style>
body {
	margin: 0;
	padding: 0;
	background: #f4f6f9;
	display: flex;
	justify-content: center;
	align-items: center;
	min-height: 100vh;
}

.register-container {
	width: 430px;
	background: white;
	padding: 35px;
	border-radius: 15px;
	box-shadow: 0 5px 20px rgba(0, 0, 0, .15);
}

.register-container h2 {
	text-align: center;
	color: #113167;
	margin-bottom: 25px;
}

.logo {
	text-align: center;
	margin-bottom: 20px;
}

.logo i {
	font-size: 30px;
	color: #113167;
}

.input-group {
	margin-bottom: 18px;
}
.input-group input {
	width: 100%;
	padding: 12px;
	border: 1px solid #ccc;
	border-radius: 8px;
	outline: none;
	font-size: 15px;
}


.btn {
	width: 100%;
	padding: 12px;
	border: none;
	border-radius: 8px;
	background: #113167;
	color: white;
	font-size: 16px;
	cursor: pointer;
	transition: .3s;
}

.btn:hover {
	background: #0b2447;
}

.login-link {
	margin-top: 20px;
	text-align: center;
}

.login-link a {
	color: #113167;
	text-decoration: none;
	font-weight: bold;
}

.login-link a:hover {
	text-decoration: underline;
}

.error {
	color: red;
	text-align: center;
	margin-bottom: 15px;
}

.success {
	color: green;
	text-align: center;
	margin-bottom: 15px;
}
</style>

</head>

<body>

	<div class="register-container">

		<div class="logo">

			<i class="fa-solid fa-hospital"></i>

		</div>

		<h2>Create Admin Account</h2>

		<%
		String error = (String) session.getAttribute("error");
		String success = (String) session.getAttribute("success");

		if (error != null) {
		%>

		<p class="error"><%=error%></p>

		<%
		session.removeAttribute("error");
		}

		if (success != null) {
		%>

		<p class="success"><%=success%></p>

		<%
		session.removeAttribute("success");
		}
		%>

		<form action="AdminRegister" method="post">

			<div class="input-group">

				<input type="text" name="name" placeholder="Enter Name" required>

			</div>

			<div class="input-group">

				<input type="email" name="email" placeholder="Enter email" required>

			</div>

			<div class="input-group">

				<input type="password" name="password" placeholder="Enter Password" required>

			</div>

			<div class="input-group">

				<input type="password" name="confirmPassword" placeholder="Enter Confirm Password"required >

			</div>

			<button class="btn">Register</button>

		</form>

		<div class="login-link">

			Already have an account? <a href="login.jsp"> Login </a>

		</div>

	</div>

</body>

</html>