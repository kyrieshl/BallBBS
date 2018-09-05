<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<title>发表新帖</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/codehome.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/responsiveslides.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/css/style.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bottom.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/responsiveslides.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/codehome.js"></script>
<script>
	$(function() {

		// Slideshow 4
		$("#slider4").responsiveSlides({
			auto : false,
			pager : false,
			nav : true,
			speed : 500,
			namespace : "callbacks",
			before : function() {
				$('.events').append("<li>before event fired.</li>");
			},
			after : function() {
				$('.events').append("<li>after event fired.</li>");
			}
		});

	});
	$(document).ready(function() {
		$("#selecttype").on("change", function() {
			var o;
			var opt = $(this).find('option');
			opt.each(function(i) {
				if (opt[i].selected == true) {
					o = opt[i].innerHTML;
				}
			})
			$(this).find('label').html(o);
		})
	})
</script>
  </head>
  
<body>
	<%@ include file="top.jsp" %>
	<div id="fabiao">
		<div id="fabiaotop">快速发帖</div>
		<form action="addPostAction" method="get">
			<div id="fabiaobody">
				<s:fielderror />
				<div id="biaoti">
					主题:&nbsp;&nbsp;<input type="text" placeholder="${actionMessages[0]}" name="post.topic">
				</div>
				<div id="tieneirong">
					<textarea name="post.matter" placeholder="<s:property value="prompt"/>"></textarea>
				</div>
				<input type="submit" value="发表帖子" id="fabutton" >
			</div>
		</form>
	</div>

	<%@ include file="bottom.html" %>

</body>
</html>
