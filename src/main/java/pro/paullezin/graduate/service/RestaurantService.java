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

    public Restaurant get(int id) {
        return checkNotFoundWithId(repository.get(id), id);
    }

    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id), id);
    }

    public List<Restaurant> getAll() {
        return repository.getAll();
    }

    public List<Restaurant> getWithMenu() { return repository.getWithMenu(); }

    public void update(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        checkNotFoundWithId(repository.save(restaurant), restaurant.getId());
    }

    public Restaurant create(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        return repository.save(restaurant);
    }
}
