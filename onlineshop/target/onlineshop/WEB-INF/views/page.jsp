<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<spring:url var="css" value="/resources/css" />
<spring:url var="js" value="/resources/js" />
<spring:url var="images" value="/resources/images" />
<c:set var="contextRoot" value="${pageContext.request.contextPath}" />


<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="author" content="Sunny Pukkalli">


<meta name="_csrf" content="${_csrf.token}">
<meta name="_csrf_header" content="${_csrf.headerName}">


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
<!-- Bootstrap DataTables -->
<link href="${css}/dataTables.bootstrap.css" rel="stylesheet">
<!-- Custom CSS -->
<link href="${css}/myapp.css" rel="stylesheet">



</head>

<body>

<div class="se-pre-con"></div>

	<div class="wrapper">
		<!-- Navigation -->
		<%@ include file="./shared/navbar.jsp"%>


		<!-- Page Content -->

		<div class="content">
			<!-- Loading Home Page -->
			<c:if test="${userClickHome == true}">
				<%@ include file="home.jsp"%>
			</c:if>

			<!-- When user clicks About -->
			<c:if test="${userClickAbout == true}">
				<%@ include file="about.jsp"%>
			</c:if>

			<!-- When user clicks Contact  -->
			<c:if test="${userClickContact == true}">
				<%@ include file="contact.jsp"%>
			</c:if>
			
			<!-- When user clicks Sidebar category or on navbar  -->
			<c:if test="${userClickAllProducts == true or userClickCategoryProducts == true}">
				<%@ include file="listProducts.jsp"%>
			</c:if>
			
			<!-- When user clicks Single Product   -->
			<c:if test="${userClickShowProduct == true}">
				<%@ include file="singleProduct.jsp"%>
			</c:if>
			
			<!-- When user clicks Manage Product   -->
			<c:if test="${userClickManageProduct == true}">
				<%@ include file="manageProducts.jsp"%>
			</c:if>

			<!-- When user clicks Cart   -->
			<c:if test="${userClickShowCart == true}">
				<%@ include file="cart.jsp"%>
			</c:if>
		</div>

		<!-- Footer -->
		<%@ include file="./shared/footer.jsp"%>


<%-- 
 		<!-- Bootstrap core JavaScript -->
		<script src="${js}/jquery.min.js"></script>			
		<!-- jQuery Validation -->
		<script src="${js}/jquery.validate.min.js"></script>			
		<script src="${js}/bootstrap.min.js"></script>
 		<script src="${js}/bootstrap.bundle.min.js"></script>
		<!-- Data Table Plugin javascript -->
		<script src="${js}/jquery.dataTables.js"></script>		
		<!-- Data Table Bootstrap javascript -->
		<script src="${js}/dataTables.bootstrap4.js"></script>		
		<!-- Bootbox Javascript -->
		<script src="${js}/bootbox.min.js"></script>		
		<!-- Self coded javascript -->
		<script src="${js}/myapp.js"></script>

 --%>

		<!-- jQuery -->
		<script src="${js}/jquery.js"></script>
		<script src="${js}/jquery.validate.js"></script>
		<!-- Bootstrap Core JavaScript -->
		<script src="${js}/bootstrap.min.js"></script>		
		<!-- DataTable Plugin -->
		<script src="${js}/jquery.dataTables.js"></script>		
		<!-- DataTable Bootstrap Script -->
		<script src="${js}/dataTables.bootstrap.js"></script>		
		<!-- DataTable Bootstrap Script -->
		<script src="${js}/bootbox.min.js"></script>		
		<!-- Self coded javascript -->
		<script src="${js}/myapp.js"></script>

	</div>
</body>

</html>