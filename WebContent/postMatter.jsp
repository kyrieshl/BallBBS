<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<title><s:property value="post.topic"/></title>
	<link href="${pageContext.request.contextPath}/css/post.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bottom.css">

  </head>
  
<body>
	<%@include file="top.jsp"%>
	<div id="postbody">
		<form>
			<table cellpadding="0" cellspacing="0" id="posttop">
				<tbody>
					<tr>
						<td class="replaynum">回复数:<span><s:property
									value="post.postReplyAmount" /></span></td>
						<td class="title"><s:property value="post.topic" />
						<a href="#map" style="float:right"><img src="http://localhost:8080/BallBBS/image/magglass.jpg" alt="定位搜索" width="20px" height="20px">地点搜索</a>
					</tr>
				</tbody>
			</table>
			<table id="line" cellpadding="0" cellspacing="0">
				<tbody>
					<tr>
						<td class="line1"></td>
						<td class="line2"></td>
					</tr>
				</tbody>
			</table>
			<div class="post1">
				<table cellpadding="0" cellspacing="0" class="post2">
					<tbody>
						<tr>
							<td class="user">
								<div class="inform">
									<a
										href="userInformationAction.action?userId=<s:property value="post.user.userId"/>">
										<span><s:property value="post.user.userName" /></span></a>
									<hr>
									<img src="<s:property value="post.user.userImage" />"
										style="width:100px; height:100px;">
								</div>
							</td>
							<td class="comment">楼主<img src="http://localhost:8080/BallBBS/image/xiaoren.jpg">&nbsp;
								<span>发表于:</span>&nbsp; <span><s:date
										name="post.postTime" format="yyyy-MM-dd HH:mm" /></span>
								<hr> <span><s:property value="post.matter" /></span></td>
						</tr>
					</tbody>
				</table>
			</div>

			<table id="line" cellpadding="0" cellspacing="0">
				<tbody>
					<tr>
						<td class="line1"></td>
						<td class="line2"></td>
					</tr>
				</tbody>
			</table>
			<div class="post1">
				<table cellpadding="0" cellspacing="0" class="post2">
					<tbody>
						<s:iterator value="allPostReply" status="st">
							<tr>
								<td class="user">
									<div class="inform">
										<hr>
										<br/>
										<a href="userInformationAction.action?userId=<s:property value="user.userId"/>" style="text-decoration:none;color:navy">
										<span><s:property value="user.userName" /></span></a>
										<img src="<s:property
													value="user.userImage" />"
											style=" width:100px; height:100px;">
									</div>
								</td>
								<td class="comment">
									<hr> 
									<div class="floor" style="display:inline">
										&nbsp;&nbsp;<span><s:property value="floor"/>楼</span>
									
									&nbsp;<span style="font-color:#AAAAAA"><s:date name="replyTime" format="yyyy-MM-dd HH:mm" /></span>
									</div>
									<br/><br/>
									<span><s:property value="replyContent" /></span>
								</td>
							</tr>
						</s:iterator>
					</tbody>
				</table>
			</div>
		</form>
		<center>
			<s:property value="pageNumber" />
			/
			<s:property value="totalPage" />
			<a class="maroon"
				href="postMatterAction.action?noteId=<%=request.getParameter("postId")%>&pageNumber=1" style="text-decoration:none;color:navy">首页</a>
			<a class="maroon"
				href="postMatterAction.action?noteId=<%=request.getParameter("postId")%>&pageNumber=<s:property value="pageNumber-1"/>" style="text-decoration:none;color:navy">上一页</a>
			<a class="maroon"
				href="postMatterAction.action?noteId=<%=request.getParameter("postId")%>&pageNumber=<s:property value="pageNumber+1"/>" style="text-decoration:none;color:navy">下一页</a>
			<a class="maroon"
				href="postMatterAction.action?noteId=<%=request.getParameter("postId")%>&pageNumber=<s:property value="totalPage"/>" style="text-decoration:none;color:navy">末页</a>
		</center>

		<div id="huitie">
			<form action="addReplyAction" method="post">
				<s:fielderror/>
				<table cellpadding="0" cellspacing="0" id="huitietable">
					<tbody>
						<tr>
							<s:if test="%{#session.loginUser != null}">
								<td class="huitie1"><img src="<s:property value="user.userImage"/>" alt="<s:property value="user.userImage"/>"></td>
							</s:if>
							<s:else>
								<td class="huitie1"><img src="http://localhost:8080/BallBBS/image/tp.jpg"></td>
							</s:else>
							<s:if test="%{#session.loginUser != null}">
								<td class="huitie2"><textarea name="postReply.replyContent"
										 id="huitiekuang"></textarea> 
								<br> 
								<input type="submit" value="发表回复" id="huifufabiao"></td>
							</s:if>
							<s:else>
								<td class="huitie2"><textarea name="postReply.replyContent"
										placeholder="请先登录" readonly="readonly"></textarea></td>
							</s:else>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>
	<br/>
	<br/>
	<div id="map" style="border:1px solid navy">
		<%@include file="map2.html" %>
	</div>
</body>

<script type="text/javascript">
            function next(){
                window.location = "map2.html";
            }
</script>
</html>
