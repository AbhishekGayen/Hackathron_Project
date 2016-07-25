<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript" src="resources/js/jquery-2.2.1.min.js"></script>
<script type="text/javascript" src="resources/js/func.js"></script>
<script type="text/javascript" src="resources/js/json2.js"></script>

<link rel="stylesheet" type="text/css" href="resources/css/fdcss.css">
</head>
<body id="background" class="custPage">
<header><div>INfy Easy Food...</div></header>
<nav>

<input type="hidden" id="FoodcourtId"/>
<input type="hidden" id="vendorIdVal"/>
<div id="wrapper">
<div id="Header">
<ul class="nolistStyle">
<li ><a href="/controller">Home</a>
</li><li id="foodCourt"><a  href="#">FoodCourt</a>
</li><li id="ratings"><a  href="customerComplaint.jsp">Ratings and Complaints</a>
</li><li><a href="contactDetails.jsp">Contact</a></li></ul>
</div>
<div id="cd">
<div id="fcOverlay" class="hidden"></div>
<div id="vdOverlay" class="hidden"></div>
</div>
</div>
</nav>
<section>
<div id="itemList" class="hidden">

</div>

</section>
<aside id="OrderDetails" class="hidden">
<h2></h2>

</aside>
<div id="mailMe" class="hidden">
<div id="content">
QR Code is generated click the below button to mail it to your email id.

<Button value ="QRGen" id="QrGen">OK</Button>
</div>

</div>

</body>
</html>