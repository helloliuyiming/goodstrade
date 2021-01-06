<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${sessionScope.user==null}">
    <c:redirect url="login.jsp"/>
</c:if>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>发布闲置</title>

    <!-- 响应式标签 -->
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="link/bootstrap-4.5.0-dist/css/bootstrap.min.css">
    <link href="link/select2.min.css" rel="stylesheet"/>
    <link href="css/common.css" rel="stylesheet"/>
    <script src="link/jquery-3.5.1.js"></script>
    <script type="text/javascript" src="js/goods.js"></script>
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
<body class="container">
<h2 class="text-center">发布闲置</h2>

<form action="addGoods" enctype="multipart/form-data" method="post" style="height: 90%">

    <div class="row justify-content-around" style="height: 100%">

        <div class="col-4 img-block" style="height: 90%">
            <div style="height: 70%">
                <div style="display: block;width: 100%;height: 80%">
                    <img src="image/添加图片.svg" style="width: 100%;height: 100%" id="preview">
                </div>

                <!-- 添加图片 -->
                <div class="row row-cols-5" style="height: 16%;margin: 2%">
                    <div class="custom-file" style="height: 95%">
                        <button class="add-picture-btn">
                            <input accept="image/*" class="custom-file-input add-picture" type="file" name="file1"
                                   value="" onchange="addPicture(this)" required>
                        </button>
                        <img src="image/add.svg" class="img-thumbnail small-preview-picture">
                        <button type="button" class="close close-picture" aria-label="Close"
                                onclick="removePicture(this)">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>

                    <div class="custom-file" style="height: 95%">
                        <button class="add-picture-btn">
                            <input accept="image/*" class="custom-file-input add-picture" type="file" name="file2"
                                   value="" onchange="addPicture(this)" required>
                        </button>
                        <img src="image/add.svg" class="img-thumbnail small-preview-picture">
                        <button type="button" class="close close-picture" aria-label="Close"
                                onclick="removePicture(this)">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>

                    <div class="custom-file" style="height: 95%">
                        <button class="add-picture-btn">
                            <input accept="image/*" class="custom-file-input add-picture" name="file3" type="file"
                                   value="" onchange="addPicture(this)" required>
                        </button>
                        <img src="image/add.svg" class="img-thumbnail small-preview-picture">
                        <button type="button" class="close close-picture" aria-label="Close"
                                onclick="removePicture(this)">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>

                    <div class="custom-file" style="height: 95%">
                        <button class="add-picture-btn">
                            <input accept="image/*" class="custom-file-input add-picture" name="file4" type="file"
                                   value="" onchange="addPicture(this)" required>
                        </button>
                        <img src="image/add.svg" class="center-block img-thumbnail small-preview-picture">
                        <button type="button" class="close close-picture" aria-label="Close"
                                onclick="removePicture(this)">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>

                    <div class="custom-file" style="height: 95%">
                        <button class="add-picture-btn">
                            <input accept="image/*" class="custom-file-input add-picture" name="file5" type="file"
                                   value="" onchange="addPicture(this)" required>
                        </button>
                        <img src="image/add.svg" class="center-block img-thumbnail small-preview-picture">
                        <button type="button" class="close close-picture" aria-label="Close"
                                onclick="removePicture(this)">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>

                </div>
            </div>
            <div class="form-group form-check field">
                <input type="checkbox" class="form-check-input" required>
                <label class="form-check-label">我保证商品与所填信息相符</label>
            </div>
            <!-- 提交 -->
            <div class="row justify-content-around field">
                <button class="col-4 btn btn-outline-success">保存</button>
                <button type="submit" class="col-4 btn btn-primary">发布</button>
            </div>
        </div>

        <!-- 输入商品信息 -->
        <div class="col-7">
            <div class="field">
                <label>标题</label>
                <input type="text" class="form-control" name="goodsName" required>
            </div>
            <div class="field">
                <label>Tags</label>
                <!-- 商品类别 -->
                　<select id="category-select" name="goodsSort" class="form-control" multiple="multiple" required>
                　　　　
                <option value="家用电器">家用电器</option>
                <option value="手机">手机</option>
                <option value="运营商">运营商</option>
                <option value="数码">数码</option>
                <option value="电脑">电脑</option>
                <option value="办公">办公</option>
                <option value="家居">家居</option>
                <option value="家具">家具</option>
                <option value="家装">家装</option>
                <option value="厨具">厨具</option>
                <option value="男装">男装</option>
                <option value="女装">女装</option>
                <option value="童装">童装</option>
                <option value="内衣">内衣</option>
                <option value="美妆">美妆</option>
                <option value="个护清洁">个护清洁</option>
                <option value="宠物">宠物</option>
                <option value="女鞋">女鞋</option>
                <option value="箱包">箱包</option>
                <option value="钟表">钟表</option>
                <option value="珠宝">珠宝</option>
                <option value="男鞋">男鞋</option>
                <option value="运动">运动</option>
                <option value="户外">户外</option>
                <option value="房产">房产</option>
                <option value="汽车">汽车</option>
                <option value="汽车用品">汽车用品</option>
                <option value="母婴">母婴</option>
                <option value="玩具乐器">玩具乐器</option>
                <option value="食品">食品</option>
                <option value="酒类">酒类</option>
                <option value="生鲜">生鲜</option>
                <option value="特产">特产</option>
                <option value="艺术">艺术</option>
                <option value="礼品鲜花">礼品鲜花</option>
                <option value="农资绿植">农资绿植</option>
                <option value="医药保健">医药保健</option>
                <option value="计生情趣">计生情趣</option>
                <option value="图书">图书</option>
                <option value="文娱">文娱</option>
                <option value="教育">教育</option>
                <option value="电子书">电子书</option>
                <option value="机票">机票</option>
                <option value="酒店">酒店</option>
                <option value="旅游">旅游</option>
                <option value="生活">生活</option>
                <option value="理财">理财</option>
                <option value="众筹">众筹</option>
                <option value="白条">白条</option>
                <option value="保险">保险</option>
                <option value="安装">安装</option>
                <option value="维修">维修</option>
                <option value="清洗">清洗</option>
                <option value="二手">二手</option>
                <option value="工业品">工业品</option>
                　　</select>
            </div>

            <div class="field">
                <label>价格</label>
                <input type="number" class="form-control" name="goodsPrice" required>
            </div>

            <div class="field">
                <label>运费</label>
                <input type="number" name="goodsFreight" class="form-control" required>
            </div>

            <div class="field">
                <label>购买时间</label>
                <input type="date" class="form-control" name="time" required>
            </div>

            <div class="field">
                <label>购买渠道</label>
                <select name="source" class="form-control" required>
                    <option selected disabled>请选择...</option>
                    <option value="京东">京东</option>
                    <option value="淘宝">淘宝</option>
                    <option value="线下店">线下店</option>
                    <option value="官网">官网</option>
                    <option value="其他">其他</option>
                </select>
            </div>

            <div class="field">
                <label>破损程度</label>
                <select class="form-control" name="goodsBroken" required>
                    <option selected disabled>请选择...</option>
                    <option value="全新">全新</option>
                    <option value="9.5成新">9.5成新</option>
                    <option value="9成新">9成新</option>
                    <option value="8成新">8成新</option>
                    <option value="7成新">7成新</option>
                    <option value="5成新">5成新</option>
                    <option value="3成新">3成新</option>
                    <option value="1成新">1成新</option>
                    <option value="不能用">不能用</option>
                </select>
            </div>

            <div class="field">
                <label>简要说明</label>
                <textarea class="form-control" name="goodsDetails" rows="3" required maxlength="1000"
                          style="visibility: visible"></textarea>
            </div>


        </div>
    </div>

