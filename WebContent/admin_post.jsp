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
<!--  -->
<script type="text/javascript" src="<%=basePath%>js/jquery-3.1.1.min.js"></script>
<link rel="stylesheet" href="<%=basePath%>css/bootstrap.min.css">
<script type="text/javascript" src="<%=basePath%>js/bootstrap.min.js"></script>
<!--  -->
<link rel="stylesheet"
	href="<%=basePath%>css/sidebar/chartist-custom.css" />
<link rel="stylesheet"
	href="<%=basePath%>css/sidebar/font-awesome.min.css" />
<link rel="stylesheet" href="<%=basePath%>css/sidebar/main.css" />
<link rel="stylesheet" href="<%=basePath%>css/sidebar/style.css" />
<link rel="stylesheet" href="<%=basePath%>css/table.css" />
<title>文章管理</title>
</head>
<body>
	<!------------------------------------------------------------------------------------------------------>
	<div id="wrapper">
		<div id="sidebar-nav" class="sidebar">
			<div class="sidebar-scroll">

				<ul class="nav">
					<li><a
						href="<%=basePath%>post?option=adminFindPost&currPage=1"
						class="active"><i class="lnr lnr-pencil"></i><span>文章管理</span></a></li>
					<li><a
						href="<%=basePath%>comment?option=adminFindComment&currPage=1"
						class=""><i class="lnr lnr-heart"></i><span>评论管理</span></a></li>
					<li><a
						href="<%=basePath%>user?option=adminFindUser&currPage=1" class=""><i
							class="lnr lnr lnr-user"></i><span>用户管理</span></a></li>

					<li><a href="<%=basePath%>index.jsp" class=""><i
							class="lnr lnr-trash"></i><span>退出系统</span></a></li>
				</ul>

			</div>
		</div>
	</div>
	<!------------------------------------------------------------------------------------------------------>
	<div
		style="width: cale(100% - 260px); margin: 0 0 0 260px; padding: 50px 0 0 0;">
		<!------------------------------------------------------------------------------------------------------>

		<div class="table-responsive"
			style="width: 60%; margin: 0px auto 10px;">
			<div style="font-size: 25px; margin: 0 20px 20px;">文章推荐：</div>
			<table class="table table-striped table-bordered table-hover">
				<tr>
					<th><span>序号</span></th>
					<td><span>图片</span></td>
					<th><span>标题</span></th>
					<th><span>推荐时间</span></th>
					<!-- <th><span>操作</span></th> -->
				</tr>
				<!--  -->
				<c:forEach items="${list_recommend_post}" var="list_recommend_post">
					<tr>
						<td style="vertical-align: middle;"><span>${list_recommend_post.recommend.recommend_rank}</span></td>
						<td style="vertical-align: middle;padding: 20px;"><span> <img
								src="<%=basePath%>img?img=${list_recommend_post.post.post_img}"
								style="height: 150px;"></img>
						</span></td>
						<td style="vertical-align: middle;"><span>${list_recommend_post.post.post_title}</span></td>
						<td style="vertical-align: middle;"><span>${list_recommend_post.recommend.recommend_gmt_modified}</span></td>
						<!-- <td style="width: 100px;"><span><button
									class="btn btn-success" style="margin: 10px; float: left;">取消推荐</button></span></td> -->
					</tr>
				</c:forEach>
				<!--  -->
			</table>
		</div>

		<!------------------------------------------------------------------------------------------------------>
		<div style="margin: 50px 5% 20px 0; width: auto; float: right;">
			<form action="<%=basePath%>post?option=adminFindPost&currPage=1"
				method="post">
				<button class="btn btn-primary" type="submit"
					style="margin: 0 5px 0 5px; float: right;">搜索</button>

				<input value="${search}" class="form-control" type="text"
					name="search" placeholder="搜索标题"
					style="margin: 0 5px 0 5px; width: 200px; float: right;">

			</form>
		</div>

		<div class="table-responsive" style="width: 90%; margin: 10px auto;">
			<table class="table table-striped table-bordered table-hover">
				<tr>
					<th style="vertical-align: middle;"><span>编号</span></th>
					<th style="vertical-align: middle;"><span>标题</span></th>
					<th style="vertical-align: middle;"><span>作者</span></th>
					<th style="vertical-align: middle;"><span>评论数</span></th>
					<th style="vertical-align: middle;"><span>图片</span></th>
					<th style="vertical-align: middle;"><span>发表时间</span></th>
					<th style="vertical-align: middle;"><span>操作</span></th>
				</tr>
				<c:forEach items="${page.list}" var="list">
					<tr>
						<td style="vertical-align: middle;"><span>${list.post.yxsj_post_id}</span></td>
						<td style="vertical-align: middle;"><span>${list.post.post_title}</span></td>
						<td style="vertical-align: middle;"><span>${list.user.user_nickname}</span></td>
						<td style="vertical-align: middle;"><span>${list.post.post_num_comment}</span></td>
						<td style="vertical-align: middle;padding: 20px;"><span><img
								src="<%=basePath%>img?img=${list.post.post_img}"
								style="height: 100px;"></img></span></td>
						<td style="vertical-align: middle;"><span>${list.post.post_gmt_create}</span></td>
						<td style="width: 220px; vertical-align: middle;"><span><button
									id="${list.post.yxsj_post_id}"
									onclick="javascript:top_post_id=this.id;" data-toggle="modal"
									data-target="#model_top" class="btn btn-success"
									style="margin: 10px; float: left;">推荐</button>
								<button class="btn btn-danger"
									style="margin: 10px; float: right;"
									onclick="window.location='<%=basePath%>post?option=adminDeletePost&yxsj_post_id=${list.post.yxsj_post_id}'">删除</button></span></td>
					</tr>
				</c:forEach>

			</table>
		</div>

		<!------------------------------------------------------------------------------------------------------>
		<div style="margin: 0 auto; width: 400px; text-align: center;">

			<button class="btn btn-primary"
				onclick="window.location='<%=basePath%>post?option=adminFindPost&currPage=1'">首页</button>

			<c:choose>
				<c:when test="${page.currPage eq 1 }">
					<button class="btn btn-primary" onclick="window.location='#'">上一页</button>
				</c:when>
				<c:otherwise>
					<button class="btn btn-primary"
						onclick="window.location='<%=basePath%>post?option=adminFindPost&currPage=${page.currPage-1}'">上一页</button>
				</c:otherwise>
			</c:choose>

			<c:choose>
				<c:when test="${page.currPage eq page.totalPage }">
					<button class="btn btn-primary" onclick="window.location='#'">下一页</button>
				</c:when>
				<c:otherwise>
					<button class="btn btn-primary"
						onclick="window.location='<%=basePath%>post?option=adminFindPost&currPage=${page.currPage+1}'">下一页</button>
				</c:otherwise>
			</c:choose>




			<button class="btn btn-primary"
				onclick="window.location='<%=basePath%>post?option=adminFindPost&currPage=${page.totalPage}'">尾页</button>
		</div>
		<!-------------------------------------------------------------------------------------------------------------------------------------------------------->

		<div
			style="margin: 20px auto 100px; width: 200px; text-align: center;">
			第 ${page.currPage}页<br>共 ${page.totalPage}页<br>共
			${page.totalCount}条记录
		</div>
	</div>
	<!------------------------------------------------------------------------------------------------------>
	<div class="modal fade" id="model_top">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title">文章置顶</h4>
				</div>
				<div class="modal-body">
					<h4>请选择推荐的位置</h4>
					<select class="form-control" id="recommend_rank"
						style="width: 200px; height: 40px; text-align: center; font-size: 20px; margin: 20px auto;">
						<option>1</option>
						<option>2</option>
						<option>3</option>
					</select>
				</div>
				<div class="modal-footer">
					<button class="btn btn-default" data-dismiss="modal">取消</button>
					<button class="btn btn-danger" onclick="recommend()">推荐</button>
				</div>
			</div>
		</div>
	</div>
</body>

<script type="text/javascript">
	var top_post_id;

	function recommend() {

		var recommend_rank = document.getElementById("recommend_rank");

		var str = "/yxsj/post?option=recommendPost&yxsj_post_id=" + top_post_id
				+ "&recommend_rank=" + recommend_rank.value;

		window.location = str;

	}
</script>
</html>