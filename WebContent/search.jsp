<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>商品搜索</title>
    <meta charset="UTF-8">
    <!-- 响应式标签 -->
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="link/bootstrap-4.5.0-dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/common.css"/>
    <script src="link/jquery-3.5.1.js"></script>
    <script src="link/bootstrap-4.5.0-dist/js/bootstrap.bundle.js"></script>
    <script src="js/common.js"></script>
    <script type="text/javascript" src="js/goods.js"></script>
</head>
<body>
<header class="container-fluid">
    <div class="row justify-content-center" id="header-title">
        <a class="col-1" href="index.jsp">
            <img alt="logo" class="img-thumbnail" src="image/logo.jpg">
        </a>
        <div class="input-group offset-1 col-6" id="search-block">
            <div class="row container-lg" id="search-input">
                <div class="col-8">
                    <input id="searchByKey" type="text" class="form-control" placeholder="输入关键字搜索"
                           aria-label="Recipient's username"
                           aria-describedby="button-addon2">
                </div>
                <div class="input-group-append col-3">
                    <button class="btn btn-outline-secondary search_btn" type="submit" id="search">搜索</button>
                </div>
            </div>

            <!-- 搜索历史 -->
            <div id="search-history" class="row container-lg">
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

        <c:choose>
            <c:when test="${user!=null}">
                <div class="col-3 row justify-content-start align-items-center">
                    <a class="col-4" href="personal" style="display: inline-block">
                        <img src="images/${user.getImage() }" style="width: 100%;object-fit: contain;top: 10%;height: 80%"/>
                    </a>

                    <div class="6">
                        <a href="personal"><span style="font-size: 22px">${user.getUsername()}</span></a><br>
                        <a href="#">我的消息(6)</a><br>
                        <c:if test="${collectGid!=null }">
                            <a href="favorites.jsp">我的收藏(${collectGid.size() })</a>
                        </c:if>
                        <c:if test="${collectGid==null }">
                            <a href="favorites.jsp">我的收藏(0)</a>
                        </c:if>
                    </div>
                </div>
            </c:when>
            <c:otherwise>
                <div class="col-3 row justify-content-start align-items-center">
                    <a class="col-4" href="login.jsp" style="display: inline-block">
                        <img src="image/avatar.png" style="width: 100%;object-fit: contain;top: 10%;height: 80%"/>
                    </a>

                    <div class="6">
                        <a href="login.jsp"><span style="font-size: 22px">登录</span></a><br>
                        <a href="register.jsp">注册</a><br>
                    </div>
                </div>
            </c:otherwise>
        </c:choose>

    </div>
</header>

