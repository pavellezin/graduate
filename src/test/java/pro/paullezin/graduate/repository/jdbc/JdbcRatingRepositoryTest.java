package pro.paullezin.graduate.repository.jdbc;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pro.paullezin.graduate.model.Rating;
import pro.paullezin.graduate.model.Restaurant;
import pro.paullezin.graduate.model.User;
import pro.paullezin.graduate.web.rating.RatingRestController;
import pro.paullezin.graduate.web.restaurant.RestaurantRestController;
import pro.paullezin.graduate.web.user.AdminRestController;

import java.time.LocalDate;

public class JdbcRatingRepositoryTest {
    private static ConfigurableApplicationContext appCtx;
    private static RatingRestController controller;
    private static RestaurantRestController restoController;
    private static AdminRestController userController;

    @BeforeClass
    public static void beforeClass() {
        appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml", "spring/spring-db.xml");
        controller = appCtx.getBean(RatingRestController.class);
        restoController = appCtx.getBean(RestaurantRestController.class);
        userController = appCtx.getBean(AdminRestController.class);
    }

    @AfterClass
    public static void afterClass() {
        appCtx.close();
    }

    @Test
    public void save() {
        User user = userController.getByMail("admin@paullezin.pro");
        Restaurant restaurant = restoController.get(100003);
        Rating rating = new Rating(null, restaurant, user, LocalDate.now(), 5);
        controller.create(rating);
    }

    @Test
    public void delete() {
        controller.delete(100011);
    }

    @Test
    public void get() {
        Rating rating = controller.get(100012);
        Assert.assertEquals(Integer.valueOf(4), rating.getVote());
    }

    @Test
    public void getAverageVote() {
        Assert.assertEquals(Double.valueOf(4.5),
                controller.getAverageVote(100004, LocalDate.of(2020, 11, 9)));
    }
}