package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import domain.yxsj_user;

public class UserDao {

	public static Connection connection = null;
	public static PreparedStatement preparedStmt = null;
	public static Statement stmt = null;
	public static ResultSet resultSet = null;

	/**
	 * 
	 * @param user
	 * @throws SQLException
	 */
	public void addUser(yxsj_user user) throws SQLException {

		connection = con_MySQL.getCon();

		String sql = "insert into yxsj_user values('" + user.getYxsj_user_id() + "','" + user.getUser_account() + "','"
				+ user.getUser_password() + "','" + user.getUser_nickname() + "','" + user.getUser_Introduction()
				+ "','" + user.getUser_img() + "','" + user.getUser_num_post() + "','" + user.getUser_gmt_create()
				+ "')";
		System.out.println(sql);

		preparedStmt = connection.prepareStatement(sql);

		preparedStmt.execute();
	}

	/**
	 * 
	 * @param user_account
	 * @param user_password
	 * @return
	 * @throws SQLException
	 */
	public yxsj_user findUserByAccountAndPassword(String user_account, String user_password) throws SQLException {

		connection = con_MySQL.getCon();

		String sql = "select * from yxsj_user where user_account='" + user_account + "' and user_password='"
				+ user_password + "'";
		System.out.println(sql);

		preparedStmt = connection.prepareStatement(sql);

		resultSet = preparedStmt.executeQuery();

		if (resultSet.next()) {
			yxsj_user user = new yxsj_user(resultSet.getString("yxsj_user_id"), resultSet.getString("user_account"),
					resultSet.getString("user_password"), resultSet.getString("user_nickname"),
					resultSet.getString("user_Introduction"), resultSet.getString("user_img"),
					resultSet.getInt("user_num_post"), resultSet.getString("user_gmt_create"));
			return user;
		} else {
			return null;
		}

	}

	public boolean findUserByAccountAndNickname(String user_account, String user_nickname) throws SQLException {

		connection = con_MySQL.getCon();

		String sql_account = "select * from yxsj_user where user_account='" + user_account + "'";

		System.out.println(sql_account);

		String sql_nickname = "select * from yxsj_user where user_nickname='" + user_nickname + "'";

		System.out.println(sql_nickname);

		preparedStmt = connection.prepareStatement(sql_account);
		resultSet = preparedStmt.executeQuery();

		if (resultSet.next()) {
			return true;
		} else {

			preparedStmt = connection.prepareStatement(sql_nickname);
			resultSet = preparedStmt.executeQuery();

			if (resultSet.next()) {
				return true;
			} else {
				return false;
			}

		}

	}

	/**
	 * 
	 * @param yxsj_user_id
	 * @return
	 * @throws SQLException
	 */
	public yxsj_user findUserByID(String yxsj_user_id) throws SQLException {

		connection = con_MySQL.getCon();

		String sql = "select * from yxsj_user where yxsj_user_id='" + yxsj_user_id + "'";
		System.out.println(sql);

		preparedStmt = connection.prepareStatement(sql);

		resultSet = preparedStmt.executeQuery();

		if (resultSet.next()) {
			yxsj_user user = new yxsj_user(resultSet.getString("yxsj_user_id"), resultSet.getString("user_account"),
					resultSet.getString("user_password"), resultSet.getString("user_nickname"),
					resultSet.getString("user_Introduction"), resultSet.getString("user_img"),
					resultSet.getInt("user_num_post"), resultSet.getString("user_gmt_create"));
			return user;
		} else {
			return null;
		}

	}

	/**
	 * 
	 * @param user
	 * @throws SQLException
	 */
	public void updateUser(yxsj_user user) throws SQLException {
		connection = con_MySQL.getCon();

		String sql = "update yxsj_user set  user_password='" + user.getUser_password() + "',user_nickname='"
				+ user.getUser_nickname() + "',user_Introduction='" + user.getUser_Introduction() + "',user_img='"
				+ user.getUser_img() + "', user_num_post='" + user.getUser_num_post() + "' where yxsj_user_id='"
				+ user.getYxsj_user_id() + "'";
		System.out.println(sql);
		stmt = connection.createStatement();
		stmt.executeUpdate(sql);
	}

	public List<yxsj_user> adminFindUser(String search, int begin, int pageSize) throws SQLException {
		System.out.println("adminFindUser3");
		connection = con_MySQL.getCon();

		String sql_user = "select * from yxsj_user where  yxsj_user.user_nickname like '%" + search
				+ "%' order by yxsj_user.user_num_post desc limit " + Integer.toString(pageSize) + " offset "
				+ Integer.toString(begin);
		System.out.println(sql_user);
		preparedStmt = connection.prepareStatement(sql_user);
		resultSet = preparedStmt.executeQuery();

		List<yxsj_user> list = new ArrayList<yxsj_user>();

		yxsj_user user = null;

		while (resultSet.next()) {

			user = new yxsj_user(resultSet.getString("yxsj_user_id"), resultSet.getString("user_account"),
					resultSet.getString("user_password"), resultSet.getString("user_nickname"),
					resultSet.getString("user_Introduction"), resultSet.getString("user_img"),
					resultSet.getInt("user_num_post"), resultSet.getString("user_gmt_create"));

			// 让搜索结果变色
			if (search != null) {
				user.setUser_nickname(user.getUser_nickname().replaceAll(search,
						"<span style='color: #ff5063;'>" + search + "</span>"));

			}

			list.add(user);

		}

		return list;
	}

	public int findCount(String search) throws SQLException {

		connection = con_MySQL.getCon();

		String sql_user = "select count(*) count from yxsj_user where user_nickname like '%" + search
				+ "%' order by user_num_post desc";

		preparedStmt = connection.prepareStatement(sql_user);

		resultSet = preparedStmt.executeQuery();

		if (resultSet.next()) {

			return resultSet.getInt("count");
		}

		return 0;
	}

	public void deleteUser(String yxsj_user_id) throws SQLException {
		connection = con_MySQL.getCon();
		String sql = "delete from yxsj_user where yxsj_user_id='" + yxsj_user_id + "' ";
		stmt = connection.prepareStatement(sql);
		stmt.executeUpdate(sql);
	}

}
