<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Admin Search</title>
	
	        <link rel="stylesheet" type="text/css" href="materialize/css/materialize.min.css">
	        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	        
	        <script type="text/javascript" src="jquery-3.2.1.min.js"></script>
	        <script type="text/javascript" src="materialize/js/materialize.min.js"></script>
			<script type="text/javascript" src="script/AdminHomePage.js"></script>
	        
	        <!-- INSERT CORRECT STYLESHEET BELOW -->
        	<link rel = "stylesheet" type="text/css" href="css/adminsearchforstudents.css">
	        
	</head>
	<body>
        <div id="left"></div>
		<div id="right"></div>
		<div id="top"></div>
		<div id="bottom"></div>
        
       <nav>
            <div class="nav-wrapper">
               <a class='brand-logo center' data-activates='dp2' href='AdminHomePage.jsp' id="tbox">
                <div id="wrapper">
                    <img src="css/arrow.png" id="arrow">
                    <p id="title">DLSU <br>STUDENT<br> INFORMATION<br> SYSTEM</p>
                </div>   
               </a>
                
                <a class='dropdown-button btn' href='#' data-activates='dp2' id="menu"><i class="medium material-icons" id="mb">menu</i>MENU</a>
                <a class='dropdown-button btn' href='#' data-activates='dp1' id="drop"><i class="large material-icons" id="dc">arrow_drop_down_circle</i></a>
                <a class='dropdown-button btn' href="#" id="searchIcon"><i class="large material-icons" id="sb">search</i></a>
                
                <div id="dp1">
                    <ul>
                        <li><a href="#!"> Settings</a></li>
                        <li><a href="logout"> Logout</a></li>
                    </ul>
                </div>
                
                <div id="dp2">
                     <ul>
                        <li><a href="ViewStudents.jsp">View Students</a></li>
                        <li><a class="modal-trigger" href="#newannounce"> New Announcement</a></li>
                    </ul>
                </div>
            </div>
        </nav> 
      	
		<div id = "searchDiv" class="input-field z-depth-1" id = "searchDiv">
			<form action="search" method="GET" id = "searchForm">
                <input type="search" id="search" name="searchbar" >
                <label class="label-icon" for="search" style="color: #006f4a"><i class="material-icons">search</i></label>
            </form>
		</div>
		
      	<div id="searchResultFeed">
            <div class="card-panel green darken-3">
                <table>
                    <tr class="white-text">
                        <td id="sId">11926004</td>
                        <td id="sName">Carl Carlo C. Carlos</td>
                        <td id="sCourse">BS CHM</td>
                        <td id="sCollege">COS</td>
                    </tr>
                </table>
            </div>
            
            <div class="card-panel green darken-3">
                <table id="cardTable">
                    <tr class="white-text">
                        <td id="sId">100115468</td>
                        <td id="sName">Marcelita Gonzales R. Yvarnadez y Llaverde</td>
                        <td id="sCourse">AB PSM</td>
                        <td id="sCollege">CLA</td>
                    </tr>
                </table>
            </div>
            
            <div class="card-panel green darken-3">
                <table id="cardTable">
                    <tr class="white-text">
                        <td id="sId">10465122</td>
                        <td id="sName">Ma. Franchesca Gianina G. Chua</td>
                        <td id="sCourse">BS MGT</td>
                        <td id="sCollege">SOM</td>
                    </tr>
                </table>
            </div>
        </div> 
      	
        <div id="newannounce" class="modal">
            <div class = "annhead">[NEW ANNOUNCEMENT]</div>
            <div class ="annwrap">
                <form action="createPost" method="POST" id="createPostForm">
					<p class="ltags">Title</p>
					<input type="text" id="anntitle" name = "anntitle">
					<p class="ltags">Description</p>
					<input type="text" id="annbody" name = "annbody">
           		</form>
					<br>	
					<button class="waves-effect waves-light btn" id="ANpost" >POST</button>
					<br>
            </div>
        </div>

        <div id="updateAnnounce" class="modal">
            <div class = "annhead">[UPDATE ANNOUNCEMENT]</div>
            <div class = "annwrap">
                <form action="createPost" method="POST" id="createPostForm">
					<p class="ltags">Title</p>
					<input type="text" id="updateTitle" name = "anntitle">
					<p class="ltags">Description</p>
					<input type="text" id="updateBody" name = "annbody">
           		</form>
					<br>	
					<button class="waves-effect waves-light btn" id="ANupdate" >Update</button>
					<br>
            </div>
        </div>      
        
	</body>
</html>