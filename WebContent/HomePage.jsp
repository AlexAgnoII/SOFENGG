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
		
		<title>Home</title>
	</head>
	
	<body>
        
        <div id="logindiv">
            <div id= "wrapper">
                <img src="css/logo.png" id="arrow">
                <p id="title">DLSU STUDENT INFORMATION SYSTEM</p>
            </div><br>
            <form>
                <div class="input-field col s12">
                    <input id="username" type="email" class="validate">
                    <label for="username">Email</label>
                 </div>
                
                <div class="input-field col s12">
                    <input id="password" type="password" class="validate">
                    <label for="password">Password</label>
                 </div>
            </form>
            
            <a class="waves-effect waves-light btn" id="loginb">login</a>
            <a class="waves-effect waves-light btn" id="signupb" href="signup.html">signup</a>
        </div>
	
	</body>
</html>