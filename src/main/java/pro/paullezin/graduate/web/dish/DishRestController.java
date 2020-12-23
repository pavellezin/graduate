package pro.paullezin.graduate.web.dish;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import pro.paullezin.graduate.model.Dish;
import pro.paullezin.graduate.repository.DishRepository;
import pro.paullezin.graduate.repository.UserRepository;
import pro.paullezin.graduate.repository.datajpa.CrudRestaurantRepository;
import pro.paullezin.graduate.web.SecurityUtil;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

import static pro.paullezin.graduate.util.ValidationUtil.*;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class DishRestController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    private final DishRepository repository;
    private final CrudRestaurantRepository restaurantRepository;
    private final UserRepository userRepository;

    public DishRestController(DishRepository repository, CrudRestaurantRepository restaurantRepository, UserRepository userRepository) {
        this.repository = repository;
        this.restaurantRepository = restaurantRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/dishes/{id}")
    public Dish get(@PathVariable int id) {
        log.info("get dish with id {}", id);
        return checkNotFoundWithId(repository.get(id), id);
    }

    @GetMapping("/restaurants/{restaurantId}/dishes")
    public List<Dish> getAll(@PathVariable int restaurantId) {
        LocalDate date = LocalDate.now();
        log.info("getAll dishes for restaurant {} for date {}", restaurantId, date);
        return repository.getAll(restaurantId, date);
    }

    @DeleteMapping("/dishes/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("delete dish with id {}", id);
        int userId = SecurityUtil.authUserId();
        assureAdmin(userRepository.get(userId));
        checkNotFoundWithId(repository.delete(id), id);
    }

    @PutMapping(value = "/dishes/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@RequestBody Dish dish, @PathVariable int id) {
        log.info("update dish {} with id={}", dish, id);
        Assert.notNull(dish, "dish must not be null");
        int userId = SecurityUtil.authUserId();
        assureAdmin(userRepository.get(userId));
        assureIdConsistent(dish, id);
        checkNotFoundWithId(repository.save(dish), dish.getId());
    }

    @PutMapping(value = "/restaurants/{restaurantId}/dishes", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Dish create(@Valid @RequestBody Dish dish, @PathVariable int restaurantId) {
        log.info("create dish {}", dish);
        Assert.notNull(dish, "dish must not be null");
        int userId = SecurityUtil.authUserId();
        assureAdmin(userRepository.get(userId));
        checkNew(dish);
        dish.setRestaurant(restaurantRepository.getOne(restaurantId));
        return repository.save(dish);
    }
}
