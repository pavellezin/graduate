package pro.paullezin.graduate.repository.datajpa;

import org.springframework.stereotype.Repository;
import pro.paullezin.graduate.model.Rating;
import pro.paullezin.graduate.repository.RatingRepository;

import java.time.LocalDate;

@Repository
public class DataJpaRatingRepository implements RatingRepository {
    @Override
    public Rating save(Rating rating, int userId) {
        return null;
    }

    @Override
    public boolean delete(int id, int userId) {
        return false;
    }

    @Override
    public Rating get(int id, int userId) {
        return null;
    }

    @Override
    public Double getAverageVote(int restaurantId, LocalDate date) {
        return null;
    }

    @Override
    public Rating getVoteForUserAndRestaurant(int restaurantId, int userId, LocalDate date) {
        return null;
    }
}
