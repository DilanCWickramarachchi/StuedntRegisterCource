package lk.ijse.dep.web.business.custom.impl;

import lk.ijse.dep.web.business.custom.CourseBO;
import lk.ijse.dep.web.dao.DAOFactory;
import lk.ijse.dep.web.dao.DAOType;
import lk.ijse.dep.web.dao.custom.CourseDAO;
import lk.ijse.dep.web.dto.CourseDTO;
import lk.ijse.dep.web.dto.StudentDTO;
import lk.ijse.dep.web.entity.Course;
import lk.ijse.dep.web.entity.Student;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : Dilan C. Wickramarachchi <dilancwickramarachchi@gmail.com>
 * @since : 2021-02-01
 **/
public class CourseBOImpl implements CourseBO {
    private final CourseDAO courseDAO;
    private EntityManager em;

    public CourseBOImpl() {
        courseDAO = DAOFactory.getInstance().getDAO(DAOType.COURSE);
    }

    @Override
    public void setEntityManager(EntityManager em) {
        this.em = em;
        courseDAO.setEntityManager(em);
    }

    @Override
    public void saveCourse(CourseDTO dto) throws Exception {
        try{
            em.getTransaction().begin();
            courseDAO.save(new Course(dto.getCode(), dto.getDescription(), dto.getDuration(), dto.getAudience()));
            em.getTransaction().commit();
        }catch (Throwable t){
            em.getTransaction().rollback();
            throw t;
        }
    }

    @Override
    public void updateCourse(CourseDTO dto) throws Exception {
        try{
            em.getTransaction().begin();
            courseDAO.update(new Course(dto.getCode(), dto.getDescription(), dto.getDuration(), dto.getAudience()));
            em.getTransaction().commit();
        }catch (Throwable t){
            em.getTransaction().rollback();
            throw t;
        }
    }

    @Override
    public void deleteCourse(String courseCode) throws Exception {
        try{
            em.getTransaction().begin();
            courseDAO.delete(courseCode);
            em.getTransaction().commit();
        }catch (Throwable t){
            em.getTransaction().rollback();
            throw t;
        }
    }

    @Override
    public List<CourseDTO> findAllCourse() throws Exception {
        try {
            em.getTransaction().begin();
            List<CourseDTO> collect = courseDAO.getAll().stream().
                    map(c -> new CourseDTO(c.getCode(), c.getDescription(), c.getDuration(), c.getAudience())).collect(Collectors.toList());
            em.getTransaction().commit();
            return collect;
        } catch (Throwable t) {
            em.getTransaction().rollback();
            throw t;
        }
    }


}
