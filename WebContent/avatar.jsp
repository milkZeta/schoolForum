<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<script>
function upFile()
{   
	//获取文件对象
	var formData = new FormData($( "#form1" )[0]);
	formData.append("","fromUrl");
	$.ajax({
		url:"${pageContext.request.contextPath}/handleImage",
		type:"post",
		data:formData,
		contentType: false,
		processData: false,
		success:(function(t)
		{
			$("#imgFrame").append("<img class='showPhoto' src='"+t+"' />");
			alert("注册成功");
			setTimeout("skip()",4000);
		})
	});
}
function skip()
{
	window.location.href="index.jsp";
}
</script>
</head>
<body>
      <jsp:include page="/header.jsp"></jsp:include>
       <div class="pageBackground">
         <div class="upPicture">
           <div class="row" id="leftOpration">
              <div class="col-md-7">      
               <div class="bigTitle" style="font-size:30px;">上传头像</div>
               <form id="form1" enctype="multipart/form-data" method="post" >
                <input  id="file1" type="file" name="uploadFile"></br>
                <input  id="upload" type="button" onclick="upFile()" value="上&nbsp;&nbsp;传"/>
                <div id="username">${role.getUsername()}</div>  
               </form>
              </div>
              <div class="col-md-5">
                <div id="imgFrame" class="imgFrame"></div>
              </div>
            </div>
         </div>
       </div>
       <div class="footer1"></div>
</body>
</html>