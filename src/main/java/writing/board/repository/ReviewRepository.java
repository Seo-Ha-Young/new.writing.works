package writing.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import writing.board.entity.Image;
import writing.board.entity.Review;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("select no, regDate, review_content, id, post_no from Review where no = :no")
    List<Object[]> getReviewWithAll(Long no);

    @Query("select no, regDate, review_content, id, post_no from Review")
    List<Object[]> getReviewWithAll();
}
