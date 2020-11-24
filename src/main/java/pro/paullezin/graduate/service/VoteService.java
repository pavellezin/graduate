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

    private final RestaurantRepository restaurantRepository;

    public VoteService(RatingRepository ratingRepository, RestaurantRepository restaurantRepository) {
        this.ratingRepository = ratingRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public void setUserVoteToRestaurant(List<Restaurant> restaurants) {
        int userId = SecurityUtil.authUserId();
        restaurants.forEach(r ->
        {
            final Rating vote = ratingRepository.getVoteForUserAndRestaurant(r.getId(), userId, LocalDate.now());
            if (vote!=null) r.setUserRating(vote.getVote());
//            r.setUserRating(ratingRepository.getVoteForUserAndRestaurant(r.getId(), userId, LocalDate.now()).getVote());
            restaurantRepository.save(r);
        });
    }

}
