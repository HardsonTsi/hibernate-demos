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

            //create a course
            Course course = new Course("Network");

            //add some reviews
            course.addReview(new Review("Great course"));
            course.addReview(new Review("Good program"));
            course.addReview(new Review("Good job !"));

            //save the course... and leverage the cascade all !
            log.info("Saving  the course: {}", course);
            log.info("Reviews: {}", course.getReviews());

            //start a transaction
            session.beginTransaction();

            session.save(course);

            //commit transaction
            session.getTransaction().commit();

            log.info("Done!");

        }
    }

}
