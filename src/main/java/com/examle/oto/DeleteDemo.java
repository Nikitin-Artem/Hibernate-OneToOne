package com.examle.oto;

import com.examle.oto.model.Instructor;
import com.examle.oto.model.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteDemo {
    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

            int id = 1;

            Instructor tempInstructor = session.get(Instructor.class, id);

            System.out.println("Found instructor:" + tempInstructor);

            if(tempInstructor != null){
                System.out.println("Deleting: " + tempInstructor);

                // Note: will ALSO delete associated "details" object because of CascadeType.ALL
                session.delete(tempInstructor);
             }
            session.getTransaction().commit();

            System.out.println("Done!");
        } finally {
            sessionFactory.close();
        }
    }
}
