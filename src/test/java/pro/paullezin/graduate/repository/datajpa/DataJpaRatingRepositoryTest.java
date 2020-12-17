package pro.paullezin.graduate.repository.datajpa;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pro.paullezin.graduate.model.Rating;
import pro.paullezin.graduate.web.rating.RatingRestController;

import java.time.LocalDate;

@ContextConfiguration(locations = {"classpath:spring/spring-app.xml", "classpath:spring/spring-datajpa.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class DataJpaRatingRepositoryTest {

    @Autowired
    private RatingRestController controller;

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
}