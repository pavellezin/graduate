package pro.paullezin.graduate.service;

import org.springframework.util.Assert;
import pro.paullezin.graduate.model.Dish;
import pro.paullezin.graduate.repository.DishRepository;

import java.time.LocalDate;
import java.util.List;

import static pro.paullezin.graduate.util.ValidationUtil.checkNotFoundWithId;

public class DishService {
    private final DishRepository repository;

    public DishService(DishRepository repository) {
        this.repository = repository;
    }

    public Dish get(int id, int userId) {
        return checkNotFoundWithId(repository.get(id, userId), id);
    }

    public void delete(int id, int userId) {
        checkNotFoundWithId(repository.delete(id, userId), id);
    }

    public List<Dish> getAll(int restaurantId, LocalDate date, int userId) {
        return repository.getAll(restaurantId, date, userId);
    }

    public void update(Dish dish, int restaurantId, LocalDate date, int userId) {
        Assert.notNull(dish, "dish must not be null");
        checkNotFoundWithId(repository.save(dish, restaurantId, date, userId), dish.getId());
    }

    public Dish create(Dish dish, int restaurantId, LocalDate date, int userId) {
        Assert.notNull(dish, "dish must not be null");
        return repository.save(dish, restaurantId, date, userId);
    }
}