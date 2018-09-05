package entity;

public class HotSearch {
	private Integer hotSearchId;
	private String hotSearchContent;
	private Integer count;
	
	public Integer getHotSearchId() {
		return hotSearchId;
	}
	public void setHotSearchId(Integer hotSearchId) {
		this.hotSearchId = hotSearchId;
	}
	public String getHotSearchContent() {
		return hotSearchContent;
	}
	public void setHotSearchContent(String hotSearchContent) {
		this.hotSearchContent = hotSearchContent;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public HotSearch() {
		super();
		// TODO Auto-generated constructor stub
	}
	public HotSearch(Integer hotSearchId, String hotSearchContent, Integer count) {
		super();
		this.hotSearchId = hotSearchId;
		this.hotSearchContent = hotSearchContent;
		this.count = count;
	}
}
