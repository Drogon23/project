package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.TodoDao;
import dto.TodoDto;

/**
 * 할일 데이터를 추가해주는 서블릿
 * 
 * @author 문기선
 *
 */
@WebServlet("/TodoAddServlet")
public class TodoAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/* 
	 * 할일 데이터 추가 후 MainServlet으로 포워딩
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		TodoDto todoDto = new TodoDto();
		todoDto.setTitle(request.getParameter("title"));
		todoDto.setName(request.getParameter("name"));
		todoDto.setSequence(Integer.parseInt(request.getParameter("seq")));

		TodoDao todoDao = new TodoDao();
		todoDao.addTodo(todoDto);

		RequestDispatcher rd = request.getRequestDispatcher("/MainServlet");
		rd.forward(request, response);

	}

}
