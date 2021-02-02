package lk.ijse.dep.web.business.custom;

import lk.ijse.dep.web.business.SuperBO;
import lk.ijse.dep.web.dto.CourseDTO;
import lk.ijse.dep.web.dto.StudentDTO;

import java.util.List;

/**
 * @author : Dilan C. Wickramarachchi <dilancwickramarachchi@gmail.com>
 * @since : 2021-02-01
 **/
public interface CourseBO extends SuperBO {
    public void saveCourse(CourseDTO courseDTO) throws Exception;

    public void updateCourse(CourseDTO courseDTO) throws Exception;

    public void deleteCourse(String courseCode) throws Exception;

    public List<CourseDTO> findAllCourse() throws Exception;


}
