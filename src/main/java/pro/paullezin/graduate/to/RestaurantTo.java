package pro.paullezin.graduate.to;

import pro.paullezin.graduate.model.Dish;

import java.util.List;

public class RestaurantTo {
    private final Integer id;

    private final String name;

    private final String address;

    private final String web;

    private final List<Dish> menu;

    private final Double rating;

    private final Integer userRating;

    private final boolean withMenu;

    private final boolean userCanVote;

    public RestaurantTo(Integer id, String name, String address, String web, List<Dish> menu, Double rating, Integer userRating, boolean withMenu, boolean userCanVote) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.web = web;
        this.menu = menu;
        this.rating = rating;
        this.userRating = userRating;
        this.withMenu = withMenu;
        this.userCanVote = userCanVote;
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

    public Integer getUserRating() { return userRating; }

    public boolean isWithMenu() {
        return withMenu;
    }

    public boolean isUserCanVote() { return userCanVote; }

    @Override
    public String toString() {
        return "RestaurantTo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rating=" + rating +
                ", userRating=" + userRating +
                ", withMenu=" + withMenu +
                ", userCanVote=" + userCanVote +
                '}';
    }

}