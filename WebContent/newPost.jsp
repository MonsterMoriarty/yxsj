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
<link rel="stylesheet" href="<%=basePath%>css/newPost.css">
<title>Insert title here</title>
</head>
<body>
	<!---------------------------------------------------------------------------------------->
	<jsp:include page="/navbar.jsp" flush="true"></jsp:include>
	<!---------------------------------------------------------------------------------------->
	<!---------------------------------------------------------------------------------------->
	<div
		style="background-color: #1d1d1d; width: 100%; height: 40px; margin: 0 0 40px 0;"></div>
	<div
		style="position: fixed; top: 130px; left: 0; background-color: #ff5063; width: 100%; height: 40px; padding: 0 0 0 200px; color: white;">
		<div onclick="window.location='<%=basePath%>index.jsp'"
			style="float: left; line-height: 40px; margin: 0 40px 0 0; cursor: pointer;">首页</div>
		<div style="float: left; line-height: 40px; margin: 0 40px 0 0;">/</div>
		<div style="float: left; line-height: 40px; margin: 0 40px 0 0;">发表文章</div>

	</div>
	<!-------------------------------------------------------------------------------------->
	<!---------------------------------------------------------------------------------------->

	<form action="<%=basePath%>post?option=newPost" method="post"
		enctype="multipart/form-data">

		<div style="width: 1200px; float: left;">
			<div
				style="font-size: 25px; width: 600px; margin: 0 auto 0; font-weight: bold;">标题：</div>
			<input class="form-control newPost_input" type="text"
				name="post_title"
				style="border-width: 3px; width: 600px; margin: 20px auto 50px; height: 50px; font-size: 25px;" />
			<div
				style="font-size: 25px; width: 800px; margin: 50px auto 0; font-weight: bold;">正文：</div>
			<textarea class="form-control newPost_input" name="post_content"
				style="border-width: 3px; width: 800px; min-height: 300px; max-height: 400px; margin: 20px auto; resize: vertical; font-size: 20px;"></textarea>
			<!--<div style="width: auto;float: left;font-size: 25px;font-weight: bold;margin: 30px 0 0 200px;">上传图片：</div>
			<input class="btn " type="file" style="width: auto; float: left;font-size: 20px;float: left;margin: 20px 0 0 0px;" />-->
			<!---->
			<input name="post_author" type="hidden"
				value="${sessionScope.user.yxsj_user_id}">
			<!---->
			<button class="my_button_2" type="submit"
				style="width: 200px; float: right; margin: 20px 200px 0 0;">发表</button>
		</div>

		<!-------------------------------------------------------------->
		<img id="img" src="<%=basePath%>img?img=default.jpg"
			style="background-color: #269ABC; height: 400px; width: 400px; float: right; margin: 20px 300px 0 0;" />
		<div id="div_changeImg" class="my_button_2"
			style="float: right; margin: 50px 400px 0 0; width: 200px;"
			onclick="img_click()">上传图片</div>
		<input id="input_file" type="file" onchange="img_change(this)"
			name="post_img" style="display: none;" />
		<script type="text/javascript">
			/*上传图片的JS*/
			function img_click() {
				document.getElementById("input_file").click();
			}

			function img_change(file) {
				var img = document.getElementById("img");
				var reader = new FileReader();
				reader.onload = function(evt) {
					img.src = evt.target.result;
				}
				reader.readAsDataURL(file.files[0]);
			}
		</script>

	</form>
	<!-------------------------------------------------------------->
	<!-------------------------------------------------------------->
	<div
		style="background-color: #1d1d1d; height: 100px; width: 100%; float: left; color: #787784; text-align: center; line-height: 100px; margin: 40px 0 0 0;">Copyright
		© 2017 Zhang Bin. All rights reserved.</div>
	<!-------------------------------------------------------------->
</body>

</html>