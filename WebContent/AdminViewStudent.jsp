<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="materialize/css/materialize.min.css">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        
        <script type="text/javascript" src="jquery-3.2.1.min.js"></script>
        <script type="text/javascript" src="materialize/js/materialize.min.js"></script>
		<script type="text/javascript" src="script/AdminHomePage.js"></script>
        
        <!-- INSERT CORRECT STYLESHEET BELOW -->
        <link rel = "stylesheet" type="text/css" href="css/adminhomepage.css">
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
        
	    <div id="profilefeed">
            <ul class="collapsible" data-collapsible="expandable">
                
                <!-- Profile Overview -->
                <li>
                  <div class="collapsible-header active"><i class="large material-icons">star</i>PROFILE OVERVIEW</div>
                  <div class="collapsible-body active">
                   
                      <p id="stname">${loggedUser.firstName} ${loggedUser.middleName} ${loggedUser.lastName}</p>
                      <p class="profow">${loggedUser.studentId}</p>
                      <p class="profow">${loggedUser.email}</p>
                      <p class="profow">${loggedUser.course}</p>
                      <p class="profow">${loggedUser.college}</p>
                      
                      <br>
                  </div>
                </li>
                
                <!-- Personal Information -->
                <li>
                  <div class="collapsible-header"><i class="large material-icons">account_circle</i>PERSONAL INFORMATION</div>
                    
                  <div class="collapsible-body">
                      
                     <form id="PIform" action="updatePersonal" method="POST">
	                     <p class="ltags">Address</p> 
	                     <input id="address" type="text" class="ipfield fullip" name="address" value="${loggedUser.address}" disabled placeholder="Ex: #10 Scout Fire st, Barangay Always Ready">
	
	                     <div class="wrap">
	                        <div class="f">
	                            <p class="ltags">City</p> <span id="citySpan" class="iSpan"></span><p> </p>
	                            <input id="city" type="text" class="ipfield halfip" name="city" value="${loggedUser.city}" disabled placeholder="Ex: Quezon City, Makati city, etc.">
	
	                            <p class="ltags">Country</p> <span id="countrySpan" class="iSpan"></span><p> </p>
	                            <input id="country" type="text" name="country" class="ipfield halfip" value="${loggedUser.country}" disabled placeholder="Ex: Philippines, USA, Singapore, etc.">
	
	                            <p class="ltags">Mobile Number</p> <span id="cellSpan" class="iSpan"></span><p> </p>
	                            <input id="cell" type="text" class="ipfield halfip" name="cell" value="${loggedUser.celNo}" disabled placeHolder="Ex: 09171234567 (11-digits)">
	                        </div>
	
	                        <div class="f">
	                            <p class="ltags">Province</p> <span id="provSpan" class="iSpan"></span><p> </p>
	                            <input id="prov" type="text" class="ipfield halfip" name="prov" value="${loggedUser.province}" disabled placeholder="Ex: Batangas, Manhattan, etc.">
	
	                            <p class="ltags">Zip Code</p> <span id="zipSpan" class="iSpan"></span><p> </p>
	                            <input id="zip" type="text" class="ipfield halfip" name="zip" value="${loggedUser.zip}" disabled placeholder="Ex: 1101, 2231, 1000, etc">
	
	                            <p class="ltags">Telephone Number</p> <span id="telSpan" class="iSpan"></span><p> </p>
	                            <input id="tel" type="text" class="ipfield halfip" name="tel" value="${loggedUser.telNo}" disabled placeholder="Ex: 108999, etc">
	                        </div>
	                    </div>
	
	                    <!-- <br><br><li><div class="divider"></div></li><br> -->  
	
	                    <div class="wrap">
	                        <div class="f">
	                            <p class="ltags">Birthday</p>
	                            <input type="date" class = "ipfield threeip" name="bday" value="${loggedUser.birthday}" disabled>
	
	                            <p class="ltags">Civil Status</p> <span id="statusSpan" class="iSpan"></span><p> </p>
	                            <input id="status" type="text" class="ipfield threeip" name="civil" value="${loggedUser.civil}" disabled placeholder="Single, Married">
	                        </div>
	
	                        <div class="f">
	                            <p class="ltags">Age</p> 
	                            <input type="text" id="specialAge" name="age" value="${loggedUser.age}" disabled>
	
	                            <p class="ltags">Nationality</p> <span id="nationSpan" class="iSpan"></span><p> </p>
	                            <input id="citizen" type="text" class="ipfield threeip" name="citizen" value="${loggedUser.citizen}" disabled placeholder="Ex: Filipino, American, Chinese, etc.">
	                        </div>
	
	                        <div class="f">
	                            <p class="ltags">Sex</p> <span id="genderSpan" class="iSpan"></span><p> </p>
	                            <input id="gender" type="text" class="ipfield threeip" name="gender" value="${loggedUser.gender}" disabled placeholder="Male or Female">

	                        </div>
	                     </div>
	                     
                     </form> <!-- PIForm -->
               
                  </div> <!-- end of div tag Personal Information -->
                </li>
                
                <!-- Family Background -->
                <li>
                    <div class="collapsible-header"><i class="large material-icons">group</i>FAMILY BACKGROUND</div>
                    
                    <div class="collapsible-body FB">
                      
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
                     
                    </div> <!-- end of div tag Family Background -->    
                </li>
                
                <li>
                    <div class="collapsible-header"><i class="large material-icons">school</i>ACADEMIC BACKGROUND</div>
                    
                    <div class="collapsible-body">
                       
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
	                        <c:choose>
	                        	<c:when test="${empty internalList}"> <!-- Internal not existing, do this -->
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
		                        </c:when>
		                        
		                        <c:otherwise> <!-- Internal exiisting, do this -->
			                        <div class="wrap">
			                            <div class="f" id="INyear">
			                                <p class="ltags">Year</p>
			                                <c:forEach items="${internalList}" begin="0" end="${internalSize}" var="i" varStatus="loop">
			                               		<input type="text" class="ipfield threeipyear" name="inYear-${loop.index}-${i.id}" value="${i.acadYear}" disabled>
			                                </c:forEach>
			                            </div>
			                            
			                            <div class="f" id="INevent">
			                                <p class="ltags">Event/Organization</p>
			                                <c:forEach items="${internalList}" begin="0" end="${internalSize}" var="i" varStatus="loop">
			                                	<input type="text" class="ipfield threeip" name="inOrg-${loop.index}-${i.id}" value="${i.iName}" disabled>       
			                                </c:forEach>                  
			                            </div>
			                            
			                            <div class="f" id="INposition">
			                                <p class="ltags">Position</p>
			                                <c:forEach items="${internalList}" begin="0" end="${internalSize}" var="i" varStatus="loop">
			                               		<input type="text" class="ipfield threeip" name="inPos-${loop.index}-${i.id}" value="${i.position}" disabled>
			                               	</c:forEach>  
			                            </div>
			                        </div>
		                        </c:otherwise>
		                    </c:choose>
	      
                        </form> <!-- end intInv -->
                    </div> <!-- end of div tag Internal Involvement --> 
                
                
                </li>
                
                <li>
                    <div class="collapsible-header"><i class="large material-icons">nature_people</i>EXTERNAL INVOLVEMENT</div>
                    
                    <div class="collapsible-body">
                       
                        <form action = "addExtInv" method = "POST" id = "extInv">
                        <c:choose>
                        	<c:when test="${empty externalList}">
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
	                        </c:when>
	                        
	                        <c:otherwise>
		                        <div class="wrap">
		                            <div class="f" id="EXyear">
		                                <p class="ltags">Year</p>
		                                <c:forEach items="${externalList}" begin="0" end="${externalSize}" var="i" varStatus="loop">
		                                	<input type="text" class="ipfield threeipyear" name="exYear-${loop.index}-${i.id}" value="${i.acadYear}" disabled>
		                            	</c:forEach>
		                            </div>
		                            
		                            <div class="f" id="EXevent">
		                                <p class="ltags">Event/Organization</p>
		                                <c:forEach items="${externalList}" begin="0" end="${externalSize}" var="i" varStatus="loop">
		                               		<input type="text" class="ipfield threeip" name="exOrg-${loop.index}-${i.id}" value="${i.iName}" disabled>                         
		                            	</c:forEach>
		                            </div>
		                            
		                            <div class="f" id="EXposition">
		                                <p class="ltags">Position</p>
		                                <c:forEach items="${externalList}" begin="0" end="${externalSize}" var="i" varStatus="loop">
		                                	<input type="text" class="ipfield threeip" name="exPos-${loop.index}-${i.id}" value="${i.position}" disabled>
		                            	</c:forEach>
		                            </div>
		                        </div>
	                        </c:otherwise>
	                    </c:choose>
	                  
                        </form> <!-- end extInv -->
                    </div> <!-- end of div tag External Involvement --> 
                </li>
           
              </ul>
        </div>
  		
        <div id="newannounce" class="modal">
            <div id="annhead">[NEW ANNOUNCEMENT]</div>
            <div id="annwrap">
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
    
     <!--<div id="searchdiv">
            <form action="search" method="GET" id = "searchForm">
                <input type="text" id="searchbar" name="searchbar" placeholder="search">
                <a><i class="material-icons" id="magglass">search</i></a>
            </form>
        </div> -->
        
     <script>
        $(document).ready(function(){
            $('.modal').modal();
        });
        
        
    </script>    
    
        
    </body>
</html>