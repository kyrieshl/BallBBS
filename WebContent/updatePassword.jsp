<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<title>�����޸�</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bottom.css">
  </head>
  
<body>
	<center>
		<%@ include file="top.jsp" %>
		<div id="wholePage">
			<div id="currentPoint" style="display:inline;float:left">
				��ǰλ��:&nbsp;&nbsp;<a class="green" href="action/allPostAction.action">��ҳ</a>&nbsp;&nbsp;--&gt;&nbsp;&nbsp;�����޸�
			</div>
			<hr width="400"/>
			<s:form action="updatePasswordAction" method="post">
				<s:password label="������" name="userPassword" maxlength="12"></s:password>
				<s:password label="������" name="userNewPassword" maxlength="12"></s:password>
				<s:password label="�ظ�������" name="userRePassword" maxlength="12"></s:password>
				<s:submit value="�޸�"></s:submit>
			</s:form>
		</div>
		<%@ include file="bottom.html" %>
	</center>
</body>
</html>
