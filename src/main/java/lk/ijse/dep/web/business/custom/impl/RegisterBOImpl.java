package lk.ijse.dep.web.business.custom.impl;

import lk.ijse.dep.web.business.custom.RegisterBO;
import lk.ijse.dep.web.dao.DAOFactory;
import lk.ijse.dep.web.dao.DAOType;
import lk.ijse.dep.web.dao.custom.CourseDAO;
import lk.ijse.dep.web.dao.custom.RegisterDAO;
import lk.ijse.dep.web.dto.RegisterDTO;

import javax.persistence.EntityManager;


/**
 * @author : Dilan C. Wickramarachchi <dilancwickramarachchi@gmail.com>
 * @since : 2021-02-01
 **/
public class RegisterBOImpl implements RegisterBO {

    private final RegisterDAO registerDAO;
    private final CourseDAO courseDAO;
    private EntityManager em;

    public RegisterBOImpl() {
        registerDAO = DAOFactory.getInstance().getDAO(DAOType.REGISTER);
        courseDAO = DAOFactory.getInstance().getDAO(DAOType.COURSE);
    }

    @Override
    public void setEntityManager(EntityManager em) {
        this.em = em;
//        courseDAO.setEntityManager(em);
//        registerDAO.setEntityManager(em);
    }

    @Override
    public void registerCourse(RegisterDTO registerDTO) throws Exception {

    }

    @Override
    public void registerStudent(RegisterDTO registerDTO) throws Exception {

    }
}
