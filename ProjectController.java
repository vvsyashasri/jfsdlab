package com.klef.jfsd.exam;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Projections;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class ProjectController extends HttpServlet {
    private static SessionFactory sessionFactory;

    static {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        sessionFactory = configuration.buildSessionFactory();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String projectName = req.getParameter("projectName");
        int duration = Integer.parseInt(req.getParameter("duration"));
        double budget = Double.parseDouble(req.getParameter("budget"));
        String teamLead = req.getParameter("teamLead");

        Project project = new Project();
        project.setProjectName(projectName);
        project.setDuration(duration);
        project.setBudget(budget);
        project.setTeamLead(teamLead);

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(project);
        transaction.commit();
        session.close();

        resp.getWriter().println("Project added successfully!");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(Project.class);

        // Get aggregate data
        int count = ((Long) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
        double maxBudget = (Double) criteria.setProjection(Projections.max("budget")).uniqueResult();
        double minBudget = (Double) criteria.setProjection(Projections.min("budget")).uniqueResult();
        double totalBudget = (Double) criteria.setProjection(Projections.sum("budget")).uniqueResult();
        double avgBudget = (Double) criteria.setProjection(Projections.avg("budget")).uniqueResult();

        session.close();

        // Pass data to JSP
        req.setAttribute("count", count);
        req.setAttribute("maxBudget", maxBudget);
        req.setAttribute("minBudget", minBudget);
        req.setAttribute("totalBudget", totalBudget);
        req.setAttribute("avgBudget", avgBudget);
        RequestDispatcher dispatcher = req.getRequestDispatcher("view-aggregates.jsp");
        dispatcher.forward(req, resp);
    }
}
