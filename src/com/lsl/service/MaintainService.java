package com.lsl.service;

import java.util.ArrayList;
import java.util.List;

import com.lsl.Dao.MessageDao;

/**
 * 维护相关的业务功能
 * service层,复杂对控制层传过来的数据进行处理,并进行相应的业务操作、算法等等
 * 如果有需要,就调用相应的Dao层去访问数据库
 */
public class MaintainService {

	/**
	 * 单条删除
	 */
	public void deleteOne(String id){
		if(id != null && !"".equals(id.trim())){
			MessageDao messageDao = new MessageDao();
			messageDao.deleteOne(Integer.valueOf(id));
		}
	}
	
	/**
	 * 批量删除
	 */
	public void deleteBatch(String[] ids){
		MessageDao messageDao = new MessageDao();
		/*List<Integer> idList = new ArrayList<Integer>();
		for (String id : ids) {
			idList.add(Integer.valueOf(id));
		}
		messageDao.deleteBatch(idList);*/
		messageDao.deleteBatch(ids);
	}
}
