package lk.ijse.dep.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author : Dilan C. Wickramarachchi <dilancwickramarachchi@gmail.com>
 * @since : 2021-02-01
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO implements Serializable {
    private String id;
    private String name;
    private String contact;
    private String dob;
    private String gender;
    private String address;
}
