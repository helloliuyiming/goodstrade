/*
*主页
*/
$(function(){
	//	搜索
	$("#search").click(function(){
		var searchkey = $("#searchByKey").val();
		if(searchkey!=""){
			location.href = "goods/searchByName?goodsName="+searchkey;
		}
	});

	//	收藏时未登录提示先登录
	$(".collect1").click(function(){
		alert("请先登录!");
	});
	
	//	购买时未登录提示先登录
	$(".buynow").click(function(){
		alert("请先登录!");
	});
	
	//	筛选类别商品
	$("#screen").change(function(){
		location.href = "goods/screen?columnNumber="+$("#screen").val();
	});
	//	筛选通过名字商品
	$("#screenByName").change(function(){
		location.href = "goods/screenByName?columnNumber="+$("#screenByName").val();
	});
	
	//	收藏商品
	$(".addCollect").click(function(){
		$.post("goods/addCollect",
				{
					gid:$(this).val()
				},
				function(data){
					alert(data);
					window.location.reload();
				})
	});
	
	//	取消收藏商品
	$(".deleteCollect").click(function(){
		$.post("goods/deleteCollect",
				{
					gid:$(this).val()
				},
				function(data){
					alert(data);
					window.location.reload();
				})
	});
	
	//	收藏页面清除单个收藏
	$(".deleteoneGoods").click(function(){
		var gid = $(this).attr("value").toString();
		$.post("goods/deleteCollect",
		{
			gid:gid
		},
		function(data){
			if(data=="取消收藏成功!"){
				alert("清除成功");
				window.location.reload();
			}else{
				alert("清除成功");
				window.location.reload();
			}
		})
	});
	
	
	//	收藏页面清除多个收藏
	$("#deleteMoreGoods").click(function(){
		var checkNumber = $(".favorites-item input:checkbox:checked").length;
		if(checkNumber==0){
			alert("您还未选中任何商品");
		}
		if(checkNumber==1){
			var gid = $(".favorites-item input:checkbox:checked").val();
			$.post("goods/deleteCollect",
			{
				gid:gid
			},
			function(data){
				if(data=="取消收藏成功!"){
					alert("清除成功");
					$(".favorites-item input:checkbox").prop("checked",false);
					window.location.reload();
				}else{
					alert("清除失败");
					$(".favorites-item input:checkbox").prop("checked",false);
					window.location.reload();
				}
			});
		}else if(checkNumber>1){
			var gid = "";
	        $(".favorites-item input:checkbox:checked").each(function(index, element) {
	            gid += $(this).val()+",";
	        });
			$.post("goods/deletemoreCollect",
			{
				gid:gid
			},
			function(data){
				alert(data);
				$(".favorites-item input:checkbox").prop("checked",false);
				window.location.reload();
			});
		}
	});
	
	
	//	左边选择右边出现
	$(".choose").click(function(){
		var gid = $(this).val();
		if($(this).is(":checked")){
			$(this).prop("checked",true);
			var goodsName = $(this).next().val();
			var goodsPrice = $(this).next().next().val();
			$(".insert").prepend('<div id="'+gid+'" class="row justify-content-around"><div class="col-1">'+
					'<button value="'+gid+'" type="button" class="close close1" aria-label="Close">'+
					'<span aria-hidden="true">&times;</span>'+'</button></div>'+
					'<p class="col-8 text-truncate">'+goodsName+'</p><p class="col-2">+'+goodsPrice+
					'</p></div>');
		}else{
			$(this).prop("checked",false);
			$("#"+gid).remove();
		}
	})
	
	//	右边小错×取消选中 - - - 动态添加的元素利用on进行事件绑定，否则不生效
	$("body").on("click", ".close1", function() {
		var gid=$(this).attr("value");
		$("#"+gid).remove();
		$(".favorites-item").find("input[value="+gid+"]").prop("checked",false);
	})
	
	//	全选
	$("#checkAll").click(function(){
		if($(this).is(":checked")){
			$(".insert").children().remove();
			$(".favorites-item input:checkbox").removeAttr("checked");
			$(".favorites-item input:checkbox").prop("checked",true);
			$(".favorites-item input:checkbox:checked").each(function(){
				$(".insert").prepend('<div id="'+$(this).val()+'" class="row justify-content-around"><div class="col-1">'+
						'<button value="'+$(this).val()+'" type="button" class="close1 close" aria-label="Close">'+
						'<span aria-hidden="true">&times;</span>'+'</button></div>'+
						'<p class="col-8 text-truncate">'+$(this).next().val()+'</p><p class="col-3">+'+$(this).next().next().val()+
						'</p></div>');
			})
			
		}
		if(!$(this).is(":checked")){
			$(".favorites-item input:checkbox").prop("checked",false);
			$(".insert").children().remove();
		}
		
	})
	
//	商品详情或个人收藏跳转至订单页面
	$(".todoOrder").click(function(){
		//	获取选中商品的商品id
		var checkNumber = $(".favorites-item input:checkbox:checked").length;
		if(checkNumber==0){
			alert("您还未选中任何商品");
		}else if(checkNumber==1){
			var gid = $(".favorites-item input:checkbox:checked").val();
			location.href = "previewOrder?goodsIds="+gid;
		}else{
			var gid = "";
	        $(".favorites-item input:checkbox:checked").each(function(index, element) {
	            gid += "&goodsIds="+$(this).val();
	        });
	        gid = gid.substr(1,gid.length)
	        location.href = "previewOrder?"+gid;
		}
	});
});