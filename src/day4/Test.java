package day4;

import java.util.ArrayList;

public class Test {

	public static void main(String[] args) {
		ArrayList<Student> students = new ArrayList<Student>();
		StudentDao2 studao = new StudentDao2();
		Student stu = studao.find(13);
		Student stu1 = new Student(12, "田华", 16, "男");
		students = (ArrayList<Student>) studao.pageQuery(6, 2);
		for (Student student : students) {
			System.out.println(student);
		}
	}

}
