package domain;

public class yxsj_post {

	private String yxsj_post_id;

	private String post_title;

	private String post_author;

	private String post_content;

	private int post_num_comment;

	private String post_img;

	private String post_gmt_create;

	public yxsj_post(String yxsj_post_id, String post_title, String post_author, String post_content,
			int post_num_comment, String post_img, String post_gmt_create) {

		this.yxsj_post_id = yxsj_post_id;
		this.post_title = post_title;
		this.post_author = post_author;
		this.post_content = post_content;
		this.post_num_comment = post_num_comment;
		this.post_img = post_img;
		this.post_gmt_create = post_gmt_create;
	}

	public String getYxsj_post_id() {
		return yxsj_post_id;
	}

	public void setYxsj_post_id(String yxsj_post_id) {
		this.yxsj_post_id = yxsj_post_id;
	}

	public String getPost_title() {
		return post_title;
	}

	public void setPost_title(String post_title) {
		this.post_title = post_title;
	}

	public String getPost_author() {
		return post_author;
	}

	public void setPost_author(String post_author) {
		this.post_author = post_author;
	}

	public String getPost_content() {
		return post_content;
	}

	public void setPost_content(String post_content) {
		this.post_content = post_content;
	}

	public int getPost_num_comment() {
		return post_num_comment;
	}

	public void setPost_num_comment(int post_num_comment) {
		this.post_num_comment = post_num_comment;
	}

	public String getPost_img() {
		return post_img;
	}

	public void setPost_img(String post_img) {
		this.post_img = post_img;
	}

	public String getPost_gmt_create() {
		return post_gmt_create;
	}

	public void setPost_gmt_create(String post_gmt_create) {
		this.post_gmt_create = post_gmt_create;
	}

	@Override
	public String toString() {
		return "yxsj_post [yxsj_post_id=" + yxsj_post_id + ", post_title=" + post_title + ", post_author=" + post_author
				+ ", post_content=" + post_content + ", post_num_comment=" + post_num_comment + ", post_img=" + post_img
				+ ", post_gmt_create=" + post_gmt_create + "]";
	}

}
