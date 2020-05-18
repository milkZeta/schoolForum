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
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script>
function showStatus()
{
 var myTitle=$("#myTitle").val();
 var myPost=$("#myPost").html();
 var path=null;
 else {path=$("#showPhoto")[0].src;}
 var module=$("input[name='module']:checked").val();  
 if(module!=null){
		if(myTitle!=""&&myPost!=""){
		   $.ajax({
			  url:"${pageContext.request.contextPath}/mypage?opration='addPage'",
			  data:{
				  "myTitle":myTitle,
				  "myPost":myPost,
				  "module":module,
				  "imagepath":path
			  },
			  success:function()
			  {
				
				  $("#showSuccess").html("发表成功!");
				  $("#myTitle").val("");
				  $("#myPost").val("");
			  }
		   });
		 }
	    else
		{
	      alert("发表内容为空!");
		}
 }
 else 
 {
 	alert("请选择话题分类!");
 }
}

function transmitVal(src)
{
	alert("哈哈"+src);
	$('#image').val(src); 
}
function upFile()
{   
	//获取文件对象
	var formData = new FormData($( "#form1" )[0]);
	alert($("#myTitle").val());
	formData.append("myTitle",$("#myTitle").val());
	$.ajax({
		url:"${pageContext.request.contextPath}/handleImage",
		type:"post",
		data:formData,
		contentType: false,
		processData: false,
		success:(function(t)
		{
			$("#myPost").append("<img id='showPhoto' class='showPhoto' src='"+t+"' />");
		})
	});
	
}
</script>
</head>
<body>
        <div class="body_right">
             <c:if test="${role.username==user.username}">  
              <div id="roleValue" style="display:none">${user.username}</div>
              <div class="edit_head">
                <i class="glyphicon glyphicon-pencil">发表帖子</i></br>
              </div>
              <div class="edit_body" id="edit_body" >
                 <div class="edit_bar">
                    <form id="form1" enctype="multipart/form-data" method="post" >
                      <div class="row">
	                   <input class="col-md-6" id="file1" type="file" name="uploadFile">
	                   <input class="col-md-3" type="button" onclick="upFile()" value="上传图像"/>
	                  </div> 
                     <div id="imgContext">${imgContext}</div>
                    </form>
                 </div>
                 <input type="text" id="myTitle" class="hide_head" placeholder="请输入标题">
                 <div contenteditable="true" id="myPost" class="hide_body">
                 </div>
                 <input type="submit" class="submit_btn" value="提 &nbsp;&nbsp;交" onclick="showStatus()">
                 <button class="cancel_btn" onclick="cancelOrNot()">取 消</button>
                 <span id="showSuccess" class="showSuccess" style="color:red"></span>
                 <div class="module_bar">
                    <label>话题分类:</label>
                    <div><input type="radio" id="mo1" name="module" value="闲谈趣事">闲谈趣事</div>
                    <div><input type="radio" id="mo2"name="module" value="校园照片">校园照片</div>
                    <div><input type="radio" id="mo3" name="module" value="寝室生活">寝室生活</div>
                    <div><input type="radio" id="mo4" name="module" value="社团活动">社团活动</div>
                    <div><input type="radio" id="mo5" name="module" value="学校活动">学校活动</div>
                    <div><input type="radio" id="mo6" name="module" value="图书馆">图书馆</div>
                 </div>
              </div>
            </c:if>
            <c:if test="${empty mypages}">
               <div>该用还未发表任何帖子</div>
            </c:if>
              <c:forEach var="page" items="${mypages}" >
                   <div class="edited_pages"> 
	                 <div class="edited_title" >
	                   <img class="myIcon" src=${role.avatarPath} />
	                   <div class="subTitle" >
	                     <div class="row">
	                      <div class="col-md-9">${role.username}</div>
	                      <div class="col-md-3">
	                        <div class="btn-group">
	                          <c:if test="${role.username==user.username}"> 
							   <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">操作
							        <span class="caret"></span>
							   </button>
							    <ul class="dropdown-menu" role="menu">
							        <li>
							            <a href="${pageContext.request.contextPath}/mypage?opration='delete'&&myTitle='${page.myTitle}'">删除</a>
							        </li>
							    </ul>
							  </c:if>
							</div>
						   </div>
	                      <div class="col-md-6">发表于 ${page.submitTime}</div>
	                     </div>
	                      
	                   </div>
	                 </div>
	                 <div class="edited_body">
	                   <div class="essay_title">${page.myTitle}</div>
	                   <div class="essay_body">${page.myPost}</div>
	                   <div></div>
	                 </div>
	               </div>
             </c:forEach>
             <div class="edit_bottom">
	             <ul class="pagination">
	               <!-- 如果当前页是第一页 -->
	               <c:if test="${pages.currentPage==1}">
		             <li class="disable"><a href="javascript:void(0);">上一页</a></li>
	               </c:if>
	               <c:if test="${pages.currentPage!=1}">
	                 <li><a href=${pageContext.request.contextPath}/searchMypage?currentPage=${pages.currentPage-1}>上一页</a></li>
	               </c:if>
	               
	               <c:forEach begin="1" end="${pages.totalPages}" var="p">
	                 <!-- 如果点击的页数等于当前页 -->
	                 <c:if test="${pages.currentPage==p}">
	                   <li class="active"><a href="javascript:void(0);"> ${p}</a></li>
	                 </c:if>
	                 <c:if test="${pages.currentPage!=p}">
	                   <li><a href="${pageContext.request.contextPath}/searchMypage?currentPage=${p}">${p}</a></li>
	                 </c:if>
	               </c:forEach>
	               
	                <!-- 如果当前页是最后一页 -->
	                <c:if test="${pages.currentPage==pages.totalEssay}">
	                 <li><a href=${pageContext.request.contextPath}/searchMypage?currentPage=${page.currentPage+1}>下一页</a></li>
	               </c:if>
	               <c:if test="${pages.currentPage!=pages.totalEssay}">
		             <li class="disable"><a href="#">下一页</a></li>
	               </c:if>
	             </ul>
	         </div>
           </div>
</body>
</html>