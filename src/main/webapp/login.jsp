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
<title>Admin Login</title>

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
	height: 100vh;
	font-family: Arial, sans-serif;
}

.login-box {
	width: 420px;
	background: white;
	padding: 35px;
	border-radius: 12px;
	box-shadow: 0 5px 20px rgba(0, 0, 0, .15);
}

.logo {
	text-align: center;
	margin-bottom: 15px;
}

.logo i {
	font-size: 30px;
	color: #113167;
}

h2 {
	text-align: center;
	margin-bottom: 25px;
}

.input-group {
	margin-bottom: 18px;
}

.input-group input {
	width: 100%;
	padding: 12px;
	border: 1px solid #ccc;
	border-radius: 8px;
	font-size: 15px;
	outline: none;
	box-sizing: border-box;
}

.btn {
	width: 100%;
	padding: 12px;
	border: none;
	background: #113167;
	color: white;
	font-size: 16px;
	border-radius: 8px;
	cursor: pointer;
	transition: .3s;
}

.btn:hover {
	background: #0b2447;
}

.bottom {
	margin-top: 20px;
	display: flex;
	justify-content: space-between;
	font-size: 14px;
}

.bottom a {
	text-decoration: none;
	color: #113167;
	font-weight: bold;
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

	<div class="login-box">

		<div class="logo">
			<i class="fa-solid fa-hospital"></i>
		</div>

		<h2>Admin Login</h2>

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

		<form action="AdminLogin" method="post">

			<div class="input-group">

				<input type="email" name="email" placeholder="Email" required>

			</div>

			<div class="input-group">

				<input type="password" name="password" placeholder="Password" required>

			</div>

			<button class="btn">Login</button>

		</form>

		<div class="bottom">

			<a href="register.jsp">Create Account</a>

		</div>

	</div>

</body>
</html>