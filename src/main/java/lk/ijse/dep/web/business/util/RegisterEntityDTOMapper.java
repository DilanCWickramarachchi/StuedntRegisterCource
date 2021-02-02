package lk.ijse.dep.web.business.util;

import lk.ijse.dep.web.dao.DAOFactory;
import lk.ijse.dep.web.dao.DAOType;
import lk.ijse.dep.web.dao.custom.StudentDAO;
import lk.ijse.dep.web.dto.RegisterDTO;
import lk.ijse.dep.web.entity.Register;
import lk.ijse.dep.web.entity.Student;
import lk.ijse.dep.web.util.JPAUtil;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import javax.persistence.EntityManager;
import java.sql.Date;

/**
 * @author : Dilan C. Wickramarachchi <dilancwickramarachchi@gmail.com>
 * @since : 2021-02-02
 **/
public interface RegisterEntityDTOMapper {
    RegisterEntityDTOMapper instance = Mappers.getMapper(RegisterEntityDTOMapper.class);

    @Mapping(source = "orderId", target = "id")
    @Mapping(source = ".", target = "date")
    @Mapping(source = ".", target = "customer")
    Register getOrder(RegisterDTO dto);

    default Date toDate(RegisterDTO dto){
        return Date.valueOf(dto.getRegisterDate());
    }

    default Student getStudent(RegisterDTO dto){
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try{
            StudentDAO dao = DAOFactory.getInstance().getDAO(DAOType.STUDENT);
            dao.setEntityManager(em);
            return dao.get(dto.getStudentId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            em.close();
        }
    }

    @Mapping(source = ".", target = "orderDetailPK", qualifiedByName = "pk")
    OrderDetail getOrderDetail(OrderDetailDTO dto);

    @Named("pk")
    default OrderDetailPK toOrderDetailPK(OrderDetailDTO dto){
        return new OrderDetailPK(dto.getOrderId(), dto.getItemCode());
    }
}
