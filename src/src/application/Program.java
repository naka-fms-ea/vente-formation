package application;

import java.util.List;

import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.CourseDao;
import model.entities.Course;

public class Program {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);

		CourseDao courseDao = DaoFactory.createCourseDao();
		
		System.out.println("\n=== TEST 1: Course findByAll ====");

		List<Course> listCourse = courseDao.findAll();
		
		for (Course obj : listCourse) {
			System.out.println(obj);
		}
		
		
		System.out.println("\n=== TEST 2: Course findAllKeyWord(String word) ====");
		
		System.out.println("Entrer un mot clé : ");
		String keyWord = sc.next();
		List<Course> listCourseKeyWord = courseDao.findAllKeyWord(keyWord);
		
		if(!listCourseKeyWord.isEmpty()) {
			for (Course obj : listCourseKeyWord) {
				System.out.println(obj);
			}
		} else {
			System.out.println("Il n'y a pas de résultat.");
		}
		
		System.out.println("\n=== TEST 3: Course findAllType(String type) ====");
		
		System.out.println("Entrer un type : ");
		String type = sc.next();
		List<Course> listCourseType = courseDao.findAllType(type);
		
		if(!listCourseType.isEmpty()) {
			for (Course obj : listCourseType) {
				System.out.println(obj);
			}
		} else {
			System.out.println("Il n'y a pas de résultat.");
		}
		
		sc.close();
		
	}

}
