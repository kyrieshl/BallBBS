<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<title>�û���¼</title>
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
			<p>��̳��½</p>
			<form id="login" method="post" action="managerLoginAction">
				<s:fielderror />
				����Ա:&nbsp;<input type="text" class="kuang2" name="manager.managerName"/><br/>
				��&nbsp;&nbsp;��:&nbsp;<input type="password" class="kuang2"
					name="manager.managerPassword"/><br />
					<input type="submit" id="logbutton" value="��¼"/>
				<a href="http://localhost:8080/BallBBS/allPostAction.action" style="text-decoration:none;color:gray">������ҳ</a>
			</form>
		</div>
	</div>
</body>
</html>
