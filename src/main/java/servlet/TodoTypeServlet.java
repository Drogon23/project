package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.TodoDao;

/**
 * main.jsp에서 화살표 버튼을 눌렀을때 할일 type을 변경시켜주는 서블릿
 * 
 * @author 문기선
 *
 */
@WebServlet("/TodoTypeServlet")
public class TodoTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/* 
	 * todo->doing, doing->done으로 변경 후 성공시 'success' 실패시 'fail'출력
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		TodoDao todoDao = new TodoDao();
		int count = todoDao.updateType(Long.parseLong(request.getParameter("id")), request.getParameter("type"));
		PrintWriter out = response.getWriter();
		if (count == 1) {
			out.print("success");
		} else {
			out.print("fail");
		}

	}

}
