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
</head>
<body>
<jsp:include page="/header.jsp"></jsp:include>
    <div class="main">
       <div class="topic_display">
         <div class="topics_bar">
            <a href="${pageContext.request.contextPath}/topic?opration='linkIndex'">主页</a>>>
           <a href=${pageContext.request.contextPath}/topic?module=${module}>${module}</a>
         </div>
       <c:forEach var="t" items="${topics}">
         <div class="topic_details"><a href=${pageContext.request.contextPath}/post?title=${t.myTitle}&&module=${module}>${t.myTitle}</a></div>
       </c:forEach>
       </div>
    </div>
<jsp:include page="/footer.jsp"></jsp:include> 
</body>
</html>