package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import domain.DTO_comment_user;
import domain.yxsj_comment;
import domain.yxsj_user;

public class CommentDao {

	public static Connection connection = null;
	public static PreparedStatement preparedStmt = null;
	public static Statement stmt = null;
	public static ResultSet resultSet = null;

	public void addComment(yxsj_comment comment) throws SQLException {

		connection = con_MySQL.getCon();

		String sql = "insert into yxsj_comment values('" + comment.getYxsj_comment_id() + "','"
				+ comment.getComment_post() + "','" + comment.getComment_author() + "','" + comment.getComment_content()
				+ "','" + comment.getComment_gmt_create() + "')";

		System.out.println(sql);

		preparedStmt = connection.prepareStatement(sql);

		preparedStmt.execute();
	}

	public List<DTO_comment_user> findCommentUserList(String comment_post) throws SQLException {

		connection = con_MySQL.getCon();

		String sql = "select * from yxsj_user,yxsj_comment where yxsj_user.yxsj_user_id=yxsj_comment.comment_author and yxsj_comment.comment_post='"
				+ comment_post + "' order by yxsj_comment.comment_gmt_create asc";

		System.out.println(sql);

		preparedStmt = connection.prepareStatement(sql);

		resultSet = preparedStmt.executeQuery();

		List<DTO_comment_user> list = new ArrayList<DTO_comment_user>();

		DTO_comment_user comment_user = null;

		yxsj_comment comment = null;

		yxsj_user user = null;

		while (resultSet.next()) {

			comment = new yxsj_comment(resultSet.getString("yxsj_comment_id"), resultSet.getString("comment_post"),
					resultSet.getString("comment_author"), resultSet.getString("comment_content"),
					resultSet.getString("comment_gmt_create"));
			user = new yxsj_user(resultSet.getString("yxsj_user_id"), resultSet.getString("user_account"),
					resultSet.getString("user_password"), resultSet.getString("user_nickname"),
					resultSet.getString("user_Introduction"), resultSet.getString("user_img"),
					resultSet.getInt("user_num_post"), resultSet.getString("user_gmt_create"));

			comment_user = new DTO_comment_user(comment, user);

			list.add(comment_user);

		}

		return list;

	}

	public void deleteComment(String yxsj_comment_id) throws SQLException {
		connection = con_MySQL.getCon();
		String sql = "delete from yxsj_comment where yxsj_comment_id='" + yxsj_comment_id + "' ";
		stmt = connection.prepareStatement(sql);
		stmt.executeUpdate(sql);
	}

	public yxsj_comment findCommentByCommentID(String yxsj_comment_id) throws SQLException {
		connection = con_MySQL.getCon();
		String sql = "select * from yxsj_comment where  yxsj_comment.yxsj_comment_id='" + yxsj_comment_id + "'";

		System.out.println(sql);

		preparedStmt = connection.prepareStatement(sql);

		resultSet = preparedStmt.executeQuery();

		yxsj_comment comment = null;

		if (resultSet.next()) {
			comment = new yxsj_comment(resultSet.getString("yxsj_comment_id"), resultSet.getString("comment_post"),
					resultSet.getString("comment_author"), resultSet.getString("comment_content"),
					resultSet.getString("comment_gmt_create"));
		}

		return comment;

	}

	public int findCount(String search) throws SQLException {

		connection = con_MySQL.getCon();

		String sql = "select count(*) count from yxsj_comment where comment_content like '%" + search + "%'";

		preparedStmt = connection.prepareStatement(sql);

		resultSet = preparedStmt.executeQuery();

		if (resultSet.next()) {

			return resultSet.getInt("count");
		}

		return 0;
	}

}
