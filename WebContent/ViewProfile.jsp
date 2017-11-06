<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	                     <input type="text" class="ipfield fullip" name="address" value="${loggedUser.address}" disabled>
	
	                     <div class="wrap">
	                        <div class="f">
	                            <p class="ltags">City</p> <span id="citySpan"></span>
	                            <input type="text" class="ipfield halfip" name="city" value="${loggedUser.city}" disabled>
	
	                            <p class="ltags">Country</p> <span id="countrySpan"></span>
	                            <input type="text" class="ipfield halfip" value="${loggedUser.country}" disabled>
	
	                            <p class="ltags">Mobile Number</p> <span id="cellSpan"></span>
	                            <input type="text" class="ipfield halfip" name="cell" value="${loggedUser.celNo}" disabled>
	                        </div>
	
	                        <div class="f">
	                            <p class="ltags">Province</p> <span id="provSpan"></span>
	                            <input type="text" class="ipfield halfip" name="prov" value="${loggedUser.province}" disabled>
	
	                            <p class="ltags">Zip Code</p> <span id="zipSpan"></span>	
	                            <input type="text" class="ipfield halfip" name="zip" value="${loggedUser.zip}" disabled>
	
	                            <p class="ltags">Telephone Number</p> <span id="telSpan"></span>
	                            <input type="text" class="ipfield halfip" name="tel" value="${loggedUser.telNo}" disabled>
	                        </div>
	                    </div>
	
	                    <!-- <br><br><li><div class="divider"></div></li><br> -->  
	
	                    <div class="wrap">
	                        <div class="f">
	                            <p class="ltags">Birthday</p>
	                            <input type="date" class = "ipfield threeip" name="bday" value="${loggedUser.birthday}" disabled>
	
	                            <p class="ltags">Civil Status</p>
	                            <input type="text" class="ipfield threeip" name="civil" value="${loggedUser.civil}" disabled>
	                        </div>
	
	                        <div class="f">
	                            <p class="ltags">Age</p> 
	                            <input type="text" class="ipfield threeip" name="age" value="${loggedUser.age}" disabled>
	
	                            <p class="ltags">Nationality</p> <span id="nationSpan"></span>
	                            <input type="text" class="ipfield threeip" name="citizen" value="${loggedUser.citizen}" disabled>
	                        </div>
	
	                        <div class="f">
	                            <p class="ltags">Gender</p> 
	                            <input type="text" class="ipfield threeip" name="gender" value="${loggedUser.gender}" disabled>

	                        </div>
	                     </div>
	                     
                     </form> <!-- PIForm -->
                     
                     <br><br> 
                     <a class="waves-effect waves-light btn" id="PIcancel">CANCEL</a>
                     <a class="waves-effect waves-light btn" id="PIsave">SAVE</a>  
                       
                                                
                  </div> <!-- end of div tag Personal Information -->
                </li>
                
                <!-- Family Background -->
                <li>
                    <div class="collapsible-header"><i class="large material-icons">group</i>FAMILY BACKGROUND</div>
                    
                    <div class="collapsible-body FB">
                        <a class="waves-effect waves-light btn" id="FBedit">EDIT</a>
                        <a class="waves-effect waves-light btn" id="addSibling">ADD SIBLING</a>

                        <form action="updateFamily" method="POST" id="FBform">
	                        <div class="wrap">
	                        	
	                        	<c:choose>
			                        <c:when test="${empty relativeList}"> <!-- Conditional to check if relative list is empty or not. -->
			                            <div class="f" id="FBname">
			                                <p class="ltags">Father's Name</p>
			                                <input type="text" class="ipfield threeip" name="dadName-0" value="Papa bear" disabled>
			                                
			                                <p class="ltags">Mother's Name</p>
			                                <input type="text" class="ipfield threeip" name="momName-0" value="Mama bear" disabled>
		
			                                <p class="ltags">Sibling/s' Name</p>
			                                <input type="text" class="sname ipfield threeip" name="sibName-0-0" value="Ate bear" disabled>
			                                
			                                <!--<p id="FBname"></p>-->
			                            </div>
			                            
			                            <div class="f" id="FBocc">
			                                <p class="ltags">Occupation</p>
			                                <input type="text" class="ipfield threeip" name="dadWork-0" value="Bear" disabled>
			                                
			                                <p class="ltags">Occupation</p>
			                                <input type="text" class="ipfield threeip" name="momWork-0" value="Bear" disabled>
			                                
			                                <p class="ltags">Occupation</p>
			                                <input type="text" class="swork ipfield threeip" name="sibWork-0-0" value="Bear" disabled>
		
			                                <!--<p id="FBocc"></p>-->
			                            </div>
			                            
			                            <div class="f" id="FBbday">
			                                <p class="ltags">Birthday</p>
		
			                                <input type="date" class="ipfield threeip" name="dadBday-0" value="2000-12-01" disabled>
			                                
			                                <p class="ltags">Birthday</p>
			                                <input type="date" class="ipfield threeip" name="momBday-0" value="2000-12-01" disabled>
		
			                                <p class="ltags">Birthday</p>
			                                <input type="date" class="sbday ipfield threeip" name="sibBday-0-0" value="2000-12-01" disabled>
			                                <!-- <p id="FBbday"></p>-->
			                            </div>
			                   		</c:when>
			                   		
			                   		<c:otherwise> <!-- If not empty, do this -->
			                            <div class="f" id="FBname">
			                                <p class="ltags">Father's Name</p>
			                                <input type="text" class="ipfield threeip" name="dadName-${father.relativeId}" value="${father.name}" disabled>
			                                
			                                <p class="ltags">Mother's Name</p>
			                                <input type="text" class="ipfield threeip" name="momName-${mother.relativeId}" value="${mother.name}" disabled>
		
			                                <p class="ltags">Sibling/s' Name</p>
			                                <c:forEach items="${siblingList}" begin="0" end="${siblingSize}" var="s" varStatus="loop">
			                                		<input type="text" class="sname ipfield threeip" name="sibName-${loop.index}-${s.relativeId}" value="${s.name}" disabled>
			                                </c:forEach>
			                                
			                                <!--<p id="FBname"></p>-->
			                            </div>
			                            
			                            <div class="f" id="FBocc">
			                                <p class="ltags">Occupation</p>
			                                <input type="text" class="ipfield threeip" name="dadWork-${father.relativeId}" value="${father.occupation}" disabled>
			                                
			                                <p class="ltags">Occupation</p>
			                                <input type="text" class="ipfield threeip" name="momWork-${mother.relativeId}" value="${mother.occupation}" disabled>
			                                
			                                <p class="ltags">Occupation</p>
			                                <c:forEach items="${siblingList}" begin="0" end="${siblingSize}" var="s" varStatus="loop">
			                                		<input type="text" class="swork ipfield threeip" name="sibWork-${loop.index}-${s.relativeId}" value="${s.occupation}" disabled>
			                                </c:forEach>
		
			                                <!--<p id="FBocc"></p>-->
			                            </div>
			                            
			                            <div class="f" id="FBbday">
			                                <p class="ltags">Birthday</p>
		
			                                <input type="date" class="ipfield threeip" name="dadBday-${father.relativeId}" value="${father.birthday}" disabled>
			                                
			                                <p class="ltags">Birthday</p>
			                                <input type="date" class="ipfield threeip" name="momBday-${mother.relativeId}" value="${mother.birthday}" disabled>
		
			                                <p class="ltags">Birthday</p>
			                                <c:forEach items="${siblingList}" begin="0" end="${siblingSize}" var="s" varStatus="loop">
			                                		<input type="date" class="sbday ipfield threeip" name="sibBday-${loop.index}-${s.relativeId}" value="${s.birthday}" disabled>
			                                </c:forEach>
			                                <!-- <p id="FBbday"></p>-->
			                            </div>
			                   		</c:otherwise>
		                   		
		                   		</c:choose>
		                   		
	                        </div>
                        </form> <!-- end FBform -->
                    
                        <br><br>
                        
                        <a class="waves-effect waves-light btn" id="FBcancel">CANCEL</a>
                        <a class="waves-effect waves-light btn" id="FBsave">SAVE</a>
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
                        <a class="waves-effect waves-light btn addMoreInvolvments" id="imInternal">Add involvement</a>
                        <form action = "addIntInv" method = "POST" id = "intInv">
	                        <div class="wrap">
	                            <div class="f" id="INyear">
	                                <p class="ltags">Year</p>
	                                <input type="text" class="ipfield threeipyear" name="inYear-0-0" value="2017" disabled>
	                            </div>
	                            
	                            <div class="f" id="INevent">
	                                <p class="ltags">Event/Organization</p>
	                                <input type="text" class="ipfield threeip" name="inOrg-0-0" value="Something" disabled>                         
	                            </div>
	                            
	                            <div class="f" id="INposition">
	                                <p class="ltags">Position</p>
	                                <input type="text" class="ipfield threeip" name="inPos-0-0" value="Someone" disabled>
	                            </div>
	                        </div>
	                        
	                        <br><br>
	                        
                            <a class="waves-effect waves-light btn" id="IIcancel">CANCEL</a>
	                        <a class="waves-effect waves-light btn" id="IIsave">SAVE</a>
                        </form> <!-- end intInv -->
                    </div> <!-- end of div tag Internal Involvement --> 
                
                
                </li>
                
                <li>
                    <div class="collapsible-header"><i class="large material-icons">nature_people</i>EXTERNAL INVOLVEMENT</div>
                    
                    <div class="collapsible-body">
                        <a class="waves-effect waves-light btn" id="EIedit">EDIT</a>
                        <a class="waves-effect waves-light btn addMoreInvolvments" id="imExternal">Add involvement</a>
                        <form action = "addExtInv" method = "POST" id = "extInv">
	                        <div class="wrap">
	                            <div class="f" id="EXyear">
	                                <p class="ltags">Year</p>
	                                <input type="text" class="ipfield threeipyear" name="exYear-0-0" value="2017" disabled>
	                            </div>
	                            
	                            <div class="f" id="EXevent">
	                                <p class="ltags">Event/Organization</p>
	                                <input type="text" class="ipfield threeip" name="exOrg-0-0" value="Something" disabled>                         
	                            </div>
	                            
	                            <div class="f" id="EXposition">
	                                <p class="ltags">Position</p>
	                                <input type="text" class="ipfield threeip" name="exPos-0-0" value="Someone" disabled>
	                            </div>
	                        </div>
	                        
	                        <br><br>
	                        
                            <a class="waves-effect waves-light btn" id="EIcancel">CANCEL</a>
	                        <a class="waves-effect waves-light btn" id="EIsave">SAVE</a>
	                        
                        </form> <!-- end extInv -->
                    </div> <!-- end of div tag External Involvement --> 
                </li>
           
              </ul>
        </div>

        <p id="dig8">ID Number should contain 8 digits only</p>
        <p id="dig11">Cellphone Number should contain 11 digits only</p>
        <p id="digonly">Telephone Number should contain digits only</p>
        <p id="invcode">Please use the valid codes per level</p>
        
    <script type="text/javascript" src="script/ViewProfile.js"></script>
    </body>
</html>