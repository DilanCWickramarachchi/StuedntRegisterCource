package lk.ijse.dep.web.api;

import lk.ijse.dep.web.business.BOFactory;
import lk.ijse.dep.web.business.BOTypes;
import lk.ijse.dep.web.business.custom.RegisterBO;
import lk.ijse.dep.web.dto.RegisterDTO;
import lk.ijse.dep.web.exception.HttpResponseException;
import lk.ijse.dep.web.exception.ResponseExceptionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;

/**
 * @author : Dilan C. Wickramarachchi <dilancwickramarachchi@gmail.com>
 * @since : 2021-02-01
 **/
@WebServlet(urlPatterns = "/api/v1/registers/*")
public class RegisterServlet extends HttpServlet {
    final Logger logger = LoggerFactory.getLogger(RegisterServlet.class);
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            super.service(req, resp);
        } catch (Throwable t) {
            ResponseExceptionUtil.handle(t, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Jsonb jsonb = JsonbBuilder.create();
        final EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
        EntityManager em = emf.createEntityManager();
        try {
            RegisterDTO dto = jsonb.fromJson(req.getReader(), RegisterDTO.class);

            if (dto.getStudentId() == null || dto.getStudentId().trim().isEmpty() || dto.getCourseCode() == null || dto.getCourseCode().isEmpty() || dto.getRegisterDate() == null || dto.getRegisterDate().isEmpty() || dto.getRegisterFee() == null || dto.getRegisterFee().isEmpty()) {
                throw new HttpResponseException(400, "Invalid register details", null);
            }

            RegisterBO registerBO = BOFactory.getInstance().getBO(BOTypes.REGISTER);
            registerBO.setEntityManager(em);
            registerBO.registerCourseStudent(dto);
            resp.setStatus(HttpServletResponse.SC_CREATED);
        } catch (SQLIntegrityConstraintViolationException exp) {
            throw new HttpResponseException(400, "Duplicate entry", exp);
        } catch (JsonbException exp) {
            exp.printStackTrace();
            throw new HttpResponseException(400, "Failed to read the JSON", exp);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        } finally {
            em.close();
        }

    }
}
