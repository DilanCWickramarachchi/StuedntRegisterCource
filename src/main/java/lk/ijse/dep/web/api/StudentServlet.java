package lk.ijse.dep.web.api;

import lk.ijse.dep.web.business.BOFactory;
import lk.ijse.dep.web.business.BOTypes;
import lk.ijse.dep.web.business.custom.StudentBO;
import lk.ijse.dep.web.dto.StudentDTO;
import lk.ijse.dep.web.exception.HttpResponseException;
import lk.ijse.dep.web.exception.ResponseExceptionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.Servlet;
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
@WebServlet(urlPatterns = "/api/v1/students/*")
public class StudentServlet extends HttpServlet {
    final Logger logger = LoggerFactory.getLogger(StudentServlet.class);

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            super.service(req, resp);
        } catch (Throwable t) {
            ResponseExceptionUtil.handle(t, resp);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
        EntityManager em = emf.createEntityManager();

        try{

            if (req.getPathInfo() == null || req.getPathInfo().replace("/", "").trim().isEmpty()) {
                throw new HttpResponseException(400, "Invalid student id", null);
            }

            String id = req.getPathInfo().replace("/", "");

            StudentBO studentBO = BOFactory.getInstance().getBO(BOTypes.STUDENT);
            studentBO.setEntityManager(em);
            studentBO.deleteStudent(id);
            resp.setStatus(HttpServletResponse.SC_NO_CONTENT);

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            em.close();
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        final EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
        EntityManager em = emf.createEntityManager();

        try{

            if (req.getPathInfo() == null || req.getPathInfo().replace("/", "").trim().isEmpty()) {
                throw new HttpResponseException(400, "Invalid student id", null);
            }

            String id = req.getPathInfo().replace("/", "");
            Jsonb jsonb = JsonbBuilder.create();
            StudentDTO dto = jsonb.fromJson(req.getReader(), StudentDTO.class);

            if (dto.getId() != null || dto.getName().trim().isEmpty() || dto.getAddress().trim().isEmpty() ||
                    dto.getContact().trim().isEmpty() || dto.getDob().trim().isEmpty() || dto.getGender().trim().isEmpty()) {
                throw new HttpResponseException(400, "Invalid details", null);
            }

            StudentBO studentBO = BOFactory.getInstance().getBO(BOTypes.STUDENT);
            studentBO.setEntityManager(em);
            studentBO.updateStudent(dto);
            resp.setStatus(HttpServletResponse.SC_NO_CONTENT);

        } catch (JsonbException exp) {
            throw new HttpResponseException(400, "Failed to read the JSON", exp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            em.close();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Jsonb jsonb = JsonbBuilder.create();

        final EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
        EntityManager em = emf.createEntityManager();

        try{
            resp.setContentType("application/json");
            StudentBO studentBO = BOFactory.getInstance().getBO(BOTypes.STUDENT);
            studentBO.setEntityManager(em);
            resp.getWriter().println(jsonb.toJson(studentBO.findAllStudents()));

        } catch (Throwable t) {
            ResponseExceptionUtil.handle(t, resp);
        } finally {
            em.close();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Jsonb jsonb = JsonbBuilder.create();
        final EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
        EntityManager em = emf.createEntityManager();

        try{
            StudentDTO dto = jsonb.fromJson(req.getReader(), StudentDTO.class);

            if (dto.getId() == null || dto.getId().trim().isEmpty() || dto.getName() == null || dto.getName().trim().isEmpty() || dto.getAddress() == null || dto.getAddress().trim().isEmpty()
            || dto.getGender() == null || dto.getGender().trim().isEmpty() || dto.getDob() == null || dto.getDob().trim().isEmpty() || dto.getContact() == null || dto.getContact().trim().isEmpty()) {
                throw new HttpResponseException(400, "Invalid customer details", null);
            }

            StudentBO studentBO = BOFactory.getInstance().getBO(BOTypes.STUDENT);
            studentBO.setEntityManager(em);
            studentBO.saveStudent(dto);
            resp.setStatus(HttpServletResponse.SC_CREATED);
            resp.setContentType("application/json");
            resp.getWriter().println(jsonb.toJson(dto));
        } catch (SQLIntegrityConstraintViolationException exp) {
            throw new HttpResponseException(400, "Duplicate entry", exp);
        } catch (JsonbException exp) {
            throw new HttpResponseException(400, "Failed to read the JSON", exp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            em.close();
        }

    }
}
