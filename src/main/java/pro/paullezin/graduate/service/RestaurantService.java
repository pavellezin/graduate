package pro.paullezin.graduate.service;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import pro.paullezin.graduate.model.Restaurant;
import pro.paullezin.graduate.repository.RestaurantRepository;

import java.util.List;

import static pro.paullezin.graduate.util.ValidationUtil.checkNotFoundWithId;

//@Service
public class RestaurantService {

    private final RestaurantRepository repository;

    public RestaurantService(RestaurantRepository repository) {
        this.repository = repository;
    }

    public Restaurant get(int id, int userId) {
        return checkNotFoundWithId(repository.get(id, userId), id);
    }

    public void delete(int id, int userId) {
        checkNotFoundWithId(repository.delete(id, userId), id);
    }

    public List<Restaurant> getAll(int userId) {
        return repository.getAll(userId);
    }

    public List<Restaurant> getWithMenu(int userId) { return repository.getWithMenu(userId); }

    public void update(Restaurant restaurant, int userId) {
        Assert.notNull(restaurant, "restaurant must not be null");
        checkNotFoundWithId(repository.save(restaurant, userId), restaurant.getId());
    }

    public Restaurant create(Restaurant restaurant, int userId) {
        Assert.notNull(restaurant, "restaurant must not be null");
        return repository.save(restaurant, userId);
    }
}
