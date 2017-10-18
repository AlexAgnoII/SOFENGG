<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>View Students</title>
        <!--Import Google Icon Font-->
		<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
		
		
		<script type="text/javascript" src="jquery-3.2.1.min.js"></script>
		<script type="text/javascript" src="materialize/js/materialize.min.js"></script>
		
        <link rel = "stylesheet" type = "text/css" href = "css/viewstudents.css">
	</head>
<body>
        
        <div>
                <ul>
                    <li><a href="AdminHomePage.jsp">Home</a></li>
                    <li class="dropdown">
                        <a href="ViewStudents.jsp">View Students</a>
                    </li>
                    <li><a href="http:google.com">Profile</a></li>
                   <li style="float:right"><a>Log Out</a></li>
                </ul>
        
        </div>
        
        <div class="searchFilter" align="center">
            <div class="dropdown">
                <div class="dropbtn">Search by</div>
                <div id="myDropdown" class="dropdown-content">
                    <a href="">Qualified for Award 1</a>
                    <a href="">Qualified for Award 2</a>
                    <a href="">Qualified for Award 3</a>
                    <a href="">Applied for Award 1</a>
                    <a href="">Applied for Award 2</a>
                    <a href="">Applied for Award 3</a>
                    <a href="">Top 5 for Award 1</a>
                    <a href="">Top 5 for Award 2</a>
                    <a href="">Top 5 for Award 3</a>
                </div>
            </div>
        </div>
        
        <div align="center">
            <div class="notices">
                <div class="holder">
                    <div class="detail1">
                        Student
                    </div>
                    <div class="detail2">
                        Award
                    </div>
                </div>
                <div class="holder">
                    <div class="detail1">
                        Student
                    </div>
                    <div class="detail2">
                        Award
                    </div>
                </div>
                <div class="holder">
                    <div class="detail1">
                        Student
                    </div>
                    <div class="detail2">
                        Award
                    </div>
                </div>
            </div>
        </div>
        
	
	</body>
</html>