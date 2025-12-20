<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.text.NumberFormat.Style"%>
<html lang="en">
<head>
<meta charset="UTF-8">

<!-- ✅ IMPORTANT: Zoom / mobile fix -->
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>Create Account</title>

<style>
body {
	margin: 0;
	font-family: Arial, sans-serif;
	background: linear-gradient(120deg, #4e54c8, #8f94fb);
	min-height: 100vh;
	display: flex;
	justify-content: center;
	align-items: center;
}

/* ✅ Responsive Card */
.card {
	background: #fff;
	padding: 4vw;
	width: min(90%, 650px);
	border-radius: 30px;
	box-shadow: 0 10px 25px rgba(0, 0, 0, 0.2);
}

.card h1 {
	text-align: center;
	margin-bottom: 40px;
	color: #333;
	font-size: 32px;
}

.form-group {
	margin-bottom: 25px;
}

.form-group label {
	display: block;
	margin-bottom: 8px;
	font-size: 18px;
	font-weight: bold;
}

.form-group input {
	width: 100%;
	padding: 12px;
	border-radius: 5px;
	border: 1px solid #ccc;
	font-size: 16px;
}

.form-group input:focus {
	outline: none;
	border-color: #4e54c8;
}

.btn {
	width: 100%;
	padding: 12px;
	background: #4e54c8;
	color: white;
	font-size: 18px;
	border: none;
	border-radius: 5px;
	cursor: pointer;
	margin-top: 10px;
}

.btn:hover {
	background: #3b40a4;
}

/* Show button */
.show {
	padding: 8px 14px;
	background: black;
	color: white;
	font-size: 16px;
	cursor: pointer;
	border-radius: 8px;
	text-decoration: none;
}

.show:hover {
	background: rgb(144, 238, 144);
	color: black;
}

.show-container {
	display: flex;
	justify-content: flex-end;
	margin-bottom: 15px;
}

/* ✅ Mobile tweak */
@media (max-width: 600px) {
	.card h1 {
		font-size: 26px;
	}
}
</style>
</head>

<body>

	<div class="card">

		<div class="show-container">
			<a href="${pageContext.request.contextPath}/login" class="show">
				Show
			</a>
		</div>

		<h1>Create Account</h1>

		<form action="${pageContext.request.contextPath}/accountForm" method="post">

			<div class="form-group">
				<label>Name</label>
				<input type="text" name="name" placeholder="Enter your name" required>
			</div>

			<div class="form-group">
				<label>Mobile Number</label>
				<input type="text" name="mobNo" placeholder="Enter mobile number" required>
			</div>

			<div class="form-group">
				<label>Email</label>
				<input type="text" name="email" placeholder="Enter your email" required>
			</div>

			<div class="form-group">
				<label>Address</label>
				<input type="text" name="address" placeholder="Enter your address" required>
			</div>

			<button type="submit" class="btn">Create Account</button>
		</form>

		<c:if test="${not empty msg1}">
			<h4 style="color: green; text-align: center; font-size: 18px;">${msg1}</h4>
		</c:if>

		<c:if test="${not empty msg2}">
			<h4 style="color: red; text-align: center; font-size: 18px;">${msg2}</h4>
		</c:if>

	</div>

</body>
</html>
