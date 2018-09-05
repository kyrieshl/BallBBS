package entity;

import java.sql.Timestamp;

import lombok.Data;

@Data
@SuppressWarnings("serial")
public class Post implements java.io.Serializable {


	private Integer postId;
	private User user;
	private String topic;
	private String matter;
	private Timestamp postTime;
	private Integer postReplyAmount;

	public Post() {
	}

	public Post(User user, String topic, String matter, Timestamp postTime) {
		this.user = user;
		this.topic = topic;
		this.matter = matter;
		this.postTime = postTime;
	}
}