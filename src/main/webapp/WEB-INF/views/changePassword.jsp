<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>

<!DOCTYPE HTML>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

	<!--Google Fonts-->
	<link href='http://fonts.googleapis.com/css@family=Open+Sans_3A400,300,300italic,400italic,600,600italic,700,700italic' rel='stylesheet' type='text/css'>
	<link href="${pageContext.request.contextPath}/resources/css/main.css" rel="stylesheet" type="text/css" />
	<link href="${pageContext.request.contextPath}/resources/css/jquery.fancybox.css" rel="stylesheet" type="text/css" />
	
	<title>Bank of G5!</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
	<link rel="shortcut icon" href="img/favicon.png" />

	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.js"> </script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.flexslider-min.js"> </script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.easing.1.3.js"> </script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/hoverIntent.js"> </script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.sfmenu.js"> </script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/retina.js"> </script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/custom.js"> </script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.fancybox.js"> </script>


	<script type="text/javascript">
	jQuery(window).load(function() {
		
	jQuery('.slider').flexslider();
	 
	// Create the dropdown base
	jQuery("<select />").appendTo(".topmenu");
	
	// Create default option "Go to..."
	jQuery("<option />", {
	 "selected": "selected",
	 "value"   : "",
	 "text"    : "Menu"
	}).appendTo(".topmenu select");
	
	// Populate dropdown with menu items
	jQuery(".topmenu a").each(function() {
	var el = jQuery(this);
	jQuery("<option />", {
	   "value"   : el.attr("href"),
	   "text"    : el.text()
	}).appendTo(".topmenu select");
	});

	jQuery(".topmenu select").change(function() {
	window.location = jQuery(this).find("option:selected").val();
	});
	
	jQuery('.fancybox').fancybox();	
		
	});
	
	</script>
	
	<script type="text/javascript">
	function formSubmit() {
		document.getElementById("logoutForm").submit();
	}
	</script>
</head>

<body>

	<!-- Start of top wrapper -->
	<div id="top_wrapper">
		<!-- Start of content wrapper -->
		<div class="content_wrapper">
		</div>
		<!-- End of content wrapper -->
	
		<!-- Clear Fix -->
		<div class="clear"></div>
	</div><!-- End of top wrapper -->
	
	<!-- Start of header wrapper -->
	<div id="header_wrapper">
	
		<!-- Start of content wrapper -->
		<div class="content_wrapper">
		
			<!-- Start of logo -->
			<div id="logo">
				<a href="#"><img src="${pageContext.request.contextPath}/resources/img/bank_logo.png" width="250" height="80" /></a>
			</div><!-- End of logo -->
	
			<!-- Start of top menu wrapper -->
			<div class="topmenuwrapper">		
				<!-- Start of topmenu -->
				<nav class="topmenu"> 
					<ul class="sf-menu">
						<li><a href="addUser">Add User</a></li>  
						<li><a href="removeUser">Remove User</a></li>
						<li><a href="viewUser">View User</a></li>
						<li><a href="modifyUser">Modify User</a></li>
						<li><a href="viewQueue">View Queue</a></li>
						<li><a href="changePassword">Change password(self)</a></li>
						<li><a href="javascript:formSubmit()">Logout</a></li>
					</ul>
					
				</nav><!-- End of topmenu -->
	
				<!-- Start of header phone -->
				<div class="header_phone">
				Contact: (000) 000-0000
				</div>
				<!-- End of header phone -->
				<!-- Clear Fix -->
				<div class="clear"></div>
			</div><!-- End of top menu wrapper -->
		</div><!-- End of content wrapper -->

		<!-- Clear Fix --><div class="clear"></div>

	</div><!-- End of header wrapper -->

	
	<!-- Start of content wrapper -->
	<div id="contentwrapper">
		<!-- Start of content wrapper -->
		<div class="content_wrapper">
			<div class="contentright" style="width: 100%">
				
				<c:if test="${not empty status}">
				<b>"${status}"</b>
				</c:if>
					  
				<form:form method="post" action="changePassword" modelAttribute="customerDetails">				
				<b>Password:</b><FONT color="red"><form:errors path="password" /></FONT> <input type="password" name="password" id="password" style="color:#999;" /><br/>
				<b>Confirm Password:</b> <input type="password" name="confirmPassword" id="cfrm_pwd" style="color:#999;" /><br/>
				<a><input type="submit" style="margin-right: 5%" name="modifyUser" id="modifyUserButton" value="Modify User"/></a>
				</form:form>

		</div>
	
			<div class="clear"></div>
		</div><!-- End of content wrapper -->

		<!-- Clear Fix --><div class="clear"></div>

	</div><!-- End of content wrapper -->

	<!-- Start of bottom wrapper -->
	<div id="bottom_wrapper">

		<!-- Start of content wrapper -->
		<div class="content_wrapper">
		
			<!-- Start of one fourth first -->
			<div class="one_fourth_first">
			<h4>FTR_TEXT_1</h4>
				<ul>
					<li><a href="#">LINK_1</a></li>
					<li><a href="#">LINK_2</a></li>			
				</ul>
			</div><!-- End of one fourth first -->
	
			<!-- Start of one fourth -->
			<div class="one_fourth">
				<h4>FTR_TEXT_2</h4>	
				<ul>
					<li><a href="#">LINK_1_1</a></li>
					<li><a href="#">LINK_2_1</a></li>			
				</ul>	
			</div><!-- End of one fourth -->
	
			<!-- Start of one fourth -->
			<div class="one_fourth">
			<h4>FTR_TEXT_3</h4>		
				<ul>
					<li><a href="#">LINK_1_2</a></li>
					<li><a href="#">LINK_2_2</a></li>			
				</ul>
			</div><!-- End of one fourth -->

		</div><!-- End of content wrapper -->

		<!-- Clear Fix --><div class="clear"></div>

	</div><!-- End of bottom wrapper -->
</body>
</html>
