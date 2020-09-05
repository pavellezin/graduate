package pro.paullezin.graduate.util;

import pro.paullezin.graduate.model.Rating;
import pro.paullezin.graduate.model.Restaurant;
import pro.paullezin.graduate.to.RestaurantTo;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class RestaurantUtil {

    private RestaurantUtil() {
    }

    private static RestaurantTo createTo(Restaurant restaurant, boolean withMenu, boolean userCanVote) {
        return new RestaurantTo(restaurant, withMenu, userCanVote);
    }

    public static List<RestaurantTo> filteredTos(Collection<Restaurant> restaurants, Predicate<Restaurant> filter) {
        return restaurants.stream()
                .filter(filter)
                .map(restaurant -> createTo(restaurant, restaurant.haveMenu(), canUserVote(restaurant)))
                .collect(Collectors.toList());
    }

    public static List<RestaurantTo> getAdminTos(Collection<Restaurant> restaurants) {
        return filteredTos(restaurants, restaurant -> true);
    }

    public static List<RestaurantTo> getUserTos(Collection<Restaurant> restaurants) {
        return filteredTos(restaurants, restaurant -> restaurant.haveMenu());
    }

    protected static boolean canUserVote(Restaurant restaurant){
        return DateTimeUtil.checkTimeIfUserCanVoteNow() || !restaurant.haveUserVote();
    }

}


