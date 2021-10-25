package com.varun.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.varun.hibernate.demo.entity.Student;

public class DeleteStudentDemo {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
								 .addAnnotatedClass(Student.class)
								 .buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {
			int studentId =2;		
			session.beginTransaction();
			
			// retrieving the object
			System.out.println("Id of student: " + studentId);
			
			//session = factory.getCurrentSession();
			
			//session.beginTransaction();
			
			System.out.println("Getting the student with id: " + studentId);
			
			Student student = session.get(Student.class, studentId);
			
			System.out.println("Deleting student: " + student);
			
			session.delete(student);
			
			session.getTransaction().commit();
			
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			System.out.println("Deleting specific student");
			
			session.createQuery("delete from Student where id='1'").executeUpdate();
			
			session.getTransaction().commit();
			
			System.out.println("Done");
		}
		finally {
			factory.close();
		}
	}
}
