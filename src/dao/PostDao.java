package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import domain.DTO_comment_post_user;
import domain.DTO_postList_user;
import domain.DTO_post_user;
import domain.DTO_recommend_post_user;
import domain.UserPostBean;
import domain.yxsj_comment;
import domain.yxsj_post;
import domain.yxsj_recommend;
import domain.yxsj_user;

public class PostDao {

	public static Connection connection = null;
	public static PreparedStatement preparedStmt = null;
	public static Statement stmt = null;
	public static ResultSet resultSet = null;

	public void addPost(yxsj_post post) throws SQLException {
		connection = con_MySQL.getCon();

		String sql = "insert into yxsj_post values('" + post.getYxsj_post_id() + "','" + post.getPost_title() + "','"
				+ post.getPost_author() + "','" + post.getPost_content() + "','" + post.getPost_num_comment() + "','"
				+ post.getPost_img() + "','" + post.getPost_gmt_create() + "')";

		System.out.println(sql);

		preparedStmt = connection.prepareStatement(sql);

		preparedStmt.execute();
	}

	public List<DTO_post_user> findAllPost(int num, String search, String sort) throws SQLException {

		connection = con_MySQL.getCon();

		String sql = "select * from yxsj_post,yxsj_user where yxsj_post.post_author=yxsj_user.yxsj_user_id order by yxsj_post.post_gmt_create desc";

		if (num != -1) {
			sql = "select * from yxsj_post,yxsj_user where   yxsj_post.post_author=yxsj_user.yxsj_user_id order by yxsj_post.post_gmt_create desc limit "
					+ num + "";
		} else {

			if (search != null) {
				sql = "select * from yxsj_post,yxsj_user where yxsj_post.post_author=yxsj_user.yxsj_user_id and (yxsj_post.post_title like '%"
						+ search + "%' or yxsj_post.post_content like '%" + search
						+ "%' or yxsj_user.user_nickname like '%" + search
						+ "%') order by yxsj_post.post_num_comment desc";
			} else if (sort != null) {
				if (sort.equals("time")) {
					sql = "select * from yxsj_post,yxsj_user where yxsj_post.post_author=yxsj_user.yxsj_user_id order by yxsj_post.post_gmt_create desc";
				} else if (sort.equals("hot")) {
					sql = "select * from yxsj_post,yxsj_user where yxsj_post.post_author=yxsj_user.yxsj_user_id order by yxsj_post.post_num_comment desc";
				}
			}

		}

		System.out.println(sql);

		preparedStmt = connection.prepareStatement(sql);

		resultSet = preparedStmt.executeQuery();

		List<DTO_post_user> list = new ArrayList<DTO_post_user>();

		DTO_post_user post_user = null;

		yxsj_post post = null;

		yxsj_user user = null;

		while (resultSet.next()) {

			post = new yxsj_post(resultSet.getString("yxsj_post_id"), resultSet.getString("post_title"),
					resultSet.getString("post_author"), resultSet.getString("post_content"),
					resultSet.getInt("post_num_comment"), resultSet.getString("post_img"),
					resultSet.getString("post_gmt_create"));
			user = new yxsj_user(resultSet.getString("yxsj_user_id"), resultSet.getString("user_account"),
					resultSet.getString("user_password"), resultSet.getString("user_nickname"),
					resultSet.getString("user_Introduction"), resultSet.getString("user_img"),
					resultSet.getInt("user_num_post"), resultSet.getString("user_gmt_create"));

			// 让搜索结果变色
			if (search != null) {
				post.setPost_title(
						post.getPost_title().replaceAll(search, "<span style='color: #ff5063;'>" + search + "</span>"));

				post.setPost_content(post.getPost_content().replaceAll(search,
						"<span style='color: #ff5063;'>" + search + "</span>"));

				user.setUser_nickname(user.getUser_nickname().replaceAll(search,
						"<span style='color: #ff5063;'>" + search + "</span>"));
			}

			post_user = new DTO_post_user(post, user);

			list.add(post_user);

		}

		return list;

	}

