package com.lsl.Dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.lsl.bean.Message;
import com.lsl.db.DBAccess;

/**
 * 和message表相关的数据库操作
 * Dao层,数据访问层,与数据库交互,执行相应的数据库语句
 */
public class MessageDao {
	/**
	 * 根据查询条件,查询消息列表
	 */
//	public List<Message> queryMessageList(String command,String description){
//		
//		List<Message> messageList = new ArrayList<Message>();
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//			Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/wechat_message","root","");
//			StringBuilder sql = new StringBuilder("select id,command,description,content from message where 1=1 ");
//			
//			List<String> paramList = new ArrayList<String>();
//			if(command != null && !"".equals(command.trim())){
//				sql.append(" and command = ? ");
//				paramList.add(command);
//			}
//			if(description != null && !"".equals(description.trim())){
//				sql.append(" and description like '%' ? '%' ");
//				paramList.add(description);
//			}
//			
//			PreparedStatement statement = conn.prepareStatement(sql.toString());
//			for (int i = 0; i < paramList.size(); i++) {
//				statement.setString(i+1, paramList.get(i));
//			}
//			
//			ResultSet rs = statement.executeQuery();
//			
//			while(rs.next()){
//				Message message = new Message();
//				messageList.add(message);//链表中存放的只是对象的引用，所以这里将对象加入链表后，依然可以对对象进行操作
//				message.setId(rs.getString("id"));
//				message.setCommand(rs.getString("command"));
//				message.setDescription(rs.getString("description"));
//				message.setContent(rs.getString("content"));
//			}
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
//		return messageList;
//	}
	
	//通过mybatis进行开发：
	public List<Message> queryMessageList(Map<String,Object> parameter){
		DBAccess dBAccess = new DBAccess();
		SqlSession sqlSession = null;
		List<Message> messageList = new ArrayList<Message>(); //存放数据库匹配到的数据,将其返回给service层
		try {
			sqlSession = dBAccess.getSqlSession();
			//通过sqlSession执行sql语句
			messageList = sqlSession.selectList("Message.queryMessageList",parameter);//参数为配置中sql语句的命名空间.id名,除了ID外,只允许传递一个参数
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(sqlSession != null)
			sqlSession.close();
		}
		return messageList;
	}
	
	public int count(Message message){
		DBAccess dBAccess = new DBAccess();
		SqlSession sqlSession = null;
		int result = 0;
		try {
			sqlSession = dBAccess.getSqlSession();
			//通过sqlSession执行sql语句
			result = sqlSession.selectOne("Message.queryMessageCount",message);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(sqlSession != null) {
				sqlSession.close();
			}
		}
		return result;
	}
	
//	public static void main(String[] args) {
//		MessageDao messageDao = new MessageDao();
//		messageDao.queryMessageList("", "");
//		java.sql.Types.INTEGER
//	}
	
	/**
	 * 单条删除
	 */
	public void deleteOne(int id){
		DBAccess dBAccess = new DBAccess();
		SqlSession sqlSession = null;
		try {
			sqlSession = dBAccess.getSqlSession();
			//通过sqlSession执行sql语句
			sqlSession.delete("Message.deleteOne",id);//参数为配置中sql语句的命名空间.id名,除了ID外,只允许传递一个参数
			sqlSession.commit();
			/* 此处必须提交,数据库中的数据才会真正进行修改
			 * 本来Connection默认是自动提交的,但是mybatis对它进行了封装,默认是不自动提交的
			 * */
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(sqlSession != null)
			sqlSession.close();
		}
		
	}
	
	/**
	 *  批量删除
	 * */
	public void deleteBatch(/*List<Integer>*/String[] ids){
		DBAccess dBAccess = new DBAccess();
		SqlSession sqlSession = null;
		try {
			sqlSession = dBAccess.getSqlSession();
			//通过sqlSession执行sql语句
			sqlSession.delete("Message.deleteBatch",ids);//参数为配置中sql语句的命名空间.id名,除了ID外,只允许传递一个参数
			sqlSession.commit();
			/* 此处必须提交,数据库中的数据才会真正进行修改
			 * 本来Connection默认是自动提交的,但是mybatis对它进行了封装,默认是不自动提交的
			 * */
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(sqlSession != null)
			sqlSession.close();
		}
		
	}
	
}
