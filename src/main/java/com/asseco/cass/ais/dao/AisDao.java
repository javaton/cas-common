package com.asseco.cass.ais.dao;

public abstract class AisDao {
    private Repository repository;

    public Repository getRepository() {
        return repository;
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
    }
}
