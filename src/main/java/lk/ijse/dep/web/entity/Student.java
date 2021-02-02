package lk.ijse.dep.web.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author : Dilan C. Wickramarachchi <dilancwickramarachchi@gmail.com>
 * @since : 2021-02-01
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "student")
public class Student implements SuperEntity {
    @Id
    private String id;
    private String name;
    private String contact;
    private String dob;
    private String gender;
    @Embedded
    private Address address;

    public Student(String id, String name, String dob, String contact, String gender, String address) {
    }
}
