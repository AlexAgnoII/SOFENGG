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
		
	    <link rel = "stylesheet" type="text/css" href="css/changepassword.css">

		<title></title>
	</head>
	
	<body>
		<div id="left"></div>
		<div id="right"></div>
		<div id="top"></div>
		<div id="bottom"></div>
		
        <nav>
            <div class="nav-wrapper">
               <a class='brand-logo center' data-activates='dp2' href='HomePage.jsp' id="tbox">
                <div id="wrapper">
                    <img src="css/arrow.png" id="arrow">
                    <p id="title">DLSU <br>STUDENT<br> INFORMATION<br> SYSTEM</p>
                </div>   
               </a>
            </div>
        </nav>
        
        <div id="changepwfeed">
            <!-- insert form below -->
                    <br>
                    <!-- TENTATIVE: constraint divs -->
                    <p class="center-align" id="error1">Passwords does not match</p>
                    <p class="center-align" id="error2">Password is the same with your old password</p>
                    
                    <p class="ltags">Current Password</p>	
                    <input type="password" id="oPassword" name="oPassword">
                    
                    <div class="errorPlaceholder">
                        <div class="errors" id="opwError1">*Invalid password*</div>
                    </div>
      
                    <p class="ltags">New Password</p>	
                      <input type="password" id="newPassword" name="newPassword" onblur="checkPasswordField(this)">
            
                    <div class="errorPlaceholder">
                        <div class="errors" id="npwError1">*Password must contain a capital letter, a number and a symbol*</div>
                        <div class="errors" id="pwError2">*This password is already in use*</div>
                    </div>
            
                    <p class="ltags">Confirm New Password</p>	
                      <input type="password" id="nPasswordConfirm" name="nPasswordConfirm" onblur="checkPasswordEqual(this, getElementById('newPassword'))">
            
                    <div class="errorPlaceholder">
                        <div class="errors" id="npwcError1">*Password did not match*</div>
                    </div>
            
                <br><br>
                <a class="waves-effect waves-light btn" id="ResetPassword">Reset Password</a>
                <br><br>           
            
        </div>
        <input id="token" type="hidden" name="token" value="${U}">
         <!-- Originally from head. -->
        <script type="text/javascript" src="jquery-3.2.1.min.js"></script>
        <script type="text/javascript" src="materialize/js/materialize.min.js">            
        </script>
        <script type="text/javascript" src="script/ChangePassword.js"></script>
   	</body>
</html>