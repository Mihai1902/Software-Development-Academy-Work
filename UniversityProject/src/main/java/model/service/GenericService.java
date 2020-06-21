package model.service;

import model.dao.IGeneric;
import model.dao.SessionFactorySingleton;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class GenericService<T> implements IGeneric<T> {
    private SessionFactory sessionFactory;

    public GenericService(){
        SessionFactorySingleton sessionFactorySingleton = SessionFactorySingleton.getInstance();
        sessionFactory = sessionFactorySingleton.getSessionFactory();
    }

    @Override
    public void add(T object){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(object);
        transaction.commit();

        if(session != null){
            session.close();
        }
    }

    @Override
    public void update(T object){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(object);
        transaction.commit();

        if(session != null){
            session.close();
        }
    }
    @Override
    public void delete(T object){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(object);
        transaction.commit();

        if(session != null){
            session.close();
        }
    }
    @Override
    public List<T> getAll(){
        T object = null;
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
    @Override
    public T findById(int id){
        T object = null;
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
