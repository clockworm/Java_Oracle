package day1;

import java.util.ArrayList;

public class Jdbc_Student_Demo_1 {
	static int flag;

	public static void main(String[] args) {
		StudentDao stuDao = new StudentDao();
		Student student = new Student();
		// student.setAge(20);
		// student.setName("夏丽");
		// student.setSex("女");
		ArrayList<Student> students = stuDao.findMore(student);
		for (Student stu : students) {
			System.out.println(stu);
		}

	}
}
