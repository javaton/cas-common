package com.asseco.cass.persist;

import com.asseco.cass.ais.domain.BaseEntity;

public interface EntityRepository <T extends BaseEntity>{

    public static String FETCH_GROUP_NAME = "find_by_criteria";

    T findById(final Long id);
    T findByUuid(final String uuid);
    T store(final T entity);
    void remove(final T entity);
}

