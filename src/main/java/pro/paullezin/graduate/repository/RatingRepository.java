package pro.paullezin.graduate.repository;

import pro.paullezin.graduate.model.Rating;

import java.time.LocalDate;
import java.util.List;

public interface RatingRepository {
    // null if not found, when updated
    Rating save(Rating rating, int restaurantId, LocalDate date, int userId);

    // false if not found
    boolean delete(int id, int userId);

    // null if not found
    Rating get(int id, int userId);

    // ORDERED id asc
    List<Rating> getAll(int restaurantId, LocalDate date, int userId);
}
