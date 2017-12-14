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
			}
		</style>
	</head>
	
	<body>
		<table>
			<tr>
				<th colspan="3" class="sectionHeader">Nominee's Personal Data</th>
			</tr>
			<tr>
				<td><p>${loggedUser.lastName}</p></td> 
				<td><p>${loggedUser.firstName}</p></td> 
				<td><p>${loggedUser.middleName}</p></td> 
			</tr>
			<tr>
				<td><p>Last Name</p></td> 
				<td><p>First Name</p></td> 
				<td><p>Middle Name</p></td> 
			</tr>
			<tr>
				<td colspan="3"><p>${loggedUser.address}; ${loggedUser.city}; ${loggedUser.country}; ${loggedUser.zip}</p></td>
			</tr>
			<tr>
				<td colspan="3"><p>Address</p></td>
			</tr>
			<tr>
				<td><p>${loggedUser.telNo}</p></td> 
				<td><p>${loggedUser.celNo}</p></td> 
				<td><p>${loggedUser.email}</p></td> 
			</tr>
			<tr>
				<td><p>Telephone Number</p></td> 
				<td><p>Cellphone Number</p></td> 
				<td><p>Email Address</p></td> 
			</tr>
			<tr>
				<td><p>${loggedUser.birthday}</p></td> 
				<td><p>${loggedUser.age}</p></td> 
				<td><p>${loggedUser.civil}</p></td> 
			</tr>
			<tr>
				<td><p>Birthdate</p></td> 
				<td><p>Age</p></td> 
				<td><p>Civil Status</p></td> 
			</tr>
			<tr>
				<td><p>${loggedUser.citizen}</p></td> 
				<td><p>${loggedUser.gender}</p></td> 
			</tr>
			<tr>
				<td colspan="2"><p>Citizenship</p></td> 
				<td><p>Gender</p></td> 
			</tr>
		</table>
		<br>
		<table>
			<tr>
				<th colspan="4" class="sectionHeader">Family Background</th>
			</tr>

					<tr>
						<td><p>Father:</p></td>
						<td><p>${father.name}</p></td>
						<td><p>${father.birthday}</p></td>
						<td><p>${father.occupation}</p></td>
					</tr>
					<tr>
						<td><p>Mother:</p></td>
						<td><p>${mother.name}</p></td>
						<td><p>${mother.birthday}</p></td>
						<td><p>${mother.occupation}</p></td>
					</tr>
					
					<c:forEach items="${siblingList}" begin="0" end="${siblingSize}" var="s" varStatus="loop">
						<tr>
							<td><p>${s.type}</p></td>
							<td><p>${s.name}</p></td>
							<td><p>${s.birthday}</p></td>
							<td><p>${s.occupation}</p></td>
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
				<td><p>${loggedUser.college}</p></td>
				<td><p>${loggedUser.course}</p></td>
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