package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.DTO_post_user;
import domain.DTO_recommend_post_user;
import service.PostService;

@SuppressWarnings("serial")
public class IndexServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PostService postService = new PostService();

		try {

			List<DTO_post_user> list_time = new ArrayList<DTO_post_user>();
			List<DTO_post_user> list_comment = new ArrayList<DTO_post_user>();
			/*
			 * 
			 */
			list_time = postService.findAllPost(4, null, null);
			request.setAttribute("list_time", list_time);
			/*
			 * 
			 */
			list_comment = postService.findPost_commentNum(5);
			request.setAttribute("list_comment", list_comment);
			/*
			 * 
			 */
			List<DTO_recommend_post_user> list_recommend_post = new ArrayList<DTO_recommend_post_user>();

			list_recommend_post = postService.findAllRecommendPost();

			if (list_recommend_post.size() > 0) {
				if (list_recommend_post.get(0) != null) {
					request.setAttribute("recommend_post_1", list_recommend_post.get(0));
				}
			}
			if (list_recommend_post.size() > 1) {
				if (list_recommend_post.get(1) != null) {
					request.setAttribute("recommend_post_2", list_recommend_post.get(1));
				}
			}
			if (list_recommend_post.size() > 2) {
				if (list_recommend_post.get(2) != null) {
					request.setAttribute("recommend_post_3", list_recommend_post.get(2));
				}
			}

			request.getRequestDispatcher("/shouye.jsp").forward(request, response);

		} catch (Exception e) {

			e.printStackTrace();
		}
	}
}
