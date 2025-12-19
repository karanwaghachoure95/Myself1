<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="java.text.NumberFormat.Style"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create Account</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: linear-gradient(120deg, #4e54c8, #8f94fb);
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .card {
            background: #fff;
            padding: 140px;
            width: 650px;
            border-radius: 30px;
            box-shadow: 0 10px 25px rgba(0,0,0,0.2);
        }

        .card h1 {
            text-align: center;
            margin-bottom: 70px;
            color: #333;
        }

        .form-group {
            margin-bottom: 45px;
        }

        .form-group label {
            display: block;
            margin-bottom: 10px;
            font-size:23px;
            font-weight: bold;
        }

        .form-group input {
            width: 100%;
            padding: 13px;
            border-radius: 5px;
            border: 1px solid #ccc;
            font-size: 23px;
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
            font-size: 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            margin-top: 10px;
        }

        .btn:hover {
            background: #3b40a4;
        }
        
        .show{
              padding: 10px 18px;
background: black;
    color: white;
    font-size: 20px;
    cursor: pointer;
    border-radius: 8px;
    text-decoration: none;

        }
        
        .show:hover{
         background: rgb(144, 238, 144);
        }
        
        .show-container{
        display: flex;
        justify-content: flex-end;
        }
        
    </style>
</head>
<body>

<div class="card">

<div class = "show-container">
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

        <div class = "form-group">
        <label>Email</label>
          <input type ="text"  name = "email" placeholder = "enter your email" required>
        </div>

       <div class = "form-group">
       <label>Address</label>
          <input type= "text" name="address" placeholder = "enter your address"  required>
       </div>
       
        <button type="submit" class="btn">Create Account</button>
    </form>


		<c:if test="${not empty msg2}">
			<h4 style="color: red; text-align: center; font-size: 25px;">${msg2}</h4>
		</c:if>



	</div>

</body>
</html>
