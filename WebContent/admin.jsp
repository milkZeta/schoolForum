<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/manager.css" />
<link rel="stylesheet" href="css/bootstrap.css" />
<link rel="stylesheet" href="css/font-awesome.css" />
<script type="text/javascript" src="js/jquery-3.2.1.min.js" charset="UTF-8"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
</head>
<body>
     <div class="header">
         <div class="titleFont"> 校园论坛管理  </div>  
          <a href="login.jsp" style="size:18px">登录</a>
     </div>
	<table width="100%" height="700" border="1" cellpadding="1" cellspacing="0">
	  <tr>
	    <td width="15%" height="100%" valign="top">
	  <a  href="userManager.jsp" target="mainFrame">用户管理</a><br>
	  <a href="${pageContext.request.contextPath}/messageManager?opration='getMessage'" target="mainFrame">消息管理</a><br>
	 </td>
	    <td width="80%" valign="top"><iframe src="messageManager.jsp"   name="mainFrame" frameborder="0" marginheight="0" marginwidth="0" height="700" width="100%"></iframe></td>
	  </tr>
	</table>
</body>
</html>