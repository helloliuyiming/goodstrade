window.onload = function() {
	var searchKey = document.getElementById("searchByKey")
	console.log(searchKey)
	searchKey.addEventListener('keyup', function(event) {
		if (event.keyCode == "13") {
			search()
		}
	})

	var goodsBlocks = document.getElementsByClassName("goods-block");
	for ( var i = 0; i <goodsBlocks.length; i++){
		let remain =goodsBlocks.item(i).getElementsByClassName("goods-outline").length%5;
		if (remain !== 0) {
			for (var j = 0; j < remain; j++) {
				var divElement = document.createElement("div");
				divElement.className="goods-outline"
				goodsBlocks.item(i).appendChild(divElement)
			}
		}
	}

	var orderInfoBadge = document.getElementsByClassName("order-info-badge")
	for (var i = 0; i < orderInfoBadge.length; i++) {
		var badge = orderInfoBadge.item(i).textContent;
		console.log("badge="+badge)
		if (badge.trim().length > 0 && badge.trim() !== '0') {
			orderInfoBadge.item(i).style.visibility = 'visible'
		}else {
			orderInfoBadge.item(i).style.visibility = 'hidden'
		}
	}
	
	let favoritesBtn = document.getElementsByClassName("btn-favorite");
	for(var i = 0;i<favoritesBtn.length;i++){
		favoritesBtn.item(i).addEventListener("mousedown",function(event){
			console.log("ddd")
			event.stopPropagation();

		})
	}
	
}

var search = function() {
	if (searchKey.value.trim().length = 0) {
		return
	}
	console.log("search...")
}


function getCookie(name){
	var strcookie = document.cookie;//获取cookie字符串
	var arrcookie = strcookie.split("; ");//分割
	//遍历匹配
	for ( var i = 0; i < arrcookie.length; i++) {
		var arr = arrcookie[i].split("=");
		if (arr[0] == name){
			return arr[1];
		}
	}
	return "";
}