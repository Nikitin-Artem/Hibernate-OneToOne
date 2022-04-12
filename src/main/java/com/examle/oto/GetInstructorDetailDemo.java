package com.examle.oto;

import com.examle.oto.model.Instructor;
import com.examle.oto.model.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GetInstructorDetailDemo {
    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

            int id = 6;
            InstructorDetail tempInstructorDetail = session.get(InstructorDetail.class, id);
            System.out.println("tempInstructorDetail: " + tempInstructorDetail);

            System.out.println("the associated instructor: " + tempInstructorDetail.getInstructor());

            session.getTransaction().commit();

            System.out.println("Done!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }

}
