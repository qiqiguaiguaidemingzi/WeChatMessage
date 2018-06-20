package com.lsl.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lsl.service.MaintainService;
import com.lsl.service.QueryService;

/**
 * 单条删除的控制层
 */
@WebServlet(value="/DeleteOneServlet.action")
public class DeleteOneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				//设置页面编码
				request.setCharacterEncoding("utf-8");
				
				//接受页面的值
				String id = request.getParameter("id");
				
				//业务需要,调用service,在service里在调用Dao层
				MaintainService maintainService = new MaintainService();
				maintainService.deleteOne(id);
				/* 在这里的id有两个问题需要解决：
				 * 		1、这个参数id可能为空,我们应该对它进行判断
				 * 		2、我们得到的这个id为string类型,但是数据库中为int类型,我们需要进行类型转化
				 *  但是,以上这两个问题,我们都不在这里进行操作。
				 *  	因为控制层这里应该只进行简单的判断和跳转,
				 *  	稍稍复杂点的业务逻辑,我们应该在业务层(service)进行操作
				 * */
				
				//向页面跳转
				request.getRequestDispatcher("/List.action").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
