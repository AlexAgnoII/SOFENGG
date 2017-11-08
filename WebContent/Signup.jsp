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
		
        <nav>
            <div class="nav-wrapper">
               <a class='brand-logo center' data-activates='dp2' href='#' id="tbox">
                <div id="wrapper">
                    <img src="css/arrow.png" id="arrow">
                    <p id="title">DLSU <br>STUDENT<br> INFORMATION<br> SYSTEM</p>
                </div>   
               </a>
            </div>
        </nav>
        
        <div id= sufeed>
            <form action="signUp" method="POST" id="signUpForm">
                <p>ID Number</p>
                <input type="text" id="idNum" name="idNum" onblur="checkIdNumber(this, getElementById('idError'))">
                <!-- idErrorMain 1: Invalid ID -->
                <span id="idErrorMain1"><br>Invalid ID</span>
                
                <!-- idError 1: incorrect ID; not all digits -->
                <span id="idError1"><br>Your ID number must only contain numbers.</span>
                
                <!-- idError 2: incorrect ID; more or less than 8 digits -->
                <span id="idError2"><br>Your ID number is not 8 digits.</span>
                
                <!-- idError 3: ID taken already -->
                <span id="idError3"><br>This ID number has already been taken.</span>
                
                <p>Last Name</p> 
                <input type="text" id="lastName" name="lastName" onblur="checkStringField(this, getElementById('lastNameError'))">
                <!-- lastNameErrorMain: Invalid LastName -->
                <span id="lastNameErrorMain"><br>Invalid last name.</span>
                
                <!-- lastNameError -->
                <span id="lastNameError"><br>Your last name only contains letters.</span>
                
                <p>First Name</p> 
                <input type="text" id="firstName" name="firstName" onblur="checkStringField(this, getElementById('firstNameError'))">
                <!-- firstNameErrorMain: Invalid FirstName -->
                <span id="firstNameErrorMain"><br>Invalid first name.</span>
                
                <!-- firstNameError -->
                <span id="firstNameError"><br>Your first name only contains letters.</span>
                
                <p>Middle Name</p> 
                <input type="text" id="middleName" name="middleName"  onblur="checkStringField(this, getElementById('middleNameError'))"> 
                <!-- middleNameErrorMain: Invalid MiddleName -->
                <span id="middleNameErrorMain"><br>Invalid middle name.</span>
                
                <!-- middleNameError -->
                <span id="middleNameError"><br>Your middle name only contains letters.</span>
                
                
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
                
                <p>Course</p>
                <input type="text" id="courseName" name="course" onblur="checkStringField(this, getElementById('courseError'))">
                <br>
                <!-- courseErrorMain: Invalid Course -->
                <span id="courseErrorMain"><br>Invalid course.</span>
                
                <!-- courseError -->
                <span id="courseError">Please write your full course name</span>

                <p>Email Address</p>
                <input type="text" id="email" name="email" onblur="checkEmailField(this, getElementById('emailError'))"><br>
                <!-- emailError: taken already -->
                <span id="emailError"><br>This Email has already been taken.</span>
                
                <p>Password</p>
                <input type="password" id="password" name="password" onblur="checkPasswordField(this, getElementById('passwordError'))"><br>
                <!-- passwordErrorMain 1: Invalid Password-->
                <span id="passwordErrorMain1"><br>Invalid password.</span>
                
                <!-- passwordError 1: less than 8 characters -->
                <span id="passwordError1"><br>Your password must be at least 8 characters.</span>
                
                <!-- passwordError 2: either no number or no special character -->
                <span id="passwordError2"><br>Your password must be have at least 1 number and at least 1 special character.</span>
                
                <p>Re-enter Password</p>
                <input type="password" id="password2" name="password2"><br><br> 
                
                <a class="waves-effect waves-light btn" id="backb">BACK</a>
                <a class="waves-effect waves-light btn modal-trigger" href="#modal1" id="signupb" type=submit>SIGNUP</a>
                <br>
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
        <!-- <script>$('#idError1').show();</script> -->
   	</body>
</html>