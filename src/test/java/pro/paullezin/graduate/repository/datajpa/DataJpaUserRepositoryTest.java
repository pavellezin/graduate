package pro.paullezin.graduate.repository.datajpa;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pro.paullezin.graduate.model.User;
import pro.paullezin.graduate.util.exception.NotFoundException;
import pro.paullezin.graduate.web.user.AdminRestController;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static pro.paullezin.graduate.UserTestData.*;

@ContextConfiguration(locations = {
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-mvc.xml",
        "classpath:spring/spring-datajpa.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class DataJpaUserRepositoryTest {

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
    public void get() {
        User user = controller.get(ADMIN_ID);
        USER_MATCHER.assertMatch(user, ADMIN);
    }

    @Test
    public void getNotFound() throws Exception {
        assertThrows(NotFoundException.class, () -> controller.get(1));
    }

    @Test
    public void getByEmail() {
        User user = controller.getByMail("user2@paullezin.pro");
        USER_MATCHER.assertMatch(user, USER);
    }

    @Test
    public void getAll() {
        List<User> all = controller.getAll();
        Assert.assertEquals(3, all.size());
        Assert.assertTrue(all.contains(USER)&&all.contains(ADMIN));
    }
}