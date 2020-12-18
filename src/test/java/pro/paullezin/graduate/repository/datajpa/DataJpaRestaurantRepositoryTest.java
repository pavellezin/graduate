package pro.paullezin.graduate.repository.datajpa;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pro.paullezin.graduate.model.Restaurant;
import pro.paullezin.graduate.to.RestaurantTo;
import pro.paullezin.graduate.web.SecurityUtil;
import pro.paullezin.graduate.web.restaurant.RestaurantRestController;

import java.util.List;

@ContextConfiguration(locations = {
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-mvc.xml",
        "classpath:spring/spring-datajpa.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class DataJpaRestaurantRepositoryTest {

    @Autowired
    private RestaurantRestController controller;

    @Test
    public void saveAndDelete() {
        Assert.assertEquals(3, controller.getAll().size());
        Restaurant newRestaurant = new Restaurant("New Resto", "Red Square", "www.newresto.com");
        final Restaurant restaurant = controller.create(newRestaurant);
        Assert.assertEquals(4, controller.getAll().size());
        Assert.assertTrue(restaurant.getAddress().contains("Red"));
        controller.delete(restaurant.getId());
        Assert.assertEquals(3, controller.getAll().size());
    }

    @Test
    public void get() {
        Assert.assertTrue(controller.get(100017).getName().contains("New"));
    }

    @Test
    public void getAll() {
        SecurityUtil.setAuthUserId(100001);
        List<RestaurantTo> restaurants = controller.getAll();
        SecurityUtil.setAuthUserId(100000);
        Assert.assertEquals(2, restaurants.size());
    }
}