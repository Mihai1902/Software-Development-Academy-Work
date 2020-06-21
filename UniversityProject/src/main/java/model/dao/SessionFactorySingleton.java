package model.dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.util.Properties;

public class SessionFactorySingleton {
    private static SessionFactorySingleton sessionFactorySingleton;
    private SessionFactory sessionFactory;

    private SessionFactorySingleton(){
        Configuration configuration = new Configuration();
        Properties properties = new Properties();

        properties.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
        properties.put(Environment.URL,"jdbc:mysql://");
        properties.put(Environment.USER,"");
        properties.put(Environment.PASS,"");
        properties.put(Environment.DIALECT,"org.hibernate.dialect.MySQL5Dialect");
        properties.put(Environment.SHOW_SQL,"true");
        properties.put(Environment.HBM2DDL_AUTO,"update");

        configuration.setProperties(properties);
        sessionFactory = configuration.buildSessionFactory();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static SessionFactorySingleton getInstance(){
        if(sessionFactorySingleton == null){
            sessionFactorySingleton = new SessionFactorySingleton();
        }
        return sessionFactorySingleton;
    }
}
