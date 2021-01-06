<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
    <script src="link/jquery-3.5.1.js"></script>
</head>
<body>
<form action="/testImage" enctype="multipart/form-data" method="post">
    <input type="file" name="file"><br>
    <input type="submit" value="提交">
</form>
</body>
</html>