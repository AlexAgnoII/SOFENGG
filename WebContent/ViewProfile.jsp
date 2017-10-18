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
        
        <div id="navbar">
            <a class="dropdown-button btn" href='#' data-activates="menu" id="tbox">
            <div id="wrapper">
                <i class="material-icons" id="arrow">near_me</i>
                <p id="title">DLSU STUDENT INFORMATION SYSTEM</p>
            </div>   
            </a>
            
            <!-- please update correct hrefs hehe -->
            <ul id="menu" class='dropdown-content'>
                <li><a href="#!" id="profileb">profile</a></li>
                <li><a href="view2edit" id="editprofb"> edit profile</a></li>
                <li><a href="#!" id="settingsb">settings</a></li>
                <li><a href="logout" id="logoutb">logout</a></li>
            </ul>
        </div>
        
        <div id="profilefeed">
            <ul class="collapsible" data-collapsible="accordion">
                <li>
                  <div class="collapsible-header"><i class="material-icons">filter_drama</i>First</div>
                  <div class="collapsible-body active">
                      <p id="stname" >${loggedUser.firstName} ${loggedUser.middleName} ${loggedUser.lastName}</p>
                      <p id="stidnum" class="hfeedp">${loggedUser.studentId}</p>
                      <p id="stcourse" class="hfeedp">${loggedUser.course}</p>
                      <p id="stcollege" class="hfeedp">${loggedUser.college}</p>
                  </div>
                </li>
                
                <li>
                  <div class="collapsible-header"><i class="material-icons">place</i>Personal Information</div>
                  <div class="collapsible-body">
                      <p id="staddress" class="binfo">${loggedUser.address}</p>
                      <p id="stcity" class="binfo">${loggedUser.city}</p>
                      <p id="stprov" class="binfo">${loggedUser.province}</p>
                      <p id="stcountry" class="binfo">${loggedUser.country}</p>
                      <p id="stzip" class="binfo">${loggedUser.zip}</p>
                      <p id="stemail" class="binfo">${loggedUser.email}</p>
                      <p id="stmob" class="binfo">${loggedUser.celNo}</p>
                      <p id="sttel" class="binfo">${loggedUser.telNo}</p>
                      <p id="stbday" class="binfo">${loggedUser.birthday}</p>
                      <p id="stage" class="binfo">${loggedUser.age}</p>
                      <p id="stciv" class="binfo">${loggedUser.civil}</p>
                      <p id="stcitzen" class="binfo">${loggedUser.citizen}</p>
                      <p id="gender" class="binfo">${loggedUser.gender}</p>
                  </div>
                </li>
                
                <li>
                  <div class="collapsible-header"><i class="material-icons">whatshot</i>Family Background</div>
                  <div class="collapsible-body">
                       <p class="heads">Family Background</p>
                      <br>
                      <p class="subheads">Father</p>
                      <p id="stdad" class="binfo">Daddy Kazama</p>
                      <p id="stdadprof" class="binfo">Fighter</p>
                      <p id="stdadbday" class="binfo">01/01/1990</p>
                      <br>
                      <p class="subheads">Mother</p>
                      <p id="stmom" class="binfo">Mommy Kazama</p>
                      <p id="stmomprof" class="binfo">Fighter</p>
                      <p id="stmombday" class="binfo">01/01/1990</p>
                      <br>
                      <p class="subheads">Brother/s</p>
                      <p id="stbro" class="binfo">Kuya Kazama</p>
                      <p id="stbroprof" class="binfo">Fighter</p>
                      <p id="stbrobday" class="binfo">01/01/1990</p>
                      <br>
                      <p class="subheads">Sister/s</p>
                      <p id="stsis" class="binfo">Ate Kazama</p>
                      <p id="stsisprof" class="binfo">Fighter</p>
                      <p id="stsisbday" class="binfo">01/01/1990</p>

                  </div>
                </li>
              </ul>
        </div>
        
        <script>
             $(document).ready(function(){
                $('.collapsible').collapsible();
              });
            
             $('.dropdown-button').dropdown({
                  hover: true, // Activate on hover
                }
              );
        </script>

    </body>
</html>