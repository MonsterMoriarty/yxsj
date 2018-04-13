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
<link rel="stylesheet" href="<%=basePath%>css/toastr.css" />
<script type="text/javascript" src="<%=basePath%>js/toastr.js"></script>
<!------------------------------------------------------------------------------>
<link rel="stylesheet" href="<%=basePath%>css/navbar.css" />
<title>Insert title here</title>
</head>
<body>
	<!-------------------------------------------------------------->
	<!-------------------------------------------------------------->
	<!----------------------------------onclick="window.location='<%=basePath%>login.jsp'"---------------------------->
	<div class="navbar-top">
		<div
			style="line-height: 40px; float: left; color: #787784; margin: 0 0 0 150px;">Welcome
			to the world of gaming news!</div>
		<c:choose>
			<c:when test="${sessionScope.user eq null}">
				<span class="dl_zc"
					style="line-height: 40px; float: right; margin: 0 200px 0 0; cursor: pointer;"
					data-toggle="modal" data-target="#modal_register">注册</span>
				<span class="dl_zc"
					style="line-height: 40px; float: right; margin: 0 30px 0 0; cursor: pointer;"
					data-toggle="modal" data-target="#modal_login">登录</span>
			</c:when>
			<c:otherwise>
				<span class="dl_zc"
					style="line-height: 40px; float: right; margin: 0 200px 0 0; cursor: pointer;"
					onclick="window.location='<%=basePath%>user?option=logout'">注销</span>
				<span class="dl_zc"
					style="line-height: 40px; float: right; margin: 0 30px 0 0; cursor: pointer;"
					onclick="window.location='<%=basePath%>user?option=my&yxsj_user_id=${sessionScope.user.yxsj_user_id}'">${sessionScope.user.user_nickname}</span>
			</c:otherwise>
		</c:choose>
	</div>
	<!-------------------------------------------------------------->
	<div class="navbar">
		<div class="navbar-title bianfen"
			onclick="window.location='<%=basePath%>index.jsp'">游戏世界</div>
		<div style="float: right; margin: 0 300px 0 0;">

			<div class="navbar-list"
				onclick="window.location='<%=basePath%>post?option=allPost'">全部文章</div>
			<c:choose>
				<c:when test="${sessionScope.user eq null}">
					<div class="navbar-list"
						onclick="javascript:$('#modal_login').modal('show');">发表文章</div>
					<div class="navbar-list"
						onclick="javascript:$('#modal_login').modal('show');">我的文章</div>
				</c:when>
				<c:otherwise>
					<div class="navbar-list"
						onclick="window.location='<%=basePath%>newPost.jsp'">发表文章</div>
					<div class="navbar-list"
						onclick="window.location='<%=basePath%>post?option=poplePost&yxsj_user_id=${sessionScope.user.yxsj_user_id}'">我的文章</div>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
	<div style="background-color: #1d1d1d; width: 100%; height: 130px;"></div>
	<!-------------------------------------------------------------->
	<!-------------------------------------------------------------->
	<!-------------------------------------------------------------->



	<!----登录---------------------------------------------------------------------------------------------------------------------------------------------------------->
	<div class="modal fade " id="modal_login">
		<div class="modal-dialog ">
			<div class="modal-content">
				<!-- 模态弹出窗内容 -->
				<!--弹出框头部，一般使用“modal-header”表示，主要包括标题和关闭按钮-->
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						style="margin: 5px 10px 0 0;">
						<span style="color: red;" aria-hidden="true">X</span> <span
							class="sr-only">Close</span>
					</button>
					<h2 class="modal-title" style="text-align: center;">登录</h2>
				</div>
				<form id="form_login" action="<%=basePath%>user?option=login"
					method="post">
					<!--弹出框主体，一般使用“modal-body”表示，弹出框的主要内容-->
					<div class="modal-body">
						<div style="margin: 40px 0;">
							<input autocomplete="off" autocomplete="off" maxlength="20"
								style="margin: 10px 0 40px 0; border-width: 3px; height: 50px; font-size: 22px; width: 90%; margin: 0 auto; color: #ff5063;"
								class="form-control" name="user_account"
								id="input_login_account" type="text" placeholder="账号">
						</div>
						<div style="margin: 50px 0 20px 0;">
							<input autocomplete="off" maxlength="20"
								style="margin: 10px 0 40px 0; border-width: 3px; height: 50px; font-size: 22px; width: 90%; margin: 0 auto; color: #ff5063;"
								class="form-control" name="user_password"
								id="input_login_password" type="password" placeholder="密码">
						</div>
					</div>
					<!--弹出框脚部，一般使用“modal-footer”表示，主要放置操作按钮-->
				</form>
				<div class="modal-footer">
					<button class="my_button_1" data-dismiss="modal">取消</button>
					<button class="my_button_2" style="margin: 10px 20px 20px 20px;"
						onclick="login_verificationLogin()">登录</button>
				</div>

			</div>
		</div>
	</div>
	<!-------------------------------------------------------------------------------------------------------------------------------------------------------------->
	<!----注册---------------------------------------------------------------------------------------------------------------------------------------------------------->
	<div class="modal fade " id="modal_register">
		<div class="modal-dialog ">
			<div class="modal-content">
				<!-- 模态弹出窗内容 -->
				<!--弹出框头部，一般使用“modal-header”表示，主要包括标题和关闭按钮-->
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						style="margin: 5px 10px 0 0;">
						<span style="color: red;" aria-hidden="true">X</span> <span
							class="sr-only">Close</span>
					</button>
					<h2 class="modal-title" style="text-align: center;">注册新用户</h2>
				</div>
				<form id="form_register" action="<%=basePath%>user?option=register"
					method="post">
					<!--弹出框主体，一般使用“modal-body”表示，弹出框的主要内容-->
					<div class="modal-body">
						<div style="margin: 40px 0;">
							<input autocomplete="off" autocomplete="off" maxlength="20"
								style="margin: 10px 0 40px 0; border-width: 3px; height: 50px; font-size: 22px; width: 90%; margin: 0 auto; color: #ff5063;"
								class="form-control" name="user_account"
								id="input_register_account" type="text" placeholder="账号">
						</div>
						<div style="margin: 40px 0;">
							<input autocomplete="off" autocomplete="off" maxlength="20"
								style="margin: 10px 0 40px 0; border-width: 3px; height: 50px; font-size: 22px; width: 90%; margin: 0 auto; color: #ff5063;"
								class="form-control" name="user_nickname"
								id="input_register_nickname" type="text" placeholder="昵称">
						</div>
						<div style="margin: 40px 0;">
							<input autocomplete="off" autocomplete="off" maxlength="20"
								style="margin: 10px 0 40px 0; border-width: 3px; height: 50px; font-size: 22px; width: 90%; margin: 0 auto; color: #ff5063;"
								class="form-control" name="user_password"
								id="input_register_password" type="password" placeholder="密码">
						</div>
						<div style="margin: 50px 0 20px 0;">
							<input autocomplete="off" maxlength="20"
								style="margin: 10px 0 40px 0; border-width: 3px; height: 50px; font-size: 22px; width: 90%; margin: 0 auto; color: #ff5063;"
								class="form-control" name="" id="input_register_sure"
								type="password" placeholder="确认密码">
						</div>
					</div>
					<!--弹出框脚部，一般使用“modal-footer”表示，主要放置操作按钮-->
				</form>
				<div class="modal-footer">
					<button class="my_button_1" data-dismiss="modal">取消</button>
					<button class="my_button_2" style="margin: 10px 20px 10px 20px;"
						onclick="register_verificationLogin()">注册</button>
				</div>

			</div>
		</div>
	</div>
	<!-------------------------------------------------------------------------------------------------------------------------------------------------------------->

	<script type="text/javascript">
		function login_verificationLogin() {

			var input_login_account = document
					.getElementById("input_login_account");
			var input_login_password = document
					.getElementById("input_login_password");

			if (input_login_account.value == null
					|| input_login_account.value == "") {
				toastr.warning("账号不能为空");
				return;
			}
			if (input_login_password.value == null
					|| input_login_password.value == "") {
				toastr.warning("密码不能为空");
				return;
			}

			var xhr = false;
			xhr = new XMLHttpRequest();
			xhr.onreadystatechange = function() {
				var message;
				if (xhr.readyState == 4) {
					if (xhr.status == 200) {
						message = xhr.responseText;
						if (message != "账号或密码错误") {
							toastr.success("登陆成功");
							window.location = "/yxsj/post?option=poplePost&yxsj_user_id="
									+ message;
							/* post?option=poplePost&yxsj_user_id= */
						} else {
							toastr.error(message);
						}

					} else {
						toastr.error(xhr.status);
					}
				}
			}
			xhr.open("POST", "/yxsj/user?option=verification_login", true);
			xhr.setRequestHeader("Content-type",
					"application/x-www-form-urlencoded");
			var str = "user_account=" + input_login_account.value
					+ "&user_password=" + input_login_password.value;
			xhr.send(str);
		}
	</script>

	<script type="text/javascript">
		function register_verificationLogin() {
			/*  */
			var input_register_account = document
					.getElementById("input_register_account");

			var input_register_nickname = document
					.getElementById("input_register_nickname");

			var input_register_password = document
					.getElementById("input_register_password");

			var input_register_sure = document
					.getElementById("input_register_sure");
			/*  */
			if (input_register_account.value == null
					|| input_register_account.value == "") {
				toastr.warning("账号不能为空");
				return;
			}
			if (input_register_nickname.value == null
					|| input_register_nickname.value == "") {
				toastr.warning("昵称不能为空");
				return;
			}
			if (input_register_password.value == null
					|| input_register_password.value == "") {
				toastr.warning("密码不能为空");
				return;
			}
			if (input_register_sure.value == null
					|| input_register_sure.value == "") {
				toastr.warning("请重复输入密码");
				return;
			}
			/*  */
			if (input_register_password.value != input_register_sure.value) {
				toastr.warning("两次密码不一致");
				return;
			}
			/*  */
			var xhr = false;
			xhr = new XMLHttpRequest();
			xhr.onreadystatechange = function() {
				var message;
				if (xhr.readyState == 4) {
					if (xhr.status == 200) {
						message = xhr.responseText;
						if (message != "账号或昵称已存在") {
							toastr.success("登陆成功");
							window.location = "/yxsj/user?option=my&yxsj_user_id="
									+ message;

						} else {
							toastr.error(message);
						}

					} else {
						toastr.error(xhr.status);
					}
				}
			}
			xhr.open("POST", "/yxsj/user?option=verification_register", true);
			xhr.setRequestHeader("Content-type",
					"application/x-www-form-urlencoded");
			var str = "user_account=" + input_register_account.value
					+ "&user_nickname=" + input_register_nickname.value
					+ "&user_password=" + input_register_password.value;

			xhr.send(str);
		}
	</script>
</body>
</html>