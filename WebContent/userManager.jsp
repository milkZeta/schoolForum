<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/bootstrap.css" />
<link rel="shtylesheet" href="css/font-awesome.css" />
<script type="text/javascript" src="js/jquery-3.2.1.min.js" charset="UTF-8"></script> 
<script type="text/javascript" src="js/bootstrap.js"></script>
<script>
  function modifyInfo(j){
	  alert("哈哈哈"+j);
	  var content=document.getElementById("content"+j);
	  content.contentEditable = true;
    }
  function deleteInfo(j){
	  var content=document.getElementById("content"+j);
	  alert(j);
	  $.ajax({
		  url:"${pageContext.request.contextPath}/messageManager",
		  data: 
			  {
			  "opration":"deleteUser",
			  "userFloor":j
			  },
		  success:function()
		  {
			  alert("删除成功，接下来显示");
			  $.ajax({
			  		url:"${pageContext.request.contextPath}/login",
			  		data:{
			  			"login_status":1,
			  			"username":"admin",
			  			"password":"123"
			  			},
			  		success:function(data){
			  			 if(data=="admin")
			   			{
			  				 alert(data);
			   				 window.location.href="userManager.jsp";
			   			}
			  		  }
			  		});
		  }
	  });
    }
  function saveInfo(j)
  {
	  var username=$("#username"+j).html();
	  var password=$('#password'+j).html();
	  var lastLogin=$('#lastLogin'+j).html();
	  var birthday=$('#birthday'+j).html();
// 	  var school=$('#school'+j).html();
	  var sex=$('#sex'+j).html();
	  $.ajax
	   ({
		  url:"${pageContext.request.contextPath}/messageManager",
		  data:
			  {
			  "opration":"saveUser",
			  "username":username,
			  "password":password,
			  "lastLogin":lastLogin,
			  "birthday":birthday,
			  "sex":sex
			  },
		  success:function()
		  {
			  alert("删除成功，接下来显示");
			  $.ajax({
			  		url:"${pageContext.request.contextPath}/login",
			  		data:{
			  			"login_status":1,
			  			"username":"admin",
			  			"password":"123"
			  			},
			  		success:function(data){
			  			 if(data=="admin")
			   			{
			  				 alert(data);
			   				 window.location.href="userManager.jsp";
			   			}
			  		  }
			  		});
		  }
	  })
  }
</script>
</head>
<body>
     <div>用户管理页面</div>
     <table border="1" cellpadding="10" style="text-align:center">
        <tr> 
           <td width="150px">用户名</td>
           <td width="150px">密 码</td>
           <td width="150px">上次登录时间</td>
           <td width="150px">生 日</td>
           <td > 性 别 </td>
           <td>进行操作</td>
        </tr>
        <c:forEach var="u" items="${users}" varStatus="j">
	        <tr  id="content${j.count}"> 
	          <td id="username${j.count}" class="${j.count}" >${u.username}</td>
	          <td id="password${j.count}" class="${j.count}" >${u.password}</td>
	          <td id="lastLogin${j.count}" class="${j.count}" >${u.lastLogin}</td>
	          <td id="birthday${j.count}" class="${j.count}" >${u.birthday}</td>
	          <td id="sex${j.count}" class="${j.count}" >${u.sex}</td>
			  <td>
			     <input type="button" onclick="deleteInfo(${j.count})" value="删除"> 
			     <input type="button" onclick="saveInfo(${j.count})" value="保存"> 
			     <input type="button" onclick="modifyInfo(${j.count})" value="修改">
			  </td>
    	   </tr>
        </c:forEach>
     </table>
</body>
</html>