<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<title>Update Account</title>

<style>
body {
    font-family: Arial, sans-serif;
    background: linear-gradient(120deg, #4e54c8, #8f94fb);
    height: 100vh;
    margin: 0;
    display: flex;
    justify-content: center;
    align-items: center;
}

.card {
    background: white;
    width: 430px;
    padding: 30px;
    border-radius: 15px;
    box-shadow: 0 10px 25px rgba(0,0,0,0.3);
}

.card h2 {
    text-align: center;
    margin-bottom: 25px;
    color: #4e54c8;
}

label {
    font-weight: bold;
    display: block;
    margin-bottom: 5px;
}

input {
    width: 100%;
    padding: 10px;
    margin-bottom: 15px;
    border-radius: 8px;
    border: 1px solid #ccc;
    font-size: 14px;
}

input:focus {
    outline: none;
    border-color: #4e54c8;
}

.btn-group {
    display: flex;
    gap: 10px;
}

button {
    flex: 1;
    padding: 12px;
    background: #4e54c8;
    color: white;
    border: none;
    border-radius: 10px;
    font-size: 16px;
    cursor: pointer;
}

button:hover {
    background: #3b40a4;
}

.back-btn {
    flex: 1;
    text-align: center;
    padding: 12px;
    background: black;
    color: white;
    text-decoration: none;
    border-radius: 10px;
}

.back-btn:hover {
    background: #444;
}

.msg {
    text-align: center;
    font-size: 18px;
    margin-top: 15px;
}
</style>
</head>

<body>

<div class="card">
    <h2>Update Profile</h2>

    <form action="${pageContext.request.contextPath}/updateAccount" method="post">

        <!-- Hidden ID -->
        <input type="hidden" name="id" value="${account.id}" />

        <label>Name</label>
        <input type="text" name="name" value="${account.name}" required />

        <label>Mobile</label>
        <input type="number" name="mobNo" value="${account.mobNo}" required />

        <label>Email</label>
        <input type="email" name="email" value="${account.email}" required />

        <label>Address</label>
        <input type="text" name="address" value="${account.address}" required />

        <div class="btn-group">
            <button type="submit">Update</button>
            <a href="${pageContext.request.contextPath}/accountFormSave" class="back-btn">
                Back
            </a>
        </div>
    </form>

    <c:if test="${not empty msg6}">
        <div class="msg" style="color:red;">${msg6}</div>
    </c:if>

   
</div>

</body>
</html>
