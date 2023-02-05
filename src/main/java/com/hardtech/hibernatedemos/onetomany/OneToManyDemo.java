package com.hardtech.hibernatedemos.onetomany;


import com.hardtech.hibernatedemos.entities.Student;
import com.hardtech.hibernatedemos.onetomany.entities.Course;
import com.hardtech.hibernatedemos.onetomany.entities.Instructor;
import com.hardtech.hibernatedemos.onetomany.entities.InstructorDetail;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@Slf4j(topic = "OneToManyDemo")
public class OneToManyDemo {
    public static void main(String[] args) {
        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        // create session
        try (factory) {
            Session session = factory.getCurrentSession();

            //start a transaction
            session.beginTransaction();

            //get a course
            Long id = 1L;
            Course course = session.get(Course.class, id);

            //delete a course
            log.info("Deleting course: {}", course);
            session.delete(course);

            //commit transaction
            session.getTransaction().commit();

            log.info("Done!");

        }
    }

}
