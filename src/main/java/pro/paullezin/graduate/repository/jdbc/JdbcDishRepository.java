package pro.paullezin.graduate.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pro.paullezin.graduate.model.Dish;
import pro.paullezin.graduate.repository.DishRepository;

import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JdbcDishRepository implements DishRepository {

    private static final BeanPropertyRowMapper<Dish> ROW_MAPPER = BeanPropertyRowMapper.newInstance(Dish.class);

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final SimpleJdbcInsert insertDish;

    @Autowired
    public JdbcDishRepository(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.insertDish = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("dishes")
                .usingGeneratedKeyColumns("id");

        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    @Transactional
    public Dish save(Dish dish) {
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(dish);

        if (dish.isNew()) {
            Number newKey = insertDish.executeAndReturnKey(parameterSource);
            dish.setId(newKey.intValue());
        } else if (namedParameterJdbcTemplate.update(
                "UPDATE dishes SET description=:description, price=:price" +
                        " WHERE id=:id", parameterSource) == 0) {
            return null;
        }
        return dish;
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return jdbcTemplate.update("DELETE FROM dishes WHERE id=?", id) != 0;
    }

    @Override
    public Dish get(int id) {
        List<Dish> dishes = jdbcTemplate.query("SELECT * FROM dishes WHERE id=?", ROW_MAPPER, id);
        return DataAccessUtils.singleResult(dishes);
    }

    @Override
    public List<Dish> getAll(int restaurantId, LocalDate date) {
        return jdbcTemplate.query(
                "SELECT * FROM dishes WHERE restaurant_id=?  AND date = ? ORDER BY id",
                ROW_MAPPER, restaurantId, date);
    }
}
