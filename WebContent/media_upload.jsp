<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>视频上传</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/codehome.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/responsiveslides.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bottom.css">
</head>
<body>
<%
	String message = (String)session.getAttribute("message");
%>
<%@include file="top.jsp" %>
<center>
<div class="top">
</div>
<s:if test="%{#session.message != null}">
<%=message %>
</s:if>
<div class="wrapper">
<br/>
<s:actionmessage/>
<form action="uploadFile.jsp" method="post" enctype="multipart/form-data">
<table class = "uploadTable" border="1px">
<tr>
<td style="background-color: gray;">选择视频</td>
<td><input type="file" name="vedioFile" /></td>
</tr>
<tr>
<td style="background-color: gray;">视频标题</td>
<td><input type="text" name="title" /></td>
</tr>
<tr>
<td style="background-color: gray;">视频描述</td>
<td><textarea name="descript"></textarea></td>
</tr>
<tr>
<td style="background-color: gray;">&nbsp;&nbsp;&nbsp;</td>
<td>
<s:if test="%{#session.loginUser == null}">
<input type="submit" value="上传视频" disabled="disabled"/>
<input type="reset" value="重新选择"/>
</s:if>
<s:else>
<input type="submit" value="上传视频"/>
<input type="reset" value="重新选择"/>
</s:else>
</td>
</tr>
<tr>
<td><a href="showMediaList.action?pageNumber=1">进入视频专区</a>
</td>
<td>
<s:if test="%{#session.loginUser == null}">
<font color="gray">上传视频请先登录</font>
</s:if>
</td>
</tr>
</table>
</form>
</div>
</center>
<br/><br/><br/><br/><br/><br/>
<%
	session.removeAttribute("message");
%>
</body>
<%@include file="bottom.html" %>
</html>