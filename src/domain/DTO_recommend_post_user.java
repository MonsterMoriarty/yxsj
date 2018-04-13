package domain;

public class DTO_recommend_post_user {

	private yxsj_post post;

	private yxsj_recommend recommend;

	private yxsj_user user;

	public DTO_recommend_post_user(yxsj_post post, yxsj_recommend recommend, yxsj_user user) {

		this.post = post;
		this.recommend = recommend;
		this.user = user;
	}

	public yxsj_post getPost() {
		return post;
	}

	public void setPost(yxsj_post post) {
		this.post = post;
	}

	public yxsj_recommend getRecommend() {
		return recommend;
	}

	public void setRecommend(yxsj_recommend recommend) {
		this.recommend = recommend;
	}

	public yxsj_user getUser() {
		return user;
	}

	public void setUser(yxsj_user user) {
		this.user = user;
	}

}
