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
		
        <link rel = "stylesheet" type = "text/css" href = "css/userhomepage.css">
		
		<title>Home</title>
	</head>
	
	<body>
        
        <div>
<!--
            <button>Home</button>
            <button>Apply for Rewards</button>
            <button>Profile</button>
-->
                <ul>
                    <li><a href="UserHomePage.jsp">Home</a></li>
                    <li class="dropdown">
                        <a href="http:google.com" class="dropbtn">Apply for Awards</a>
                        <div class="dropdown-content">
                            <a href="http:google.com">Award 1</a>
                            <a href="http:google.com">Award 2</a>
                            <a href="http:google.com">Award 3</a>
                        </div>
                    </li>
                    <li><a href="http:google.com">Profile</a></li>
                    <li style="float:right"><a href="http:google.com"">Log Out</a></li>
                </ul>
        
        </div>
        
        <div align="center">
            <div class="notices"></div>
        </div>
        
	
	</body>
</html>