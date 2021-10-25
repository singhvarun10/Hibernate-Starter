package com.varun.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.varun.hibernate.demo.entity.Student;

public class QueryStudentDemo {
	
	public static void main(String[] args) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
								 .addAnnotatedClass(Student.class)
								 .buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();

			List<Student> theStudents = session.createQuery("from Student").list();
			
			for(Student tempStudent:theStudents) {
				System.out.println(tempStudent);
			}
			
			theStudents = session.createQuery("from Student s where s.lastName='Singh'").list();
			
			for(Student tempStudent:theStudents) {
				System.out.println(tempStudent);
			}
			session.getTransaction().commit();
			
			System.out.println("Done");
		}
		finally {
			factory.close();
		}
	}
}
