package lk.ijse.dep.web.business;

import lk.ijse.dep.web.business.custom.impl.CourseBOImpl;
import lk.ijse.dep.web.business.custom.impl.RegisterBOImpl;
import lk.ijse.dep.web.business.custom.impl.StudentBOImpl;

/**
 * @author : Dilan C. Wickramarachchi <dilancwickramarachchi@gmail.com>
 * @since : 2021-02-01
 **/
public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {

    }

    public static BOFactory getInstance() {
        return (boFactory != null) ? boFactory : (boFactory = new BOFactory());
    }

    public <T extends SuperBO> T getBO(BOTypes boType) {
        switch (boType) {
            case COURSE:
                return (T) new CourseBOImpl();
            case REGISTER:
                return (T) new RegisterBOImpl();
            case STUDENT:
                return (T) new StudentBOImpl();
            default:
                return null;
        }
    }
}
