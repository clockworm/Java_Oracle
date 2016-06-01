package day4;

import java.util.List;

public class StudentServiceImp implements StudentService<Student> {
	private StudentDao stuDao = new StudentDao();

	@Override
	public int add(Student student) {
		return stuDao.add(student);
	}

	@Override
	public Student query(int id) {
		return stuDao.find(id);
	}

	@Override
	public int update(Student student) {
		return stuDao.update(student);
	}

	@Override
	public int delete(int id) {
		return stuDao.delete(id);
	}

	@Override
	public List<Student> pageQuery(int count, int page) {
		return stuDao.pageQuery(count, page);
	}

}
