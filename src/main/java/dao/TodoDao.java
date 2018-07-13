package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.TodoDto;

/**
 * pjt2 데이터베이스에 접근하기 위한 클래스
 * 
 * @author 문기선
 *
 */
public class TodoDao {
	private static String dburl = "jdbc:mysql://localhost:3306/pjt2?serverTimezone=Asia/Seoul&useSSL=false";
	private static String dbUser = Mysql.getDbuser();
	private static String dbpasswd = Mysql.getDbpasswd();

	/**
	 * type에 따른 데이터 리스트 반환
	 * 
	 * @return type에 따른 데이터 List
	 */
	public List<TodoDto> getTodoList(String inputType) {
		List<TodoDto> todoList = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
			String sql = "SELECT * FROM todo WHERE type= ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, inputType);
			rs = ps.executeQuery();

			while (rs.next()) {
				Long id = rs.getLong("id");
				String title = rs.getString("title");
				String name = rs.getString("name");
				int sequence = rs.getInt("sequence");
				String type = rs.getString("type");
				String regdate = rs.getString("regdate");
				TodoDto todoDto = new TodoDto();
				todoDto.setId(id);
				todoDto.setTitle(title);
				todoDto.setName(name);
				todoDto.setSequence(sequence);
				todoDto.setType(type);
				todoDto.setRegdate(regdate);
				todoList.add(todoDto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
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
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);

			String sql = "INSERT INTO todo(title, name, sequence) VALUES(?, ?, ?)";

			ps = conn.prepareStatement(sql);

			ps.setString(1, todoDto.getTitle());
			ps.setString(2, todoDto.getName());
			ps.setInt(3, todoDto.getSequence());

			insertCount = ps.executeUpdate();

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {

			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return insertCount;
	}

	/**
	 * 한건의 데이터 type을 todo->doing, doing->done으로 수정
	 * 
	 * @return 데이터 수정 성공시 1
	 */
	public int updateType(Long id, String type) {
		int updateCount = 0;

		Connection conn = null;
		PreparedStatement ps = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);

			String sql = "UPDATE todo SET type = ? WHERE id = ?";

			ps = conn.prepareStatement(sql);

			if (type.equalsIgnoreCase("TODO")) {
				type = "DOING";
			} else if (type.equalsIgnoreCase("DOING")) {
				type = "DONE";
			} else {
				type = null;
				System.out.println("ERROR");
				return 0;
			}
			ps.setString(1, type);
			ps.setLong(2, id);

			updateCount = ps.executeUpdate();

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {

			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return updateCount;
	}

}
