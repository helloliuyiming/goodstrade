<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>下订单</title>
    <!-- 响应式标签 -->
    <meta content="width=device-width, initial-scale=1, shrink-to-fit=no" name="viewport">
    <link href="link/bootstrap-4.5.0-dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="css/common.css" rel="stylesheet">
    <script src="link/jquery-3.5.1.js"></script>
    <script src="link/bootstrap-4.5.0-dist/js/bootstrap.bundle.js"></script>
    <style>
        html, body {
            width: 100%;
            height: 100%;
        }

    </style>
</head>
<body class="container">
<div class="card" style="margin-top: 2%;margin-bottom: 10%">
    <h5 class="card-header" style="text-align: center;">结算页</h5>
    <div class="card-body">
        <form action="doOrder" method="post">
            <div style="overflow-x: hidden;">
                <!-- 地址栏 -->
                <div class="card" style="padding: 10px 20px;margin-top: 10px">
                    <p>收货人信息</p>
                    <c:choose>
                        <c:when test="${defaultAddress==null}">
                            <div id="no-address">
                                <button class="btn btn-primary" data-target="#add-address" data-toggle="modal"
                                        type="button">添加收货地址
                                </button>
                            </div>
                        </c:when>

                        <c:otherwise>
                            <div id="has-address">
                                <div class="form-check">
                                    <input class="form-check-input" type="radio" name="addrId"
                                           value="${defaultAddress.id}"
                                           checked>
                                    <label class="form-check-label">
                                            ${defaultAddress.name} ${defaultAddress.phone}·${defaultAddress.province}${defaultAddress.city}${defaultAddress.district}${defaultAddress.detail}
                                    </label>
                                </div>
                                <c:if test="${fn:length(otherAddress)>0}">
                                    <a class="" type="button" data-toggle="collapse" data-target="#collapseExample"
                                       aria-expanded="false" aria-controls="collapseExample">查看更多</a>
                                    <c:forEach var="address" items="${otherAddress }">
                                        <div class="form-check">
                                            <input class="form-check-input" type="radio" name="addrId"
                                                   value="${address.id}">
                                            <label class="form-check-label">
                                                    ${address.name} ${address.phone}·${address.province}${address.city}${address.district}${address.detail}
                                            </label>
                                        </div>
                                    </c:forEach>
                                </c:if>
                            </div>
                        </c:otherwise>
                    </c:choose>

                    <div class="card" style="padding: 10px 20px;margin-top: 30px">
                        <p>商品清单</p>

                        <div class="one-home" style="width: 100%">
                            <table class="table">
                                <thead>
                                <tr class="row justify-content-center">
                                    <th class="col-1">图片</th>
                                    <th class="col-5">商品标题</th>
                                    <th class="col-2">商品价格</th>
                                    <th class="col-1">运费</th>
                                    <th class="col-2">操作</th>
                                </tr>
                                </thead>
                                <tbody>

                                <c:forEach var="goods" items="${goodsList}">
                                    <tr class="row justify-content-center">
                                        <input type="hidden" name="goodsIds" value="${goods.goodsId}"/>
                                        <td class="col-1"><img src="${pageContext.request.contextPath}/images/${goods.goodsImage}1" style="width: 100%"></td>
                                        <td class="col-5 ">
                                            <p class="text-truncate"><a
                                                    href="goodsdetail?id=${goods.goodsId}">${goods.goodsName}</a></p>
                                            <p class="text-truncate"></p>
                                        </td>
                                        <td class="col-2">￥${goods.goodsPrice}</td>
                                        <td class="col-1">￥${goods.goodsFreight}</td>
                                        <td class="col-2">
                                            <button class="btn btn-outline-info" data-toggle="modal" disabled>添加备注
                                            </button>
                                        </td>
                                    </tr>
                                </c:forEach>

                                </tbody>
                            </table>
                        </div>
                    </div>

                    <div class="card" style="padding: 10px 20px;margin-top: 30px">
                        <div class="form-inline">
                            <label class="my-1 mr-2" for="inlineFormCustomSelectPref">支付方式</label>
                            <select name="paymentMethod" class="custom-select my-1 mr-sm-2"
                                    id="inlineFormCustomSelectPref" required>
                                <option selected value="支付宝">支付宝</option>
                                <option value="微信">微信</option>
                                <option value="云闪付">云闪付</option>
                                <option value="货到付款">货到付款</option>
                            </select>
                        </div>

                    </div>

                </div>

                <div>
                    <div class="row" style="padding: 0 20px">
                        <p class="col-10 text-right"><span style="color: red">${goodsCount}</span> 件商品，总商品金额：</p>
                        <p class="col-2 text-right">￥<span id="allprice">${totalPrice}</span></p>
                    </div>

                    <div class="row" style="padding: 0 20px">
                        <p class="col-10 text-right">总运费：</p>
                        <p class="col-2 text-right">￥<span id="allfreight">${totalFreight}</span></p>
                    </div>
                </div>
                <div style="width: 100%;background: #d3d9df">
                    <div class="row" style="padding: 10px 20px 0 20px">
                        <p class="col-10 text-right align-self-center">应付总额：</p>
                        <p class="col-2 text-right">+<span style="color: red;font-size: 28px"><span
                                id="allpay">${totalPay}</span></span></p>
                    </div>
                    <p class="text-right " style="color: #6c757d;font-size: 14px;padding: 0 20px 10px 20px">
                        寄送至：<span
                            style="margin-right: 10px">${address.province}${address.city}${address.district}${address.detail}</span>
                        收货人：<span> ${address.name}</span><span style="margin-left: 10px">${address.phone}</span>
                    </p>
                </div>
                <div style="text-align: right">
                    <button type="submit" class="btn btn-primary toPayment">提交订单</button>
                </div>
            </div>
        </form>


    </div>


    <div id="add-other-info" class="modal" tabindex="-1" role="dialog">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">添加备注信息</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <textarea class="form-control">

                    </textarea>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-primary">保存</button>
                </div>
            </div>
        </div>
    </div>


    <!-- 添加收货地址对话框 -->
    <div aria-hidden="true" class="modal fade bd-example-modal-lg" id="add-address" role="dialog" tabindex="-1">
        <div class="modal-dialog modal-lg modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    添加收获地址
                </div>
                <form action="addAddress" method="post">
                    <input type="hidden" name="redirect" value="previewOrder">
                    <div class="modal-body">
                        <label>地址</label>
                        <div class="form-row">
                            <div class="form-check-inline" id="select-address"><!-- container -->
                                <select name="province" class="form-control" required></select><!-- 省 -->
                                <select name="city" class="form-control" required></select><!-- 市 -->
                                <select name="district" class="form-control" required></select><!-- 区 -->
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="inputAddress">详细地址</label>
                            <input name="detail" class="form-control" id="inputAddress" placeholder="xx街道xx小区xx号"
                                   type="text" required>
                        </div>

                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <label for="inputEmail4">收件人</label>
                                <input class="form-control" id="inputEmail4" type="text" name="name" required>
                            </div>
                            <div class="form-group col-md-6">
                                <label for="inputPassword4">联系电话</label>
                                <input name="phone" class="form-control" id="inputPassword4" placeholder="仅支持中国大陆+86手机号"
                                       type="text" required>
                            </div>
                        </div>
                        <div class="form-check">
                            <input name="isDefault" class="form-check-input" id="exampleCheck1" type="checkbox">
                            <label class="form-check-label" for="exampleCheck1">设为默认地址</label>
                        </div>

                    </div>

                    <div class="modal-footer">
                        <button class="btn btn-secondary" data-dismiss="modal" type="button">取消</button>
                        <button class="btn btn-primary" type="submit" id="addAddressBtn">保存</button>
                    </div>
                </form>

            </div>
        </div>
    </div>
    <script src="link/jquery-3.5.1.js"></script>
    <script src="link/bootstrap-4.5.0-dist/js/bootstrap.bundle.js"></script>
    <script src="link/distpicker/distpicker.min.js"></script>
    <script>
        $('#select-address').distpicker({
            province: '--请选择省份--',
            city: '--请选择市--',
            district: '--请选择区/县--'
        });

    </script>
</body>
</html>