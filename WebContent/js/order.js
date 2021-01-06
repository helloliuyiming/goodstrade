/**
 * 订单do-order
 */




//	自动合计金额
$(function(){
	allprice = 0;
	$(".singleprice").each(function(){
		singleprice = Number($(this).val());
		allprice += singleprice;
	})
	allfreight = 0;
	$(".singlefreight").each(function(){
		singlefreight = Number($(this).val());
		allfreight += singlefreight;
	});
	allpay = allprice + allfreight;
	$("#allprice").html(allprice);
	$("#allfreight").html(allfreight);
	$("#allpay").html(allpay);
	
	//	去支付，如果货到付款则直接进入订单详情,session域中有生成订单的商品信息orderGoods
	$(".toPayment").click(function(){
		var payMethod = $("#inlineFormCustomSelectPref").val();
		if(payMethod!="default"){
			location.href = "order/toPayment?payMethod="+payMethod+"&allprice="+allprice+"&allfreight="+allfreight;
		}else{
			alert("请选择支付方式");
			return false;
		}
		
	})	
	
	
	
});