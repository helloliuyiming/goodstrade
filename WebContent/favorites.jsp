<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>我的收藏</title>
    <!-- 响应式标签 -->
    <meta content="width=device-width, initial-scale=1, shrink-to-fit=no" name="viewport">
    <link href="link/bootstrap-4.5.0-dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="css/common.css" rel="stylesheet">
    <script src="link/jquery-3.5.1.js"></script>
    <script src="link/bootstrap-4.5.0-dist/js/bootstrap.bundle.js"></script>
    <script src="js/goods.js"></script>
    <style>
        html, body {
            width: 100%;
            height: 100%;
            overflow: hidden;
        }

        .favorites-item:hover .favorites-cancel {
            visibility: visible;
        }

        .favorites-cancel {
            visibility: hidden;
        }

    </style>
</head>
<body class="container">
<div class="card"
     style="height: 90%;width: 100%;margin-top: 3%;display: flex;flex-flow: row nowrap;flex-direction: column">
    <h2 class="card-header" style="text-align: center;">我的收藏</h2>
    <div class="card-body row justify-content-between" style="flex-grow: 1;height: 100%">

        <div id="favorites-left" class="col-6" style="height: 100%">
            <div style="height: 88%">
                <table class="table" style="height: 100%; overflow-y: scroll;display: block" align="center">
                    <c:if test="${collectGoods!=null or collectGoods.size()!=0}">
                        <c:forEach var="goods" items="${collectGoods}">
                            <tr class="favorites-item">
                                <td>
                                    <input class="align-self-center choose" value="${goods.getGoodsId()}"
                                           type="checkbox">
                                    <input type="hidden" value="${goods.getGoodsName() }"/>
                                    <input type="hidden" value="${goods.getGoodsPrice() }"/>
                                </td>

                                <td><img src="images/${goods.getGoodsImage()}" style="width: 80px;height: 80px"/></td>
                                <td><p class="text-truncate text-wrap goods-outline-title"><a
                                        href="goodsdetail?id=${goods.getGoodsId() }">${goods.getGoodsName() }</a>
                                </p></td>
                                <td class="d-flex flex-column align-items-start">
                                    <button type="button" value="${goods.getGoodsId() }"
                                            class="close favorites-cancel align-self-end deleteoneGoods"
                                            aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                    <p class="">￥${goods.getGoodsPrice() }</p>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:if>
                    <c:if test="${collectGoods==null }">
                        <h2>您还未收藏任何商品,<a href="index.jsp">去收藏</a></h2>
                    </c:if>
                </table>
            </div>
            <hr style="height: 2%">
            <c:if test="${collectGoods!=null }">
                <div class="row" style="height: 10%">
                    <div class="form-check col-3 offset-1">
                        <input type="checkbox" id="checkAll" class="form-check-input">
                        <label class="form-check-label">全选</label>
                    </div>
                    <div class="col-4 offset-3">
                        <button id="deleteMoreGoods">清除选中的商品</button>
                    </div>
                </div>
            </c:if>
        </div>
        <!-- 选中商品 -->
        <div id="favorites-right" class="col-6" style="height: 100%">
            <h5 style="height: 8%;margin: 0">选中商品</h5>
            <!-- 展示选中的商品 -->
            <div class="insert" style="width: 100%;height: 80%;overflow-y: scroll;overflow-x: hidden">
            </div>

            <hr style="height: 2%">
            <c:if test="${collectGoods!=null }">
                <div style="height: 10%;text-align: right">
                    <button class="btn btn-outline-info rounded-pill todoOrder" style="padding: 2px 8px">下订单</button>
                </div>
            </c:if>
        </div>
    </div>
</div>

<script>

</script>
</body>
</html>