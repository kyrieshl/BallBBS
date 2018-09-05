package entity;

import java.io.Serializable;
import java.sql.Timestamp;


import lombok.Data;
@Data
@SuppressWarnings("serial")
public class PostReply implements Serializable{
	private Integer replyId;
	private Post post;
	private User user;
	private Integer floor;
	private Integer parentFloor;
	private String replyContent;
	private Timestamp replyTime;
	public PostReply(){
		
	}
}
