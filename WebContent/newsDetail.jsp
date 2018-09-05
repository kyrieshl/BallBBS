<%@page import="org.jsoup.select.Elements"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>体育新闻</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/codehome.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/responsiveslides.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/newsDetail.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bottom.css">
</head>
<body>

<%@include file="top.jsp" %>
<%
	Elements link = (Elements)session.getAttribute("currentLink");
	List<String> list1 = (List<String>)session.getAttribute("currentList1");
	List<String> list2 = (List<String>)session.getAttribute("currentList2");
%>
<div class="whole-wrapper">
<%=link %>
<div class="hours24-top">
	<div class="hd">
        <h2 style="color:navy">24小时新闻排行榜</h2>
	</div>
	<div class="bd">
		<ul class="list">
			<%
				for(int i=0;i<list1.size();i++){
			%>
			<li>
				<span class="number"><%=i+1 %>.</span>
				<a href="showPopularNewsDetailAction.action?popularHrefString=<%=list1.get(i) %>" style="text-decoration:none;font-size:12px;color:gray"><%=list2.get(i) %></a>
			</li>
			<%
				}
			%>
		</ul>
	</div>
</div>
</div>
<%@include file="bottom.html" %>
</body>
</html>