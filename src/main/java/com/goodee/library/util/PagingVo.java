package com.goodee.library.util;

public class PagingVo {
	private int totalCount;
	private int startPage;
	private int endPage;
	private boolean prev;
	private boolean next;
	private int pageSize = 10;
	private int page_no = 1;
	private int limit_pageNo;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                
	
	public int getPage_no() {
		return page_no;
	}

	public void setPage_no(int page_no) {
		this.page_no = page_no;
	}

	public int getLimit_pageNo() {
		return limit_pageNo;
	}

	public void setLimit_pageNo(int limit_pageNo) {
		this.limit_pageNo = limit_pageNo;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		calcData();
	}
	
	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalCount() {
		return totalCount;
	}

	private void calcData() {
		limit_pageNo = (page_no-1) * pageSize;
		endPage = (int) (Math.ceil(page_no / (double) pageSize) * pageSize);
		startPage = (endPage - pageSize) + 1;
		int tempEndPage = (int) (Math.ceil(totalCount / (double) pageSize));
		if (endPage > tempEndPage) {
            endPage = tempEndPage;
        }
		prev = startPage == 1 ? false : true;
		next = endPage * pageSize >= totalCount ? false : true;
	}
	
}
