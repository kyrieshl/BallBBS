<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>BBS</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/responsiveslides.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/codehome.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bottom.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/codehome.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/responsiveslides.min.js"></script>
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
	<%@include file="top.jsp"%>
	<%@include file="update.jsp"%>
	<div id="Postlist">
		<div id="Postlistbody">
			<form>
				<table cellpadding="0" cellspacing="0" id="tabbody">
					<thead id="Postlisttop">
						<tr>
							<td colspan=6>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;����</td>
							<td>�ظ���</td>
							<td>����</td>
							<td>����ʱ��</td>
							<td>����</td>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="personalPost" status="st">
							<tr>
								<td colspan=6 class="common"><img
									src="http://localhost:8080/BallBBS/image/upjiantou.png" class="lefticon">&nbsp;<a
									class="maroon"
									href="postMatterAction.action?postId=<s:property value="postId"/>"
									style="margin-left:10px;color:black"><s:property value="topic" /></a></td>
								<td class="other"><s:property value="postReplyAmount" /></td>
								<td class="other"><s:property value="user.userName" /></td>
								<td class="other"><s:date name="postTime"
										format="yyyy-MM-dd HH:mm" /></td>
								<td class="other"><a class="maroon"
									href="deleteMyPostAction.action?postId=<s:property value="postId"/>">ɾ��</a></td>
							</tr>
						</s:iterator>
					</tbody>
				</table>
			</form>
		</div>
	</div>
	<center>
		<s:property value="pageNumber" />
		/
		<s:property value="totalPage" />
		<a class="maroon" href="personalPostAction.action?pageNumber=1">��ҳ</a>
		<a class="maroon" href="personalPostAction.action?pageNumber=<s:property value="pageNumber-1"/>">��һҳ</a>
		<a class="maroon" href="personalPostAction.action?pageNumber=<s:property value="pageNumber+1"/>">��һҳ</a>
		<a class="maroon" href="personalPostAction.action?pageNumber=<s:property value="totalPage"/>">ĩҳ</a>
	</center>

	<%@ include file="bottom.html"%>
</body>
</html>
