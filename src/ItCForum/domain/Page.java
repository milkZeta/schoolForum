package ItCForum.domain;

public class Page {
	private int totalPages;//总页数
    private int currentPage;//当前页数
	private int pageCount=5;//每页显示个数
	private int totalEssay;//总发表文章个数
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getTotalEssay() {
		return totalEssay;
	}
	public void setTotalEssay(int totalEssay) {
		this.totalEssay = totalEssay;
	}
	
}
