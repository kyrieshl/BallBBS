<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="entity.*"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>播放</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/codehome.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/responsiveslides.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bottom.css">
</head>
<body>
	<%@include file="top.jsp" %>
	<center>
	<object type="application/x-shockwave-flash" data="tools/vcastr3.swf"
		width="650" height="500" id="vcastr3">
		<param name="movie" value="tools/vcastr3.swf" />
		<param name="allowFullScreen" value="true" />
		<param name="FlashVars"
			value="xml=  
	<vcastr> 
	<channel> 
	<item> 
	<source><%=basePath %>${media.src}</source>  
	</item> 
	</channel>  
	</vcastr>" />
	</object>
	</center>
	<%@include file="bottom.html" %>
</body>
</html>