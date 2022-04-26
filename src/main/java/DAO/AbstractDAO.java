package DAO;

import util.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Map;

public class AbstractDAO<T> {
    public static final EntityManager entityManger = JpaUtil.getEntityManager();

    @SuppressWarnings("deprecation")
    @Override
    protected void finalize() throws Throwable {
        entityManger.close();
        super.finalize();
    }

    public T findById(Class<T> clazz, Integer id) {
        return entityManger.find(clazz, id);
    }

    public List<T> findAll(Class<T> clazz, boolean existIsActive) {
        String entityName = clazz.getSimpleName();
        StringBuilder sql = new StringBuilder();
        sql.append("select o from ").append(entityName).append(" o");
        if (existIsActive) {
            sql.append(" where isActive = 1");
        }
        TypedQuery<T> query = entityManger.createQuery(sql.toString(), clazz);
        return query.getResultList();
    }

    public List<T> findAll(Class<T> clazz, boolean existIsActive, int pageNumber, int pageSize) {
        String entityName = clazz.getSimpleName();
        StringBuilder sql = new StringBuilder();
        sql.append("select o from ").append(entityName).append(" o");
        if (existIsActive) {
            sql.append(" where isActive = 1");
        }
        TypedQuery<T> query = entityManger.createQuery(sql.toString(), clazz);
        query.setFirstResult((pageNumber - 1) * pageSize);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }

    public T findOne(Class<T> clazz, String sql, Object... params) {
        TypedQuery<T> query = entityManger.createQuery(sql, clazz);
        for (int i = 0; i < params.length; i++) {
            query.setParameter(i, params[i]);
        }
        List<T> result = query.getResultList();
        if (result.isEmpty()) {
            return null;
        }
        return result.get(0);
    }

    public List<T> findMany(Class<T> clazz, String sql, Object... params) {
        TypedQuery<T> query = entityManger.createQuery(sql, clazz);
        for (int i = 0; i < params.length; i++) {
            query.setParameter(i, params[i]);
        }
        return query.getResultList();
    }


    @SuppressWarnings("unchecked")
    public List<Object[]> findManyByNativeQuery(String sql, Object... params) {
        Query query = entityManger.createNativeQuery(sql);
        for (int i = 0; i < params.length; i++) {
            query.setParameter(i, params[i]);
        }
        return query.getResultList();
    }

    public T create(T entity) {
        try {
            entityManger.getTransaction().begin();
            entityManger.persist(entity);
            entityManger.getTransaction().commit();
            System.out.println("Create success");
            return entity;
        } catch (Exception e) {
            entityManger.getTransaction().rollback();
            System.out.println("Cannot insert entity " + entity.getClass().getSimpleName());
            throw new RuntimeException(e);
        }
    }

    public T update(T entity) {
        try {
            entityManger.getTransaction().begin();
            entityManger.merge(entity);
            entityManger.getTransaction().commit();
            System.out.println("Update success");
            return entity;
        } catch (Exception e) {
            entityManger.getTransaction().rollback();
            System.out.println("Cannot Update entity " + entity.getClass().getSimpleName());
            throw new RuntimeException(e);
        }
    }

    public T delete(T entity) {
        try {
            entityManger.getTransaction().begin();
            entityManger.remove(entity);
            entityManger.getTransaction().commit();
            System.out.println("Delete success");
            return entity;
        } catch (Exception e) {
            entityManger.getTransaction().rollback();
            System.out.println("Cannot Delete entity " + entity.getClass().getSimpleName());
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public List<T> callStored(String nameStored, Map<String, Object> params) {
        StoredProcedureQuery query = entityManger.createNamedStoredProcedureQuery(nameStored);
        params.forEach((key, value) -> query.setParameter(key, value));
        return (List<T>) query.getResultList();
    }
}
