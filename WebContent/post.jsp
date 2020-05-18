<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/topic.css" />
<link rel="stylesheet" href="css/bootstrap.css" />
<link rel="shtylesheet" href="css/font-awesome.css" />
<script type="text/javascript" src="js/jquery-3.2.1.min.js" charset="UTF-8"></script> 
<script type="text/javascript" src="js/bootstrap.js"></script>
<script>
   function publishReply()
   {
	   var context=$("#publicArea").html();
	   var title=$("#postTitle").html();
	   var commentUsername=$("#commentUsername").html();
	   alert(commentUsername);
	   $.ajax
	   ({
		   url:"${pageContext.request.contextPath}/mypage",
		   data:
			   {
			   "commentContext":context,
			   "commentTitle":title,
			   "opration":"addComment",
			   "commentUsername":commentUsername
			   },
		   success:(function()
		   {
			   $("#showSuccess").html("发表成功!");
			   $("#publicArea").html("");
		   })
	   });
   }
   function cancelOrNot()
   {
    if(confirm("确定取消发布？")){
  	 $("#publicArea").html("");
  	}
   }
    $(function(){
  		$("#main").click(function()
  		   {
  	 		   $("#showSuccess").html("");
  			});
  });
    function clickSpread(j)
    {
    	var hideBlock=$("#hideBlock");
    	alert("聚焦");
    	$("#hideBlock"+j).toggle();
    }
    function sendCommentReply(j)
    {
    	var content=$("#editContent").html();
    	var commentUser=$("#commentContent").html();
    	var postTitle=$("#postTitle").html();
    	alert(content);
    	$.ajax({
    	   url:"${pageContext.request.contextPath}/mypage",
    	   data:
    		   {
    		   "opration":"addReply",
    		   "replyContent":content,
    		   "commetUser":commentUser,
    		   "commentFloor":j,
    		   "postName":postTitle
    		   },
    	   success:function(data)
    	   {
    		   alert("评论成功");
    	   }
    		   
    	})
    }
    function showCommentReply(j)
    {
    	var postTitle=$("#postTitle").html();
    	$.ajax({
    		url:"${pageContext.request.contextPath}/post",
    		data:
    			{
    			"commentTitle":postTitle,
    			"commentFloor":j,
    			"opration":'showCommentReply'
    			},
    	    success:function(data)
    	    {
    	    	alert("#hideReply"+j);
    	    	$("#hideReply"+j).toggle();
    	    	alert("哈哈哈");
    	    }
    	})
    }
</script>
</head>
<body>
<jsp:include page="/header.jsp"></jsp:include>
    <div id="main" class="topic_mudule">
       <div class="topic_display">
         <div class="topics_bar">
           <a href="index.jsp">主页</a>>>
           <a href="${pageContext.request.contextPath}/topic?module=${module}">${module}</a>
         </div>
         <div class="post_area">
            <div id="title" class="title_bar">
              <div id="postTitle" class="postTitle">${post.myTitle}</div>
              <div class="postContent">发帖人:<a href=${pageContext.request.contextPath}/searchMypage?role=${post.getUsername()}">${post.getUsername()}</a> 发帖时间:${post.getSubmitTime() }</div>
            </div>
            <div class="post_context">${post.myPost}</div>
         </div>
            <!-- 发表的回复和评论 -->
         <c:forEach var="t" items="${comment}" varStatus="j">
           <div class="row">
             <div class="border_line"> 
                <div id="comment_order" class="comment_order">${j.count}</div>
                <c:if test="${role.username==user.username}"> 
                  <div class="btn-group">
					 <button class="btn btn-default dropdown-toggle" data-toggle="dropdown">操作
				        <span class="caret"></span>
			   	     </button>
				     <ul class="dropdown-menu" role="menu">
				        <li>
				            <a href="${pageContext.request.contextPath}/post?opration='delete'&&commentTitle=${t.commentTitle}&&commentFloor=${j.count}">删除</a>
				        </li>
				        <li>
				            <a href="#">置顶</a>
				        </li>
				     </ul>
				  </div>
			    </c:if>
             </div>
              </br>
             <div class="col-md-3">
              <!-- mypage页面时别人的页面还是自己的页面 -->
              <img class="commentUserAvatar" src="${commentUser.get(j.count-1).avatarPath}" />
              <div><a href="${pageContext.request.contextPath}/searchMypage?role=${t.getCommentUsername()}">${t.getCommentUsername()}</a></div>
            </div>
             <div class="col-md-9">${t.getCommentContext()}</div>
           </div>
           <div class="commentRow">
             <div style="cursor:hand">
                                               评论(<a onclick="showCommentReply(${j.count})">${t.commentReplyCount}</a>)
                <a onclick="clickSpread(${j.count})">点击回复</a> 
             </div>
             <div id="hideReply${j.count}" class="hideReply" style="display:none">
               <c:forEach var="cr" items="${commentReply}">
                  <div class="replyRow" style="text-align:left">${cr.replyUser}回复${cr.commentUser}:${cr.replyContent}</div>
               </c:forEach>
             </div>
             <div id="hideBlock${j.count}" class="hideBlock" style="display:none"> 
                <div id="commentUser" style="display:none">  ${t.getCommentUsername()}</div>                             
                <div contenteditable="true" style="text-align:left" id="editContent" placeholder="写评论"></div>
                <button style="float:right" class="btn-default" onclick="sendCommentReply(${j.count})">发表评论 </button>
             </div>
             ${t.getCommentTime()}
          </div> </br>
         </c:forEach>
      </div>
       <div id="publish" class="publish">
         <i class="fa fa-edit" style="margin:5px;">发表回复</i>
         <div class="add_head">
            <div id="commentUsername" style="display:none">${user.username}</div>
            <div class="addImg">图片</div>
            <div class="addEmo">表情</div>
            <div class="addTopic">话题</div>
         </div>
         <c:if test="${empty user}">
           <div  class="publicArea"> 
              <div class="visitor">请<a href="login.jsp">登录</a></div>
           </div>
         </c:if>
         <c:if test="${!empty user}">
            <div  contenteditable="true" id="publicArea" class="publicArea"> </div>
         </c:if>
         <div class="publish_bar">
           <span id="showSuccess" class="showSuccess" style="color:red"></span>
           <button class="publish_btn" onclick="publishReply()">发表</button>
           <button class="cancel" onclick="cancelOrNot()">取消</button>
         </div>
      </div>
    </div>
    
<jsp:include page="/footer.jsp"></jsp:include>
</body>
</html>