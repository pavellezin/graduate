package pro.paullezin.graduate.util;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pro.paullezin.graduate.model.*;
import pro.paullezin.graduate.to.RestaurantTo;
import pro.paullezin.graduate.web.user.AdminRestController;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RestaurantMain {

    public static void main(String[] args) {
        List<Dish> menu1 = new ArrayList<>();

//        User user1 = new User(1, "user1", "user1@mail.ru", "pass1", Role.USER);
//        User user2 = new User(2, "user2", "user2@mail.ru", "pass2", Role.USER);

        Restaurant restaurant1 = new Restaurant(3, "Restaurant 1", "Kaslinskaya str", "www.restaurant-1.com", 3.0);
        Restaurant restaurant2 = new Restaurant(4, "Restaurant 2", "Khudyakova str", "www.restaurant-2.com", null);

        Dish dish1 = new Dish(11,LocalDateTime.now(),restaurant1,"Dish1", BigDecimal.valueOf(10));
        Dish dish2 = new Dish(12,LocalDateTime.now(),restaurant1,"Dish1", BigDecimal.valueOf(20));
        menu1.add(dish1);
        menu1.add(dish2);

        List<Restaurant> restaurantList = new ArrayList<>();
        restaurantList.add(restaurant1);
        restaurantList.add(restaurant2);

        restaurant1.setMenu(menu1);
        restaurant1.setUserRating(3);

        List<RestaurantTo> restaurantUserTos = RestaurantUtil.getUserTos(restaurantList);
        List<RestaurantTo> restaurantAdminTos = RestaurantUtil.getAdminTos(restaurantList);
        System.out.println("restaurant1 = " + restaurant1);
        System.out.println("restaurant2 = " + restaurant2);
        System.out.println("UserTos = " + restaurantUserTos);
        System.out.println("AdminTos = " + restaurantAdminTos);
        System.out.println(restaurant1.haveUserVote() + "  " + RestaurantUtil.canUserVote(restaurant1));
        System.out.println(restaurant2.haveUserVote() + "  " + RestaurantUtil.canUserVote(restaurant2));

        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml", "spring/spring-db.xml")) {
            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
            AdminRestController adminController = appCtx.getBean(AdminRestController.class);
            adminController.create(new User(null, "123Name", "email@mail.ru", "password", Role.ADMIN));
            System.out.println();
            System.out.println(adminController.getAll());
            System.out.println();
            adminController.delete(100000);
            System.out.println(adminController.getAll());
        }
    }
}
