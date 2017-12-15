<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<style>
			body{
				font-family:Calibri;
			}
			.sectionHeader{
				text-align:left;
				font-size:20px;
			}
			table{
				border-spacing:10px;
			}
			.typing{
				font-weight:bold;
			}
			.infoInput{
				border-bottom:1px solid #000;
				padding-right:70px;
			}
			.label{
				font-style:italic;
				font-size:12px;
			}
		</style>
	</head>
	
	<body>
		<table>
			<tr>
				<th colspan="3" class="sectionHeader">Nominee's Personal Data</th>
			</tr>
			<tr>
				<td class="infoInput"><p class="typing">${loggedUser.lastName}</p></td> 
				<td class="infoInput"><p class="typing">${loggedUser.firstName}</p></td> 
				<td class="infoInput"><p class="typing">${loggedUser.middleName}</p></td> 
			</tr>
			<tr>
				<td><p class="label">Last Name</p></td> 
				<td><p class="label">First Name</p></td> 
				<td><p class="label">Middle Name</p></td> 
			</tr>
			<tr>
				<td colspan="3" class="infoInput"><p class="typing">${loggedUser.address}; ${loggedUser.city}; ${loggedUser.country}; ${loggedUser.zip}</p></td>
			</tr>
			<tr>
				<td colspan="3"><p class="label">Address</p></td>
			</tr>
			<tr>
				<td class="infoInput"><p class="typing">${loggedUser.telNo}</p></td> 
				<td class="infoInput"><p class="typing">${loggedUser.celNo}</p></td> 
				<td class="infoInput"><p class="typing">${loggedUser.email}</p></td> 
			</tr>
			<tr>
				<td><p class="label">Telephone Number</p></td> 
				<td><p class="label">Cellphone Number</p></td> 
				<td><p class="label">Email Address</p></td> 
			</tr>
			<tr>
				<td class="infoInput"><p class="typing">${loggedUser.birthday}</p></td> 
				<td class="infoInput"><p class="typing">${loggedUser.age}</p></td> 
				<td class="infoInput"><p class="typing">${loggedUser.civil}</p></td> 
			</tr>
			<tr>
				<td><p class="label">Birthdate</p></td> 
				<td><p class="label">Age</p></td> 
				<td><p class="label">Civil Status</p></td> 
			</tr>
			<tr>
				<td class="infoInput"><p class="typing">${loggedUser.citizen}</p></td> 
				<td class="infoInput"><p class="typing">${loggedUser.gender}</p></td> 
			</tr>
			<tr>
				<td><p class="label">Citizenship</p></td> 
				<td colspan="2"><p class="label">Sex</p></td> 
			</tr>
		</table>
		<br>
		<table>
			<tr>
				<th colspan="4" class="sectionHeader">Family Background</th>
			</tr>
			<tr>
				<th><p>    </p></th>
				<th><p>Name</p></th>
				<th><p>Birthdate</p></th>
				<th><p>Occupation</p></th>
			</tr>

					<tr>
						<td><p>Father:</p></td>
						<td class="infoInput"><p class="typing">${father.name}</p></td>
						<td class="infoInput"><p class="typing">${father.birthday}</p></td>
						<td class="infoInput"><p class="typing">${father.occupation}</p></td>
					</tr>
					<tr>
						<td><p>Mother:</p></td>
						<td class="infoInput"><p class="typing">${mother.name}</p></td>
						<td class="infoInput"><p class="typing">${mother.birthday}</p></td>
						<td class="infoInput"><p class="typing">${mother.occupation}</p></td>
					</tr>
					
					<c:forEach items="${siblingList}" begin="0" end="${siblingSize}" var="s" varStatus="loop">
						<tr>
							<td><p>${s.type}</p></td>
							<td class="infoInput"><p class="typing">${s.name}</p></td>
							<td class="infoInput"><p class="typing">${s.birthday}</p></td>
							<td class="infoInput"><p class="typing">${s.occupation}</p></td>
						</tr>
                    </c:forEach>
		</table>
		<br>
		<table>
			<tr>
				<th colspan="4" class="sectionHeader">Nominee's Educational Profile</th>
			</tr>
			<!-- <tr>
				<td><p>Elementary:</p></td>
				<td><p>*insert schoolname*</p></td>
				<td><p>*insert lvl/course*</p></td>
				<td><p>*insert inclusiveyrs*</p></td>
			</tr>
			<tr>
				<td><p>High School:</p></td>
				<td><p>*insert schoolname*</p></td>
				<td><p>*insert lvl/course*</p></td>
				<td><p>*insert inclusiveyrs*</p></td>
			</tr> -->
			<tr>
				<td><p>College:</p></td>
				<td><p class="typing">${loggedUser.college}</p></td>
				<td><p class="typing">${loggedUser.course}</p></td>
				<!-- <td><p>*insert inclusiveyrs*</p></td> -->
			</tr>
			<!-- <tr>
				<td><p>CGPA:</p></td>
				<td><p>*insert grade*</p></td>	
				<td><p>Highest Possible Grade:</p></td>
				<td><p>*insert grade*</p></td>
			</tr> -->
		</table>
		<br>
		<!-- <table>
			<tr>
				<th colspan="3" class="sectionHeader">Nominee's Academic Achievements</th>
			</tr>
			<tr>
				<th><p>Date</p></th>
				<th><p>Rank - Name of Award</p></th>
				<th><p>Level</p></th>
			</tr>
			<tr>
				<td><p>*insert date*</p></td>
				<td><p>*insert award*</p></td>
				<td><p>*insert levelcode*</p></td>
			</tr>
			populate this table with academic awards
		</table> -->
		<br>
		<table>
			<tr>
				<th colspan="3" class="sectionHeader">Nominee's Extra-Curricular Internal Involvements</th>
			</tr>
			
			<tr>
				<th><p>Date</p></th>
				<th><p>Position - Name of Organization</p></th>
				<th><p>Level</p></th>
			</tr>
			
			<c:forEach items="${internalList}" begin="0" end="${internalSize}" var="i" varStatus="loop">
				<tr>
					<td><p>${i.acadYear}</p></td>
					<td><p>${i.iName}</p></td>
					<td><p>${i.position}</p></td>
				</tr>
	        </c:forEach>
			
			<!--populate this table with extra curricular activities-->
		</table>
		<br>
		<table>
			<tr>
				<th colspan="3" class="sectionHeader">Nominee's Extra-Curricular External Involvements</th>
			</tr>
			
			<tr>
				<th><p>Date</p></th>
				<th><p>Position - Name of Organization</p></th>
				<th><p>Level</p></th>
			</tr>
			
			<c:forEach items="${externalList}" begin="0" end="${internalSize}" var="i" varStatus="loop">
				<tr>
					<td><p>${i.acadYear}</p></td>
					<td><p>${i.iName}</p></td>
					<td><p>${i.position}</p></td>
				</tr>
	        </c:forEach>
			
			<!--populate this table with extra curricular activities-->
		</table>
	</body>
</html>