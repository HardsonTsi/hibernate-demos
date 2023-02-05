package com.hardtech.hibernatedemos.onetomany;


import com.hardtech.hibernatedemos.entities.Student;
import com.hardtech.hibernatedemos.onetomany.entities.Course;
import com.hardtech.hibernatedemos.onetomany.entities.Instructor;
import com.hardtech.hibernatedemos.onetomany.entities.InstructorDetail;
import com.hardtech.hibernatedemos.onetomany.entities.Review;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@Slf4j(topic = "OneToManyUni")
public class OneToManyUniDemo {
    public static void main(String[] args) {
        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        // create session
        try (factory) {
            Session session = factory.getCurrentSession();

            //start a transaction
            session.beginTransaction();

            //get the course
            Long id = 3L;
            Course course = session.get(Course.class, id);

            //print the course
                log.info("Course: {}", course);

            //print the course reviews
            log.info("Reviews: {}", course.getReviews());

            //delete the course
            session.delete(course);

            //commit transaction
            session.getTransaction().commit();

            log.info("Done!");

        }
    }

}
