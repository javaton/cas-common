package com.asseco.cass.persist;

import javax.persistence.EntityManager;
import org.apache.log4j.Logger;

abstract class BaseRepository<T extends Object> {
    protected static Logger logger = Logger.getLogger(BaseRepository.class);

    protected EntityManager em;



//
//	public void directEJBQL(String ejbql) {
//		Query q = em.createQuery(ejbql);
//		q.executeUpdate();
//	}
//
//	public void directSQL(String sql) {
//		Query q = em.createNativeQuery(sql);
//		q.getResultList();
//	}



//	protected BaseEntity saveObject(BaseEntity entity) {
//		em.persist(entity);
//		em.close();
//		return entity;
//	}
//
//	protected void saveOrUpdateObject(BaseEntity entity) {
//		em.merge(entity);
//		em.close();
//	}






//	protected List selectRangeQuery(String queryString, int start, int end) {
//		Query q = em.createQuery(queryString);
//
//		q.setFirstResult(start);
//		q.setMaxResults(end-start);
//
//		return q.getResultList();
//	}
//
//	protected List selectRangeQuery(String queryString, Integer start, Integer maxResults) {
//		Query q = em.createQuery(queryString);
//
//		if(start != null){
//			q.setFirstResult(start);
//		}
//
//		if(maxResults != null){
//			q.setMaxResults(maxResults);
//		}
//
//		return q.getResultList();
//	}
//
//	public List selectRangeQuery(Query query, Integer start, Integer maxResults) {
//		if(query == null){
//			return null;
//		}
//
//		if(start != null){
//			query.setFirstResult(start);
//		}
//
//		if(maxResults != null){
//			query.setMaxResults(maxResults);
//		}
//
//		return query.getResultList();
//	}
//
//	public List<Object> selectRangeQuery(String queryString, Object[] params,
//			int start, int end) {
//		Query q = em.createQuery(queryString);
//
//		for(int i=0; i < params.length; i++){
//			q.setParameter(i+1, params[i]);
//		}
//
//		q.setFirstResult(start);
//		q.setMaxResults(end-start);
//
//		return q.getResultList();
//	}


//	public int updateQuery(String queryString) {
//		Query q = em.createQuery(queryString);
//
//		return q.executeUpdate();
//	}
//
//	public int updateQuery(String queryString, Object[] params) {
//		Query q = em.createQuery(queryString);
//
//		for(int i=0; i < params.length; i++){
//			q.setParameter(i+1, params[i]);
//		}
//
//		return q.executeUpdate();
//	}

//	public BaseEntity mergeObject(BaseEntity entity) {
//		return em.merge(entity);
//	}
//
//	public void refreshObject(BaseEntity entity) {
//		em.refresh(entity);
//	}

//	public void setRangeOnQuery(Query query, Integer firstResult, Integer maxResults){
//		if(query == null){
//			return;
//		}
//
//		if(firstResult != null){
//			query.setFirstResult(firstResult);
//		}
//		if(maxResults != null){
//			query.setMaxResults(maxResults);
//		}
//	}
//
//	public <T extends BaseEntity> void mergeLists(List<T> sourceList, List<T> destinationList){
//		if(sourceList == null || sourceList.isEmpty()){
//			if(destinationList != null && !destinationList.isEmpty()){
//				destinationList.clear();
//				return;
//			}
//		}
//
//		List<T> tempDestinationList =  new ArrayList<T>(destinationList);
//
//		for(BaseEntity elemFromDestinationList : tempDestinationList){
//			if(!sourceList.contains(elemFromDestinationList)){
//				destinationList.remove(elemFromDestinationList);
//			}
//		}
//
//		for(T elemFromSourceList : sourceList){
//			if(!destinationList.contains(elemFromSourceList)){
//				destinationList.add(elemFromSourceList);
//			}
//		}
//	}
}

