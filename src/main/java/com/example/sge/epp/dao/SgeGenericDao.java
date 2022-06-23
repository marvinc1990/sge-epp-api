/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.sge.epp.dao;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author MARVIN
 * @param <T>
 * @param <Id>
 */
public interface SgeGenericDao<T, Id extends Serializable> {

    void insert(T t);

    void update(T t);

    void delete(T t);

    T get(Id id);

    List<T> getAll();

}
