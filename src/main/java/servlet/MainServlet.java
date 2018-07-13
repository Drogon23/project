package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.TodoDao;
import dto.TodoDto;

/**
 * main.jsp에 데이터를 넘겨주는 서블릿
 * 
 * @author 문기선
 *
 */
@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/* 
	 * DB에서 type에 따른 데이터리스트를 가져와 main.jsp로 포워딩
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		TodoDao todoDao = new TodoDao();

		List<TodoDto> todoList = todoDao.getTodoList("TODO");
		List<TodoDto> doingList = todoDao.getTodoList("DOING");
		List<TodoDto> doneList = todoDao.getTodoList("DONE");

		request.setAttribute("todoList", todoList);
		request.setAttribute("doingList", doingList);
		request.setAttribute("doneList", doneList);

		RequestDispatcher rd = request.getRequestDispatcher("/main.jsp");
		rd.forward(request, response);
	}

	/* 
	 * 할일이 추가됐을때 doGet메서드 재호출
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		
		this.doGet(request, response);
	}

}
