package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.CommentDao;
import dao.PostDao;
import domain.DTO_comment_post_user;
import domain.DTO_postList_user;
import domain.DTO_post_user;
import domain.DTO_recommend_post_user;
import domain.PageBean;
import domain.yxsj_post;
import domain.yxsj_recommend;
import util.YxsjUtil;

public class PostService {

	public void newPost(String post_title, String post_author, String post_content, String post_img)
			throws SQLException {
		PostDao postDao = new PostDao();
		UserService userService = new UserService();

		String yxsj_post_id = YxsjUtil.getUuid();

		int post_num_comment = 0;

		String post_gmt_create = YxsjUtil.getDateSecond();

		yxsj_post post = new yxsj_post(yxsj_post_id, post_title, post_author, post_content, post_num_comment, post_img,
				post_gmt_create);

		// �������
		postDao.addPost(post);

		// �����û�������
		userService.updatePostNum(post_author, 1);

	}

	public List<DTO_post_user> findAllPost(int num, String search, String sort) throws SQLException {

		PostDao postDao = new PostDao();

		List<DTO_post_user> list = new ArrayList<DTO_post_user>();

		list = postDao.findAllPost(num, search, sort);

		return list;

	}

	public PageBean<DTO_post_user> adminFindAllPost(String search, int currPage) throws SQLException {

		PostDao postDao = new PostDao();

		PageBean<DTO_post_user> page = new PageBean<DTO_post_user>();

		// ��װ��ǰҳ��
		page.setCurrPage(currPage);

		// ÿҳ��ʾ�ļ�¼��
		int pageSize = 10;
		// ��װÿҳ��ʾ �ļ�¼��
		page.setPageSize(pageSize);

		if (search == null) {
			search = "";
		}

		// ��ѯ�ܼ�¼��
		int totalCount = postDao.findCount(search);
		// ��װ�ܼ�¼��
		page.setTotalCount(totalCount);

		// ��ѯ��ҳ��
		int totalPage = 1 + ((totalCount - 1) / pageSize);
		// ��װ��ҳ��
		page.setTotalPage(totalPage);

		// ����ǰҳ�棬��ѯ��ǰҳ���¼
		int begin = (currPage - 1) * pageSize;

		if (search == null) {
			search = "";
		}

		page.setList(postDao.adminFindPost(search, begin, pageSize));

		return page;

	}

	public List<DTO_post_user> findPost_commentNum(int num) throws SQLException {

		PostDao postDao = new PostDao();

		List<DTO_post_user> list = new ArrayList<DTO_post_user>();

		list = postDao.findPost_ORDERcommentNum(num);

		return list;

	}

	public DTO_post_user findPost(String yxsj_post_id) throws SQLException {

		PostDao postDao = new PostDao();

		DTO_post_user post_user = postDao.findPost(yxsj_post_id);

		return post_user;

	}

	public DTO_postList_user findPostByUserID(String yxsj_user_id) throws SQLException {

		PostDao postDao = new PostDao();

		DTO_postList_user postList_user = postDao.findPostByUserID(yxsj_user_id);

		return postList_user;

	}

	public void deletePost(String yxsj_post_id) throws SQLException {
		PostDao postDao = new PostDao();
		postDao.removePost(yxsj_post_id);

	}

	public void recommendPost(int recommend_rank, String recommend_post) throws SQLException {
		PostDao postDao = new PostDao();

		yxsj_recommend recommend = new yxsj_recommend(recommend_rank, recommend_post, YxsjUtil.getDateSecond());

		postDao.updateRecommend(recommend);

	}

	public List<DTO_recommend_post_user> findAllRecommendPost() throws SQLException {
		PostDao postDao = new PostDao();

		List<DTO_recommend_post_user> list = new ArrayList<DTO_recommend_post_user>();

		list = postDao.findAllRecommendPost();

		return list;
	}

	public PageBean<DTO_comment_post_user> adminFindAllComment(String search, int currPage) throws SQLException {
		PostDao postDao = new PostDao();
		CommentDao commentDao = new CommentDao();

		PageBean<DTO_comment_post_user> page = new PageBean<DTO_comment_post_user>();

		// ��װ��ǰҳ��
		page.setCurrPage(currPage);

		// ÿҳ��ʾ�ļ�¼��
		int pageSize = 10;
		// ��װÿҳ��ʾ �ļ�¼��
		page.setPageSize(pageSize);

		if (search == null) {
			search = "";
		}

		// ��ѯ�ܼ�¼��
		int totalCount = commentDao.findCount(search);
		// ��װ�ܼ�¼��
		page.setTotalCount(totalCount);

		// ��ѯ��ҳ��
		int totalPage = 1 + ((totalCount - 1) / pageSize);
		// ��װ��ҳ��
		page.setTotalPage(totalPage);

		// ����ǰҳ�棬��ѯ��ǰҳ���¼
		int begin = (currPage - 1) * pageSize;

		page.setList(postDao.adminFindCommentPostUser(search, begin, pageSize));

		return page;

	}
}
