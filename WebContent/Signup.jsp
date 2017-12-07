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
	
	<body onkeypress="enterKeyEvent(event)">
		<div id="left"></div>
		<div id="right"></div>
		<div id="top"></div>
		<div id="bottom"></div>
		
        <nav>
            <div class="nav-wrapper">
               <a class='brand-logo center' data-activates='dp2' href='#' id="tbox" tabindex=-1>
                <div id="wrapper">
                    <img src="css/arrow.png" id="arrow">
                    <p id="title">DLSU <br>STUDENT<br> INFORMATION<br> SYSTEM</p>
                </div>   
               </a>
            </div>
        </nav>
        
       <div id="sufeed">
            <form action="signUp" method="POST" id="signUpForm">
                <p id="lol">ID Number</p>
                <input type="text" id="idNum" name="idNum" placeholder="e.g.11134516" tabindex=1 onblur="checkIdNumber(this)">
                    <p id="idnumerrorPlaceHolder" class="errorplaceholder">_______________________________________</p>
                    <p id="idnumerror" class="errors">*Invalid ID Number*</p>
                  
                <p>Last Name</p>
                <input type="text" id="lastName" name="lastName" tabindex=2 onblur="checkStringField(this, getElementById('lnameerrorPlaceHolder'), getElementById('lnameerror'), getElementById('lastName'))">
                <p id="lnameerrorPlaceHolder" class="errorplaceholder">_________________________________________________________________________________________________________________________________________</p>
                <p id="lnameerror" class="errors">*Invalid last name*</p>

                <div class="wrap">
                    <div class="f">
                        <p>First Name</p> 
                        <input type="text" class="twos" id="firstName" name="firstName" tabindex=3 onblur="checkStringField(this, getElementById('fnameerrorPlaceHolder'), getElementById('fnameerror'), getElementById('firstName'))">
                        <p id="fnameerrorPlaceHolder" class="errorplaceholder">___________________________________________________________________</p>
                        <p id="fnameerror" class="errors">*Invalid first name*</p>
                        
                        <p>College</p>
                        <div class="input-field">
                            <select id="dropDownCollege" name="college" class="ddcollege" tabindex=5 onchange="checkCollege(this)">
                                <option value="0" disabled selected>Select your College:</option>
                                <option value="1">College of Computer Studies</option>
                                <option value="2">Ramon V. del Rosario College of Business</option>
                                <option value="3">Br. Andrew Gonzales College of Education</option>
                                <option value="4">Gokongwei College of Engineering</option>
                                <option value="5">College of Liberal Arts</option>
                                <option value="6">College of Science</option>
                                <option value="7">School of Economics</option>
                            </select>
                        </div>
                        <p id="collegeerrorPlaceHolder" class="errorplaceholder">___________________________________________________________________</p>
                        <p id="collegeerror" class="errors">*Invalid selected college*</p>
                    </div>
                    
                     <div class="f">
                        <p>Middle Name</p> 
                        <input type="text" class="twos" id="middleName" name="middleName"  tabindex=4 onblur="checkStringField(this, getElementById('mnameerrorPlaceHolder'), getElementById('mnameerror'), getElementById('middleName'))">
                         <p id="mnameerrorPlaceHolder" class="errorplaceholder">___________________________________________________________________</p>
                         <p id="mnameerror" class="errors">*Invalid middle name*</p>
                         
    
                        <div id="passwordInfo">
                             <p id="pCourse">Degree</p>
                             <i class="tiny material-icons tooltipped" data-position="right" data-delay="50" data-tooltip="Course should be written without spaces and dashes (-) like CIV, MKT, INSYS and so on" id="iPassword">info_outline</i>
                         </div>
                        <input id="courseName" type="text" class="twos" name="course" tabindex=6 onblur="checkStringField(this, getElementById('courseerrorPlaceHolder'), getElementById('courseerror'))">
                        <p id="courseerror" class="errors">*Invalid degree code*</p>
                    
                    </div>
                </div>
                
                <p>Email Address</p>
                <input type="text" id="email" name="email" tabindex=7 onblur="checkEmailField(this)">
                <p id="emailerrorPlaceHolder" class="errorplaceholder">_________________________________________________________________________________________________________________________________________</p>
                <p id="emailerror" class="errors">*Invalid email address*</p>
                
                <div class="wrap">
                    <div class="f">
                         <div id="passwordInfo">
                             <p id="pPassword">Password</p>
                             <i class="tiny material-icons tooltipped" data-position="right" data-delay="50" data-tooltip="Password should contain a capital letter, a number, and a symbol along with lowercase letters." id="iPassword">info_outline</i>
                         </div>
                         <input type="password" class="twos" id="password" name="password" tabindex=8 onblur="checkPasswordField(this)">
                         <p id="pwerrorPlaceHolder" class="errorplaceholder">___________________________________________________________________</p>
                         <p id="pwerror" class="errors">*Password must contain a capital letter, a number and a symbol along with lowercase letters*</p>
                    </div>
                    
                    <div class="f">
                         <p>Re-enter Password</p>
                         <input type="password" id="password2" class="twos" name="password2" tabindex=9 onblur="checkPasswordEqual(this, getElementById('password'))">
                         <p id="pwerrorPlaceHolderNotEqual" class="errorplaceholder">___________________________________________________________________</p>
                         <p id="pwNotEqual" class="errors">*Password did not match*</p>
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
        
        </script>
   	</body>
</html>