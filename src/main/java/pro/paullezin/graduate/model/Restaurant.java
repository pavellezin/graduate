package pro.paullezin.graduate.model;

import java.util.List;

public class Restaurant extends AbstractNamedEntity {

    private String address;

    private String web;

    private List<Dish> menu;

    private Double rating;

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

    public boolean haveMenu() {
        return this.menu != null && !this.menu.isEmpty();
    }

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
