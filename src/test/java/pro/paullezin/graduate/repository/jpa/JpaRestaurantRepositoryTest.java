package pro.paullezin.graduate.repository.jpa;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pro.paullezin.graduate.model.Restaurant;
import pro.paullezin.graduate.web.restaurant.RestaurantRestController;

@ContextConfiguration(locations = {"classpath:spring/spring-app.xml", "classpath:spring/spring-jpa.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class JpaRestaurantRepositoryTest {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private RestaurantRestController controller;

    @Test
    public void get() {
        Restaurant restaurant = controller.get(100003);
        log.info("get restaurant = {}",restaurant);
    }

}