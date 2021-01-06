<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>登陆</title>
    <!-- 响应式标签 -->
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="link/bootstrap-4.5.0-dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/login.css" type="text/css">
    <script type="text/javascript" src="link/jquery-3.5.1.js"></script>
    <script type="text/javascript" src="js/loginRegister.js"></script>
</head>
<body>
<form class="form-signin" name="loginForm" action="user/login" method="post">
    <div class="text-center mb-4">
        <img class="mb-4" src="image/logo.jpg" alt="" width="72" height="72">
        <h1 class="h3 mb-3 font-weight-normal">欢迎回来</h1>
    </div>
    <div class="form-label-group">
        <input type="text" id="loginAccount" class="form-control" name="name" placeholder="Account" required>
        <label for="inputPassword">请输入用户名</label>
    </div>


    <div class="form-label-group">
        <input type="password" id="loginPassword" class="form-control" placeholder="Password" required>
        <label for="inputPassword">请输入密码</label>
    </div>

    <div class="checkbox mb-3">
        <label>
            <input type="checkbox" value="remember-me"> 记住密码
        </label>
    </div>
    <button class="btn btn-lg btn-primary btn-block" type="button" id="login">登陆</button>
    <p class="mt-5 mb-3 text-muted text-center">&copy; 2017-2020</p>
</form>
</body>
</html>