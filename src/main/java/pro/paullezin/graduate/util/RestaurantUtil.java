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

    private static RestaurantTo createTo(Restaurant restaurant, Double rating) {
        return new RestaurantTo(restaurant, rating);
    }

    public static List<RestaurantTo> getTos(Collection<Restaurant> restaurants, LocalDateTime dateTime, Predicate<Restaurant> filter) {
        Map<Integer, Double> restaurantRatings = restaurants.stream()
                .collect(
                        Collectors.groupingBy(Restaurant::getId,
                                Collectors.flatMapping(r -> r.getRatings().stream(), Collectors.averagingDouble(v -> v.getVote())))
                );
        return restaurants.stream()
                .filter(filter)
                .map(restaurant -> createTo(restaurant, restaurantRatings.get(restaurant.getId())))
                .collect(Collectors.toList());
    }
}

