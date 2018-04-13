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
<title>Insert title here</title>
</head>
<body>
	<!---------------------------------------------------------------------------------------->
	<jsp:include page="/navbar.jsp" flush="true"></jsp:include>
	<!--  -->
	<div
		style="background-color: #1d1d1d; width: 100%; height: 40px; margin: 0 0 40px 0;"></div>
	<div
		style="position: fixed; top: 130px; left: 0; background-color: #ff5063; width: 100%; height: 40px; padding: 0 0 0 200px; color: white;">
		<div onclick="window.location='<%=basePath%>index.jsp'"
			style="float: left; line-height: 40px; margin: 0 40px 0 0; cursor: pointer;">首页</div>
		<div style="float: left; line-height: 40px; margin: 0 40px 0 0;">/</div>
		<div style="float: left; line-height: 40px; margin: 0 40px 0 0;">${postList_user.user.user_nickname}的文章</div>

	</div>
	<!---------------------------------------------------------------------------------------->
	<div style="width: 720px; margin: 50px 0 0 380px; float: left;">

		<c:forEach items="${postList_user.postList}" var="postList">
			<div
				style="height: 520px; width: 320px; margin: 0 20px 70px 20px; float: left;">
				<img src="<%=basePath%>img?img=${postList.post_img}" width="100%"
					height="200px" style="cursor: pointer;"
					onclick="window.location='<%=basePath%>post?option=detailPost&yxsj_post_id=${postList.yxsj_post_id}'" />
				<!-- 标题-->
				<div class="bianfen"
					style="font-size: 18px; font-weight: bold; margin: 20px 0;"
					onclick="window.location='<%=basePath%>post?option=detailPost&yxsj_post_id=${postList.yxsj_post_id}'">${postList.post_title}</div>
				<!-- 内容-->
				<div class="bianfen"
					onclick="window.location='<%=basePath%>post?option=detailPost&yxsj_post_id=${postList.yxsj_post_id}'"
					style="font-size: 16px; letter-spacing: 1px; word-spacing: 5px; overflow: hidden; height: 180px;">${postList.post_content}</div>
				<div style="margin: 20px 0;">
					<span style="color: #c4bdbd;">by</span>
					<!-- 昵称 -->
					<span class="bianfen"
						onclick="window.location='<%=basePath%>post?option=poplePost&yxsj_user_id=${postList.post_author}'"
						style="margin: 0 0 0 5px; border-bottom: 1px solid #ff5063; padding-bottom: 2px;">${postList_user.user.user_nickname}</span>
					<!-- 时间-->
					<span style="float: right; color: #c4bdbd;">${postList.post_gmt_create}</span>
				</div>
			</div>
		</c:forEach>


	</div>
	<!--------------------------------------------------------------------------------------------------------->
	<div
		style="padding: 40px; float: right; width: 750px; height: 280px; margin: 50px 0 0 0; background-color: #f5f5f5;">
		<img src="<%=basePath%>img?img=${postList_user.user.user_img}"
			width="200px" height="200px" style="float: left; border-radius: 50%;" />
		<div style="width: calc(100% - 220px); height: 200px; float: right;">
			<!-- 昵称 -->
			<div class="bianfen" style="font-size: 22px; font-weight: bold;">${postList_user.user.user_nickname}</div>
			<div style="width: 30%; font-size: 18px; margin: 20px 0;">
				文章数<span class="badge pull-right" style="font-size: 18px;">${postList_user.user.user_num_post}</span>
			</div>
			<div class="bianfen"
				style="width: 70%; font-size: 18px; overflow: hidden; height: 100px;">${postList_user.user.user_Introduction}
			</div>
		</div>
	</div>
</body>
</html>