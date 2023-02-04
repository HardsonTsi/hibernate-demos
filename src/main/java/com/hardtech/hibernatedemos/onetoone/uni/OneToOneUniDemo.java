package com.hardtech.hibernatedemos.onetoone.uni;

import com.hardtech.hibernatedemos.onetoone.uni.entities.Instructor;
import com.hardtech.hibernatedemos.onetoone.uni.entities.InstructorDetail;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@Slf4j(topic = "OneToOneUniDemo")
public class OneToOneUniDemo {
    public static void main(String[] args) {
        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        // create session
        try (factory) {
            Session session = factory.getCurrentSession();

            //create the objects
            Instructor instructor = new Instructor("TESSI", "Hardson", "hardsontessi2@gmail.com");

            InstructorDetail instructorDetail = new InstructorDetail("Hardtech", "Tech!");

            //associate the objects
            instructor.setInstructorDetail(instructorDetail);

            //start a transaction
            session.beginTransaction();

            //save the instructor
            log.info("Saving instructor: {}", instructor);
            session.save(instructor);

            //commit transaction
            session.getTransaction().commit();

            log.info("Done!");

        }
    }

}
