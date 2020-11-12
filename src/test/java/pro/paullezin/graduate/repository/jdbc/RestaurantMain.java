package pro.paullezin.graduate.repository.jdbc;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pro.paullezin.graduate.model.*;
import pro.paullezin.graduate.to.RestaurantTo;
import pro.paullezin.graduate.util.RestaurantUtil;
import pro.paullezin.graduate.web.dish.DishRestController;
import pro.paullezin.graduate.web.rating.RatingRestController;
import pro.paullezin.graduate.web.restaurant.RestaurantRestController;
import pro.paullezin.graduate.web.user.AdminRestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class RestaurantMain {

    public static void main(String[] args) {
        List<Dish> menu1 = new ArrayList<>();

        Restaurant restaurant1 = new Restaurant(3, "Restaurant 1", "Kaslinskaya str", "www.restaurant-1.com", 3.0);
        Restaurant restaurant2 = new Restaurant(4, "Restaurant 2", "Khudyakova str", "www.restaurant-2.com", null);

        Dish dish1 = new Dish(11, LocalDate.now(), restaurant1, "Dish1", BigDecimal.valueOf(10));
        Dish dish2 = new Dish(12, LocalDate.now(), restaurant1, "Dish1", BigDecimal.valueOf(20));
        menu1.add(dish1);
        menu1.add(dish2);

        List<Restaurant> restaurantList = new ArrayList<>();
        restaurantList.add(restaurant1);
        restaurantList.add(restaurant2);

        restaurant1.setMenu(menu1);
        restaurant1.setUserRating(3);

        List<RestaurantTo> restaurantUserTos = RestaurantUtil.getUserTos(restaurantList);
        List<RestaurantTo> restaurantAdminTos = RestaurantUtil.getAdminTos(restaurantList);
        System.out.println("menu1 = " + menu1);
        System.out.println("restaurant1 = " + restaurant1);
        System.out.println("restaurant2 = " + restaurant2);
        System.out.println("UserTos = " + restaurantUserTos);
        System.out.println("AdminTos = " + restaurantAdminTos);
        System.out.println(restaurant1.haveUserVote() + "  " + RestaurantUtil.canUserVote(restaurant1));
        System.out.println(restaurant2.haveUserVote() + "  " + RestaurantUtil.canUserVote(restaurant2));

        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml", "spring/spring-db.xml")) {
//            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
            AdminRestController adminController = appCtx.getBean(AdminRestController.class);
            RestaurantRestController restaurantRestController = appCtx.getBean(RestaurantRestController.class);
            DishRestController dishRestController = appCtx.getBean(DishRestController.class);
            RatingRestController ratingRestController = appCtx.getBean(RatingRestController.class);
            User newUser = adminController.create(new User(null, "123Name", "email@mail.ru", "password", Role.ADMIN));
            System.out.println();
            System.out.println(restaurantRestController.getAll());
            System.out.println(dishRestController.getAll(100003, LocalDate.now().minus(1, ChronoUnit.DAYS)));
            dishRestController.delete(100006);
            System.out.println(dishRestController.getAll(100003, LocalDate.now()));
            Dish dish = dishRestController.get(100007);
            dish.setDescription("New description");
            dishRestController.update(dish, 100007);
            System.out.println(dishRestController.getAll(100003, LocalDate.now()));
            System.out.println(ratingRestController.getAverageVote(100003, LocalDate.of(2020, 11, 9)));
            Restaurant basilio = restaurantRestController.get(100003);
            Rating newVote = new Rating(null, basilio, newUser, LocalDate.of(2020,11,9),4);
            ratingRestController.create(newVote);
            System.out.println(ratingRestController.getAverageVote(100003, LocalDate.of(2020, 11, 9)));
            newVote.setVote(2);
            ratingRestController.update(newVote,100017);
            System.out.println(ratingRestController.getAverageVote(100003, LocalDate.of(2020, 11, 9)));
        }
    }
}
