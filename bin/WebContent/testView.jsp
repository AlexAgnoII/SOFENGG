<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Testing View profile of logged user.</h1>
	Student ID: ${loggedUser.studentId} <br>
	Name: <span>${loggedUser.firstName} ${loggedUser.middleName} ${loggedUser.lastName}</span><br>
	Birthday: ${loggedUser.birthday}<br>
	Year enrolled: ${loggedUser.yEnrolled}<br>
	telno: ${loggedUser.telNo}<br>
	CelNo: ${loggedUser.celNo}<br>
	email: ${loggedUser.email}<br>
	address: ${loggedUser.address}<br>
	course: ${loggedUser.course}<br>
	civil: ${loggedUser.civil}<br>
	citizen: ${loggedUser.citizen}<br>
	gender: ${loggedUser.gender}<br>
	<a href="UserHomePage.jsp">Back to userhomepage</a><br>
	</body>
</html>