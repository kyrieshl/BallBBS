<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
</head>

<body>
<%
	List<String> listTop5 = (List<String>)session.getAttribute("listTop5");
%>
	<div id="top">
		<img id="ballImage" src="http://localhost:8080/BallBBS/image/ball.jpg" alt="ball.jpg" style="width:88px;height:110px;position:absolute;left:2%;top:2%;">
		<s:if test="%{#session.loginUser != null}">
			<a href="personalInformationAction.action"><img
				src="<s:property value="%{#session.loginUser.userImage}"/>"
				id="txicon"></a>
		</s:if>
		<s:else>
			<img src="http://localhost:8080/BallBBS/image/tp.jpg" id="txicon">
		</s:else>
		<p>
			<s:if test="%{#session.loginUser != null}">
				<font style="color: green;font-weight: bold;"><a href="<s:property
						value="#session.loginUser.userName" />"><s:property value="#session.loginUser.userName" />
						</a>
						&nbsp;|
				</font>
			</s:if>
			<s:elseif test="%{#session.loginManager != null}">
				<font style="color: green;font-weight: bold;">
					管理员：<s:property value="#session.loginManager.managerName" />
				</font>
			</s:elseif>
			<s:else>
				<font style="color: red;font-weight: bold;">游客</font>
			</s:else>
			<s:if test="%{#session.loginUser != null}">
				<a class="blue"
					href="http://localhost:8080/BallBBS/userExitAction.action">退出</a>
			</s:if>
			<s:elseif test="%{#session.loginManager != null}">
				<a class="blue"
					href="http://localhost:8080/BallBBS/managerExitAction.action">退出</a>
			</s:elseif>
			<s:else>
				<a class="blue" href="http://localhost:8080/BallBBS/login.jsp">用户登录</a>
				<a class="blue" href="http://localhost:8080/BallBBS/managerLogin.jsp">管理员登录</a>
			</s:else>
			<s:if test="%{#session.loginManager == null}">
				<s:if test="%{#session.loginUser == null}">
					<a href="http://localhost:8080/BallBBS/enroll.jsp">注册</a>
				</s:if>
			</s:if>
		</p>
	</div>

	<!-- 导航栏 -->
	<s:if test="%{#session.loginManager == null}">
	<div id="navigation">
		<ul id="nav">
			<li><a href="http://localhost:8080/BallBBS/allPostAction.action">篮球论坛</a></li>
			<li><a href="http://localhost:8080/BallBBS/showNewsListAction.action">体育新闻</a></li>
			<li><a href="http://localhost:8080/BallBBS/media_upload.jsp">上传视频</a></li>
			<li><a href="http://localhost:8080/BallBBS/showMediaList.action?pageNumber=1">视频专区</a></li>
			<li><a href="http://localhost:8080/BallBBS/newPost.jsp">发表新帖</a></li>
			<li><a href="http://localhost:8080/BallBBS/personalPostAction.action">修改信息|管理帖子</a></li>
		</ul>
	</div>
	</s:if>
	
	<s:if test="%{#session.loginManager != null}">
	<div id="navigation">
		<ul id="nav">
			<li><a href="http://localhost:8080/BallBBS/allPostAction.action">论坛管理</a></li>
			<li><a href="http://localhost:8080/BallBBS/showMediaList.action?pageNumber=1">视频管理</a></li>
		</ul>
	</div>
	</s:if>

	<!-- 热搜 -->
	<div id="sousuo">
		<form action="searchPostAction" name="search">
			<table border="0" align="left" cellpadding="0" cellspacing="0"
				class="tab_search">
				<tr>
					<td><input type="text" name="hotSearch.hotSearchContent" title="Search"
						class="searchinput" id="searchinput"
						value="搜索帖子" onfocus="if (value =='搜索帖子'){value =''}" onblur="if (value ==''){value='搜索帖子'}"
						size="10" /></td>
					<td><input type="image" width="21" height="17"
						class="searchaction"
						alt="Submit" src="http://localhost:8080/BallBBS/image/magglass.jpg" border="0" hspace="2" /></td>
				</tr>
			</table>
		</form>
		<p class="hotSearch">热搜:</p>
		<ul>
			<li><a href="searchPostByTop5Action.action?searchTop5=<%=listTop5.get(0) %>"><%=listTop5.get(0) %></a></li>
			<li><a href="searchPostByTop5Action.action?searchTop5=<%=listTop5.get(1) %>"><%=listTop5.get(1) %></a></li>
			<li><a href="searchPostByTop5Action.action?searchTop5=<%=listTop5.get(2) %>"><%=listTop5.get(2) %></a></li>
			<li><a href="searchPostByTop5Action.action?searchTop5=<%=listTop5.get(3) %>"><%=listTop5.get(3) %></a></li>
			<li><a href="searchPostByTop5Action.action?searchTop5=<%=listTop5.get(4) %>"><%=listTop5.get(4) %></a></li>
		</ul>
	</div>
</body>
</html>
