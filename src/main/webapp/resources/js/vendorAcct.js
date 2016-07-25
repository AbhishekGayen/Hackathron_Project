/**
 * 
 */
$(document).ready(function(){
	var itemTable=$("#itemList");
	var JSONObj = null;
	
	var images=new Array('resources/images/image2.jpg','resources/images/chef-preparing-food.jpg','resources/images/fcImage1.jpg'/*,'images/fcimage2.jpg'*//*,'images/fcimage2.jpg','images/fcImage3.jpg'*/);
	var nextimage=0;
	doSlideshow();
	vendorACCounts();
$("#wrapper").on('click',"#Complaints",function(e){
	window.location.href ="vendorComplaints.jsp";
	
});
	/*$("#Complaints").click(function(e){
		window.location.href ="vendorComplaints.jsp";
		
	})*/;
	function doSlideshow(){
	    if(nextimage>=images.length){nextimage=0;}
	    $('#background')
	    .css('background-image','url("'+images[nextimage++]+'")')
	    .fadeIn(6000,function(){
	        setTimeout(doSlideshow,6000);
	    });
	}
function vendorACCounts() {
		JSONObj=new Object();;
			
		var VendorId=localStorage.getItem('vendorID');
			$.ajax({
				type : "POST",
				contentType : "application/json",
				url : "hackathon/vendorAccount",
				contentType : "application/json; charset=UTF-8",
				data : JSON.stringify(VendorId),
				dataType : 'json',
				timeout : 100000,
				success : function(itemLists) {
					console.log(itemLists);
					var tableHtml='<table id="itemData"></table>';
					
					itemTable.html(tableHtml);
					/*var itemList=jddata.vid1;*/
					//alert(jd);
					var itemInnertable=$("#itemData");
				/*	$.each(jddata, function(type,itemLists ){*/
						/*var typeHtml="<tr colspan='5'><td>"+type+"</td></>";
						itemInnertable.append(typeHtml);*/
						var header=	itemInnertable.append("<tr><td>ItemID</td><td>No Of Quantity</td><td>TotalCost</td></tr>");
						var totalTypeCost=0;
						$.each(itemLists, function(i,field ){
						
						var itemId=field.itemID;
						var qty=field.quantity;
						var totalCost=field.itemcost;
						var qtynum=parseInt(qty);
						var itemCost=parseInt(totalCost);
						itemCost=itemCost*qtynum;
						totalTypeCost+=itemCost;
						/*var itemId=field.itemid;
						var itemName=field.itemName;
						var cost=field.Cost;*/
						var htmlData="<tr><td class='itemCost'>"+itemId+"</td><td class='itemCost'>"+qty+"</td></td><td class='itemOrder'>"+itemCost+"</td></tr>";
						itemInnertable.append(htmlData);
						});
						
						var totalCostHtml="<tr class='bt' colspan='5'><td>Total Amount :"+totalTypeCost+"</td></>";
						itemInnertable.append(totalCostHtml);
			       /* });*/
						
				},
				error : function(e) {
					console.log("ERROR: ", e);
				},
				done : function(e) {
					console.log("DONE");
				}
		});
			setTimeout(showitemOrderStatus,6000);       
	}
});