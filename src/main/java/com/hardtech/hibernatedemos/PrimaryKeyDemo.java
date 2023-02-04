package com.hardtech.hibernatedemos;

import com.hardtech.hibernatedemos.entities.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class PrimaryKeyDemo {
    public static void main(String[] args) {
        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        // create session
        try (factory) {
            Session session = factory.getCurrentSession();
            // create a student object
            System.out.println("Creating new student object...");
            Student student1 = new Student("TESSI", "Hardson", "hardsontessi2@gmail.com");
            Student student2 = new Student("TESSI", "Modeste", "hardsontessi2@gmail.com");
            Student student3 = new Student("JOHN", "Doe", "johndoe@gmail.com");


            // start a transaction
            session.beginTransaction();

            // save the student object
            System.out.println("Saving the student...");
            session.save(student1);
            session.save(student2);
            session.save(student3);

            // commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");
        }
    }
}
