package servlet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import domain.PageBean;
import domain.yxsj_user;
import service.UserService;
import util.YxsjUtil;

public class UserServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String option = request.getParameter("option");

		switch (option) {
		// case "register": {
		// register(request, response);
		// break;
		// }
		// case "login": {
		// login(request, response);
		// break;
		// }
		case "logout": {
			logout(request, response);
			break;
		}
		case "my": {
			my(request, response);
			break;
		}
		case "modifyUserInformation": {
			modifyUserInformation(request, response);
			break;
		}
		case "password": {
			password(request, response);
			break;
		}
		case "verification_login": {
			verification_login(request, response);
			break;
		}
		case "verification_register": {
			verification_register(request, response);
			break;
		}
		case "adminFindUser": {
			adminFindUser(request, response);
			break;
		}
		case "adminDeleteUser": {
			adminDeleteUser(request, response);
			break;
		}
		}

	}

	private void adminDeleteUser(HttpServletRequest request, HttpServletResponse response) {

		UserService userService = new UserService();

		String yxsj_user_id = request.getParameter("yxsj_user_id");

		try {
			userService.deleteUser(yxsj_user_id);

			request.getRequestDispatcher("/user?option=adminFindUser&currPage=1").forward(request, response);

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	private void adminFindUser(HttpServletRequest request, HttpServletResponse response) {
		UserService userService = new UserService();
		String search = request.getParameter("search");
		String currPage = request.getParameter("currPage");

		PageBean<yxsj_user> page = new PageBean<yxsj_user>();

		try {
			page = userService.adminFindAllUser(search, Integer.parseInt(currPage));

			request.setAttribute("page", page);

			if (search != null) {
				request.setAttribute("search", search);
			} else {
				request.setAttribute("search", "");
			}

			request.getRequestDispatcher("/admin_user.jsp").forward(request, response);
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param request
	 * @param response
	 */
	private void verification_register(HttpServletRequest request, HttpServletResponse response) {
		String user_account = request.getParameter("user_account");
		String user_password = request.getParameter("user_password");
		String user_nickname = request.getParameter("user_nickname");

		HttpSession httpSession = request.getSession();
		UserService userService = new UserService();

		try {
			if (userService.verification_register(user_account, user_nickname)) {
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().write("账号或昵称已存在");
			} else {
				yxsj_user user = userService.register(user_account, user_password, user_nickname);

				httpSession.setAttribute("user", user);

				response.setContentType("text/html;charset=utf-8");
				response.getWriter().write(user.getYxsj_user_id());

			}
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	/**
	 * 
	 * @param request
	 * @param response
	 */
	private void verification_login(HttpServletRequest request, HttpServletResponse response) {
		UserService userService = new UserService();
		HttpSession httpSession = request.getSession();

		String user_account = request.getParameter("user_account");
		String user_password = request.getParameter("user_password");

		try {

			yxsj_user user = userService.login(user_account, user_password);

			if (user == null) {
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().write("账号或密码错误");
			} else {
				httpSession.setAttribute("user", user);
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().write(user.getYxsj_user_id());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param request
	 * @param response
	 */
	private void password(HttpServletRequest request, HttpServletResponse response) {
		UserService userService = new UserService();
		String user_password = request.getParameter("user_password");
		String yxsj_user_id = request.getParameter("yxsj_user_id");
		try {
			userService.password(yxsj_user_id, user_password);
			request.getRequestDispatcher("/user?option=my&yxsj_user_id=" + yxsj_user_id).forward(request, response);
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	/**
	 * 
	 * @param request
	 * @param response
	 */
	private void modifyUserInformation(HttpServletRequest request, HttpServletResponse response) {

		String yxsj_user_id = null;
		String user_nickname = null;
		String user_Introduction = null;
		String user_img = null;

		// 1、创建一个DiskFileItemFactory工厂
		DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
		// 2、创建一个文件上传解析器
		ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
		// 解决上传文件名的中文乱码
		servletFileUpload.setHeaderEncoding("UTF-8");

		try {
			// 1. 得到 FileItem 的集合 items
			List<FileItem> list_fileItems = servletFileUpload.parseRequest(request);

			// 2. 遍历 items:
			for (FileItem fileItem : list_fileItems) {
				// 若是一个一般的表单域, 打印信息
				if (fileItem.isFormField()) {

					switch (fileItem.getFieldName()) {
					case "yxsj_user_id": {
						yxsj_user_id = fileItem.getString("utf-8");
						break;
					}
					case "user_nickname": {
						user_nickname = fileItem.getString("utf-8");
						break;
					}
					case "user_Introduction": {
						user_Introduction = fileItem.getString("utf-8");
						break;
					}
					}
					System.out.println(fileItem.getFieldName() + ": " + fileItem.getString("utf-8"));

				}
				// 若是文件域
				else {
					String fileName = fileItem.getName();
					long sizeInBytes = fileItem.getSize();
					System.out.println("原文件名fileName:" + fileName);
					System.out.println("sizeInBytes:" + sizeInBytes);

					if (sizeInBytes != 0) {

						String[] fileNameArray = fileName.split("\\.");

						System.out.println("fileNameArray.length:" + fileNameArray.length);
						System.out.println("fileNameArray[0]:" + fileNameArray[0]);
						System.out.println("fileNameArray[1]:" + fileNameArray[1]);

						user_img = YxsjUtil.getUuid() + "." + fileNameArray[1];
						System.out.println("改名后post_img:" + user_img);

						InputStream inputStream = fileItem.getInputStream();
						byte[] buffer = new byte[1024];
						int lenth = 0;

						String filePath = "F:\\yxsj_img\\" + user_img;// 文件最终上传的位置
						System.out.println(filePath);
						OutputStream outputStream = new FileOutputStream(filePath);

						while ((lenth = inputStream.read(buffer)) != -1) {
							outputStream.write(buffer, 0, lenth);
						}

						outputStream.close();
						inputStream.close();

					} else {
						user_img = null;
					}
				}
			}

			/*
			 * 处理其他数据
			 */
			UserService userService = new UserService();

			userService.modifyUserInformation(yxsj_user_id, user_nickname, user_Introduction, user_img);

			request.getRequestDispatcher("/post?option=poplePost&yxsj_user_id=" + yxsj_user_id).forward(request,
					response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param request
	 * @param response
	 */
	private void my(HttpServletRequest request, HttpServletResponse response) {
		UserService userService = new UserService();
		HttpSession httpSession = request.getSession();
		String yxsj_user_id = request.getParameter("yxsj_user_id");

		try {

			yxsj_user user = userService.findUserByID(yxsj_user_id);

			httpSession.setAttribute("user", user);

			request.getRequestDispatcher("/my.jsp").forward(request, response);

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param request
	 * @param response
	 */
	private void logout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession httpSession = request.getSession();
		httpSession.removeAttribute("user");
		httpSession.setAttribute("user", null);
		try {
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param request
	 * @param response
	 */
	// private void register(HttpServletRequest request, HttpServletResponse
	// response) {
	//
	// UserService userService = new UserService();
	// HttpSession httpSession = request.getSession();
	//
	// String user_account = request.getParameter("user_account");
	// String user_password = request.getParameter("user_password");
	// String user_nickname = request.getParameter("user_nickname");
	// String user_Introduction = request.getParameter("user_Introduction");
	//
	// try {
	//
	// yxsj_user user = userService.register(user_account, user_password,
	// user_nickname);
	//
	// httpSession.setAttribute("user", user);
	//
	// request.getRequestDispatcher("/index.jsp").forward(request, response);
	//
	// } catch (Exception e) {
	//
	// e.printStackTrace();
	// }
	// }

}
