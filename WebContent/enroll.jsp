<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<title>�û�ע��</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/register.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bottom.css">
  	<sx:head parseContent="true" extraLocales="UTF-8"/>
  </head>
  
<body>
	<%@include file="top.jsp" %>

	<!-- ע�� -->
	<div id="register">

		<!-- ��½ -->
		<div id="regist1">
			<p>����ע��</p>
			<a href="login.jsp">�����ʺţ����ڵ�½</a>
		</div>

		<!-- ע�� -->
		<div id="regist2">
			<form action="enrollAction" name="register" method="post" enctype="multipart/form-data">
				<ul>
					<li>
						<p class="red">*</p>�û���:<input type="text" class="kuang1"
						onFocus="abc()" name="user.userName">
						<hr />
					</li>
					<li>
						<p class="red">*</p>����:<input type="password" class="kuang1"
						name="user.userPassword">
						<hr />
					</li>
					<li>
						<p class="red">*</p>ȷ������:<input type="password" class="kuang1"
						name="userRePassword">
						<hr />
					</li>
					<li>
						<p class="red">*</p>�Ա�:&nbsp;<s:select name="user.sex" list="#{'1':'��','2':'Ů'}"></s:select>
						<hr />
					</li>
					<li>
						<p class="red">*</p>����:&nbsp;<s:textfield name="user.userEmail"></s:textfield>
						<hr />
					</li>
					<li>
						<p class="red">*</p>ͷ��:&nbsp;<s:file name="userImage"  id ="file"/>
						<hr />
					</li>
					<s:fielderror />
					<li>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</li>
					<li><input type="submit" id="rgsbutton" value="ע��">
						&nbsp;&nbsp; <input type="checkbox" id="provision">
						<p style="display:inline-block;"><a href="">ͬ����վ��������</a></p>
					</li>
				</ul>
			</form>
		</div>
		<%@include file="bottom.html"%>
	</div>
</body>

<%-- <script>
  document.getElementById('file').onchange = function() {
    var imgFile = this.files[0];
    var fr = new FileReader();
    fr.onload = function() {
      document.getElementById('image1').getElementsByTagName('img')[0].src = fr.result;
    };
    fr.readAsDataURL(imgFile);
  };
</script> --%>

</html>
