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
                <p id="lol">ID Number</p>
                <input type="text" id="idNum" name="idNum" placeholder="e.g.11134516" onblur="checkIdNumber(this, getElementById('idError'))">
                <!-- idErrorMain 1: Invalid ID -->
                <div id="idErrorMain1">Your ID number must consist of 8 numbers</div>
                
                <!-- idError 1: incorrect ID; not all digits -->
                <div id="idError1">Your ID number must only contain numbers.</div>
                
                <!-- idError 2: incorrect ID; more or less than 8 digits -->
                <div id="idError2">Your ID number is not 8 digits.</div>
                
                <!-- idError 3: ID taken already -->
                <div id="idError3">This ID number has already been taken.</div>
                
                <p>Last Name</p> 
                <input type="text" id="lastName" name="lastName" onblur="checkStringField(this, getElementById('lastNameError'))">
                <!-- lastNameErrorMain: Invalid LastName -->
                <div id="lastNameErrorMain">Invalid last name.</div>
                
                <!-- lastNameError -->
                <div id="lastNameError">Your last name only contains letters.</div>
                
                <p>First Name</p> 
                <input type="text" id="firstName" name="firstName" onblur="checkStringField(this, getElementById('firstNameError'))">
                <!-- firstNameErrorMain: Invalid FirstName -->
                <div id="firstNameErrorMain">Invalid first name.</div>
                
                <!-- firstNameError -->
                <div id="firstNameError">Your first name only contains letters.</div>
                
                <p>Middle Name</p> 
                <input type="text" id="middleName" name="middleName"  onblur="checkStringField(this, getElementById('middleNameError'))"> 
                <!-- middleNameErrorMain: Invalid MiddleName -->
                <div id="middleNameErrorMain">Invalid middle name.</div>
                
                <!-- middleNameError -->
                <div id="middleNameError">>Your middle name only contains letters.</div>
                
                
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
                <div id="courseErrorMain">Your course must be abbreviated version</div>
                
                <!-- courseError -->
                <div id="courseError">Please write your full course name</div>

                <p>Email Address</p>
                <input type="text" id="email" name="email" onblur="checkEmailField(this, getElementById('emailError'))">
                <!-- emailError: taken already -->
                <div id="emailError">This e-mail has already been taken.</div>
                
                <p>Password</p>
                <input type="password" id="password" name="password" onblur="checkPasswordField(this, getElementById('passwordError'))"><br>
                <!-- passwordErrorMain 1: Invalid Password-->
                <div id="passwordErrorMain1">Your password must be at least 8 characters with at least 1 number and 1 special character.</div>
                
                <!-- passwordError 1: less than 8 characters -->
                <div id="passwordError1">Your password must be at least 8 characters.</div>
                
                <!-- passwordError 2: either no number or no special character -->
                <div id="passwordError2">Your password must be have at least 1 number and at least 1 special character.</div>
                
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
        <script>
            // tests
            //$('#idNum').css("border-color", "indianred");
 
        </script>
   	</body>
</html>