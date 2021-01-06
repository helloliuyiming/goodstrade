<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>付款</title>
    <!-- 响应式标签 -->
    <meta content="width=device-width, initial-scale=1, shrink-to-fit=no" name="viewport">
    <link href="link/bootstrap-4.5.0-dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="css/common.css" rel="stylesheet">
    <script src="link/jquery-3.5.1.js"></script>
    <script src="link/bootstrap-4.5.0-dist/js/bootstrap.bundle.js"></script>
    <script src="js/order.js"></script>
    <style>
        html, body {
            width: 100%;
            height: 100%;
            overflow: hidden;
        }
    </style>
</head>
<body class="container">

<div class="card"
     style="height: 90%;width: 100%;margin-top: 3%;display: flex;flex-flow: row nowrap;flex-direction: column">
    <h2 class="card-header" style="text-align: center;">付款</h2>
    <div class="card-body row justify-content-between" style="flex-grow: 1;height: 100%">
        <div class="col-2 card offset-1"
             style="height: 100%;display: flex;flex-flow: column;justify-content: space-around;align-items: center">
            <button class="btn" style="display: block;width: 80%"><img src="image/alipay.svg" style="width: 100%"
                                                                       class="rounded  mx-auto d-block"></button>
            <button class="btn" style="display: block;width: 80%"><img src="image/云闪付.svg" style="width: 100%"
                                                                       class="rounded  mx-auto d-block"></button>
            <button class="btn" style="display: block;width: 80%"><img src="image/微信支付.svg" style="width: 100%"
                                                                       class="rounded  mx-auto d-block"></button>
        </div>

        <div class="col-8" style="height: 100%;display: flex;justify-content: center;align-items: center">
            <div style="width: 50%;text-align: center">
                <img src="image/alipay.svg" width="200px">
                <p class="text-center" style="margin-top: 20px">使用${paymentMethod}扫一扫付款</p>
                <p class="text-center" style="margin-top: 20px">总金额:${allpay}</p>
                <div class="row justify-content-around" style="margin-top: 20%;width: 100%">
                    <form action="pay">
                        <input type="hidden" name="paymentMethod" value="${paymentMethod}">
                        <input type="hidden" name="allpay" value="${allpay}">
                        <c:forEach var="order" items="${orders}">
                            <input type="hidden" name="ids" value="${order.id}">
                        </c:forEach>

                        <button type="button" class="col-4 btn btn-outline-secondary paycancal" onclick="javascrtpt:window.location.href='personal';">取消支付</button>
                        <button type="submit" class="col-4 btn btn-primary paysuccess"> 支付完成</button>
                    </form>

                </div>
            </div>

        </div>
    </div>
</div>

</body>
</html>