</form>
<footer style="margin-bottom: 20%">

</footer>
<script src="link/bootstrap-4.5.0-dist/js/bootstrap.bundle.js"></script>
<script src="link/select2.min.js"></script>
<script src="js/common.js"></script>
<script>
    var selectory = $('#category-select').select2({
        placeholder: '请选择该商品的分类，最多三个哦',
        allowClear: true,
        maximumSelectionLength: 3,
    });


    function addPicture(element) {
        var fileReader = new FileReader();
        var file = element.files[0];
        fileReader.readAsDataURL(file)
        console.log(this)
        fileReader.onload = function (e) {
            document.getElementById('preview').src = this.result;
            document.getElementById('preview').style.height = document.getElementById('preview').style.width
            var img = element.parentElement.parentElement.getElementsByTagName("img").item(0);
            img.src = this.result
            img.style.height = img.style.width
            var closeBtn = element.parentElement.parentElement.getElementsByClassName("close").item(0);
            closeBtn.style.visibility = 'visible'
            var addPictureBtn = element.parentElement.parentElement.getElementsByClassName("add-picture-btn").item(0);
            addPictureBtn.style.visibility = 'hidden'
            img.addEventListener("onclick", function () {
                console.log("switchPicture()")
                alert("success")
                document.getElementById("preview").src = img.src
            })
        };
    }

    function removePicture(element) {
        element.parentElement.getElementsByClassName("add-picture-btn").item(0).style.visibility = 'visible'
        let currentSrc = element.parentElement.getElementsByClassName("small-preview-picture").item(0).src

        if (document.getElementById("preview").src === currentSrc) {
            var smallPreviewPictureElements = document.getElementsByClassName("small-preview-picture");
            for (var i = 0; i < smallPreviewPictureElements.length; i++) {
                if (smallPreviewPictureElements.item(i).src !== 'image/add.svg') {
                    document.getElementById("preview").src = smallPreviewPictureElements.item(i).src;
                    break;
                }
                if (i === 5) {
                    document.getElementById("preview").src = 'image/添加图片.svg'
                }
            }

        }
        element.parentElement.getElementsByClassName("small-preview-picture").item(0).src = 'image/add.svg'
        element.style.visibility = 'hidden'

    }

    function switchPicture(element) {
        console.log("switchPicture()")
        document.getElementById("preview").src = element.src
    }

</script>
</body>
</html>