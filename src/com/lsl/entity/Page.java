package com.lsl.entity;

/**
 * 分页对应的实体类
 *
 */
public class Page {
	
	private int totalNumber;  //总条数
	
	private int currentPage;  //当前第几页
	
	private int totalPage;    //总页数
	
	private int pageNumber = 5;   //每页显示几条信息
	
	private int dbIndex;      //数据库中limit的参数，从第几条开始取（第一条是0）
	
	private int dbNumber;     //数据库中limit的参数，取几条信息
	
	/**
	 * 根据当前对象中的属性值，计算并设置相关属性值
	 */
	private void count(){     
		//计算总页数
		int totalPageTemp = this.totalNumber / this.pageNumber;
		int plus = (this.totalNumber % this.pageNumber) == 0 ? 0 : 1; 
		totalPageTemp = totalPageTemp + plus;
		if(totalPageTemp <= 0){
			totalPageTemp = 1;
		}
		this.totalPage = totalPageTemp;
		
		//设置当前页数
		//如果总页数小于前端传过来的要查询的当前页数，就把当前页数设置为最后一页
		if(this.totalPage < this.currentPage){
			this.currentPage = this.totalPage;
		}
		//当前页数小于1设置为1
		if(this.currentPage < 1){
			this.currentPage = 1;
		}
		
		//设置limit的参数
		this.dbIndex = (this.currentPage - 1) * this.pageNumber;
		this.dbNumber = this.pageNumber;
	}

	public int getTotalNumber() {
		return totalNumber;
	}

	public void setTotalNumber(int totalNumber) {
		this.totalNumber = totalNumber;
		this.count();
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getDbIndex() {
		return dbIndex;
	}

	public void setDbIndex(int dbIndex) {
		this.dbIndex = dbIndex;
	}

	public int getDbNumber() {
		return dbNumber;
	}

	public void setDbNumber(int dbNumber) {
		this.dbNumber = dbNumber;
	}
	
}
