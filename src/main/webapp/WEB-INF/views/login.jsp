<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">

    <style>
        /* Body & Background */
        body {
            margin: 0;
            height: 100vh;
            font-family: 'Arial', sans-serif;
            background: linear-gradient(120deg, #4e54c8, #8f94fb);
            display: flex;
            justify-content: center;
            align-items: center;
        }

        /* Card */
        .login-box {
            background: #fff;
            padding: 40px 30px 30px 30px;
            width: 380px;
            border-radius: 15px;
            box-shadow: 0 15px 35px rgba(0, 0, 0, 0.3);
            position: relative;
        }

        /* Back Button */
        .top-bar {
            position: absolute;
            top: 20px;
            right: 20px;
        }

        .btn-back {
            padding: 8px 18px;
            background: #333;
            color: #fff;
            border-radius: 8px;
            text-decoration: none;
            font-size: 14px;
            transition: all 0.3s ease;
        }

        .btn-back:hover {
            background: #28a745;
        }

        /* Heading */
        .login-box h1 {
            text-align: center;
            color: #333;
            margin-bottom: 30px;
            font-size: 32px;
        }

        /* Input Groups */
        .input-group {
            margin-bottom: 20px;
        }

        .input-group label {
            display: block;
            margin-bottom: 5px;
            color: #555;
            font-size: 18px;
            font-weight: bold;
        }

        .input-group input {
            width: 100%;
            padding: 12px;
            border-radius: 8px;
            border: 1px solid #ccc;
            font-size: 16px;
            transition: all 0.3s ease;
        }

        .input-group input:focus {
            outline: none;
            border-color: #4e54c8;
            box-shadow: 0 0 5px rgba(78, 84, 200, 0.5);
        }

        /* Submit Button */
        .btn {
            width: 100%;
            padding: 12px;
            background: #4e54c8;
            border: none;
            color: #fff;
            font-size: 18px;
            border-radius: 8px;
            cursor: pointer;
            transition: all 0.3s ease;
        }

        .btn:hover {
            background: #3b40a4;
        }

        /* Error Message */
        .error {
            color: red;
            text-align: center;
            margin-top: 15px;
            font-size: 14px;
        }

        /* Responsive */
        @media (max-width: 450px) {
            .login-box {
                width: 90%;
                padding: 30px 20px;
            }

            .login-box h1 {
                font-size: 28px;
            }

            .input-group label,
            .input-group input,
            .btn {
                font-size: 16px;
            }

            .btn-back {
                padding: 6px 14px;
                font-size: 12px;
            }
        }

    </style>
</head>

<body>

<div class="login-box">

    <!-- Back Button -->
    <div class="top-bar">
        <a href="/accountForm" class="btn-back">
            <i class="fa fa-arrow-left"></i> Back
        </a>
    </div>

    <h1>Login</h1>

    <!-- Login Form -->
    <form action="${pageContext.request.contextPath}/loginProcess" method="post">
        <div class="input-group">
            <label>Username</label>
            <input type="text" name="username" required>
        </div>

        <div class="input-group">
            <label>Password</label>
            <input type="password" name="password" required>
        </div>

        <input type="submit" value="Login" class="btn">
    </form>

    <!-- Error Message -->
    <div class="error">
        <% String error = (String) request.getAttribute("error");
           if(error != null){
               out.println(error);
           }
        %>
    </div>

</div>

</body>
</html>
