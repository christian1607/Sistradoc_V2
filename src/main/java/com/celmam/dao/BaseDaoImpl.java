package com.celmam.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("baseDao")
public class BaseDaoImpl<K, T> implements BaseDao<K, T> {

    final Logger logger = LoggerFactory.getLogger(BaseDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void insertTransaccion(T obj) {
        Session session = this.getSession();
        session.persist(obj);
    }

    @Override
    public void updateT(T obj) {
        Session session = this.getSession();
        session.update(obj);
    }

    @Override
    public void saveOrUpdate(Object obj) {
        Session session = this.getSession();
        session.saveOrUpdate(obj);
        session.flush();
    }

    @Override
    public void insertOrUpdate(T obj) {
        Session session = this.getSession();
        session.merge(obj);
        session.flush();
    }

    @Override
    public void insert(T obj) {
        Session session = this.getSession();
        session.persist(obj);
        session.flush();
    }

    @Override
    public void update(T obj) {
        Session session = this.getSession();
        session.update(obj);
        session.flush();
    }

    @Override
    public T findByKey(K key, Class<T> type) throws IllegalArgumentException {
        Session session = this.getSession();
        return session.find(type, key);
    }

    @Override
    public void delete(T obj) {
        Session session = this.getSession();
        session.delete(obj);
        session.flush();
    }

    @Override
    public void deleteQuery(String namedQueryName, Map<String, Object> parameters) {
        getSession().clear();
        Query q = getSession().createNamedQuery(namedQueryName);
        for (Map.Entry<String, Object> paramName : parameters.entrySet()) {
            q.setParameter(paramName.getKey(), paramName.getValue());
        }
        q.executeUpdate();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> findByNamedQuery(String namedQueryName) throws IllegalStateException {
        Query q = getSession().createNamedQuery(namedQueryName);
        return q.getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public T findByNamedQueryObject(String namedQueryName) {
        Query q = getSession().createNamedQuery(namedQueryName);
        try {
            return (T) q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> findByNamedQuery(String namedQueryName, Map<String, Object> parameters)
            throws IllegalStateException {
        Query q = getSession().createNamedQuery(namedQueryName);
        for (Map.Entry<String, Object> paramName : parameters.entrySet()) {
            q.setParameter(paramName.getKey(), paramName.getValue());
        }
        return q.getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> findByNamedQuery(String namedQueryName, int resultLimit) throws IllegalStateException {
        Query q = getSession().createNamedQuery(namedQueryName);
        q.setMaxResults(resultLimit);
        return q.getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> findByNamedQuery(String namedQueryName, Map<String, Object> parameters, int resultLimit)
            throws IllegalStateException {
        Query q = getSession().createNamedQuery(namedQueryName);
        for (Map.Entry<String, Object> paramName : parameters.entrySet()) {
            q.setParameter(paramName.getKey(), paramName.getValue());
        }
        q.setMaxResults(resultLimit);
        return q.getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public T findByNamedQuery(String namedQueryName, String parametro, Object value, int resultLimit) {
        Query q = getSession().createNamedQuery(namedQueryName);
        q.setParameter(parametro, value);
        q.setMaxResults(1);
        try {
            return (T) (q.getSingleResult());
        } catch (NoResultException e) {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public T findByObject(String namedQueryName, String parametro, Object value) throws IllegalArgumentException {
        Query q = getSession().createNamedQuery(namedQueryName);
        q.setParameter(parametro, value);
        try {
            return (T) q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public T findByObjectQuery(String query, String parametro, Object value) {
        Query q = getSession().createQuery(query);
        q.setParameter(parametro, value);
        q.setMaxResults(1);
        try {
            return (T) (q.getSingleResult());
        } catch (NoResultException e) {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public List<T> findByObjectNamedQuery(String namedQueryName, String parametro, Object value) {
        Query q = getSession().createNamedQuery(namedQueryName);
        q.setParameter(parametro, value);
        q.setMaxResults(1);
        return (q.getResultList());
    }

    @SuppressWarnings("unchecked")
    @Override
    public T findByObjectValues(String namedQueryName, Map<String, Object> parameters) {
        Query q = getSession().createNamedQuery(namedQueryName);
        for (Map.Entry<String, Object> paramName : parameters.entrySet()) {
            q.setParameter(paramName.getKey(), paramName.getValue());
        }
        try {
            return (T) (q.getSingleResult());
        } catch (NoResultException e) {
            return null;
        }
    }
    
    
    @SuppressWarnings("unchecked")
    @Override
    public T findByObjectValuesFirtResult(String namedQueryName, Map<String, Object> parameters) {
        Query q = getSession().createNamedQuery(namedQueryName);
        for (Map.Entry<String, Object> paramName : parameters.entrySet()) {
            q.setParameter(paramName.getKey(), paramName.getValue());
        }
        q.setMaxResults(1);
        try {
            return (T) (q.getSingleResult());
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public Object callFunction(String function) {
        return (Object) getSession().createNativeQuery("SELECT " + function + " FROM DUAL").getSingleResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> findByNamedQuery(String namedQueryName, String parametro, Object value) {
        Query q = getSession().createNamedQuery(namedQueryName);
        q.setParameter(parametro, value);
        return q.getResultList();
    }

    @Override
    public Object[] callQuery(String query, Map<Integer, Object> parameters) {
        Query q = getSession().createNativeQuery(query);
        for (Map.Entry<Integer, Object> paramName : parameters.entrySet()) {
            q.setParameter(paramName.getKey(), paramName.getValue());
        }
        try {
            return (Object[]) q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public BigDecimal callQueryBigDecimal(String query, Map<Integer, Object> parameters) {
        Query q = getSession().createNativeQuery(query);
        for (Map.Entry<Integer, Object> paramName : parameters.entrySet()) {
            q.setParameter(paramName.getKey(), paramName.getValue());
        }
        try {
            return (BigDecimal) q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Object[]> callQueryList(String query, Map<Integer, Object> parameters) {
        Query q = getSession().createNativeQuery(query);
        for (Map.Entry<Integer, Object> paramName : parameters.entrySet()) {
            q.setParameter(paramName.getKey(), paramName.getValue());
        }
        return q.getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<String> findByNamedQuerybyString(String namedQueryName, Map<String, Object> parameters) {
        Query q = getSession().createNamedQuery(namedQueryName);
        for (Map.Entry<String, Object> paramName : parameters.entrySet()) {
            q.setParameter(paramName.getKey(), paramName.getValue());
        }
        return q.getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<String> findByNamedQuerybyString(String namedQueryName, Map<String, Object> parameters, int resultLimit)
            throws IllegalStateException {
        Query q = getSession().createNamedQuery(namedQueryName);
        for (Map.Entry<String, Object> paramName : parameters.entrySet()) {
            q.setParameter(paramName.getKey(), paramName.getValue());
        }
        q.setMaxResults(resultLimit);
        return q.getResultList();
    }

    @SuppressWarnings("hiding")
    @Override
    public <T> void delete(T obj, String id) {
        Session session = this.getSession();
        session.load(obj, id);
        session.delete(obj);
        session.flush();
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public void callStoreProcedure(String query, Map<String, Object> parameters) {
        Session session = this.getSession();
        Query q = session.createNativeQuery(query);
        for (Map.Entry<String, Object> paramName : parameters.entrySet()) {
            q.setParameter(paramName.getKey(), paramName.getValue());
        }
        q.executeUpdate();
    }
}
