<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
			<title>Home</title>
		<link rel = "stylesheet" type = "text/css" href = "css/adminhomepage.css">
		
		<script type="text/javascript" src="jquery-3.2.1.min.js"></script>
		<script type="text/javascript" src="materialize/js/materialize.min.js"></script>
	</head>
	<body>

		<ul>
		    <li><a href="AdminHomePage.jsp">Home</a></li>
		    <li><a href="ViewStudents.jsp" id = "viewStudents">View Students</a></li>
		    <li><a href="http:google.com">Profile</a></li>
		    <li style="float: right"><a href="http:google.com">Log Out</a><li>
		</ul>
        
        
        <div align="center">
            <div class="notices"></div>
        </div>
        
		<script src="script/HomePage.js"></script>
    </body>
</html>