package domain;

public class yxsj_recommend {

	private int recommend_rank;

	private String recommend_post;

	private String recommend_gmt_modified;

	public yxsj_recommend() {

	}

	public yxsj_recommend(int recommend_rank, String recommend_post, String recommend_gmt_modified) {

		this.recommend_rank = recommend_rank;
		this.recommend_post = recommend_post;

		this.recommend_gmt_modified = recommend_gmt_modified;
	}

	@Override
	public String toString() {
		return "yxsj_recommend [recommend_rank=" + recommend_rank + ", recommend_post=" + recommend_post
				+ ", recommend_gmt_modified=" + recommend_gmt_modified + "]";
	}

	public int getRecommend_rank() {
		return recommend_rank;
	}

	public void setRecommend_rank(int recommend_rank) {
		this.recommend_rank = recommend_rank;
	}

	public String getRecommend_post() {
		return recommend_post;
	}

	public void setRecommend_post(String recommend_post) {
		this.recommend_post = recommend_post;
	}

	public String getRecommend_gmt_modified() {
		return recommend_gmt_modified;
	}

	public void setRecommend_gmt_modified(String recommend_gmt_modified) {
		this.recommend_gmt_modified = recommend_gmt_modified;
	}

}
