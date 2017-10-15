<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<!--Import materialize.css-->
        <link rel="stylesheet" type="text/css" href="materialize/css/materialize.min.css">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        
        <script type="text/javascript" src="jquery-3.2.1.min.js"></script>
        <script type="text/javascript" src="materialize/js/materialize.min.js"></script>
		
	    <link rel = "stylesheet" type="text/css" href="css/signup.css">

		<title>Signup</title>
	</head>
	
	<body>
		<div id="left"></div>
		<div id="right"></div>
		<div id="top"></div>
		<div id="bottom"></div>
        
        <div id="pulse">
            <div id= "wrapper">
                <i class="material-icons" id="arrow">near_me</i>
                <p id="title">DLSU STUDENT INFORMATION SYSTEM</p>
            </div>
        </div>
        
        <div id= sufeed>
            <form action="signUp" method="POST" ">
                <p id="idn">ID Number</p> 
                <input type="text" id="idNum" name="idNum"><br> 
                
                <p id="lname">Last Name</p>
                <input type="text" id="lastName" name="lastName"><br> 
                
                <p id="fname">First Name</p>
                <input type="text" id="firstName" name="firstName"><br> 
                
                <p id="mname">Middle Name</p>
                <input type="text" id="middleName" name="middleName"><br> 
                
                <div id="nums">
                    <div id="m">
                        <p id="cnum">Mobile Number</p>
                        <input type="text" id="celNo" name="celNo"> 
                    </div>
                
                    <div id="t">
                        <p id="tnum">Telephone Number</p>
                        <input type="text" id="telNo" name="telNo"><br> 
                    
                    </div>
                    
                </div>

                <p id="emailt">Email Address</p>
                <input type="text" id="email" name="email"><br> 
                
                <p id="pw">Password</p>
                <input type="password" id="password" name="password"><br>
                
                <p id="pw">Re-enter Password</p>
                <input type="password" id="password2" name="password2"><br><br> 
                
                <!--    <button id="backb">BACK</button>  -->               
             	<!--   <input id = "signupb" type="submit" value="SIGNUP">  -->
                
                <a class="waves-effect waves-light btn" id="backb">BACK</a>
                <a class="waves-effect waves-light btn modal-trigger" href="#modal1" id="signupb" type=submit>SIGNUP</a>
            </form>
            
            <div id="modal1" class="modal modal-fixed-footer">
                <div class="modal-content">
                  <p id="modver">A verification link has been sent to the email address you used to create your account. Please click on the link to verify your account and proceed with the registration.</p>
                </div>
                <div class="modal-footer">
                  <a href="#!" class="modal-action modal-close waves-effect waves-green btn-flat " id = "proceedModal">Proceed</a>
                </div>
              </div>

        </div>
             
		<script src="script/Signup.js"></script>
   	</body>
</html>