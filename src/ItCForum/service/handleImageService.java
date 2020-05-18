package ItCForum.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.SQLException;
import java.util.Base64;

import ItCForum.dao.handleImageDao;

public class handleImageService {

	public boolean commitImage(String path,String image)  {
		//保存到服务器
//			 FileInputStream in=inImageStream(image);
//			 outImageStream(in,path);//输入到服务器端的文件下
	    //将图片的数据流保存到服务器下的路径
	boolean f=createImg(image,path);
	  if(f)return true;
	  else return false;
	}
	//获取图片的输入流
	public FileInputStream inImageStream(String path) throws FileNotFoundException
	 {
		 return new FileInputStream(new File(path));
	 }
	//获取图片的输出流
	public void outImageStream(FileInputStream in,String path)
	{
		//path服务器的图片存储的地址
		 File file = new File(path);
		 FileOutputStream fos = null;
		          try {
		              fos = new FileOutputStream(file);
		              int len = 0;
		              byte[] buf = new byte[1024];
		              while ((len = in.read(buf)) != -1) {
		                  fos.write(buf, 0, len);
		              }
		              fos.flush();
		          } catch (Exception e) {
		              e.printStackTrace();
		          } finally {
		              if (null != fos) {
		                  try {
		                      fos.close();
		                  } catch (IOException e) {
		                      e.printStackTrace();
		                  }
		             }
		         }
	}
	//base64字符串转化成图片  
    public  boolean createImg(String imgStr,String imgPath)  
    {   //对字节数组字符串进行Base64解码并生成图片  
        if (imgStr == null) //图像数据为空  
            return false;  
        Base64.Decoder decoder = Base64.getDecoder(); 
        try   
        {  
            //Base64解码  
            byte[] b = decoder.decode(imgStr);  
            for(int i=0;i<b.length;++i)  
            {  
                if(b[i]<0)  
                {//调整异常数据  
                    b[i]+=256;  
                }  
            }  
            //生成jpeg图片  
            Path path=Paths.get(imgPath);
            Files.write(path, b, StandardOpenOption.CREATE);
            OutputStream out = new FileOutputStream(imgPath);      
            out.write(b);  
            out.flush();  
            out.close();  
            return true;  
        }   
        catch (Exception e)   
        {  
            return false;  
        }  
    }
	public int saveAvatarPath(String path,String username,String type,String myTitle) throws SQLException {
		// TODO Auto-generated method stub
		handleImageDao dao=new handleImageDao();
		return dao.savaAvatarPath(path,username,type,myTitle);
	}
}