<main class="container-fluid">
    <%-- <c:if test="${listBySort!=null or listByName!=null}">
        <h4 class="offset-2">搜索结果</h4>
    </c:if> --%>
    <h4 class="offset-2">搜索结果</h4>
    <c:if test="${listBySort==null and listByName==null}">
        <center><h2>未找到搜索结果<a href="index.jsp">去主页</a>或继续搜索</h2></center>
    </c:if>
    <div class="row" style="width: 100%;margin-top: 30px">
        <aside id="search-filter-block" class="col-2">
            <c:if test="${listBySort!=null}">
                <div>
                    <h5>排序</h5>
                    <select class="custom-select" id="screen">
                        <c:if test="${columnNumber==3 and  orderNumber == 2}">
                            <option selected value="3&orderNumber=2">收藏量升序</option>
                        </c:if>
                        <c:if test="${columnNumber!=3 or  orderNumber != 2}">
                            <option value="3&orderNumber=2">收藏量升序</option>
                        </c:if>
                        <c:if test="${columnNumber==3 and  orderNumber == 1}">
                            <option selected value="3&orderNumber=1">收藏量降序</option>
                        </c:if>
                        <c:if test="${columnNumber!=3 or  orderNumber != 1}">
                            <option value="3&orderNumber=1">收藏量降序</option>
                        </c:if>
                        <c:if test="${columnNumber==4 and  orderNumber == 2}">
                            <option selected value="4&orderNumber=2">运费升序</option>
                        </c:if>
                        <c:if test="${columnNumber!=4 or  orderNumber != 2}">
                            <option value="4&orderNumber=2">运费升序</option>
                        </c:if>
                        <c:if test="${columnNumber==4 and  orderNumber == 1}">
                            <option selected value="4&orderNumber=1">运费降序</option>
                        </c:if>
                        <c:if test="${columnNumber!=4 or  orderNumber != 1}">
                            <option value="4&orderNumber=1">运费降序</option>
                        </c:if>
                        <c:if test="${columnNumber==2 and  orderNumber == 2}">
                            <option selected value="2&orderNumber=2">价格升序</option>
                        </c:if>
                        <c:if test="${columnNumber!=2 or  orderNumber != 2}">
                            <option value="2&orderNumber=2">价格升序</option>
                        </c:if>
                        <c:if test="${columnNumber==2 and  orderNumber == 1}">
                            <option selected value="2&orderNumber=1">价格升序</option>
                        </c:if>
                        <c:if test="${columnNumber!=2 or  orderNumber != 1}">
                            <option value="2&orderNumber=1">价格降序</option>
                        </c:if>
                    </select>
                </div>
            </c:if>
            <c:if test="${listByName!=null}">
                <div>
                    <h5>排序</h5>
                    <select class="custom-select" id="screenByName">
                        <c:if test="${columnNumber==3 and  orderNumber == 2}">
                            <option selected value="3&orderNumber=2">收藏量升序</option>
                        </c:if>
                        <c:if test="${columnNumber!=3 or  orderNumber != 2}">
                            <option value="3&orderNumber=2">收藏量升序</option>
                        </c:if>
                        <c:if test="${columnNumber==3 and  orderNumber == 1}">
                            <option selected value="3&orderNumber=1">收藏量降序</option>
                        </c:if>
                        <c:if test="${columnNumber!=3 or  orderNumber != 1}">
                            <option value="3&orderNumber=1">收藏量降序</option>
                        </c:if>
                        <c:if test="${columnNumber==4 and  orderNumber == 2}">
                            <option selected value="4&orderNumber=2">运费升序</option>
                        </c:if>
                        <c:if test="${columnNumber!=4 or  orderNumber != 2}">
                            <option value="4&orderNumber=2">运费升序</option>
                        </c:if>
                        <c:if test="${columnNumber==4 and  orderNumber == 1}">
                            <option selected value="4&orderNumber=1">运费降序</option>
                        </c:if>
                        <c:if test="${columnNumber!=4 or  orderNumber != 1}">
                            <option value="4&orderNumber=1">运费降序</option>
                        </c:if>
                        <c:if test="${columnNumber==2 and  orderNumber == 2}">
                            <option selected value="2&orderNumber=2">价格升序</option>
                        </c:if>
                        <c:if test="${columnNumber!=2 or  orderNumber != 2}">
                            <option value="2&orderNumber=2">价格升序</option>
                        </c:if>
                        <c:if test="${columnNumber==2 and  orderNumber == 1}">
                            <option selected value="2&orderNumber=1">价格升序</option>
                        </c:if>
                        <c:if test="${columnNumber!=2 or  orderNumber != 1}">
                            <option value="2&orderNumber=1">价格降序</option>
                        </c:if>
                    </select>
                </div>
            </c:if>
            <br>
            <div>
                <!-- 通过类别筛选 -->
                <c:if test="${sort!=null and goodsName==null }">
                    <c:if test="${listBySort!=null}">
                        <h5>筛选</h5>
                        <div class="form-check">
                            <label class="form-check-label" >
                                <a href="goods/baokuanGoods?sort=${sort}">此类精品</a>
                            </label>
                        </div>

                        <div class="form-check">
                            <label class="form-check-label" >
                                <a href="goods/xinpinGoods?sort=${sort}">此类新品</a>
                            </label>
                        </div>

                        <div class="form-check">
                            <label class="form-check-label" >
                                <a href="goods/guesslikeGoods?sort=${sort}">猜你喜欢</a>
                            </label>
                        </div>
                    </c:if>
                </c:if>
                <!-- 通过名字筛选 -->
                <c:if test="${goodsName!=null and sort==null}">
                    <c:if test="${listByName!=null}">
                        <h5>筛选</h5>
                        <div class="form-check">
                            <label class="form-check-label" >
                                <a href="goods/baokuanGoodsByName?goodsName=${goodsName}">此类精品</a>
                            </label>
                        </div>

                        <div class="form-check">
                            <label class="form-check-label" >
                                <a href="goods/xinpinGoodsByName?goodsName=${goodsName}">此类新品</a>
                            </label>
                        </div>

                        <div class="form-check">
                            <label class="form-check-label" >
                                <a href="goods/guesslikeGoodsByName?goodsName=${goodsName}">猜你喜欢</a>
                            </label>
                        </div>
                    </c:if>
                </c:if>
            </div>
        </aside>


        <div class="d-flex flex-wrap justify-content-around home-block goods-block col-10">
            <c:if test="${listBySort != null}">
                <c:forEach var="searchGoods" items="${listBySort}">
                    <div class="goods-outline">
                        <a href="goodsdetail?id=${searchGoods.getGoodsId() }">
                            <div class="goods-outline-info">

                                <img style="width:196px;height:196px;" src="${basePath}images/${searchGoods.goodsImage}"
                                     class="figure-img img-fluid rounded mx-auto d-block goods-outline-img">
                                <p class="text-truncate text-wrap goods-outline-title">${searchGoods.getGoodsName()}</p>
                                <h4 class="goods-outline-price">￥${searchGoods.getGoodsPrice()}</h4>
                                <p class="goods-outline-origin">${searchGoods.getGoodsBuytime()}购于${searchGoods.getGoodsBuyof()}</p>
                            </div>
                        </a>

                        <button type="button" class="close btn-unlike" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>

                        <div align="center" class="rounded goods-outline-like">
                            <button class="btn btn-outline-success btn-find-similar">
                                <a href="goods/search?sort=${searchGoods.getGoodsSort()}">寻找相似</a>
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
                                        <c:if test="${searchGoods.getGoodsId()==gid }">
                                            <c:set value="${gid }" var="argument"></c:set>
                                            <button value="${searchGoods.getGoodsId()}"
                                                    class="btn btn-outline-danger btn-favorite deleteCollect">
                                                <a href="#"><span>已收藏</span></a>
                                            </button>
                                        </c:if>
                                    </c:forEach>
                                </c:if>
                                <c:if test="${collectGid == null or argument == 0}">
                                    <button value="${searchGoods.getGoodsId()}"
                                            class="btn btn-outline-danger btn-favorite addCollect">
                                        <a href="#"><span>收藏</span></a>
                                    </button>
                                </c:if>
                            </c:if>
                        </div>
                    </div>
                </c:forEach>
            </c:if>

            <!-- 通过名字筛选的商品集合遍历 -->
            <c:if test="${listByName != null}">
                <c:forEach var="searchGoods" items="${listByName}">
                    <div class="goods-outline">
                        <a href="goodsdetail?id=${searchGoods.getGoodsId() }">
                            <div class="goods-outline-info">

                                <img style="width:196px;height:196px;" src="${basePath}images/${searchGoods.goodsImage}"
                                     class="figure-img img-fluid rounded mx-auto d-block goods-outline-img">
                                <p class="text-truncate text-wrap goods-outline-title">${searchGoods.getGoodsName()}</p>
                                <h4 class="goods-outline-price">￥${searchGoods.getGoodsPrice()}</h4>
                                <p class="goods-outline-origin">${searchGoods.getGoodsBuytime()}购于${searchGoods.getGoodsBuyof()}</p>
                            </div>
                        </a>

                        <button type="button" class="close btn-unlike" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>

                        <div align="center" class="rounded goods-outline-like">
                            <button class="btn btn-outline-success btn-find-similar">
                                <a href="goods/search?sort=${searchGoods.getGoodsSort()}">寻找相似</a>
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
                                        <c:if test="${searchGoods.getGoodsId()==gid }">
                                            <c:set value="${gid }" var="argument"></c:set>
                                            <button value="${searchGoods.getGoodsId()}"
                                                    class="btn btn-outline-danger btn-favorite deleteCollect">
                                                <a href="#"><span>已收藏</span></a>
                                            </button>
                                        </c:if>
                                    </c:forEach>
                                </c:if>
                                <c:if test="${collectGid == null or argument == 0}">
                                    <button value="${searchGoods.getGoodsId()}"
                                            class="btn btn-outline-danger btn-favorite addCollect">
                                        <a href="#"><span>收藏</span></a>
                                    </button>
                                </c:if>
                            </c:if>
                        </div>
                    </div>
                </c:forEach>
            </c:if>
        </div>
    </div>

    <!-- 翻页 -->
    <!-- <div align="center" class="offset-2" style="margin-top: 80px">
    <ul class="pagination">
      <li class="page-item">
        <a class="page-link" href="#" aria-label="Previous">
          <span aria-hidden="true">&laquo;</span>
          <span class="sr-only">Previous</span>
        </a>
      </li>
      <li class="page-item"><a class="page-link" href="#">1</a></li>
      <li class="page-item"><a class="page-link" href="#">2</a></li>
      <li class="page-item"><a class="page-link" href="#">3</a></li>
      <li class="page-item">
        <a class="page-link" href="#" aria-label="Next">
          <span aria-hidden="true">&raquo;</span>
          <span class="sr-only">Next</span>
        </a>
      </li>
    </ul>
