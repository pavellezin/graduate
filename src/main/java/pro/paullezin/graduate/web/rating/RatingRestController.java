package pro.paullezin.graduate.web.rating;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import pro.paullezin.graduate.model.Rating;
import pro.paullezin.graduate.repository.RatingRepository;
import pro.paullezin.graduate.web.SecurityUtil;

import java.time.LocalDate;

import static pro.paullezin.graduate.util.ValidationUtil.*;

@Controller
public class RatingRestController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    private final RatingRepository repository;

    public RatingRestController(RatingRepository repository) {
        this.repository = repository;
    }

    public Rating get(int id) {
        int userId = SecurityUtil.authUserId();
        log.info("get rating with id {}", id);
        return checkNotFoundWithId(repository.get(id, userId), id);
    }

    public Double getAverageVote(int restaurantId, LocalDate date) {
        log.info("getAll rating for restaurant {} for date {}", restaurantId, date);
        return repository.getAverageVote(restaurantId, date);
    }

    public void delete(int id) {
        int userId = SecurityUtil.authUserId();
        log.info("delete rating with id {}", id);
        checkNotFoundWithId(repository.delete(id, userId), id);
    }

    public void update(Rating rating, int id) {
        int userId = SecurityUtil.authUserId();
        log.info("update rating {} with id={}", rating, id);
        Assert.notNull(rating, "rating must not be null");
        assureIdConsistent(rating, id);
        checkNotFoundWithId(repository.save(rating, userId), rating.getId());
    }

    public Rating create(Rating rating) {
        int userId = SecurityUtil.authUserId();
        log.info("create rating {}", rating);
        Assert.notNull(rating, "rating must not be null");
        checkNew(rating);
        return repository.save(rating, userId);
    }
}
