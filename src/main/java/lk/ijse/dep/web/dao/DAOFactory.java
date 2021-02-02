package lk.ijse.dep.web.dao;

import lk.ijse.dep.web.dao.custom.impl.CourseDAOImpl;
import lk.ijse.dep.web.dao.custom.impl.QueryDAOImpl;
import lk.ijse.dep.web.dao.custom.impl.RegisterDAOImpl;
import lk.ijse.dep.web.dao.custom.impl.StudentDAOImpl;

/**
 * @author : Dilan C. Wickramarachchi <dilancwickramarachchi@gmail.com>
 * @since : 2021-02-01
 **/
public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory(){

    }

    public static DAOFactory getInstance(){
        return (daoFactory != null)? daoFactory: (daoFactory = new DAOFactory());
    }

    public <T extends SuperDAO> T getDAO(DAOType daoType){
        switch (daoType){
            case STUDENT:
                return (T) new StudentDAOImpl();
            case REGISTER:
                return (T) new RegisterDAOImpl();
            case COURSE:
                return (T) new CourseDAOImpl();
            case QUERY:
                return (T) new QueryDAOImpl();
            default:
                return null;
        }
    }
}
