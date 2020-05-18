<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/page.css" />
<link rel="stylesheet" href="css/bootstrap.css" />
<link rel="stylesheet" href="css/font-awesome.css" />
<script type="text/javascript" src="js/jquery-3.2.1.min.js" charset="UTF-8"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script>

 function cancelOrNot()
 {
if(confirm("确定取消发布？")){
	 $("#myTitle").val("");
	 $("#myPost").val("");
	}
 }
$(function(){
		$("#edit_body").click(function()
		   {
	 		   $("#showSuccess").html("");
			});
		$("#userpic").click(function(){
			if($("#judge")){
			window.location.href="avatar.jsp";
			}
			else {
				document.getElementById("userPic").contentEditable=true;
				$("#userpic").append("<img src='../image/flat.jpg'> width=250px height=250px")};
		});
});
   function showFens()
   {
	   $.ajax({
		 url:"${pageContext.request.contextPath}/fens",
		 data:
			 {
			 "role":"${role.username}",
			 "contexttype":0
			 },
		 success:(function(data)
				 {
				window.location.href="mypage.jsp";
			//如果成功如何给粉丝区域加上粉丝数据
				 })
	   });
   }
   function showFellow()
   {
	   $.ajax({
		 url:"${pageContext.request.contextPath}/fens",
		 data:
		 {
		 "role":"${role.username}",
		 "contexttype":1
		 },
	   success:(function(data)
			 {
			window.location.href="mypage.jsp";
		//如果成功如何给粉丝区域加上粉丝数据
			 })
	    });
   }
   function showPost()
   {
	   $.ajax({
			 url:"${pageContext.request.contextPath}/searchMypage",
			 success:(function()
					 {
					window.location.href="mypage.jsp";
					 })
	   });
   }
   function addFellow()
   {
	   var fellowobj=$("#fellowobj").html();
	   var avatarpath=$("#avatarpath").html();
	   alert(fellowobj);
	   alert(avatarpath);
	   $.ajax({
		   url:"${pageContext.request.contextPath}/fens",
		   data:
			   {
			   "contexttype":3,
			   "fellowObj":fellowobj,
			   "avatarPath":avatarpath
			   },
		  success:function(data)
		  {
			  if(data==1)
				  {
				  alert("关注成功");
				  }
		  }
	   })
   }
</script>
</head>
<body>
     <div class="mypage" >
        <div class="main">
         <div class="myhead"></div>
         <div class="link_bar">
           <a href="${pageContext.request.contextPath}/topic">主页</a>>>
         </div>
         <div class="mybody">
           <div class="body_left">
              <c:if test="${empty role.getAvatarPath()&&role.username==user.username}">
                <div id="judge" style="display:none">${role.username==user.username}</div>
                <div id="userpic" class="userpic">单击上传头像</div>
              </c:if>
              <c:if test="${!empty role.getAvatarPath()}">
                <div id="userpic" class="userpic">
                  <img width="250px" height="250px" src=${role.getAvatarPath()} />
                </div>
              </c:if>
              <div class="buttons">
                <div class="innerBorder" id="Fellow" onclick="showFellow()">关 注 </br>  ${fellow.size()}</div>
                <div class="innerBorder" id="Fence" onclick="showFens()">粉 丝 </br> ${fens.size()}</div>
                <div class="innerBorder" id="Post" onclick="showPost()">帖 子</br>${pages.totalEssay}</div>
                 <c:if test="${role.username!=user.username}">  
                   <c:if test="${relation!=1}">
	                 <button class="innerBorder1" id="addFellow" onclick="addFellow()">+关注</button>
                   </c:if>
                   <c:if test="${relation==1}">
                       <button class="innerBorder1" id="addFellow" onclick="addFellow()">已关注</button>
                   </c:if>
                   <div id="fellowobj" style="display:none">${role.username}</div>
	               <div id="avatarpath" style="display:none">${role.avatarPath}</div>
                 </c:if>
              </div>
              <div class="mymessage"></div>
           </div>
	        <div id="right_context" >
	         <c:if test="${contexttype==0}">
	         <jsp:include page="/fens.jsp"></jsp:include>
	         </c:if>
	         <c:if test="${contexttype==1}">
	         <jsp:include page="/fellow.jsp"></jsp:include>
	         </c:if>
	         <c:if test="${empty contexttype}">
	         <jsp:include page="/mypost.jsp"></jsp:include>
	         </c:if>
	        </div>
	       </div>
       </div>
     </div>
     <jsp:include page="footer.jsp"></jsp:include>
</body>
</html>