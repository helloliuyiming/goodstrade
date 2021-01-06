<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>主页</title>
    <!-- 响应式标签 -->
    <meta content="width=device-width, initial-scale=1, shrink-to-fit=no" name="viewport">
    <link href="link/bootstrap-4.5.0-dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="css/common.css" rel="stylesheet"/>
    <script src="link/jquery-3.5.1.js"></script>
    <script src="link/bootstrap-4.5.0-dist/js/bootstrap.bundle.js"></script>
    <script src="js/common.js"></script>
    <script type="text/javascript" src="js/goods.js"></script>
    <style>
        .goods-outline {
            padding: 8px 15px;
        }
    </style>
</head>
<body>
<!-- 初始化首页信息 -->
<c:if test="${hotGoodsList==null}">
    <jsp:forward page="goods/getAllGoods"></jsp:forward>
</c:if>
<!-- 搜索头 -->
<header class="container-fluid">
    <div class="row justify-content-around" id="header-title">
        <a class="col-1 offset-1" href="index.jsp">
            <img alt="logo" class="img-thumbnail" src="image/logo.jpg">
        </a>
        <div class="input-group offset-1 col-9" id="search-block">

            <div class="row container-lg" id="search-input">

                <div class="col-8">
                    <input aria-describedby="button-addon2" aria-label="Recipient's username" class="form-control"
                           id="searchByKey"
                           placeholder="输入关键字搜索"
                           type="text">
                </div>

                <div class="input-group-append col-3">
                    <button class="btn btn-outline-secondary search_btn" id="search" type="submit">搜索</button>
                </div>
            </div>
            <!-- 搜索历史 -->
            <div class="row container-lg" id="search-history">
                <ul class="nav">
                    <li class="nav-item">
                        <c:if test="${history1!=null }">
                            <a class="nav-link active" href="goods/searchByName?goodsName=${history1 }">${history1}</a>
                        </c:if>
                    </li>
                    <li class="nav-item">
                        <c:if test="${history2!=null }">
                            <a class="nav-link active" href="goods/searchByName?goodsName=${history2 }">${history2}</a>
                        </c:if>
                    </li>
                    <li class="nav-item">
                        <c:if test="${history3!=null }">
                            <a class="nav-link active" href="goods/searchByName?goodsName=${history3 }">${history3}</a>
                        </c:if>
                    </li>
                </ul>
            </div>

        </div>

    </div>
</header>

