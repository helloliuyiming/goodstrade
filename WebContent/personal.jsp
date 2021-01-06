<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<c:if test="${requestScope.user==null}">
    <c:redirect url="personal">
        <c:param name="id" value="${param.id}"/>
    </c:redirect>
</c:if>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>个人中心</title>
    <!-- 响应式标签 -->
    <meta content="width=device-width, initial-scale=1, shrink-to-fit=no" name="viewport">
    <link href="link/bootstrap-4.5.0-dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="css/common.css" rel="stylesheet"/>
    <style>
        .unpaid-img-action-block {

        }

        .unpaid-img-action a {
            color: white;
            text-decoration: none;
        }

        .unpaid-img-action {
            visibility: hidden;
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            text-align: center
        }

        .manage-goods-outline-action {
            visibility: hidden;
        }

        .unpaid-img-action-block:hover .unpaid-img-action {
            visibility: visible;
        }

    </style>
</head>
<body class="container">

<div style="margin: 30px 0">
    <div class="card" style="width: 100%">
        <div class="card-header" style="width: 100%">
            <div class="row justify-content-between" style="width: 100%">
                <h5 class="col-5">我的信息</h5>
                <c:if test="${(requestScope.isSelf!=null)&&(requestScope.isSelf)}">
                    <button class="btn btn-outline-secondary">编辑</button>
                </c:if>
            </div>
        </div>
        <div class="card-body row">
            <img class="img-thumbnail col-3" src="${pageContext.request.contextPath}/images/${requestScope.user.image}"
                 style="object-fit: contain">
            <div class="col-6" style="margin-top: 10px">
                <div class="row">
                    <h5 class="col-6">${requestScope.user.username}</h5>
                    <span class="col-3">★★★★★</span>
                </div>
                <p>${requestScope.user.gender}</p>
                <c:if test="${(requestScope.isSelf!=null)&&(requestScope.isSelf)}">
                    <div class="row" style="width: 100%">
                        <p class="col-3">默认收获地址</p>
                    </div>
                    <c:choose>
                        <c:when test="${requestScope.defaultAddress!=null}">
                            <p><c:out value="${requestScope.defaultAddress}"/></p>
                            <button class="btn btn-primary" data-target="#change-default-address" data-toggle="modal"
                                    type="button">更改默认收货地址
                            </button>
                        </c:when>
                        <c:otherwise>
                            <button class="btn btn-primary" data-target="#add-address" data-toggle="modal"
                                    type="button">添加收货地址
                            </button>
                        </c:otherwise>
                    </c:choose>

                    <!-- 添加收货地址对话框 -->
                    <div aria-hidden="true" class="modal fade bd-example-modal-lg" id="add-address" role="dialog"
                         tabindex="-1">
                        <div class="modal-dialog modal-lg modal-dialog-centered">
                            <div class="modal-content">
                                <div class="modal-header">
                                    添加收获地址
                                </div>
                                <form action="addAddress" id="addAddressSubmit-" method="post">
                                    <input type="hidden" name="redirect" value="personal">
                                    <div class="modal-body">
                                        <label>地址</label>

                                        <div class="form-row">

                                            <div class="form-check-inline" id="select-address"><!-- container -->
                                                <select name="province" class="form-control" required></select>
                                                <!-- 省 -->
                                                <select name="city" class="form-control" required></select><!-- 市 -->
                                                <select name="district" class="form-control" required></select>
                                                <!-- 区 -->
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label for="inputAddress">详细地址</label>
                                            <input name="detail" class="form-control" id="inputAddress"
                                                   placeholder="xx街道xx小区xx号" type="text" required>
                                        </div>

                                        <div class="form-row">
                                            <div class="form-group col-md-6">
                                                <label for="inputEmail4">收件人</label>
                                                <input class="form-control" id="inputEmail4" type="text" name="name"
                                                       required>
                                            </div>
                                            <div class="form-group col-md-6">
                                                <label for="inputPassword4">联系电话</label>
                                                <input name="phone" class="form-control" id="inputPassword4"
                                                       placeholder="仅支持中国大陆+86手机号" type="text" required>
                                            </div>
                                        </div>
                                        <div class="form-check">
                                            <input name="isDefault" class="form-check-input" id="exampleCheck1"
                                                   type="checkbox">
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

                    <!-- 修改默认收货地址 -->
                    <div aria-hidden="true" class="modal fade bd-example-modal-lg" id="change-default-address"
                         role="dialog"
                         tabindex="-1">
                        <div class="modal-dialog modal-lg modal-dialog-centered">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button class="btn btn-primary rounded-pill" data-target="#add-address"
                                            data-toggle="modal"
                                            onclick="$('#change-default-address').modal('hide')" type="button">
                                        添加收货地址
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <div style="width: 80%;height: 400px;overflow-x:hidden;overflow-y: scroll; margin-left: 10%">
                                        <form action="changeDefault" method="post">

                                            <input type="hidden" name="redirect" value="personal"/>
                                            <c:forEach var="addr" items="${requestScope.address}">
                                            <div class="card">
                                                <div class="row">
                                                    <div class="col-1 offset-1 form-check">

                                                        <input class="form-control" type="radio" name="addrId"
                                                               value="${addr.id}" ${addr.isDefault ? 'checked' : ''}>
                                                    </div>
                                                    <div class="col-10">
                                                        <p>
                                                            <c:out value="${addr.province}"/>
                                                            <c:out value="${addr.city}"/>
                                                            <c:out value="${addr.district}"/>
                                                            <c:out value="${addr.detail}"/>
                                                        </p>
                                                        <p><c:out value="${addr.name}"/></p>
                                                        <p><c:out value="${addr.phone}"/></p>
                                                    </div>
                                                </div>
                                            </div>
                                            </c:forEach>

                                    </div>
                                </div>

                                <div class="modal-footer">
                                    <button class="btn btn-secondary" data-dismiss="modal" type="button">取消</button>
                                    <button class="btn btn-primary" type="submit">保存</button>
                                </div>
                                </form>
                            </div>
                        </div>
                    </div>

                </c:if>

                <p style="margin-top: 10px">
                    <c:choose>
                        <c:when test="${requestScope.user.introduction==null}">
                            这个用户懒死了，什么也没写。。。
                        </c:when>
                        <c:otherwise>
                            <c:out value="${requestScope.user.introduction}"/>
                        </c:otherwise>
                    </c:choose>
                </p>
            </div>

            <c:if test="${(requestScope.isSelf!=null)&&(requestScope.isSelf)}">
                <div class="col-3 card card-body">
                    <ul class="nav nav-pills mb-3" role="tablist">
                        <li class="nav-item">
                            <a class="nav-link active" id="pills-home-tab" data-toggle="pill" href="#pills-home"
                               role="tab" aria-controls="pills-home" aria-selected="true">消息</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" id="pills-profile-tab" data-toggle="pill" href="#pills-profile"
                               role="tab" aria-controls="pills-profile" aria-selected="false">收藏</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" id="pills-contact-tab" data-toggle="pill" href="#pills-contact"
                               role="tab" aria-controls="pills-contact" aria-selected="false">历史</a>
                        </li>
                    </ul>
                    <div class="tab-content" id="pills-tabContent" style="height: 250px">
                            <%--						消息--%>
                        <div class="tab-pane fade show active" id="pills-home" role="tabpanel"
                             aria-labelledby="pills-home-tab"
                             style="width: 100%;height: 100%;overflow-y: scroll;overflow-x: hidden">
                            <c:forEach var="message" items="${messages}">
                                <p class="text-truncate"><a href="message?id=${message.id}">${message.title}</a></p>
                            </c:forEach>
                            <c:choose>
                                <c:when test="${fn:length(requestScope.messages)==0}">你的收件箱空空的...</c:when>
                                <c:otherwise><p><a href="message-list.html">查看更多</a></p></c:otherwise>
                            </c:choose>
                        </div>
                            <%--						收藏--%>
                        <div class="tab-pane fade" id="pills-profile" role="tabpanel"
                             aria-labelledby="pills-profile-tab"
                             style="width: 100%;height: 100%;overflow-y: scroll;overflow-x: hidden">
                            <c:forEach var="collect" items="${collects}">
                                <div class="row" style="width: 100%">
                                    <img src="${pageContext.request.contextPath}/images/${collect.goods_image}"
                                         style="width: 100%;object-fit: contain" class="col-3">
                                    <p class="text-truncate col-9"><a href="goodsdetail?id=${collect.goods_id}">${collect.goods_name}</a></p>
                                </div>
                            </c:forEach>

                            <c:choose>
                                <c:when test="${fn:length(requestScope.collects)==0}">你的收藏空空的...</c:when>
                                <c:otherwise><p><a href="favorites.jsp">查看更多</a></p></c:otherwise>
                            </c:choose>

                        </div>
                            <%--						浏览历史--%>
                        <div class="tab-pane fade" id="pills-contact" role="tabpanel"
                             aria-labelledby="pills-contact-tab"
                             style="width: 100%;height: 100%;overflow-y: scroll;overflow-x: hidden">

                            <div class="row" style="width: 100%">
                                <img src="image/avatar.png" style="width: 100%;object-fit: contain" class="col-3">
                                <p class="text-truncate col-9"><a href="#">这是标题最多显示5条</a></p>
                            </div>

                            <div class="row" style="width: 100%">
                                <img src="image/avatar.png" style="width: 100%;object-fit: contain" class="col-3">
                                <p class="text-truncate col-9"><a href="#">这是标题最多显示5条</a></p>
                            </div>

                            <div class="row" style="width: 100%">
                                <img src="image/avatar.png" style="width: 100%;object-fit: contain" class="col-3">
                                <p class="text-truncate col-9"><a href="#">这是标题最多显示5条</a></p>
                            </div>
                            <p><a href="#">查看更多</a></p>
                        </div>
                    </div>
                </div>
            </c:if>

        </div>

    </div>

