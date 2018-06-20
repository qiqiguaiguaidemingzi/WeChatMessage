package com.lsl.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lsl.service.MaintainService;
import com.lsl.service.QueryService;


@WebServlet("/DeleteBatchServlet.action")
public class DeleteBatchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				//设置页面编码
				request.setCharacterEncoding("utf-8");
				
				//接受页面的值
				String[] ids = request.getParameterValues("ids");
				
				
				//业务需要,调用service,在service里在调用Dao层
				MaintainService maintainService = new MaintainService();
				maintainService.deleteBatch(ids);
				
				//向页面跳转
				request.getRequestDispatcher("/List.action").forward(request, response);
				
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
