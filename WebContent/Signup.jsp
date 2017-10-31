<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<!--Import materialize.css-->
        <link rel="stylesheet" type="text/css" href="materialize/css/materialize.min.css">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        
		<!-- Moved some scripts right before the end of body. -->
		
	    <link rel = "stylesheet" type="text/css" href="css/signup.css">

		<title>Signup</title>
	</head>
	
	<body>
		<div id="left"></div>
		<div id="right"></div>
		<div id="top"></div>
		<div id="bottom"></div>
		
       <div id="navbar">
            <a class="dropdown-button btn" href='#' data-activates="menu" id="tbox">
            <div id="wrapper">
                <i class="material-icons" id="arrow">near_me</i>
                <p id="title">DLSU STUDENT INFORMATION SYSTEM</p>
            </div>   
            </a>
        </div>
        
        <div id= sufeed>
            <form action="signUp" method="POST" id="signUpForm">
                <p>ID Number</p> <span id="idError"></span>
                <input type="text" id="idNum" name="idNum" onblur="checkIdNumber(this, getElementById('idError'))"><br> 
                
                <p>Last Name</p> <span id="lastNameError"></span>
                <input type="text" id="lastName" name="lastName"><br> 
                
                <p>First Name</p> <span id="firstNameError"></span>
                <input type="text" id="firstName" name="firstName"><br> 
                
                <p>Middle Name</p> <span id="middleNameError"></span>
                <input type="text" id="middleName" name="middleName"><br> 
                
                <p>College</p>
                
                <div class="input-field">
	                <select id="dropDownCollege" name="college" class="ddcollege">
	                    <option value="" disabled selected>Select your College:</option>
	                	<option value="1">College of Computer Studies</option>
	                	<option value="2">Ramon V. del Rosario College of Business</option>
	                	<option value="3">Br. Andrew Gonzales College of Education</option>
	                	<option value="4">Gokongwei College of Engineering</option>
	                	<option value="5">College of Liberal Arts</option>
	                	<option value="6">College of Science</option>
	                	<option value="7">School of Economics</option>
	                </select>
                </div>
                
                
                <p>Course</p> <span id="courseError"></span>
                <input type="text" id="courseName" name="course"> <br>

                <p>Email Address</p> <span id="emailError"></span>
                <input type="text" id="email" name="email"><br> 
                
                <p>Password</p> <span id="passwordError"></span>
                <input type="password" id="password" name="password"><br>
                
                <p>Re-enter Password</p>
                <input type="password" id="password2" name="password2"><br><br> 
                
                <a class="waves-effect waves-light btn" id="backb">BACK</a>
                <a class="waves-effect waves-light btn modal-trigger" href="#modal1" id="signupb" type=submit>SIGNUP</a>
            </form>

        </div>
                    
            <div id="modal1" class="modal modal-fixed-footer">
                <div class="modal-content">
                  <p id="modver">A verification link has been sent to the email address you used to create your account. Please click on the link to verify your account and proceed with the registration.</p>
                </div>
                <div class="modal-footer">
                  <a href="#!" class="modal-action modal-close waves-effect waves-green btn-flat " id = "proceedModal">Proceed</a>
                </div>
              </div>
         
         <!-- Originally from head. -->
        <script type="text/javascript" src="jquery-3.2.1.min.js"></script>
        <script type="text/javascript" src="materialize/js/materialize.min.js"></script>
		<script src="script/Signup.js"></script>
   	</body>
</html>