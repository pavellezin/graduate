package pro.paullezin.graduate.repository;

import pro.paullezin.graduate.model.Rating;

import java.time.LocalDate;

public interface RatingRepository {
    // null if not found, when updated
    Rating save(Rating rating, int userId);

    // false if not found
    boolean delete(int id);

    // null if not found
    Rating get(int id);

    // null if not found
    Double getAll(int restaurantId, LocalDate date);
}
