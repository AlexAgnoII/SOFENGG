<html>
    <head>
        <link rel="stylesheet" type="text/css" href="materialize/css/materialize.min.css">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        
        <script type="text/javascript" src="jquery-3.2.1.min.js"></script>
        <script type="text/javascript" src="materialize/js/materialize.min.js"></script>
        
        <!-- INSERT CORRECT STYLESHEET BELOW -->
        <link rel = "stylesheet" type="text/css" href="css/viewprofile.css">
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
                        <li><a href="#!">Profile</a></li>
                        <li><a href="#!">Annoucements</a></li>
                    </ul>
                </div>
                
                <div id="dp3">
                    <p id="ann">Notification here</p>
                </div>
            </div>
        </nav> 
        
        <!-- others below -->
        <div id="profilefeed">
            <ul class="collapsible" data-collapsible="expandable">
                
                <!-- Profile Overview -->
                <li>
                  <div class="collapsible-header active"><i class="large material-icons">star</i>PROFILE OVERVIEW</div>
                  <div class="collapsible-body active">
                      
                      <p id="stname">${loggedUser.firstName} ${loggedUser.middleName} ${loggedUser.lastName}</p>
                      <p class="profow">${loggedUser.studentId}</p>
                      <p class="profow">Email Address</p>
                      <p class="profow">${loggedUser.course}</p>
                      <p class="profow">${loggedUser.college}</p>
                      
                      <br>
                  </div>
                </li>
                
                <!-- Personal Information -->
                <li>
                  <div class="collapsible-header"><i class="large material-icons">account_circle</i>PERSONAL INFORMATION</div>
                    
                  <div class="collapsible-body">
                     <a class="waves-effect waves-light btn" id="PIedit">EDIT</a>
                      
                     <form id="PIform" action="updatePersonal" method="POST">
	                     <p class="ltags">Address</p> 
	                     <input type="text" id="address" name="address" value="${loggedUser.address}" disabled>
	                     <!--  <p id="staddress" class="stinfo1">${loggedUser.address}</p> -->
	
	                     <div class="wrap">
	                        <div class="f">
	                            <p class="ltags">City</p> <span id="citySpan"></span>
	                            <input type="text" id="city" name="city" value="${loggedUser.city}" disabled>
	                            <!-- <p id="stcity" class="stinfo2">${loggedUser.city}</p> -->
	
	                            <p class="ltags">Country</p> <span id="countrySpan"></span>
	                            <input type="text" id="country" name="country" value="${loggedUser.country}" disabled>
	                            <!-- <p id="stcountry" class="stinfo2">${loggedUser.country}</p> -->
	
	                            <p class="ltags">Mobile Number</p> <span id="cellSpan"></span>
	                            <input type="text" id="cell" name="cell" value="${loggedUser.celNo}" disabled>
	                            <!-- <p id="stmob" class="stinfo2">${loggedUser.celNo}</p> -->
	                        </div>
	
	                        <div class="f">
	                            <p class="ltags">Province</p> <span id="provSpan"></span>
	                            <input type="text" id="prov" name="prov"
	                                    value="${loggedUser.province}" disabled>
	                            <!-- <p id="stprov" class="stinfo2">${loggedUser.province}</p> -->
	
	                            <p class="ltags">Zip Code</p> <span id="zipSpan"></span>	
	                            <input type="text" id="zip" name="zip" value="${loggedUser.zip}" disabled>
	                            <!-- <p id="stzip" class="stinfo2">${loggedUser.zip}</p> -->
	
	                            <p class="ltags">Telephone Number</p> <span id="telSpan"></span>
	                            <input type="text" id="tel" name="tel" value="${loggedUser.telNo}" disabled>
	                            <!-- <p id="sttel" class="stinfo2">${loggedUser.telNo}</p> -->
	                        </div>
	                    </div>
	
	                    <!-- <br><br><li><div class="divider"></div></li><br> -->  
	
	                    <div class="wrap">
	                        <div class="f">
	                            <p class="ltags">Birthday</p>
	                            <input type="date" id="bday" name="bday" value="${loggedUser.birthday}" disabled>
	                            <!-- <p id="stbday" class="stinfo3">${loggedUser.birthday}</p> -->
	
	                            <p class="ltags">Civil Status</p>
	                            <input type="text" id="civil" name="civil" value="${loggedUser.civil}" disabled>
	                            <!-- <p id="stciv" class="stinfo3">${loggedUser.civil}</p> -->
	                        </div>
	
	                        <div class="f">
	                            <p class="ltags">Age</p> 
	                            <input type="text" id="age" name="age" value="${loggedUser.age}" disabled>
	                            <!-- <p id="stage" class="stinfo3">${loggedUser.age}</p> -->
	
	                            <p class="ltags">Nationality</p> <span id="nationSpan"></span>
	                            <input type="text" id="citizen" name="citizen" value="${loggedUser.citizen}" disabled>
	                            <!-- <p id="stcitzen" class="stinfo3">${loggedUser.citizen}</p> -->
	                        </div>
	
	                        <div class="f">
	                            <p class="ltags">Gender</p> 
	                            <input type="text" id="gender" name="gender" value="${loggedUser.gender}" disabled>
	                            <!-- <p id="genderpi" class="stinfo3">${loggedUser.gender}</p> -->
	                        </div>
	                     </div>
	                     
                     </form> <!-- PIForm -->
                     
                     <br><br>  
                     <a class="waves-effect waves-light btn" id="PIsave">SAVE</a>  
                     <a class="waves-effect waves-light btn" id="PIcancel">CANCEL</a>  
                                                
                  </div> <!-- end of div tag Personal Information -->
                </li>
                
                <!-- Family Background -->
                <li>
                    <div class="collapsible-header"><i class="large material-icons">group</i>FAMILY BACKGROUND</div>
                    
                    <div class="collapsible-body">
                        <a class="waves-effect waves-light btn" id="FBedit">EDIT</a>
                        
                        <div class="wrap">
                            <div class="f">
                            	<button id="addSibling">Add Sibling</button>
                            	
                                <p class="ltags">Father's Name</p> 
                                <input type="text" id="dname" name="dname" value="Papa bear" disabled>
                                
                                <p class="ltags">Mother's Name</p>
                                <input type="text" id="mname" name="mname" value="Mama bear" disabled>
                                
                                <p class="ltags">Sibling/s' Name</p>
                                <input type="text" id="sname" name="sname" value="Sibling Bear" disabled>
                                
                       
                            </div>
                            
                            <div class="f">
                                <p class="ltags">Occupation</p>
                                <input type="text" id="dwork" name="dwork" value="Bear" disabled>
                                
                                <p class="ltags">Occupation</p>
                                <input type="text" id="mwork" name="mwok" value="Bear" disabled>
                             
                                <p class="ltags">Occupation</p>
                                <input type="text" id="swork" name="swork" value="Bear" disabled>
                            </div>
                            
                            <div class="f">
                                <p class="ltags">Birthday</p>
                                <input type="text" id="dbday" name="bday" value="Tomorrow" disabled>
                                
                                <p class="ltags">Birthday</p>
                                <input type="text" id="mbday" name="mbday" value="Tomorrow" disabled>
                                                           
                                <p class="ltags">Birthday</p>
                                <input type="text" id="sbday" name="sbday" value="Tomorrow" disabled>
                            </div>
                        
                        </div>
                        <br><br>
                        <a class="waves-effect waves-light btn" id="FBsave">SAVE</a>
                        <a class="waves-effect waves-light btn" id="FBcancel">CANCEL</a>
                    </div> <!-- end of div tag Family Background -->    
                </li>
                
                <li>
                    <div class="collapsible-header"><i class="large material-icons">school</i>ACADEMIC BACKGROUND</div>
                    
                    <div class="collapsible-body">
                        <a class="waves-effect waves-light btn" id="FBedit">EDIT</a>
                        
                     
                        <p class="ltags">Accumulation</p>
                        <input type="text" id="failed" name="failed" value="0" disabled>
                
                        
                    </div> <!-- end of div tag Academic Background --> 
                </li>
                
                <li>
                    <div class="collapsible-header"><i class="large material-icons">book</i>INTERNAL INVOLVEMENT</div>
                    
                    <div class="collapsible-body">
                        <a class="waves-effect waves-light btn" id="IIedit">EDIT</a>
                        <form action = "addIntInv" method = "POST" id = "intInv">
	                        <div class="wrap">
	                            <div class="f">
	                                <p class="ltags">Year</p>
	                                <input type="text" id="inyear" name="inyear" value="2017" disabled>
	                            </div>
	                            
	                            <div class="f">
	                                <p class="ltags">Event/Organization</p>
	                                <input type="text" id="inorg" name="inorg" placeholder="Something" disabled>                         
	                            </div>
	                            
	                            <div class="f">
	                                <p class="ltags">Position</p>
	                                <input type="text" id="inpos" name="inpos" placeholder="Someone" disabled>
	                            </div>
	                        </div>
	                        
	                        <br><br>
	                        
	                        <a class="waves-effect waves-light btn" id="IIsave">SAVE</a>
	                        <a class="waves-effect waves-light btn" id="IIcancel">CANCEL</a>
                        </form>
                    </div> <!-- end of div tag Internal Involvement --> 
                
                
                </li>
                
                <li>
                    <div class="collapsible-header"><i class="large material-icons">nature_people</i>EXTERNAL INVOLVEMENT</div>
                    
                    <div class="collapsible-body">
                        <a class="waves-effect waves-light btn" id="EIedit">EDIT</a>
                        <form action = "addExtInv" method = "POST" id = "extInv">
	                        <div class="wrap">
	                            <div class="f">
	                                <p class="ltags">Year</p>
	                                <input type="text" id="exyear" name="exyear" value="2017" disabled>
	                            </div>
	                            
	                            <div class="f">
	                                <p class="ltags">Event/Organization</p>
	                                <input type="text" id="exorg" name="exorg" placeholder="Something" disabled>                         
	                            </div>
	                            
	                            <div class="f">
	                                <p class="ltags">Position</p>
	                                <input type="text" id="expos" name="expos" placeholder="Someone" disabled>
	                            </div>
	                        </div>
	                        
	                        <br><br>
	                        
	                        <a class="waves-effect waves-light btn" id="EIsave">SAVE</a>
	                        <a class="waves-effect waves-light btn" id="EIcancel">CANCEL</a>
                        </form>
                    </div> <!-- end of div tag External Involvement --> 
                </li>
                
               
              </ul>
        </div>
        
        
    <script type="text/javascript" src="script/ViewProfile.js"></script>
    </body>
</html>