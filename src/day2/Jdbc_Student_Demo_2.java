package day2;

import java.util.ArrayList;

public class Jdbc_Student_Demo_2 {
	static int flag;

	public static void main(String[] args) {
		StudentDao stuDao = new StudentDao();
		ArrayList<Student> students = stuDao.pageQuery(10, 1);
		for (Student stu : students) {
			System.out.println(stu);
		}
	}
}
