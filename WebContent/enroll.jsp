<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<title>用户注册</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/register.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bottom.css">
  	<sx:head parseContent="true" extraLocales="UTF-8"/>
  </head>
  
<body>
	<%@include file="top.jsp" %>

	<!-- 注册 -->
	<div id="register">

		<!-- 登陆 -->
		<div id="regist1">
			<p>立即注册</p>
			<a href="login.jsp">已有帐号？现在登陆</a>
		</div>

		<!-- 注册 -->
		<div id="regist2">
			<form action="enrollAction" name="register" method="post" enctype="multipart/form-data">
				<ul>
					<li>
						<p class="red">*</p>用户名:<input type="text" class="kuang1"
						onFocus="abc()" name="user.userName">
						<hr />
					</li>
					<li>
						<p class="red">*</p>密码:<input type="password" class="kuang1"
						name="user.userPassword">
						<hr />
					</li>
					<li>
						<p class="red">*</p>确认密码:<input type="password" class="kuang1"
						name="userRePassword">
						<hr />
					</li>
					<li>
						<p class="red">*</p>性别:&nbsp;<s:select name="user.sex" list="#{'1':'男','2':'女'}"></s:select>
						<hr />
					</li>
					<li>
						<p class="red">*</p>邮箱:&nbsp;<s:textfield name="user.userEmail"></s:textfield>
						<hr />
					</li>
					<li>
						<p class="red">*</p>头像:&nbsp;<s:file name="userImage"  id ="file"/>
						<hr />
					</li>
					<s:fielderror />
					<li>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</li>
					<li><input type="submit" id="rgsbutton" value="注册">
						&nbsp;&nbsp; <input type="checkbox" id="provision">
						<p style="display:inline-block;"><a href="">同意网站服务条款</a></p>
					</li>
				</ul>
			</form>
		</div>
		<%@include file="bottom.html"%>
	</div>
</body>

<%-- <script>
  document.getElementById('file').onchange = function() {
    var imgFile = this.files[0];
    var fr = new FileReader();
    fr.onload = function() {
      document.getElementById('image1').getElementsByTagName('img')[0].src = fr.result;
    };
    fr.readAsDataURL(imgFile);
  };
</script> --%>

</html>
