package domain;

public class DTO_comment_post_user {

	private yxsj_comment comment;

	private yxsj_post post;

	private yxsj_user user;

	public DTO_comment_post_user(yxsj_comment comment, yxsj_post post, yxsj_user user) {

		this.comment = comment;
		this.post = post;
		this.user = user;
	}

	public yxsj_comment getComment() {
		return comment;
	}

	public void setComment(yxsj_comment comment) {
		this.comment = comment;
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
