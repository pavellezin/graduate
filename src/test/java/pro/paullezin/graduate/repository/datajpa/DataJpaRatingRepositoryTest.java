package pro.paullezin.graduate.repository.datajpa;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pro.paullezin.graduate.model.Rating;
import pro.paullezin.graduate.model.Restaurant;
import pro.paullezin.graduate.model.User;
import pro.paullezin.graduate.util.exception.NotFoundException;
import pro.paullezin.graduate.web.SecurityUtil;
import pro.paullezin.graduate.web.rating.RatingRestController;
import pro.paullezin.graduate.web.restaurant.RestaurantRestController;
import pro.paullezin.graduate.web.user.AdminRestController;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ContextConfiguration(locations = {
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-mvc.xml",
        "classpath:spring/spring-datajpa.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class DataJpaRatingRepositoryTest {

    @Autowired
    private RatingRestController controller;
    @Autowired
    private RestaurantRestController restoController;
    @Autowired
    private AdminRestController userController;

    @Test
    public void getAverageVote() {
        Double averageVote = controller.getAverageVote(100003, LocalDate.now());
        Assert.assertEquals(Double.valueOf(5), averageVote);
    }

    @Test
    public void getVoteForUserAndRestaurant() {
        Rating userRating = controller.getVoteForUserAndRestaurant(100004, 100000);
        if (userRating != null) {
            Assert.assertEquals(Integer.valueOf(5), userRating.getVote());
        }
    }

    @Test
    public void get() {
        Rating userRating = controller.get(100012);
        Assert.assertEquals(Integer.valueOf(5), userRating.getVote());
    }

    @Test
    public void getNotFound() {
        NotFoundException ex = assertThrows(NotFoundException.class,
                () -> controller.get(100114));
        String msg = ex.getMessage();
        assertTrue(msg.contains("Not found entity with id=100114"));
    }

    @Test
    public void save() {
        User user = userController.get(SecurityUtil.authUserId());
        Restaurant restaurant = restoController.get(100003);
        Rating rating = new Rating(null, restaurant, user, LocalDate.now(), 5);
        controller.create(rating);
        Assert.assertEquals(Integer.valueOf(5), controller.getVoteForUserAndRestaurant(restaurant.getId(), user.getId()).getVote());
    }
}