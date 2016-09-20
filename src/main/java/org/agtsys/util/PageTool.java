package org.agtsys.util;

public class PageTool {
	private Integer index;
	private Integer size = 5;
	private Integer begin; 
	private Integer total_count;
	private Integer total_pages;
	
	public PageTool(Integer index,Integer size,Integer total_count){
		this.index = index;
		this.size = size;
		//计算第一条记录的偏移位置
		this.begin = (index-1)*size;
		this.total_count = total_count;
		//根据总条数计算总页数
		if(total_count!=null)
			this.total_pages = total_count % size == 0 ? total_count / size : total_count / size + 1;
	}
	public Integer getIndex() {
		return index;
	}
	public Integer getSize() {
		return size;
	}
	public Integer getTotal_count() {
		return total_count;
	}
	public Integer getTotal_pages() {
		return total_pages;
	}
	public Integer getBegin() {
		return begin;
	}
}
