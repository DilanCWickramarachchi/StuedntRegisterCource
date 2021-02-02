package lk.ijse.dep.web.business;

import javax.persistence.EntityManager;

/**
 * @author : Dilan C. Wickramarachchi <dilancwickramarachchi@gmail.com>
 * @since : 2021-02-01
 **/
public interface SuperBO {
    void setEntityManager(EntityManager em);
}
