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
  <script type="text/javascript">
     $(document).ready(function(){
    	  $("input[name=repassword]").blur(function(){
    		            var ar=[];
    		            $("input[type=password]").each(function(){
    		                ar.push($(this).val());
    		            });
    		            var a=ar[0];
    		            var b=ar[1];
    		          if(a!=b)$("#showCheck").text("密码输入不一致！");
    		        });
    		    
            $("input[type=password]").focus(function(){
            	$("#showCheck").text("");
            });
            
            $("input[name=username]").blur(function(){
           	 var username=$("input[name=username]").val();
           	$.ajax({
          		url:"${pageContext.request.contextPath}/register",
           		data:
           			{
           			"username":username,
           			"opration":"check"
           			},
           		success:function(data)
           		{
           			if(data=="exist")
           				{
           				$("#username").text("");
           				$("#checkUser").text("该用户已存在,请重新输入用户名");
           				}
           		}
           	 });
            })
            $("input[name=username]").click(function(){
    	 $("#checkUser").text("");
     })
            	})
     function judge()
        {
    	 if($("input[type=text]").text()==""&& $("input[type=password]").text()==""&& $("input[type=date]").text()==""&& $("input[name='sex']:checked").val()==null)
         	{
         		alert("请输入完整信息！");
         		}
    	 else $("#register-btn").toggle();
     }
     
   </script> 
</head>
<body>
     <jsp:include page="/header.jsp"></jsp:include>
      
     <div class="register">
	     <div class="register-left"></div>
	     <form action="${pageContext.request.contextPath}/register">
		     <div class="register1">
		       <span>用 户 注 册</span>
		       <!-- 缺一个用户头像信息 -->
		       <div ><label>昵 称：</label><input type="text" name="username"/></div>
		       <span id="checkUser" style="color:red"></span>
		       <div><label>密  码：</label><input type="password" name="password" /></div>
		       <div >
		          <label>确认密码：</label><br>
		          <label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
		          <input type="password" name="repassword" onblur="checkPsw()" /><br>
		          <span id="showCheck" style="color:red"></span>
		       </div>
		       <div  >
		          <label>性 别：</label>
		          <label><input name="sex" id="male" type="radio" value="男"/> 男 &nbsp;&nbsp;</label>
		          <label><input name="sex" id="female" type="radio" value="女"/> 女</label>
		       </div>
<!-- 		       <div> -->
<!-- 		           <label>学 校：</label> -->
<!-- 		           <input type="text"  name="school"> -->
<!-- 		       </div> -->
		       <div style="padding:5px;">
		           <label>出生日期：</label>
		           <input type="date" name="birthday">
		       </div>
		       <div id="register-btn" style="display:none">
		         <input type="submit" value="&nbsp;注 &nbsp;&nbsp;册&nbsp;" class="register-btn">
		       </div>
		        <button type="button" onclick="judge()">保存</button>
		     </div>
	     </form>
     </div>
     <div class="footer1"></div>
</body>
</html>