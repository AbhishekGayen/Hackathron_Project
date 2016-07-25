/**
 * 
 */
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
function showitemOrderStatus() {
		JSONObj=new Object();;
		var VendorId=localStorage.getItem('vendorID');
			
			$.ajax({
				type : "POST",
				contentType : "application/json",
				url : "hackathon/vendorDetails",
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
					var header="<tr><td>VendorId</td><td>rating</td><td>Complaint</td></tr>";
					itemInnertable.append(header);
					$.each(jddata, function(i, field){
						var vendorId=field.itemID;
						var rating=field.rating;
						var Complaint=field.complaint;
						
						
						/*var itemId=field.itemid;
						var itemName=field.itemName;
						var cost=field.Cost;*/
						var htmlData="<tr><td class='itemName'>"+vendorId+"</td><td class='itemCost'>"+rating+"</td></td><td class='itemOrder'>"+Complaint+"</td></tr>";
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
});