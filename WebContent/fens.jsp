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
</head>
<body>
        <div class="body_right">
           <div class="fens" style="text-align:left">
             <div class="fens_count">全部粉丝  ${fens.size()}</div>
             <div class="order">排序</div>
             <div class="row">
             <c:forEach var="f" items="${fens}">
	           <div class="col-md-5" style="margin:5px;">
	              <img src="${f.getFensAvatar()}" width="65px" height=65px" /> 
	              <a class="showName" href="${pageContext.request.contextPath}/searchMypage?role=${f.getFens()}">${f.getFens()}</a>
	           </div>
	         </c:forEach>
              </div>
           </div>
        </div>
     
</body>
</html>