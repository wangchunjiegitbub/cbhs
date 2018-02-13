<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en" class="no-js">

<head>

<meta charset="utf-8">
<title>沈阳车务段成本核算管理系统</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<!-- CSS -->

<link rel="stylesheet" href="<c:url value='/resources/login/css/supersized.css'/>">
<link rel="stylesheet" href="<c:url value='/resources/login/css/login.css'/>">
<link href="<c:url value='/resources/login/css/bootstrap.min.css'/>" rel="stylesheet">
<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
	<script src="../resources/login/js/html5.js"></script>
<![endif]-->
<script src="<c:url value='/resources/login/js/jquery-1.8.2.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/login/js/jquery.form.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/login/js/tooltips.js.jsp'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/login/js/login.js'/>"></script>

</head>

<body onload="loadTopWindow()">

<div id="loginId" class="page-container">
		<div class="main_box">
		<div class="login_box">
			<div class="login_logo">
				<img src="<c:url value='/resources/login/images/logo.png'/>" >
			</div>
		
			<div class="login_form">
				<form action="<c:url value='/login'/>" id="login_form" method="post">
					<div class="form-group">
						<label class="error" id="msg">${msg }</label> 
					</div>
					<div class="form-group">
						<label for="j_username" class="t">用户名：</label> 
						<input id="userName" value="" name="userName" type="text" class="form-control x319 in" 
						>
					</div>
					<div class="form-group">
						<label for="j_password" class="t">密　码：</label> 
						<input id="password" value="" name="password" type="password" 
						class="password form-control x319 in">
					</div>
					
					
					<div class="form-group space">
						<label class="t"></label>　　　
						<button type="button"  id="submit_btn" 
						class="btn btn-primary btn-lg">&nbsp;登&nbsp;录&nbsp; </button>
						<input type="reset" value="&nbsp;重&nbsp;置&nbsp;" class="btn btn-default btn-lg">
					</div>
				</form>
			</div>
		</div>
		<div class="bottom">Copyright &copy; 2016 - 2017 <a href="#">系统登陆</a></div>
	</div>
</div>

<!-- Javascript -->
<script type="text/javascript">
	$(function() {/*Map<String(Cookie名称),Cookie(Cookie本身)>*/
		
		if (window != top){
			 
			top.location.href = location.href; 
		}
		
		
		
		// 获取cookie中的用户名(window.decodeURI对中文进行url编码)
		var loginname = window.decodeURI("${cookie.loginname.value}");
		if("${requestScope.user.userName}") {
			loginname = "${requestScope.user.userName}";
		}
		$("#userName").val(loginname);
		 
		
		 
	});
	
	
	
	
</script>
<script src="<c:url value='/resources/login/js/supersized.3.2.7.min.js'/>"></script>
<script src="<c:url value='/resources/login/js/supersized-init.js'/>"></script>
<script src="<c:url value='/resources/login/js/scripts.js'/>"></script>

</body>
</html>