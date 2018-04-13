package servlet;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class ImgServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {

		String img = request.getParameter("img");
		FileInputStream fileInputStream = null;

		try {
			fileInputStream = new FileInputStream("F:/yxsj_img/" + img);
		} catch (FileNotFoundException e) {
			try {
				fileInputStream = new FileInputStream("F:/yxsj_img/default.jpg");
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}

		}

		try {
			int i;

			i = fileInputStream.available();

			byte[] buff = new byte[i];
			fileInputStream.read(buff);
			fileInputStream.close();
			response.setContentType("image/*");
			OutputStream outputStream = response.getOutputStream();
			outputStream.write(buff);
			outputStream.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
