package pro.paullezin.graduate.repository.jdbc;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pro.paullezin.graduate.model.User;
import pro.paullezin.graduate.util.exception.NotFoundException;
import pro.paullezin.graduate.web.SecurityUtil;
import pro.paullezin.graduate.web.user.AdminRestController;

public class JdbcUserRepositoryTest{
    private static ConfigurableApplicationContext appCtx;
    private static AdminRestController controller;

    @BeforeClass
    public static void beforeClass(){
        appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml", "spring/spring-db.xml");
        controller = appCtx.getBean(AdminRestController.class);
    }

    @AfterClass
    public static void afterClass(){
        appCtx.close();
    }

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
    public void getByEmailNotFound() throws Exception{
        controller.getByMail("user100@paullezin.pro");
    }

    @Test
    public void getAll() {
        controller.getAll();
    }
}