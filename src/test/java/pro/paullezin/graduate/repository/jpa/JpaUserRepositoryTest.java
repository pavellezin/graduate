package pro.paullezin.graduate.repository.jpa;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pro.paullezin.graduate.model.Role;
import pro.paullezin.graduate.model.User;
import pro.paullezin.graduate.web.user.AdminRestController;

@ContextConfiguration(locations = {"classpath:spring/spring-app.xml", "classpath:spring/spring-jpa.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class JpaUserRepositoryTest {

    @Autowired
    private AdminRestController controller;

    @Test
    public void save() {
        User newUser = getNew();
        User created = controller.create(new User(newUser));
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

    private User getNew() {
        return new User(null, "New", "new@gmail.com", "newPass", Role.USER);
    }
}