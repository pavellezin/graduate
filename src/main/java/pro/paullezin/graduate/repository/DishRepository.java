package pro.paullezin.graduate.repository;

import pro.paullezin.graduate.model.Dish;

import java.util.List;

public interface DishRepository {
    // null if not found, when updated
    Dish save(Dish dish, int restaurantId);

    // false if not found
    boolean delete(int id, int restaurantId);

    // null if not found
    Dish get(int id, int restaurantId);

    // ORDERED dateTime asc
    List<Dish> getAll(int restaurantId);
}
