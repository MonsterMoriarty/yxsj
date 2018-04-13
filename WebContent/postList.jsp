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
	<div
		style="background-color: #1d1d1d; width: 100%; height: 40px; margin: 0 0 40px 0;"></div>
	<div
		style="position: fixed; top: 130px; left: 0; background-color: #ff5063; width: 100%; height: 40px; padding: 0 0 0 200px; color: white;">
		<div onclick="window.location='<%=basePath%>index.jsp'"
			style="float: left; line-height: 40px; margin: 0 40px 0 0; cursor: pointer;">首页</div>
		<div style="float: left; line-height: 40px; margin: 0 40px 0 0;">/</div>
		<div style="float: left; line-height: 40px; margin: 0 40px 0 0;">文章列表</div>

	</div>
	<!---------------------------------------------------------------------------------------->
	<div
		style="width: 1200px; margin: 20px 0 0 calc(( 100% - 1200px)/2); float: left;">

		<div
			style="width: 100%; padding: 0 20px; float: left; margin: 0 0 0 0">

			<form action="<%=basePath%>post?option=allPost" method="post">
				<!--  -->
				<button class="my_button_2" style="float: right;">搜索</button>
				<!--  -->
				<input class="form-control" type="text" maxlength="20" name="search"
					value="${search}" 
					style="border-color: #ff5063; color: #ff5063; border-width: 5px; width: 400px; height: 50px; font-size: 22px; border-radius: 0; float: right; margin: 0 40px 0 0;" />

			</form>
		</div>

		<div
			style="width: 100%; padding: 0 20px; float: left; margin: 0 0 20px 0">
			<div
				style="float: left; width: auto; font-size: 22px; font-weight: bold; line-height: 50px; margin: 50px 0 0 0">
				共搜索到<span style="color: #ff5063; margin: 0 10px 0 10px;">${list_num}</span>篇文章
			</div>
			<c:choose>
				<c:when test="${sort eq null}">
					<form action="<%=basePath%>post?option=allPost&sort=time"
						method="post">
						<button type="submit" class="my_button_2"
							style="float: right; width: 200px; margin: 40px 0 20px 40px;">按时间排序</button>
					</form>
					<form action="<%=basePath%>post?option=allPost&sort=hot"
						method="post">
						<button type="submit" class="my_button_1"
							style="width: 200px; float: right; margin: 40px 0 20px 0;">按热度排序</button>
					</form>
				</c:when>
				<c:when test="${sort eq 'hot'}">
					<form action="<%=basePath%>post?option=allPost&sort=time"
						method="post">
						<button type="submit" class="my_button_1"
							style="float: right; width: 200px; margin: 40px 0 20px 40px;">按时间排序</button>
					</form>
					<form action="<%=basePath%>post?option=allPost&sort=hot"
						method="post">
						<button type="submit" class="my_button_2"
							style="width: 200px; float: right; margin: 40px 0 20px 0;">按热度排序</button>
					</form>
				</c:when>
				<c:when test="${sort eq 'time'}">
					<form action="<%=basePath%>post?option=allPost&sort=time"
						method="post">
						<button type="submit" class="my_button_2"
							style="float: right; width: 200px; margin: 40px 0 20px 40px;">按时间排序</button>
					</form>
					<form action="<%=basePath%>post?option=allPost&sort=hot"
						method="post">
						<button type="submit" class="my_button_1"
							style="width: 200px; float: right; margin: 40px 0 20px 0;">按热度排序</button>
					</form>
				</c:when>
				<c:otherwise>

				</c:otherwise>
			</c:choose>


		</div>

		<c:forEach items="${list}" var="list">

			<!---------------------------------------------------------------------------->
			<div
				style="height: 520px; width: 360px; margin: 0 20px 70px 20px; float: left;">
				<img src="<%=basePath%>img?img=${list.post.post_img}" width="100%"
					onclick="window.location='<%=basePath%>post?option=detailPost&yxsj_post_id=${list.post.yxsj_post_id}'"
					style="cursor: pointer;" height="200px" />
				<div class="bianfen"
					onclick="window.location='<%=basePath%>post?option=detailPost&yxsj_post_id=${list.post.yxsj_post_id}'"
					style="font-size: 18px; font-weight: bold; margin: 20px 0;">${list.post.post_title}</div>
				<div class="bianfen"
					onclick="window.location='<%=basePath%>post?option=detailPost&yxsj_post_id=${list.post.yxsj_post_id}'"
					style="font-size: 16px; letter-spacing: 1px; word-spacing: 5px; overflow: hidden; height: 180px;">${list.post.post_content}</div>
				<div style="margin: 20px 0;">
					<span style="color: #c4bdbd;">by</span>
					<!---->
					<span class="bianfen"
						onclick="window.location='<%=basePath%>post?option=poplePost&yxsj_user_id=${list.post.post_author}'"
						style="margin: 0 0 0 5px; border-bottom: 1px solid #ff5063; padding-bottom: 2px;">${list.user.user_nickname}</span>
					<!--  -->
					<span style="margin: 0 10px 0 20px;">❤</span><span class="bianfen"
						style="border-bottom: 1px solid #ff5063; padding-bottom: 2px; cursor: pointer; margin: 0 40px 0 0;">${list.post.post_num_comment}</span>
					<!---->
					<span style="float: right; color: #c4bdbd;">${list.post.post_gmt_create}</span>
				</div>
			</div>
			<!---------------------------------------------------------------------------->
		</c:forEach>
	</div>

</body>
</html>