<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<title><s:property value="user.userName"/>的信息</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/responsiveslides.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/codehome.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bottom.css">
  </head>
  
<body>
	<%@ include file="top.jsp" %>
	<center>
		<div id="wholePage">
			<s:form>
				<s:label label="用户名" name="user.userName" readonly="readonly"></s:label>
				<s:select label="性别" name="user.sex" list="#{'1':'男','2':'女'}" readonly="readonly"></s:select>
				<s:textfield label="邮箱" name="user.userEmail" readonly="readonly" ></s:textfield>
				<s:textarea label="简介" name="user.userRemark" readonly="readonly"></s:textarea>
			</s:form>
		</div>
	</center>
	<br/><br/><br/><br/><br/><br/><br/>
	<%@ include file="bottom.html" %>
	
</body>
</html>
