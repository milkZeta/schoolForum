<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<body>
     <div class="bar-box"></div>
    <div class="index_bar"  >
       <div style="float:left" style="color:red">
	      <a href="${pageContext.request.contextPath}/login?login_status=2">首  页</a><label>&nbsp;&nbsp;&nbsp; </label>
<!-- 	       <a href="forum.jsp">论  坛</a> -->
	   </div>
	   <div  style="float:right">
         <c:if test="${empty user}">
           <a href="login.jsp"><i class="glyphicon glyphicon-user" ><span id="login">登  录 </span></i></a><label>&nbsp;&nbsp; </label>
           <a href="register.jsp"><span>注 册</span></a>
         </c:if>
         <c:if test="${!empty user}">
          <a href="${pageContext.request.contextPath}/searchMypage?role=${user.username}"><i class="glyphicon glyphicon-user" ><span>${user.username}的主页 </span></i></a><label>&nbsp;&nbsp; </label>
          <a href="${pageContext.request.contextPath}/login?login_status=0"><span id="quit">退 出</span></a>
         </c:if>
         <label>&nbsp;&nbsp; </label>
<!--          <a href="feedbackCenter.jsp"><span>反馈中心</span></a> -->
	   </div>
    </div>
</body>
</html>



