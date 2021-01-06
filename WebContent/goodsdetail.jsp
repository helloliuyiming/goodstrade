<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:if test="${requestScope.goods==null}">
    <c:redirect url="index.jsp"/>
</c:if>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${goods.goodsName}</title>

    <!-- 响应式标签 -->
    <meta content="width=device-width, initial-scale=1, shrink-to-fit=no" name="viewport">
    <link href="link/bootstrap-4.5.0-dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="css/common.css" rel="stylesheet"/>
    <style>
        body, html {
            width: 100%;
            height: 100%;
        }

        #preview {
            height: 36%;
        }

        .field {
            margin-top: 5px;
        }
    </style>
</head>
<body class="container" style="overflow:hidden;">
<div style="height: 90%;margin-top: 3%;" class="card">
    <form action="#" style="height: 96%;margin: 1%">

        <div class="row justify-content-around" style="height: 100%">

            <div class="col-4 img-block" style="height: 90%">
                <div style="height: 75%">

                    <div class="carousel slide" data-ride="carousel" id="carouselExampleIndicators"
                         style="display: block;width: 100%;height: 90%">
                        <div class="carousel-inner" style="height: 80%">
                            <div class="carousel-item active">
                                <img class="d-block w-100" src="images/${imageIds[0]}" style="width: 100%;height: 100%">
                            </div>
                            <c:forEach begin="1" end="5" var="item">
                                <div class="carousel-item">
                                    <img class="d-block w-100" src="images/${imageIds[item]}" style="width: 100%;height: 100%">
                                </div>
                            </c:forEach>

                        </div>

                        <div class="row row-cols-5 carousel-indicators" style="height: 16%;margin: 2%">

                            <button class="active" data-slide-to="0" data-target="#carouselExampleIndicators"
                                    style="height: 100%;padding: 0" type="button">
                                <img src="images/${imageIds[0]}" style="height: 100%;width: 100%">
                            </button>
                            <c:forEach begin="1" end="4" var="item">
                                <button data-slide-to="1" data-target="#carouselExampleIndicators" style="height: 100%;padding: 0"
                                        type="button">
                                    <img src="images/${imageIds[item]}" style="height: 100%;width: 100%">
                                </button>
                            </c:forEach>

                        </div>
                    </div>

                </div>

                <div id="seller-block" style="width: 100%;height: 25%">
                    <h3>卖家信息</h3>
                    <div class="row">
                        <a href="personal${sellerInfo.userId}" class="col-4 align-self-center">
                            <img  src="images/${sellerInfo.image}" style="width: 100%;object-fit: contain"/>
                        </a>

                        <div class="col-7">

                            <p style="margin-bottom: 10px"><a href="personal?id=${sellerInfo.userId}"> ${sellerInfo.username}</a></p>
                            <p style="margin-bottom: 10px">信誉:★★★★★</p>
                            <p style="margin-bottom: 10px">14分钟前来过</p>
                            <p style="margin-bottom: 10px">现居地：河南洛阳</p>
                        </div>
                    </div>


                </div>

            </div>

            <div class="col-7" style="position: relative;margin-top: 40px">
                <h5>${goods.goodsName}</h5>
                <div class="row">
                    <p class="col">价格：<span style="color: red">￥<span style="font-size: 28px">${goods.goodsPrice}</span> </span></p>
                    <p class="col align-self-end">运费：${goods.goodsFreight}</p>
                </div>

                <p>在<fmt:formatDate type="date" value="${goods.goodsBuytime}" />购买于${goods.goodsBuyof}平台</p>

                <p>${goods.goodsBroken}</p>

                <div class="card">
                    <p class="card-header">
                        说明
                    </p>
                    <div class="card-body" style="overflow-y: scroll;max-height: 240px">
                        ${goods.goodsDetails}
                    </div>
                </div>

                <div class="row justify-content-around field" style="position: absolute;bottom: 20px;width: 100%">
                    <button class="col-4 btn btn-success">
                        <a style="color: white" href="previewOrder?goodsIds=${goods.goodsId}">收藏</a>
                    </button>

                        <button class="col-4 btn btn-primary" ${goods.goodsStatus=='public'?'':'disabled'} >
                            <c:choose>
                                <c:when test="${goods.goodsStatus=='public'}">
                                    <a style="color: white" href="previewOrder?goodsIds=${goods.goodsId}">立即购买</a>
                                </c:when>
                                <c:otherwise>
                                    已出售或下架
                                </c:otherwise>
                            </c:choose>

                        </button>

                </div>
            </div>
        </div>

    </form>

</div>

<script src="link/jquery-3.5.1.js"></script>
<script src="link/bootstrap-4.5.0-dist/js/bootstrap.bundle.js"></script>
<script src="js/common.js"></script>
<script>
    function addPicture() {
        var fileReader = new FileReader();
        var file = document.getElementById("add-picture").files[0];
        fileReader.readAsDataURL(file)
        fileReader.onload = function (e) {
            document.getElementById('preview').src = this.result;
            document.getElementById('preview').style.height = document.getElementById('preview').style.width
        };

        console.log("add")
    }
</script>
</body>
</html>