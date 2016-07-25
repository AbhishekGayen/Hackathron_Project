<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript" src="resources/js/jquery-2.2.1.min.js"></script>
<script type="text/javascript" src="resources/js/login.js"></script>
<script type="text/javascript" src="resources/js/json2.js"></script>

<link rel="stylesheet" type="text/css" href="resources/css/fdcss.css">
</head>
<body id="background" class="vendorBackground">
<script>var contextPath = "${pageContext.request.contextPath}"</script>
<header><div>INfy Easy Food.s..</div></header>
<nav>
<div id="wrapper">
<div id="Header">
<ul class="nolistStyle">


</div>

</div>
</nav>
<section>


</section>
<aside id="Registration" >
 	
<div class="RegHolder">
<div class="RegPanel">
<h2>Register</h2>
<div id="RegForm" class="">

              <div id="alert-msg-booking" class="alert-msg"></div>

              <div class="inputGroup" >

                <div class="inputDiv">
                  <i class=""></i>
                  <input type="text" placeholder="Vendor userName"  class="uName input" required="required">
                </div>

                <div class="inputDiv">
                  <i class="s"></i>
                  <input type="password" placeholder="Vendor Password"  class="uPwd input" required="required">
                </div>

                <div class="inputDiv">
                  <i class=""></i>
                  <input type="password" placeholder="retype password"  class="rPwd input"  required="required">
                </div>

                

                <div class="inputDiv">
                  <i class=""></i>
                  <input type="text" placeholder="VendorId" name="vid" class="vId input" required="required">
                </div>

                <div class="inputDiv">
                  <button id="booking_submit" type="submit" class="fd-button registerVendor">Register</button>
                </div>
              </div>
            </div>
            </div></div>
</aside>


<aside id="Login" >
 	
<div class="RegHolder">
<div class="RegPanel">
<h2>LOGIN</h2>
<div id="RegForm" class="">

              <div id="alert-msg-booking" class="alert-msg"></div>

              <div class="inputGroup" >

                <div class="inputDiv">
                  <i class=""></i>
                  <input type="text" placeholder="Vendor userName"  class="uName input" required="required">
                </div>

                <div class="inputDiv">
                  <i class="s"></i>
                  <input type="password" placeholder="Vendor Password"  class="uPwd input" required="required">
                </div>


                <div class="inputDiv">
                  <button id="booking_submit" type="submit" class="fd-button loginUser">Login</button>
                </div>
              </div>
            </div>
            </div></div>
</aside>

</body>
</html>