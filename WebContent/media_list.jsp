<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="entity.*"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>视频列表</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/codehome.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/responsiveslides.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/mediaList.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bottom.css">

<%-- <script type="text/javascript" src="skin/js/jquery1.3.2.js"></script>
<script type="text/javascript">
$(function() {
    var sWidth = $("#focus").width(); //获取焦点图的宽度（显示面积）
    var len = $("#focus ul li").length; //获取焦点图个数
    var index = 0;
    var picTimer;
    
    //以下代码添加数字按钮和按钮后的半透明条，还有上一页、下一页两个按钮
    var btn = "<div class='btnBg'></div><div class='btn'>";
    for(var i=0; i < len; i++) {
        btn += "<span></span>";
    }
    btn += "</div><div class='preNext pre'></div><div class='preNext next'></div>";
    $("#focus").append(btn);
    $("#focus .btnBg").css("opacity",0.5);

    //为小按钮添加鼠标滑入事件，以显示相应的内容
    $("#focus .btn span").css("opacity",0.4).mouseenter(function() {
        index = $("#focus .btn span").index(this);
        showPics(index);
    }).eq(0).trigger("mouseenter");

    //上一页、下一页按钮透明度处理
    $("#focus .preNext").css("opacity",0.2).hover(function() {
        $(this).stop(true,false).animate({"opacity":"0.5"},300);
    },function() {
        $(this).stop(true,false).animate({"opacity":"0.2"},300);
    });

    //上一页按钮
    $("#focus .pre").click(function() {
        index -= 1;
        if(index == -1) {index = len - 1;}
        showPics(index);
    });

    //下一页按钮
    $("#focus .next").click(function() {
        index += 1;
        if(index == len) {index = 0;}
        showPics(index);
    });

    //本例为左右滚动，即所有li元素都是在同一排向左浮动，所以这里需要计算出外围ul元素的宽度
    $("#focus ul").css("width",sWidth * (len));
    
    //鼠标滑上焦点图时停止自动播放，滑出时开始自动播放
    $("#focus").hover(function() {
        clearInterval(picTimer);
    },function() {
        picTimer = setInterval(function() {
            showPics(index);
            index++;
            if(index == len) {index = 0;}
        },4000); //此4000代表自动播放的间隔，单位：毫秒
    }).trigger("mouseleave");
    
    //显示图片函数，根据接收的index值显示相应的内容
    function showPics(index) { //普通切换
        var nowLeft = -index*sWidth; //根据index值计算ul元素的left值
        $("#focus ul").stop(true,false).animate({"left":nowLeft},300); //通过animate()调整ul元素滚动到计算出的position
        //$("#focus .btn span").removeClass("on").eq(index).addClass("on"); //为当前的按钮切换到选中的效果
        $("#focus .btn span").stop(true,false).animate({"opacity":"0.4"},300).eq(index).stop(true,false).animate({"opacity":"1"},300); //为当前的按钮切换到选中的效果
    }
});

</script> --%>
</head>

<body>
<%@include file="top.jsp" %>
<div class="wrapper">
	<div style="width:200px">
		<div style="float:left">
    	<h1>最新视频</h1>
    	</div>
    	<div style="float:left;padding-top:27px;padding-left:5px">
    	<img src="http://localhost:8080/BallBBS/image/video.jpg" width="35px" height="35px">
    	</div>
    </div>
    	<br/>
        <table class="video_table">
        	<tbody>
        	<s:iterator value="mediaList" status="st">    	
        		<s:if test="(#st.getIndex() + 1 + (pageNumber - 1) * pageSize) %5 ==1 ">		
                    <tr>
         		</s:if>
                        <td>
                            <div class="p_video">
                                <div class="img_outer">
                                    <a href="play.action?vedioId=<s:property value="vedioId"/>" target="_blank" title="title"><img src="<s:property value="picture"/>" alt="<s:property value="title"/>" width="120px" height="70px"></a></div>
                                <dl><dt class="video_title"><a href="play.action?vedioId=<s:property value="vedioId"/>" target="_blank" title="<s:property value="title"/>" style="text-decoration:none;color:navy"><s:property value="title" /></a></dt>
                                   <div style="color:gray;display:inline">上传用户: </div><a href="userInformationAction.action?userId=<s:property value="user.userId"/>" target="_blank" style="text-decoration:none;color:black"><s:property value="user.userName"/></a>
                                   <br/>
                                   <div style="color:gray;display:inline"><s:date name="uptime" format="yyyy-MM-dd HH:MM"/>
                                   		<s:if test="%{#session.loginManager != null}">
                                   			<a href="http://localhost:8080/BallBBS/deleteMediaAction.action?vedioId=<s:property value="vedioId"/>&pageNumber=<%=request.getParameter("pageNumber")%>">
                                   				<img src="http://localhost:8080/BallBBS/image/close.png" alt="删除" width="20px" height="20px">
                                   			</a>
                                   		</s:if>
                                   </div></dl>
                            </div>
                        </td>
                 <s:if test="(#st.getIndex() + 1 + (pageNumber - 1) * pageSize) %5 ==0 ">
                    </tr>
                 </s:if>
				</s:iterator>                    
                </tbody>
            </table>
            
            <center>
			<s:property value="pageNumber" />
			/
			<s:property value="totalPage" />
			<a class="maroon"
				href="showMediaList.action?pageNumber=1" style="text-decoration:none;color:black">首页</a>
			<a class="maroon"
				href="showMediaList.action?pageNumber=<s:property value="pageNumber-1"/>" style="text-decoration:none;color:black">上一页</a>
			<a class="maroon"
				href="showMediaList.action?pageNumber=<s:property value="pageNumber+1"/>" style="text-decoration:none;color:black">下一页</a>
			<a class="maroon"
				href="showMediaList.action?pageNumber=<s:property value="totalPage"/>" style="text-decoration:none;color:black">末页</a>
			</center>
                
</div>
<%@include file="bottom.html" %>
</body>
</html>