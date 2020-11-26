package pro.paullezin.graduate.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import pro.paullezin.graduate.model.Rating;

@Transactional(readOnly = true)
public interface CrudRatingRepository extends JpaRepository<Rating, Integer> {
}
