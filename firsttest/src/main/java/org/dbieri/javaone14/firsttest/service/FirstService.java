package org.dbieri.javaone14.firsttest.service;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.dbieri.javaone14.firsttest.entity.FirstEntity;

/**
 * Created by dbieri on 22.09.2014.
 */
@Stateless
public class FirstService {
    @PersistenceContext
    private EntityManager em;


    public FirstEntity createFirstEntity(FirstEntity entity) {
        em.persist(entity);
        return entity;
    }

}
