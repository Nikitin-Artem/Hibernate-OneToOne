package com.examle.oto;

import com.examle.oto.model.Instructor;
import com.examle.oto.model.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteInstructorDetailDemo {
    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

            int id = 4;
            InstructorDetail tempInstructorDetail = session.get(InstructorDetail.class, id);
            System.out.println("tempInstructorDetail: " + tempInstructorDetail);

            System.out.println("the associated instructor: " + tempInstructorDetail.getInstructor());

            System.out.println("Deleting tempInstructorDetail: " + tempInstructorDetail);

            tempInstructorDetail.getInstructor().setInstructorDetail(null);

            session.delete(tempInstructorDetail);

            session.getTransaction().commit();

            System.out.println("Done!");
        } finally {
            sessionFactory.close();
        }
    }
}