	public List<DTO_post_user> findPost_ORDERcommentNum(int num) throws SQLException {

		connection = con_MySQL.getCon();

		String sql;

		if (num != -1) {
			sql = "select * from yxsj_post,yxsj_user where yxsj_post.post_author=yxsj_user.yxsj_user_id order by yxsj_post.post_num_comment desc, yxsj_post.post_gmt_create desc limit "
					+ num + "";
		} else {
			sql = "select * from yxsj_post,yxsj_user where yxsj_post.post_author=yxsj_user.yxsj_user_id order by yxsj_post.post_num_comment desc, yxsj_post.post_gmt_create desc";
		}

		System.out.println(sql);

		preparedStmt = connection.prepareStatement(sql);

		resultSet = preparedStmt.executeQuery();

		List<DTO_post_user> list = new ArrayList<DTO_post_user>();

		DTO_post_user post_user = null;

		yxsj_post post = null;

		yxsj_user user = null;

		while (resultSet.next()) {

			post = new yxsj_post(resultSet.getString("yxsj_post_id"), resultSet.getString("post_title"),
					resultSet.getString("post_author"), resultSet.getString("post_content"),
					resultSet.getInt("post_num_comment"), resultSet.getString("post_img"),
					resultSet.getString("post_gmt_create"));
			user = new yxsj_user(resultSet.getString("yxsj_user_id"), resultSet.getString("user_account"),
					resultSet.getString("user_password"), resultSet.getString("user_nickname"),
					resultSet.getString("user_Introduction"), resultSet.getString("user_img"),
					resultSet.getInt("user_num_post"), resultSet.getString("user_gmt_create"));

			post_user = new DTO_post_user(post, user);

			list.add(post_user);

		}

		return list;

	}

	public DTO_post_user findPost(String yxsj_post_id) throws SQLException {

		connection = con_MySQL.getCon();

		String sql = "select * from yxsj_post,yxsj_user where yxsj_post.post_author=yxsj_user.yxsj_user_id and yxsj_post_id='"
				+ yxsj_post_id + "'";

		System.out.println(sql);

		preparedStmt = connection.prepareStatement(sql);

		resultSet = preparedStmt.executeQuery();

		DTO_post_user post_user = null;

		yxsj_post post = null;

		yxsj_user user = null;

		if (resultSet.next()) {
			post = new yxsj_post(resultSet.getString("yxsj_post_id"), resultSet.getString("post_title"),
					resultSet.getString("post_author"), resultSet.getString("post_content"),
					resultSet.getInt("post_num_comment"), resultSet.getString("post_img"),
					resultSet.getString("post_gmt_create"));
			user = new yxsj_user(resultSet.getString("yxsj_user_id"), resultSet.getString("user_account"),
					resultSet.getString("user_password"), resultSet.getString("user_nickname"),
					resultSet.getString("user_Introduction"), resultSet.getString("user_img"),
					resultSet.getInt("user_num_post"), resultSet.getString("user_gmt_create"));

			post_user = new DTO_post_user(post, user);
		}

		return post_user;
	}

	public DTO_postList_user findPostByUserID(String yxsj_user_id) throws SQLException {

		connection = con_MySQL.getCon();

		DTO_postList_user postList_user = null;

		List<yxsj_post> postList = new ArrayList<yxsj_post>();

		yxsj_post post = null;

		yxsj_user user = null;

		String sql_post = "select * from yxsj_post where post_author='" + yxsj_user_id
				+ "' order by post_gmt_create desc";
		String sql_user = "select * from yxsj_user where yxsj_user_id='" + yxsj_user_id + "'";
		System.out.println(sql_post);
		System.out.println(sql_user);

		preparedStmt = connection.prepareStatement(sql_post);

		resultSet = preparedStmt.executeQuery();

		while (resultSet.next()) {

			post = new yxsj_post(resultSet.getString("yxsj_post_id"), resultSet.getString("post_title"),
					resultSet.getString("post_author"), resultSet.getString("post_content"),
					resultSet.getInt("post_num_comment"), resultSet.getString("post_img"),
					resultSet.getString("post_gmt_create"));

			postList.add(post);

		}

		preparedStmt = connection.prepareStatement(sql_user);

		resultSet = preparedStmt.executeQuery();

		if (resultSet.next()) {
			user = new yxsj_user(resultSet.getString("yxsj_user_id"), resultSet.getString("user_account"),
					resultSet.getString("user_password"), resultSet.getString("user_nickname"),
					resultSet.getString("user_Introduction"), resultSet.getString("user_img"),
					resultSet.getInt("user_num_post"), resultSet.getString("user_gmt_create"));
		}

		postList_user = new DTO_postList_user(postList, user);

		return postList_user;
	}

	public void updatePostCommentNum(yxsj_post post) throws SQLException {

		connection = con_MySQL.getCon();

		String sql = "update yxsj_post set post_num_comment='" + post.getPost_num_comment() + "' where yxsj_post_id='"
				+ post.getYxsj_post_id() + "'";
		System.out.println(sql);
		stmt = connection.createStatement();
		stmt.executeUpdate(sql);
	}

	public int findCount(String search) throws SQLException {

		connection = con_MySQL.getCon();

		String sql_post = "select count(*) count from yxsj_post where post_title like '%" + search
				+ "%' order by post_gmt_create desc";

		preparedStmt = connection.prepareStatement(sql_post);

		resultSet = preparedStmt.executeQuery();

		if (resultSet.next()) {

			return resultSet.getInt("count");
		}

		return 0;
	}

