package pro.paullezin.graduate.web.restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import pro.paullezin.graduate.model.Restaurant;
import pro.paullezin.graduate.repository.RestaurantRepository;
import pro.paullezin.graduate.repository.UserRepository;
import pro.paullezin.graduate.to.RestaurantTo;
import pro.paullezin.graduate.web.SecurityUtil;

import java.util.List;

import static pro.paullezin.graduate.util.RestaurantUtil.getAdminTos;
import static pro.paullezin.graduate.util.RestaurantUtil.getUserTos;
import static pro.paullezin.graduate.util.ValidationUtil.*;
import static pro.paullezin.graduate.web.SecurityUtil.authUserIsAdmin;

@Controller
public class RestaurantRestController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    private final RestaurantRepository repository;

    private final UserRepository userRepository;

    public RestaurantRestController(RestaurantRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    public Restaurant get(int id) {
        log.info("get restaurant with id {}", id);
        return checkNotFoundWithId(repository.get(id), id);
    }

    public List<RestaurantTo> getAll() {
        int userId = SecurityUtil.authUserId();
        log.info("getAll restaurants for user {}", userId);
        List<Restaurant> restaurants = repository.getAll();
        return authUserIsAdmin(userRepository.get(userId)) ? getAdminTos(restaurants) : getUserTos(restaurants);
    }

    public void delete(int id) {
        log.info("delete restaurant with id {}", id);
        checkNotFoundWithId(repository.delete(id), id);
    }

    public void update(Restaurant restaurant, int id) {
        log.info("update restaurant {} with id={}", restaurant, id);
        assureIdConsistent(restaurant, id);
        Assert.notNull(restaurant, "restaurant must not be null");
        checkNotFoundWithId(repository.save(restaurant), restaurant.getId());
    }

    public Restaurant create(Restaurant restaurant) {
        log.info("create restaurant {}", restaurant);
        checkNew(restaurant);
        Assert.notNull(restaurant, "restaurant must not be null");
        return repository.save(restaurant);
    }

}