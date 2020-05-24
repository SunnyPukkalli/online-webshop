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
<meta name="author" content="Sunny Pukkalli">

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
<link href="${css}/dataTables.bootstrap.css" rel="stylesheet">
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
	                <a class="navbar-brand" href="${contextRoot}/home">Home</a>
	            </div>
			</div>
		</nav>

		<div class="content">
			
			<div class="container">
				
				<div class="row">
					
					<div class="col-xs-12">
						
						<div class="jumbotron">
							<h1>${errorTitle}</h1>
							<hr/>
							
							<blockquote style="word-wrap:break-word" >
								${errorDescription}
							</blockquote>
						</div>
						
					</div>
				
				</div>
			
			
			</div>
		</div>

		<!-- Footer -->
		<%@ include file="./shared/footer.jsp"%>
		
	</div>
</body>

</html>