</div>

<c:if test="${(requestScope.isSelf!=null)&&(requestScope.isSelf)}">
    <div>
        <div class="card">
            <div class="card-header">
                我的订单
            </div>
            <div class="card-body row">
                <div aria-orientation="vertical" class="nav flex-column nav-pills col-2" id="v-pills-tab"
                     role="tablist">
                    <a aria-controls="unpaid" aria-selected="true" class="nav-link active" data-toggle="pill"
                       href="#unpaid"
                       id="unpaid-tab" role="tab">待付款<span class="badge badge-light"
                                                           style="margin-left: 8px"><c:if
                            test="${fn:length(unpaidOrder)>0}">${fn:length(unpaidOrder)}</c:if></span></a>
                    <a aria-controls="shipped" aria-selected="false" class="nav-link" data-toggle="pill" href="#shipped"
                       id="shipped-tab" role="tab">待收货<span class="badge badge-light"
                                                            style="margin-left: 8px"><c:if
                            test="${fn:length(unreceiveOrder)>0}">${fn:length(unreceiveOrder)}</c:if></span></a>
                    <a aria-controls="unremark" aria-selected="false" class="nav-link" data-toggle="pill"
                       href="#unremark"
                       id="unremark-tab" role="tab">待评价<span class="badge badge-light"
                                                             style="margin-left: 8px"><c:if
                            test="${fn:length(uncommentOrder)>0}">${fn:length(uncommentOrder)}</c:if></span></a>
                    <a aria-controls="after-sale" aria-selected="false" class="nav-link" data-toggle="pill"
                       href="#after-sale"
                       id="after-sale-tab" role="tab">退货/售后<span class="badge badge-light"
                                                                 style="margin-left: 8px"></span></a>
                    <a class="nav-link" href="order?userId=${user.userId}">全部订单</a>
                </div>
                <div class="tab-content col-10" id="v-pills-tabContent" style="max-height: 300px">
                    <div aria-labelledby="unpaid-tab" class="tab-pane fade show active" id="unpaid" role="tabpanel"
                         style="width: 100%;height: 100%">
                        <div class="row" style="width: 100%;height: 100%;overflow-x: scroll">

                            <c:forEach var="order" items="${unpaidOrder}">
                                <div class="col-2 card"
                                     style="width: 100%;height: 100%;padding: 5px;text-align: center">
                                    <div class="unpaid-img-action-block"
                                         style="width: 80%;height: 50%;position: relative;margin-left: 10%">
                                        <img class="img-thumbnail"
                                             src="${pageContext.request.contextPath}/images/${order.goods_image}"
                                             style="width: 100%;height: 100%">

                                        <div class="unpaid-img-action">
                                            <button class="btn btn-danger rounded-pill"
                                                    style="height: 35%;margin-top: 7%;margin-bottom: 8%"><a href="#"
                                                                                                            data-orderid="${order.order_id}">取消订单</a>
                                            </button>
                                            <button class="btn btn-success rounded-pill"
                                                    style="height: 35%;margin-top: 0;margin-bottom: 8%"><a
                                                    href="orderdetail?id=${order.order_id}">订单详情</a></button>
                                        </div>
                                    </div>
                                    <p class="text-truncate" style="margin-bottom: 5px"><a
                                            href="#">${order.goods_name}</a></p>
                                    <p style="margin-bottom: 5px">￥${order.money}</p>
                                    <button class="btn btn-primary"><a style="color: white" href="payment?ids=${order.order_id}"> 去付款</a></button>
                                </div>
                            </c:forEach>

                            <c:if test="${fn:length(unpaidOrder)==null||fn:length(unpaidOrder)==0}">
                                没有未付款的订单哦！！！
                            </c:if>

                        </div>
                    </div>

                    <div aria-labelledby="shipped-tab" class="tab-pane fade" id="shipped" role="tabpanel"
                         style="width: 100%;height: 100%">
                        <div style="width: 100%;height: 100%;overflow-y: scroll;padding: 5px;overflow-x: hidden">

                            <c:forEach var="order" items="${unreceiveOrder}">
                                <div class="card">
                                    <div class="card-body row">
                                        <div class="col-2" style="height: 120px">
                                            <img src="${pageContext.request.contextPath}/images/${order.goods_image}"
                                                 style="width: 100%;height: 100%;">
                                        </div>
                                        <div class="col-9">
                                            <p class=" text-truncate"><a href="goodsdetail?id=${order.goods_id}">${order.goods_name}</a></p>
                                            <div class="row">
                                                <p class="col-10  text-truncate">最近一次物流更新信息</p>
                                                <a class="col-2" href="orderdetail?id=${order.order_id}">物流详情...</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                            <c:if test="${fn:length(unreceiveOrder)==null||fn:length(unreceiveOrder)==0}">
                                没有待收货的订单哦！！！
                            </c:if>

                        </div>
                    </div>

                    <div aria-labelledby="unremark-tab" class="tab-pane fade" id="unremark" role="tabpanel"
                         style="width: 100%;height: 100%">
                        <div class="row" style="width: 100%;height: 100%;overflow-x: scroll">

                            <c:forEach var="item" items="${uncommentOrder}">
                                <div class="col-2 card"
                                     style="width: 100%;height: 100%;padding: 5px;text-align: center">
                                    <div class="unpaid-img-action-block"
                                         style="width: 80%;height: 50%;position: relative;margin-left: 10%">
                                        <img class="img-thumbnail"
                                             src="${pageContext.request.contextPath}/images/${order.goods_image}"
                                             style="width: 100%;height: 100%">
                                        <div class="unpaid-img-action">
                                            <button class="btn btn-danger rounded-pill"
                                                    style="height: 35%;margin-top: 7%;margin-bottom: 8%"><a href="#">退货/售后</a>
                                            </button>
                                            <button class="btn btn-success rounded-pill"
                                                    style="height: 35%;margin-top: 0;margin-bottom: 8%"><a
                                                    href="orderdetail?id=${order.order_id}">订单详情</a></button>
                                        </div>
                                    </div>
                                    <p class="text-truncate" style="margin-bottom: 5px"><a
                                            href="#">${order.goods_name}</a>
                                    </p>
                                    <button class="btn btn-primary">去评价</button>
                                </div>
                            </c:forEach>
                            <c:if test="${fn:length(uncommentOrder)==null||fn:length(uncommentOrder)==0}">
                                没有待评价的订单哦！！！
                            </c:if>

                        </div>
                    </div>
                    <div aria-labelledby="after-sale-tab" class="tab-pane fade" id="after-sale" role="tabpanel"
                         style="width: 100%;height: 100%">
                        <div style="width: 100%;height: 100%;overflow-y: scroll;padding: 5px;overflow-x: hidden">

                            <c:forEach var="order" items="${suspendOrder}">
                                <div class="card">
                                    <div class="card-body row">
                                        <div class="col-2" style="height: 120px">
                                            <img src="${pageContext.request.contextPath}/images/${order.goods_image}"
                                                 style="width: 100%;height: 100%;">
                                        </div>
                                        <div class="col-9">
                                            <p class=" text-truncate"><a href="#">${order.goods_name}</a></p>
                                            <p>2020-02-02</p>
                                            <div class="row">
                                                <p class="col-10  text-truncate">最近一次进度更新信息</p>
                                                <a class="col-2" href="order-detail.html">售后详情...</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                            <c:if test="${fn:length(suspendOrder)==null||fn:length(suspendOrder)==0}">
                                太棒了，所有订单都是正常的！！！
                            </c:if>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</c:if>


