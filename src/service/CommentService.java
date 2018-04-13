package service;

import java.sql.SQLException;
import java.util.List;

import dao.CommentDao;
import dao.PostDao;
import domain.DTO_comment_user;
import domain.DTO_post_user;
import domain.yxsj_comment;
import domain.yxsj_post;
import util.YxsjUtil;

public class CommentService {

	public void addComment(String comment_post, String comment_author, String comment_content) throws SQLException {

		CommentDao commentDao = new CommentDao();
		PostDao postDao = new PostDao();

		String yxsj_comment_id = YxsjUtil.getUuid();

		String comment_gmt_create = YxsjUtil.getDateSecond();

		yxsj_comment comment = new yxsj_comment(yxsj_comment_id, comment_post, comment_author, comment_content,
				comment_gmt_create);

		commentDao.addComment(comment);

		/*
		 * 更新文章评论数
		 */
		DTO_post_user post_user = postDao.findPost(comment_post);

		yxsj_post post = post_user.getPost();

		post.setPost_num_comment(post.getPost_num_comment() + 1);

		postDao.updatePostCommentNum(post);
	}

	public List<DTO_comment_user> findCommentUserList(String comment_post) throws SQLException {

		CommentDao commentDao = new CommentDao();

		List<DTO_comment_user> list_comment_user = commentDao.findCommentUserList(comment_post);

		return list_comment_user;

	}

	public void deleteComment(String yxsj_comment_id) throws SQLException {

		CommentDao commentDao = new CommentDao();

		yxsj_comment comment = commentDao.findCommentByCommentID(yxsj_comment_id);

		PostDao postDao = new PostDao();

		DTO_post_user post_user = postDao.findPost(comment.getComment_post());

		yxsj_post post = post_user.getPost();

		post.setPost_num_comment(post.getPost_num_comment() - 1);
		/*
		 * 
		 */
		postDao.updatePostCommentNum(post);

		/*
		 * 
		 */
		commentDao.deleteComment(yxsj_comment_id);
	}

}
