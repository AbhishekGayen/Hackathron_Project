$(document).ready(function(e){
	
	/*lLogin of the vendor*/
	
	$(".registerVendor").bind('click',function(e){
	
		/*Validating the pwd*/
		var data=null;
		
		var uname=$(".uName").val().trim();
		var pwd=$(".uPwd").val().trim();
		var vid=$(".vId").val();
		var retypepwd=$(".rPwd").val();
		var data=uname+","+pwd+","+vid;
		var message="";
		if(pwd==retypepwd){
			
			$.ajax({
				  type: "POST",
				  url: "hackathon/vendorLogin",
				  contentType: 'application/json',
				  data: JSON.stringify(data),
				 
				  success: function(e){
					  message=e;
				  },
				  
				})
			   .fail(function(e) {
				   message=e;
			  })
			  .always(function() {
				  alert(message);
				  $("#Registration").hide();
			    $("#Login .alert-msg").text(message);
			    $("#Login ").css({"left":"18%"});
			  });
		}
		else{
			$(".alert-msg").text("Passwords does not match");
		}
		
		
	});
	
/*	$(".registerVendor").bind('click',function(e){
		
		Validating the pwd
		var uname=$(".uName").val();
		var pwd=$(".uPwd").val();
		var vid=$(".vid").val();
		var retypepwd=$(".rPwd").val();
		var data={name:pwd,pwd:pwd,vendorId:vid};
		var message="";
		if(pwd==retypepwd){
			
			$.ajax({
				  type: "POST",
				  url: "testData.jsp",
				  data: data,
				  success: function(e){
					  message=e;
				  },
				  
				})
			   .fail(function(e) {
				   message=e;
			  })
			  .always(function() {
				  alert(message);
			    $("#Registration .alert-msg").text(message);
			  });
		}
		else{
			$("#Registration .alert-msg").text("Passwords does not match");
		}
		
		
	});*/
	
$(".loginUser").bind('click',function(e){
		
		/*Validating the pwd*/
		var uname=$("#Login .uName").val();
		var pwd=$("#Login .uPwd").val();
		var data=uname+","+pwd;
				
			$.ajax({
				  type: "POST",
				  url: "hackathon/login",
				  contentType: 'application/json',
				  data: JSON.stringify(data),
				  success: function(e){ 
					  console.log("Value of type = : "+e);
					  if(e == "admin"){
						  window.location.href = "adminHome.jsp";
					  }else{
						  var split = e.split(",");
						  localStorage.setItem('vendorID',split[1]);
						  window.location.href = "vendorHomePage.jsp";
					  }
				  },
				})
			   .fail(function(e) {
				   message=e;
			  })
			  .always(function() {
			    $("#Login .alert-msg").text(message);
			  });
	});
	
});