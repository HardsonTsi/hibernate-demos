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

            // create a course
            Course tempCourse = new Course("Pacman - How To Score One Million Points");

            // save the course
            log.info("Saving the course ...");
            session.save(tempCourse);
            log.info("Saved the course: {}", tempCourse);

            // create the students
            Student tempStudent1 = new Student("Hard", "Tessi", "hardsontessi2@gmail.com");
            Student tempStudent2 = new Student("Modeste", "Tessi", "modestetessi.code.com");

            // add students to the course
            tempCourse.addStudent(tempStudent1);
            tempCourse.addStudent(tempStudent2);

            // save the students
            log.info("Saving students ...");
            session.save(tempStudent1);
            session.save(tempStudent2);
            log.info("Saved students: {}", tempCourse.getStudents());

            // commit transaction
            session.getTransaction().commit();

            log.info("Done!");
        }

        // add clean up code

    }

}





