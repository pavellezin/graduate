package pro.paullezin.graduate.repository.datajpa;

import org.springframework.stereotype.Repository;
import pro.paullezin.graduate.model.Restaurant;
import pro.paullezin.graduate.repository.RestaurantRepository;

import java.util.List;

@Repository
public class DataJpaRestaurantRepository implements RestaurantRepository {
    @Override
    public Restaurant save(Restaurant restaurant) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public Restaurant get(int id) {
        return null;
    }

    @Override
    public List<Restaurant> getAll() {
        return null;
    }
}