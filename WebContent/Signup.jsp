<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	    <link rel = "stylesheet" type="text/css" href="css/signup.css">
	    <script type="text/javascript" src="jquery-3.2.1.min.js"></script>
		<title>Signup</title>
	</head>
	
	<body>
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
                
                
                <button id="backb" type="button">BACK</button> 
                <input id = "signupb" type="submit" value="SIGNUP"> 
            </form>

        </div>
        
        <script src="script/Signup.js"></script>
        
	</body>
</html>