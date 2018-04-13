package domain;

public class yxsj_comment {

	private String yxsj_comment_id;

	private String comment_post;

	private String comment_author;

	private String comment_content;

	private String comment_gmt_create;

	public yxsj_comment(String yxsj_comment_id, String comment_post, String comment_author, String comment_content,
			String comment_gmt_create) {

		this.yxsj_comment_id = yxsj_comment_id;
		this.comment_post = comment_post;
		this.comment_author = comment_author;
		this.comment_content = comment_content;
		this.comment_gmt_create = comment_gmt_create;
	}

	@Override
	public String toString() {
		return "yxsj_comment [yxsj_comment_id=" + yxsj_comment_id + ", comment_post=" + comment_post
				+ ", comment_author=" + comment_author + ", comment_content=" + comment_content
				+ ", comment_gmt_create=" + comment_gmt_create + "]";
	}

	public String getYxsj_comment_id() {
		return yxsj_comment_id;
	}

	public void setYxsj_comment_id(String yxsj_comment_id) {
		this.yxsj_comment_id = yxsj_comment_id;
	}

	public String getComment_post() {
		return comment_post;
	}

	public void setComment_post(String comment_post) {
		this.comment_post = comment_post;
	}

	public String getComment_author() {
		return comment_author;
	}

	public void setComment_author(String comment_author) {
		this.comment_author = comment_author;
	}

	public String getComment_content() {
		return comment_content;
	}

	public void setComment_content(String comment_content) {
		this.comment_content = comment_content;
	}

	public String getComment_gmt_create() {
		return comment_gmt_create;
	}

	public void setComment_gmt_create(String comment_gmt_create) {
		this.comment_gmt_create = comment_gmt_create;
	}

}
