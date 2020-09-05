package pro.paullezin.graduate.repository;

import pro.paullezin.graduate.model.Dish;

import java.time.LocalDate;
import java.util.List;

public interface DishRepository {
    // null if not found, when updated
    Dish save(Dish dish, int restaurantId, LocalDate date, int userId);

    // false if not found
    boolean delete(int id, int userId);

    // null if not found
    Dish get(int id, int userId);

    // ORDERED dateTime asc
    List<Dish> getAll(int restaurantId, LocalDate date, int userId);
}
