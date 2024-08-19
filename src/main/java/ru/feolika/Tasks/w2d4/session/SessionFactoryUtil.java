package ru.feolika.Tasks.w2d4.session;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * Класс для инициализации сессии
 */
public class SessionFactoryUtil {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            Configuration configuration = new Configuration();
            configuration.configure();
            sessionFactory = configuration.buildSessionFactory();
        }
        return sessionFactory;
    }
}
