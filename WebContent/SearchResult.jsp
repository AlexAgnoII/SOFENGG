<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="materialize/css/materialize.min.css">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        
        <script type="text/javascript" src="jquery-3.2.1.min.js"></script>
        <script type="text/javascript" src="materialize/js/materialize.min.js"></script>
        
        <!-- INSERT CORRECT STYLESHEET BELOW -->
        <link rel = "stylesheet" type="text/css" href="css/searchresult.css">
    </head>
    
    <body>
        <div id="left"></div>
		<div id="right"></div>
		<div id="top"></div>
		<div id="bottom"></div>
        
        <a class="dropdown-button btn" href='#' data-activates="menu" id="tbox">
            <div id="wrapper">
                <i class="material-icons" id="arrow">near_me</i>
                <p id="title">DLSU STUDENT INFORMATION SYSTEM</p>
            </div>   
        </a>
           
        <ul id="menu" class='dropdown-content'>
            <li><a href="homepage.jsp" id="logoutb">logout</a></li>
        </ul>
        
        <div id="searchdiv">
            <form>
                <input type="text" id="searchbar" name="searchbar" placeholder="search">
                <a><i class="material-icons" id="magglass">search</i></a>
            </form>
        </div>
        
        <div id="srfeed">
            <div id="studentres">
                <p id="srname" name="srname">Student Result Goes Here</p>
                <p id="sridnum" name="sridnum">114xxxxx</p>
                <p id="srcol" name="srcol">CCS</p>
            
            </div>
            
            <div id="orgres">
                <p id="or" name="or">Org Result Goes Here</p>
 
            </div>
            
            <div id="projres">
                <p id="pr" name="pr">Project Result Goes Here</p>
            </div>
            
        </div>
        
        
        
    </body>
</html>