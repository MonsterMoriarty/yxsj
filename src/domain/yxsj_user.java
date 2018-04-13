package domain;

public class yxsj_user {

	// 用户ID,使用UUID
	private String yxsj_user_id;
	// 用户账号
	private String user_account;
	// 用户密码
	private String user_password;
	// 用户昵称
	private String user_nickname;
	// 用户简介
	private String user_Introduction;
	// 用户头像图片名及后缀
	private String user_img;
	// 文章数
	private int user_num_post;
	// 用户记录创建时刻
	private String user_gmt_create;

	public yxsj_user(String yxsj_user_id, String user_account, String user_password, String user_nickname,
			String user_Introduction, String user_img, int user_num_post, String user_gmt_create) {

		this.yxsj_user_id = yxsj_user_id;
		this.user_account = user_account;
		this.user_password = user_password;
		this.user_nickname = user_nickname;
		this.user_Introduction = user_Introduction;
		this.user_img = user_img;
		this.user_num_post = user_num_post;
		this.user_gmt_create = user_gmt_create;
	}

	@Override
	public String toString() {
		return "yxsj_user [yxsj_user_id=" + yxsj_user_id + ", user_account=" + user_account + ", user_password="
				+ user_password + ", user_nickname=" + user_nickname + ", user_Introduction=" + user_Introduction
				+ ", user_img=" + user_img + ", user_num_post=" + user_num_post + ", user_gmt_create=" + user_gmt_create
				+ "]";
	}

	public String getYxsj_user_id() {
		return yxsj_user_id;
	}

	public void setYxsj_user_id(String yxsj_user_id) {
		this.yxsj_user_id = yxsj_user_id;
	}

	public String getUser_account() {
		return user_account;
	}

	public void setUser_account(String user_account) {
		this.user_account = user_account;
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

	public String getUser_nickname() {
		return user_nickname;
	}

	public void setUser_nickname(String user_nickname) {
		this.user_nickname = user_nickname;
	}

	public String getUser_Introduction() {
		return user_Introduction;
	}

	public void setUser_Introduction(String user_Introduction) {
		this.user_Introduction = user_Introduction;
	}

	public String getUser_img() {
		return user_img;
	}

	public void setUser_img(String user_img) {
		this.user_img = user_img;
	}

	public int getUser_num_post() {
		return user_num_post;
	}

	public void setUser_num_post(int user_num_post) {
		this.user_num_post = user_num_post;
	}

	public String getUser_gmt_create() {
		return user_gmt_create;
	}

	public void setUser_gmt_create(String user_gmt_create) {
		this.user_gmt_create = user_gmt_create;
	}

}
