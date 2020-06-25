package model.dao;

import model.dto.*;
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
        properties.put(Environment.URL,"jdbc:mysql://localhost:3306/university");
        properties.put(Environment.USER,"root"); //TODO de completat cu user
        properties.put(Environment.PASS,"iwasbornin921"); //TODO de completat cu parola
        properties.put(Environment.DIALECT,"org.hibernate.dialect.MySQL5Dialect");
        properties.put(Environment.SHOW_SQL,"false");
        properties.put(Environment.HBM2DDL_AUTO,"update");
        properties.put(Environment.ENABLE_LAZY_LOAD_NO_TRANS, "true");

        configuration.setProperties(properties);

        configuration.addAnnotatedClass(Student.class);
        configuration.addAnnotatedClass(Teacher.class);
        configuration.addAnnotatedClass(Course.class);
        configuration.addAnnotatedClass(Group.class);
        configuration.addAnnotatedClass(SubGroup.class);
        configuration.addAnnotatedClass(Classroom.class);
        configuration.addAnnotatedClass(Timetable.class);
        configuration.addAnnotatedClass(User.class);

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
