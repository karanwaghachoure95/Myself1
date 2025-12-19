<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>OTP Verification</title>

<style>
body {
    font-family: Arial, sans-serif;
    background: linear-gradient(120deg, #4e54c8, #8f94fb);
    height: 100vh;
    display: flex;
    justify-content: center;
    align-items: center;
}

.otp-box {
    background: #fff;
    padding: 30px;
    width: 350px;
    border-radius: 10px;
    box-shadow: 0 10px 25px rgba(0,0,0,0.2);
    text-align: center;
}

.otp-box h2 {
    margin-bottom: 20px;
}

.otp-box input {
    width: 100%;
    padding: 10px;
    font-size: 16px;
    margin-bottom: 15px;
}

.otp-box button {
    width: 100%;
    padding: 10px;
    font-size: 16px;
    background: #4e54c8;
    color: white;
    border: none;
    border-radius: 5px;
    cursor: pointer;
}

.otp-box button:hover {
    background: #3c40a0;
}

.msg {
    color: red;
    margin-top: 10px;
}

.msg1{
  color:green;
  margin-top:10px;
}
</style>
</head>

<body>

<div class="otp-box">
    <h2>OTP Verification</h2>

    <form action="verifyOtp" method="post">
        <input type="text" name="otp" placeholder="Enter OTP" required />
        <button type="submit">Verify OTP</button>
    </form>

   

    <%-- Error Message --%>
    <%
        String msg = (String) request.getAttribute("msg");
        if (msg != null) {
    %>
        <div class="msg"><%= msg %></div>
    <%
        }
    %>
</div>

</body>
</html>
