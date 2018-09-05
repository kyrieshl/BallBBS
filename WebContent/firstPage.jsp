<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>BBS</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bottom.css">
  </head>
  
<body>
	<center>
		<%@ include file="top.jsp" %>
		<div id="wholePage">
			<br/><br/><br/><br/><br/><br/><br/><br/>
			<a href="action/allPostAction.action" style="font-size: 24px;font-weight: bold;">Ω¯»ÎBBS</a>
			<br/><br/><br/><br/><br/><br/><br/><br/>
		</div>
		<%@ include file="bottom.html" %>
	</center>
</body>
</html>

