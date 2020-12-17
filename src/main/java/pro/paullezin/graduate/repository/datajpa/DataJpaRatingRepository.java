package pro.paullezin.graduate.repository.datajpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pro.paullezin.graduate.model.Rating;
import pro.paullezin.graduate.repository.RatingRepository;

import java.time.LocalDate;

@Repository
public class DataJpaRatingRepository implements RatingRepository {

    private final CrudRatingRepository crudRepository;
    private final CrudUserRepository crudUserRepository;

    public DataJpaRatingRepository(CrudRatingRepository crudRepository, CrudUserRepository crudUserRepository) {
        this.crudRepository = crudRepository;
        this.crudUserRepository = crudUserRepository;
    }

    @Override
    @Transactional
    public Rating save(Rating rating, int userId) {
        if (!rating.isNew() && get(rating.getId(), userId) == null) {
            return null;
        }
        rating.setUser(crudUserRepository.getOne(userId));
        return crudRepository.save(rating);
    }

    @Override
    public boolean delete(int id, int userId) {
        return crudRepository.delete(id, userId) != 0;
    }

    @Override
    public Rating get(int id, int userId) {
        return crudRepository.findById(id).filter(rating -> rating.getUser().getId() == userId).orElse(null);
    }

    @Override
    public Double getAverageVote(int restaurantId, LocalDate currentDate) {
        return crudRepository.getAverageVote(restaurantId, currentDate);
    }

    @Override
    public Rating getVoteForUserAndRestaurant(int restaurantId, int userId, LocalDate currentDate) {
        return crudRepository.getVoteForUserAndRestaurant(restaurantId, userId, currentDate);
    }

}
