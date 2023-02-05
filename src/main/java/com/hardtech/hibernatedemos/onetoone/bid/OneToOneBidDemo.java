package com.hardtech.hibernatedemos.onetoone.bid;

import com.hardtech.hibernatedemos.onetoone.bid.entities.Instructor;
import com.hardtech.hibernatedemos.onetoone.bid.entities.InstructorDetail;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@Slf4j(topic = "OneToOneBidDemo")
public class OneToOneBidDemo {
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

            //get the instructor detail object
            Long id = 1L;
            InstructorDetail instructorDetail = session.get(InstructorDetail.class, id);

            //print the instructor detail
            log.info("{}", instructorDetail);

            //print the associated instructor
            log.info("The associated instructor: {}", instructorDetail.getInstructor());

            //now let's delete the instructor detail
            log.info("Deleting instructorDetail: {}", instructorDetail);
            session.delete(instructorDetail);

            //commit transaction
            session.getTransaction().commit();

            log.info("Done!");

        }
    }

}
