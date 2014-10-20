<!--

<!DOCTYPE HTML>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

	<!--Google Fonts-->
	<link href='http://fonts.googleapis.com/css@family=Open+Sans_3A400,300,300italic,400italic,600,600italic,700,700italic' rel='stylesheet' type='text/css'>
	<link href="../resources/css/main.css" rel="stylesheet" type="text/css" />
	<link href="../resources/css/jquery.fancybox.css" rel="stylesheet" type="text/css" />
	
	<title>Bank of G5!</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
	<link rel="shortcut icon" href="img/favicon.png" />

	<script type="text/javascript" src="../resources/js/jquery.js"> </script>
	<script type="text/javascript" src="../resources/js/jquery.flexslider-min.js"> </script>
	<script type="text/javascript" src="../resources/js/jquery.easing.1.3.js"> </script>
	<script type="text/javascript" src="../resources/js/hoverIntent.js"> </script>
	<script type="text/javascript" src="../resources/js/jquery.sfmenu.js"> </script>
	<script type="text/javascript" src="../resources/js/retina.js"> </script>
	<script type="text/javascript" src="../resources/js/custom.js"> </script>
	<script type="text/javascript" src="../resources/js/jquery.fancybox.js"> </script>


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
				<a href="#"><img src="../resources/img/bank_logo.png" width="250" height="80" /></a>
			</div><!-- End of logo -->
	
			<!-- Start of top menu wrapper -->
			<div class="topmenuwrapper">		
				<!-- Start of topmenu -->
				<nav class="topmenu"> 
					<ul class="sf-menu">
						<li><a href="accountSummary.jsp">Accounts</a></li>  
						<li><a href="billPay.jsp">Bill Pay</a></li>
						<li><a href="transferMoney.jsp">Transfer Money</a>
							<ul>
								<li><a href="transferMoney.jsp"> Make A Transfer</a></li>
								<li><a href="transferActivity.jsp"> View Transfer Activity</a></li>
							</ul>
						</li>
						<li><a href="">Debit/Credit Funds</a>
							<ul>
								<li><a href="debitAmount.jsp"> Debit Money</a></li>
								<li><a href="creditAmount.jsp"> Credit Money</a></li>
							</ul>
						</li>
						<li><a href="helpSupport.jsp">Help and Support</a></li>
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
				<form method="post" action="#">
					<i><b>Transfer Money at Bank of G5!</b></i> <br/>					
					<p> Current Account Balance: PRINT BALANCE HERE --</p>
					
					<h6>Transfer Money To:</h6><input type="number" name="transferTo" id="receiver" style="color:#999;" /><br/>
					<h6>Amount:</h6><input type="number" name="amount" id="transfer_amount" style="color:#999;" /> <br/>
					<input type="submit" name="transfer" id="transferButton" value="Transfer" />
				</form>
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
