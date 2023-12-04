package writing.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import writing.board.dto.PostWrittenDTO;
import writing.board.entity.Image;
import writing.board.entity.Review;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("select no, regDate, review_content, id, post_no from Review where no = :no")
    List<Object[]> getReviewWithAll(Long no);

    @Query("select no, regDate, review_content, id, post_no from Review")
    List<Object[]> getReviewWithAll();

    @Query(value = "select r.no, r.regDate, r.review_content, r.id, r.post_no from Review r "
            +"left join PostWritten p on p.no = post_no "
            + "where post_no = :no", nativeQuery = true)
    List<Review> findByPostWritten(Long no);
}
