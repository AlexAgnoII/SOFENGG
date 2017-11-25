<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="materialize/css/materialize.min.css">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        
        <script type="text/javascript" src="jquery-3.2.1.min.js"></script>
        <script type="text/javascript" src="materialize/js/materialize.min.js"></script>
        
        <!-- INSERT CORRECT STYLESHEET BELOW -->
        <link rel = "stylesheet" type="text/css" href="css/userhomepage.css">
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
                
                <a class='dropdown-button btn' href='#' data-activates='dp2' id="menu"><i class="medium material-icons" id="mb">menu</i>MENU</a>
                
                <a class='dropdown-button btn' href='#' data-activates='dp1' id="drop"><i class="large material-icons" id="dc">arrow_drop_down_circle</i></a>
                
                <a id="notif"><i class="large material-icons" id="nc">notifications</i></a>
                
                
                <div id="dp1">
                    <ul>
                        <li><a href="#!"> Settings</a></li>
                        <li><a href="logout"> Logout</a></li>
                    </ul>
                </div>
                
                <div id="dp2">
                     <ul>
                        <li><a href="viewByStudent">Profile</a></li>
                        <li><a href="UserAnnouncements.jsp">Announcements</a></li>
                    </ul>
                </div>
                
                <div id="dp3">
                    <p id="ann">Notification here</p>
                </div>
            </div>
        </nav> 
        
        <script>
            
            $('#notif').click( function(event){
                event.stopPropagation();
                $('#dp3').slideToggle();
            });
            
            $(document).click( function(){
                $('#dp3').hide();
            });

        </script>
    </body>
</html>