package day4;

/**
 * 防止SQL注解
 */
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import jdbc.oracle.util.JDBC;

public class StudentDao implements Dao<Student> {
	@Override
	public int add(Student student) {
		String sql = "insert into student(id,name,age,sex) values(?,?,?,?)";
		try {
			PreparedStatement prestatement = JDBC.getPreparedStatement(sql);
			prestatement.setInt(1, student.getId());
			prestatement.setString(2, student.getName());
			prestatement.setInt(3, student.getAge());
			prestatement.setString(4, student.getSex());
			int flag = prestatement.executeUpdate();
			return flag;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		} finally {
			JDBC.close();
		}
	}

	@Override
	public int delete(int id) {
		String sql = "delete student where id = ?";
		try {
			PreparedStatement prestatement = JDBC.getPreparedStatement(sql);
			prestatement.setInt(1, id);
			int flag = prestatement.executeUpdate();
			return flag;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		} finally {
			JDBC.close();
		}
	}

	@Override
	public int update(Student student) {
		String sql = "update student set name=?,age=?,sex=? where id=?";
		try {
			PreparedStatement prestatement = JDBC.getPreparedStatement(sql);
			prestatement.setString(1, student.getName());
			prestatement.setInt(2, student.getAge());
			prestatement.setString(3, student.getSex());
			prestatement.setInt(4, student.getId());
			int flag = prestatement.executeUpdate();
			return flag;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		} finally {
			JDBC.close();
		}
	}

	@Override
	public Student find(int id) {
		Student student = new Student();
		String sql = "select id,name,age,sex from student where id=?";
		try {
			PreparedStatement prestatement = JDBC.getPreparedStatement(sql);
			prestatement.setInt(1, id);
			ResultSet result = prestatement.executeQuery();
			if (result.next()) {
				student.setId(result.getInt("id"));
				student.setName(result.getString("name"));
				student.setAge(result.getInt("age"));
				student.setSex(result.getString("sex"));
			}
			return student;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			JDBC.close();
		}
	}

	@Override
	public ArrayList<Student> query() {
		ArrayList<Student> students = new ArrayList<Student>();
		String sql = "select id,name,age,sex from student order by id asc";
		try {
			PreparedStatement prestatement = JDBC.getPreparedStatement(sql);
			ResultSet resultSet = prestatement.executeQuery();
			while (resultSet.next()) {
				Student student = new Student();
				student.setId(resultSet.getInt("id"));
				student.setName(resultSet.getString("name"));
				student.setAge(resultSet.getInt("age"));
				student.setSex(resultSet.getString("sex"));
				students.add(student);
			}
			return students;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			JDBC.close();
		}
	}

	@Override
	public ArrayList<Student> findMore(Student student) {
		ArrayList<Student> students = new ArrayList<Student>();
		StringBuilder sql = new StringBuilder("select id,name,age,sex from student where 1=1");
		if (student != null) {
			String name = student.getName();
			int age = student.getAge();
			String sex = student.getSex();
			if (name != null && !"".equals(name)) {
				sql.append(" and name='" + name + "'");
			}
			if (age > 0) {
				sql.append(" and age=" + age + "");
			}
			if (sex != null && !"".equals(sex)) {
				sql.append(" and sex='" + sex + "'");
			}
		}
		Statement statement = JDBC.getStatement();
		try {
			ResultSet result = statement.executeQuery(sql.toString());
			while (result.next()) {
				Student stu = new Student();
				stu.setId(result.getInt("id"));
				stu.setName(result.getString("name"));
				stu.setAge(result.getInt("age"));
				stu.setSex(result.getString("sex"));
				students.add(stu);
			}
			return students;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			JDBC.close();
		}
	}

	@Override
	public ArrayList<Student> pageQuery(int count, int page) {
		ResultSet result = new Student().paging(count, page);
		ArrayList<Student> students = new ArrayList<Student>();
		try {
			while (result.next()) {
				Student stu = new Student();
				stu.setId(result.getInt(1));
				stu.setName(result.getString(2));
				stu.setAge(result.getInt(3));
				stu.setSex(result.getString(4));
				students.add(stu);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return students;
	}

}
