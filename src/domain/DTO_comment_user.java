package domain;

public class DTO_comment_user {

	private yxsj_comment comment;

	private yxsj_user user;

	public DTO_comment_user(yxsj_comment comment, yxsj_user user) {
		this.comment = comment;
		this.user = user;
	}

	@Override
	public String toString() {
		return "DTO_comment_user [comment=" + comment + ", user=" + user + "]";
	}

	public yxsj_comment getComment() {
		return comment;
	}

	public void setComment(yxsj_comment comment) {
		this.comment = comment;
	}

	public yxsj_user getUser() {
		return user;
	}

	public void setUser(yxsj_user user) {
		this.user = user;
	}

}
