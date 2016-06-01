package day1;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import jdbc.oracle.util.JDBC;

public class StudentDao implements Dao<Student> {
	@Override
	public int add(Student student) {
		String sql = "insert into student(id,name,age,sex) values(" + student.getId() + ",'" + student.getName() + "',"
				+ student.getAge() + ",'" + student.getSex() + "')";
		try {
			Statement statement = JDBC.getStatement();
			int flag = statement.executeUpdate(sql);
			statement.close();
			return flag;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		} finally {
			JDBC.close();
		}
	}

	@Override
	public int delete(Student student) {
		String sql = "delete student where id=" + student.getId();
		try {
			Statement statement = JDBC.getStatement();
			int flag = statement.executeUpdate(sql);
			statement.close();
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
		String sql = "update student set " + "name='" + student.getName() + "',age=" + student.getAge() + ",sex='"
				+ student.getSex() + "' where id=" + student.getId();
		try {
			Statement statement = JDBC.getStatement();
			int flag = statement.executeUpdate(sql);
			statement.close();
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
		String sql = "select id,name,age,sex from student where id=" + id;
		Statement statement = JDBC.getStatement();
		try {
			ResultSet result = statement.executeQuery(sql);
			if (result.next()) {
				student.setId(result.getInt("id"));
				student.setName(result.getString("name"));
				student.setAge(result.getInt("age"));
				student.setSex(result.getString("sex"));
			}
			statement.close();
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
		String sql = "select id,name,age,sex from student order by id";
		try {
			Statement statement = JDBC.getStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				Student student = new Student();
				student.setId(resultSet.getInt("id"));
				student.setName(resultSet.getString("name"));
				student.setAge(resultSet.getInt("age"));
				student.setSex(resultSet.getString("sex"));
				students.add(student);
			}
			statement.close();
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
			statement.close();
			return students;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			JDBC.close();
		}
	}
}
