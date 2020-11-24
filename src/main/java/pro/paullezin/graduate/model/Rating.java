package pro.paullezin.graduate.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@NamedQueries({
        @NamedQuery(name = Rating.DELETE, query = "DELETE FROM Rating r WHERE r.id=:id AND r.user.id=:userId"),
        @NamedQuery(name = Rating.GET_AVERAGE_VOTE, query = "SELECT AVG(r.vote) FROM Rating r WHERE r.restaurant.id=:restaurantId AND r.date=:date"),
        @NamedQuery(name = Rating.GET_CURRENT_USER_RATING_FOR_RESTAURANT,
                query = "SELECT r FROM Rating r WHERE r.user.id=:userId AND r.restaurant.id=:restaurantId AND r.date=:date"),
})

@Entity
@Table(name = "ratings")
public class Rating extends AbstractBaseEntity {
    public static final Integer ONE_STAR = 1;
    public static final Integer TWO_STARS = 2;
    public static final Integer THREE_STARS = 3;
    public static final Integer FOUR_STARS = 4;
    public static final Integer FIVE_STARS = 5;

    public static final String GET_CURRENT_USER_RATING_FOR_RESTAURANT = "Rating.getCurrent";
    public static final String DELETE = "Rating.delete";
    public static final String GET_AVERAGE_VOTE = "Rating.average";

    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "date", nullable = false, columnDefinition = "timestamp default current_date")
    @NotNull
    private LocalDate date;

    @Column(name = "vote", nullable = false)
    @NotNull
    @Range(min = 1, max = 5)
    private Integer vote;

    public Rating() {
    }

    public Rating(Integer id, Restaurant restaurant, User user, LocalDate date, Integer vote) {
        super(id);
        this.restaurant = restaurant;
        this.user = user;
        this.date = date;
        this.vote = vote;
    }

    public Rating(Rating r) {
        this(r.getId(), r.getRestaurant(), r.getUser(), r.getDate(), r.getVote());
    }

    public Rating(Restaurant restaurant, User user, LocalDate date, Integer vote) {
        this(null, restaurant, user, date, vote);
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getVote() {
        return vote;
    }

    public void setVote(Integer vote) {
        this.vote = vote;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "id=" + id +
//                ", restaurantName='" + restaurant.getName() + '\'' +
//                ", userName='" + user.getName() + '\'' +
                ", date=" + date +
                ", vote=" + vote +
                '}';
    }
}