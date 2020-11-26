package pro.paullezin.graduate.repository.jpa;

import org.hibernate.jpa.QueryHints;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pro.paullezin.graduate.model.Rating;
import pro.paullezin.graduate.model.User;
import pro.paullezin.graduate.repository.RatingRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
        rating.setUser(em.getReference(User.class, userId));
        if (rating.isNew()) {
            em.persist(rating);
            return rating;
        } else if (get(rating.getId(), userId) == null) {
            return null;
        }
        return em.merge(rating);
    }

    @Override
    @Transactional
    public boolean delete(int id, int userId) {
        return em.createNamedQuery(Rating.DELETE)
                .setParameter("id", id)
                .setParameter("userId", userId)
                .executeUpdate() != 0;
    }

    @Override
    public Rating get(int id, int userId) {
        Rating rating = em.find(Rating.class, id);
        return rating != null && rating.getUser().getId() == userId ? rating : null;
    }

    @Override
    public Double getAverageVote(int restaurantId, LocalDate date) {
        Query query = em.createNamedQuery(Rating.GET_AVERAGE_VOTE)
                .setParameter("restaurantId", restaurantId)
                .setParameter("date", date);
        return (Double) query.getSingleResult();
    }

    @Override
    public Rating getVoteForUserAndRestaurant(int restaurantId, int userId, LocalDate date) {
        List<Rating> ratings = em.createNamedQuery(Rating.GET_CURRENT_USER_RATING_FOR_RESTAURANT, Rating.class)
                .setParameter("restaurantId", restaurantId)
                .setParameter("userId", userId)
                .setParameter("date", date)
                .setHint(QueryHints.HINT_PASS_DISTINCT_THROUGH, false)
                .getResultList();
        return DataAccessUtils.singleResult(ratings);
    }
}
