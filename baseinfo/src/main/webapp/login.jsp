<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
	<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/include.jsp"></jsp:include>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	$(window).resize(function(){ 
	    $("body").css({ 
	        position: "absolute", 
	        left: ($(window).width() - 260)/2, 
	        top: ($(window).height() - $("body").outerHeight())/2 
	    });
	}); 
	
	$(function(){ 
	    $(window).resize();
	    var errorMsg = "<%=request.getParameter("errorMsg")%>";
	    if (errorMsg && errorMsg!="" && errorMsg != "null") {
	    	<%--$("input")[0].value = "<%=reqauest.getParameter("login")%>";--%>
	    	<%--$("input")[1].focus();--%>
	    	$("#msg").text(errorMsg);
	    }
//        $("ipput　")
	}); 
</script>
</head>
<body>
		<form action="login" method="post" style="width:260px; text-align: center;">
			<fieldset>
				<legend style="font-size:16pt;">登录</legend>
				用户: <input type="text" name="login" style="width:150px;"/><br/><br/>
				密码: <input type="password" name="password" style="width:150px;"/><br/><br/>
				<button type="submit">登陆</button>&nbsp&nbsp&nbsp<button type="reset">重置</button>
			</fieldset>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		</form>
		<div id="msg" style="color:red;"></div>

       sessionID: <%=request.getSession().getId()%>
</body>
</html>