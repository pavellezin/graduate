package pro.paullezin.graduate.to;

import pro.paullezin.graduate.model.Dish;

import java.util.List;
import java.util.OptionalDouble;

public class RestaurantTo {
    private final Integer id;

    private final String name;

    private final String address;

    private final String web;

    private final List<Dish> menu;

    private final OptionalDouble rating;

    public RestaurantTo(Integer id, String name, String address, String web, List<Dish> menu, OptionalDouble rating) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.web = web;
        this.menu = menu;
        this.rating = rating;
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

    public OptionalDouble getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return "RestaurantTo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", web='" + web + '\'' +
                ", menu=" + !getMenu().isEmpty() +
                ", rating=" + rating +
                '}';
    }
}