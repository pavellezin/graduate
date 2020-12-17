package pro.paullezin.graduate.repository.jdbc;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pro.paullezin.graduate.model.Rating;
import pro.paullezin.graduate.model.Restaurant;
import pro.paullezin.graduate.model.User;
import pro.paullezin.graduate.web.SecurityUtil;
import pro.paullezin.graduate.web.rating.RatingRestController;
import pro.paullezin.graduate.web.restaurant.RestaurantRestController;
import pro.paullezin.graduate.web.user.AdminRestController;

import java.time.LocalDate;

@ContextConfiguration(locations = {"classpath:spring/spring-app.xml", "classpath:spring/spring-db.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class JdbcRatingRepositoryTest {

    @Autowired
    private RatingRestController controller;
    @Autowired
    private RestaurantRestController restoController;
    @Autowired
    private AdminRestController userController;

    @Test
    public void save() {
        User user = userController.get(SecurityUtil.authUserId());
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
        Assert.assertEquals(Integer.valueOf(5), rating.getVote());
    }

    @Test
    public void getAverageVote() {
        Assert.assertEquals(Double.valueOf(4),
                controller.getAverageVote(100004, LocalDate.now()));
    }

    @Test
    public void getVoteForUserAndRestaurant() {
        Rating vote = controller.getVoteForUserAndRestaurant(100003, SecurityUtil.authUserId() + 1);
        Assert.assertEquals(Integer.valueOf(5), vote.getVote());
    }
}