package pro.paullezin.graduate.repository.datajpa;

import org.springframework.stereotype.Repository;
import pro.paullezin.graduate.model.Dish;
import pro.paullezin.graduate.repository.DishRepository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class DataJpaDishRepository implements DishRepository {
    private final CrudDishRepository crudRepository;

    public DataJpaDishRepository(CrudDishRepository crudRepository) {
        this.crudRepository = crudRepository;
    }

    @Override
    public Dish save(Dish dish) {
        return crudRepository.save(dish);
    }

    @Override
    public boolean delete(int id) {
        return crudRepository.delete(id) != 0;
    }

    @Override
    public Dish get(int id) {
        return crudRepository.findById(id).orElse(null);
    }

    @Override
    public List<Dish> getAll(int restaurantId, LocalDate currentDate) {
        return crudRepository.getAll(restaurantId, currentDate);
    }
}
