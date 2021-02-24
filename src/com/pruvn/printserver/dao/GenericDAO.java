package com.pruvn.printserver.dao;

import java.io.Serializable;
import java.util.List;
public interface GenericDAO <T, ID extends Serializable> {
    T findById(ID id);
    List<T> findAll();
    T save(T entity);
    T update(T entity);
    void delete(ID id);
    void delete(T entity);
    void refresh(T entity);
    void merge(T entity);
    void flush();
}
