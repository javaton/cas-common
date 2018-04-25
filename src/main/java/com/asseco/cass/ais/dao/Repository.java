package com.asseco.cass.ais.dao;

import java.util.List;
import java.util.Map;

public interface Repository {

    public <T> T merge(T entity);

    public void refresh(Object entity);

    public void remove(Object entity);

    public void persist(Object entity);

    public void flush();

    public <T> T findById(Class<T> entityClass, Object id);

    public <T> List<T> findByParameters(String queryString, Object... values);

    public <T> List<T> findByNamedParams(String queryString, Map<String, ?> params);

    public<T> List<T> executeQuery(String queryString);

}
