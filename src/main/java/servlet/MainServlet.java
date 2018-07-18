package servlet;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
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

	private static List<TodoDto> todoList;
	private static List<TodoDto> doingList;
	private static List<TodoDto> doneList;
	
	
	
	/* 
	 * DB에서 type에 따른 데이터리스트를 가져와 main.jsp로 포워딩
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		TodoDao todoDao =  TodoDao.getInstance();

		List<TodoDto> todos = todoDao.getTodos();
		todoList = new ArrayList<>();
		doingList = new ArrayList<>();
		doneList = new ArrayList<>();
		
		groubByType(todos);
		
		request.setAttribute("todoList", todoList);
		request.setAttribute("doingList", doingList);
		request.setAttribute("doneList", doneList);

		RequestDispatcher rd = request.getRequestDispatcher("/main.jsp");
		rd.forward(request, response);
	}

	
	/**
	 * type에 따라 각 리스트로 데이터 분류
	 * 
	 * @param todos DB에 저장되어 있는 전체 데이터
	 */
	private void groubByType(List<TodoDto> todos) {
		
		for (int i = 0; i < todos.size(); i++) {
			if (todos.get(i).getType().equals("TODO")) {
				todoList.add(todos.get(i));
			} else if (todos.get(i).getType().equals("DOING")) {
				doingList.add(todos.get(i));
			} else {
				doneList.add(todos.get(i));
			}
		}
	}
	
	/* 
	 * 데이터가 추가됐을 때 doGet메서드 재호출
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		this.doGet(request, response);
	}

}
