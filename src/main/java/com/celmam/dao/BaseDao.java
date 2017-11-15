package com.celmam.dao;

import java.util.List;
import java.util.Map;

public interface BaseDao<K, T> {

	void insertTransaccion(T obj);

	public void updateT(T obj);

	public void saveOrUpdate(Object obj);

	public void insertOrUpdate(T obj);

	void insert(T obj);

	void update(T obj);

	public T findByKey(K key, Class<T> type);

	void delete(T obj);

	public void deleteQuery(String namedQueryName, Map<String, Object> parameters);

	List<T> findByNamedQuery(String namedQueryName);
	
	T findByNamedQueryObject(String namedQueryName);

	List<T> findByNamedQuery(String namedQueryName, Map<String, Object> parameters);

	List<T> findByNamedQuery(String namedQueryName, int resultLimit);

	List<T> findByNamedQuery(String namedQueryName, Map<String, Object> parameters, int resultLimit);
	
	T findByNamedQuery(String namedQueryName, String parametro, Object value, int resultLimit);

	T findByObject(String namedQueryName, String parametro, Object value);

	T findByObjectQuery(String query, String parametro, Object value);

	T findByObjectValues(String namedQueryName, Map<String, Object> parameters);
        
        T findByObjectValuesFirtResult(String namedQueryName, Map<String, Object> parameters);

	Object callFunction(String function);
	
	void callStoreProcedure(String namedQuery, Map<String, Object> parameters);

	List<T> findByNamedQuery(String namedQueryName, String parametro, Object value);

	Object[] callQuery(String query, Map<Integer, Object> parameters);

	List<Object[]> callQueryList(String query, Map<Integer, Object> parameters);

	List<String> findByNamedQuerybyString(String namedQueryName, Map<String, Object> parameters);
	
	@SuppressWarnings("hiding")
	<T> void delete(T obj, String id);

}