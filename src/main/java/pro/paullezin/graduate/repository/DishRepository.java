package pro.paullezin.graduate.repository;

import pro.paullezin.graduate.model.Dish;

import java.time.LocalDate;
import java.util.List;

public interface DishRepository {
    // null if not found, when updated
    Dish save(Dish dish);

    // false if not found
    boolean delete(int id);

    // null if not found
    Dish get(int id);

    // ORDERED id asc
    List<Dish> getAll(int restaurantId, LocalDate date);
}
