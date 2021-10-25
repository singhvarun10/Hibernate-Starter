package com.varun.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.varun.hibernate.demo.entity.Student;

public class ReadStudentDemo {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
								 .addAnnotatedClass(Student.class)
								 .buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {
			
			System.out.println("Creating new student object");
			Student tempStudent = new Student("Daffy","Duck","daffy@gmail.com");
			
			session.beginTransaction();
			
			System.out.println("Saving the student...");
			
			session.save(tempStudent);
			
			session.getTransaction().commit();
			
			// retrieving the object
			System.out.println("Id of student: " + tempStudent.getId());
			
			session = factory.getCurrentSession();
			
			session.beginTransaction();
			
			System.out.println("Getting the student with id: " + tempStudent.getId());
			
			Student student = session.get(Student.class, tempStudent.getId());
			
			System.out.println("Get complete: " + student);
			
			session.getTransaction().commit();
			
			System.out.println("Done");
		}
		finally {
			factory.close();
		}
	}
}
