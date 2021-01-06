<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>订单详情</title>
    <!-- 响应式标签 -->
    <meta content="width=device-width, initial-scale=1, shrink-to-fit=no" name="viewport">
    <link href="link/bootstrap-4.5.0-dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="link/bootstrap-steps.min.css" rel="stylesheet">
    <link href="css/common.css" rel="stylesheet">
    <style>
        #order-goods-block, #order-info, #order-progress-block {
            margin-top: 50px;
            padding: 10px;
        }
    </style>
</head>
<body class="container">

<main>
    <div class="card" id="order-progress-block">
        <div class="card-body row">
            <div class="col-3" id="status-and-action">
                <p>订单号：${orderDetail.order_id}</p>
                <h4 class="text-center">
                    <c:choose>
                        <c:when test="${isSeller&&(orderDetail.status=='unpaid')}">
                            买家未付款
                        </c:when>
                        <c:when test="${isSeller&&(orderDetail.status=='unsent')}">
                            买家已付款
                        </c:when>
                        <c:when test="${isSeller&&(orderDetail.status=='unreceive')}">
                            等待买家收货
                        </c:when>
                        <c:when test="${isSeller&&(orderDetail.status=='uncomment')}">
                            完成
                        </c:when>
                        <c:when test="${isSeller&&(orderDetail.status=='finished')}">
                            完成
                        </c:when>

                        <c:when test="${isCustomer&&(orderDetail.status=='unpaid')}">
                            未付款
                        </c:when>
                        <c:when test="${isCustomer&&(orderDetail.status=='unsent')}">
                            卖家未发货
                        </c:when>
                        <c:when test="${isCustomer&&(orderDetail.status=='unreceive')}">
                            等待收货
                        </c:when>
                        <c:when test="${isCustomer&&(orderDetail.status=='uncomment')}">
                            待评价
                        </c:when>
                        <c:when test="${isCustomer&&(orderDetail.status=='finished')}">
                            完成
                        </c:when>
                        <c:otherwise>
                            未完成
                        </c:otherwise>
                    </c:choose>
                </h4>
                <div style="width: 100%;text-align: center">

                    <c:choose>

                        <c:when test="${isSeller&&(orderDetail.status=='unsent')}">
                            <button class="btn btn-outline-danger" data-toggle="modal" data-target="#seller-update-express">发货</button>
                        </c:when>


                        <c:when test="${isCustomer&&(orderDetail.status=='unpaid')}">
                            <button class="btn btn-outline-success"><a href="payment?ids=${orderDetail.order_id}">去付款</a> </button>
                        </c:when>


                        <c:when test="${isCustomer&&(orderDetail.status=='unreceive')}">
                            <button class="btn btn-outline-success"><a href="#">查看物流</a> </button>
                        </c:when>
                        <c:when test="${isCustomer&&(orderDetail.status=='uncomment')}">
                            <button class="btn btn-outline-danger" data-toggle="modal" data-target="#updateOrderComment">去评价</button>
                        </c:when>

                    </c:choose>


                </div>
            </div>

            <div class="col-9">



                <ul class="steps">
                    <li class="step step-success">
                        <div class="step-content">
                            <span class="step-circle">1</span>
                            <span class="step-text">生成订单</span>
                        </div>
                    </li>
                    <li class="step step-active">
                        <div class="step-content">
                            <span class="step-circle">2</span>
                            <span class="step-text">买家未付款</span>
                        </div>
                    </li>
                    <li class="step ">
                        <div class="step-content">
                            <span class="step-circle">3</span>
                            <span class="step-text">代发货</span>
                        </div>
                    </li>
                    <li class="step">
                        <div class="step-content">
                            <span class="step-circle">4</span>
                            <span class="step-text">待收货</span>
                        </div>
                    </li>

                    <li class="step">
                        <div class="step-content">
                            <span class="step-circle">4</span>
                            <span class="step-text">完成</span>
                        </div>
                    </li>
                </ul>

            </div>
        </div>
    </div>

    <div class="card" id="order-goods-block">
        <table class="table">
            <thead>
            <tr class="row">
                <th class="offset-1 col-6">商品</th>
                <th class="col-2">价格</th>
                <th class="col-2">操作</th>
            </tr>
            </thead>
            <tbody>
            <tr class="row">
                <td class="offset-1 col-6">
                    <div class="row justify-content-center">
                        <img src="images/${orderDetail.goods_image}" class="col-3" style="width: 100%">
                        <p class="col-7 text-truncate"><a href="goodsdetail?id=${orderDetail.goods_id}">${orderDetail.goods_name}</a></p>
                    </div>
                </td>
                <td class="col-2">
                    ￥${orderDetail.money}+${orderDetail.freight}
                </td>
                <td class="col-2">
                    <button class="btn">取消订单</button>
                </td>
            </tr>
            </tbody>
        </table>
        <div class="row" style="width: 100%">
            <table class="table table-sm table-borderless offset-8 col-4">
                <tr>
                    <td>商品总额：</td>
                    <td>¥${orderDetail.money}</td>
                </tr>
                <tr>
                    <td>运费：</td>
                    <td>+ ¥${orderDetail.freight}</td>
                </tr>
                <tr>
                    <td>支付有礼：</td>
                    <td>- ¥0.02</td>
                </tr>
                <tr>
                    <td><p style="font-size: 22px;color: red">实付款：</p></td>
                    <td><p style="font-size: 25px;color: red">¥${orderDetail.money+orderDetail.freight}</p></td>
                </tr>

            </table>
        </div>
    </div>

    <div class="card " id="order-info" style="margin-bottom: 100px">
        <div class="row row-cols-3">
            <div class="col">
                <h5 class="text-center">收货人信息</h5>
                <table class="table table-borderless">
                    <tr class="row">
                        <td class="offset-1 col-4">收货人：</td>
                        <td class="col-6">${reveiverName}</td>
                    </tr>
                    <tr class="row">
                        <td class="offset-1 col-4">地址：</td>
                        <td class="col-6">${reveiverDetail}</td>
                    </tr>
                    <tr class="row">
                        <td class="offset-1 col-4">手机号：</td>
                        <td class="col-6">${reveiverPhone}</td>
                    </tr>
                </table>

            </div>

            <div class="col">
                <h5 class="text-center">配送信息</h5>
                <table class="table table-borderless">
                    <tr class="row">
                        <td class="offset-1 col-5">配送方式：</td>
                        <td class="col-6">xxx</td>
                    </tr>
                    <tr class="row">
                        <td class="offset-1 col-5">期望送货日期：</td>
                        <td class="col-6">2020-12-05</td>
                    </tr>
                    <tr class="row">
                        <td class="offset-1 col-5">期望配送时间：</td>
                        <td class="col-6">2020-12-05</td>
                    </tr>
                </table>
            </div>

            <div class="col">
                <h5 class="text-center">付款信息</h5>
                <table class="table table-borderless">
                    <tr class="row">
                        <td class="offset-1 col-5">付款方式：</td>
                        <td class="col-6">在线支付</td>
                    </tr>
                    <tr class="row">
                        <td class="offset-1 col-5">付款时间：</td>
                        <td class="col-6">${orderDetail.create_time}</td>
                    </tr>
                </table>
            </div>

        </div>
    </div>
</main>


<!--                            卖家更新物流信息-->
<!-- Modal -->
<div class="modal fade" id="seller-update-express" tabindex="-1" role="dialog"
     aria-labelledby="exampleModalCenterTitle2" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalCenterTitle2">确认取消？</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="form-inline">
                    <div class="col-auto">
                        <select class="custom-select" id="inlineFormCustomSelect">
                            <option selected disabled>选择快递</option>
                            <option value="1">中通</option>
                            <option value="2">圆通</option>
                            <option value="3">顺丰</option>
                            <option value="4">邮政</option>
                            <option value="5">申通</option>
                            <option value="6">韵达</option>
                            <option value="7">天天</option>
                            <option value="8">其他</option>
                        </select>
                        <input type="text" class="form-control" id="inlineFormInputName2" placeholder="请输入运单号">
                    </div>
                </div>
                <div class="form-group" style="margin-top: 5px">
                    <label>备注信息：</label>
                    <input type="text" class="form-control">
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary">确认</button>
            </div>
        </div>
    </div>
</div>
<script src="link/jquery-3.5.1.js"></script>
<script src="link/bootstrap-4.5.0-dist/js/bootstrap.bundle.js"></script>


</body>
</html>