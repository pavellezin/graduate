package pro.paullezin.graduate.repository.jdbc;

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

@ContextConfiguration(locations = {"classpath:spring/spring-app.xml", "classpath:spring/spring-db.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class JdbcDishRepositoryTest {

    @Autowired
    private DishRestController controller;
    @Autowired
    private RestaurantRestController restoController;

    @Test
    public void save() {
        Restaurant restaurant = restoController.get(100003);
        Dish dish = new Dish(null, LocalDate.of(2020, 11, 9), restaurant, "New Dish", BigDecimal.valueOf(15));
        Dish newDish = controller.create(dish);
        int newDishId = newDish.getId();
        Assert.assertEquals(newDish, controller.get(newDishId));
    }

    @Test
    public void delete() {
        controller.delete(100005);
        Assert.assertEquals(0, controller.getAll(100003, LocalDate.of(2020, 11, 8)).size());
    }

    @Test
    public void get() {
        Dish dish = controller.get(100006);
        Assert.assertEquals("Salat", dish.getDescription());
    }

    @Test
    public void getAll() {
        Assert.assertEquals(3, controller.getAll(100003, LocalDate.of(2020, 11, 10)).size());
    }
}