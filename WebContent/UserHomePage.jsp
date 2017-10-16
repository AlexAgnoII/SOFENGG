<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="/materialize/css/materialize.min.css">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        
        <script type="text/javascript" src="/jquery-3.2.1.min.js"></script>
        <script type="text/javascript" src="/materialize/js/materialize.min.js"></script>
        
        <!-- INSERT CORRECT STYLESHEET BELOW -->
        <link rel = "stylesheet" type="text/css" href="css/userhomepage.css">
        
    
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
        
        <!-- add your codes below! :) -->
        
        <div align="center">
            <ul>
                <div style="margin-left:20%"></div>
                <li><a href="UserHomePage.jsp">Home</a></li>
                <li class="dropdown">
                    <a href="http:google.com" class="dropbtn">Apply for Awards</a>
                    <div class="dropdown-content">
                        <a href="http:google.com">Award 1</a>
                        <a href="http:google.com">Award 2</a>
                        <a href="http:google.com">Award 3</a>
                    </div>
                </li>
                <li><a href="view">Profile</a></li>
               <li style="float:right"><a href="logout">Log Out</a></li>
            </ul>
        </div>
        
        <div align="center">
            <div class="notices"></div>
        </div>
       
    </body>
</html>