<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Protected</title>

<style>
    body {
        font-family: Arial, sans-serif;
        background: linear-gradient(120deg, #4e54c8, #8f94fb);
        height: 100vh;
        display: flex;
        justify-content: center;
        align-items: center;
    }

    .box {
        background: white;
        padding: 25px;
        border-radius: 8px;
        width: 320px;
        box-shadow: 0 5px 15px rgba(0,0,0,0.3);
    }

    h2 {
        text-align: center;
        margin-bottom: 20px;
    }

    input {
        width: 100%;
        padding: 8px;
        margin: 10px 0;
    }

    button {
        width: 100%;
        padding: 10px;
        background: #4e54c8;
        color: white;
        border: none;
        cursor: pointer;
    }

    button:hover {
        background: #3b3fc1;
    }
</style>
</head>

<body>

<div class="box">
    <h2>Update Details</h2>

    <form action="updateProtected" method="post">

        <!-- ID hidden -->
        <input type="hidden" name="id" value="1"/>

        <label>Username</label>
        <input type="text" name="username"
               value="${protectedObj.username}" required/>

        <label>Password</label>
        <input type="password" name="password"
               value="${protectedObj.password}" required/>

        <button type="submit">Update</button>
    </form>
    
    <% String msg2 = (String) request.getAttribute("msg2");
      if(msg2!=null){
    %>
    <h2 style = "color:red; text-align:center"><% out.println(msg2); %></h2>
    <% } %>
</div>

</body>
</html>
