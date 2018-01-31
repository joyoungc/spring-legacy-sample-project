<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sp" uri="http://www.springframework.org/tags"%>
<html lang="ko">
<head>
<jsp:include page="/WEB-INF/jsp/common/head.jsp" />
<title>로그인 화면</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-4 col-md-offset-4">
				<div class="panel panel-default">
					<div class="panel-heading text-center">
						<h3 class="panel-title"><sp:message code="login" /></h3>
					</div>
					<div class="panel-body">
						<form role="form" action="${contextPath}/login" method="POST">
							<c:if test="${param.error != null}">
								<div class="alert alert-danger">
									<p>Invalid username and password.</p>
								</div>
							</c:if>
							<c:if test="${param.logout != null}">
								<div class="alert alert-success">
									<p>You have been logged out successfully.</p>
								</div>
							</c:if>
							<div class="form-group">
								<input class="form-control" placeholder="ID" name="userId"
									autofocus>
							</div>
							<div class="form-group">
								<input class="form-control" placeholder="Password"
									name="password" type="password">
							</div>
							<input class="btn btn-primary btn-lg btn-block" name="submit"
								type="submit" value="Login" />
						</form>
			  		</div>
				</div> <!-- /.panel -->
			</div>
		</div>
	</div>
</body>
</html>