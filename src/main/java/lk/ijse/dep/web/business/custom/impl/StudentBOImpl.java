package lk.ijse.dep.web.business.custom.impl;

import lk.ijse.dep.web.business.custom.StudentBO;
import lk.ijse.dep.web.dao.DAOFactory;
import lk.ijse.dep.web.dao.DAOType;
import lk.ijse.dep.web.dao.custom.StudentDAO;
import lk.ijse.dep.web.dto.StudentDTO;
import lk.ijse.dep.web.entity.Course;
import lk.ijse.dep.web.entity.Student;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * @author : Dilan C. Wickramarachchi <dilancwickramarachchi@gmail.com>
 * @since : 2021-02-01
 **/
public class StudentBOImpl implements StudentBO {
    private final StudentDAO studentDAO;
    private EntityManager em;

    public StudentBOImpl() {
        studentDAO = DAOFactory.getInstance().getDAO(DAOType.STUDENT);
    }

    @Override
    public void setEntityManager(EntityManager em) {
        this.em = em;
        studentDAO.setEntityManager(em);
    }

    @Override
    public void saveStudent(StudentDTO dto) throws Exception {
        try{
            em.getTransaction().begin();
            studentDAO.save(new Student(dto.getId(), dto.getName(), dto.getDob(), dto.getContact(), dto.getAddress(), dto.getGender()));
            em.getTransaction().commit();
        }catch (Throwable t){
            em.getTransaction().rollback();
            throw t;
        }
    }

    @Override
    public void updateStudent(StudentDTO dto) throws Exception {
        try{
            em.getTransaction().begin();
            studentDAO.update(new Student(dto.getId(), dto.getName(), dto.getDob(), dto.getContact(), dto.getAddress(), dto.getGender()));
            em.getTransaction().commit();
        }catch (Throwable t){
            em.getTransaction().rollback();
            throw t;
        }
    }

    @Override
    public void deleteStudent(String studentId) throws Exception {
        try{
            em.getTransaction().begin();
            studentDAO.delete(studentId);
            em.getTransaction().commit();
        }catch (Throwable t){
            em.getTransaction().rollback();
            throw t;
        }
    }

    @Override
    public List<StudentDTO> findAllStudents() throws Exception {
        em.getTransaction().begin();
    }


}
