<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<title>用户登录</title>
	<link href="${pageContext.request.contextPath}/css/login.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bottom.css">
  </head>
  
<body>
<div id="head">
		
	</div>
	<div id="middle">
		<div id="left">
			<img src="http://localhost:8080/BallBBS/image/cat.jpg" style=" width:600px;">
		</div>
		<div id="right">
			<p>论坛登陆</p>
			<form id="login" method="post" action="loginAction">
				<s:actionmessage/>
				<s:fielderror />
				用户名:&nbsp;<input type="text" class="kuang2" name="user.userName"/><br/>
				密&nbsp;&nbsp;码:&nbsp;<input type="password" class="kuang2"
					name="user.userPassword"/><br />
					<input type="submit" id="logbutton" value="登录"/>
				<a href="http://localhost:8080/BallBBS/enroll.jsp" style="text-decoration:none;color:gray">还没账号?</a>
				&nbsp;
				<a href="http://localhost:8080/BallBBS/allPostAction.action" style="text-decoration:none;color:gray">返回首页</a>
			</form>
		</div>
	</div>
</body>
</html>
