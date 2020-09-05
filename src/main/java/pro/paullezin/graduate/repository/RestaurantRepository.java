package pro.paullezin.graduate.repository;

import pro.paullezin.graduate.model.Restaurant;

import java.util.List;

public interface RestaurantRepository {
    // null if not found, when updated
    Restaurant save(Restaurant restaurant, int userId);

    // false if not found
    boolean delete(int id, int userId);

    // null if not found
    Restaurant get(int id, int userId);

    // ORDERED rating, name desc
    List<Restaurant> getAll(int userId);

    // ORDERED rating, name desc
    List<Restaurant> getWithMenu(int userId);
}