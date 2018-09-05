<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>BBS</title>
		<%-- <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css"> --%>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/codehome.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/responsiveslides.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bottom.css">
		<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.3.min.js"></script>
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
</script>	 --%>
  </head>
  
<body>
		<%@ include file="top.jsp" %>

		<div id="viwepager">
			<div id="wrapper">
				<div class="callbacks_container">
					<ul class="rslides" id="slider4">
						<a href="###" title="HTML5">
							<li><img src="http://localhost:8080/BallBBS/image/ballboard.jpg" alt=""></li>
						</a>
						 <!-- <a href="###" title="Smart Phone">
							<li><img src="image/2.jpg" alt=""></li>
						</a>
						<a href="###" title="JAVA">
							<li><img src="image/3.jpg" alt=""></li>
						</a> -->
					</ul>
				</div>
			</div>
		</div>		
		
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
							</tr>
						<tbody>
						<s:iterator value="allPost" status="st">
							<tr>
								<td colspan=6 class="common">
									<a	class="maroon" href="postMatterAction.action?postId=<s:property value="postId"/>"
									style="margin-left:10px;color:black"><s:property value="topic" /></a>
								</td>
								<td class="other">
									<label><s:property value="postReplyAmount"/></label>
								</td>
								<td class="other">
									<label><a class="maroon" href="userInformationAction.action?userId=<s:property value="user.userId"/>">
									<s:property value="user.userName"/></a></label>
								</td>
								<td class="other">
									<label><s:date name="postTime" format="yyyy-MM-dd HH:mm" /></label>
								</td>
							</tr>
						</s:iterator>
						</tbody>
					</table>
				</form>
				<%-- <s:iterator value="allPost" status="st">
					<ul class="singlePost">
						<li class="sequence">
							<s:property value="postReplyAmount"/>
						</li>
						<li class="topic">
							<div align="center"><a class="maroon" href="postMatterAction.action?postId=<s:property value="postId"/>"><s:property value="topic"/></a></div>
						</li>
						<li class="author">
							<a class="green" href="userInformationAction.action?userId=<s:property value="user.userId"/>"><s:property value="user.userName"/></a>
						</li>
						<li class="date">
							<s:date name="postTime" format="yyyy-MM-dd HH:mm:ss"/>
						</li>
						<s:if test="%{#session.loginManager != null}">
							<li class="delete">						
								<a class="blue" href="deletePostAction.action?postId=<s:property value="postId"/>&pageNumber=<s:property value="pageNumber"/>">ɾ��</a>
							</li>
						</s:if>	
					</ul>				
				</s:iterator> --%>
			</div>
		</div>
			
		<center>
			<s:property value="pageNumber" />
			/
			<s:property value="totalPage" />	
				<a class="maroon" href="allPostAction.action?pageNumber=1">��ҳ</a>
				<a class="maroon" href="allPostAction.action?pageNumber=<s:property value="pageNumber-1"/>">��һҳ</a>
				<a class="maroon" href="allPostAction.action?pageNumber=<s:property value="pageNumber+1"/>">��һҳ</a>
				<a class="maroon" href="allPostAction.action?pageNumber=<s:property value="totalPage"/>">ĩҳ</a>
		</center>
		<%@ include file="bottom.html" %>		
</body>
</html>
