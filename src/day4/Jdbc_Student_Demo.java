package day4;

import java.util.ArrayList;

public class Jdbc_Student_Demo {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		StudentServiceImp studentService = new StudentServiceImp();
		DynamicProxyFactory proxy = new DynamicProxyFactory(studentService);
		StudentService<Student> proxyService = (StudentService<Student>) proxy.getProxy();
		ArrayList<Student> students = (ArrayList<Student>) proxyService.pageQuery(5, 2);
		for (Student student : students) {
			System.out.println(student);
		}
		// int i = proxyService.delete(new Student(12, "田华", 16, "男"));
		// System.out.println(i);
	}
}
