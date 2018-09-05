package entity;

import java.sql.Timestamp;

import lombok.Data;
@Data
@SuppressWarnings("serial")
public class User implements java.io.Serializable {

	private Integer userId;
	private String userName;
	private String userPassword;
	private String userEmail;
	private Integer sex;
	private Integer userPoints;
	private String userRemark;
	private String userImage;

	public User() {
	}

	public User(String userName, String userPassword, String userEmail,
			Integer sex,Integer userPoints,String userImage) {
		this.userName = userName;
		this.userPassword = userPassword;
		this.userEmail = userEmail;
		this.sex = sex;
		this.userPoints = userPoints;
		this.userImage = userImage;
	}
}