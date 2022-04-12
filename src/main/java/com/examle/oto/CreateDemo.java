package com.examle.oto;

import com.examle.oto.model.Instructor;
import com.examle.oto.model.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateDemo {
    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();

        try {
            Instructor tempInstructor = new Instructor("Chad", "Darby", "darby@gmail.com");
            InstructorDetail tempInstructorDetail = new InstructorDetail("Spring course", "Codding");

            tempInstructor.setInstructorDetail(tempInstructorDetail);

            session.beginTransaction();

            // Note: this will ALSO save the details object because of CascadeType.ALL
            System.out.println("Saving instructor: " + tempInstructor);
            session.save(tempInstructor);

            session.getTransaction().commit();

            System.out.println("Done!");
        } finally {
            sessionFactory.close();
        }
    }
}
