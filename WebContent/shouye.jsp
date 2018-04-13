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
<!------------------------------------------------------------------------------>
<link rel="stylesheet" href="<%=basePath%>css/index.css" />
<!------------------------------------------------------------------------------>
<title>首页</title>
</head>
<body>
	<!---------------------------------------------------------------------------------------->
	<jsp:include page="/navbar.jsp" flush="true"></jsp:include>
	<!---------------------------------------------------------------------------------------->
	<div style="height: 600px; width: 100%;">
		<div
			style="float: left; background-image: url(<%=basePath%>img?img=${recommend_post_1.post.post_img}); width: 60%; height: 600px; background-size:100%;">
			<h1 class="bianfen post_title"
				onclick="window.location='<%=basePath%>post?option=detailPost&yxsj_post_id=${recommend_post_1.post.yxsj_post_id}'"
				style="margin: 450px 0 0 70px; float: left;">${recommend_post_1.post.post_title}</h1>
			<div style="width: 100%; float: left;">
				<span style="color: white; margin: 10px 0 0 70px; float: left;">by</span>
				<span class="bianfen top_post"
					onclick="window.location='<%=basePath%>post?option=poplePost&yxsj_user_id=${recommend_post_1.post.post_author}'"
					style="margin: 10px 0 0 10px; float: left;">${recommend_post_1.user.user_nickname}</span>
				<span style="color: white; margin: 10px 0 0 30px; float: left;">${recommend_post_1.post.post_gmt_create}</span>
			</div>
		</div>
		<div
			style="background-color: #8B211E; width: 40%; height: 600px; float: right;">
			<div
				style="background-image: url(<%=basePath%>img?img=${recommend_post_2.post.post_img}); width: 100%; height: 300px;background-size:100%; float: right;">
				<h1 class="bianfen post_title"
					onclick="window.location='<%=basePath%>post?option=detailPost&yxsj_post_id=${recommend_post_2.post.yxsj_post_id}'"
					style="margin: 150px 0 0 50px; float: left;">${recommend_post_2.post.post_title}</h1>
				<div style="width: 100%; float: left;">
					<span style="color: white; margin: 10px 0 0 70px; float: left;">by</span>
					<span class="bianfen top_post"
						onclick="window.location='<%=basePath%>post?option=poplePost&yxsj_user_id=${recommend_post_2.post.post_author}'"
						style="margin: 10px 0 0 10px; float: left;">${recommend_post_2.user.user_nickname}</span>
					<span style="color: white; margin: 10px 0 0 30px; float: left;">${recommend_post_2.post.post_gmt_create}</span>
				</div>
			</div>
			<div
				style="background-image: url(<%=basePath%>img?img=${recommend_post_3.post.post_img}); width: 100%; height: 300px;background-size:100%; float: right;">
				<h1 class="bianfen post_title"
					onclick="window.location='<%=basePath%>post?option=detailPost&yxsj_post_id=${recommend_post_3.post.yxsj_post_id}'"
					style="margin: 150px 0 0 50px; float: left;">${recommend_post_3.post.post_title}</h1>
				<div style="width: 100%; float: left;">
					<span style="color: white; margin: 10px 0 0 70px; float: left;">by</span>
					<span class="bianfen top_post"
						onclick="window.location='<%=basePath%>post?option=poplePost&yxsj_user_id=${recommend_post_3.post.post_author}'"
						style="margin: 10px 0 0 10px; float: left;">${recommend_post_3.user.user_nickname}</span>
					<span style="color: white; margin: 10px 0 0 30px; float: left;">${recommend_post_3.post.post_gmt_create}</span>
				</div>
			</div>
		</div>
	</div>
	<!-------------------------------------------------------------->

	<!-------------------------------------------------------------->
	<div
		style="width: 750px; height: auto; margin: 100px 0 0 10%; float: left;">
		<div style="height: 70px; float: left; width: 100%;">
			<div
				style="font-size: 20px; font-weight: bold; letter-spacing: 5px; float: left; margin: 0 0 0 40px;">最新文章</div>
			<div class="bianfen"
				style="float: right; border-bottom: 1px solid #ff5063; padding-bottom: 3px;"
				onclick="window.location='<%=basePath%>post?option=allPost'">查看所有文章</div>
		</div>


		<div>
			<c:forEach items="${list_time}" var="DTO_post_user">

				<div class="Latest-News">
					<img class="Latest-News-img"
						onclick="window.location='<%=basePath%>post?option=detailPost&yxsj_post_id=${DTO_post_user.post.yxsj_post_id}'"
						src="<%=basePath%>img?img=${DTO_post_user.post.post_img}"
						style="cursor: pointer;"></img>
					<h4 class="bianfen" style="cursor: pointer;"
						onclick="window.location='<%=basePath%>post?option=detailPost&yxsj_post_id=${DTO_post_user.post.yxsj_post_id}'">${DTO_post_user.post.post_title}</h4>
					<span style="color: #c4bdbd;">by</span>
					<!---->
					<span class="bianfen"
						onclick="window.location='<%=basePath%>post?option=poplePost&yxsj_user_id=${DTO_post_user.post.post_author}'"
						style="margin: 0 0 0 5px; border-bottom: 1px solid #ff5063; padding-bottom: 2px;">${DTO_post_user.user.user_nickname}</span>
					<!---->
					<span style="float: right; margin: 0 20px 0 0; color: #c4bdbd;">${DTO_post_user.post.post_gmt_create}</span>
				</div>
			</c:forEach>
		</div>

	</div>
	<!-------------------------------------------------------------->
	<div
		style="background-color: #f5f5f5; width: 37%; float: right; margin: 100px 0 0 0;">
		<div
			style="font-size: 20px; font-weight: bold; letter-spacing: 5px; float: left; margin: 30px 0 20px 40px;">热门文章</div>
		<!--最热文章------------>
		<c:forEach items="${list_comment}" var="DTO_post_user">
			<div
				style="height: 150px; width: 90%; float: right; margin: 0 0 20px 0;">
				<img src="<%=basePath%>img?img=${DTO_post_user.post.post_img}"
					width="100px" height="100px"
					onclick="window.location='<%=basePath%>post?option=detailPost&yxsj_post_id=${DTO_post_user.post.yxsj_post_id}'"
					style="float: left; cursor: pointer;" />
				<div class="bianfen"
					onclick="window.location='<%=basePath%>post?option=detailPost&yxsj_post_id=${DTO_post_user.post.yxsj_post_id}'"
					style="float: left; width: 60%; font-size: 18px; font-weight: bold; margin: 10px 0 0 20px; cursor: pointer;">${DTO_post_user.post.post_title}</div>
				<div
					style="color: #ffcc42; float: left; width: calc(100% - 120px); margin: 5px 0 0 20px;">★★★★★</div>
				<div style="float: left; margin: 5px 0 0 20px;">
					<span style="color: #c4bdbd;">by</span><span class="bianfen"
						onclick="window.location='<%=basePath%>post?option=poplePost&yxsj_user_id=${DTO_post_user.post.post_author}'"
						style="margin: 0 0 0 10px; border-bottom: 1px solid #ff5063; padding-bottom: 2px; cursor: pointer;">${DTO_post_user.user.user_nickname}</span><span
						style="margin: 0 10px 0 20px;">❤</span><span class="bianfen"
						style="border-bottom: 1px solid #ff5063; padding-bottom: 2px; cursor: pointer;">${DTO_post_user.post.post_num_comment}</span>
				</div>
				<div
					style="float: left; margin: 5px 0 0 120px; color: #c4bdbd; width: 500px;">${DTO_post_user.post.post_gmt_create}
				</div>
			</div>
		</c:forEach>
	</div>
	<!-------------------------------------------------------------->
	<!--<div style="background-color: #212128;height: 400px;width: 100%;float: left;margin-top: 100px;"></div>-->
	<div
		style="background-color: #1d1d1d; height: 100px; width: 100%; float: left; color: #787784; text-align: center; line-height: 100px; margin: 20px 0 0 0;">
		Copyright <a href="<%=basePath%>admin_login.jsp">©</a> 2017 Zhang Bin.
		All rights reserved.
	</div>
	<!-------------------------------------------------------------->
</body>
</html>