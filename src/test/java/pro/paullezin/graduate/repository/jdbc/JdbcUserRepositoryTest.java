package pro.paullezin.graduate.repository.jdbc;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pro.paullezin.graduate.model.User;
import pro.paullezin.graduate.util.exception.NotFoundException;
import pro.paullezin.graduate.web.SecurityUtil;
import pro.paullezin.graduate.web.user.AdminRestController;

@ContextConfiguration(locations = {"classpath:spring/spring-app.xml", "classpath:spring/spring-db.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class JdbcUserRepositoryTest {

    @Autowired
    private AdminRestController controller;

    @Test
    public void save() {
        User user = controller.getByMail("user1@paullezin.pro");
        int userId = user.getId();
        user.setName("New Name");
        controller.update(user, userId);
        Assert.assertEquals("New Name", controller.get(userId).getName());
    }

    @Test
    public void delete() {
        Assert.assertEquals(3, controller.getAll().size());
        User user = controller.getByMail("user2@paullezin.pro");
        controller.delete(user.getId());
        Assert.assertEquals(2, controller.getAll().size());
    }

    @Test
    public void get() {
        int userId = SecurityUtil.authUserId();
        controller.get(userId);
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound() throws Exception {
        int userId = SecurityUtil.authUserId() - 100;
        controller.get(userId);
    }

    @Test
    public void getByEmail() {
        controller.getByMail("user1@paullezin.pro");
    }

    @Test(expected = NotFoundException.class)
    public void getByEmailNotFound() throws Exception {
        controller.getByMail("user100@paullezin.pro");
    }

    @Test
    public void getAll() {
        controller.getAll();
    }
}