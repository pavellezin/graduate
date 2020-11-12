package pro.paullezin.graduate.repository.jdbc;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pro.paullezin.graduate.model.Restaurant;
import pro.paullezin.graduate.util.exception.NotFoundException;
import pro.paullezin.graduate.web.restaurant.RestaurantRestController;

public class JdbcRestaurantRepositoryTest {
    private static ConfigurableApplicationContext appCtx;
    private static RestaurantRestController controller;

    @BeforeClass
    public static void beforeClass() {
        appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml", "spring/spring-db.xml");
        controller = appCtx.getBean(RestaurantRestController.class);
    }

    @AfterClass
    public static void afterClass() {
        appCtx.close();
    }

    @Test
    public void save() {
        Restaurant newRestaurant = new Restaurant(null, "New restaurant", "Addr", "newrestaurant.com");
        controller.create(newRestaurant);
        Assert.assertEquals(1, controller.getAll().stream().filter(r -> r.getName().contains("New restaurant")).count());
    }

    @Test
    public void delete() {
        int count = controller.getAll().size();
        controller.delete(100003);
        Assert.assertEquals(count - 1, controller.getAll().size());
    }

    @Test
    public void get() {
        Restaurant restaurant = controller.get(100003);
        Assert.assertEquals("Basilio", restaurant.getName());
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound() throws Exception {
        controller.get(100013);
    }

    @Test
    public void getAll() {
        controller.getAll();
    }
}