<main class="container-fluid">
    <div class="row justify-content-around">
        <!-- 类别选择 -->
        <aside class="col-2" id="category">
            <div>
                <ul class="nav">
                    <li class="nav-item">
                        <a href="goods/search?sort=家用电器">家用电器</a>
                    </li>
                    <li class="nav-item">
                        <a href="goods/search?sort=手机">手机</a>
                        <span class="cate_menu_line">/</span>
                        <a href="goods/search?sort=平板">平板</a>
                        <span class="cate_menu_line">/</span>
                        <a href="goods/search?sort=数码">数码</a>
                    </li>
                    <li class="nav-item">
                        <a href="goods/search?sort=电脑">电脑</a>
                        <span class="cate_menu_line">/</span>
                        <a href="goods/search?sort=办公">办公</a>
                    </li>
                    <li class="nav-item">
                        <a href="goods/search?sort=家居">家居</a>
                        <span class="cate_menu_line">/</span>
                        <a href="goods/search?sort=家具">家具</a>
                        <span class="cate_menu_line">/</span>
                        <a href="goods/search?sort=家装">家装</a>
                        <span class="cate_menu_line">/</span>
                        <a href="goods/search?sort=厨具">厨具</a>
                    </li>
                    <li class="nav-item">
                        <a href="goods/search?sort=男装">男装</a>
                        <span class="cate_menu_line">/</span>
                        <a href="goods/search?sort=女装">女装</a>
                        <span class="cate_menu_line">/</span>
                        <a href="goods/search?sort=童装">童装</a>
                        <span class="cate_menu_line">/</span>
                        <a href="goods/search?sort=内衣">内衣</a>
                    </li>
                    <li class="nav-item">
                        <a href="goods/search?sort=美妆">美妆</a>
                        <span class="cate_menu_line">/</span>
                        <a href="goods/search?sort=个护清洁">个护清洁</a>
                        <span class="cate_menu_line">/</span>
                        <a href="goods/search?sort=宠物">宠物</a>
                    </li>
                    <li class="nav-item">
                        <a href="goods/search?sort=女鞋">女鞋</a>
                        <span class="cate_menu_line">/</span>
                        <a href="goods/search?sort=箱包">箱包</a>
                        <span class="cate_menu_line">/</span>
                        <a href="goods/search?sort=钟表">钟表</a>
                        <span class="cate_menu_line">/</span>
                        <a href="goods/search?sort=珠宝">珠宝</a>
                    </li>
                    <li class="nav-item">
                        <a href="goods/search?sort=男鞋">男鞋</a>
                        <span class="cate_menu_line">/</span>
                        <a href="goods/search?sort=运动">运动</a>
                        <span class="cate_menu_line">/</span>
                        <a href="goods/search?sort=户外">户外</a>
                    </li>
                    <li class="nav-item">
                        <a href="goods/search?sort=房产">房产</a>
                        <span class="cate_menu_line">/</span>
                        <a href="goods/search?sort=汽车">汽车</a>
                        <span class="cate_menu_line">/</span>
                        <a href="goods/search?sort=汽车用品">汽车用品</a>
                    </li>
                    <li class="nav-item">
                        <a href="goods/search?sort=母婴">母婴</a>
                        <span class="cate_menu_line">/</span>
                        <a href="goods/search?sort=玩具乐器">玩具乐器</a>
                    </li>
                    <li class="nav-item">
                        <a href="goods/search?sort=食品">食品</a>
                        <span class="cate_menu_line">/</span>
                        <a href="goods/search?sort=酒类">酒类</a>
                        <span class="cate_menu_line">/</span>
                        <a href="goods/search?sort=生鲜">生鲜</a>
                        <span class="cate_menu_line">/</span>
                        <a href="goods/search?sort=特产">特产</a>
                    </li>
                    <li class="nav-item">
                        <a href="goods/search?sort=艺术">艺术</a>
                        <span class="cate_menu_line">/</span>
                        <a href="goods/search?sort=礼品鲜花">礼品鲜花</a>
                        <span class="cate_menu_line">/</span>
                        <a href="goods/search?sort=农资绿植">农资绿植</a>
                    </li>
                    <li class="nav-item">
                        <a href="goods/search?sort=医药保健">医药保健</a>
                        <span class="cate_menu_line">/</span>
                        <a href="goods/search?sort=计生情趣">计生情趣</a>
                    </li>
                    <li class="nav-item">
                        <a href="goods/search?sort=图书">图书</a>
                        <span class="cate_menu_line">/</span>
                        <a href="goods/search?sort=文娱">文娱</a>
                        <span class="cate_menu_line">/</span>
                        <a href="goods/search?sort=教育">教育</a>
                        <span class="cate_menu_line">/</span>
                        <a href="goods/search?sort=电子书">电子书</a>
                    </li>
                    <li class="nav-item">
                        <a href="goods/search?sort=机票">机票</a>
                        <span class="cate_menu_line">/</span>
                        <a href="goods/search?sort=酒店">酒店</a>
                        <span class="cate_menu_line">/</span>
                        <a href="goods/search?sort=旅游">旅游</a>
                        <span class="cate_menu_line">/</span>
                        <a href="goods/search?sort=生活">生活</a>
                    </li>
                    <li class="nav-item">
                        <a href="goods/search?sort=理财">理财</a>
                        <span class="cate_menu_line">/</span>
                        <a href="goods/search?sort=众筹">众筹</a>
                        <span class="cate_menu_line">/</span>
                        <a href="goods/search?sort=白条">白条</a>
                        <span class="cate_menu_line">/</span>
                        <a href="goods/search?sort=保险">保险</a>
                    </li>
                    <li class="nav-item">
                        <a href="goods/search?sort=安装">安装</a>
                        <span class="cate_menu_line">/</span>
                        <a href="goods/search?sort=维修">维修</a>
                        <span class="cate_menu_line">/</span>
                        <a href="goods/search?sort=清洗">清洗</a>
                        <span class="cate_menu_line">/</span>
                        <a href="goods/search?sort=二手">二手</a>
                    </li>
                    <li class="nav-item">
                        <a href="goods/search?sort=工业品">工业品</a>
                    </li>
                </ul>
            </div>
        </aside>

        <!-- 轮播图 -->
        <div class="col-7">
            <div class="carousel slide" data-ride="carousel" id="carouselExampleCaptions">
                <ol class="carousel-indicators">
                    <li class="active" data-slide-to="0" data-target="#carouselExampleCaptions"></li>
                    <li data-slide-to="1" data-target="#carouselExampleCaptions"></li>
                    <li data-slide-to="2" data-target="#carouselExampleCaptions"></li>
                </ol>
                <div class="carousel-inner">
                    <div class="carousel-item active">
                        <img alt="..." class="d-block w-100" src="image/1.jpg">
                        <div class="carousel-caption d-none d-md-block">
                            <h5>First slide label</h5>
                            <p>Nulla vitae elit libero, a pharetra augue mollis interdum.</p>
                        </div>
                    </div>
                    <div class="carousel-item">
                        <img alt="..." class="d-block w-100" src="image/2.jpg">
                        <div class="carousel-caption d-none d-md-block">
                            <h5>Second slide label</h5>
                            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
                        </div>
                    </div>
                    <div class="carousel-item">
                        <img alt="..." class="d-block w-100" src="image/3.jpg">
                        <div class="carousel-caption d-none d-md-block">
                            <h5>Third slide label</h5>
                            <p>Praesent commodo cursus magna, vel scelerisque nisl consectetur.</p>
                        </div>
                    </div>
                </div>
                <a class="carousel-control-prev" data-slide="prev" href="#carouselExampleCaptions" role="button">
                    <span aria-hidden="true" class="carousel-control-prev-icon"></span>
                    <span class="sr-only">Previous</span>
                </a>
                <a class="carousel-control-next" data-slide="next" href="#carouselExampleCaptions" role="button">
                    <span aria-hidden="true" class="carousel-control-next-icon"></span>
                    <span class="sr-only">Next</span>
                </a>
            </div>
        </div>

        <!-- 个人中心 -->
        <aside class="col-2" style="padding: 0">
            <div align="center" style="height: 100%;position:relative;">
                <div style="height: 100%">
                    <!--如果登录之后则显示名字-->
                    <c:if test="${user!=null }">
                        <a href="personal.jsp">
                            <img class="img-thumbnail rounded-circle" id="side-avatar" src="images/${user.image}">
                            <h6><c:out value="${user.getUsername()}"></c:out></h6>
                        </a>
                    </c:if>

                    <!--未登录显示登录和注册入口-->
                    <c:if test="${user==null }">
                        <a href="#">
                            <img class="img-thumbnail rounded-circle" id="side-avatar" src="image/avatar.png">
                        </a>
                        <span>
							<a href="login.jsp">登录</a> | <a href="register.jsp">注册</a>
						</span>
                    </c:if>
                    <hr>
                    <div id="info-panel">
                        <div class="row justify-content-between" style="height: 20%">
                            <p class="font-weight-bold col-6">我的消息</p>
                            <a class="col-4" href="#" style="text-decoration: none;color: black">更多</a>
                        </div>
                        <ul class="info-list">
                            <li class="info-item"><a class="d-inline-block text-truncate" href="#">一封你的新的邮件请注意查收shoushoushou</a>
                            </li>
                        </ul>
                    </div>

                    <div class="row">
                        <div class="col-3" style="padding: 0;position:relative;">
                            <a href="personal.jsp#unpaid-tab">
                                <img src="image/待付款.svg" style="width: 80%">
                                <span style="font-size: 14px;color: black">待付款</span>
                            </a>
                            <span class="badge badge-pill badge-danger order-info-badge">0</span>
                        </div>

                        <div class="col-3" style="padding: 0;position:relative;">
                            <a href="personal.jsp#shipped-tab">
                                <img src="image/待收货.svg" style="width: 80%">
                                <span style="font-size: 14px;color: black">待收货</span>
                            </a>
                            <span class="badge badge-pill badge-danger order-info-badge">1</span>
                        </div>

                        <div class="col-3" style="padding: 0;position:relative;">
                            <a href="personal.jsp#unremark-tab">
                                <img src="image/待评价.svg" style="width: 80%">
                                <span style="font-size: 14px;color: black">待评价</span>
                            </a>
                            <span class="badge badge-pill badge-danger order-info-badge">10</span>
                        </div>

                        <div class="col-3" style="padding: 0;position:relative;">
                            <a href="personal.jsp#after-sale-tab">
                                <img src="image/退货售后.svg" style="width: 80%">
                                <span style="font-size: 14px;color: black">退货售后</span>
                            </a>
                            <span class="badge badge-pill badge-danger order-info-badge">2</span>
                        </div>
                    </div>
                    <!-- 用户登录后可发布闲置 -->
                    <c:if test="${user!=null }">
                        <button class="btn btn-primary rounded-pill"
                                style="position: absolute;bottom: 20px;width: 80%;left: 10%"
                                type="button">
                            <a href="addgoods.jsp" style="color:white;">发布闲置/需求</a>
                        </button>
                    </c:if>
                </div>

                <!--						<div style="height: 100%;position: absolute;top: 0;left: 0;width: 100%;height: 100%;background: #0c5460">-->

                <!--						</div>-->
            </div>

        </aside>

    </div>

    <!-- 商品展示 -->
    <div id="block-index">
        <!-- 爆款商品 -->
        <div class="row justify-content-between" style="width: 100%;margin-top:10px;margin-bottom:5px;">
            <h3 class="col-3">爆款</h3>
            <p class="col-2" style="font-size: 18px;text-align: right"><a href="goods/baokuanGoods">更多</a></p>
        </div>
        <!-- 爆款商品展示 -->
        <div class="row row-cols-6 justify-content-around home-block goods-block">
            <c:if test="${hotGoodsList!=null}">
                <c:forEach var="hotGoods" items="${hotGoodsList}" begin="0" end="9">
                    <div class="goods-outline col">
                        <div class="goods-outline-content">
                            <a href="goodsdetail?id=${hotGoods.getGoodsId() }">
                                <div class="goods-outline-info">

                                    <img class="figure-img img-fluid rounded mx-auto d-block goods-outline-img"
                                         style="width:196px;height:196px;"
                                         src="${basePath}images/${hotGoods.goodsImage}">
                                    <p class="text-truncate text-wrap goods-outline-title">
                                        <span class="tag">爆款</span>${hotGoods.getGoodsName()}
                                    </p>
                                    <h4 class="goods-outline-price">￥${hotGoods.getGoodsPrice()}</h4>
                                    <p class="goods-outline-origin">${hotGoods.getGoodsBuytime()}购于${hotGoods.getGoodsBuyof()}</p>

                                </div>
                            </a>

                            <button aria-label="Close" class="close btn-unlike" type="button">
                                <span aria-hidden="true">&times;</span>
                            </button>

                            <div align="center" class="rounded goods-outline-like">
                                <button class="btn btn-outline-success btn-find-similar">
                                    <a href="goods/search?sort=${hotGoods.getGoodsSort()}">寻找相似</a>
                                </button>

                                <!-- 用户未登录不能收藏 -->
                                <c:if test="${user==null}">
                                    <button class="btn btn-outline-danger btn-favorite collect1">
                                        <a><span>收藏</span></a>
                                    </button>
                                </c:if>

                                <c:if test="${user != null}">
                                    <c:if test="${collectGid!=null }">
                                        <c:set value="${0}" var="argument"></c:set>
                                        <!-- 用户已登录，若商品已收藏则显示颜色，未收藏不显示 -->
                                        <c:forEach var="gid" items="${collectGid}">
                                            <c:if test="${hotGoods.getGoodsId()==gid }">
                                                <c:set value="${gid }" var="argument"></c:set>
                                                <button value="${hotGoods.getGoodsId()}"
                                                        class="btn btn-outline-danger btn-favorite deleteCollect">
                                                    <a href="#"><span>已收藏</span></a>
                                                </button>
                                            </c:if>
                                        </c:forEach>
                                    </c:if>
                                    <c:if test="${collectGid == null or argument == 0}">
                                        <button value="${hotGoods.getGoodsId()}"
                                                class="btn btn-outline-danger btn-favorite addCollect">
                                            <a href="#"><span>收藏</span></a>
                                        </button>
                                    </c:if>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </c:forEach></c:if>
        </div>


        <!-- 新品 -->
        <div class="row justify-content-between" style="width: 100%;margin-top:10px;margin-bottom:5px;">
            <h3 class="col-3">新品</h3>
            <p class="col-2" style="font-size: 18px;text-align: right"><a href="goods/xinpinGoods">更多</a></p>
        </div>
        <!-- 新品展示 -->
        <div class="row row-cols-6 justify-content-around home-block goods-block">
            <c:if test="${newGoodsList!=null}">
                <c:forEach var="newGoods" items="${newGoodsList}" begin="0" end="9">
                    <div class="goods-outline col">
                        <div class="goods-outline-content">
                            <a href="goodsdetail?id=${newGoods.getGoodsId() }">
                                <div class="goods-outline-info">

                                    <img class="figure-img img-fluid rounded mx-auto d-block goods-outline-img"
                                         style="width:196px;height:196px;"
                                         src="${basePath}images/${newGoods.goodsImage}">
                                    <p class="text-truncate text-wrap goods-outline-title"><span
                                            class="tag">新品</span>${newGoods.getGoodsName()}</p>
                                    <h4 class="goods-outline-price">￥${newGoods.getGoodsPrice()}</h4>
                                    <p class="goods-outline-origin">${newGoods.getGoodsBuytime()}购于${newGoods.getGoodsBuyof()}</p>
                                </div>
                            </a>

                            <button aria-label="Close" class="close btn-unlike" type="button">
                                <span aria-hidden="true">&times;</span>
                            </button>

                            <div align="center" class="rounded goods-outline-like">
                                <button class="btn btn-outline-success btn-find-similar">
                                    <a href="goods/search?sort=${newGoods.getGoodsSort()}">寻找相似</a>
                                </button>

                                <!-- 用户未登录不能收藏 -->
                                <c:if test="${user==null}">
                                    <button class="btn btn-outline-danger btn-favorite collect1">
                                        <a href=""><span>收藏</span></a>
                                    </button>
                                </c:if>

                                <c:if test="${user != null}">
                                    <c:if test="${collectGid!=null }">
                                        <c:set value="${0}" var="argument"></c:set>
                                        <!-- 用户已登录，若商品已收藏则显示颜色，未收藏不显示 -->
                                        <c:forEach var="gid" items="${collectGid}">
                                            <c:if test="${newGoods.getGoodsId()==gid }">
                                                <c:set value="${gid }" var="argument"></c:set>
                                                <button value="${newGoods.getGoodsId()}"
                                                        class="btn btn-outline-danger btn-favorite deleteCollect">
                                                    <a href="#"><span>已收藏</span></a>
                                                </button>
                                            </c:if>
                                        </c:forEach>
                                    </c:if>
                                    <c:if test="${collectGid == null or argument == 0}">
                                        <button value="${newGoods.getGoodsId()}"
                                                class="btn btn-outline-danger btn-favorite addCollect">
                                            <a href="#"><span>收藏</span></a>
                                        </button>
                                    </c:if>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </c:forEach></c:if>
        </div>


        <!-- 猜你喜欢 -->
        <div class="row justify-content-between" style="width: 100%;margin-top:10px;margin-bottom:5px;">
            <h3 class="col-3">猜你喜欢</h3>
            <p class="col-2" style="font-size: 18px;text-align: right"><a href="goods/guesslikeGoods">更多</a></p>
        </div>
        <!-- 猜你喜欢展示 -->
        <div class="row row-cols-6 justify-content-around home-block goods-block">
            <c:if test="${guessLikeList!=null}">
                <c:forEach var="guessLike" items="${guessLikeList}" begin="0" end="9">
                    <div class="goods-outline col">
                        <div class="goods-outline-content">
                            <a href="goodsdetail?id=${guessLike.getGoodsId()}">
                                <div class="goods-outline-info">

                                    <img class="figure-img img-fluid rounded mx-auto d-block goods-outline-img"
                                         style="width:196px;height:196px;"
                                         src="${basePath}images/${guessLike.goodsImage}">
                                    <p class="text-truncate text-wrap goods-outline-title"><span
                                            class="tag">猜你喜欢</span>${guessLike.getGoodsName()}</p>
                                    <h4 class="goods-outline-price">￥${guessLike.getGoodsPrice()}</h4>
                                    <p class="goods-outline-origin">${guessLike.getGoodsBuytime()}购于${guessLike.getGoodsBuyof()}</p>
                                </div>
                            </a>

                            <button aria-label="Close" class="close btn-unlike" type="button">
                                <span aria-hidden="true">&times;</span>
                            </button>

                            <div align="center" class="rounded goods-outline-like">
                                <button class="btn btn-outline-success btn-find-similar">
                                    <a href="goods/search?sort=${guessLike.getGoodsSort()}">寻找相似</a>
                                </button>

                                <!-- 用户未登录不能收藏 -->
                                <c:if test="${user==null}">
                                    <button class="btn btn-outline-danger btn-favorite collect1">
                                        <a><span>收藏</span></a>
                                    </button>
                                </c:if>

                                <c:if test="${user != null}">
                                    <c:if test="${collectGid!=null }">
                                        <c:set value="${0}" var="argument"></c:set>
                                        <!-- 用户已登录，若商品已收藏则显示颜色，未收藏不显示 -->
                                        <c:forEach var="gid" items="${collectGid}">
                                            <c:if test="${guessLike.getGoodsId()==gid }">
                                                <c:set value="${gid }" var="argument"></c:set>
                                                <button value="${guessLike.getGoodsId()}"
                                                        class="btn btn-outline-danger btn-favorite deleteCollect">
                                                    <a href="#"><span>已收藏</span></a>
                                                </button>
                                            </c:if>
                                        </c:forEach>
                                    </c:if>
                                    <c:if test="${collectGid == null or argument == 0}">
                                        <button value="${guessLike.getGoodsId()}"
                                                class="btn btn-outline-danger btn-favorite addCollect">
                                            <a href="#"><span>收藏</span></a>
                                        </button>
                                    </c:if>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </c:if>
        </div>
    </div>
