package pro.paullezin.graduate.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Dish extends AbstractBaseEntity {
    private LocalDate date = LocalDate.now();

    private String description;

    private BigDecimal price;

    private Restaurant restaurant;

    public Dish() {
    }

    public Dish(Dish d) {
        this(d.getId(), d.getDate(), d.getRestaurant(), d.getDescription(), d.getPrice());
    }

    public Dish(Integer id, LocalDate date, Restaurant restaurant, String description, BigDecimal price) {
        super(id);
        this.date = date;
        this.restaurant = restaurant;
        this.description = description;
        this.price = price;
    }

    public Dish(LocalDate dateTime, Restaurant restaurant, String description, BigDecimal price) {
        this(null, dateTime, restaurant, description, price);
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", date=" + date +
//                ", restaurantName='" + restaurant.getName() + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}
