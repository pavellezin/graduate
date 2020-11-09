package pro.paullezin.graduate.model;

import java.time.LocalDate;

public class Rating extends AbstractBaseEntity {
    public static final Integer ONE_STAR = 1;
    public static final Integer TWO_STARS = 2;
    public static final Integer THREE_STARS = 3;
    public static final Integer FOUR_STARS = 4;
    public static final Integer FIVE_STARS = 5;

    private Restaurant restaurant;

    private User user;

    private LocalDate date;

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