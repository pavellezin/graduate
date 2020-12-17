package pro.paullezin.graduate.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.transaction.annotation.Transactional;
import pro.paullezin.graduate.model.Rating;

import java.time.LocalDate;

@Transactional(readOnly = true)
public interface CrudRatingRepository extends JpaRepository<Rating, Integer> {

    @Query("SELECT r FROM Rating r WHERE r.user.id=:userId AND r.restaurant.id =:restaurantId AND r.date=:currentDate")
    Rating getVoteForUserAndRestaurant(@Param("restaurantId") int restaurantId, @Param("userId") int usertId, @Param("currentDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate currentDate);

    @Query("SELECT AVG(r.vote) FROM Rating r WHERE r.restaurant.id=:restaurantId AND r.date=:currentDate")
    Double getAverageVote(@Param("restaurantId") int restaurantId, @Param("currentDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate currentDate);
}
