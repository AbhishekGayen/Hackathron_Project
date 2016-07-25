/**
 * 
 */
var x;
$(document).ready(
function(){
	var itemTable=$("#itemList");
	var JSONObj = null;
	
	var images=new Array('resources/images/bg-image-2.jpg','resources/images/bg-image-4.jpg','resources/images/fcImage1.jpg'/*,'images/fcimage2.jpg'*//*,'images/fcimage2.jpg','images/fcImage3.jpg'*/);
	var nextimage=0;
	doSlideshow();

	function doSlideshow(){
	    if(nextimage>=images.length){nextimage=0;}
	    $('#background')
	    .css('background-image','url("'+images[nextimage++]+'")')
	    .fadeIn(6000,function(){
	        setTimeout(doSlideshow,6000);
	    });
	}
	
	
	//alert("hii");
	var $fcOverlay=$('#fcOverlay');
		$.getJSON('hackathon/one', function(jd) {
			console.log("Value of JSON Response"+jd);
			$fcOverlay.html('<ul id="fcList" class="nolistStyle" 	/>');
			
			$.each(jd, function(i, field){
				$("#fcList").append("<li id="+field.foodCourtID+">"+field.foodCourtName+"</li>");
	        })
	      //  $("#fcList").append('<div id="vdOverlay" class="hidden"></div>');
	        
			/*var parentOffset=$("#Header #foodCourt").position(); 	
			var offset=$fcOverlay.position();
			var left=parentOffset.left+offset.left;
			$fcOverlay.css({'top':-parentOffset.top,'left':left,'position':'relative'});*/
			
			
			$("#fcList li").hover(function(){
				//alert("ji");
				var fcid=$(this).attr("id"),	
			
				   data=fcid;
				$("#FoodcourtId").val(fcid);
				$.ajax({
					type : "POST",
					contentType : "application/json",
					url : "hackathon/vendor",
					contentType : "application/json; charset=UTF-8",
					data : JSON.stringify(data),
					dataType : 'json',
					timeout : 100000,
					success : function(vendorData) {
						console.log(vendorData);
						$("#vdOverlay").html('<ul id="vdList" class="nolistStyle" 	/>');
						//var jd=jddata.Fc1;
						//alert(jd);
						$.each(vendorData, function(i, field){
							$("#vdList").append("<li id="+field[0]+">"+field[1]+"</li>");
				        });
						$("#vdOverlay").show();
						console.log("SUCCESS: ", data);
						console.log(data);
					},
					error : function(e) {
						console.log("ERROR: ", e);
					},
					done : function(e) {
						console.log("DONE");
					}
			});
				/*	$.getJSON('hackathon/vendor', data, function(vendorData) 
				{
						console.log(vendorData);
						$("#vdOverlay").html('<ul id="vdList" class="nolistStyle" 	/>');
						//var jd=jddata.Fc1;
						//alert(jd);
						$.each(vendorData, function(i, field){
							$("#vdList").append("<li id="+field.vid+">"+field.vendorName+"</li>");
				        });
						var parentOffset=$("#"+id).position(); 	
						var offset=$("#fcOverlay").position();
						console.log(parentOffset.left+"  "+offset.left);
						var left=parentOffset.left;+$fcOverlay.width();
						var top=$fcOverlay.position().top+parentOffset.top 		;
						top=11;
						$("#vdOverlay").css({'top':top 	,'left':left,'position':'relative'});
						$("#vdOverlay").show();
						
					});
					*/
					
					
	}
			);
			
		});
		
		$( "#cd" ).on( "click", "#vdList li", function() {
			JSONObj=new Object();;
			//$("#vdList li").bind('click',function(e){
				$fcOverlay.hide();
				$("#vdOverlay").hide();
				//var id=$(this).attr("id");
				var vcid=$(this).attr("id"),	
				   data=vcid;
				$("#vendorIdVal").val(vcid);
				$.ajax({
					type : "POST",
					contentType : "application/json",
					url : "hackathon/itemsDetails",
					contentType : "application/json; charset=UTF-8",
					data : JSON.stringify(data),
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
							
							/*var itemId=field.itemid;
							var itemName=field.itemName;
							var cost=field.Cost;*/
							var htmlData="<tr id='"+itemId+"'><td class='itemName'>"+itemName+"</td><td class='itemCost'>"+cost+"</td><td><input value='ADD' type='button' id='add'></td></tr>";
							itemInnertable.append(htmlData);
				        });
						var countData="<tr><td colspan='5'></tr>";
						itemInnertable.append(countData);
						/*var width=$(window).width();
						var height=$(window).height();
						itemTable.css("left",width/3);
						itemTable.css("top",height/4);*/
						var $OrderDetails=$("#OrderDetails");
						var initialHtml="<table id='orderData'></table><span>TOTAL:</span><span id='Totalcost'>0</span><button id='buyCoupon'>Buy Coupon</button>";
						$OrderDetails.html(initialHtml);
						itemTable.show();
					},
					error : function(e) {
						console.log("ERROR: ", e);
					},
					done : function(e) {
						console.log("DONE");
					}
			});
				/*$.getJSON('hackathon', function(jddata) {
					var tableHtml='<table id="itemData">></table>';
					
					itemTable.html(tableHtml);
					var itemList=jddata.vid1;
					//alert(jd);
					var itemInnertable=$("#itemData");
					$.each(itemList, function(i, field){
						var itemId=field.itemid;
						var itemName=field.itemName;
						var cost=field.Cost;
						var htmlData="<tr id='"+itemId+"'><td class='itemName'>"+itemName+"</td><td class='itemCost'>"+cost+"</td><td><input value='ADD' type='button' id='add'></td></tr>";
						itemInnertable.append(htmlData);
			        });
					var countData="<tr><td colspan='5'></tr>";
					itemInnertable.append(countData);
					var width=$(window).width();
					var height=$(window).height();
					itemTable.css("left",width/3);
					itemTable.css("top",height/4);
					var $OrderDetails=$("#OrderDetails");
					var initialHtml="<table id='orderData'></table><span>TOTAL:</span><span id='Totalcost'>0</span><button id='buyCoupon'>Buy Coupon</button>";
					$OrderDetails.html(initialHtml);
					itemTable.show();
					
				});*/
			});
		
		$( "#itemList" ).on( "click", "#add", function() {
			JSONitemObj=new Object();
			var $tr=$(this).closest('tr');
			var itemId=$tr.attr("id");
			if(jQuery.isEmptyObject(JSONObj[itemId])){
			//	alert("data exist already");
			
			JSONitemObj[itemId]=itemId;
			var itemName=$tr.find('.itemName').text();
			JSONitemObj.itemName=itemName;
			var itemCost= parseInt($tr.find('.itemCost').text(),10);
			JSONitemObj.cost=itemCost;
			JSONitemObj.qty=1;
			//alert(itemName);
			//alert($(this).closest('tr').html());
			var qty=1;
			var $OrderData=$("#orderData");
			var $OrderDetails=$("#OrderDetails");
			var totCost=itemCost*qty;
			
			var rowData="<tr id="+itemId+"><td class='itemName'>"+itemName+"</td><td class='calc'>"+qty+"*"+itemCost+"=</td><td class='itemCost'>"+totCost+"</td><td><span class='qtySpan'><input type='number' class='qtyVal'/></span><span class='remove'>X</span></td></tr>";
			$OrderData.append(rowData);
			var cost= parseInt($("#Totalcost").text(),10);
			cost=cost+itemCost;
			$("#Totalcost").text(cost);
			JSONObj[itemId]=JSONitemObj;
			$(this).val("Added to Cart");
			$OrderDetails.show();
			}/*else{
				alert("Data exist already");
			}*/
			
			
		});
		$( "#OrderDetails" ).on( "click", ".remove", function() {
			//alert("chnage cap");
			JSONitemObj=new Object();
			var $tr=$(this).closest('tr');
			var itemId=$tr.attr("id");
			JSONitemObj=JSONObj[itemId];
			var prevqty=parseInt(JSONitemObj.qty);
			var cost=parseInt(JSONitemObj.cost);
			
			var prevCost=prevqty*cost;
			//alert(qty);
			
			
			var totcost= parseInt($("#Totalcost").text(),10);
			totcost=totcost-prevCost;
			$("#Totalcost").text(totcost);
			
			 	
			JSONObj[itemId]=null;
			
			$tr.find(".itemCost").text(cost);
			var dat= "#"+itemId+" #add";
			$("#itemList").find(dat).val("Add");
			$tr.remove(); 	
			
			
		});
		$( "#OrderDetails" ).on( "click", "#buyCoupon", function() {
			var dataStr=null;
			var vendorId=$("#vendorIdVal").val();
			var FoodCourtId=$("#FoodcourtId").val();
			var reqData="#"+vendorId+","+FoodCourtId;
			$.each(JSONObj,function(i,j){JSONitemObj=new Object();if(j!=null){ 
				JSONitemObj=j; 
				var k=i+"/"+j.cost+"/"+j.qty+"/"+j.itemName;
				if(dataStr==null){
					dataStr=k;
				}
				else{
					dataStr=dataStr+","+k;
				}
				}});
			var data={id:dataStr};
			console.log(dataStr);
			dataStr=dataStr+reqData;
			$.ajax({
				  type : "POST",
				  url: "hackathon/buyCoupon",
				  data:JSON.stringify(dataStr),
				  contentType: 'application/json'
				}).done(function() {
					console.log("Successfull");
				 // $( this ).addClass( "done" );
					$("#vdOverlay").hide();
					$("#itemList").hide();
					$( "#OrderDetails" ).hide();
					 $("#mailMe").show();
				  //alert("QR code generated ");
				});
			
		});
		$( "#OrderDetails" ).on( "change", ".qtyVal", function() {
			//alert("chnage cap");
			JSONitemObj=new Object();
			var $tr=$(this).closest('tr');
			var itemId=$tr.attr("id");
			JSONitemObj=JSONObj[itemId];
			var prevqty=parseInt(JSONitemObj.qty);
			var cost=parseInt(JSONitemObj.cost);
			var qty=parseInt($(this).val());
			var prevCost=prevqty*cost;
			//alert(qty);
			if(qty>0){
			var txt=qty+"*"+cost;
			cost=cost*qty;
			$tr.find(".calc").text(txt);
			var totcost= parseInt($("#Totalcost").text(),10);
			totcost=cost+totcost-prevCost;
			$("#Totalcost").text(totcost);
			
			JSONitemObj.qty=qty;
			JSONObj[itemId]=JSONitemObj;
			
			$tr.find(".itemCost").text(cost);
			}
		});
		$("#mailMe").on('click','#QrGen',function(e){
			$("#mailMe").hide();
			$.ajax({
				  type : "POST",
				  url: "hackathon/sendMessage",
				  contentType: 'application/json'
				}).done(function() {
				 alert("Send Message Successfully!!!");
				});
		});
		
		$("#foodCourt").hover(function(){$fcOverlay.slideDown()});
		$("body").bind('click',function(e){
			$fcOverlay.hide();
			$("#vdOverlay").hide();
			
			/*e.preventDefault();
			e.stopPropagation();*/
		
		});
	
}		
);