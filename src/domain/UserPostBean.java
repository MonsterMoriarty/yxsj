package domain;

public class UserPostBean {

	// ��Щ�������д��û�������
	private String yxsj_post_id;

	// ��Щ���·ֱ��м������û�������
	private int comment_user_num;

	// ��Щ���·ֱ��м�������
	private int post_num_comment;

	public UserPostBean() {

	}

	public UserPostBean(String yxsj_post_id, int comment_user_num, int post_num_comment) {

		this.yxsj_post_id = yxsj_post_id;
		this.comment_user_num = comment_user_num;
		this.post_num_comment = post_num_comment;
	}

	public String getYxsj_post_id() {
		return yxsj_post_id;
	}

	public void setYxsj_post_id(String yxsj_post_id) {
		this.yxsj_post_id = yxsj_post_id;
	}

	public int getComment_user_num() {
		return comment_user_num;
	}

	public void setComment_user_num(int comment_user_num) {
		this.comment_user_num = comment_user_num;
	}

	public int getPost_num_comment() {
		return post_num_comment;
	}

	public void setPost_num_comment(int post_num_comment) {
		this.post_num_comment = post_num_comment;
	}

}
