package domain;

import java.util.List;

public class DTO_postList_user {

	private List<yxsj_post> postList;

	private yxsj_user user;

	public DTO_postList_user(List<yxsj_post> postList, yxsj_user user) {

		this.postList = postList;
		this.user = user;
	}

	public List<yxsj_post> getPostList() {
		return postList;
	}

	public void setPostList(List<yxsj_post> postList) {
		this.postList = postList;
	}

	public yxsj_user getUser() {
		return user;
	}

	public void setUser(yxsj_user user) {
		this.user = user;
	}

}
