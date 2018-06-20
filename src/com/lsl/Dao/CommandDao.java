package com.lsl.Dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.lsl.bean.Command;
import com.lsl.bean.Message;
import com.lsl.db.DBAccess;

/**
 * 与指令表对应的数据库操作类
 *
 */
public class CommandDao {
	/**
	 * 根据查询条件查询指令列表
	 */
	public List<Command> queryCommandList(String name,String description){
		DBAccess dBAccess = new DBAccess();
		SqlSession sqlSession = null;
		List<Command> commandList = new ArrayList<Command>(); //存放数据库匹配到的数据,将其返回给service层
		Command command = new Command();
		command.setName(name);
		command.setDescription(description);
		try {
			sqlSession = dBAccess.getSqlSession();
			//通过sqlSession执行sql语句
//			commandList = sqlSession.selectList("Command.queryCommandList",command);//参数为配置中sql语句的命名空间.id名,除了ID外,只允许传递一个参数(----原来的方法)
			
			ICommand iCommand = sqlSession.getMapper(ICommand.class);  //面向接口编程,让配置文件的命名空间名等于对应接口的全限定名,接口中方法名必须等于对应sql语句的id名
			commandList = iCommand.queryCommandList(command);  //与原来写法相比好处有:1、防止拼写命名空间名.id名时出错; 
		    //2、防止参数类型和承接返回数据的对象的类型,与配置文件中sql语句的参数和返回数据类型不同(因为原来的方法，这两个是object类型,无论怎么写，编译都不会检测出错误)
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(sqlSession != null)
			sqlSession.close();
		}
		return commandList;
	}
}
