package pro.paullezin.graduate.util;

import pro.paullezin.graduate.model.Rating;
import pro.paullezin.graduate.model.Restaurant;
import pro.paullezin.graduate.model.Role;
import pro.paullezin.graduate.model.User;
import pro.paullezin.graduate.to.RestaurantTo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RestaurantMain {


    public static void main(String[] args) {
        List<Rating> ratings1 = new ArrayList<>();
        List<Rating> ratings2 = new ArrayList<>();

        User user1 = new User(1, "user1", "user1@mail.ru", "pass1", Role.USER);
        User user2 = new User(2, "user2", "user2@mail.ru", "pass2", Role.USER);
        User user3 = new User(9, "admin", "admin@mail.ru", "pass3", Role.ADMIN);

        Restaurant restaurant1 = new Restaurant(3, "Restaurant 1", "Kaslinskaya str", "www.restaurant-1.com", ratings1);
        Restaurant restaurant2 = new Restaurant(4, "Restaurant 2", "Khudyakova str", "www.restaurant-2.com", ratings2);

        Rating rating1 = new Rating(5, restaurant1, user1, LocalDateTime.now(), Rating.FIVE_STARS);
        Rating rating2 = new Rating(6, restaurant1, user2, LocalDateTime.now(), Rating.FOUR_STARS);
        Rating rating3 = new Rating(7, restaurant2, user1, LocalDateTime.now(), Rating.FOUR_STARS);
        Rating rating4 = new Rating(8, restaurant2, user2, LocalDateTime.now(), Rating.ONE_STAR);
        Rating rating5 = new Rating(10, restaurant2, user3, LocalDateTime.now(), Rating.FIVE_STARS);

        List<Restaurant> restaurantList = new ArrayList<>();
        restaurantList.add(restaurant1);
        restaurantList.add(restaurant2);

        ratings1.add(rating1);
        ratings1.add(rating2);

        ratings2.add(rating3);
        ratings2.add(rating4);
        ratings2.add(rating5);

        ratings1.clear();

        List<RestaurantTo> restaurantTos = RestaurantUtil.getTos(restaurantList, LocalDateTime.now(), restaurant -> true);
        System.out.println("restaurant1 = " + restaurant1);
        System.out.println("restaurant2 = " + restaurant2);
        System.out.println("restaurantTos = " + restaurantTos);
    }
}
