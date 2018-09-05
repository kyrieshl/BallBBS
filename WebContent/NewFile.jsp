<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="org.jsoup.nodes.Document"%>
<%@ page import="org.jsoup.select.Elements"%>
<%@ page import="org.jsoup.Jsoup"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

</head>
<body>
<%
	Document doc = Jsoup.connect("https://nba.hupu.com/").get();
	Elements link1 = doc.getElementsByClass("swiper-slide");
	String imgStr = link1.get(0).select("img").attr("src");
	String imgHref = link1.get(0).select("a").attr("href");
%>
	<a href=<%=imgHref %> class="swiper-slide" target="_blank" style="width: 340px; height: 280px;">
		<img src=<%=imgStr %> alt="" width="340px" height="280px">
	</a>
</body>
</html>