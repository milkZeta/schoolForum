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
<script>
    function saveMessage()
    {
    	var messageType=$("input[name='message']:checked").val();
    	var message=$("#message_box").html();
    	 $.ajax
  	     ({
  		  url:"${pageContext.request.contextPath}/messageManager",
  		  data:
  			  {
  			  "messageType":messageType,
  			  "message":message,
  			  "opration":"saveMessage"
  			  },
  			  success:function(data)
  			  {
  				$("#message_box").html("");
  				 $.ajax({
 			  		url:"${pageContext.request.contextPath}/messageManager",
 			  		data:{
 			  			"opration":"getMessage"
 			  		},
 			  		success:function(data){
			   			{
			   				 window.location.href="messageManager.jsp";
			   			}
			  		  }
  			    })
  			  },
  			 error:function(data)
 			  {
 				  alert("发布失败！");
 			  }
  	  });
    }
    function deleteMessage(j)
    {
    	var content=document.getElementById("showMessage"+j);
    	 $.ajax
  	     ({
  		  url:"${pageContext.request.contextPath}/messageManager",
  		  data:
  			  {
  			  "messageFloor":j,
  			  "opration":"deleteMessage"
  			  },
  			 success:function(data)
 			  {
 				$("#message_box").html("");
 				 $.ajax({
			  		url:"${pageContext.request.contextPath}/messageManager",
			  		data:{
			  			"opration":"getMessage"
			  		},
			  		success:function(data){
			   			{
			   				 window.location.href="messageManager.jsp";
			   			}
			  		  }
 			    })
 			  }
  	  })
    }
</script>
</head>
<body>
             &nbsp;消息管理
    <div class="send_box">
         <div  class="messageType">
             <label><input type="radio" id="inform" name="message" value="通知">通知</label>
             <label><input type="radio" id="findAndLose"name="message" value="失物招领">失物招领</label>
         </div>
	    <div contentEditable="true" id="message_box" class="message_box"></div>
	    <button class="btn-default" onclick="saveMessage()">发布消息</button></br>
    </div>
    <c:forEach var="m" items="${messages}" varStatus="j">
      <div class="showMessage" id="showMessage${j.count}">
        <div>
        <span class="serial_number">&nbsp;${j.count }</span>&nbsp;&nbsp;${m.messageType}
        <button onclick="deleteMessage(${j.count})" style="float:right">删除</button>
        </div>
        <div >${m.message}</div>
        <div style="float:right">${m.publishTime}</div></br>
      </div>
    </c:forEach>
</body>
</html>