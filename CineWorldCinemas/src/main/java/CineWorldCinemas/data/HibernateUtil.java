/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CineWorldCinemas.data;

import CineWorldCinemas.logic.*;
import java.util.Properties;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

/**
 *
 * @author joela
 */
public class HibernateUtil {

    private static SessionFactory sessionFactory;

    /**
     * Class utils for connection to the database using Hibernate
     *
     * @return sessionFactory
     */
    private HibernateUtil() {
    }

    public static synchronized SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                Properties settings = new Properties();

                settings.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
                settings.put(Environment.URL, "jdbc:mysql://localhost:3306/cinema?useSSL=false");
                settings.put(Environment.USER, "root");
                settings.put(Environment.PASS, "root");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5InnoDBDialect");
                settings.put(Environment.SHOW_SQL, "true");
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                settings.put(Environment.HBM2DDL_AUTO, "create");

                // IMPORTANT HBM2DDL_AUTO +++++++++++++
                // validate: validate the schema, makes no changes to the database.
                // update: update the schema.
                // create: creates the schema, destroying previous data.
                // create-drop: drop the schema when the SessionFactory is closed explicitly, typically when the application is stopped.
                // none: does nothing with the schema, makes no changes to the database
                configuration.addAnnotatedClass(Administrator.class);
                configuration.addAnnotatedClass(Auditorium.class);
                configuration.addAnnotatedClass(Movie.class);
                configuration.addAnnotatedClass(Screening.class);
                configuration.addAnnotatedClass(Seat.class);
                configuration.addAnnotatedClass(SeatReserved.class);
                configuration.addAnnotatedClass(Ticket.class);
                configuration.addAnnotatedClass(User.class);
                configuration.setProperties(settings);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
                //sessionFactory.openSession();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}
