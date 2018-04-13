<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<%=basePath%>js/jquery-3.1.1.min.js"></script>
<link rel="stylesheet" href="<%=basePath%>css/bootstrap.min.css">
<script type="text/javascript" src="<%=basePath%>js/bootstrap.min.js"></script>
<!--  -->

<title>Insert title here</title>
</head>
<body style="background-color: #2b333e; color: white;">

	<div
		style="margin: 200px auto 40px; height: 40px; font-size: 30px; color: white; text-align: center;">游戏世界管理系统</div>

	<input class="form-control" type="text"
		style="width: 500px; height: 50px; font-size: 22px; margin: 0px auto 0;"
		placeholder="管理员账号" />
	<!--  -->
	<input class="form-control" type="password"
		style="width: 500px; height: 50px; font-size: 22px; margin: 50px auto 50px;"
		placeholder="口令" />
	<button class="btn btn-primary" onclick="window.location='<%=basePath%>post?option=adminFindPost&currPage=1'"
		style="float: right; margin: 0 calc(( 100% - 500px)/2) 0 0; width: 100px; height: 40px; font-size: 20px;">登录</button>
	<button class="btn btn-default" onclick="window.location='<%=basePath%>index.jsp'"
		style="float: right; margin: 0 30px 0 calc(( 100% - 500px)/2); width: 100px; height: 40px; font-size: 20px;">返回</button>
</body>
</html>