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


@Slf4j
public class ManyToManyDemo {

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

        try (factory; Session session = factory.getCurrentSession()) {

            // start a transaction
            session.beginTransaction();

            //get student from db
            Long id = 1L;
            Student student = session.get(Student.class, id);
            log.info("Loaded student: {}", student);
            log.info("Courses: {}", student.getCourses());

            //create more courses
            Course course1 = new Course("French");
            Course course2 = new Course("English");

            //add student to courses
            log.info("Saving the courses...");
            course1.addStudent(student);
            course2.addStudent(student);

            //save the courses
            session.save(course1);
            session.save(course2);

            // commit transaction
            session.getTransaction().commit();

            log.info("Done!");
        }

        // add clean up code

    }

}





