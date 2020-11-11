package pro.paullezin.graduate.web.dish;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import pro.paullezin.graduate.model.Dish;
import pro.paullezin.graduate.repository.DishRepository;

import java.time.LocalDate;
import java.util.List;

import static pro.paullezin.graduate.util.ValidationUtil.*;

@Controller
public class DishRestController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    private final DishRepository repository;

    public DishRestController(DishRepository repository) {
        this.repository = repository;
    }

    public Dish get(int id) {
        log.info("get dish with id {}", id);
        return checkNotFoundWithId(repository.get(id), id);
    }

    public List<Dish> getAll(int restaurantId, LocalDate date) {
        log.info("getAll dishes for restaurant {} for date {}", restaurantId, date);
        return repository.getAll(restaurantId, date);
    }

    public void delete(int id) {
        log.info("delete dish with id {}", id);
        checkNotFoundWithId(repository.delete(id), id);
    }

    public void update(Dish dish, int id) {
        log.info("update dish {} with id={}", dish, id);
        assureIdConsistent(dish, id);
        Assert.notNull(dish, "dish must not be null");
        checkNotFoundWithId(repository.save(dish), dish.getId());
    }

    public Dish create(Dish dish) {
        log.info("create dish {}", dish);
        checkNew(dish);
        Assert.notNull(dish, "dish must not be null");
        return repository.save(dish);
    }
}
