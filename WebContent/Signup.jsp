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
                <p id="idnumerror" class="errors">*Invalid ID Number*</p>
                  
                <p>Last Name</p>
                <input type="text" id="lastName" name="lastName" onblur="checkStringField(this, getElementById('lastNameError'))">
                <p id="lnameerror" class="errors">*Invalid last name*</p>

                <div class="wrap">
                    <div class="f">
                        <p>First Name</p> 
                        <input type="text" class="twos" id="firstName" name="firstName" onblur="checkStringField(this, getElementById('firstNameError'))">
                        <p id="fnameerror" class="errors">*Invalid first name*</p>
                        
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
                        <p id="collegeerror" class="errors">*Invalid selected college*</p>
                    </div>
                    
                     <div class="f">
                        <p>Middle Name</p> 
                        <input type="text" class="twos" id="middleName" name="middleName"  onblur="checkStringField(this, getElementById('middleNameError'))">
                         <p id="mnameerror" class="errors">*Invalid middle name*</p>
                         
                        <p>Course</p>
                        <input type="text" class="twos" id="course" name="course" placeholder="e.g. MKT, CIV, INSYS" onblur="checkStringField(this, getElementById('courseError'))">
                        <p id="courseerror" class="errors">*Invalid degree code*</p>
                    
                    </div>
                </div>
                
                <p>Email Address</p>
                <input type="text" id="email" name="email" onblur="checkEmailField(this, getElementById('emailError'))">
                <p id="emailerror" class="errors">*Invalid email address*</p>
                
                <div class="wrap">
                    <div class="f">
                         <p>Password</p>
                         <input type="password" id="password" class="twos" name="password" onblur="checkPasswordField(this, getElementById('passwordError'))">
                         <p id="pwerror" class="errors">*Passwords do not match*</p>
                    </div>
                    
                    <div class="f">
                         <p>Re-enter Password</p>
                         <input type="password" id="password2" class="twos" name="password2">
                    </div>
                </div>
                
                <br><br><br>
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
        <script>
            // tests
            //$('#idNum').css("border-color", "indianred");
 
        </script>
   	</body>
</html>