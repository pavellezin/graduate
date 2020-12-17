package pro.paullezin.graduate.service;

import org.springframework.stereotype.Service;
import pro.paullezin.graduate.model.Rating;
import pro.paullezin.graduate.model.Restaurant;
import pro.paullezin.graduate.repository.RatingRepository;
import pro.paullezin.graduate.repository.RestaurantRepository;
import pro.paullezin.graduate.web.SecurityUtil;

import java.time.LocalDate;
import java.util.List;

@Service
public class VoteService {
    private final RatingRepository ratingRepository;

    public VoteService(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    public void setUserVoteToRestaurant(List<Restaurant> restaurants) {
        int userId = SecurityUtil.authUserId();
        restaurants.forEach(r ->
        {
            final Rating rating = ratingRepository.getVoteForUserAndRestaurant(r.getId(), userId, LocalDate.now());
            if (rating != null) r.setUserRating(rating.getVote());
        });
    }

}
