<%--
  Created by IntelliJ IDEA.
  User: gliuy
  Date: 2020/12/29
  Time: 21:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>我的订单</title>
    <!-- 响应式标签 -->
    <meta content="width=device-width, initial-scale=1, shrink-to-fit=no" name="viewport">
    <link href="link/bootstrap-4.5.0-dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .order-item-block{
            height: 240px;
            margin-top: 10px;
        }
        .order-item-block .row{
            margin: 0;
        }
    </style>
</head>
<body class="container">
<div class="card" style="margin-top: 50px">
    <div class="card-header">
        我的订单
    </div>
    <div class="card-body">
        <div style="width: 100%" class="row justify-content-between">
            <form class="form-inline col-9">
                <select class="custom-select my-1 mr-sm-2" id="inlineFormCustomSelectPref">
                    <option selected>全部订单</option>
                    <option value="1">已完成</option>
                    <option value="2">未完成</option>
                    <option value="3">已取消</option>
                    <option value="4">售后</option>
                </select>

                <div class="form-group">
                    <input type="text" class="form-control" placeholder="关键字搜索...">
                </div>

                <div class="col-auto">
                    <button type="submit" class="btn btn-primary">筛选</button>
                </div>
            </form>

            <div class="col-2">
                <select class="custom-select my-1 mr-sm-2">
                    <option selected>排序</option>
                    <option value="1">时间降序</option>
                    <option value="2">时间升序</option>
                    <option value="3">价格升序</option>
                    <option value="4">价格降序</option>
                </select>
            </div>
        </div>
        <hr>

        <div style="width: 100%">

            <div class="card order-item-block">
                <div class="card-header row justify-content-start">
                    <p class="col-3">2020-12-10 07:01:10</p>
                    <p class="col-3">订单号： 133814696223</p>
                    <p class="offset-1 col"><a href="#">店铺名称</a></p>
                </div>
                <div class="card-body row">
                    <img src="image/avatar.png" style="width: 100%;height: 100%" class="img-thumbnail align-self-center col-2">
                    <p class="col-6">联想（lenovo）魔方插座插排插板一转四插座魔方转换器 总控开关LX-M801白色</p>

                    <div class="col-1">
                        ￥25.5
                    </div>

                    <div class="col-1">
                        已完成
                    </div>

                    <div class="col-2">
                        <ol style="list-style: none" class="align-self-center align-bottom">
                            <li><a href="#">删除</a> </li>
                            <li><a href="#">详情</a> </li>
                            <li><a href="#">申请售后</a> </li>
                        </ol>
                    </div>

                </div>
            </div>

            <div class="card order-item-block">
                <div class="card-header row justify-content-start">
                    <p class="col-3">2020-12-10 07:01:10</p>
                    <p class="col-3">订单号： 133814696223</p>
                    <p class="offset-1 col"><a href="#">店铺名称</a></p>
                </div>
                <div class="card-body row">
                    <img src="image/avatar.png" style="width: 100%;height: 100%" class="img-thumbnail align-self-center col-2">
                    <p class="col-6">联想（lenovo）魔方插座插排插板一转四插座魔方转换器 总控开关LX-M801白色</p>

                    <div class="col-1">
                        ￥25.5
                    </div>

                    <div class="col-1">
                        已完成
                    </div>

                    <div class="col-2">
                        <ol style="list-style: none" class="align-self-center align-bottom">
                            <li><a href="#">删除</a> </li>
                            <li><a href="#">详情</a> </li>
                            <li><a href="#">申请售后</a> </li>
                        </ol>
                    </div>

                </div>
            </div>

            <div class="card order-item-block">
                <div class="card-header row justify-content-start">
                    <p class="col-3">2020-12-10 07:01:10</p>
                    <p class="col-3">订单号： 133814696223</p>
                    <p class="offset-1 col"><a href="#">店铺名称</a></p>
                </div>
                <div class="card-body row">
                    <img src="image/avatar.png" style="width: 100%;height: 100%" class="img-thumbnail align-self-center col-2">
                    <p class="col-6">联想（lenovo）魔方插座插排插板一转四插座魔方转换器 总控开关LX-M801白色</p>

                    <div class="col-1">
                        ￥25.5
                    </div>

                    <div class="col-1">
                        已完成
                    </div>

                    <div class="col-2">
                        <ol style="list-style: none" class="align-self-center align-bottom">
                            <li><a href="#">删除</a> </li>
                            <li><a href="#">详情</a> </li>
                            <li><a href="#">申请售后</a> </li>
                        </ol>
                    </div>

                </div>
            </div>

            <div class="card order-item-block">
                <div class="card-header row justify-content-start">
                    <p class="col-3">2020-12-10 07:01:10</p>
                    <p class="col-3">订单号： 133814696223</p>
                    <p class="offset-1 col"><a href="#">店铺名称</a></p>
                </div>
                <div class="card-body row">
                    <img src="image/avatar.png" style="width: 100%;height: 100%" class="img-thumbnail align-self-center col-2">
                    <p class="col-6">联想（lenovo）魔方插座插排插板一转四插座魔方转换器 总控开关LX-M801白色</p>

                    <div class="col-1">
                        ￥25.5
                    </div>

                    <div class="col-1">
                        已完成
                    </div>

                    <div class="col-2">
                        <ol style="list-style: none" class="align-self-center align-bottom">
                            <li><a href="#">删除</a> </li>
                            <li><a href="#">详情</a> </li>
                            <li><a href="#">申请售后</a> </li>
                        </ol>
                    </div>

                </div>
            </div>

            <div class="card order-item-block">
                <div class="card-header row justify-content-start">
                    <p class="col-3">2020-12-10 07:01:10</p>
                    <p class="col-3">订单号： 133814696223</p>
                    <p class="offset-1 col"><a href="#">店铺名称</a></p>
                </div>
                <div class="card-body row">
                    <img src="image/avatar.png" style="width: 100%;height: 100%" class="img-thumbnail align-self-center col-2">
                    <p class="col-6">联想（lenovo）魔方插座插排插板一转四插座魔方转换器 总控开关LX-M801白色</p>

                    <div class="col-1">
                        ￥25.5
                    </div>

                    <div class="col-1">
                        已完成
                    </div>

                    <div class="col-2">
                        <ol style="list-style: none" class="align-self-center align-bottom">
                            <li><a href="#">删除</a> </li>
                            <li><a href="#">详情</a> </li>
                            <li><a href="#">申请售后</a> </li>
                        </ol>
                    </div>

                </div>
            </div>

            <div class="card order-item-block">
                <div class="card-header row justify-content-start">
                    <p class="col-3">2020-12-10 07:01:10</p>
                    <p class="col-3">订单号： 133814696223</p>
                    <p class="offset-1 col"><a href="#">店铺名称</a></p>
                </div>
                <div class="card-body row">
                    <img src="image/avatar.png" style="width: 100%;height: 100%" class="img-thumbnail align-self-center col-2">
                    <p class="col-6">联想（lenovo）魔方插座插排插板一转四插座魔方转换器 总控开关LX-M801白色</p>

                    <div class="col-1">
                        ￥25.5
                    </div>

                    <div class="col-1">
                        已完成
                    </div>

                    <div class="col-2">
                        <ol style="list-style: none" class="align-self-center align-bottom">
                            <li><a href="#">删除</a> </li>
                            <li><a href="#">详情</a> </li>
                            <li><a href="#">申请售后</a> </li>
                        </ol>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>

</div>



<script src="link/jquery-3.5.1.js"></script>
<script src="link/bootstrap-4.5.0-dist/js/bootstrap.bundle.js"></script>
</body>
</html>