</main>


<!-- 底部信息栏 -->
<footer style="background: #d3d9df;width: 100%;margin: 60px 0 0 0;padding-top: 20px;padding-bottom: 50px">
    <div>
        <div class="container-fluid row promise-block justify-content-around" style="width: 100%">
            <div class="col-2">
                <img class="img-thumbnail" src="image/promise/promise1.jpg" style="width: 35%;display: inline-block">
                <div style="width: 68%;display: inline-block;padding-left: 10px;position: absolute;top: 0;bottom: 0">
                    <span style="font-size: 22px">正品保障</span><br>
                    <span style="font-size: 12px">正品保障、提供发票</span>
                </div>
            </div>

            <div class="col-2">
                <img class="img-thumbnail" src="image/promise/promise2.jpg" style="width: 35%;display: inline-block">
                <div style="width: 68%;display: inline-block;padding-left: 10px;position: absolute;top: 0;bottom: 0">
                    <span style="font-size: 22px">急速物流</span><br>
                    <span style="font-size: 14px">如约送货、送货入户</span>
                </div>
            </div>

            <div class="col-2">
                <img class="img-thumbnail" src="image/promise/promise3.jpg" style="width: 35%;display: inline-block">
                <div style="width: 68%;display: inline-block;padding-left: 10px;position: absolute;top: 0;bottom: 0">
                    <span style="font-size: 22px">售后无忧</span><br>
                    <span style="font-size: 14px">14天包退30天包换</span>
                </div>
            </div>

            <div class="col-2">
                <img class="img-thumbnail" src="image/promise/promise4.jpg" style="width: 35%;display: inline-block">
                <div style="width: 68%;display: inline-block;padding-left: 10px;position: absolute;top: 0;bottom: 0">
                    <span style="font-size: 22px">帮助中心</span><br>
                    <span style="font-size: 14px">您的购物指南</span>
                </div>
            </div>

            <div class="col-2">
                <img class="img-thumbnail" src="image/promise/promise5.png" style="width: 35%;display: inline-block">
                <div style="width: 68%;display: inline-block;padding-left: 10px;position: absolute;top: 0;bottom: 0">
                    <span style="font-size: 22px">省心购</span><br>
                    <span style="font-size: 14px">专注好服务</span>
                </div>
            </div>
        </div>

        <div class="ng-s-f-con" style="text-align: center;margin-top: 30px">
            <p class="ng-copyright" style="display: block;">
                Copyright© 2002-2020，3305集团股份有限公司版权所有<span>|</span>
                <a class="gwab" href="http://www.beian.gov.cn/portal/registerSystemInfo?recordcode=32010202010078"
                   rel="nofollow" style="color:#999" target="_blank"><i></i>豫公网安备 32010202010078号</a><span>|</span>
                <a href="http://www.beian.miit.gov.cn" style="color:#999" target="_blank">豫ICP备10207551号-4</a></p>
            <p class="ng-copyright ng-copyright-2" style="display: block;">
                <a href="//res.suning.cn/public/v3/images/dx5006.png" rel="nofollow" style="color:#999" target="_blank">合字B2-20180025</a><span>|</span>
                <a href="//res.suning.cn/public/v3/images/dx1001.png" rel="nofollow" style="color:#999" target="_blank">合字A1.B1.B2-20180017</a><span>|</span>
                <a href="//res.suning.cn/public/v3/images/cbw-20200402.jpg" rel="nofollow" style="color:#999"
                   target="_blank">出版物经营许可证新出发豫批字第A-243号</a><span>|</span>
                互联网药品信息服务资格证书<a href="//res.suning.cn/public/v3/images/yaopin_new.png" rel="nofollow" style="color:#999"
                                target="_blank">（豫）-非经营性-2016-0005</a></p>
            <p class="ng-copyright ng-copyright-3" style="display: block;">
                <a href="https://cuxiao.suning.com/jyzz2.html" rel="nofollow" style="color:#999"
                   target="_blank">经营证照</a><span>|</span>
                <a href="http://sq.ccm.gov.cn/ccnt/sczr/service/business/emark/toDetail/ccaf7746824a4f91af567c29abc4b6aa"
                   rel="nofollow" style="color:#999" target="_blank">网络文化经营许可证：豫网文〔2018〕10580-203号</a><span>|</span>
                <a href="javascript:" style="cursor: default;">互联网违法和不良信息举报邮箱：kfpt-yy@cnsuning.com</a><span>|</span>
                <a href="javascript:" style="cursor: default;">举报电话：02566996699-865948</a>
            </p>
            <p class="ng-copyright" style="display: block;"><a
                    href="//res.suning.cn/public/v3/images/ylqx-20190103.jpg?v=20200827" rel="nofollow"
                    style="color:#999"
                    target="_blank">医疗器械网络交易服务第三方平台备案凭证-（豫）网械平台备字（2018）第00052号</a></p>
            <p class="ng-copyright" style="display: block;">本网站直接或间接向消费者推销商品或者服务的商业宣传均属于“广告”（包装及参数、售后保障等商品信息除外）</p>
            <div class="ng-authentication" style="display: block;">
                <a href="http://www.itrust.org.cn/Home/Index/itrust_certifi/wm/1566884810.html"
                   name="public0_none_wb_zs0301"
                   rel="nofollow" sap-expo="true" target="_blank">
                    <img alt="网信认证" height="24" src="//res.suning.cn/public/v3/images/wxrz.png" width="65">
                </a>
                <a href="https://search.szfw.org/cert/l/CX20111018000608000610" name="public0_none_wb_zs0302"
                   rel="nofollow" sap-expo="true" target="_blank">
                    <img alt="诚信网站" height="24" src="//res.suning.cn/public/v3/images/chengxin.png" width="76">
                </a>
                <a href="http://image.suning.cn/uimg/snnet/snnetImg/142891196680527240.jpg"
                   name="public0_none_wb_zs0303"
                   rel="nofollow" sap-expo="true" target="_blank">
                    <img alt="中国联通授权网络经营代理商" height="24" src="//res.suning.cn/public/v3/images/unicom.png" width="76">
                </a>
                <a href="//res.suning.cn/public/v3/images/dianxin_content.jpg" name="public0_none_wb_zs0304"
                   rel="nofollow" sap-expo="true" target="_blank">
                    <img alt="中国电信授权网络经营代理商" height="24" src="//res.suning.cn/public/v3/images/dianxin.jpg" width="76">
                </a>
                <a href="https://zzlz.gsxt.gov.cn/businessCheck/verifKey.do?showType=p&amp;serial=913200005668848108-SAIC_SHOW_1000009132000056688481081572311357911&amp;signData=MEQCII+7OGallh6hxToamA3VfhexsTQw4fBcArcI7NexQaS0AiA8CqvkBVtdxtB/yiHMrNo9FWipRU161IH5hhB5BlBi2g=="
                   name="public0_none_wb_zs0305" rel="nofollow" sap-expo="true" target="_blank">
                    <img alt="电子营业执照" height="24" src="//res.suning.cn/public/v3/images/dianzizhizhao.png?v=01"
                         width="24">
                </a>
                <a href="//www.12377.cn/node_548446.htm" name="public0_none_wb_zs0306" rel="nofollow" sap-expo="true"
                   target="_blank">
                    <img alt="12377" height="24" src="//res.suning.cn/public/v3/images/netsafe1.png?v=01" width="72">
                </a>
                <a href="//www.12377.cn/" name="public0_none_wb_zs0307" rel="nofollow" sap-expo="true" target="_blank">
                    <img alt="网上有害信息举报" height="24" src="//res.suning.cn/public/v3/images/netsafe2.png?v=01"
                         width="144">
                </a>
                <a href="http://www.shdf.gov.cn/shdf/channels/740.html" name="public0_none_wb_zs0308" rel="nofollow"
                   sap-expo="true" target="_blank">
                    <img alt="扫黄打非" height="24" src="//res.suning.cn/public/v3/images/netsafe3.png?v=01" width="166">
                </a>
            </div>
        </div>
    </div>
</footer>

<script>
    var userName = getCookie("user-name");
    if (userName !== null && userName.trim().length > 0) {

    }
</script>
</body>
</html>
