package service;

import java.sql.SQLException;
import java.util.List;

import dao.CommentDao;
import dao.PostDao;
import dao.UserDao;
import domain.PageBean;
import domain.UserPostBean;
import domain.yxsj_user;
import util.YxsjUtil;

public class UserService {

	/**
	 * 
	 * @param user_account
	 * @param user_password
	 * @param user_nickname
	 * @param user_Introduction
	 * @return
	 * @throws SQLException
	 */
	public yxsj_user register(String user_account, String user_password, String user_nickname) throws SQLException {
		UserDao userDao = new UserDao();

		String yxsj_user_id = YxsjUtil.getUuid();

		String user_img = "default.jpg";

		String user_Introduction = "";

		int user_num_post = 0;

		String user_gmt_create = YxsjUtil.getDateSecond();

		yxsj_user user = new yxsj_user(yxsj_user_id, user_account, user_password, user_nickname, user_Introduction,
				user_img, user_num_post, user_gmt_create);

		System.out.println(user.toString());

		userDao.addUser(user);

		return user;
	}

	/**
	 * 
	 * @param user_account
	 * @param user_password
	 * @return
	 * @throws SQLException
	 */
	public yxsj_user login(String user_account, String user_password) throws SQLException {
		UserDao userDao = new UserDao();
		yxsj_user user = userDao.findUserByAccountAndPassword(user_account, user_password);
		return user;
	}

	/**
	 * 
	 * @param yxsj_user_id
	 * @param num
	 * @throws SQLException
	 */
	public void updatePostNum(String yxsj_user_id, int num) throws SQLException {

		System.out.println("updatePostNum:yxsj_user_id" + yxsj_user_id);

		UserDao userDao = new UserDao();

		yxsj_user user = userDao.findUserByID(yxsj_user_id);

		user.setUser_num_post(user.getUser_num_post() + num);

		userDao.updateUser(user);
	}

	/**
	 * 
	 * @param yxsj_user_id
	 * @return
	 * @throws SQLException
	 */
	public yxsj_user findUserByID(String yxsj_user_id) throws SQLException {
		UserDao userDao = new UserDao();
		yxsj_user user = userDao.findUserByID(yxsj_user_id);
		return user;
	}

	/**
	 * 
	 * @param yxsj_user_id
	 * @param user_nickname
	 * @param user_Introduction
	 * @param user_img
	 * @throws SQLException
	 */
	public void modifyUserInformation(String yxsj_user_id, String user_nickname, String user_Introduction,
			String user_img) throws SQLException {
		UserDao userDao = new UserDao();
		yxsj_user user;

		user = userDao.findUserByID(yxsj_user_id);

		user.setUser_nickname(user_nickname);
		user.setUser_Introduction(user_Introduction);
		if (user_img != null) {
			user.setUser_img(user_img);
		}

		userDao.updateUser(user);
	}

	public void password(String yxsj_user_id, String user_password) throws SQLException {
		UserDao userDao = new UserDao();
		yxsj_user user;

		user = userDao.findUserByID(yxsj_user_id);

		user.setUser_password(user_password);

		userDao.updateUser(user);
	}

	public boolean verification_register(String user_account, String user_nickname) throws SQLException {

		UserDao userDao = new UserDao();

		return userDao.findUserByAccountAndNickname(user_account, user_nickname);
	}

	public PageBean<yxsj_user> adminFindAllUser(String search, int currPage) throws SQLException {
		System.out.println("adminFindUser2");
		UserDao userDao = new UserDao();

		PageBean<yxsj_user> page = new PageBean<yxsj_user>();

		// 封装当前页数
		page.setCurrPage(currPage);

		// 每页显示的记录数
		int pageSize = 10;
		// 封装每页显示 的记录数
		page.setPageSize(pageSize);

		if (search == null) {
			search = "";
		}

		// 查询总记录数
		int totalCount = userDao.findCount(search);
		// 封装总记录数
		page.setTotalCount(totalCount);

		// 查询总页数
		int totalPage = 1 + ((totalCount - 1) / pageSize);
		// 封装总页数
		page.setTotalPage(totalPage);

		// 按当前页面，查询当前页面记录
		int begin = (currPage - 1) * pageSize;

		page.setList(userDao.adminFindUser(search, begin, pageSize));

		return page;

	}

	public void deleteUser(String yxsj_user_id) throws SQLException {

		UserDao userDao = new UserDao();

		PostDao postDao = new PostDao();

		CommentDao commentDao = new CommentDao();

		/*
		 * 1、查询哪些文章里有此用户的评论
		 *
		 * 2、这些文章分别有几条此用户的评论
		 *
		 * 3、这些文章分别有几条评论
		 * 
		 * 4、分别更新这些写文章
		 *
		 */
		List<UserPostBean> list_UserPostBean = postDao.findPost_TheUser_Comment(yxsj_user_id);
		postDao.updatePostCommentNum(list_UserPostBean);
		/*
		 * 5、最后才删除用户
		 */
		userDao.deleteUser(yxsj_user_id);
	}
}
