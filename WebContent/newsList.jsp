<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="org.jsoup.nodes.Document"%>
<%@ page import="org.jsoup.select.Elements"%>
<%@ page import="org.jsoup.Jsoup"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>体育新闻</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/codehome.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/responsiveslides.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/newsList.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bottom.css">

</head>
<body>
<%@include file="top.jsp" %>
<%
	List<String> list1 = (List<String>)session.getAttribute("list1");
	List<String> list2 = (List<String>)session.getAttribute("list2");
	List<String> list3 = (List<String>)session.getAttribute("list3");
	List<String> list4 = (List<String>)session.getAttribute("list4");
	List<String> list5 = (List<String>)session.getAttribute("list5");
	List<String> list6 = (List<String>)session.getAttribute("list6");
	
%>
<div class="whole-wrapper">
<div class="news-list">
	<ul>
	<%
		for(int i=0;i<list1.size();i++){
	%>
		<li style="list-style-type:none;border-bottom:1px solid #DDDDDD;width:65%">
			<div class="list-hd">
                 <h4>
                      <a href="showNewsDetailAction.action?hrefString=<%=list1.get(i) %>" style="color:black;text-decoration:none;font-size:25px"><%=list2.get(i) %></a>
                 </h4>
            </div>
            <br/>
            <div class="otherInfo">
            	<span class="oter-left" style="color:gray">
            		<%= list3.get(i) %>&nbsp;来自&nbsp;
            		<span class="comeFrom" style="color:gray">
            			<%=list4.get(i) %>
            		</span>
            	</span>
            </div>
		</li>
	<%
		}
	%>
	</ul>
</div> 
<div class="hours24-top">
	<div class="hd">
        <h2 style="color:navy">24小时新闻排行榜</h2>
	</div>
	<div class="bd">
		<ul class="list">
			<%
				for(int i=0;i<list5.size();i++){
			%>
			<li>
				<span class="number"><%=i+1 %>.</span>
				<a href="showPopularNewsDetailAction.action?popularHrefString=<%=list5.get(i) %>" style="text-decoration:none;font-size:12px;color:gray"><%=list6.get(i) %></a>
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