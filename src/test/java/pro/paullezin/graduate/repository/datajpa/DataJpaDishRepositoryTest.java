package pro.paullezin.graduate.repository.datajpa;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pro.paullezin.graduate.model.Dish;
import pro.paullezin.graduate.model.Restaurant;
import pro.paullezin.graduate.web.dish.DishRestController;
import pro.paullezin.graduate.web.restaurant.RestaurantRestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

@ContextConfiguration(locations = {
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-mvc.xml",
        "classpath:spring/spring-datajpa.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class DataJpaDishRepositoryTest {

    @Autowired
    private DishRestController controller;
    @Autowired
    RestaurantRestController restoController;

    @Test
    public void saveAndDelete() {
        assertEquals(3, controller.getAll(100003).size());
        Restaurant restaurant = restoController.get(100003);
        Dish newDish = new Dish(LocalDate.now(), restaurant, "New Dish", BigDecimal.valueOf(5.5));
        Dish dish = controller.create(newDish, 100003);
        Assert.assertEquals(newDish, dish);
        assertEquals(4, controller.getAll(100003).size());
        controller.delete(dish.getId());
        assertEquals(3, controller.getAll(100003).size());
    }

    @Test
    public void get() {
        Dish dish = controller.get(100006);
        Assert.assertTrue(dish.getDescription().contains("Salat"));
    }

    @Test
    public void getAll() {
        final List<Dish> dishes = controller.getAll(100003);
        Assert.assertEquals(3, dishes.size());
    }
}