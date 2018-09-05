package entity;


import java.sql.Timestamp;


public class Media {

	private Integer vedioId;
	private User user;
	private String title;
	private String src;
	private String picture;
	private String descript;
	private Timestamp uptime;
	private Integer beLikedAmount;
	
	public Integer getVedioId() {
		return vedioId;
	}
	public void setVedioId(Integer VedioId) {
		this.vedioId = VedioId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getDescript() {
		return descript;
	}
	public void setDescript(String descript) {
		this.descript = descript;
	}
	public Timestamp getUptime() {
		return uptime;
	}
	public void setUptime(Timestamp uptime) {
		this.uptime = uptime;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Integer getBeLikedAmount() {
		return beLikedAmount;
	}
	public void setBeLikedAmount(Integer beLikedAmount) {
		this.beLikedAmount = beLikedAmount;
	}
}
