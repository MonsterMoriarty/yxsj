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
	<jsp:include page="/navbar.jsp" flush="true"></jsp:include><!--  -->
	<div
		style="background-color: #1d1d1d; width: 100%; height: 40px; margin: 0 0 40px 0;"></div>
	<div
		style="position: fixed; top: 130px; left: 0; background-color: #ff5063; width: 100%; height: 40px; padding: 0 0 0 200px; color: white;">
		<div onclick="window.location='<%=basePath%>index.jsp'"
			style="float: left; line-height: 40px; margin: 0 40px 0 0; cursor: pointer;">首页</div>
		<div style="float: left; line-height: 40px; margin: 0 40px 0 0;">/</div>
		<div style="float: left; line-height: 40px; margin: 0 40px 0 0;">个人信息</div>

	</div>
	<!---------------------------------------------------------------------------------------->

	<form action="<%=basePath%>user?option=modifyUserInformation"
		method="post" enctype="multipart/form-data">
		<div style="width: 800px; height: 500px; margin: 40px auto;">

			<!---->
			<div style="font-size: 30px; float:;">我的信息</div>
			<hr style="width: 100%; height: 0px; border: 2px solid #eeeeee;">

			<!---->
			<div style="width: 500px; height: 500px; float: left;">
				<div style="font-size: 25px; margin: 20px 0 40px 0;">账号：${user.user_account}</div>
				<div style="font-size: 25px;">昵称：</div>
				<input class="form-control" type="text" name="user_nickname"
					style="margin: 10px 0 40px 0; border-width: 5px; width: 500px; height: 50px; font-size: 22px; border-radius: 0;"
					value="${user.user_nickname}" />
				<div style="font-size: 25px;">个人说明：</div>
				<textarea class="form-control" type="text" name="user_Introduction"
					style="margin: 10px 0 0 0; resize: none; width: 500px; height: 150px; font-size: 22px; border-radius: 0; border-width: 5px;">${user.user_Introduction}</textarea>
				<div class="my_button_1" style="margin: 40px 0 0 0; float: left;"
					data-toggle="modal" data-target="#modal_password">修改密码</div>
				<input type="hidden" value="${user.yxsj_user_id}"
					name="yxsj_user_id">
				<button class="my_button_2"
					style="margin: 40px 0 0 0; float: right;" type="submit">保存修改</button>
			</div>

			<img id="imhhg" src="<%=basePath%>img?img=${user.user_img}"
				style="background-color: #269ABC; height: 200px; width: 200px; border-radius: 50%; float: right;" />
			<div id="div_changeImg" class="my_button_2"
				style="float: right; margin: 30px 0 0 0; width: 200px;"
				onclick="img_click()">修改头像</div>
			<input id="input_file" type="file" onchange="img_change(this)"
				name="user_img" style="display: none;" />
			<script type="text/javascript">
				/*上传图片的JS*/
				function img_click() {
					document.getElementById("input_file").click();
				}

				function img_change(file) {
					var img = document.getElementById("imhhg");
					var reader = new FileReader();
					reader.onload = function(evt) {
						img.src = evt.target.result;
					}

					reader.readAsDataURL(file.files[0]);
				}
			</script>
			<!---->
			<div
				style="float: right; font-size: 20px; width: 200px; margin: 40px 0 0 0; background-color: #EEEEEE; padding: 30px 20px;">
				文章数 <span class="badge pull-right" style="font-size: 20px;">${user.user_num_post}</span>
			</div>

		</div>
	</form>


	<!----注册---------------------------------------------------------------------------------------------------------------------------------------------------------->
	<div class="modal fade " id="modal_password">
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
					<h2 class="modal-title" style="text-align: center;">修改密码</h2>
				</div>
				<form id="form_password" action="<%=basePath%>user?option=password"
					method="post">
					<!--弹出框主体，一般使用“modal-body”表示，弹出框的主要内容-->
					<div class="modal-body">
						<div style="margin: 40px 0;">
							<input autocomplete="off" autocomplete="off" maxlength="20"
								style="margin: 10px 0 40px 0; border-width: 3px; height: 50px; font-size: 22px; width: 90%; margin: 0 auto; color: #ff5063;"
								class="form-control" name="" id="input_login_account"
								type="password" placeholder="原密码">
						</div>
						<div style="margin: 40px 0;">
							<input autocomplete="off" autocomplete="off" maxlength="20"
								style="margin: 10px 0 40px 0; border-width: 3px; height: 50px; font-size: 22px; width: 90%; margin: 0 auto; color: #ff5063;"
								class="form-control" name="user_password"
								id="input_login_account" type="password" placeholder="新密码">
						</div>
						<div style="margin: 50px 0 20px 0;">
							<input autocomplete="off" maxlength="20"
								style="margin: 10px 0 40px 0; border-width: 3px; height: 50px; font-size: 22px; width: 90%; margin: 0 auto; color: #ff5063;"
								class="form-control" name="" id="input_login_password"
								type="password" placeholder="确认密码">
						</div>

					</div>
					<!--弹出框脚部，一般使用“modal-footer”表示，主要放置操作按钮-->
					<input name="yxsj_user_id" type="hidden"
						value="${sessionScope.user.yxsj_user_id}">
				</form>
				<div class="modal-footer">
					<button class="my_button_1" data-dismiss="modal">取消</button>
					<button class="my_button_2" style="margin: 10px 20px 10px 20px;"
						onclick="password_click()">修改</button>
				</div>

			</div>
		</div>
	</div>
	<!-------------------------------------------------------------------------------------------------------------------------------------------------------------->
	<script type="text/javascript">
		/*登录*/
		function password_click() {
			document.getElementById("form_password").submit();
		}
	</script>
</body>
</html>