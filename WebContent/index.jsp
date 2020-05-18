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
<script type="text/javascript" src="js/bootstrap.js"></script>
<script type="text/javascript" src="js/jquery-3.2.1.min.js" charset="UTF-8"></script>

<script>
   $(function(){
	   $.ajax({
   		url:"${pageContext.request.contextPath}/login",
   		data:
   			{
   			"login_status":2
   			}
   	})
</script>
</head>
<body onload="init()">
     <jsp:include page="/header.jsp"></jsp:include>
     <div class="index_main">
	    <div class="middle_block">
	        <div class="middle_left">
            <div id="myCarousel" class="carousel slide">
		     <!-- 轮播（Carousel）指标 -->
		     <ol class="carousel-indicators">
		        <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
		        <li data-target="#myCarousel" data-slide-to="1"></li>
		        <li data-target="#myCarousel" data-slide-to="2"></li>
		     </ol>   
		     <!-- 轮播（Carousel）项目 -->
		     <div class="carousel-inner">
		        <div class="item active">
		            <img src="${recentImage.get(0).getImagePath()}" alt="First slide" width="1250px" height="350px" class="changeImg">
		             <a class="carousel-caption" href="${pageContext.request.contextPath}/post?title=${recentImage.get(0).getTitlename()}">${recentImage.get(0).getTitlename()}</a>
		        </div>
		        <div class="item" >
		            <img src="${recentImage.get(1).getImagePath()}" alt="Second slide" id="changeImg" class="changeImg">
		             <a class="carousel-caption" href="${pageContext.request.contextPath}/post?title=${recentImage.get(1).getTitlename()}">${recentImage.get(1).getTitlename()}</a>
		        </div>
		        <div class="item">
		            <img src="${recentImage.get(2).getImagePath()}" alt="Third slide" class="changeImg">
		             <a class="carousel-caption" href="${pageContext.request.contextPath}/post?title=${recentImage.get(2).getTitlename()}">${recentImage.get(2).getTitlename()}</a>
		        </div>
		     </div>
		     <!-- 轮播（Carousel）导航 -->
		     <!-- 轮播（Carousel）导航 -->
		      <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
		        <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
		        <span class="sr-only">Previous</span>
		      </a>
		      <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
		        <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
		        <span class="sr-only">Next</span>
		      </a>
     </div>
   </div>


	       <div class="middle_right">
	         <c:forEach var="t" items="${todayTopics}">
	           <a class="today_block" href=${pageContext.request.contextPath}/post?title=${t.myTitle}&&module=${t.module}>${t.getMyTitle()}</a></br>
	         </c:forEach>
	       </div>
	    </div>
	    <div id="middle_bar" class="middle_bar">话题模块</div>
	    <div class="row">
            <div class="col-md-10">
	          <div class="top_block">
	            <c:forEach var="topic" items="${topics}">
	              <div class="index_card" style="color:white">
	               <c:forEach items="${topic}" var="t">
	                 <div class="inner_topic"> <a style="color:black">${t.module}</a><a href=${pageContext.request.contextPath}/topic?module=${t.module} class="clickMore">更多...</a></div>
	                 <div class="show_topic"><a style="color:black" href=${pageContext.request.contextPath}/post?title=${t.myTitle}&&module=${t.module} >${t.myTitle}</a></div>
	               </c:forEach>
	              </div>
	            </c:forEach>
		       </div>
		    </div>
		    <div class="col-md-2">
			   <div class="top_right">
			     <span style="font-size:18px;">推荐人物</span>
			     <c:forEach var="r" items="${recommendUser}">
						 <div class="right_card">
						  <div class="row">
						    <div class="col-sm-5">
				             <img id="showAvatar" src="${r.getAvatarPath()}" width="65px" height=65px" /> 
				            </div>
				            <div class="col-sm-5">
				             <div id="showName" class="showName">
				              <a href="${pageContext.request.contextPath}/searchMypage?role=${r.getUsername()}">
				                ${r.getUsername()}
				              </a>
				             </div></br>
<!-- 				             <button class="addFellow2" onclick="addFellow()"> -->
<!-- 				             </button> -->
				            </div>
			              </div>
			             </div>
			     </c:forEach>
		      </div>
	       </div>
	     </div>
	    <div class="middle_bar">日常生活</div>
	    <div class="bottom_block">
	       <div class="row">
	         <div class="col-md-12">
	           <label style="font-size:18px;">站内通知</label>
	           <div class="stationInform">
	             <c:if test="${empty messages}">
	                                          目前没有通知
	             </c:if>
	             <c:if test="${!empty messages}">
	               <c:forEach var="f" items="${messages}">
	                  <div class="showInform">[${f.messageType}] ${f.message}</div>
	               </c:forEach>    
	             </c:if>
	           </div></br>
	         </div>
	       </div>
	    </div>
     </div>
     
     <jsp:include page="/footer.jsp"></jsp:include> 
</body>
</html>