package pro.paullezin.graduate.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pro.paullezin.graduate.model.Rating;
import pro.paullezin.graduate.repository.RatingRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;

@Repository
@Transactional(readOnly = true)
public class JpaRatingRepository implements RatingRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Rating save(Rating rating, int userId) {
        return null;
    }

    @Override
    @Transactional
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
}
