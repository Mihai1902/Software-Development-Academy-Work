package model.service;

import model.dao.SessionFactorySingleton;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class GenericService<T> {
    private SessionFactory sessionFactory;

    public GenericService(){
        SessionFactorySingleton sessionFactorySingleton = SessionFactorySingleton.getInstance();
        sessionFactory = sessionFactorySingleton.getSessionFactory();
    }

    public void add(T object){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(object);
        transaction.commit();

        if(session != null){
            session.close();
        }
    }

    public void update(T object){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(object);
        transaction.commit();

        if(session != null){
            session.close();
        }
    }

    public void delete(T object){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(object);
        transaction.commit();

        if(session != null){
            session.close();
        }
    }

    public List<T> getAll(T object){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from " + object.getClass().getName(),object.getClass());
        List<T> results = query.getResultList();
        transaction.commit();

        if(session != null){
            session.close();
        }
        return results;
    }

    public T findByiD(T object, int id){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        object = (T) session.find(object.getClass(), id);
        transaction.commit();

        if(session != null){
            session.close();
        }

        return object;
    }
}
