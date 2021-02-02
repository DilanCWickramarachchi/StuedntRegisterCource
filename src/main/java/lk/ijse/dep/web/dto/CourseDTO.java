package lk.ijse.dep.web.dto;

import lk.ijse.dep.web.entity.Audience;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author : Dilan C. Wickramarachchi <dilancwickramarachchi@gmail.com>
 * @since : 2021-02-01
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseDTO implements Serializable {
    private String code;
    private String description;
    private String duration;
    private Audience audience;
}
