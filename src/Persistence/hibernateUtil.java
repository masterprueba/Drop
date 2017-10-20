/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistence;

import Controller.Login_Controller;
import javax.swing.JOptionPane;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author Andres
 */
public class hibernateUtil {

    private static final SessionFactory sessionFactory;

    static {
        try {
            // Create the SessionFactory from standard (hibernate.cfg.xml) 
            // config file.
            Configuration configuration = new Configuration().configure("Persistence/hibernate.cfg.xml");
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().
                    applySettings(configuration.getProperties());
            sessionFactory = configuration.buildSessionFactory(builder.build());
        } catch (Throwable ex) {
            // Log the exception. 
            System.err.println("Initial SessionFactory creation failed." + ex);
            Login_Controller.continuar = false;
            JOptionPane.showMessageDialog(null, "No hay conexion con la base de datos!\n -Verifique la conexion con el servidor e intente nuevamente", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSessionFactory() {
        if (sessionFactory.getCurrentSession().isOpen()) {
            sessionFactory.openSession();
        }

        return sessionFactory.getCurrentSession();
    }
}
