package com.hardtech.hibernatedemos;

import com.hardtech.hibernatedemos.entities.Student;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@Slf4j(topic = "Read Student Demo")
public class ReadStudentDemo {
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
            log.info("Creating new student object...");
            Student student = new Student("AGOSSOU", "Celine", "agossouceline@gmail.com");

            // start a transaction
            session.beginTransaction();

            // save the student object
            log.info("Saving the student...");
            System.out.println(student);
            session.save(student);

            // commit transaction
            session.getTransaction().commit();

            //Find out the student's id: primary key
            log.info("Saved student. Generated id: {}", student.getId());

            //now get a new student based on the id: primary key
            session = factory.getCurrentSession();
            session.beginTransaction();

            //retrieve student based on the id: primary key
            log.info("Getting student with id: {}", student.getId());
            Student theStudent = session.get(Student.class, student.getId());

            log.info("Get complete: {}", theStudent);

            //commit the transaction
            session.getTransaction().commit();

            log.info("Done!");
        }

    }
}
