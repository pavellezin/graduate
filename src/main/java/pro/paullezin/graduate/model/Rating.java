package pro.paullezin.graduate.model;

import java.time.LocalDateTime;

public class Rating extends AbstractBaseEntity {
    public static final Integer ONE_STAR = 1;
    public static final Integer TWO_STARS = 2;
    public static final Integer THREE_STARS = 3;
    public static final Integer FOUR_STARS = 4;
    public static final Integer FIVE_STARS = 5;

    private Restaurant restaurant;

    private User user;

    private LocalDateTime dateTime;

    private Integer vote;

    public Rating() {
    }

    public Rating(Integer id, Restaurant restaurant, User user, LocalDateTime dateTime, Integer vote) {
        super(id);
        this.restaurant = restaurant;
        this.user = user;
        this.dateTime = dateTime;
        this.vote = vote;
    }

    public Rating(Rating r) {
        this(r.getId(), r.getRestaurant(), r.getUser(), r.getDateTime(), r.getVote());
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

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
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
                ", restaurantName='" + restaurant.getName() + '\'' +
                ", userName='" + user.getName() + '\'' +
                ", dateTime=" + dateTime +
                ", vote=" + vote +
                '}';
    }
}