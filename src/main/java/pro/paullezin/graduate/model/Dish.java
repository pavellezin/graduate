package pro.paullezin.graduate.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

@NamedQueries({
        @NamedQuery(name = Dish.DELETE, query = "DELETE FROM Dish d WHERE d.id=:id"),
        @NamedQuery(name = Dish.ALL, query = "SELECT d FROM Dish d WHERE d.restaurant.id=:restaurantId AND d.date=:date"),
})
@Entity
@Table(name = "dishes")
public class Dish extends AbstractBaseEntity {

    public static final String DELETE = "Dish.delete";
    public static final String ALL = "Dish.getAll";

    @Column(name = "date", nullable = false)
    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonIgnore
    private LocalDate date = LocalDate.now();

    @Column(name = "description", nullable = false)
    @NotBlank
    @Size(min = 2, max = 120)
    private String description;

    @Column(name = "price", nullable = false)
    @NotNull
    @Range(min = 0, max = 500)
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
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

    public Dish(String description, BigDecimal price) {
        this(null, LocalDate.now(), null, description, price);
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
