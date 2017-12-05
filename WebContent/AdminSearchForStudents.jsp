<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Admin Search</title>
	
	        <link rel="stylesheet" type="text/css" href="materialize/css/materialize.min.css">
	        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	        
	        <!-- Moved some scripts right before the end of body. -->
	        
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
                        <li><a class="modal-trigger" href="#newannounce"> New Announcement</a></li>
                    </ul>
                </div>
            </div>
        </nav> 
        
      	<div id="searcher">
            <div id = "searchDiv" class="input-field z-depth-1" id = "searchDiv">
                <form id = "searchForm">
                    <input type="search" id="search" name="searchbar" >
                    <label class="label-icon" for="search" style="color: #006f4a"><i class="material-icons">search</i></label>
                </form>
		  </div>
        
        	
            <div id="filterContainer">
                <div id="dropAward">
                    <div class="input-field">
                        <select id="dropDownFilterAwards" name="filterAwards" class="dropDown">
                            <option value="0">Award</option>
                            <option value="1">GL<!--Gawad Lasalyano--></option>
                            <option value="2">TOSP<!--Ten Outstanding Students of the Philippines--></option>
                            <option value="3">AYLC<!--Ayala Young Leaders Congress--></option>
                            <!--
                            <option value="4">Qualified for Gawad Lasalyano</option>
                            <option value="5">Qualified for Ten Outstanding Students of the Philippines</option>
                            <option value="6">Qualified for Ayala Young Leaders Congress</option>
                            -->
                        </select>
                    </div>
                </div>
                
                <div id="dropCollege">
                    
                    <div class="input-field">
                        <select id="dropDownFilterCollege" name="filterCollege" class="dropDown">
                            <option value="0">College</option>
                            <option value="1">CCS<!--College of Computer Studies--></option>
                            <option value="2">RVRCOB<!--Ramon V. del Rosario College of Business--></option>
                            <option value="3">BAGCED<!--Br. Andrew Gonzales College of Education--></option>
                            <option value="4">GCOE<!--Gokongwei College of Engineering--></option>
                            <option value="5">CLA<!--College of Liberal Arts--></option>
                            <option value="6">COS<!--College of Science--></option>
                            <option value="7">SOE<!--School of Economics--></option>
                        </select>
                    </div>
                    
                </div>
            </div>
        </div> 
		
        <div id="searchResultInfo" class="left-align">
            <p id="searchResultText"></p>
        </div>
        
      	<div id="searchResultFeed" class="z-depth-2">
            <div id="tableHeader" class="z-depth-1">
                <table>
                    <tr>
                        <th class="tableIdNum center-align white-text">ID Number</th>
                        <th class="tableName center-align white-text">Name</th>
                        <th class="tableCollege center-align white-text">College</th>
                        <th class="tableAward center-align white-text">Award</th>
                        </tr>
                </table>
            </div>
            
                <table id="tableDetails">
                    <!-- <tr>
                        <td class="tableIdNum center-align">11436166</td>
                        <td class="tableName left-align">Juan Johann John J. Johannes</td>
                        <td class="tableCollege center-align">BAGCED</td>
                        <td class="tableAward center-align"> GL</td>
                    </tr> -->
                </table>
            
            </div> 
      	
        <div id="newannounce" class="modal">
            <div class = "annhead">[NEW ANNOUNCEMENT]</div>
            <div class ="annwrap">
                <form action="createPost" method="POST" id="createPostForm">
					<p class="ltags">Title</p>
					<input type="text" id="anntitle" name = "anntitle">
					<p class="ltags">Description</p>
					<textarea type="text" id="annbody" name = "annbody"></textarea>
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
        
        
        <!-- Scripts originally from head-->
        <script type="text/javascript" src="jquery-3.2.1.min.js"></script>
        <script type="text/javascript" src="materialize/js/materialize.min.js"></script>
        <script type="text/javascript" src="script/AdminHomePage.js"></script>
        <script type="text/javascript" src="script/AdminSearch.js"></script>
        
        <script>
        $('#searchResultText').text("Found # students")
        </script>
	</body>
</html>