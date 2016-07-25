/**
 * 
 */
$(document).ready(
		function(){
			/*$('#savedata').click(function(e){
				console.log("hello");
			});*/
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


				$.ajax({
					type : "POST",
					contentType : "application/json",
					url : "hackathon/cusComplaint",
					contentType : "application/json; charset=UTF-8",
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
							var orderid=field[0];
							var itemName=field[1];
							var rating=field[2];
							var Complaint=field[3];


							/*var itemId=field.itemid;
						var itemName=field.itemName;
						var cost=field.Cost;*/
							var htmlData='<tr id="'+orderid+'"><td class="itemName">'+orderid+'</td><td>'+itemName+'</td><td class="itemCost itemrating">'+rating+'</td></td><td class="itemOrder custCompt"><select id="rating">'
							+'<option value="good">good</option>'
							+'<option value="bad">bad</option>'
							+'<option value="Need Improvement">Need improvement</option>'
							+'</td>'
							+'<td><select id="complaints">'
							+'<option value="NO COMPLAINTS">NO COMPLAINTS</option>'
							+'<option value="HEATH ISSUES">HEATH ISSUES</option>'
							+'<option value="BAD TASTE">BAD TASTE</option></td><td id="save"><button id="save">SAVE</button></td>'
							+'</tr>';
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

			$("#itemList").on('click','#save',function(e){
				console.log("Hi");
				var $tr=$(this).closest('tr');
			var orderid=$tr.attr("id");
				var rateSelect="#"+orderid+" #rating";
				var rating=$(rateSelect).val();
				var compt="#"+orderid+" #complaints";
				var comptVal=$(compt).val();
				var data=orderid+","+rating+","+comptVal;
				$.ajax({
				type : "POST",
					contentType : "application/json",
				url : "hackathon/cusRatingComplaint",
				contentType : "application/json; charset=UTF-8",
					data : data,
					dataType : 'json',
					timeout : 100000,
					success : function(jddata) {
						$tr.hide();
					}
				});
			});
});