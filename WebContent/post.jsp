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
	<%--  --%><jsp:include page="/navbar.jsp" flush="true"></jsp:include><!--  -->
	<div
		style="background-color: #1d1d1d; width: 100%; height: 40px; margin: 0 0 40px 0;"></div>
	<div
		style="position: fixed; top: 130px; left: 0; background-color: #ff5063; width: 100%; height: 40px; padding: 0 0 0 200px; color: white;">
		<div onclick="window.location='<%=basePath%>index.jsp'"
			style="float: left; line-height: 40px; margin: 0 40px 0 0; cursor: pointer;">首页</div>
		<div style="float: left; line-height: 40px; margin: 0 40px 0 0;">/</div>
		<div
			style="float: left; line-height: 40px; margin: 0 40px 0 0; cursor: pointer;"
			onclick="window.location='<%=basePath%>post?option=allPost'">文章列表</div>
		<div style="float: left; line-height: 40px; margin: 0 40px 0 0;">/</div>
		<div style="float: left; line-height: 40px; margin: 0 40px 0 0;">${post_user.post.post_title}</div>

	</div>
	<!---------------------------------------------------------------------------------------->
	<!--正文------------------------------------------------------------------------------------------------------------------------------------------------------>
	<div style="margin: 40px 0 0 400px; float: left; width: 700px;">
		<!-- 标题 -->
		<div style="font-size: 50px;">${post_user.post.post_title}</div>

		<div style="width: 100%; height: 25px; margin: 20px 0;">

			<span style="color: #c4bdbd; float: left;">作者：</span>
			<!-- 作者 -->
			<span class="bianfen"
				onclick="window.location='<%=basePath%>post?option=poplePost&yxsj_user_id=${post_user.post.post_author}'"
				style="margin: 0 20px 0 10px; border-bottom: 1px solid #ff5063; padding-bottom: 2px; float: left;">${post_user.user.user_nickname}</span>
			<!-- 评论数-->
			<span style="margin: 0 10px 0 0; float: left;">❤</span><span
				class="bianfen"
				style="border-bottom: 1px solid #ff5063; padding-bottom: 2px; cursor: pointer; float: left; margin: 0 40px 0 0;">${post_user.post.post_num_comment}</span>
			<span style="float: left; color: #c4bdbd; width: 61%;">${post_user.post.post_gmt_create}</span>
		</div>
		<!-- 图片 -->
		<img src="<%=basePath%>img?img=${post_user.post.post_img}"
			width="100%" style="margin: 10px 0 50px;" />
		<div
			style="letter-spacing: 2px; word-spacing: 5px; text-indent: 40px; line-height: 30px;">${post_user.post.post_content}
		</div>
		<!-------------->

		<hr
			style="width: 100%; float: left; height: 0px; border: 2px solid #eeeeee;">
		<!-------------->
		<div
			style="border: 2px solid #ff5063; padding: 50px; float: left; width: 700px; margin: 20px 0px;">
			<!-- 头像-->

			<img src="<%=basePath%>img?img=${post_user.user.user_img}"
				width="150px" height="150px"
				style="float: left; border-radius: 50%;" />

			<div style="width: 400px; height: 150px; float: right;">
				<!-- 昵称-->
				<div class="bianfen" style="font-size: 22px; font-weight: bold;"
					onclick="window.location='<%=basePath%>post?option=poplePost&yxsj_user_id=${post_user.post.post_author}'">${post_user.user.user_nickname}</div>
				<!-- 个人说明-->
				<div class="bianfen"
					style="width: 100%; font-size: 18px; overflow: hidden; height: 100px; margin: 20px 0 0 0;">
					${post_user.user.user_Introduction}</div>
			</div>
		</div>


		<!--评论遍历------------>
		<c:forEach items="${comment_user_list}" var="comment_user">
			<hr
				style="width: 100%; float: left; height: 0px; border: 2px solid #eeeeee;">
			<div style="float: left; width: 100%; margin: 30px 0;">
				<img src="<%=basePath%>img?img=${comment_user.user.user_img}"
					width="100px" height="100px"
					style="float: left; border-radius: 50%;" />
				<div
					style="width: 580px; height: 100px; float: right; width: calc(100% - 130px);">
					<div class="bianfen"
						onclick="window.location='<%=basePath%>post?option=poplePost&yxsj_user_id=${comment_user.comment.comment_author}'"
						style="font-size: 20px; font-weight: bold; float: left; width: auto;">${comment_user.user.user_nickname}</div>
					<div style="float: right; margin: 0 50px; line-height: 30px;">${comment_user.comment.comment_gmt_create}</div>
					<div
						style="float: left; margin: 10px 0 0 0; letter-spacing: 1px; word-spacing: 5px; max-height: 60px; overflow: hidden; width: 100%;">${comment_user.comment.comment_content}</div>
				</div>
			</div>
		</c:forEach>
		<!-------------->
		<hr
			style="width: 100%; float: left; height: 0px; border: 2px solid #eeeeee;">
		<!---->
		<h2 style="">发表评论</h2>
		<!--评论框-->
		<c:choose>
			<c:when test="${sessionScope.user eq null}">

				<textarea class="form-control post_textarea" name="comment_content"
					style="border-radius: 0; border-width: 3px; width: 100%; height: 150px; margin: 20px auto; resize: none; font-size: 20px; float: left;"></textarea>
				<input name="comment_post" type="hidden"
					value="${post_user.post.yxsj_post_id}">
				<input name="comment_author" type="hidden"
					value="${sessionScope.user.yxsj_user_id}">
				<!--评论按钮-->
				<button id="reply_button" class="my_button_2"
					onclick="javascript:toastr.warning('登录后才可评论');"
					style="cursor: pointer; font-weight: bold; float: right; width: 150px; height: 50px; border: 3px solid #ff5063; font-size: 22px; line-height: 50px; text-align: center; margin: 0 0 30px 0;">评论</button>

			</c:when>
			<c:otherwise>
				<form action="<%=basePath%>comment?option=newComment" method="post">
					<textarea class="form-control post_textarea" name="comment_content"
						style="border-radius: 0; border-width: 3px; width: 100%; height: 150px; margin: 20px auto; resize: none; font-size: 20px; float: left;"></textarea>
					<input name="comment_post" type="hidden"
						value="${post_user.post.yxsj_post_id}"> <input
						name="comment_author" type="hidden"
						value="${sessionScope.user.yxsj_user_id}">
					<!--评论按钮-->
					<button id="reply_button" class="my_button_2" type="submit"
						style="cursor: pointer; font-weight: bold; float: right; width: 150px; height: 50px; border: 3px solid #ff5063; font-size: 22px; line-height: 50px; text-align: center; margin: 0 0 30px 0;">评论</button>
				</form>
			</c:otherwise>
		</c:choose>



		<!-------------->

	</div>
	<!--侧栏------------------------------------------------------------------------------------------------------------------------------------------------------>
	<div
		style="background-color: #f5f5f5; width: 700px; float: right; margin: 40px 0 0 0;">
		<div
			style="font-size: 20px; font-weight: bold; letter-spacing: 5px; float: left; margin: 30px 0 20px 40px;">热门文章</div>
		<!--最热文章------------>
		<c:forEach items="${list_post_user}" var="DTO_post_user">
			<div
				style="height: 150px; width: 90%; float: right; margin: 0 0 20px 0;">
				<img src="<%=basePath%>img?img=${DTO_post_user.post.post_img}"
					width="100px" height="100px"
					onclick="window.location='<%=basePath%>post?option=detailPost&yxsj_post_id=${DTO_post_user.post.yxsj_post_id}'"
					style="float: left; cursor: pointer;" />
				<div class="bianfen"
					onclick="window.location='<%=basePath%>post?option=detailPost&yxsj_post_id=${DTO_post_user.post.yxsj_post_id}'"
					style="float: left; width: 40%; font-size: 18px; font-weight: bold; margin: 10px 0 0 20px; cursor: pointer;">${DTO_post_user.post.post_title}</div>
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
	<!-------------------------------------------------------------->
	<div
		style="background-color: #1d1d1d; height: 100px; width: 100%; float: left; color: #787784; text-align: center; line-height: 100px; margin: 40px 0 0 0;">Copyright
		© 2017 Zhang Bin. All rights reserved.</div>
	<!-------------------------------------------------------------->
</body>
</html>