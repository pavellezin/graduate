package pro.paullezin.graduate.repository.jpa;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pro.paullezin.graduate.web.rating.RatingRestController;

import java.time.LocalDate;

import static org.junit.Assert.*;

@ContextConfiguration(locations = {"classpath:spring/spring-app.xml", "classpath:spring/spring-jpa.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class JpaRatingRepositoryTest {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private RatingRestController controller;

    @Test
    public void getAverageVote() {
        Double averageVote = controller.getAverageVote(100003, LocalDate.now());
        Assert.assertEquals(Double.valueOf(5), averageVote);
    }

    @Test
    public void get() {
        controller.get(100012);
    }
}