package com.hardtech.hibernatedemos.onetoone.uni;

import com.hardtech.hibernatedemos.onetoone.uni.entities.Instructor;
import com.hardtech.hibernatedemos.onetoone.uni.entities.InstructorDetail;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Objects;

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

            //start a transaction
            session.beginTransaction();

            //get instructor by primary key: id = 1
            Long id = 1L;
            Instructor instructor = session.get(Instructor.class, id);

            log.info("Found instructor: {}", instructor);

            //delete the instructor
            if (Objects.nonNull(instructor)) {
                log.info("Deleting: {}", instructor);
                session.delete(instructor);
            }

            //commit transaction
            session.getTransaction().commit();

            log.info("Done!");

        }
    }

}