</div> -->
</main>


<footer style="background: #d3d9df;width: 100%;margin: 60px 0 0 0;padding-top: 20px;padding-bottom: 50px">
    <div>
        <div class="container-fluid row promise-block justify-content-around" style="width: 100%">
            <div class="col-2">
                <img src="image/promise/promise1.jpg" class="img-thumbnail" style="width: 35%;display: inline-block">
                <div style="width: 68%;display: inline-block;padding-left: 10px;position: absolute;top: 0;bottom: 0">
                    <span style="font-size: 22px">正品保障</span><br>
                    <span style="font-size: 12px">正品保障、提供发票</span>
                </div>
            </div>

            <div class="col-2">
                <img src="image/promise/promise2.jpg" class="img-thumbnail" style="width: 35%;display: inline-block">
                <div style="width: 68%;display: inline-block;padding-left: 10px;position: absolute;top: 0;bottom: 0">
                    <span style="font-size: 22px">急速物流</span><br>
                    <span style="font-size: 14px">如约送货、送货入户</span>
                </div>
            </div>

            <div class="col-2">
                <img src="image/promise/promise3.jpg" class="img-thumbnail" style="width: 35%;display: inline-block">
                <div style="width: 68%;display: inline-block;padding-left: 10px;position: absolute;top: 0;bottom: 0">
                    <span style="font-size: 22px">售后无忧</span><br>
                    <span style="font-size: 14px">14天包退30天包换</span>
                </div>
            </div>

            <div class="col-2">
                <img src="image/promise/promise4.jpg" class="img-thumbnail" style="width: 35%;display: inline-block">
                <div style="width: 68%;display: inline-block;padding-left: 10px;position: absolute;top: 0;bottom: 0">
                    <span style="font-size: 22px">帮助中心</span><br>
                    <span style="font-size: 14px">您的购物指南</span>
                </div>
            </div>

            <div class="col-2">
                <img src="image/promise/promise5.png" class="img-thumbnail" style="width: 35%;display: inline-block">
                <div style="width: 68%;display: inline-block;padding-left: 10px;position: absolute;top: 0;bottom: 0">
                    <span style="font-size: 22px">省心购</span><br>
                    <span style="font-size: 14px">专注好服务</span>
                </div>
            </div>
        </div>

        <div class="ng-s-f-con" style="text-align: center;margin-top: 30px">
            <p class="ng-copyright" style="display: block;">
                Copyright© 2002-2020，3305集团股份有限公司版权所有<span>|</span>
                <a target="_blank" href="http://www.beian.gov.cn/portal/registerSystemInfo?recordcode=32010202010078"
                   rel="nofollow" style="color:#999" class="gwab"><i></i>豫公网安备 32010202010078号</a><span>|</span>
                <a href="http://www.beian.miit.gov.cn" target="_blank" style="color:#999">豫ICP备10207551号-4</a></p>
            <p class="ng-copyright ng-copyright-2" style="display: block;">
                <a href="//res.suning.cn/public/v3/images/dx5006.png" target="_blank" rel="nofollow" style="color:#999">合字B2-20180025</a><span>|</span>
                <a href="//res.suning.cn/public/v3/images/dx1001.png" target="_blank" rel="nofollow" style="color:#999">合字A1.B1.B2-20180017</a><span>|</span>
                <a href="//res.suning.cn/public/v3/images/cbw-20200402.jpg" target="_blank" rel="nofollow"
                   style="color:#999">出版物经营许可证新出发豫批字第A-243号</a><span>|</span>
                互联网药品信息服务资格证书<a href="//res.suning.cn/public/v3/images/yaopin_new.png" target="_blank" rel="nofollow"
                                style="color:#999">（豫）-非经营性-2016-0005</a></p>
            <p class="ng-copyright ng-copyright-3" style="display: block;">
                <a href="https://cuxiao.suning.com/jyzz2.html" target="_blank" rel="nofollow"
                   style="color:#999">经营证照</a><span>|</span>
                <a href="http://sq.ccm.gov.cn/ccnt/sczr/service/business/emark/toDetail/ccaf7746824a4f91af567c29abc4b6aa"
                   target="_blank" rel="nofollow" style="color:#999">网络文化经营许可证：豫网文〔2018〕10580-203号</a><span>|</span>
                <a href="javascript:" style="cursor: default;">互联网违法和不良信息举报邮箱：kfpt-yy@cnsuning.com</a><span>|</span>
                <a href="javascript:" style="cursor: default;">举报电话：02566996699-865948</a>
            </p>
            <p class="ng-copyright" style="display: block;"><a
                    href="//res.suning.cn/public/v3/images/ylqx-20190103.jpg?v=20200827" target="_blank" rel="nofollow"
                    style="color:#999">医疗器械网络交易服务第三方平台备案凭证-（豫）网械平台备字（2018）第00052号</a></p>
            <p class="ng-copyright" style="display: block;">本网站直接或间接向消费者推销商品或者服务的商业宣传均属于“广告”（包装及参数、售后保障等商品信息除外）</p>
            <div class="ng-authentication" style="display: block;">
                <a href="http://www.itrust.org.cn/Home/Index/itrust_certifi/wm/1566884810.html" target="_blank"
                   name="public0_none_wb_zs0301" rel="nofollow" sap-expo="true">
                    <img src="//res.suning.cn/public/v3/images/wxrz.png" height="24" width="65" alt="网信认证">
                </a>
                <a href="https://search.szfw.org/cert/l/CX20111018000608000610" target="_blank"
                   name="public0_none_wb_zs0302" rel="nofollow" sap-expo="true">
                    <img src="//res.suning.cn/public/v3/images/chengxin.png" height="24" width="76" alt="诚信网站">
                </a>
                <a href="http://image.suning.cn/uimg/snnet/snnetImg/142891196680527240.jpg" target="_blank"
                   name="public0_none_wb_zs0303" rel="nofollow" sap-expo="true">
                    <img src="//res.suning.cn/public/v3/images/unicom.png" height="24" width="76" alt="中国联通授权网络经营代理商">
                </a>
                <a href="//res.suning.cn/public/v3/images/dianxin_content.jpg" target="_blank"
                   name="public0_none_wb_zs0304" rel="nofollow" sap-expo="true">
                    <img src="//res.suning.cn/public/v3/images/dianxin.jpg" height="24" width="76" alt="中国电信授权网络经营代理商">
                </a>
                <a href="https://zzlz.gsxt.gov.cn/businessCheck/verifKey.do?showType=p&amp;serial=913200005668848108-SAIC_SHOW_1000009132000056688481081572311357911&amp;signData=MEQCII+7OGallh6hxToamA3VfhexsTQw4fBcArcI7NexQaS0AiA8CqvkBVtdxtB/yiHMrNo9FWipRU161IH5hhB5BlBi2g=="
                   target="_blank" rel="nofollow" name="public0_none_wb_zs0305" sap-expo="true">
                    <img src="//res.suning.cn/public/v3/images/dianzizhizhao.png?v=01" height="24" width="24"
                         alt="电子营业执照">
                </a>
                <a href="//www.12377.cn/node_548446.htm" target="_blank" rel="nofollow" name="public0_none_wb_zs0306"
                   sap-expo="true">
                    <img src="//res.suning.cn/public/v3/images/netsafe1.png?v=01" height="24" width="72" alt="12377">
                </a>
                <a href="//www.12377.cn/" target="_blank" rel="nofollow" name="public0_none_wb_zs0307" sap-expo="true">
                    <img src="//res.suning.cn/public/v3/images/netsafe2.png?v=01" height="24" width="144"
                         alt="网上有害信息举报">
                </a>
                <a href="http://www.shdf.gov.cn/shdf/channels/740.html" target="_blank" rel="nofollow"
                   name="public0_none_wb_zs0308" sap-expo="true">
                    <img src="//res.suning.cn/public/v3/images/netsafe3.png?v=01" height="24" width="166" alt="扫黄打非">
                </a>
            </div>
        </div>
    </div>
</footer>
</body>
</html>
