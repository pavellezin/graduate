package pro.paullezin.graduate.repository.jpa;

import org.hibernate.jpa.QueryHints;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pro.paullezin.graduate.model.Rating;
import pro.paullezin.graduate.repository.RatingRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;

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

    @Override
    public Rating getVoteForUserAndRestaurant(int restaurantId, int userId, LocalDate date) {
        List<Rating> ratings = em.createNamedQuery(Rating.GET_CURRENT_USER_RATING_FOR_RESTAURANT)
                .setParameter("restaurantId", restaurantId)
                .setParameter("userId", userId)
                .setParameter("date", date)
                .setHint(QueryHints.HINT_PASS_DISTINCT_THROUGH, false)
                .getResultList();
        return DataAccessUtils.singleResult(ratings);
    }
}
