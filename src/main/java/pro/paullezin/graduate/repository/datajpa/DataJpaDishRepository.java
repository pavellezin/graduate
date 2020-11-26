package pro.paullezin.graduate.repository.datajpa;

import org.springframework.stereotype.Repository;
import pro.paullezin.graduate.model.Dish;
import pro.paullezin.graduate.repository.DishRepository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class DataJpaDishRepository implements DishRepository {
    @Override
    public Dish save(Dish dish) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public Dish get(int id) {
        return null;
    }

    @Override
    public List<Dish> getAll(int restaurantId, LocalDate date) {
        return null;
    }
}
