package lk.ijse.dep.web.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * @author : Dilan C. Wickramarachchi <dilancwickramarachchi@gmail.com>
 * @since : 2021-02-01
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class RegisterPK implements Serializable {
    @Column(name = "student_id")
    private String studentId;
    @Column(name = "course_code")
    private String courseCode;

}
