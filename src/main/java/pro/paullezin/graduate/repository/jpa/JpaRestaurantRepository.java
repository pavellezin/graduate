package pro.paullezin.graduate.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pro.paullezin.graduate.model.Restaurant;
import pro.paullezin.graduate.repository.RestaurantRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaRestaurantRepository implements RestaurantRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Restaurant save(Restaurant restaurant) {
        return null;
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return false;
    }

    @Override
    public Restaurant get(int id) {return em.find(Restaurant.class, id);}

    @Override
    public List<Restaurant> getAll() {
        return null;
    }
}
