package domain;

public class DTO_post_user {

	private yxsj_post post;

	private yxsj_user user;

	public DTO_post_user(yxsj_post post, yxsj_user user) {
		this.post = post;
		this.user = user;
	}

	@Override
	public String toString() {
		return "DTO_post_user [post=" + post + ", user=" + user + "]";
	}

	public yxsj_post getPost() {
		return post;
	}

	public void setPost(yxsj_post post) {
		this.post = post;
	}

	public yxsj_user getUser() {
		return user;
	}

	public void setUser(yxsj_user user) {
		this.user = user;
	}

}