<div>

    <div class="card" style="margin-top: 30px">
        <div class="card-header">
            闲置
        </div>
        <div class="card-body">
            <div class="row justify-content-between" style="margin: 0">

                <div class="col-9">

                    <ul class="nav nav-pills mb-3" id="pills-tab" role="tablist">
                        <!--                        下面的li标签只有自己看自己个人中心才出现-->
                        <c:if test="${(requestScope.isSelf!=null)&&(requestScope.isSelf)}">
                            <li class="nav-item">
                                <a aria-controls="pre-sale" aria-selected="true" class="nav-link active"
                                   data-toggle="pill"
                                   href="#pre-sale"
                                   id="pre-sale-tab" role="tab">已拍</a>
                            </li>
                        </c:if>


                        <li class="nav-item">
                            <a aria-controls="on-sale" aria-selected="false"
                               class="nav-link ${requestScope.isSelf ?'':'active'}" data-toggle="pill"
                               href="#on-sale"
                               id="on-sale-tab" role="tab">在售</a>
                        </li>
                        <li class="nav-item">
                            <a aria-controls="completion" aria-selected="false" class="nav-link" data-toggle="pill"
                               href="#completion"
                               id="completion-tab" role="tab">完成</a>
                        </li>
                    </ul>

                    <div class="tab-content">
                        <c:if test="${(requestScope.isSelf!=null)&&(requestScope.isSelf)}">
                            <div aria-labelledby="pre-tab"
                                 class="tab-pane fade row show active row-cols-2 justify-content-around" id="pre-sale"
                                 role="tabpanel">

                                    <%--卖家未发货区域--%>
                                <c:forEach var="order" items="${unsentOrder}">
                                    <div class="col" style="display: inline-block;width: 48%;margin: 0;padding: 0">
                                        <div class="card manage-goods-outline">
                                            <div class="row card-body ">
                                                <a class="col-3 align-self-center" href="#"
                                                   style="width: 100%;padding: 0;position: relative">
                                                    <img src="${pageContext.request.contextPath}/images/${order.goods_image}"
                                                         style="width: 100%">
                                                    <img src="image/已拍下.svg"
                                                         style="width: 100%;position: absolute;right: 0;border: 0">
                                                </a>
                                                <div class="col-9">
                                                    <a href="#">${order.goods_name}</a>
                                                    <div class="row">
                                                        <div class="col-7">
                                                            <p style="margin: 0">
                                                                成交价格:<span>${order.money+order.freight}</span>
                                                            </p>
                                                            <a href="orderdetail?id=${order.order_id}">详情</a>
                                                        </div>
                                                        <div class="col-5" style="text-align: center">
                                                            <button type="button" class="btn-sm btn btn-outline-danger"
                                                                    data-toggle="modal"
                                                                    data-target="#seller-cancel-order">取消订单
                                                            </button>
                                                            <button type="button" class="btn-sm btn btn-outline-primary"
                                                                    data-toggle="modal"
                                                                    data-target="#seller-update-express"
                                                                    data-orderid="${order.order_id}"
                                                                    style="margin-top: 5px">已发货
                                                            </button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>


                                    <%--买家未付款区域--%>
                                <c:forEach var="order" items="${customerUnpaidOrder}">
                                    <div class="col" style="display: inline-block;width: 48%;margin: 0;padding: 0">
                                        <div class="card manage-goods-outline">
                                            <div class="row card-body ">
                                                <a class="col-3 align-self-center" href="#"
                                                   style="width: 100%;padding: 0;position: relative">
                                                    <img src="${pageContext.request.contextPath}/images/${order.goods_image}"
                                                         style="width: 100%">
                                                    <img src="image/已拍下.svg"
                                                         style="width: 100%;position: absolute;right: 0;border: 0">
                                                </a>
                                                <div class="col-9">
                                                    <a href="#">${order.goods_name}</a>
                                                    <div class="row">
                                                        <div class="col-7">
                                                            <p style="margin: 0">
                                                                成交价格:<span>${order.money+order.freight}</span>
                                                            </p>
                                                            <a href="orderdetail?id=${order.order_id}">详情</a>
                                                        </div>
                                                        <div class="col-5" style="text-align: center">
                                                            <p style="margin: 0;width: 100%;text-align: center;font-size: 14px">
                                                                等待买家付款</p>
                                                            <button type="button" class="btn-sm btn btn-outline-danger"
                                                                    data-toggle="modal"
                                                                    data-target="#seller-cancel-order">取消订单
                                                            </button>
                                                        </div>
                                                    </div>

                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>


                                    <%--	买家未收货区域--%>
                                <c:forEach var="order" items="${customerUnreceivedOrder}">
                                    <div class="col" style="display: inline-block;width: 48%;margin: 0;padding: 0">
                                        <div class="card manage-goods-outline">
                                            <div class="row card-body ">
                                                <a class="col-3 align-self-center" href="#"
                                                   style="width: 100%;padding: 0;position: relative">
                                                    <img src="${pageContext.request.contextPath}/images/${order.goods_image}"
                                                         style="width: 100%">
                                                    <img src="image/已拍下.svg"
                                                         style="width: 100%;position: absolute;right: 0;border: 0">
                                                </a>
                                                <div class="col-9">
                                                    <a href="#">${order.goods_name}</a>
                                                    <div class="row">
                                                        <div class="col-7">
                                                            <p style="margin: 0">
                                                                成交价格:<span>${order.money+order.freight}</span>
                                                            </p>
                                                            <a href="orderdetail?id=${order.order_id}">详情</a>
                                                        </div>
                                                        <div class="col-5" style="text-align: center">
                                                            <p style="margin: 0;width: 100%;text-align: center;font-size: 14px">
                                                                等待买家收获</p>
                                                            <button type="button" class="btn-sm btn btn-outline-success"
                                                                    data-toggle="modal"
                                                                    data-target="#seller-cancel-order">查看物流
                                                            </button>
                                                        </div>
                                                    </div>

                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>

                                <c:if test="${fn:length(unsentOrder)==0&&fn:length(customerUnpaidOrder)==0&&fn:length(customerUnreceivedOrder)==0}">
                                    最近都没有新的订单哦!!!
                                </c:if>
                            </div>


                        </c:if>

                        <div aria-labelledby="on-sale-tab"
                             class="tab-pane fade row row-cols-2 justify-content-around ${requestScope.isSelf?'':'show active'}"
                             id="on-sale" role="tabpanel">

                            <c:forEach var="goods" items="${onSale}">
                                <div class="col-5" style="display: inline-block">
                                    <div class="card manage-goods-outline">
                                        <div class="row card-body ">
                                            <a class="col-3 align-self-center" href="#" style="width: 100%;padding: 0">
                                                <img src="${pageContext.request.contextPath}/images/${goods.goodsImage}"
                                                     style="width: 100%">
                                            </a>

                                            <div class="col-8">
                                                <a href="goodsdetail?id=${goods.goodsId}"><p>${goods.goodsName}</p></a>
                                                <p>${goods.goodsPrice}</p>
                                            </div>
                                        </div>
                                        <c:if test="${(requestScope.isSelf!=null)&&(requestScope.isSelf)}">
                                            <div class="manage-goods-outline-action btn-group-vertical"
                                                 style="position:absolute;right: 0;top: 0;bottom: 0">
                                                <button class="btn btn-danger" type="button">删除</button>
                                                <button class="btn btn-success" type="button">编辑</button>
                                                <button class="btn btn-secondary" type="button">下架</button>
                                            </div>
                                        </c:if>
                                    </div>
                                </div>
                            </c:forEach>

                            <c:if test="${fn:length(onSale)==0}">
                                你没有正在出售中的商品哦!!!
                            </c:if>

                        </div>

                        <div aria-labelledby="completion-tab"
                             class="tab-pane fade row row-cols-2 justify-content-around" id="completion"
                             role="tabpanel">

                            <c:forEach var="order" items="${finishedOrder}">
                                <div class="col-5" style="display: inline-block">
                                    <div class="card manage-goods-outline">
                                        <div class="row card-body ">
                                            <a class="col-3 align-self-center" href="#"
                                               style="width: 100%;padding: 0;position: relative">
                                                <img src="${pageContext.request.contextPath}/images/${order.goods_image}"
                                                     style="width: 100%">
                                                <img src="image/sold.svg"
                                                     style="width: 100%;position: absolute;right: 0;border: 0">
                                            </a>
                                            <div class="col-8">
                                                <a href="goodsdetail?id=${order.goods_id}">${order.goods_name}</a>

                                                <p style="margin: 0">成交价格:<span>${order.money+order.freight}</span></p>
                                                <c:if test="${(requestScope.isSelf!=null)&&(requestScope.isSelf)}">
                                                    <a href="#">详情</a>
                                                </c:if>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>

                            <c:if test="${fn:length(finishedOrder)==0}">
                                还没有卖出过商品哦!!!
                            </c:if>

                        </div>

                    </div>


                </div>

                <div class="col-3 card">
                    <h5 style="margin: 10px">最近评价</h5>
                    <hr style="margin-top: 0">
                    <div>

                    </div>

                </div>
            </div>

        </div>
    </div>


    <!--                            卖家取消订单模态框-->
    <!-- Modal -->
    <div class="modal fade" id="seller-cancel-order" tabindex="1" role="dialog">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">确认取消？</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label>请附上取消理由</label>
                        <input type="email" class="form-control">
                        <small class="form-text text-muted">我们非常不提倡无缘无故取消订单，因为这可能会影响你的信誉！！！</small>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary">确认</button>
                </div>
            </div>
        </div>
    </div>

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


</div>

<footer style="margin-bottom: 50px"></footer>
<script src="link/jquery-3.5.1.js"></script>
<script src="link/bootstrap-4.5.0-dist/js/bootstrap.bundle.js"></script>
<script src="link/distpicker/distpicker.min.js"></script>
<script src="js/common.js"></script>
<script>
    $('#select-address').distpicker({
        province: '--请选择省份--',
        city: '--请选择市--',
        district: '--请选择区/县--'
    });

    $('#addAddressSubmit').on('submit', function (event) {
        console.log("add Address 被拦截")
        event.preventDefault()
        addAddressPost()
        return false
    })

    function addAddressPost() {
        console.log("form:")
        console.log($('#addAddressSubmit').serialize())
        $.ajax({
            type: "get",
            url: "addAddress",
            data: $('#addAddressSubmit').serialize(),
        }).success(function (message) {
            console.log(message)
        }).fail(function (err) {
                console.log(err)
            }
        )
        return false
    }
</script>
</body>
</html>