package ItCForum.web;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import ItCForum.domain.User;
import ItCForum.service.handleImageService;  


@WebServlet("/handleImage")
public class handleImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public handleImageServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //判断普通表单还是文件上传表单，文件表单无法像普通表单一样获取
		if(ServletFileUpload.isMultipartContent(request))
        {
			DiskFileItemFactory factory = new DiskFileItemFactory();
        	//创建ServletFileUpload对象并设置大小
        	 ServletFileUpload fileUpload = new ServletFileUpload( factory); 
        	fileUpload.setSizeMax(10*1024*1024);
        	fileUpload.setHeaderEncoding("utf-8");
        	List<FileItem> fileItems = null;
			try {
				fileItems = fileUpload.parseRequest(request);
			} catch (org.apache.commons.fileupload.FileUploadException e1) {
				e1.printStackTrace();
			} 
        	Iterator<FileItem> iter = fileItems.iterator();
        	String newFileName=null;
        	String myTitle=null;
			while(iter.hasNext())
			{
				FileItem item=iter.next();
				//判断是否是普通表单文件上传
				if(item.isFormField())
				{
					//获得文件名
					 myTitle=item.getString("utf-8");
					String name=item.getFieldName();
					//获得对应的值
					
				}
				else //<input type="file">的上传文件的元素
				{
					String fileName=item.getName();
					//获得扩展名
					String extension=fileName.substring(fileName.indexOf("."));
					//创建新的文件名
					newFileName=new Date().getTime()+extension;
					//在wencontext下创建新的文件
					File file=new File("E:\\upload\\"+newFileName);
					try {
						item.write(file);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				//删除临时文件
				item.delete();
			}
			HttpSession session = request.getSession();
			String refer=request.getHeader("Referer");
			User user=(User) session.getAttribute("user");
			user.setAvatarPath("/upload/"+newFileName);
			String username=user.getUsername();
			System.out.print(username);
			if(refer.contains("register")||refer.contains("avatar.jsp"))
			{
				saveAvatarPath("/upload/"+newFileName,username,"registerPicture",myTitle);
				System.out.print("hahha");
			}
			else  //不是作为头像的图片，上传到数据库
			{
				saveAvatarPath("/upload/"+newFileName,username,"normalPicture",myTitle);
			}
			response.getWriter().write("/upload/"+newFileName);
			session.setAttribute("user", user);
        }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	//若是注册页面提交的申请，则将头像路径保存到user数据库中
	private int saveAvatarPath(String path,String username,String type,String myTitle)
	{
		int result=0;
		handleImageService service=new handleImageService();
		try {
			result=service.saveAvatarPath(path,username,type,myTitle);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
