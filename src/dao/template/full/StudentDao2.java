package dao.template.full;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.java.oracle.base.BaseDao;
import jdbc.oracle.util.JDBC;

public class StudentDao2 extends BaseDao<Student> implements Dao<Student> {
	@Override
	public int add(Student stu) {
		String sql = "insert into student values(?,?,?,?)";
		try {
			return this.add(sql, stu.getId(), stu.getName(), stu.getAge(), stu.getSex());
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		} finally {
			JDBC.close();
			JDBC.closeConnection();
		}
	}

	@Override
	public int delete(int id) {
		String sql = "delete student where id = ?";
		try {
			return this.delete(id, sql);
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		} finally {
			JDBC.close();
			JDBC.closeConnection();
		}
	}

	@Override
	public int update(Student stu) {
		String sql = "update student set name=?,age=?,sex=? where id=?";
		try {
			return this.update(sql, stu.getName(), stu.getAge(), stu.getSex(), stu.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC.close();
			JDBC.closeConnection();
		}
		return 0;
	}

	@Override
	public Student find(int id) {
		ResultSet result;
		String sql = "select * from student where id=?";
		Student student = new Student();
		try {
			result = this.find(id, sql);
			while (result.next()) {
				student.setId(result.getInt(1));
				student.setName(result.getString(2));
				student.setAge(result.getInt(3));
				student.setSex(result.getString(4));
			}
			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC.close();
			JDBC.closeConnection();
		}
		return student;
	}

	@Override
	public List<Student> findMore(Student student) {
		StringBuilder sql = new StringBuilder("select id,name,age,sex from student where 1=1 ");
		ArrayList<Object> list = new ArrayList<Object>();
		if (student != null) {
			String name = student.getName();
			int age = student.getAge();
			String sex = student.getSex();
			if (name != null && !"".equals(name)) {
				sql.append(" and name=?");
				list.add(name);
			}
			if (age > 0) {
				sql.append(" and age=?");
				list.add(age);
			}
			if (sex != null && !"".equals(sex)) {
				sql.append(" and sex=?");
				list.add(sex);
			}
		}
		Object[] objs = new Object[list.size()];
		for (int i = 0; i < list.size(); i++) {
			objs[i] = list.get(i);
		}
		ArrayList<Student> students = new ArrayList<Student>();
		try {
			ResultSet result = this.findMore(sql.toString(), objs);
			while (result.next()) {
				Student stu = new Student();
				stu.setId(result.getInt(1));
				stu.setName(result.getString(2));
				stu.setAge(result.getInt(3));
				stu.setSex(result.getString(4));
				students.add(stu);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC.close();
			JDBC.closeConnection();
		}
		return students;
	}

	@Override
	public List<Student> query() {
		String sql = "select id,name,age,sex from student order by id asc";
		ArrayList<Student> students = new ArrayList<Student>();
		try {
			ResultSet result = this.Query(sql);
			while (result.next()) {
				Student stu = new Student();
				stu.setId(result.getInt(1));
				stu.setName(result.getString(2));
				stu.setAge(result.getInt(3));
				stu.setSex(result.getString(4));
				students.add(stu);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC.close();
			JDBC.closeConnection();
		}
		return students;
	}

	@Override
	public List<Student> pageQuery(int count, int page) {
		ArrayList<Student> students = new ArrayList<Student>();
		String sql = "select id,name,age,sex from (select id,name,age,sex, rownum rn from student where id<? order by id asc) where rn between ? and ?";
		try {
			ResultSet result = this.pageQuery(sql, count, page, 12);
			while (result.next()) {
				Student stu = new Student();
				stu.setId(result.getInt(1));
				stu.setName(result.getString(2));
				stu.setAge(result.getInt(3));
				stu.setSex(result.getString(4));
				students.add(stu);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC.close();
			JDBC.closeConnection();
		}
		return students;
	}
}