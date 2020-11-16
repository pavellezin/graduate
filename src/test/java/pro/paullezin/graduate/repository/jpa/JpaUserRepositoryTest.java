package pro.paullezin.graduate.repository.jpa;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pro.paullezin.graduate.model.User;
import pro.paullezin.graduate.web.user.AdminRestController;

import static pro.paullezin.graduate.UserTestData.USER_MATCHER;
import static pro.paullezin.graduate.UserTestData.getNew;

@ContextConfiguration(locations = {"classpath:spring/spring-app.xml", "classpath:spring/spring-jpa.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class JpaUserRepositoryTest {

    @Autowired
    private AdminRestController controller;

    @Test
    public void save() {
        User newUser = getNew();
        User created = controller.create(new User(newUser));
        int newId = created.getId();
        newUser.setId(newId);
        USER_MATCHER.assertMatch(created, newUser);
        USER_MATCHER.assertMatch(controller.get(newId), newUser);
        Assert.assertEquals(4, controller.getAll().size());
    }

    @Test
    public void delete() {
    }

    @Test
    public void get() {
    }

    @Test
    public void getByEmail() {
    }

    @Test
    public void getAll() {
    }

}