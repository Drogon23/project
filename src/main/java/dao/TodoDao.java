package dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import dto.TodoDto;

/**
 * pjt2 데이터베이스에 접근하기 위한 클래스
 * 
 * @author 문기선
 *
 */
public class TodoDao {
	private static Connection conn;
	private static Properties statementProperty;

	public TodoDao() {
		InputStream inputStream = getClass().getResourceAsStream("/statement.properties");
		statementProperty = new Properties();
		try {
			statementProperty.load(inputStream);
			conn = DatabaseConnection.getConnection();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * DB에 저장되어 있는 모든 데이터 반환
	 * 
	 * @return DB에 있는 모든 데이터
	 */
	public List<TodoDto> getTodos() {
		List<TodoDto> todoList = new ArrayList<>();
		String sql = statementProperty.getProperty("getTodosSql");

		try (PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {

				TodoDto todoDto = new TodoDto(rs);
				todoList.add(todoDto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		DatabaseConnection.close();
		return todoList;
	}

	/**
	 * 데이터 한건 추가
	 * 
	 * @return 데이터 추가 성공시 1
	 */
	public int addTodo(TodoDto todoDto) {
		int insertCount = 0;
		String sql = statementProperty.getProperty("addTodoSql");

		try (PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, todoDto.getTitle());
			ps.setString(2, todoDto.getName());
			ps.setInt(3, todoDto.getSequence());

			insertCount = ps.executeUpdate();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		DatabaseConnection.close();
		return insertCount;
	}

	/**
	 * 한건의 데이터 type을 todo->doing, doing->done으로 수정
	 * 
	 * @return 데이터 수정 성공시 1
	 */
	public int updateTodo(Long id, String type) {
		int updateCount = 0;

		String sql = statementProperty.getProperty("updateTodoSql");
		try (PreparedStatement ps = conn.prepareStatement(sql)) {

			if (type.equals("TODO")) {
				type = "DOING";
			} else {
				type = "DONE";
			}
			ps.setString(1, type);
			ps.setLong(2, id);

			updateCount = ps.executeUpdate();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		DatabaseConnection.close();

		return updateCount;
	}

}
