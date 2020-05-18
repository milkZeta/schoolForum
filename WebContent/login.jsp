<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" 
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/login.css" />
<link rel="stylesheet" href="css/bootstrap.css" />
<link rel="stylesheet" href="css/font-awesome.css" />
<script type="text/javascript" src="js/jquery-3.2.1.min.js" charset="UTF-8"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script>
  function send()
  {
  	var username=$("#username").val();
  	var password=$("#password").val();
  	 var autologin="";
  	if($("#autologin").is(':checked')){
  		autologin ="autologin"; 
  		}
  	if(username!=null&&password!=null){
  	$.ajax({
  		url:"${pageContext.request.contextPath}/login",
  		data:{
  			"login_status":1,
  			"username":username,
  			"password":password,
  			"autologin":autologin
  			},
  		success:function(data){
  			if(data=="success")
  			{
  				 window.location.href="index.jsp";
  			}
  			else if(data=="admin")
  			{
  				 window.location.href="admin.jsp";
  			}
  			else $("#showLoginStatus").html("输入用户名或密码错误!");
  		}
  	 });
  	}
  }
$(function(){
$(".login").click(function()
   {
		   $("#showLoginStatus").html("");
	});
});
</script> 
</head>
<body>
<jsp:include page="/header.jsp"></jsp:include>
    <div class="main" >
        <div class="login" >
         <ul class="land">
           <li>
		     <div><span id="showLogin" style="color:#fff">用户登录</span></div>
		     <div> <span style="color:red" id="showLoginStatus"></span></div>
		   </li>
		   <li>	
		      <div class="bor">
		       <i class="glyphicon glyphicon-user" ></i> <label style="color:#fff">用户名：</label>
		       <input id="username" name="username" type="text" width="182px" height="25px" placeholder="请输入用户名" >
		      </div>
		      <div>
		       <i class="glyphicon glyphicon-lock" ></i> <label style="color:#fff">密 &nbsp;&nbsp; 码：</label>
		       <input id="password" name="password" type="password" width="180px" height="25px" placeholder="请输入密码">
		      </div>
           </li>	
           <li>	      
<!-- 		     <input type="submit" width="100" height="28" value="&nbsp;登 &nbsp;&nbsp;录&nbsp;" name="submit">&nbsp;&nbsp;&nbsp;&nbsp; -->
             <button id="login" class="btn-default" onclick="send()" >&nbsp;登&nbsp; &nbsp;录&nbsp;</button>
             <div style="color:black">
                                               其他登录：<a href="${pageContext.request.contextPath}/login?login_status=2" style="color:black">游客 </a>
             </div>
		   </li> 
		   <li>
		     <label><input type="checkbox" id="autologin" name="autologin" value="aotologin" style="color:#fff">自动登录</label>
		   </li>
<!-- 		   <li> -->
<!-- 		     <div><a style="color:#fff" style="float:right"> 忘记密码？</a></div> -->
<!-- 		   </li>  -->
         </ul>
         </div>
</div>
    <div class="footer"></div>
</body>
</html>