package pro.paullezin.graduate.repository;

import pro.paullezin.graduate.model.Rating;

import java.util.List;

public interface RatingRepository {
    // null if not found, when updated
    Rating save(Rating rating, int userId, int restaurantId);

    // false if not found
    boolean delete(int id, int userId, int restaurantId);

    // null if not found
    Rating get(int id, int userId, int restaurantId);

    // ORDERED id asc
    List<Rating> getAll(int restaurantId);
}
