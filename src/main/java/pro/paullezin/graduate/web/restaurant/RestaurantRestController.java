package pro.paullezin.graduate.web.restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import pro.paullezin.graduate.model.Restaurant;
import pro.paullezin.graduate.repository.RestaurantRepository;
import pro.paullezin.graduate.repository.UserRepository;
import pro.paullezin.graduate.service.VoteService;
import pro.paullezin.graduate.to.RestaurantTo;
import pro.paullezin.graduate.web.SecurityUtil;

import javax.validation.Valid;
import java.util.List;

import static pro.paullezin.graduate.util.RestaurantUtil.getAdminTos;
import static pro.paullezin.graduate.util.RestaurantUtil.getUserTos;
import static pro.paullezin.graduate.util.ValidationUtil.*;
import static pro.paullezin.graduate.web.SecurityUtil.authUserIsAdmin;

@RestController
@RequestMapping(value = "/api/restaurants", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantRestController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    private final RestaurantRepository repository;

    private final UserRepository userRepository;

    private final VoteService voteService;

    public RestaurantRestController(RestaurantRepository repository, UserRepository userRepository, VoteService voteService) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.voteService = voteService;
    }

    @GetMapping("/{id}")
    public Restaurant get(@PathVariable int id) {
        log.info("get restaurant with id {}", id);
        return checkNotFoundWithId(repository.get(id), id);
    }

    @GetMapping
    public List<RestaurantTo> getAll() {
        int userId = SecurityUtil.authUserId();
        log.info("getAll restaurants for user {}", userId);
        List<Restaurant> restaurants = repository.getAll();
        voteService.setUserVoteToRestaurant(restaurants);
        return authUserIsAdmin(userRepository.get(userId)) ? getAdminTos(restaurants) : getUserTos(restaurants);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("delete restaurant with id {}", id);
        int userId = SecurityUtil.authUserId();
        assureAdmin(userRepository.get(userId));
        checkNotFoundWithId(repository.delete(id), id);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@RequestBody Restaurant restaurant, @PathVariable int id) {
        log.info("update restaurant {} with id={}", restaurant, id);
        Assert.notNull(restaurant, "restaurant must not be null");
        int userId = SecurityUtil.authUserId();
        assureAdmin(userRepository.get(userId));
        assureIdConsistent(restaurant, id);
        checkNotFoundWithId(repository.save(restaurant), restaurant.getId());
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Restaurant create(@Valid @RequestBody Restaurant restaurant) {
        log.info("create restaurant {}", restaurant);
        Assert.notNull(restaurant, "restaurant must not be null");
        int userId = SecurityUtil.authUserId();
        assureAdmin(userRepository.get(userId));
        checkNew(restaurant);
        return repository.save(restaurant);
    }

}