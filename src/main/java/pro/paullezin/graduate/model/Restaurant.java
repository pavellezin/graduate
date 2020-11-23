package pro.paullezin.graduate.model;

import org.hibernate.annotations.Formula;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "restaurants", uniqueConstraints = {@UniqueConstraint(columnNames = "web", name = "restaurants_unique_web_idx")})
public class Restaurant extends AbstractNamedEntity {

    @Column(name = "address", nullable = false)
    @NotBlank
    @Size(max = 200)
    private String address;

    @Column(name = "web", nullable = false, unique = true)
    @URL
    @NotBlank
    @Size(max = 500)
    private String web;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "restaurant")
    @Where(clause = "date = now()")
    private List<Dish> menu;

    @Formula("(SELECT AVG(Cast(r.vote as DOUBLE)) FROM ratings r WHERE r.restaurant_id=id)")
    @Where(clause = "date = now()")
    private Double rating;

    @Transient
    private Integer userRating;

    public Restaurant() {
    }

    public Restaurant(Restaurant r) {
        this(r.getId(), r.getName(), r.getAddress(), r.getWeb());
    }

    public Restaurant(Integer id, String name, String address, String web) {
        super(id, name);
        this.address = address;
        this.web = web;
    }

    public Restaurant(Integer id, String name, String address, String web, Double rating) {
        super(id, name);
        this.address = address;
        this.web = web;
        this.rating = rating;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public List<Dish> getMenu() {
        return menu;
    }

    public void setMenu(List<Dish> menu) {
        this.menu = menu;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Integer getUserRating() { return userRating; }

    public void setUserRating(Integer userRating) { this.userRating = userRating; }

    public boolean haveMenu() {
        return this.menu != null && !this.menu.isEmpty();
    }

    public boolean haveUserVote() {return this.userRating != null; }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", web='" + web + '\'' +
                ", rating=" + rating +
                '}';
    }

}
