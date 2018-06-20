package com.lsl.Servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lsl.bean.Message;
import com.lsl.entity.Page;
import com.lsl.service.QueryService;

/**
 * 列表页面初始化控制
 */
@WebServlet(value="/List.action")
public class ListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置页面编码
		request.setCharacterEncoding("utf-8");
		
		//接受页面的值
		String command = request.getParameter("command");
		String description = request.getParameter("description");
		String currentPage = request.getParameter("currentPage");
		
		//创建分页对象
		Page page = new Page();
		Pattern pattern = Pattern.compile("[0-9]{1,9}");
		if(currentPage == null || !pattern.matcher(currentPage).matches()){
			page.setCurrentPage(1);
		}else{
			page.setCurrentPage(Integer.valueOf(currentPage));
		}
		
		
		//业务需要,调用service,在service里在调用Dao层
		QueryService queryService = new QueryService();
		
		//查询消息列表并传给页面
		request.setAttribute("messageList", queryService.queryMessageList(command, description,page));
		
		//向页面传值
		request.setAttribute("command", command);
		request.setAttribute("description", description);
		request.setAttribute("page", page);
		
		//向页面跳转
		request.getRequestDispatcher("/WEB-INF/jsp/back/list.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
