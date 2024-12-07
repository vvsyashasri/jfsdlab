package com.klef.jfsd.exam;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Projections;

import java.util.List;

public class ClientDemo {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        // Insert records
        Project p1 = new Project();
        p1.setProjectName("AI Research");
        p1.setDuration(12);
        p1.setBudget(500000);
        p1.setTeamLead("Dr. Smith");

        Project p2 = new Project();
        p2.setProjectName("Web Development");
        p2.setDuration(6);
        p2.setBudget(300000);
        p2.setTeamLead("Ms. Jane");

        session.save(p1);
        session.save(p2);

        // Aggregate functions on the Budget field
        Criteria criteria = session.createCriteria(Project.class);

        // Count
        criteria.setProjection(Projections.rowCount());
        System.out.println("Count of Projects: " + criteria.uniqueResult());

        // Max Budget
        criteria.setProjection(Projections.max("budget"));
        System.out.println("Maximum Budget: " + criteria.uniqueResult());

        // Min Budget
        criteria.setProjection(Projections.min("budget"));
        System.out.println("Minimum Budget: " + criteria.uniqueResult());

        // Sum Budget
        criteria.setProjection(Projections.sum("budget"));
        System.out.println("Total Budget: " + criteria.uniqueResult());

        // Average Budget
        criteria.setProjection(Projections.avg("budget"));
        System.out.println("Average Budget: " + criteria.uniqueResult());

        transaction.commit();
        session.close();
        sessionFactory.close();
    }
}
