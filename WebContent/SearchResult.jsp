<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
            <li><a href="logout" id="logoutb">logout</a></li>
        </ul>
        
        <div id="searchdiv">
            <form action="search" method="POST" id = "searchForm">
                <input type="text" id="searchbar" name="searchbar" placeholder="search">
                <a><i class="material-icons" id="magglass">search</i></a>
            </form>
        </div>
        
        <div id="srfeed">
        	<c:forEach items = "${studentList}" var = "s">
	        	<div id="studentres">
	                <p id="srname" name="srname">${s.firstName} ${s.middleName} ${s.lastName}</p>
	                <p id="sridnum" name="sridnum">${s.studentId}</p>
	                <p id="srcol" name="srcol">${s.college}</p>
	            </div>
            </c:forEach>
            <!-- 
            <div id="orgres">
                <p id="or" name="or">Org Result Goes Here</p>
 
            </div>
            
            <div id="projres">
                <p id="pr" name="pr">Project Result Goes Here</p>
            </div> -->
            
        </div>
        
        
        
    </body>
</html>