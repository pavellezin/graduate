package pro.paullezin.graduate.repository.jpa;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pro.paullezin.graduate.model.Dish;
import pro.paullezin.graduate.web.SecurityUtil;
import pro.paullezin.graduate.web.dish.DishRestController;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static pro.paullezin.graduate.UserTestData.USER_ID;

@ContextConfiguration(locations = {"classpath:spring/spring-app.xml", "classpath:spring/spring-jpa.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class JpaDishRepositoryTest {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private DishRestController controller;

    @Test
    public void save() {
        Dish dish = controller.get(100008);
        log.info("get desert {}", dish);
        dish.setDescription("Ice cream");
        controller.update(dish, 100008);
        dish = controller.get(100008);
        log.info("get desert {}", dish);
        Assert.assertTrue(dish.getDescription().contains("Ice cream"));
    }

    @Test
    public void deleteByUser() throws Exception {
        SecurityUtil.setAuthUserId(USER_ID);
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> controller.delete(100008));
        String msg = ex.getMessage();
        assertTrue(msg.contains("user must be admin"));
        SecurityUtil.setAuthUserId(100000);
    }

    @Test
    public void get() {
        Dish dish = controller.get(100007);
        String description = dish.getDescription();
        assertTrue(description.contains("Soup"));
    }

    @Test
    public void getAll() {
        List<Dish> dishes = controller.getAll(100003, LocalDate.now());
        assertEquals(3, dishes.size());
    }
}