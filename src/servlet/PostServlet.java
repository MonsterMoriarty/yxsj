package servlet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import domain.DTO_comment_user;
import domain.DTO_postList_user;
import domain.DTO_post_user;
import domain.DTO_recommend_post_user;
import domain.PageBean;
import service.CommentService;
import service.PostService;
import service.UserService;
import util.YxsjUtil;

@SuppressWarnings("serial")
public class PostServlet extends HttpServlet {
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
		case "newPost": {
			newPost(request, response);
			break;
		}
		case "allPost": {
			allPost(request, response);
			break;
		}
		case "detailPost": {
			detailPost(request, response);
			break;
		}
		case "poplePost": {
			poplePost(request, response);
			break;
		}
		case "adminFindPost": {
			adminFindPost(request, response);
			break;
		}
		case "adminDeletePost": {
			adminDeletePost(request, response);
			break;
		}
		case "recommendPost": {
			recommendPost(request, response);
			break;
		}

		}

	}

	private void recommendPost(HttpServletRequest request, HttpServletResponse response) {

		PostService postService = new PostService();

		String yxsj_post_id = request.getParameter("yxsj_post_id");

		String recommend_rank = request.getParameter("recommend_rank");

		try {

			postService.recommendPost(Integer.parseInt(recommend_rank), yxsj_post_id);

			request.getRequestDispatcher("/post?option=adminFindPost&currPage=1").forward(request, response);

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	private void adminDeletePost(HttpServletRequest request, HttpServletResponse response) {

		PostService postService = new PostService();

		UserService userService = new UserService();

		String yxsj_post_id = request.getParameter("yxsj_post_id");

		try {

			userService.updatePostNum(postService.findPost(yxsj_post_id).getUser().getYxsj_user_id(), -1);

			postService.deletePost(yxsj_post_id);

			request.getRequestDispatcher("/post?option=adminFindPost&currPage=1").forward(request, response);

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	private void adminFindPost(HttpServletRequest request, HttpServletResponse response) {

		PostService postService = new PostService();

		String search = request.getParameter("search");
		String currPage = request.getParameter("currPage");

		PageBean<DTO_post_user> page = new PageBean<DTO_post_user>();

		try {

			page = postService.adminFindAllPost(search, Integer.parseInt(currPage));

			request.setAttribute("page", page);

			if (search != null) {
				request.setAttribute("search", search);
			} else {
				request.setAttribute("search", "");
			}

			List<DTO_recommend_post_user> list_recommend_post = new ArrayList<DTO_recommend_post_user>();

			list_recommend_post = postService.findAllRecommendPost();

			request.setAttribute("list_recommend_post", list_recommend_post);

			request.getRequestDispatcher("/admin_post.jsp").forward(request, response);

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param request
	 * @param response
	 */
	private void poplePost(HttpServletRequest request, HttpServletResponse response) {
		PostService postService = new PostService();
		String yxsj_user_id = request.getParameter("yxsj_user_id");

		try {

			DTO_postList_user postList_user = postService.findPostByUserID(yxsj_user_id);

			request.setAttribute("postList_user", postList_user);

			request.getRequestDispatcher("/peoplePost.jsp").forward(request, response);

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void detailPost(HttpServletRequest request, HttpServletResponse response) {
		PostService postService = new PostService();
		CommentService commentService = new CommentService();

		String yxsj_post_id = request.getParameter("yxsj_post_id");

		try {

			DTO_post_user post_user = postService.findPost(yxsj_post_id);
			request.setAttribute("post_user", post_user);

			List<DTO_comment_user> comment_user_list = commentService.findCommentUserList(yxsj_post_id);
			request.setAttribute("comment_user_list", comment_user_list);

			List<DTO_post_user> list_post_user = new ArrayList<DTO_post_user>();
			list_post_user = postService.findPost_commentNum(5);
			request.setAttribute("list_post_user", list_post_user);

			request.getRequestDispatcher("/post.jsp").forward(request, response);

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void allPost(HttpServletRequest request, HttpServletResponse response) {

		PostService postService = new PostService();

		String search = request.getParameter("search");
		String sort = request.getParameter("sort");

		System.out.println("search:" + search);
		System.out.println("sort:" + sort);

		try {
			List<DTO_post_user> list = new ArrayList<DTO_post_user>();

			list = postService.findAllPost(-1, search, sort);

			int list_num = list.size();

			request.setAttribute("list", list);
			request.setAttribute("list_num", list_num);

			if (sort != null) {
				request.setAttribute("sort", sort);
			}

			if (search != null) {
				request.setAttribute("search", search);
			} else {
				request.setAttribute("search", "");
			}

			request.getRequestDispatcher("/postList.jsp").forward(request, response);

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void newPost(HttpServletRequest request, HttpServletResponse response) {

		String post_title = null;
		String post_author = null;
		String post_content = null;
		String post_img = null;

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
					case "post_title": {
						post_title = fileItem.getString("utf-8");
						break;
					}
					case "post_author": {
						post_author = fileItem.getString("utf-8");
						break;
					}
					case "post_content": {
						post_content = fileItem.getString("utf-8");
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

						post_img = YxsjUtil.getUuid() + "." + fileNameArray[1];
						System.out.println("改名后post_img:" + post_img);

						InputStream inputStream = fileItem.getInputStream();
						byte[] buffer = new byte[1024];
						int lenth = 0;

						String filePath = "F:\\yxsj_img\\" + post_img;// 文件最终上传的位置
						System.out.println(filePath);
						OutputStream outputStream = new FileOutputStream(filePath);

						while ((lenth = inputStream.read(buffer)) != -1) {
							outputStream.write(buffer, 0, lenth);
						}

						outputStream.close();
						inputStream.close();
					} else {
						post_img = "default.jpg";
					}

				}
			}

			/*
			 * 处理其他数据
			 */
			PostService postService = new PostService();

			postService.newPost(post_title, post_author, post_content, post_img);

			request.getRequestDispatcher("/post?option=poplePost&yxsj_user_id=" + post_author).forward(request,
					response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
