package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.DTO_comment_post_user;
import domain.DTO_comment_user;
import domain.DTO_post_user;
import domain.PageBean;
import service.CommentService;
import service.PostService;

@SuppressWarnings("serial")
public class CommentServlet extends HttpServlet {
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
		case "newComment": {
			newComment(request, response);
			break;
		}
		case "adminFindComment": {
			adminFindComment(request, response);
			break;
		}
		case "adminDeleteComment": {
			adminDeleteComment(request, response);
			break;
		}
		}

	}

	private void adminDeleteComment(HttpServletRequest request, HttpServletResponse response) {

		CommentService commentService = new CommentService();

		String yxsj_comment_id = request.getParameter("yxsj_comment_id");

		try {

			/*
			 * 文章评论减一，未做
			 */

			commentService.deleteComment(yxsj_comment_id);

			request.getRequestDispatcher("/comment?option=adminFindComment&currPage=1").forward(request, response);

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	private void adminFindComment(HttpServletRequest request, HttpServletResponse response) {

		PostService postService = new PostService();

		String search = request.getParameter("search");
		String currPage = request.getParameter("currPage");
		PageBean<DTO_comment_post_user> page = new PageBean<DTO_comment_post_user>();
		try {
			page = postService.adminFindAllComment(search, Integer.parseInt(currPage));

			request.setAttribute("page", page);

			if (search != null) {
				request.setAttribute("search", search);
			} else {
				request.setAttribute("search", "");
			}

			request.getRequestDispatcher("/admin_comment.jsp").forward(request, response);
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param request
	 * @param response
	 */
	private void newComment(HttpServletRequest request, HttpServletResponse response) {

		CommentService commentService = new CommentService();
		PostService postService = new PostService();

		String comment_post = request.getParameter("comment_post");
		String comment_author = request.getParameter("comment_author");
		String comment_content = request.getParameter("comment_content");

		try {
			commentService.addComment(comment_post, comment_author, comment_content);
		} catch (SQLException e1) {

			e1.printStackTrace();
		}

		try {
			DTO_post_user post_user = postService.findPost(comment_post);
			request.setAttribute("post_user", post_user);

			List<DTO_comment_user> list_comment = commentService.findCommentUserList(comment_post);
			request.setAttribute("list_comment", list_comment);

			request.getRequestDispatcher("/post?option=detailPost&yxsj_post_id=" + comment_post).forward(request,
					response);

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

}
