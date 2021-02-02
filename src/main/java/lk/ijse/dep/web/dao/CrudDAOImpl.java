package lk.ijse.dep.web.dao;

import lk.ijse.dep.web.entity.SuperEntity;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * @author : Dilan C. Wickramarachchi <dilancwickramarachchi@gmail.com>
 * @since : 2021-02-01
 **/
public abstract class CrudDAOImpl<T extends SuperEntity, PK extends Serializable> implements CrudDAO<T, PK> {
    private EntityManager em;
    private Class<T> entityClassObj;

    public CrudDAOImpl() {
        entityClassObj =  (Class<T>)(((ParameterizedType)(this.getClass().getGenericSuperclass())).getActualTypeArguments()[0]);
    }

    @Override
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    protected EntityManager getEntityManager(){
        return em;
    }

    @Override
    public void save(T entity) throws Exception {
        em.persist(entity);
    }

    @Override
    public void update(T entity) throws Exception {
        em.merge(entity);
    }

    @Override
    public void delete(PK key) throws Exception {
        em.remove(em.getReference(entityClassObj, key));
    }

    @Override
    public T get(PK key) throws Exception {
        return em.find(entityClassObj, key);
    }

    @Override
    public List<T> getAll() throws Exception {
        return em.createQuery("SELECT e FROM " + entityClassObj.getName() + " e").getResultList();
    }
}
