package pro.paullezin.graduate.to;

import pro.paullezin.graduate.model.Dish;
import pro.paullezin.graduate.model.Restaurant;

import java.util.List;

public class RestaurantTo {
    private final Integer id;

    private final String name;

    private final String address;

    private final String web;

    private final List<Dish> menu;

    private final Double rating;

    private final boolean withMenu;

    public RestaurantTo(Integer id, String name, String address, String web, List<Dish> menu, Double rating, boolean withMenu) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.web = web;
        this.menu = menu;
        this.rating = rating;
        this.withMenu = withMenu;
    }

    public RestaurantTo(Restaurant restaurant, boolean withMenu) {
        this(restaurant.getId(), restaurant.getName(), restaurant.getAddress(), restaurant.getWeb(), restaurant.getMenu(), restaurant.getRating(), withMenu);
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getWeb() {
        return web;
    }

    public List<Dish> getMenu() {
        return menu;
    }

    public Double getRating() {
        return rating;
    }

    public boolean isWithMenu() {
        return withMenu;
    }

    @Override
    public String toString() {
        return "RestaurantTo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rating=" + rating +
                ", withMenu=" + withMenu +
                '}';
    }
}