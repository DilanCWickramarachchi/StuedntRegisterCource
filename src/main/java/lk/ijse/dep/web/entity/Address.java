package lk.ijse.dep.web.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

/**
 * @author : Dilan C. Wickramarachchi <dilancwickramarachchi@gmail.com>
 * @since : 2021-02-01
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Address {
    private String no;
    private String addressLine1;
    private String addressLine2;
    private String city;

}
