package ItCForum.dao;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.dbutils.QueryRunner;

import ItCForum.domain.Images;
import ItCForum.utils.DatasourceUtils;

public class handleImageDao {

	public int  savaAvatarPath(String path,String username,String type,String titlename) throws SQLException {
		String sql;
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
		Images image = new Images();
		image.setUpTime(sdf.format(new Date()));
	if(type.contains("registerPicture")) {//登记页面图片存入user数据库
	    sql="update user set avatarpath='"+path+"' where username='"+username+"'";
	}
	else if(type.contains("afterAdd"))//先上传图片，标题栏为空时，保存题目
	{
		sql="update images set titlename='"+titlename+"' where imagepath='"+path+"'";
	}
	//发布帖子时发布的图片存入图片库
	else sql="insert into images (imagepath,uptime,titlename) values ('"+path+"','"+image.getUpTime()+"','"+titlename+"')";
		QueryRunner runner=new QueryRunner(DatasourceUtils.getDataSource());
		return runner.update(sql);
	}
}
