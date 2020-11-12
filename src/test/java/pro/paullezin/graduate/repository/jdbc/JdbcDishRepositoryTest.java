package pro.paullezin.graduate.repository.jdbc;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pro.paullezin.graduate.model.Dish;
import pro.paullezin.graduate.model.Restaurant;
import pro.paullezin.graduate.web.dish.DishRestController;
import pro.paullezin.graduate.web.restaurant.RestaurantRestController;

import java.math.BigDecimal;
import java.time.LocalDate;

public class JdbcDishRepositoryTest {
    private static ConfigurableApplicationContext appCtx;
    private static DishRestController controller;
    private static RestaurantRestController restoController;

    @BeforeClass
    public static void beforeClass() {
        appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml", "spring/spring-db.xml");
        controller = appCtx.getBean(DishRestController.class);
        restoController = appCtx.getBean(RestaurantRestController.class);
    }

    @AfterClass
    public static void afterClass() {
        appCtx.close();
    }

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