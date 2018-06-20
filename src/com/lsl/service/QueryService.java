package com.lsl.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.lsl.Dao.CommandDao;
import com.lsl.Dao.MessageDao;
import com.lsl.bean.Command;
import com.lsl.bean.CommandContent;
import com.lsl.bean.Message;
import com.lsl.entity.Page;
import com.lsl.util.Iconst;

/**
 * 列表相关的业务功能
 *
 */
public class QueryService {
	
	
	public List<Message> queryMessageList(String command,String description,Page page){
		//组织消息对象
		Message message = new Message();
		message.setCommand(command);
		message.setDescription(description);
		MessageDao messageDao = new MessageDao();
		//根据条件查询，符合条件的消息总数目
		int totalNumber = messageDao.count(message);
		//组织分页查询limit的参数
		page.setTotalNumber(totalNumber);
		Map<String,Object> parameter = new HashMap<String,Object>();
		parameter.put("message", message);
		parameter.put("page", page);
		return messageDao.queryMessageList(parameter);
	}
	
	/**
	 * 通过指令查询自动回复的内容
	 * @param command 指令
	 * @return 自动回复的内容
	 */
	public String queryByCommand(String command){
		CommandDao commandDao = new CommandDao();
		List<Command> commandList;
		
		if(Iconst.HELP_COMMAND.equals(command)){
			commandList = commandDao.queryCommandList(null, null);
			StringBuilder resullt = new StringBuilder();
			for (int i = 0; i < commandList.size(); i++) {
				if( i != 0 )
					resullt.append("<br />");
				resullt.append("回复["+ commandList.get(i).getName() +"]可以查看"+ commandList.get(i).getDescription());
			}
			return resullt.toString();
		}
		
		commandList = commandDao.queryCommandList(command, null);
		if(commandList.size() > 0){
			List<CommandContent> contentList = commandList.get(0).getContentList();
			int i = new Random().nextInt(contentList.size());
			return contentList.get(i).getContent();
		}
		return Iconst.NO_MATCHING_CONTENT;
	}
	
}
