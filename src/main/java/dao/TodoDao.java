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
	private static Connection connection = null;
	private static String dbUrl, dbUser, dbPasswd;
	private static Properties databasePropery;

	/**
	 * 현재 connection이 없을때만 초기화
	 */
	private TodoDao() {
		if (connection == null) {
			InputStream inputStreamDatabase = getClass().getResourceAsStream("/database.properties");
			databasePropery = new Properties();
			try {
				databasePropery.load(inputStreamDatabase);
				dbUrl = databasePropery.getProperty("dbUrl");
				dbUser = databasePropery.getProperty("dbUser");
				dbPasswd = databasePropery.getProperty("dbPasswd");
				Class.forName("com.mysql.cj.jdbc.Driver");
				connection = DriverManager.getConnection(dbUrl, dbUser, dbPasswd);
			} catch (final IOException | SQLException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * thread-safe를 보장하기 위한 클래스
	 * 
	 * @author 문기선
	 *
	 */
	private static class LazyHolder {
		private static final TodoDao INSTANCE = new TodoDao();
	}

	public static TodoDao getInstance() {
		return LazyHolder.INSTANCE;
	}

	/**
	 * DB에 저장되어 있는 모든 데이터 반환
	 * 
	 * @return DB에 있는 모든 데이터
	 */
	public List<TodoDto> getTodos() {
		List<TodoDto> todoList = new ArrayList<>();
		String sql = "SELECT * FROM todo ORDER BY sequence";

		try (PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {

				TodoDto todoDto = new TodoDto(rs);
				todoList.add(todoDto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return todoList;
	}

	/**
	 * 데이터 한건 추가
	 * 
	 * @return 데이터 추가 성공시 1
	 */
	public int addTodo(TodoDto todoDto) {
		int insertCount = 0;
		String sql = "INSERT INTO todo(title, name, sequence) VALUES(?, ?, ?)";

		try (PreparedStatement ps = connection.prepareStatement(sql)) {

			ps.setString(1, todoDto.getTitle());
			ps.setString(2, todoDto.getName());
			ps.setInt(3, todoDto.getSequence());

			insertCount = ps.executeUpdate();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return insertCount;
	}

	/**
	 * 한건의 데이터 type을 수정
	 * 
	 * @return 데이터 수정 성공시 1
	 */
	public int updateTodo(Long id, String type) {
		int updateCount = 0;

		String sql = "UPDATE todo SET type = ? WHERE id = ?";
		try (PreparedStatement ps = connection.prepareStatement(sql)) {
			if(type.equals("TODO")) {
				type = "DOING";
			}else {
				type = "DONE";
			}
			ps.setString(1, type);
			ps.setLong(2, id);

			updateCount = ps.executeUpdate();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return updateCount;
	}

}
