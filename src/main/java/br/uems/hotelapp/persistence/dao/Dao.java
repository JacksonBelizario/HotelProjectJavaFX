/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uems.hotelapp.persistence.dao;

import br.uems.hotelapp.persistence.ConnectionFactory;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Jackson
 * @param <T>
 */
public abstract class Dao<T> implements AbstractDao<T> {
    
    protected static EntityManager entityManager = ConnectionFactory.getEntityManager();

    protected Class<T> entityClass;

    @SuppressWarnings("unchecked")
    public Dao() {
        Type type = getClass().getGenericSuperclass();

        while (!(type instanceof ParameterizedType) || ((ParameterizedType) type).getRawType() != Dao.class) {
            if (type instanceof ParameterizedType) {
                type = ((Class<?>) ((ParameterizedType) type).getRawType()).getGenericSuperclass();
            } else {
                type = ((Class<?>) type).getGenericSuperclass();
            }
        }

        this.entityClass = (Class<T>) ((ParameterizedType) type).getActualTypeArguments()[0];
    }
    
     
    protected void executeInsideTransaction(Consumer<EntityManager> action) {
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            action.accept(entityManager);
            tx.commit(); 
        }
        catch (RuntimeException e) {
            tx.rollback();
            throw e;
        }
    }

    @Override
    public void save(T entity) {
        executeInsideTransaction(entityMan -> entityMan.persist(entity));
    }

    @Override
    public void delete(T entity) {
        executeInsideTransaction(entityMan -> entityMan.remove(entity));
    }

    @Override
    public void update(T entity) {
        executeInsideTransaction(entityMan -> entityMan.merge(entity));
    }
    
    List<T> getList(String queryName, Map<String, Object> params) {
      Query query = entityManager.createNamedQuery(queryName);
      if (params != null) {
          params.entrySet().forEach((paramEntry) -> {
              query.setParameter(paramEntry.getKey(), paramEntry.getValue());
          });
      }
      return query.getResultList();
    }

    public List<T> getList() {
        return entityManager.createQuery(String.format("SELECT e FROM %s e ORDER BY id", entityClass.getSimpleName()), entityClass).getResultList();
    }

    @Override
    public T find(Integer id) {
        return entityManager.find(entityClass, id);
    }
    
    @Override
    public List<T> getAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(entityClass);
        Root<T> rootEntry = cq.from(entityClass);
        CriteriaQuery<T> all = cq.select(rootEntry);
        TypedQuery<T> allQuery = entityManager.createQuery(all);
        return allQuery.getResultList();
    }

    @Override
    public long getCount() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        criteria.select(builder.count(criteria.from(entityClass)));
        TypedQuery<Long> query = entityManager.createQuery(criteria);

        return query.getSingleResult();
    }

    @Override
    public List<T> getList(String qlString, Object[] params) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<T> getList(String qlString, Object[] params, int[] range) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long getCount(String qlString, Object[] params) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
