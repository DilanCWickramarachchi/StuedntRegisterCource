package lk.ijse.dep.web.business.custom;

import lk.ijse.dep.web.business.SuperBO;
import lk.ijse.dep.web.dto.RegisterDTO;

/**
 * @author : Dilan C. Wickramarachchi <dilancwickramarachchi@gmail.com>
 * @since : 2021-02-01
 **/
public interface RegisterBO extends SuperBO {
    public void registerCourse(RegisterDTO registerDTO) throws Exception;

    public void registerStudent(RegisterDTO registerDTO) throws Exception;
}