	public List<DTO_post_user> adminFindPost(String search, int begin, int pageSize) throws SQLException {

		connection = con_MySQL.getCon();

		String sql_post = "select * from yxsj_post,yxsj_user where yxsj_post.post_author=yxsj_user.yxsj_user_id and yxsj_post.post_title like '%"
				+ search + "%' order by yxsj_post.post_gmt_create desc limit " + Integer.toString(pageSize) + " offset "
				+ Integer.toString(begin);
		System.out.println(sql_post);
		preparedStmt = connection.prepareStatement(sql_post);
		resultSet = preparedStmt.executeQuery();

		List<DTO_post_user> list = new ArrayList<DTO_post_user>();

		DTO_post_user post_user = null;

		yxsj_post post = null;

		yxsj_user user = null;

		while (resultSet.next()) {

			post = new yxsj_post(resultSet.getString("yxsj_post_id"), resultSet.getString("post_title"),
					resultSet.getString("post_author"), resultSet.getString("post_content"),
					resultSet.getInt("post_num_comment"), resultSet.getString("post_img"),
					resultSet.getString("post_gmt_create"));
			user = new yxsj_user(resultSet.getString("yxsj_user_id"), resultSet.getString("user_account"),
					resultSet.getString("user_password"), resultSet.getString("user_nickname"),
					resultSet.getString("user_Introduction"), resultSet.getString("user_img"),
					resultSet.getInt("user_num_post"), resultSet.getString("user_gmt_create"));

			// 让搜索结果变色
			if (search != null) {
				post.setPost_title(
						post.getPost_title().replaceAll(search, "<span style='color: #ff5063;'>" + search + "</span>"));

			}

			post_user = new DTO_post_user(post, user);

			list.add(post_user);

		}

		return list;
	}

	public void removePost(String yxsj_post_id) throws SQLException {

		connection = con_MySQL.getCon();
		String sql = "delete from yxsj_post where yxsj_post_id='" + yxsj_post_id + "' ";
		stmt = connection.prepareStatement(sql);
		stmt.executeUpdate(sql);
	}

	public void updateRecommend(yxsj_recommend recommend) throws SQLException {

		connection = con_MySQL.getCon();

		String sql = "update yxsj_recommend set recommend_post='" + recommend.getRecommend_post()
				+ "',recommend_gmt_modified='" + recommend.getRecommend_gmt_modified() + "' where recommend_rank='"
				+ recommend.getRecommend_rank() + "'";
		System.out.println(sql);
		stmt = connection.createStatement();
		stmt.executeUpdate(sql);
	}

	public List<DTO_recommend_post_user> findAllRecommendPost() throws SQLException {

		connection = con_MySQL.getCon();

		List<DTO_recommend_post_user> list = new ArrayList<DTO_recommend_post_user>();

		DTO_recommend_post_user recommend_post = null;

		yxsj_recommend recommend = null;

		yxsj_post post = null;

		yxsj_user user = null;

		String sql = "select * from yxsj_recommend,yxsj_post,yxsj_user where yxsj_recommend.recommend_post=yxsj_post.yxsj_post_id and yxsj_user.yxsj_user_id=yxsj_post.post_author  order by yxsj_recommend.recommend_rank asc";
		System.out.println(sql);
		preparedStmt = connection.prepareStatement(sql);
		resultSet = preparedStmt.executeQuery();

		while (resultSet.next()) {

			recommend = new yxsj_recommend(resultSet.getInt("recommend_rank"), resultSet.getString("recommend_post"),
					resultSet.getString("recommend_gmt_modified"));

			post = new yxsj_post(resultSet.getString("yxsj_post_id"), resultSet.getString("post_title"),
					resultSet.getString("post_author"), resultSet.getString("post_content"),
					resultSet.getInt("post_num_comment"), resultSet.getString("post_img"),
					resultSet.getString("post_gmt_create"));

			user = new yxsj_user(resultSet.getString("yxsj_user_id"), resultSet.getString("user_account"),
					resultSet.getString("user_password"), resultSet.getString("user_nickname"),
					resultSet.getString("user_Introduction"), resultSet.getString("user_img"),
					resultSet.getInt("user_num_post"), resultSet.getString("user_gmt_create"));

			recommend_post = new DTO_recommend_post_user(post, recommend, user);

			list.add(recommend_post);

		}

		return list;
	}

