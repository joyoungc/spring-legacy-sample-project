<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
	<jsp:include page="/WEB-INF/jsp/common/head.jsp" />
	<title>Welcome page</title>
</head>
<body>
	Dear <strong>${user}</strong>, Welcome to Home Page.
	<a href="<c:url value="/logout" />">Logout</a>

	<br/>
	<br/>
	<div>
		<label>View all information| This part is visible to Everyone</label>
	</div>

	<br/>
	<div>
		<sec:authorize access="hasRole('ROLE_ADMIN')">
			<label><a href="#">Edit this page</a> | This part is visible only to ADMIN</label>
		</sec:authorize>
	</div>

	<br/>
	<div>
		<sec:authorize access="hasRole('ROLE_ADMIN') and hasRole('ROLE_USER')">
			<label><a href="#">Start backup</a> | This part is visible only to one who is both ADMIN & USER</label>
		</sec:authorize>
	</div>
</html>