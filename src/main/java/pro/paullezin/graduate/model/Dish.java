package pro.paullezin.graduate.model;

import java.math.BigDecimal;
import java.util.Date;

public class Dish extends AbstractBaseEntity {
    private Date dateTime = new Date();

    private String description;

    private BigDecimal price;

    private Restaurant restaurant;

    public Dish() {
    }

    public Dish(Dish d) {
        this(d.getId(), d.getDateTime(), d.getRestaurant(), d.getDescription(), d.getPrice());
    }

    public Dish(Integer id, Date dateTime, Restaurant restaurant, String description, BigDecimal price) {
        super(id);
        this.dateTime = dateTime;
        this.restaurant = restaurant;
        this.description = description;
        this.price = price;
    }

    public Dish(Date dateTime, Restaurant restaurant, String description, BigDecimal price) {
        this(null, dateTime, restaurant, description, price);
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
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
                ", dateTime=" + dateTime +
                ", restaurantName='" + restaurant.getName() + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}