	public List<DTO_comment_post_user> adminFindCommentPostUser(String search, int begin, int pageSize)
			throws SQLException {

		connection = con_MySQL.getCon();

		String sql_post = "select * from yxsj_comment,yxsj_post,yxsj_user where yxsj_comment.comment_author=yxsj_user.yxsj_user_id and yxsj_comment.comment_post=yxsj_post.yxsj_post_id and (yxsj_post.post_title like '%"
				+ search + "%' or yxsj_comment.comment_content like '%" + search
				+ "%')   order by yxsj_comment.comment_post desc limit " + Integer.toString(pageSize) + " offset "
				+ Integer.toString(begin);

		System.out.println(sql_post);
		preparedStmt = connection.prepareStatement(sql_post);
		resultSet = preparedStmt.executeQuery();

		List<DTO_comment_post_user> list = new ArrayList<DTO_comment_post_user>();

		DTO_comment_post_user comment_post_user = null;

		yxsj_comment comment = null;

		yxsj_post post = null;

		yxsj_user user = null;

		while (resultSet.next()) {

			comment = new yxsj_comment(resultSet.getString("yxsj_comment_id"), resultSet.getString("comment_post"),
					resultSet.getString("comment_author"), resultSet.getString("comment_content"),
					resultSet.getString("comment_gmt_create"));

			post = new yxsj_post(resultSet.getString("yxsj_post_id"), resultSet.getString("post_title"),
					resultSet.getString("post_author"), resultSet.getString("post_content"),
					resultSet.getInt("post_num_comment"), resultSet.getString("post_img"),
					resultSet.getString("post_gmt_create"));
			user = new yxsj_user(resultSet.getString("yxsj_user_id"), resultSet.getString("user_account"),
					resultSet.getString("user_password"), resultSet.getString("user_nickname"),
					resultSet.getString("user_Introduction"), resultSet.getString("user_img"),
					resultSet.getInt("user_num_post"), resultSet.getString("user_gmt_create"));

			// 让搜索结果变色
			if (search != null) {
				post.setPost_title(
						post.getPost_title().replaceAll(search, "<span style='color: #ff5063;'>" + search + "</span>"));

				comment.setComment_content(comment.getComment_content().replaceAll(search,
						"<span style='color: #ff5063;'>" + search + "</span>"));

			}

			comment_post_user = new DTO_comment_post_user(comment, post, user);

			list.add(comment_post_user);

		}

		return list;
	}

	/**
	 * 
	 * 
	 * @param yxsj_user_id
	 * @throws SQLException
	 */
	public List<UserPostBean> findPost_TheUser_Comment(String yxsj_user_id) throws SQLException {

		connection = con_MySQL.getCon();

		String sql_1 = "select distinct yxsj_post.yxsj_post_id,yxsj_post.post_num_comment from yxsj_user,yxsj_comment,yxsj_post where yxsj_comment.comment_author=yxsj_user.yxsj_user_id and yxsj_comment.comment_post=yxsj_post.yxsj_post_id and yxsj_user.yxsj_user_id = '"
				+ yxsj_user_id + "'";

		preparedStmt = connection.prepareStatement(sql_1);

		resultSet = preparedStmt.executeQuery();

		List<UserPostBean> list = new ArrayList<UserPostBean>();

		UserPostBean userPostBean = null;
		/*
		 * 
		 */

		Connection connection2 = null;
		PreparedStatement preparedStmt2 = null;
		Statement stmt2 = null;
		ResultSet resultSet2 = null;

		while (resultSet.next()) {

			connection2 = con_MySQL.getCon();

			String sql_2 = "select count(*) count from yxsj_user,yxsj_comment,yxsj_post where yxsj_comment.comment_author=yxsj_user.yxsj_user_id and yxsj_comment.comment_post=yxsj_post.yxsj_post_id and yxsj_post.yxsj_post_id='"
					+ resultSet.getString("yxsj_post_id") + "' and yxsj_user.yxsj_user_id = '" + yxsj_user_id + "'";

			preparedStmt2 = connection2.prepareStatement(sql_2);

			resultSet2 = preparedStmt2.executeQuery();
			if (resultSet2.next()) {
				userPostBean = new UserPostBean(resultSet.getString("yxsj_post_id"), resultSet2.getInt("count"),
						resultSet.getInt("post_num_comment"));
			}
			/*
			 * 
			 */
			list.add(userPostBean);

		}

		return list;

	}

	public void updatePostCommentNum(List<UserPostBean> list_UserPostBean) throws SQLException {

		connection = con_MySQL.getCon();

		int i = list_UserPostBean.size();

		while (i-- > 0) {
			String sql = "update yxsj_post set post_num_comment='"
					+ (list_UserPostBean.get(i).getPost_num_comment() - list_UserPostBean.get(i).getComment_user_num())
					+ "' where yxsj_post_id ='" + list_UserPostBean.get(i).getYxsj_post_id() + "'";

			stmt = connection.createStatement();

			stmt.executeUpdate(sql);

		}

	}
}
