package pro.paullezin.graduate.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import pro.paullezin.graduate.model.Rating;
import pro.paullezin.graduate.repository.RatingRepository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class JdbcRatingRepository implements RatingRepository {
    private static final BeanPropertyRowMapper<Rating> ROW_MAPPER = BeanPropertyRowMapper.newInstance(Rating.class);

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final SimpleJdbcInsert insertRating;

    @Autowired
    public JdbcRatingRepository(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.insertRating = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("ratings")
                .usingGeneratedKeyColumns("id");

        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Rating save(Rating rating, int userId) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", rating.getId())
                .addValue("date", rating.getDate())
                .addValue("vote", rating.getVote())
                .addValue("restaurant_id", rating.getRestaurant().getId())
                .addValue("user_id", userId);

        if (rating.isNew()) {
            Number newKey = insertRating.executeAndReturnKey(map);
            rating.setId(newKey.intValue());
        } else if (namedParameterJdbcTemplate.update(
                "UPDATE ratings SET vote=:vote" +
                        " WHERE id=:id AND user_id=:user_id", map) == 0) {
            return null;
        }
        return rating;
    }

    @Override
    public boolean delete(int id, int userId) {
        return jdbcTemplate.update("DELETE FROM dishes WHERE id=? AND user_id=?", id, userId) != 0;
    }

    @Override
    public Rating get(int id, int userId) {
        List<Rating> ratings = jdbcTemplate.query("SELECT * FROM ratings WHERE id=? AND user_id=?", ROW_MAPPER, id, userId);
        return DataAccessUtils.singleResult(ratings);
    }

    @Override
    public Double getAverageVote(int restaurantId, LocalDate date) {
        return jdbcTemplate.queryForObject("SELECT AVG(Cast(vote as DOUBLE)) FROM ratings WHERE restaurant_id=? AND date=?", Double.class, restaurantId, date);
    }
}
