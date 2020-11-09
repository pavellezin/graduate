package pro.paullezin.graduate.service;

import org.springframework.util.Assert;
import pro.paullezin.graduate.model.Rating;
import pro.paullezin.graduate.repository.RatingRepository;

import java.time.LocalDate;
import java.util.List;

import static pro.paullezin.graduate.util.ValidationUtil.checkNotFoundWithId;

public class RatingService {

//    private final RatingRepository repository;
//
//    public RatingService(RatingRepository repository) {
//        this.repository = repository;
//    }
//
//    public Rating get(int id, int userId) {
//        return checkNotFoundWithId(repository.get(id, userId), id);
//    }
//
//    public void delete(int id, int userId) {
//        checkNotFoundWithId(repository.delete(id, userId), id);
//    }
//
//    public List<Rating> getAll(int restaurantId, LocalDate date, int userId) {
//        return repository.getAll(restaurantId, date, userId);
//    }
//
//    public void update(Rating rating, int restaurantId, LocalDate date, int userId) {
//        Assert.notNull(rating, "rating must not be null");
//        checkNotFoundWithId(repository.save(rating, restaurantId, date, userId), rating.getId());
//    }
//
//    public Rating create(Rating rating, int restaurantId, LocalDate date, int userId) {
//        Assert.notNull(rating, "rating must not be null");
//        return repository.save(rating, restaurantId, date, userId);
//    }

}