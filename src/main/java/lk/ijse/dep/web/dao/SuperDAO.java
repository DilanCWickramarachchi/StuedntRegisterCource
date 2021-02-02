package lk.ijse.dep.web.dao;

import javax.persistence.EntityManager;

/**
 * @author : Dilan C. Wickramarachchi <dilancwickramarachchi@gmail.com>
 * @since : 2021-02-01
 **/
public interface SuperDAO {
    void setEntityManager(EntityManager em);
}
