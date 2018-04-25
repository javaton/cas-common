package com.asseco.cass.persist;

import com.asseco.cass.ais.domain.BaseEntity;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Query;

import org.apache.commons.lang.Validate;

import org.eclipse.persistence.annotations.FetchGroup;
import org.eclipse.persistence.config.QueryHints;




public abstract class EntityRepositoryImpl<T extends BaseEntity> extends BaseRepository<T> implements EntityRepository<T>{

    abstract protected Class<T> getEntityClass();


    public T findById(Long id) {
        Validate.notNull(id, "Entity identification (id) is null");
        return getEntityById(getEntityClass(), id);
    }


    public T  findByUuid(final String uuid){
        Validate.notNull(uuid, "UUID is null");
        String queryString = "select o from " +  getEntityClassName() + " o where o.uuid = ?1";
        Object[] params = new Object[1];
        params[0] = uuid;
        return (T)singleResultQuery(queryString,  params);
    }

    final protected String getEntityClassName() {
        return getEntityClass().getSimpleName();
    }


    public T store(final T entity) {
        return saveOrUpdate(entity);
    }


    public void remove(final T entity) {
        deleteObject(entity);
    }

    private T findById(Class<T> entityClass, Long id){
        if(id == null){
            return null;
        }
        T newObject = em.find(entityClass, id);
        em.close();
        return newObject;
    }

    private T saveOrUpdate(T entity){

        if(entity == null){
            return null;
        }
        if(entity.getId() == null){
            em.persist(entity);
        }else{
            entity = em.merge(entity);
        }
        //Iskomentarisano zbog kompleksnosti koju sa sobom povlaci Operacion klasa
        /*if (!(entity instanceof Operation)){
            em.flush();
        }*/

        em.close();
        return entity;

    }

    public void deleteObject(BaseEntity entity) {
        if(entity == null){
            return;
        }
        entity = getEntityById((Class<T>) entity.getClass(), entity.getId());
        em.remove(entity);
        em.flush();
        em.close();
    }
    protected T getEntityById(Class<T> objectClass, Serializable id) {
        //em.close();
//		em.clear();

        return em.find(objectClass, id);
    }
    protected  List<T> selectQuery(String queryString, Integer startPosition, Integer maxResult) {
        Query q = em.createQuery(queryString);
        if (startPosition != null) {
            q.setFirstResult(startPosition);
        }
        if (maxResult != null) {
            q.setMaxResults(maxResult);
        }
        em.close();
        return (List<T>)q.getResultList();
    }


    protected List<T> selectQuery(String queryString, Object[] params, Integer startPosition, Integer maxResult) {
        Query q = em.createQuery(queryString);
        if (startPosition != null) {
            q.setFirstResult(startPosition);
        }
        if (maxResult != null) {
            q.setMaxResults(maxResult);
        }

        for(int i=0; i < params.length; i++){
            q.setParameter(i+1, params[i]);
        }
        em.close();
        return (List<T>)q.getResultList();
    }
    public T singleResultQuery(String queryString) {
        Query q = em.createQuery(queryString);
        em.close();
        return (T)q.getSingleResult();
    }

    public T singleResultQuery(String queryString, Object[] params){
        Query q = em.createQuery(queryString);

        for(int i=0; i < params.length; i++){
            q.setParameter(i+1, params[i]);
        }
        Object result = null;

        try{
            result = q.getSingleResult();
        }catch (javax.persistence.NoResultException e) {
            logger.warn(e);
            return null;
        }
        em.close();
        return (T)result;
    }
    public T singleResultQuery(String selectClause,  String whereClause, List  params) {
        return (T)singleResultInternalQuery(selectClause, whereClause, params);
    }
    protected Object singleResultInternalQuery(String selectClause,  String whereClause, List  params) {
        StringBuffer queryText = new StringBuffer(selectClause);
        if (whereClause != null){
            queryText.append(" ").append(whereClause);
        }
        Query q = em.createQuery(queryText.toString());
        int i = 0;
        for (Object param : params) {
            q.setParameter(++i, param);
        }
        Object result = null;
        try{
            result = q.getSingleResult();
        }catch (javax.persistence.NoResultException e) {
            logger.warn(e);
            return null;
        }
        em.close();
        return result;
    }
    protected List<T> selectQuery(String selectClause, String whereClause, String orderClause, List params, Integer startPosition, Integer maxResult) {
        String fetchGroupName = null;
//		for (Annotation a : getEntityClass().getDeclaredAnnotations()) {
//			System.err.println("*************" + a.annotationType().getName());
//	}
        FetchGroup fetchGroup = getEntityClass().getAnnotation(FetchGroup.class);
        if (fetchGroup != null) {
            fetchGroupName = fetchGroup.name();
        }
        return selectQuery(fetchGroupName, selectClause, whereClause, orderClause, params, startPosition, maxResult);

    }	protected List<T> selectQuery(String selectClause, String whereClause, List params, Integer startPosition, Integer maxResult) {
        return selectQuery(selectClause, whereClause, null, params, startPosition, maxResult);
    }


    //	protected List<T> fetchQuery(String fetchGroupName, String selectClause, String whereClause, String orderClause, List params, Integer startPosition, Integer maxResult) {
//		StringBuffer queryText = new StringBuffer(selectClause);
//		if (whereClause != null){
//			queryText.append(" ").append(whereClause);
//		}
//		if (orderClause != null){
//			queryText.append(" ").append(orderClause);
//		}
////		System.err.println(queryText.toString());
//		Query q = em.createQuery(queryText.toString());
//		if (startPosition != null) {
//			q.setFirstResult(startPosition);
//		}
//		if (maxResult != null) {
//			q.setMaxResults(maxResult);
//		}
//		int i = 0;
//		for (Object param : params) {
//			q.setParameter(++i, param);
//		}
//		em.close();
//		return (List<T>)q.getResultList();
//	}
    protected List<T> selectQuery(String fetchGroupName, String selectClause, String whereClause, String orderClause, List params, Integer startPosition, Integer maxResult) {
        StringBuffer queryText = new StringBuffer(selectClause);
        if (whereClause != null){
            queryText.append(" ").append(whereClause);
        }
        if (orderClause != null){
            queryText.append(" ").append(orderClause);
        }
//		System.err.println(queryText.toString());
        Query q = em.createQuery(queryText.toString());
        if (fetchGroupName != null) {
//			System.err.println("*************hint");
            q.setHint(QueryHints.FETCH_GROUP_NAME, fetchGroupName);
        }
        if (startPosition != null) {
            q.setFirstResult(startPosition);
        }
        if (maxResult != null) {
            q.setMaxResults(maxResult);
        }
        int i = 0;
        for (Object param : params) {
            q.setParameter(++i, param);
        }
        em.close();
        return (List<T>)q.getResultList();
    }
}

