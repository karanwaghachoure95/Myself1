<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">

<meta charset="UTF-8">
<title>All Accounts</title>

<style>
body {
	font-family: Arial;
	background: #f2f2f2;
}

h2 {
	text-align: center;
	margin-top: 20px;
}

/* container */
.table-container {
	width: 95%;              /* table bada */
	margin: 30px auto;
}

/* top bar */
.top-bar {
	display: flex;
	justify-content: space-between;
	align-items: center;
	margin-bottom: 20px;
}

/* search group */
.search-group {
	display: flex;
	align-items: center;
	gap: 8px;
}

.search-box {
	width: 45vw;        /* pehle 320px tha */
	padding: 12px 14px;
	border-radius: 10px;
	border: 1px solid #ccc;
	font-size: 15px;
}


.search-btn {
	padding: 10px 14px;
	background: #4e54c8;
	color: white;
	border: none;
	border-radius: 8px;
	cursor: pointer;
	font-size: 14px;
}

.search-btn:hover {
	background: #3b40a4;
}

/* back button */
.btn-back {
	padding: 10px 20px;
	background: black;
	color: white;
	border-radius: 10px;
	text-decoration: none;
}

.btn-back:hover {
	background: rgb(130, 200, 120);
}

/* table */
table {
	width: 100%;
	border-collapse: collapse;
	background: white;
}

th, td {
	border: 1px solid #ccc;
	padding: 14px;          /* row height bada */
	text-align: center;
	font-size: 15px;        /* text readable */
}

th {
	background: #4e54c8;
	color: white;
	font-size: 16px;
}

/* buttons */
.btn-delete {
	padding: 6px 16px;
	background: red;
	color: white;
	border-radius: 6px;
	text-decoration: none;
	font-size: 14px;
}

.btn-delete:hover {
	background: darkred;
}

.btn-update {
	font-size: 14px;
	border-radius: 7px;
	background: blue;
	color: white;
	padding: 6px 16px;
	text-decoration: none;
}

.btn-update:hover {
	background: lightblue;
}
</style>
</head>

<body>

<h2>Account Details</h2>

<div class="table-container">

	<!-- Search + Back -->
	<div class="top-bar">

		<div class="search-group">
			<input type="text" id="searchInput"
				placeholder="Search by name, mobile, email, address..."
				class="search-box">

			<button class="search-btn" onclick="searchTable()">
				<i class="fa fa-search"></i>
			</button>
		</div>

		<a href="/accountForm" class="btn-back">
			<i class="fa fa-arrow-left"></i> Back
		</a>
	</div>

	<table id="accountTable">
		<tr>
			<th>ID</th>
			<th>Name</th>
			<th>Mobile</th>
			<th>Email</th>
			<th>Address</th>
			<th>Remove</th>
			<th>Update Profile</th>
		</tr>

		<c:forEach var="acc" items="${accounts}">
			<tr>
				<td>${acc.id}</td>
				<td>${acc.name}</td>
				<td>${acc.mobNo}</td>
				<td>${acc.email}</td>
				<td>${acc.address}</td>
				<td>
					<a href="${pageContext.request.contextPath}/deleteAccount/${acc.id}"
					   class="btn-delete"
					   onclick="return confirm('Are you sure you want to delete this account?')">
						Delete
					</a>
				</td>
				<td>
					<a href="${pageContext.request.contextPath}/updateProfile/${acc.id}"
					   class="btn-update"
					   onclick="return confirm('Are you sure update your profile?')">
						Update
					</a>
				</td>
			</tr>
		</c:forEach>
	</table>
</div>

<script>
function searchTable() {
	let input = document.getElementById("searchInput");
	let filter = input.value.toLowerCase();
	let table = document.getElementById("accountTable");
	let tr = table.getElementsByTagName("tr");

	for (let i = 1; i < tr.length; i++) {
		let rowText = tr[i].innerText.toLowerCase();
		tr[i].style.display = rowText.includes(filter) ? "" : "none";
	}
}
</script>

<c:if test="${not empty msg3}">
	<h4 style="color: green; text-align: center;">${msg3}</h4>
</c:if>

<c:if test="${not empty msg4}">
	<h4 style="color: red; text-align: center;">${msg4}</h4>
</c:if>

<c:if test="${not empty msg5}">
	<h4 style="color: green; text-align: center;">${msg5}</h4>
</c:if>

<c:if test="${not empty correct }">
 <h2 style="color: green; text-align:center;">${correct}</h2>
</c:if>

</body>
</html>
