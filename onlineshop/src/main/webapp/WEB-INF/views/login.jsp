<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<spring:url var="css" value="/resources/css" />
<spring:url var="js" value="/resources/js" />
<spring:url var="images" value="/resources/images" />
<c:set var="contextRoot" value="${pageContext.request.contextPath}" />


<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>OnlineMedicalShop-${title}</title>


<script type="text/javascript">
	window.menu = '${title}';
	window.contextRoot = '${contextRoot}';
</script>


<%-- 
<!-- Bootstrap core CSS -->
<link href="${css}/bootstrap.min.css" rel="stylesheet">
<!-- Bootstrap YETI CSS -->
<link href="${css}/bootstrap-yeti.css" rel="stylesheet">
<!-- Data Table Bootstrap CSS -->
<link href="${css}/dataTables.bootstrap4.css" rel="stylesheet">
<!-- Custom styles for this template -->
<link href="${css}/myapp.css" rel="stylesheet">
 --%>

<!-- Bootstrap Core CSS -->
<link href="${css}/bootstrap.min.css" rel="stylesheet">
<!-- Bootstrap Readable Theme -->
<link href="${css}/bootstrap-readable-theme.css" rel="stylesheet">
<!-- Custom CSS -->
<link href="${css}/myapp.css" rel="stylesheet">


</head>

<body>

	<div class="wrapper">

		<!-- Navigation -->
	    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	        <div class="container">
	            <!-- Brand and toggle get grouped for better mobile display -->
	            <div class="navbar-header">
	                <a class="navbar-brand" href="${contextRoot}/home">Online Shopping</a>
	            </div>
			</div>
		</nav>	


		<!-- Page Content -->

		<div class="content">

			<div class="container">
			
			<!-- This will be displayed only when credentails are wrong -->
   	<c:if test="${not empty message}">
		<div class="row">
			<div class="col-xs-12 col-md-offset-2 col-md-8">
				<div class="alert alert-danger fade in">${message}</div>
			</div>
		</div>
	</c:if>
			
			<!-- This will be displayed only when user logged out -->
   	<c:if test="${not empty logout}">
		<div class="row">
			<div class="col-xs-12 col-md-offset-2 col-md-8">
				<div class="alert alert-success">${logout}</div>
			</div>
		</div>
	</c:if>
			
			
				<div class="row">

					<div class="col-md-offset-3 col-md-6">

						<div class="panel panel-primary">

							<div class="panel-heading">
								<h4>Login</h4>
							</div>

							<div class="panel-body">
								<form action="${contextRoot}/login" method="POST"
									class="form-horizontal" id="loginForm">
									<div class="form-group">
										<label for="username" class="col-md-4 control-label">Email:
										</label>
										<div class="col-md-8">
											<input type="text" name="username" id="username"
												class="form-control" />
										</div>
									</div>
									<div class="form-group">
										<label for="password" class="col-md-4 control-label">Password:
										</label>
										<div class="col-md-8">
											<input type="password" name="password" id="password"
												class="form-control" />
										</div>
									</div>
									<div class="form-group">
										<div class="col-md-offset-4 col-md-8">
											<input type="hidden" name="${_csrf.parameterName}"
												value="${_csrf.token}" /> <input type="submit"
												value="Login" class="btn btn-primary" />
										</div>
									</div>
								</form>

							</div>
							<div class="panel-footer">
								<div class="text-right">
									New User - <a href="${contextRoot}/register">Register Here</a>
								</div>
							</div>

						</div>

					</div>

				</div>
			</div>

		</div>

		<!-- Footer -->
		<%@ include file="./shared/footer.jsp"%>

<%-- 
		<!-- Bootstrap core JavaScript -->
		<script src="${js}/jquery.min.js"></script>
		<!-- jQuery Validation -->
		<script src="${js}/jquery.validate.min.js"></script>
		<script src="${js}/bootstrap.bundle.min.js"></script>
		<!-- Data Table Plugin javascript -->
		<script src="${js}/jquery.dataTables.js"></script>
		<!-- Data Table Bootstrap javascript -->
		<script src="${js}/dataTables.bootstrap4.js"></script>
		<!-- Bootbox Javascript -->
		<script src="${js}/bootbox.min.js"></script>
		<!-- jQuery Validation -->
		<script src="${js}/jquery.validate.min.js"></script>
		<!-- Self coded javascript -->
		<script src="${js}/myapp.js"></script>
 --%>
 
 		<!-- jQuery -->
		<script src="${js}/jquery.js"></script>
		<script src="${js}/jquery.validate.js"></script>
		<!-- Bootstrap Core JavaScript -->
		<script src="${js}/bootstrap.min.js"></script>		
		<!-- Self coded javascript -->
		<script src="${js}/myapp.js"></script>
		
		
	</div>
</body>

</html>