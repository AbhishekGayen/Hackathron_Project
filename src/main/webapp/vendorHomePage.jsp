<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<body id="background" class="custPage">
<script>var contextPath = "${pageContext.request.contextPath}"</script>
<header><div>INfy Easy Food...</div></header>
<link rel="stylesheet" type="text/css" href="resources/css/fdcss.css">
<script type="text/javascript" src="resources/js/jquery-2.2.1.min.js"></script>

<script type="text/javascript" src="resources/js/json2.js"></script>
<script type="text/javascript" src="resources/js/vendor.js"></script>
<nav>
<div id="wrapper">
<div id="Header">
<ul class="nolistStyle">
<li ><a  id="home" href="vendorHomePage.jsp">Home</a>
<li ><a id="Accounts">Accounts</a>
<li ><a id="Complaints">Complaints</a>
<li><a id="SignOut" href="/controller">SignOut</a></li>
</ul>
</div>

</div>
</nav>
<section id="VendorSec">
<div id="FileUpload">
<form action="controller/login/upload" method="get" enctype="multipart/form-data" target="upload_target" >
 
 <div id="dropdown">
 FOOD TYPE:
 <select id="foodType">
  <option value="All day">ALL DAY</option>
  <option value="Breakfast">BREAK FAST</option>
  <option value="Dinner">DINNER</option>
  <option value="Lunch">LUNCH</option>
  <option value="snacks">SNACKS</option>
</select>
 
 </div>
 
  <div id="file">  File: <input id="fileInput" name="myfile" type="file" />
          <input id="fileSubmit" type="submit" name="submitBtn" value="Upload" />
          </div>
</form>


</div>


<div id="itemList">


</div>


</section>

</body>
</html>