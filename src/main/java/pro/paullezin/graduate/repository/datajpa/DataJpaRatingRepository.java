package pro.paullezin.graduate.repository.datajpa;

import org.springframework.stereotype.Repository;
import pro.paullezin.graduate.model.Rating;
import pro.paullezin.graduate.repository.RatingRepository;

import java.time.LocalDate;

@Repository
public class DataJpaRatingRepository implements RatingRepository {

    private final CrudRatingRepository crudRepository;

    public DataJpaRatingRepository(CrudRatingRepository crudRepository) {
        this.crudRepository = crudRepository;
    }

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
        return crudRepository.findById(id).filter(rating -> rating.getUser().getId() == userId).orElse(null);
    }

    @Override
    public Double getAverageVote(int restaurantId, LocalDate currentDate) {
        return crudRepository.getAverageVote(restaurantId,currentDate);
    }

    @Override
    public Rating getVoteForUserAndRestaurant(int restaurantId, int userId, LocalDate currentDate) {
        return crudRepository.getVoteForUserAndRestaurant(restaurantId, userId, currentDate);
    }

}
