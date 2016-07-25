$(document).ready(
function(){
	var itemTable=$("#itemList");
	var JSONObj = null;
	
	var images=new Array('resources/images/image2.jpg','resources/images/chef-preparing-food.jpg','resources/images/fcImage1.jpg'/*,'images/fcimage2.jpg'*//*,'images/fcimage2.jpg','images/fcImage3.jpg'*/);
	var nextimage=0;
	doSlideshow();
	showitemOrderStatus();

	function doSlideshow(){
	    if(nextimage>=images.length){nextimage=0;}
	    $('#background')
	    .css('background-image','url("'+images[nextimage++]+'")')
	    .fadeIn(6000,function(){
	        setTimeout(doSlideshow,6000);
	    });
	}
	//$('#load_tweets').load('record_count.php').fadeIn("slow");
	$("#Complaints").click(function(e){
		window.location.href = "vendorComplaints.jsp";
		
	});
	
	$("#Accounts").click(function(e){
		window.location.href = "vendorAccounts.jsp";
		
	});
	function showitemOrderStatus() {
		JSONObj=new Object();
			   var VendorId=localStorage.getItem('vendorID');
			$.ajax({
				type : "POST",
				contentType : "application/json",
				url : "hackathon/itemsDetails",
				contentType : "application/json; charset=UTF-8",
				data : JSON.stringify(VendorId),
				dataType : 'json',
				timeout : 100000,
				success : function(jddata) {
					console.log(jddata);
					var tableHtml='<table id="itemData"></table>';
					
					itemTable.html(tableHtml);
					/*var itemList=jddata.vid1;*/
					//alert(jd);
					var itemInnertable=$("#itemData");
					$.each(jddata, function(i, field){
						var itemId=field[0];
						var itemName=field[1];
						var cost=field[2];
						var totalOrder=field[3];
						
						/*var itemId=field.itemid;
						var itemName=field.itemName;
						var cost=field.Cost;*/
						var htmlData="<tr id='"+itemId+"'><td class='itemName'>"+itemName+"</td><td class='itemCost'>"+cost+"</td></td><td class='itemOrder'>"+totalOrder+"</td><td><input value='DELETE' type='button' id='deleteItem'></td></tr>";
						itemInnertable.append(htmlData);
			        });
						
				},
				error : function(e) {
					console.log("ERROR: ", e);
				},
				done : function(e) {
					console.log("DONE");
				}
		});
			  
	}
	/**/
	$("#itemList").on('click',"#deleteItem",function(e){
		$(this).closest('tr').hide();
		var itemId=$(this).closest('tr').attr("id");
		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "hackathon/deleteItemsDetails",
			contentType : "application/json; charset=UTF-8",
			data : itemId,
			dataType : 'json',
		
			success : function(jddata) {
				
			}});
		
	});
	
	$("#Header").on('click',"#deleteItem",function(e){});
});