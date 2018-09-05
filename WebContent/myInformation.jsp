<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<title>个人信息</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/responsiveslides.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/codehome.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bottom.css">
  </head>
  
<body>
	<%@ include file="top.jsp" %>
	<center>
		<div id="wholePage">
			<s:form action="personalManageAction" method="post">
				<s:label label="用户名" value="%{#session.loginUser.userName}" readonly="readonly"></s:label>
				<s:select label="性别" name="user.sex" value="%{#session.loginUser.sex}" list="#{'1':'男','2':'女'}"></s:select>
				<s:textfield label="邮箱" name="user.userEmail" value="%{#session.loginUser.userEmail}"></s:textfield>
				<s:textarea label="简介" name="user.userRemark" value="%{#session.loginUser.userRemark}"></s:textarea>
				<s:submit value="修改"></s:submit>
			</s:form>
		</div>
	</center>
	<br/><br/><br/><br/><br/><br/><br/>
	<%@ include file="bottom.html" %>
</body>
</html>
