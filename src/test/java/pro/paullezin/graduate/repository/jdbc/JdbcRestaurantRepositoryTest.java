package pro.paullezin.graduate.repository.jdbc;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pro.paullezin.graduate.model.Restaurant;
import pro.paullezin.graduate.util.exception.NotFoundException;
import pro.paullezin.graduate.web.restaurant.RestaurantRestController;

@ContextConfiguration(locations = {"classpath:spring/spring-app.xml", "classpath:spring/spring-db.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class JdbcRestaurantRepositoryTest {

    @Autowired
    private RestaurantRestController controller;

    @Test
    public void save() {
        Restaurant newRestaurant = new Restaurant(null, "New restaurant", "Addr", "newrestaurant.com");
        controller.create(newRestaurant);
        Assert.assertEquals(1, controller.getAll().stream().filter(r -> r.getName().contains("New restaurant")).count());
    }

    @Test
    public void delete() {
        int count = controller.getAll().size();
        controller.delete(100004);
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