<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>注册</title>
    <!-- 响应式标签 -->
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="link/bootstrap-4.5.0-dist/css/bootstrap.min.css">
    <script type="text/javascript" src="link/jquery-3.5.1.js"></script>
    <script type="text/javascript" src="js/loginRegister.js"></script>
    <style>
        #title {
            text-align: center;
            margin-top: 100px;
        }

        .form-group {
            margin: 15px 0;
        }

        #verifycode {
            width: 120px;
            height: 50px;
        }
    </style>
</head>
<body>
<h2 id="title">注册</h2>
<form class="form-inline needs-validation" name="registerForm" enctype="multipart/form-data" method="post"
      action="user/register">
    <div class="container form-content justify-content-center">
        <div class="row form-row form-group">
            <label class="col-1 form-name offset-3">用户名</label>
            <input type="text" id="username" class="col-3 form-item-input form-control" name="username" required>
            <small class="text-muted mx-sm-3" id="checkResult">
                Must be 8-20 characters long.
            </small>
        </div>

        <div class="row form-row form-group">
            <label class="col-1 form-name offset-3">密码</label>
            <input type="password" id="password" class="col-3 form-item-input form-control" name="password" required>
            <small class="text-muted mx-sm-3">
                Must be 8-20 characters long.
            </small>
        </div>

        <div class="row form-row form-group">
            <label class="col-1 form-name offset-3">重复密码</label>
            <input type="password" id="repassword" class="col-3 form-item-input form-control" required>
            <small class="text-muted mx-sm-3">
                Must be 8-20 characters long.
            </small>
        </div>


        <div class="row form-row form-group">
            <label class="col-1 form-name offset-3">性别</label>
            <fieldset class="form-group">
                <div class="col-3">
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="sex" id="gridRadios1" value="男" checked>
                        <label class="form-check-label" for="gridRadios1">男</label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="sex" id="gridRadios2" value="女">
                        <label class="form-check-label" for="gridRadios2">女</label>
                    </div>
                </div>
            </fieldset>
        </div>

        <div class="row form-row form-group">
            <label class="col-1 form-name offset-3">邮箱</label>
            <input type="text" class="col-3 form-item-input form-control" id="email" name="email" required>
        </div>

        <div class="row form-row form-group">
            <label class="col-1 form-name offset-3">手机号</label>
            <input type="text" class="col-3 form-item-input form-control" id="phonenubmer" name="phonenumber" required>
        </div>

        <div class="row form-row form-group">
            <label class="col-1 form-name offset-3">选择头像</label>
            <div class="col-3">
                <input type="file" class="custom-file-input" id="customFile" name="avatar">
                <label class="custom-file-label" for="customFile">选择头像</label>
            </div>
        </div>


        <div class="row form-row form-group">
            <label class="col-1 form-name offset-3">验证码</label>
            <input type="text" id="verifycodes" class="col-3 form-item-input form-control" name="veritycode" required>
            <img src="${path}createCode/auto" id="verifycode" class="mx-sm-3">
        </div>

        <div class="row form-group form-row">
            <div class="col-7 form-check justify-content-end">

                <input class="form-check-input" type="checkbox" name="treatment">
                <label class="form-check-label">
                    我已阅读并同意<a href="#">用户协议</a>
                </label>
            </div>
        </div>

        <div class="row form-group form-row">
            <div class="col-4 offset-3">
                <button id="register" type="button" class="btn btn-primary btn-block">注册</button>
            </div>
        </div>
    </div>
</form>
</body>
</html>