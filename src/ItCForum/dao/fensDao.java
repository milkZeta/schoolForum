package ItCForum.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import ItCForum.domain.Relation;
import ItCForum.utils.DatasourceUtils;

public class fensDao {

	public List<Relation> getFens(String username) throws SQLException {
		String sql="select * from relation where fellow='"+username+"'";
		QueryRunner runner=new QueryRunner(DatasourceUtils.getDataSource());
		return runner.query(sql, new BeanListHandler<Relation>(Relation.class));
	
	}

	public List<Relation> getFellow(String username) throws SQLException {
		String sql="select * from relation where fens='"+username+"'";
		QueryRunner runner=new QueryRunner(DatasourceUtils.getDataSource());
		return runner.query(sql, new BeanListHandler<Relation>(Relation.class));
	
	}

	public int addFellow(String username, String fellowObj,String fellowAvatarPath,String fensAvatarPath) throws SQLException {
		String sql="select * from relation where fellow='"+fellowObj+"' and fens='"+username+"'";
		QueryRunner runner=new QueryRunner(DatasourceUtils.getDataSource());
		if( runner.query(sql,new BeanHandler<Relation>(Relation.class))!=null) {
		  String sql1="delete from relation where fellow='"+fellowObj+"' and fens='"+username+"'";
		  if(runner.update(sql1)!=0)return 1;
		  else return 0;
		}
		else {
			String sql2="insert into relation (fens,fellow,fellowavatar,fensavatar) values(?,?,?,?)";
			if(runner.update(sql2,username,fellowObj,fellowAvatarPath,fensAvatarPath)!=0)return 2;
			else return 0;
			
		}
	}

}
