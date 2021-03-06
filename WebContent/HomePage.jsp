<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

		<!--Import Google Icon Font-->
		<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
		
		<!--Import materialize.css-->
		<link rel="stylesheet" type="text/css" href="materialize/css/materialize.min.css">
		<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
		
		<script type="text/javascript" src="jquery-3.2.1.min.js"></script>
		<script type="text/javascript" src="materialize/js/materialize.min.js"></script>
		
		<link rel = "stylesheet" type="text/css" href="css/homepage.css">
		
		<title>Login</title>
	</head>
	
	<body>
		<div id="left"></div>
		<div id="right"></div>
		<div id="top"></div>
		<div id="bottom"></div>
        
        <div id="logindiv">
            <div id= "wrapper">
                <img src="css/arrow.png" id="arrow">
                <p id="title">DLSU STUDENT INFORMATION SYSTEM</p>
            </div><br>
            
            <form action="login" method="POST" id="loginForm">
                <div class="input-field col s12">
                    <input id="username" type="email" class="validate" name="email">
                    <label for="username">Email</label>
                 </div>
                
                <div class="input-field col s12">
                    <input id="password" type="password" class="validate" name="password">
                    <label for="password">Password</label>
                 </div>
            </form>
         
            <div id="errorFeed">
                <p id="dnm" class="center-align">Username and password do not match</p>
                <!-- <p id="dne">Account does not exist</p> -->
                <!-- <p id="dnv">Account has not been verified yet</p> -->
            </div>
            
            <br><br>
            <a class="waves-effect waves-light btn" id="loginb">login</a>
            
            <br>
            <a  id="signupb" href="Signup.jsp">Create Account</a>
            <a id="forgotpw" href="ResetPasswordEmail.html">Forgot password?</a>
        </div>
        
		<script type="text/javascript" src="script/HomePage.js"></script>
	</body>
    <script>
        // for testing scripts
    </script>
</html>