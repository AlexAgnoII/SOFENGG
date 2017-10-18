<html>
<head>
<!--Import materialize.css-->
<link rel="stylesheet" type="text/css"
	href="materialize/css/materialize.min.css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">

<script type="text/javascript" src="jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="materialize/js/materialize.min.js"></script>

<link rel="stylesheet" type="text/css" href="css/editprofile.css">

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
                <li><a href="view" id="profileb">Profile</a></li>
                <li><a href="#!" id="settingsb">Settings</a></li>
                <li><a href="logout" id="logoutb">Logout</a></li>
            </ul>
        </div>
	
	<div id="epfeed">
		<div class="col s12">
			<ul class="tabs">
				<li class="tab col s3"><a href="#perinfo">Personal
						Information</a></li>

				<li class="tab col s3"><a href="#acadinfo">Academic
						Information</a></li>

				<li class="tab col s3"><a href="#inin">Internal
						Involvements</a></li>

				<li class="tab col s3"><a href="#exin">Internal
						Involvements</a></li>
			</ul>

		</div>


		<!-- PERSONAL INFORMATION -->
		<form action="updatePersonal" method="POST">
			<div id="perinfo" class="col s12 forms">

				<p>Last Name</p>
				<input type="text" id="lastName" name="lastName"
					disabled value="${loggedUser.lastName}"><br>

				<div class="dwrap">
					<div class="f">
						<p>First Name</p>
						<input type="text" id="firstName" name="firstName"
							disabled value="${loggedUser.firstName}"><br>

					</div>

					<div class="m">
						<p>Middle Name</p>
						<input type="text" id="middleName" name="middleName"
							disabled value="${loggedUser.middleName}"><br>
					</div>
				</div>

				<ul>
					<li><div class="divider"></div></li>
				</ul>

				<p>Address</p>
				<input type="text" id="address" name="address"
					value="${loggedUser.address}">

				<div class="dwrap">
					<div class="f">
						<p>City</p>
						<input type="text" id="city" name="city"
							value="${loggedUser.city}">
					</div>

					<div class="m">
						<p>Province</p>
						<input type="text" id="prov" name="prov"
							value="${loggedUser.province}">
					</div>
				</div>

				<div class="dwrap">
					<div class="f">
						<p>Country</p>
						<input type="text" id="country" name="country"
							value="${loggedUser.country}">
					</div>

					<div class="m">
						<p>Zip Code</p>
						<input type="text" id="zip" name="zip" value="${loggedUser.zip}">
					</div>
				</div>

				<ul>
					<li><div class="divider"></div></li>
				</ul>

				<p>Email Address</p>
				<input type="text" id="email" name="email"
					value="${loggedUser.email}">

				<div class="dwrap">
					<div class="f">
						<p>Mobile Number</p>
						<input type="text" id="cell" name="cell"
							value="${loggedUser.celNo}">
					</div>

					<div class="m">
						<p>Telephone Number</p>
						<input type="text" id="tel" name="tel" value="${loggedUser.telNo}">
					</div>
				</div>

				<div class="dwrap">
					<div class="f">
						<p>Birthday</p>
						<input type="date" id="bday" name="bday"
							 value="${loggedUser.birthday}">
					</div>

					<div class="m">
						<p>Age</p>
						<input type="text" id="age" name="age"
							disabled value="${loggedUser.age}">
					</div>

					<div class="m">
						<p>Civil Status</p>
						<input type="text" id="civil" name="civil"
							value="${loggedUser.civil}"><br>
					</div>
				</div>

				<div class="dwrap">
					<div class="f">
						<p>Citizenship</p>
						<input type="text" id="citizen" name="citizen"
							value="${loggedUser.citizen}">
					</div>

					<div class="m">
						<p>Gender</p>
						<input type="text" id="gender" name="gender"
							value="${loggedUser.gender}">
					</div>
				</div>

				<ul>
					<li><div class="divider"></div></li>
				</ul>
				<h5>Family Background</h5>

				<p class="family">Father</p>
				<div class="dwrap">
					<div class="f">
						<p>Name</p>
						<input type="text" id="dname" name="dname">
					</div>

					<div class="m">
						<p>Occupation</p>
						<input type="text" id="dprof" name="dprof"><br>
					</div>

					<div class="m">
						<p>Birthday</p>
						<input type="date" id="dbday" name="dbday">
					</div>
				</div>

				<p class="family">Mother</p>
				<div class="dwrap">
					<div class="f">
						<p>Name</p>
						<input type="text" id="mname" name="mname">
					</div>

					<div class="m">
						<p>Occupation</p>
						<input type="text" id="mprof" name="mprof"><br>
					</div>

					<div class="m">
						<p>Birthday</p>
						<input type="date" id="mbday" name="mbday">
					</div>
				</div>
				<!-- DYNAMIC -->
				<p class="family">Sister/s</p>
				<div class="dwrap">
					<div class="f">
						<p>Name</p>
						<input type="text" id="sname" name="sname">
					</div>

					<div class="m">
						<p>Occupation</p>
						<input type="text" id="sprof" name="sprof"><br>
					</div>

					<div class="m">
						<p>Birthday</p>
						<input type="date" id="sbday" name="sbday">
					</div>
				</div>
				
				<!-- DYNAMIC -->
				<p class="family">Brother/s</p>
				<div class="dwrap">
					<div class="f">
						<p>Name</p>
						<input type="text" id="bname" name="bname">
					</div>

					<div class="m">
						<p>Occupation</p>
						<input type="text" id="bprof" name="bprof"><br>
					</div>

					<div class="m">
						<p>Birthday</p>
						<input type="date" id="bbday" name="bbday">
					</div>
				</div>

			</div>
			<button>EDIT</button>
		</form>

		<!-- ACADEMIC INFORMATION -->
		<form action="updateAcadInfo" method="POST">
			<div id="acadinfo" class="col s12 forms">
				<form>
					<p>ID Number</p>
					<input type="text" id="idNum" name="idNum"
						disabled value="${loggedUser.studentId}"><br>
		
					<div class="dwrap">
						<div class="f">
							<p>Course</p>
							<input type="text" id="course" name="course"
								value="${loggedUser.course}">
						</div>
		
						<%-- <div class="m">
							<p>College</p>
							<input type="text" id="college" name="college"
								value="${loggedUser.college}">
						</div> --%>
		
						<div class="m">
							<p>College</p>
							<div class="input-field">
				                <select id="dropDownCollege" name="college" class="ddcollege">
				                    <!--<option value="" disabled selected>Select your College:</option>-->
				                	<option value="1" selected>College of Computer Studies</option>
				                	<option value="2">Ramon V. del Rosario College of Business</option>
				                	<option value="3">Br. Andrew Gonzales College of Education</option>
				                	<option value="4">Gokongwei College of Engineering</option>
				                	<option value="5">College of Liberal Arts</option>
				                	<option value="6">College of Science</option>
				                	<option value="7">School of Economics</option>
				                </select>
		              		</div>
	                	</div>
	
						<div class="m">
							<p>No. of Units Failed</p>
							<input type="text" id="failed" name="failed" value="">
						</div>
					</div>
					<button class="waves-effect waves-light btn modal-trigger" type="submit" id="submit">SAVE</button>	
				</form>
		
					<ul>
						<li><div class="divider"></div></li>
					</ul>
					<h5>Academic Achievements</h5>
					<!-- DYNAMIC -->
					<div class="dwrap">
						<div class="f">
							<p>Year</p>
							<input type="date" id="ayear" name="ayear">
						</div>
		
						<div class="m">
							<p>Award</p>
							<input type="text" id="award" name="award">
						</div>
		
						<div class="m">
							<p>Level</p>
							<input type="text" id="alev" name="alev">
						</div>
					</div>
		
					<p>Given by</p>
					<input type="text" id="agive" name="agive">
		
					<p>Description</p>
					<input type="text" id="adesc" name="adesc">
					<button>EDIT</button>
				</form>
			</div>

		<!-- INTERNAL INVOLVEMENTS -->
		<!-- DYNAMIC -->
		<!-- 
		<div id="inin" class="col s12 forms">
			<div class="dwrap">
				<div class="f">
					<p>Year</p>
					<input type="date" id="inyear" name="inyear">
				</div>

				<div class="m">
					<p>Organization</p>
					<input type="text" id="inorgname" name="inorgname">
				</div>

				<div class="m">
					<p>Position</p>
					<input type="text" id="inorgpos" name="inorgpos">
				</div>
			</div>

			<p>Description</p>
			<input type="text" id="inorgdesc" name="inrogsdesc">

		</div>
		 -->
		
		<!-- DYNAMIC -->
		<!-- EXTERNAL INVOLVEMENTS -->
		<!-- 
		<div id="exin" class="col s12 forms">
			<div class="dwrap">
				<div class="f">
					<p>Year</p>
					<input type="date" id="exyear" name="exyear">
				</div>

				<div class="m">
					<p>Organization</p>
					<input type="text" id="exorgname" name="exorgname">
				</div>

				<div class="m">
					<p>Position</p>
					<input type="text" id="exorgpos" name="exorgpos">
				</div>
			</div>

			<p>Description</p>
			<input type="text" id="exorgdesc" name="exorgdesc">
			
		</div>
		<button type="submit">Edit</button>
		</form>
		-->
	</div>
	
	<script>
	 $('.dropdown-button').dropdown({
         hover: true, // Activate on hover
       }
     );
	</script>
	 

</body>
</html>