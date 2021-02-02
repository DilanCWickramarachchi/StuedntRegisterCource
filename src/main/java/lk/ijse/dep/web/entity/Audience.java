package lk.ijse.dep.web.entity;

import java.lang.annotation.Target;

/**
 * @author : Dilan C. Wickramarachchi <dilancwickramarachchi@gmail.com>
 * @since : 2021-02-01
 **/

public enum Audience {
    AFTER_AL(0), UNDERGRADUATE(1), GRADUATE(2);

    private final int hierarchy;
    private Audience(final Integer hierarchy) {
        this.hierarchy = hierarchy;
    }
    public int getHierarchy() {
        return hierarchy;
    }

}
