package writing.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import writing.board.entity.Image;
import writing.board.entity.Recommendation;

import java.util.List;

public interface RecommendationRepository extends JpaRepository<Recommendation, Long> {

    @Query("select no, regDate, id, post_no, push from Recommendation where no = :no")
    List<Object[]> getRecommendationWithAll(Long no);

    @Query("select no, regDate, id, post_no, push from Recommendation")
    List<Object[]> getRecommendationWithAll();
}
